package com.Escenario1.dto;

import java.io.Serializable;
import java.util.Calendar;

import android.R.integer;

public class Ventas implements Serializable{
	private int codVentas;
	private float total;
	private Calendar fecha;
	private integer cliente;
	private integer vendedor;
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
	public integer getCliente() {
		return cliente;
	}
	public void setCliente(integer cliente) {
		this.cliente = cliente;
	}
	public integer getVendedor() {
		return vendedor;
	}
	public void setVendedor(integer vendedor) {
		this.vendedor = vendedor;
	}
	
	
	
	
}
