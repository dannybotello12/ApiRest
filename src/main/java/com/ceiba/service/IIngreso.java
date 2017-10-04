package com.ceiba.service;

import java.util.Date;

import com.ceiba.entity.Registro;

public interface IIngreso {
	
public int diaSemana(Date fecha);
	
	public String letraPlaca(String placa);
	
	public String diasrestriccionplaca(String placa );
	
	public boolean accesovalido( Registro registro);
	
	public boolean espaciodisponible(int tipoVehiculo);
	
	public String registroEntrada(Registro registro);  


}
