package modelo;

import java.time.LocalDate;

import excepciones.CampoVacioException;
import excepciones.CuentaBancariaException;
import excepciones.DniException;

public class CuentaBancaria {
	private String numCuenta;
	private String titular;
	private String dni;
	private double saldo;
	private LocalDate fechaOperacion;
	private boolean bloqueado;
	

	public CuentaBancaria() {
		// TODO Auto-generated constructor stub
	}
	

	public CuentaBancaria(String numCuenta, String titular, String dni, String saldo, String fechaOperacion,
			String bloqueado) throws CuentaBancariaException, CampoVacioException, DniException {
		this.setNumCuenta(numCuenta);
		this.setTitular(titular);
		this.setDni(dni);
		this.setSaldo(saldo);
		this.setFechaOperacion(fechaOperacion);
		this.setBloqueado(bloqueado);
	}


	public String getNumCuenta() {
		return numCuenta;
	}


	public void setNumCuenta(String numCuenta) throws CuentaBancariaException {
		if(compruebaCuentaBancaria(numCuenta)) {
			this.numCuenta = numCuenta;
		}
		else {
			throw new CuentaBancariaException();
		}
	}


	private boolean compruebaCuentaBancaria(String numCuenta) {
		// TODO Auto-generated method stub
		boolean esValido=true;
		numCuenta=numCuenta.replace("-", "");
		if(numCuenta.length()!=20) {
			return false;
		}
		double compruebaNums=0;
		try {
			compruebaNums=Double.parseDouble(numCuenta);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		String entidadSucursal="00"+numCuenta.substring(0, 8);
		String digControlPuestoIzq=numCuenta.substring(8, 9);
		String digControlPuestoDer=numCuenta.substring(9, 10);
		String cuentaBancaria=numCuenta.substring(10);
		
		String digControlCalcIzq=calculaDigito(entidadSucursal);
		String digControlCalcDer=calculaDigito(cuentaBancaria);
		
		if(!digControlCalcIzq.equals(digControlPuestoIzq)||!digControlCalcDer.equals(digControlPuestoDer)) {
			return false;
		}
		
		return esValido;
	}


	private String calculaDigito(String parteCalcular) {
		// TODO Auto-generated method stub
		String digControl="";
		char [] calcular=parteCalcular.toCharArray();
		int [] multiplicador= {1,2,4,8,5,10,9,7,3,6};
		int acumulador=0;
		int suma=0;
		for(int x=0;x<calcular.length;x++) {
			acumulador=Integer.parseInt(Character.toString(calcular[x]));
			suma+=acumulador*multiplicador[x];
		}
		int dig=11-(suma%11);
		if(dig==10) {
			dig=1;
		}
		if(dig==11) {
			dig=0;
		}
		digControl=String.valueOf(dig);
		return digControl;
	}


	public String getTitular() {
		return titular;
	}


	public void setTitular(String titular) throws CampoVacioException {
		if(titular.isEmpty()) {
			throw new CampoVacioException();
		}
		else {
			this.titular = titular;
		}
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) throws DniException {
		if(compruebaDni(dni)) {
			this.dni = dni;
		}
		else {
			throw new DniException();
		}
	}


	private boolean compruebaDni(String dni) {
		// TODO Auto-generated method stub
		boolean esValido=true;
		dni=dni.replace("-", "");
		if(dni.length()!=9) {
			return false;
		}
		int numsDni=0;
		try {
			numsDni=Integer.parseInt(dni.substring(0, 8));
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		String letraPuesta=dni.substring(8);
		String [] letras= {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		int resto=numsDni%23;
		String letraCalc=letras[resto];
		if(!letraPuesta.equals(letraCalc)) {
			return false;
		}
		
		return esValido;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(String saldo) throws CampoVacioException {
		if(saldo.isEmpty()) {
			throw new CampoVacioException();
		}
		else {
			this.saldo = Double.parseDouble(saldo);
		}
	}


	public LocalDate getFechaOperacion() {
		return fechaOperacion;
	}


	public void setFechaOperacion(String fechaOperacion) throws CampoVacioException {
		if(fechaOperacion.isEmpty()) {
			throw new CampoVacioException();
		}
		else {
			this.fechaOperacion = LocalDate.parse(fechaOperacion);
		}
	}


	public boolean isBloqueado() {
		return bloqueado;
	}


	public void setBloqueado(String bloqueado) throws CampoVacioException {
		if(bloqueado.isEmpty()) {
			throw new CampoVacioException();
		}
		else {
			this.bloqueado = Boolean.parseBoolean(bloqueado);
		}
	}


	@Override
	public String toString() {
		return numCuenta + "," + titular + "," + dni + "," + saldo
				+ "," + fechaOperacion + "," + bloqueado;
	}
	
}
