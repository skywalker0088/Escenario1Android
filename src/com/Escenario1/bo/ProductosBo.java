package com.Escenario1.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IProductoDao;
import com.Escenario1.dto.Productos;
import com.Escenario1.dto.Vendedor;

public class ProductosBo implements IProductoDao{
	DaoFactory dao;
	
	public ProductosBo(){
		dao = DaoFactory.getFactory(0);
	}
	
	
	@Override
	public void create(Productos entity) throws Exception {
		dao.getproductodao().create(entity);
		//dao.cerrar();
		
	}

	@Override
	public Productos retriveById(Long id) throws Exception {
		Productos p= new Productos();
		p=dao.getproductodao().retriveById(id);
		//dao.cerrar();
		return p;
	}

	@Override
	public List<Productos> retrieveAll() throws Exception {
		List<Productos>listaProductos= new ArrayList<Productos>();
		listaProductos=dao.getproductodao().retrieveAll();
		//dao.cerrar();
		return listaProductos;
	}

	@Override
	public void update(Productos entity) throws Exception {
		dao.getproductodao().update(entity);
		//dao.cerrar();
	}

	@Override
	public void delete(Productos entity) throws Exception {
		dao.getproductodao().delete(entity);
		//dao.cerrar();
		
	}
	
}
