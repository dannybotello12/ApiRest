package com.ceiba.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;
import com.ceiba.persistencia.RegistroDAO;
import com.ceiba.persistencia.TipoVehiculoDAO;

public class SalidaTests {
	
	
	private Salida salida;
	private RegistroDAO registroDAO; // we will be mocking this class
	private TipoVehiculoDAO tipoVehiculoDAO; // we will be mocking this class

	@Before
	public void setup() {
		registroDAO = mock(RegistroDAO.class); // here is the actual mocking
												// call
		tipoVehiculoDAO = mock(TipoVehiculoDAO.class); // here is the actual													// mocking call
		salida = new Salida();
		salida.setRegistroDAO(registroDAO);
		salida.setTipoVehiculoDAO(tipoVehiculoDAO);

	}
	

	@Test
	public void valorestest() {
		

		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1);
		tipoVehiculo.setDescripcion("Moto");
		tipoVehiculo.setRecargo(2000);
		tipoVehiculo.setStock(20);
		tipoVehiculo.setValordia(6000);
		tipoVehiculo.setValorhora(500);
		// MOCK ALERT: return mocked result set on find
		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(tipoVehiculo);
		// call the main method you want to test
		TipoVehiculo tipoVehiculo2=salida.valores(1);
		// MOCK ALERT: verify the method was called
		assertEquals(tipoVehiculo, tipoVehiculo2);
		
	}
	
	@Test
	public void valortotaltest() throws Throwable {
	   
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1);
		tipoVehiculo.setDescripcion("Moto");
		tipoVehiculo.setRecargo(2000);
		tipoVehiculo.setStock(20);
		tipoVehiculo.setValordia(6000);
		tipoVehiculo.setValorhora(500);
		// MOCK ALERT: return mocked result set on find
		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(tipoVehiculo);
	
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date	date = df.parse("28/09/2017 15:30:00");
				Date	date2 = df.parse("30/09/2017 15:30:00");

			Registro registro = new Registro();
			registro.setPlaca("A");
			registro.setRecargo(false);
			registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
			registro.setValor(0);
			registro.setFechaIngreso(date);
			registro.setFechaSalida(date2);
		
	    int valor=salida.valortotal(registro);
	    
	    assertEquals(valor, 12000);
			
	}
	
	
	@Test
	public void valortotalunahoratest() throws Throwable {
	   
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1);
		tipoVehiculo.setDescripcion("Moto");
		tipoVehiculo.setRecargo(2000);
		tipoVehiculo.setStock(20);
		tipoVehiculo.setValordia(6000);
		tipoVehiculo.setValorhora(500);
		// MOCK ALERT: return mocked result set on find
		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(tipoVehiculo);
	
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date	date = df.parse("28/09/2017 15:30:00");
				Date	date2 = df.parse("28/09/2017 15:31:00");

			Registro registro = new Registro();
			registro.setPlaca("A");
			registro.setRecargo(false);
			registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
			registro.setValor(0);
			registro.setFechaIngreso(date);
			registro.setFechaSalida(date2);
		
	    int valor=salida.valortotal(registro);
	    
	    assertEquals(valor, 500);
			
	}
	
	@Test
	public void valortotalrecargotest() throws Throwable {
	   
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1);
		tipoVehiculo.setDescripcion("Moto");
		tipoVehiculo.setRecargo(2000);
		tipoVehiculo.setStock(20);
		tipoVehiculo.setValordia(6000);
		tipoVehiculo.setValorhora(500);
		// MOCK ALERT: return mocked result set on find
		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(tipoVehiculo);
	
		  
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
				Date	date = df.parse("28/09/2017 15:30:00");
				Date	date2 = df.parse("30/09/2017 15:30:00");
		

			Registro registro = new Registro();
			registro.setPlaca("A");
			registro.setRecargo(true);
			registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
			registro.setValor(0);
			registro.setFechaIngreso(date);
			registro.setFechaSalida(date2);
		
	    int valor=salida.valortotal(registro);
	    
	    assertEquals(valor, 14000);
			
	}	
	
	@Test
	public void valortotalhorastest() throws Throwable {
	   
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1);
		tipoVehiculo.setDescripcion("Moto");
		tipoVehiculo.setRecargo(2000);
		tipoVehiculo.setStock(20);
		tipoVehiculo.setValordia(6000);
		tipoVehiculo.setValorhora(500);
		// MOCK ALERT: return mocked result set on find
		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(tipoVehiculo);
	
		  
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
				Date	date = df.parse("28/09/2017 15:30:00");
				Date	date2 = df.parse("28/09/2017 16:30:00");
		

			Registro registro = new Registro();
			registro.setPlaca("A");
			registro.setRecargo(false);
			registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
			registro.setValor(0);
			registro.setFechaIngreso(date);
			registro.setFechaSalida(date2);
		
	    int valor=salida.valortotal(registro);
	    
	    assertEquals(500,valor);
			
	}	
	
	@Test
	public void valortotalfechastruncadas() throws Throwable {
	   
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1);
		tipoVehiculo.setDescripcion("Moto");
		tipoVehiculo.setRecargo(2000);
		tipoVehiculo.setStock(20);
		tipoVehiculo.setValordia(6000);
		tipoVehiculo.setValorhora(500);
		// MOCK ALERT: return mocked result set on find
		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(tipoVehiculo);
	
		  
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
				Date	date = df.parse("28/09/2017 15:30:00");
				Date	date2 = df.parse("28/09/2017 14:30:00");
		

			Registro registro = new Registro();
			registro.setPlaca("A");
			registro.setRecargo(false);
			registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
			registro.setValor(0);
			registro.setFechaIngreso(date);
			registro.setFechaSalida(date2);
		
	    int valor=salida.valortotal(registro);
	    
	    assertEquals(-3,valor);
			
	}	
	
	@Test
	public void registrartest() throws Throwable {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
			Date	date = df.parse("28/09/2017 15:30:00");
			Date	date2 = df.parse("30/09/2017 16:30:00");
		

		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(true);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 6000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		registro.setFechaSalida(date2);
		registro.setId(5);
		
		
		when(registroDAO.registroExists("A")).thenReturn(true);
		when( tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(new TipoVehiculo(1, "", 20, 0, 500, 6000));
		when(registroDAO.updateRegistro(registro)).thenReturn("");
		
		String respuesta= salida.registrarsalida(registro);
		
	     assertEquals("Salida registrada correctamente 12500", respuesta);
		
	}
	
	
	@Test
	public void registrarNoencontradotest() throws Throwable {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
			Date	date = df.parse("28/09/2017 15:30:00");
			Date	date2 = df.parse("30/09/2017 16:30:00");
		

		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(true);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 6000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		registro.setFechaSalida(date2);
		
		
		
		when(registroDAO.registroExists("A")).thenReturn(false);
		when( tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(new TipoVehiculo(1, "", 20, 0, 500, 6000));
		when(registroDAO.updateRegistro(registro)).thenReturn("");
		
		String respuesta= salida.registrarsalida(registro);
		
	     assertEquals("Vehiculo No encontrado", respuesta);
		
	}

}
