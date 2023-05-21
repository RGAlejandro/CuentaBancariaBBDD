package controler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import excepciones.CampoVacioException;
import excepciones.CuentaBancariaException;
import excepciones.DniException;
import modelo.CuentaBancaria;

public class CuentaControler {
	private List<CuentaBancaria> cuentas=new ArrayList<CuentaBancaria>();
	private Connection conn;
	public CuentaControler(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
	}
	public List<CuentaBancaria> getCuentas(String sql) throws SQLException, DniException, CuentaBancariaException, CampoVacioException {
		CuentaDao dao=new CuentaDao(conn);
		
		return dao.getCuentas(sql);
	}
	public boolean agregarCuenta(String numCuenta, String titular, String dni, String saldo) throws CampoVacioException, DniException, CuentaBancariaException, SQLException {
		boolean agregado=false;
		CuentaBancaria cuenta=new CuentaBancaria(numCuenta, titular, dni, saldo);
		CuentaDao dao=new CuentaDao(conn);
		agregado=dao.añadirCuenta(cuenta);
		return agregado;
		
	}
	public boolean eliminarCuenta(String numCuenta) throws SQLException {
		boolean eliminado=false;
		CuentaDao dao=new CuentaDao(conn);
		eliminado=dao.eliminarCuenta(numCuenta);
		return eliminado;
		
	}
	
}
