package controler;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import modelo.CuentaBancaria;

public class CuentaControler {
	private List<CuentaBancaria> cuentas=new ArrayList<CuentaBancaria>();
	private Connection conn;
	public CuentaControler(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
	}
	public List<CuentaBancaria> getCuentas(String sql) {
		CuentaDao dao=new CuentaDao(conn);
		
		return dao.getCuentas(sql);
	}
	
}
