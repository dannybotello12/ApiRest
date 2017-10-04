package com.ceiba.service;

import java.util.List;

import com.ceiba.entity.Registro;

public interface IConsulta {

	 Registro	consultarregistroplaca(String placa);
	 
	 List<Registro> allRegistro();
	  
}
