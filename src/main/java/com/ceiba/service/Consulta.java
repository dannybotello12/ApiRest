package com.ceiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;
import com.ceiba.persistencia.IRegistro;
import com.ceiba.persistencia.ITipoVehiculo;
import com.ceiba.persistencia.RegistroDAO;
import com.ceiba.persistencia.TipoVehiculoDAO;


@Service
public class Consulta implements IConsulta {
     
	
	private IRegistro registroDAO;
	 @Autowired public void setRegistroDAO(RegistroDAO registroDAO) {
	 this.registroDAO = registroDAO;
	 }
	
	 private ITipoVehiculo tipovehiculoDAO;
	 @Autowired public void setTipoVehiculoDAO(TipoVehiculoDAO tipoVehiculoDAO) {
	 this.tipovehiculoDAO = tipoVehiculoDAO;
	 }

	
	@Override
	public Registro consultarregistroplaca(String placa) {
		return  registroDAO.getRegistroByPlaca(placa);
		
	}

	@Override
	public List<Registro> allRegistro() {
		return  registroDAO.getAllRegistro();
	}



	@Override
	public List<TipoVehiculo> allVehiculos() {
	
		return tipovehiculoDAO.getAllTipoVehiculo();
	}
	

}

