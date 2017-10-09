package com.ceiba.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;
import com.ceiba.response.RespuestaRest;
import com.ceiba.service.Consulta;
import com.ceiba.service.Ingreso;
import com.ceiba.service.Salida;

public class ParkinControllerTests {
	
	
	private ParkingController parkingController;
	private Ingreso ingreso; // we will be mocking this class
	private Salida salida; // we will be mocking this class
	private Consulta consulta; // we will be mocking this class
	

	@Before
	public void setup() {
		ingreso = mock(Ingreso.class);
		salida = mock(Salida.class); 
		consulta = mock(Consulta.class); 
		parkingController = new ParkingController();
		parkingController.setingreso(ingreso);
		parkingController.setsalida(salida);
		parkingController.setconsulta(consulta);

	}

	

	@Test
	public void RegistroByPlaca() throws Throwable {
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");	
	    Date	date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
				
		
		 when(consulta.consultarregistroplaca("A")).thenReturn(registro);
		 
		 
		 ResponseEntity<Registro> respuesta= parkingController.getRegistroByPlaca("A");
		 
		 assertEquals( new ResponseEntity<>(registro, HttpStatus.OK), respuesta);
		
		
	}
	
	@Test
	public void getAllregistros() throws Throwable {
		
		List<Registro> list = new ArrayList<Registro>();
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");	
	    Date	date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		list.add(registro);		
		
		 when(consulta.allRegistro()).thenReturn(list);
		 
		 
		 ResponseEntity<List<Registro>> respuesta= parkingController.getAllregistros();
		 
		 assertEquals( new ResponseEntity<>(list, HttpStatus.OK), respuesta);
		
		
	}

	
	@Test
	public void addRegistroCorrecto() throws Throwable {
		
		List<Registro> list = new ArrayList<Registro>();
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");	
	    Date	date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		list.add(registro);	
	
		
	    RespuestaRest respuestarest=new RespuestaRest();
	    respuestarest.setNumero(0);
	    respuestarest.setMensaje("Vehiculo ingresado correctamente");
		
		 when(ingreso.registroEntrada(registro)).thenReturn("Vehiculo ingresado correctamente");
		 
		
		 ResponseEntity<RespuestaRest> respuesta= parkingController.addRegistro(registro);
		 
		 assertEquals( new ResponseEntity<>(respuestarest,HttpStatus.CREATED).getStatusCodeValue(), respuesta.getStatusCodeValue());
		
		
	}
	
	
	@Test
	public void addRegistroIncorrecto() throws Throwable {
		
		List<Registro> list = new ArrayList<Registro>();
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");	
	    Date	date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		list.add(registro);	
	
		
	    RespuestaRest respuestarest=new RespuestaRest();
	    respuestarest.setNumero(1);
	    respuestarest.setMensaje("Mal");
		
		 when(ingreso.registroEntrada(registro)).thenReturn("Mal");
		 
		
		 ResponseEntity<RespuestaRest> respuesta= parkingController.addRegistro(registro);
		 
		 assertEquals( new ResponseEntity<>(respuestarest,HttpStatus.CONFLICT).getStatusCodeValue(), respuesta.getStatusCodeValue());
		
		
	}
	
	@Test
	public void updateregistroCorrecto() throws Throwable {
		
		List<Registro> list = new ArrayList<Registro>();
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");	
	    Date	date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		list.add(registro);	
		
		
	    RespuestaRest respuestarest=new RespuestaRest();
	    respuestarest.setNumero(0);
	    respuestarest.setMensaje("Salida registrada correctamente");
		
		 when(salida.registrarsalida(registro)).thenReturn("Salida registrada correctamente");
		 
		
		 ResponseEntity<RespuestaRest> respuesta= parkingController.updateregistro(registro);
		 
		 
		 assertEquals( new ResponseEntity<>(respuestarest,HttpStatus.OK).getStatusCodeValue(), respuesta.getStatusCodeValue());
			
		
	}
	
	
	@Test
	public void updateregistroIncorrecto() throws Throwable {
		
		List<Registro> list = new ArrayList<Registro>();
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");	
	    Date	date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		list.add(registro);	
	
		
	    RespuestaRest respuestarest=new RespuestaRest();
	    respuestarest.setNumero(1);
	    respuestarest.setMensaje("Mal");
		
		 when(salida.registrarsalida(registro)).thenReturn("Vehiculo No encontrado");
		 
		
		 ResponseEntity<RespuestaRest> respuesta= parkingController.updateregistro(registro);
		 
		 assertEquals( new ResponseEntity<>(respuestarest,HttpStatus.CONFLICT).getStatusCodeValue(), respuesta.getStatusCodeValue());
			
		
	}
	
	@Test
	public void getAlltipoVehiculos() throws Throwable {
		
		List<TipoVehiculo> list = new ArrayList<TipoVehiculo>();
		
		
		TipoVehiculo vehiculo= new TipoVehiculo(1, "moto", 20, 0, 500, 8000);
		
		list.add(vehiculo);		
		
		 when(consulta.allVehiculos()).thenReturn(list);
		 
		 
		 ResponseEntity<List<TipoVehiculo>> respuesta= parkingController.getAlltipoVehiculos();
		 
		 assertEquals( new ResponseEntity<>(list, HttpStatus.OK), respuesta);
		
		
	}


}
