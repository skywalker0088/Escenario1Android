package com.Escenario1.bo;

import java.util.ArrayList;
import java.util.List;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IClienteDao;
import com.Escenario1.dto.Clientes;

public class ClientesBo implements IClienteDao{
	DaoFactory dao;
	
	public ClientesBo(){
	dao = DaoFactory.getFactory(0);
	}
	
	@Override
	public void create(Clientes entity) throws Exception {
		dao.getclientesdao().create(entity);
		
	}

	@Override
	public Clientes retriveById(Long id) throws Exception {
		Clientes clientes= new Clientes();
		clientes=dao.getclientesdao().retriveById(id);
	//	dao.cerrar();
		return clientes;
	}

	@Override
	public List<Clientes> retrieveAll() throws Exception {
		List<Clientes>listaClientes= new ArrayList<Clientes>();
		listaClientes=dao.getclientesdao().retrieveAll();
		//dao.cerrar();
		return listaClientes;
	}

	@Override
	public void update(Clientes entity) throws Exception {
		dao.getclientesdao().update(entity);
		
	}

	@Override
	public void delete(Clientes entity) throws Exception {
		dao.getclientesdao().delete(entity);
		
	}
	
	

	
}
