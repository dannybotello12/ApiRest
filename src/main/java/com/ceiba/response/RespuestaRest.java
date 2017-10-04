package com.ceiba.response;



public class RespuestaRest {
	
	
	private int numero;
	private String mensaje;
	
	
	
	

	
	
	
	public RespuestaRest() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RespuestaRest(int numero, String mensaje) {
		super();
		this.numero = numero;
		this.mensaje = mensaje;
	
	}



	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	

}
