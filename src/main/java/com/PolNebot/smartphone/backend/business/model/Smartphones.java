package com.PolNebot.smartphone.backend.business.model;

import java.io.Serializable;
import java.util.Objects;

public class Smartphones implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String marca;
	private Double precio;
	private Double tamañoPulgadas;
	
	
	
	public Smartphones() {
		super();
	}

	public Smartphones(Long id, String nombre, String marca, Double precio, Double tamañoPulgadas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
		this.precio = precio;
		this.tamañoPulgadas = tamañoPulgadas;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getTamañoPulgadas() {
		return tamañoPulgadas;
	}
	public void setTamañoPulgadas(Double tamañoPulgadas) {
		this.tamañoPulgadas = tamañoPulgadas;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Smartphones other = (Smartphones) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Smartphones [id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio
				+ ", tamañoPulgadas=" + tamañoPulgadas + "]";
	}
	
	
	
	
	
}
	