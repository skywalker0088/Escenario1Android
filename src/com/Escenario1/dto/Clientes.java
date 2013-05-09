package com.Escenario1.dto;

import java.io.Serializable;

import android.R.integer;
import android.R.string;

//import org.simpleframework.xml.Element;
//import org.simpleframework.xml.Root;

/*@Root(name="cliente")*/
public class Clientes implements Serializable {
	// @Element(name=("id"), required = true)
	private int idCliente;
	// @Element( required = false)
	private String apellido;
	// @Element(required = false)
	private String nombre;
	// @Element( required = false)
	private String razonsocial;
	// @Element( required = false)
	private String direccion;
	// @Element( required = false)
	private String localidad;
	// @Element(required = false)
	private String provincia;
	// @Element(name=("id"), required = false)
	private String telefono;
	// @Element(required = false)
	private String foto;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonsocial() {
		return razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	

}
