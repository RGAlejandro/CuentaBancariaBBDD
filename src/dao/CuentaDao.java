package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import excepciones.CampoVacioException;
import excepciones.CuentaBancariaException;
import excepciones.DniException;
import modelo.CuentaBancaria;

public class CuentaDao {
	private Connection conn;
	
	public CuentaDao(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
	}
	public List<CuentaBancaria> getCuentas(String sql) throws SQLException, DniException, CuentaBancariaException, CampoVacioException{
		List<CuentaBancaria> cuentasBanco=new ArrayList<CuentaBancaria>();
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		CuentaBancaria cuenta=null;
		while(rs.next()) {
			String numCuenta=rs.getString("numCuenta");
			String titular=rs.getString("titular");
			String dni=rs.getString("dni");
			double saldo=rs.getDouble("saldo");
			LocalDate fechaOperacion=rs.getDate("fechaOperacion").toLocalDate();
			boolean bloqueado=rs.getBoolean("bloqueado");
			cuenta=new CuentaBancaria(numCuenta, titular, dni, saldo, fechaOperacion, bloqueado);
			cuentasBanco.add(cuenta);
			cuenta=null;
		}
		return cuentasBanco;
	}
}
