package com.ceiba.persistencia;

import java.util.List;

import com.ceiba.entity.Restriccion;

public interface IRestriccion {
	
	  List<Restriccion> getAllRestriccion();
	    Restriccion getRestriccionById(int restriccionId);
	    Restriccion getRestriccionByletra(String letra);
	    void addRestriccion(Restriccion restriccion);
	    void updateRestriccion(Restriccion restriccion);
	    void deleteRestriccion(int restriccionId);

}
