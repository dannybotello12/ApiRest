package com.ceiba.persistencia;

import java.util.List;

import com.ceiba.entity.TipoVehiculo;

public interface ITipoVehiculo {
	
	    List<TipoVehiculo> getAllTipoVehiculo();
	    TipoVehiculo getTipoVehiculoById(int tipoVehiculoId);
	    void addTipoVehiculo(TipoVehiculo tipoVehiculo);
	    void updateTipoVehiculo(TipoVehiculo tipoVehiculo);
	    void deleteTipoVehiculo(int tipoVehiculoId);

}
