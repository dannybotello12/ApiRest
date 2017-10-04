package com.ceiba.persistencia;

import static org.junit.Assert.*;
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
import com.ceiba.entity.Restriccion;
import com.ceiba.entity.TipoVehiculo;

public class TipoVehiculoDAOTests {

	private TipoVehiculoDAO tipoVehiculoDAO;
	@PersistenceContext
	private EntityManager entityManager;// we will be mocking this class
	private EntityTransaction transaction;
	private Query query;

	@Before
	public void setup() {
		entityManager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		query = mock(Query.class);

		tipoVehiculoDAO = new TipoVehiculoDAO();
		tipoVehiculoDAO.setentityManager(entityManager);

	}

	@Test
	public void getAllTipoVehiculo() {

		List<TipoVehiculo> Listatipovehiculo = new ArrayList<TipoVehiculo>();
		String hql = "FROM TipoVehiculo as tv ORDER BY tv.id";

		TipoVehiculo tipoVehiculo = new TipoVehiculo(1, "", 20, 0, 500, 8000);

		Listatipovehiculo.add(tipoVehiculo);

		when(query.getResultList()).thenReturn(Listatipovehiculo);
		when(entityManager.createQuery(hql)).thenReturn(query);

		List<TipoVehiculo> actual = tipoVehiculoDAO.getAllTipoVehiculo();
		assertEquals(Listatipovehiculo, actual);

	}

	@Test
	public void getTipoVehiculoById() {
		
		TipoVehiculo tipoVehiculo = new TipoVehiculo(1, "", 20, 0, 500, 8000);

		when(entityManager.find(TipoVehiculo.class, tipoVehiculo.getId())).thenReturn(tipoVehiculo);

		TipoVehiculo actual = tipoVehiculoDAO.getTipoVehiculoById(tipoVehiculo.getId());

		assertEquals(tipoVehiculo, actual);

	}

	@Test
	public void addTipoVehiculo() {
		
		TipoVehiculo tipoVehiculo = new TipoVehiculo(1, "", 20, 0, 500, 8000);
		
		//Expectations
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(tipoVehiculo);
		
		
		tipoVehiculoDAO.addTipoVehiculo(tipoVehiculo);
		
		
		verify(entityManager).persist(tipoVehiculo);	

	}

	@Test
	public void updateTipoVehiculo() {
		TipoVehiculo tipoVehiculo = new TipoVehiculo(1, "", 20, 0, 500, 8000);
		
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).flush();
		when(entityManager.find(TipoVehiculo.class, tipoVehiculo.getId())).thenReturn(tipoVehiculo);
		
		tipoVehiculoDAO.updateTipoVehiculo(tipoVehiculo);
		
		verify(entityManager).flush();	

	}

	@Test
	public void deleteTipoVehiculo() {
		TipoVehiculo tipoVehiculo = new TipoVehiculo(1, "", 20, 0, 500, 8000);
		
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).remove(tipoVehiculo);
		when(entityManager.find(TipoVehiculo.class, tipoVehiculo.getId())).thenReturn(tipoVehiculo);
		tipoVehiculoDAO.deleteTipoVehiculo(tipoVehiculo.getId());
		
		verify(entityManager).remove(tipoVehiculo);	

	}
}
