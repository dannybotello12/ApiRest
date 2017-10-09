package com.ceiba.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Restricciones")
public class Restriccion implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="letraplaca")
	private String letraplaca;
	@Column(name="dias")
	private String dias;
	
	
	
	public Restriccion(){
		
	}
	
	public Restriccion(int id, String letraplaca, String dias) {
		super();
		this.id = id;
		this.letraplaca = letraplaca;
		this.dias = dias;
	}
	public int getId() {
		return id;
	}
	
	public String getLetraplaca() {
		return letraplaca;
	}
	public void setLetraplaca(String letraplaca) {
		this.letraplaca = letraplaca;
	}
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	}

	
	
	
	
}
