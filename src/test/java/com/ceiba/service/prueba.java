package com.ceiba.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.entity.Restriccion;
import com.ceiba.persistencia.RegistroDAO;
import com.ceiba.persistencia.RestriccionDAO;
import com.ceiba.persistencia.TipoVehiculoDAO;

public class prueba {
	
	
	private Ingreso ingreso;
	private RegistroDAO registroDAO; // we will be mocking this class
	private RestriccionDAO restriccionDAO; // we will be mocking this class
	private TipoVehiculoDAO tipoVehiculoDAO; // we will be mocking this class

	@Before
	public void setup() {
		registroDAO = mock(RegistroDAO.class); // here is the actual mocking
												// call
		restriccionDAO = mock(RestriccionDAO.class); // here is the actual
														// mocking call
		tipoVehiculoDAO = mock(TipoVehiculoDAO.class); // here is the actual
														// mocking call
		ingreso = new Ingreso();
		ingreso.setRegistroDAO(registroDAO);
		ingreso.setrestriccionDAO(restriccionDAO);
		ingreso.setTipoVehiculoDAO(tipoVehiculoDAO);

	}

	

	@Test
	public void diasdireccionplacaError() {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("1,2,3");
		restriccion.setLetraplaca("A");
		// MOCK ALERT: return mocked result set on find
		when(restriccionDAO.getRestriccionByletra("A")).thenReturn(restriccion);
		// call the main method you want to test
		String dias=ingreso.diasrestriccionplaca("A");
		assertEquals("1,2,3", dias);
		
	}

}
