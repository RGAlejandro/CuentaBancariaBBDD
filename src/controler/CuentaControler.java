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
	
}
