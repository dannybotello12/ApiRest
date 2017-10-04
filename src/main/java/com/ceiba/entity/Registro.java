package com.ceiba.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Registro")
public class Registro implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="placa")
	private String placa;
	@Column(name="fechaIngreso")
	private Date fechaIngreso;
	@Column(name="fechaSalida")
	private Date fechaSalida;
	@Column(name="valor")
	private int valor;
	@Column(name="recargo")
	private Boolean recargo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_vehiculo_id")
	   private TipoVehiculo tipoVehiculo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public boolean isRecargo() {
		return recargo;
	}

	public void setRecargo(boolean recargo) {
		this.recargo = recargo;
	}
	
	public Registro(int id, String placa, Date fechaIngreso, Date fechaSalida, int valor, TipoVehiculo tipoVehiculo, boolean recargo) {
		super();
		this.id = id;
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.tipoVehiculo = tipoVehiculo;
		this.recargo=recargo;
	}

	
	public Registro(){
		
	}
}
