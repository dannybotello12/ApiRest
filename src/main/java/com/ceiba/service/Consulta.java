package com.ceiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.entity.Registro;
import com.ceiba.persistencia.IRegistro;
import com.ceiba.persistencia.RegistroDAO;


@Service
public class Consulta implements IConsulta {
     
	
	private IRegistro registroDAO;
	 @Autowired public void setRegistroDAO(RegistroDAO registroDAO) {
	 this.registroDAO = registroDAO;
	 }
	

	
	@Override
	public Registro consultarregistroplaca(String placa) {
		return  registroDAO.getRegistroByPlaca(placa);
		
	}

	@Override
	public List<Registro> allRegistro() {
		return  registroDAO.getAllRegistro();
	}
	

}

