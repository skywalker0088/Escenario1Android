package com.Escenario1.bo;

import java.util.ArrayList;
import java.util.List;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IVentasProductoDao;
import com.Escenario1.dto.Vendedor;
import com.Escenario1.dto.VentasProducto;

public class VentasProductosBo implements IVentasProductoDao{

	
	DaoFactory dao;
	
	public VentasProductosBo(){
	dao = DaoFactory.getFactory(0);
	}
	
	
	@Override
	public void create(VentasProducto entity) throws Exception {
		dao.getventasproductodao().create(entity);
		
	}

	@Override
	public VentasProducto retriveById(Long id) throws Exception {
		VentasProducto v= new VentasProducto();
		v=dao.getventasproductodao().retriveById(id);
	//	dao.cerrar();
		return v;
	}

	@Override
	public List<VentasProducto> retrieveAll() throws Exception {
		List<VentasProducto>listaVentasProducto= new ArrayList<VentasProducto>();
		listaVentasProducto=dao.getventasproductodao().retrieveAll();
		//dao.cerrar();
		return listaVentasProducto;
	}

	@Override
	public void update(VentasProducto entity) throws Exception {
		dao.getventasproductodao().update(entity);
		
	}

	@Override
	public void delete(VentasProducto entity) throws Exception {
		dao.getventasproductodao().delete(entity);
		
	}

}
