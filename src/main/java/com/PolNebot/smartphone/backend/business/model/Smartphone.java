package com.PolNebot.smartphone.backend.business.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SMARTPHONE")
public class Smartphone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Familia familia;
	
	private String nombre;
	private String marca;
	private Double precio;
	private Double pulgadas;
	private boolean descatalogado;


	public Smartphone() {
		super();
	}
	
	public Smartphone(String nombre, String marca, Double precio, Double tamañoPulgadas, boolean descatalogado) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.precio = precio;
		this.pulgadas = tamañoPulgadas;
		this.descatalogado = descatalogado;
	}

	public Smartphone(Long id, String nombre, String marca, Double precio, Double tamañoPulgadas, boolean descatalogado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
		this.precio = precio;
		this.pulgadas = tamañoPulgadas;
		this.descatalogado = descatalogado;
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
		return pulgadas;
	}
	public void setTamañoPulgadas(Double tamañoPulgadas) {
		this.pulgadas = tamañoPulgadas;
	}
	public boolean isDescatalogado() {
		return descatalogado;
	}
	public void setDescatalogado(boolean descatalogado) {
		this.descatalogado = descatalogado;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
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
		Smartphone other = (Smartphone) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Smartphones [id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio
				+ ", tamañoPulgadas=" + pulgadas + "]";
	}
	
	
	
	
	
}
	