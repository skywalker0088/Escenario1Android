package com.Escenario1.dto;

import java.io.Serializable;
import java.util.Calendar;

import android.R.integer;

public class Ventas implements Serializable{
	private int codVentas;
	private float total;
	private Calendar fecha;
	private int cliente;
	private int vendedor;
	private int administrador;
	
	
	
	
	
	public int getAdministrador() {
		return administrador;
	}
	public void setAdministrador(int administrador) {
		this.administrador = administrador;
	}
	public int getCodVentas() {
		return codVentas;
	}
	public void setCodVentas(int codVentas) {
		this.codVentas = codVentas;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int i) {
		this.cliente = i;
	}
	public int getVendedor() {
		return vendedor;
	}
	public void setVendedor(int vendedor) {
		this.vendedor = vendedor;
	}
	
	
	
	
}
