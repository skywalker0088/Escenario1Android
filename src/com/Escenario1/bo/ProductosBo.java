package com.Escenario1.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Escenario1.dto.Productos;

public class ProductosBo {
	public List<Productos> retriveProducto(){
		ArrayList<Productos>productos= new ArrayList<Productos>();
		Productos producto = new Productos();
		producto.setCodigo(1);
		producto.setNombre("teclado");
		productos.add(producto);
		producto.setCodigo(1);
		producto.setNombre("mouse");
		productos.add(producto);
		return productos;
	}
}
