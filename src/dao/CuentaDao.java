package dao;

import java.sql.Connection;
import java.sql.Date;
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
			boolean bloqueada=rs.getBoolean("bloqueada");
			cuenta=new CuentaBancaria(numCuenta, titular, dni, saldo, fechaOperacion, bloqueada);
			cuentasBanco.add(cuenta);
			cuenta=null;
		}
		return cuentasBanco;
	}
	public boolean añadirCuenta(CuentaBancaria cuenta) throws SQLException {
		boolean agregado=false;
		String sql="insert into cuentas values (?,?,?,?,?,?)";
		String numCuenta=cuenta.getNumCuenta();
		String titular=cuenta.getTitular();
		String dni=cuenta.getDni();
		double saldo=cuenta.getSaldo();
		Date fechaOperacion=Date.valueOf(cuenta.getFechaOperacion());
		boolean bloqueada=cuenta.isBloqueado();
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, numCuenta);
		pst.setString(2, titular);
		pst.setString(3, dni);
		pst.setDouble(4, saldo);
		pst.setDate(5, fechaOperacion);
		pst.setBoolean(6, bloqueada);
		pst.executeUpdate();
		agregado=true;
		return agregado;
		
	}
	public boolean eliminarCuenta(String numCuenta) throws SQLException {
		boolean eliminado=false;
		String campo="numCuenta";
		String sql="delete from cuentas where "+campo+" ='"+numCuenta+"'";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.executeUpdate();
		eliminado=true;
		return eliminado;
		
	}
	public boolean bloquearCuenta(String numCuenta) throws SQLException {
		boolean cambiado=false;
		String sql="Update cuentas Set saldo=? , bloqueada=? where numCuenta=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setDouble(1, 0);
		pst.setBoolean(2, true);
		pst.setString(3, numCuenta);
		pst.executeUpdate();
		cambiado=true;
		return cambiado;
		
	}
}
