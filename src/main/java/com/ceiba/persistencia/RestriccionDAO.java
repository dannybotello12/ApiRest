package com.ceiba.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;


import com.ceiba.entity.Restriccion;

@Transactional
@Repository
public class RestriccionDAO implements IRestriccion {
	
	private EntityManager entityManager;
	@PersistenceContext 
	public void setentityManager(EntityManager entityManager) {
		 this.entityManager = entityManager;
		 }


	

	@Override
	public Restriccion getRestriccionById(int restriccionId) {
		return entityManager.find(Restriccion.class, restriccionId);
	}

	@Override
	public void addRestriccion(Restriccion restriccion) {
		entityManager.persist(restriccion);
		
	}

	@Override
	public void updateRestriccion(Restriccion restriccion) {
		Restriccion rt = getRestriccionById(restriccion.getId());
		rt.setDias(restriccion.getDias());
		rt.setLetraplaca(restriccion.getLetraplaca());
		entityManager.flush();
		
	}

	@Override
	public void deleteRestriccion(int restriccionId) {
		entityManager.remove(getRestriccionById(restriccionId));
		
	}

	@Override
	@SuppressWarnings("unchecked")
	
	public List<Restriccion> getAllRestriccion() {
		String hql = "FROM Restriccion as rt ORDER BY rt.id";
		return (List<Restriccion>) entityManager.createQuery(hql).getResultList();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Restriccion getRestriccionByletra(String letra) {
		String hql = "FROM Restriccion as rt WHERE rt.letraplaca = ? ";
		
		List<Restriccion> res=null;
		
		 res=entityManager.createQuery(hql).setParameter(1, letra).getResultList();
		
	    if (!res.isEmpty()) 
	    	return  res.get(0); 
	    	else return null; 
		
		
	}

}
