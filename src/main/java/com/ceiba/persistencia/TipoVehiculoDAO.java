package com.ceiba.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.entity.TipoVehiculo;
@Transactional
@Repository
public class TipoVehiculoDAO implements ITipoVehiculo {
	private EntityManager entityManager;
	@PersistenceContext 
	public void setentityManager(EntityManager entityManager) {
		 this.entityManager = entityManager;
		 }

	
	@Override
	@SuppressWarnings("unchecked")
	public List<TipoVehiculo> getAllTipoVehiculo() {
		String hql = "FROM TipoVehiculo as tv ORDER BY tv.id";
		return (List<TipoVehiculo>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public TipoVehiculo getTipoVehiculoById(int tipoVehiculoId) {
		return entityManager.find(TipoVehiculo.class, tipoVehiculoId);
	}

	@Override
	public void addTipoVehiculo(TipoVehiculo tipoVehiculo) {
		entityManager.persist(tipoVehiculo);
		
	}

	@Override
	public void updateTipoVehiculo(TipoVehiculo tipoVehiculo) {
	
		TipoVehiculo tv = getTipoVehiculoById(tipoVehiculo.getId());
		tv.setDescripcion(tipoVehiculo.getDescripcion());
		tv.setRecargo(tipoVehiculo.getRecargo());
		tv.setStock(tipoVehiculo.getStock());
		tv.setValordia(tipoVehiculo.getValordia());
		tv.setValorhora(tipoVehiculo.getValorhora());
		entityManager.flush();
		
		
	}

	@Override
	public void deleteTipoVehiculo(int tipoVehiculoId) {
		entityManager.remove(getTipoVehiculoById(tipoVehiculoId));
		
	}

}

