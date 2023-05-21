package vista;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import controler.CuentaControler;
import dao.DbConnection;
import excepciones.CampoVacioException;
import excepciones.CuentaBancariaException;
import excepciones.DniException;
import modelo.CuentaBancaria;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		String numCuenta="00937684380719959682";
		String titular="Alejandro";
		String dni="26905231-S";
		String saldo="100";
		String fechaOperacion="2023-05-20";
		String bloqueada="true";
		try {
			CuentaBancaria cuenta=new CuentaBancaria(numCuenta, titular, dni, saldo, fechaOperacion, bloqueado);
			System.out.println(cuenta);
		} catch (CuentaBancariaException | CampoVacioException | DniException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		*/
		Scanner leer=new Scanner(System.in);
		boolean sigue=true;
		String opcion="";
		do {
			System.out.println("1.-CONSULTA A TODA LA BBDD");
			System.out.println("2.-AÑADIR A LA BBDD");
			System.out.println("3.-ELIMINAR A LA BBDD");
			System.out.println("4.-FILTRADO A LA BBDD");
			System.out.println("5.-BLOQUEAR CUENTA EN LA BBDD");
			System.out.println("6.-APAGAR");
			opcion=leer.next();
			switch(opcion) {
			case "1":
				Connection conn;
				DbConnection dbc=new DbConnection();
				conn=dbc.getConnection();
				CuentaControler controler=new CuentaControler(conn);
				String sql="select * from cuentas";
				try {
					List<CuentaBancaria> cuentas=controler.getCuentas(sql);
					mostrar(cuentas);
				} catch (SQLException | DniException | CuentaBancariaException | CampoVacioException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} 
				
			break;
			
			case "2":
				
			break;
			
			case "3":
				
			break;
			
			case "4":
				
			break;
			
			case "5":
				
			break;
			
			case "6":
				
			break;
			
			default:
				System.out.println("Elige un numero del 1 al 6");
			break;	
			}
		}while(sigue);
	}

	private static void mostrar(List<CuentaBancaria> cuentas) {
		// TODO Auto-generated method stub
		for (CuentaBancaria cb : cuentas) {
			System.out.println(cb);
		}
		
	}

}
