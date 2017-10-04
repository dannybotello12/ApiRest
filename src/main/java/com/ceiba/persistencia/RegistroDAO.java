package com.ceiba.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.entity.Registro;

@Transactional
@Repository
public class RegistroDAO implements IRegistro {
	
	private EntityManager entityManager;
	@PersistenceContext 
	public void setentityManager(EntityManager entityManager) {
		 this.entityManager = entityManager;
		 }

	@Override
	@SuppressWarnings("unchecked")
	public List<Registro> getAllRegistro() {

		String hql = "FROM Registro as rg ORDER BY rg.id";
		return (List<Registro>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Registro getRegistroById(int registroId) {
		return entityManager.find(Registro.class, registroId);
	}

	@Override
	public Registro getRegistroByPlaca(String placa) {

		String hql = "FROM Registro as rg WHERE rg.placa = ? and rg.fechaSalida is null";
		return (Registro) entityManager.createQuery(hql).setParameter(1, placa).getSingleResult();

	}

	@Override
	public boolean registroExists(String placa) {
		String hql = "FROM Registro as rg WHERE rg.placa = ? and rg.fechaSalida is null";
		int count = entityManager.createQuery(hql).setParameter(1, placa).getResultList().size();
		return count > 0 ? true : false;

	}

	@Override
	public void addRegistro(Registro registro) {
		entityManager.persist(registro);
		

	}

	@Override
	public String updateRegistro(Registro registro) {
		Registro reg = getRegistroById(registro.getId());
		reg.setFechaSalida(registro.getFechaSalida());
		reg.setValor(registro.getValor());
		entityManager.flush();
		return "Ejecutado";

	}

	@Override
	public void deleteRegistro(int registroId) {
		
		Registro r=getRegistroById(registroId);
		
		entityManager.remove(r);

	}

	@Override
	public int getRegistroBytipoVehiculoActivo(int tipoVehiculoID) {
		String hql = "select rg FROM Registro rg join rg.tipoVehiculo tv WHERE tv.id = ? and rg.fechaSalida is null";
		return entityManager.createQuery(hql).setParameter(1, tipoVehiculoID).getResultList().size();
		
	}



}

