package com.Escenario1.dto;

import java.io.Serializable;

import android.R.integer;

public class Categoria implements Serializable{
		private int idCategoria;
		private float inflacion;
		private String nombre;
		
		
		public int getIdCategoria() {
			return idCategoria;
		}
		public void setIdCategoria(int idCategoria) {
			this.idCategoria = idCategoria;
		}
		public float getInflacion() {
			return inflacion;
		}
		public void setInflacion(float inflacion) {
			this.inflacion = inflacion;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		
		
		
}
