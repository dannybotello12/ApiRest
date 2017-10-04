package com.ceiba.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.entity.Registro;
import com.ceiba.entity.Restriccion;
import com.ceiba.entity.TipoVehiculo;
import com.ceiba.persistencia.RegistroDAO;
import com.ceiba.persistencia.RestriccionDAO;
import com.ceiba.persistencia.TipoVehiculoDAO;

public class IngresoTests {

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
	public void diaSemana() throws Throwable {

		DateFormat df = new SimpleDateFormat("dd MM yyyy");

		Date date = df.parse("28 09 2017");

		assertEquals(5, ingreso.diaSemana(date));
	}

	@Test
	public void letraplaca() {
		Ingreso ingreso = new Ingreso();
		String placa = "XYZ125";

		assertEquals("X", ingreso.letraPlaca(placa));
	}

	@Test
	public void diasdireccionplacacondias() {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("1,2,3");
		restriccion.setLetraplaca("A");
		// MOCK ALERT: return mocked result set on find
		when(restriccionDAO.getRestriccionByletra("A")).thenReturn(restriccion);
		// call the main method you want to test
		String letras = ingreso.diasrestriccionplaca("A");
		assertEquals("1,2,3", letras);

	}

	@Test
	public void diasdireccionplacavacio() {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("");
		restriccion.setLetraplaca("A");
		// MOCK ALERT: return mocked result set on find
		when(restriccionDAO.getRestriccionByletra("A")).thenReturn(restriccion);
		// call the main method you want to test
		ingreso.diasrestriccionplaca("A");
		assertEquals("", "");

	}

	@Test
	public void accesovalido() throws Throwable {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("");
		restriccion.setLetraplaca("A");

		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");

		Date date = df.parse("28/09/2017 15:30:00");

		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);

		// MOCK ALERT: return mocked result set on find
		when(restriccionDAO.getRestriccionByletra("A")).thenReturn(restriccion);
		Boolean acceso = ingreso.accesovalido(registro);

		assertTrue(acceso);

	}

	@Test
	public void accesonovalido() throws Throwable {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("1,2,3,4,5,6,7");
		restriccion.setLetraplaca("A");
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);

		// MOCK ALERT: return mocked result set on find
		when(restriccionDAO.getRestriccionByletra("A")).thenReturn(restriccion);
		Boolean acceso = ingreso.accesovalido(registro);

		assertFalse(acceso);

	}

	@Test
	public void espaciodisponible() throws ParseException {

		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		when(registroDAO.getRegistroBytipoVehiculoActivo(1)).thenReturn(15);

		boolean espacio = ingreso.espaciodisponible(1);

		assertTrue(espacio);

	}

	@Test
	public void registroEntrada() throws Throwable {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("1,2");
		restriccion.setLetraplaca("a");

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date date = df.parse("28/09/2017 15:30:00");

		Registro registro = new Registro();
		registro.setPlaca("ajye56b");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);

		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(registro.getTipoVehiculo());
		when(registroDAO.getRegistroBytipoVehiculoActivo(1)).thenReturn(15);
		when(registroDAO.registroExists(registro.getPlaca())).thenReturn(false);

		when(restriccionDAO.getRestriccionByletra("ajye56b")).thenReturn(restriccion);

		String espacio = ingreso.registroEntrada(registro);

		assertEquals("Vehiculo ingresado correctamente", espacio);

	}

	@Test
	public void registroEntradayaexiste() throws Throwable {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("1,2");
		restriccion.setLetraplaca("a");

		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");

		Date date = df.parse("28/09/2017 15:30:00");

		Registro registro = new Registro();
		registro.setPlaca("ajye56b");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);

		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(registro.getTipoVehiculo());
		when(registroDAO.getRegistroBytipoVehiculoActivo(1)).thenReturn(15);
		when(registroDAO.registroExists(registro.getPlaca())).thenReturn(true);

		when(restriccionDAO.getRestriccionByletra("ajye56b")).thenReturn(restriccion);

		String espacio = ingreso.registroEntrada(registro);

		assertEquals("Vehiculo ya ingreso", espacio);

	}

	@Test
	public void registroEntradasinespacio() throws Throwable {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("1,2");
		restriccion.setLetraplaca("a");

		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");

		Date date = df.parse("28/09/2017 15:30:00");

		Registro registro = new Registro();
		registro.setPlaca("ajye56b");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);

		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(registro.getTipoVehiculo());
		when(registroDAO.getRegistroBytipoVehiculoActivo(1)).thenReturn(60);
		when(registroDAO.registroExists(registro.getPlaca())).thenReturn(false);

		when(restriccionDAO.getRestriccionByletra("ajye56b")).thenReturn(restriccion);

		String espacio = ingreso.registroEntrada(registro);

		assertEquals("Sin espacio para el vehiculo", espacio);

	}

	@Test
	public void registroEntradarestriccionDia() throws Throwable {

		Restriccion restriccion = new Restriccion();
		restriccion.setDias("1,2,3,4,5,6,7");
		restriccion.setLetraplaca("A");

		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");

		Date date = df.parse("28/09/2017 15:30:00");

		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);

		when(tipoVehiculoDAO.getTipoVehiculoById(1)).thenReturn(registro.getTipoVehiculo());
		when(registroDAO.getRegistroBytipoVehiculoActivo(1)).thenReturn(0);
		when(registroDAO.registroExists(registro.getPlaca())).thenReturn(false);
		when(restriccionDAO.getRestriccionByletra("A")).thenReturn(restriccion);
		String espacio = ingreso.registroEntrada(registro);

		assertEquals("Vehiculo con restricion de dia", espacio);

	}

}
