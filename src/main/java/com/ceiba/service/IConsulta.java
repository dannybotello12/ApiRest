package com.ceiba.service;

import java.util.List;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;

public interface IConsulta {

	 Registro	consultarregistroplaca(String placa);
	 
	 List<Registro> allRegistro();
	 List<TipoVehiculo> allVehiculos();
	  
}
