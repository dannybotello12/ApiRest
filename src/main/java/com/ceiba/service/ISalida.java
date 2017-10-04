package com.ceiba.service;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;

public interface ISalida {
	
    public TipoVehiculo valores(int tipovehiculo);
	

	
	public int valortotal(Registro registro);
	
	public String registrarsalida(Registro registro);

}
