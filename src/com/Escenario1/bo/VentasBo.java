package com.Escenario1.bo;

import java.util.ArrayList;
import java.util.List;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IVentasDao;
import com.Escenario1.dto.Vendedor;
import com.Escenario1.dto.Ventas;

public class VentasBo implements IVentasDao{
	DaoFactory dao;
	
	public VentasBo(){
	dao = DaoFactory.getFactory(0);
	}
	@Override
	public void create(Ventas entity) throws Exception {
		dao.getventadao().create(entity);
		
	}

	@Override
	public Ventas retriveById(Long id) throws Exception {
		Ventas v= new Ventas();
		v=dao.getventadao().retriveById(id);
	//	dao.cerrar();
		return v;
	}

	@Override
	public List<Ventas> retrieveAll() throws Exception {
		List<Ventas>listaVentas= new ArrayList<Ventas>();
		listaVentas=dao.getventadao().retrieveAll();
		//dao.cerrar();
		return listaVentas;
	}

	@Override
	public void update(Ventas entity) throws Exception {
		dao.getventadao().update(entity);
		
	}

	@Override
	public void delete(Ventas entity) throws Exception {
		dao.getventadao().delete(entity);
		
	}

}
