package vista;

import java.time.LocalDate;

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
		String numCuenta="00937684380719959682";
		String titular="Alejandro";
		String dni="26905231-S";
		String saldo="100";
		String fechaOperacion="2023-05-20";
		String bloqueado="true";
		try {
			CuentaBancaria cuenta=new CuentaBancaria(numCuenta, titular, dni, saldo, fechaOperacion, bloqueado);
			System.out.println(cuenta);
		} catch (CuentaBancariaException | CampoVacioException | DniException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
