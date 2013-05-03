package com.Escenario1.bo;

import java.util.ArrayList;
import java.util.List;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.ICategoriaDao;
import com.Escenario1.dto.Categoria;
import com.Escenario1.dto.Vendedor;

public class CategoriaBo implements ICategoriaDao{
	DaoFactory dao;
	
	public CategoriaBo(){
	dao = DaoFactory.getFactory(0);
	}
	
	
	@Override
	public void create(Categoria entity) throws Exception {
		dao.getcategoriadao().create(entity);
		
	}

	@Override
	public Categoria retriveById(Long id) throws Exception {
		Categoria categoria= new Categoria();
		categoria=dao.getcategoriadao().retriveById(id);
	//	dao.cerrar();
		return categoria;
	}

	@Override
	public List<Categoria> retrieveAll() throws Exception {
		List<Categoria>listaCategoria= new ArrayList<Categoria>();
		listaCategoria=dao.getcategoriadao().retrieveAll();
		//dao.cerrar();
		return listaCategoria;
	}

	@Override
	public void update(Categoria entity) throws Exception {
		dao.getcategoriadao().update(entity);
		
	}

	@Override
	public void delete(Categoria entity) throws Exception {
		dao.getcategoriadao().delete(entity);
		
	}

}
