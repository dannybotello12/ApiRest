package com.ceiba.entity;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TipoVehiculo")
public class TipoVehiculo implements Serializable { 
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="stock")
	private int stock;
	@Column(name="recargo")
	private int recargo;
	@Column(name="valorhora")
	private int valorhora;
	@Column(name="valordia")
	private int valordia;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoVehiculo")
	  private List<Registro> registros;
	
	

	public TipoVehiculo(){
		
	}
	
	
	public TipoVehiculo(int id, String descripcion, int stock, int recargo, int valorhora, int valordia) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.stock = stock;
		this.recargo = recargo;
		this.valorhora = valorhora;
		this.valordia = valordia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getRecargo() {
		return recargo;
	}

	public void setRecargo(int recargo) {
		this.recargo = recargo;
	}

	public int getValorhora() {
		return valorhora;
	}

	public void setValorhora(int valorhora) {
		this.valorhora = valorhora;
	}

	public int getValordia() {
		return valordia;
	}

	public void setValordia(int valordia) {
		this.valordia = valordia;
	}
	
}
