package com.ceiba.persistencia;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;

public class RegistroDAOTests {

	private RegistroDAO registroDAO;
	@PersistenceContext
	private EntityManager entityManager;// we will be mocking this class
	private EntityTransaction transaction;
	private Query query;

	@Before
	public void setup() {
		entityManager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		query = mock(Query.class);

		registroDAO = new RegistroDAO();
		registroDAO.setentityManager(entityManager);

	}

	@Test
	public void getAllRegistro() throws Throwable {

		List<Registro> registros = new ArrayList<Registro>();
		String hql = "FROM Registro as rg ORDER BY rg.id";
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		registros.add(registro);
		
		when(query.getResultList()).thenReturn(registros);
		when(entityManager.createQuery(hql)).thenReturn(query);
		
		List<Registro> registros2 = registroDAO.getAllRegistro();

		/*
		 * verify(query).getResultList();
		 * verify(entityManager).createQuery(queryString);
		 */

		assertEquals(registros, registros2);

	}

	@Test
	public void getRegistroById() throws Throwable {

		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro(1, "A", date, null, 0, new TipoVehiculo(1, "moto", 20, 500, 500, 6000), false);

		when(entityManager.find(Registro.class, registro.getId())).thenReturn(registro);

		Registro actual = registroDAO.getRegistroById(registro.getId());

		assertEquals(registro, actual);

	}
	
	@Test
	public void getRegistroByPlaca() throws Throwable {
		
		String hql = "FROM Registro as rg WHERE rg.placa = ? and rg.fechaSalida is null";
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro(1, "A", date, null, 0, new TipoVehiculo(1, "moto", 20, 500, 500, 6000), false);

		
		when(query.getSingleResult()).thenReturn(registro);
		when(query.setParameter(1,"A")).thenReturn(query);
		when(entityManager.createQuery(hql)).thenReturn(query);

		Registro actual = registroDAO.getRegistroByPlaca("A");

		assertEquals(registro, actual);
		
		
		
	}
	
	@Test
	public void addRegistro() throws Throwable {
		
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro(1, "A", date, null, 0, new TipoVehiculo(1, "moto", 20, 500, 500, 6000), false);

		
		//Expectations
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(registro);
		
		
		 registroDAO.addRegistro(registro);
		
		
		verify(entityManager).persist(registro);	
		
	}
	
	@Test
	public void deleteRegistro() throws Throwable {
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro(1, "A", date, null, 0, new TipoVehiculo(1, "moto", 20, 500, 500, 6000), false);
	
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).remove(registro);
		when(entityManager.find(Registro.class, registro.getId())).thenReturn(registro);
		registroDAO.deleteRegistro(registro.getId());
		
		verify(entityManager).remove(registro);	
		
	}
	
	@Test
	public void updateRegistro() throws Throwable {
		
		
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Date date2 = df.parse("28/09/2017 17:30:00");
		Registro registro = new Registro(1, "A", date, date2, 1000, new TipoVehiculo(1, "moto", 20, 500, 500, 6000), false);

		
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).flush();
		when(entityManager.find(Registro.class, registro.getId())).thenReturn(registro);
		
		registroDAO.updateRegistro(registro);
		
		verify(entityManager).flush();	
		
	}

	@Test
	public void registroExists() throws Throwable {
		
		
		String hql = "FROM Registro as rg WHERE rg.placa = ? and rg.fechaSalida is null";

		List<Registro> registros = new ArrayList<Registro>();
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		registros.add(registro);
		
		when(query.getResultList()).thenReturn(registros);
		
		when(query.setParameter(1,"A")).thenReturn(query);
		when(entityManager.createQuery(hql)).thenReturn(query);

		Boolean actual = registroDAO.registroExists("A");

		assertTrue(actual);
		
		
	}
	
	
	@Test
	public void registroNoExists() throws Throwable {
		
		
		String hql = "FROM Registro as rg WHERE rg.placa = ? and rg.fechaSalida is null";

		List<Registro> registros = new ArrayList<Registro>();
		
		
		when(query.getResultList()).thenReturn(registros);
		
		when(query.setParameter(1,"A")).thenReturn(query);
		when(entityManager.createQuery(hql)).thenReturn(query);

		Boolean actual = registroDAO.registroExists("A");

		assertFalse(actual);
		
		
	}
	
	
	@Test
	public void getRegistroBytipoVehiculoActivo() throws Throwable {
		
		
		String hql = "select rg FROM Registro rg join rg.tipoVehiculo tv WHERE tv.id = ? and rg.fechaSalida is null";
		List<Registro> registros = new ArrayList<Registro>();
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = df.parse("28/09/2017 15:30:00");
		Registro registro = new Registro();
		registro.setPlaca("A");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
		registros.add(registro);
		
		when(query.getResultList()).thenReturn(registros);
		when(query.setParameter(1,1)).thenReturn(query);
		when(entityManager.createQuery(hql)).thenReturn(query);

		int actual = registroDAO.getRegistroBytipoVehiculoActivo(1);

		assertEquals(1, actual);
		
	}

}
