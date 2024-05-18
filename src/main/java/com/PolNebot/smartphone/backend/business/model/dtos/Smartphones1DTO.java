package com.PolNebot.smartphone.backend.business.model.dtos;

import java.io.Serializable;

public class Smartphones1DTO implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String nombre;
	private Double precio;
	
	public Smartphones1DTO(String name, Double precio) {
		this.nombre = name;
		this.precio = precio;
	}

	public String getName() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Producto1DTO [name=" + nombre + ", precio=" + precio + "]";
	}
}

