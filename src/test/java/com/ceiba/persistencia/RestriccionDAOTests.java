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

public class RestriccionDAOTests {
	
	
	private RestriccionDAO restriccionDAO;
	@PersistenceContext
	private EntityManager entityManager;// we will be mocking this class
	private EntityTransaction transaction;
	private Query query;

	@Before
	public void setup() {
		entityManager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		query = mock(Query.class);

		restriccionDAO = new RestriccionDAO();
		restriccionDAO.setentityManager(entityManager);

	}
	

	
   
	@Test
	public void getRestriccionById() {
		
		
		Restriccion restriccion = new Restriccion(1,"A","1,2,3");

		when(entityManager.find(Restriccion.class, restriccion.getId())).thenReturn(restriccion);

		Restriccion actual = restriccionDAO.getRestriccionById(1);

		assertEquals(restriccion, actual);
		
	}
	@Test
	public void addRestriccion() {
		
		Restriccion restriccion = new Restriccion(1,"A","1,2,3");
		
		//Expectations
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(restriccion);
		
		restriccionDAO.addRestriccion(restriccion);
		verify(entityManager).persist(restriccion);	
	}
	@Test
	public void updateRestriccion() {
	
		Restriccion restriccion = new Restriccion(1,"A","1,2,3");
		
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).flush();
		when(entityManager.find(Restriccion.class, restriccion.getId())).thenReturn(restriccion);
		
		restriccionDAO.updateRestriccion(restriccion);
		
		verify(entityManager).flush();	
		
		
	}
	@Test
	public void deleteRestriccion() {
		Restriccion restriccion = new Restriccion(1,"A","1,2,3");
		
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).remove(restriccion);
		when(entityManager.find(Restriccion.class, restriccion.getId())).thenReturn(restriccion);
		restriccionDAO.deleteRestriccion(restriccion.getId());
		
		verify(entityManager).remove(restriccion);	
	}
	@Test
	public void getAllRestriccion() {
		
		List<Restriccion> restricciones = new ArrayList<Restriccion>();
		String hql = "FROM Restriccion as rt ORDER BY rt.id";
		Restriccion restriccion = new Restriccion(1,"A","1,2,3");
		restricciones.add(restriccion);
		
		when(query.getResultList()).thenReturn(restricciones);
		when(entityManager.createQuery(hql)).thenReturn(query);
		
		List<Restriccion> actual = restriccionDAO.getAllRestriccion();

	
		assertEquals(actual, restricciones);
		
	}
	@Test
	public void getRestriccionByletra() {
	
		
    String hql = "FROM Restriccion as rt WHERE rt.letraplaca = ? ";
    List<Restriccion> restricciones = new ArrayList<Restriccion>();
	Restriccion restriccion = new Restriccion(1,"A","1,2,3");
	restricciones.add(restriccion);
	
	when(query.getResultList()).thenReturn(restricciones);
	when(entityManager.createQuery(hql)).thenReturn(query);	
	when(query.setParameter(1,"A")).thenReturn(query);
	
	Restriccion  actual = restriccionDAO.getRestriccionByletra("A");
	
	assertEquals(actual, restriccion);
		
	}
	
	@Test
	public void getSinRestriccionByletra() {
	
		
    String hql = "FROM Restriccion as rt WHERE rt.letraplaca = ? ";
    List<Restriccion> restricciones = new ArrayList<Restriccion>();
	Restriccion restriccion = null;
	restricciones.add(restriccion);
	
	when(query.getResultList()).thenReturn(restricciones);
	when(entityManager.createQuery(hql)).thenReturn(query);	
	when(query.setParameter(1,"A")).thenReturn(query);
	
	Restriccion  actual = restriccionDAO.getRestriccionByletra("A");
	

	assertEquals(actual, restriccion);
	
	   
		
	}
}
