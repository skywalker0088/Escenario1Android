package com.Escenario1.dto;

import java.io.Serializable;

import android.R.integer;
import android.R.string;

public class VentasProducto implements Serializable{
	private int idProductoVenta;
	private int venta;
	private int producto;
	private int cantidad;
	private float subtotal;
	private float precioproducto;
	public int getIdProductoVenta() {
		return idProductoVenta;
	}
	public void setIdProductoVenta(int idProductoVenta) {
		this.idProductoVenta = idProductoVenta;
	}
	public int getVenta() {
		return venta;
	}
	public void setVenta(int venta) {
		this.venta = venta;
	}
	public int getProducto() {
		return producto;
	}
	public void setProducto(int producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	public float getPrecioproducto() {
		return precioproducto;
	}
	public void setPrecioproducto(float precioproducto) {
		this.precioproducto = precioproducto;
	}
	
	
	
}
