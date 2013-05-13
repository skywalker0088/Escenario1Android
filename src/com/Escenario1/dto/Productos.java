package com.Escenario1.dto;

import java.io.Serializable;

import android.R.integer;
import android.R.string;

public class Productos implements Serializable {

	private int codProducto;
	private String nombre;
	private int categoria;
	private int stock;
	private float precio;
	
	
	
	public int getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	
	
	
	
	

	
	
	
	
}
