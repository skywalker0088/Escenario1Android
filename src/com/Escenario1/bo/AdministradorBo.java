package com.Escenario1.bo;

import java.util.ArrayList;
import java.util.List;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IAdministradorDao;
import com.Escenario1.dto.Administrador;
import com.Escenario1.dto.Vendedor;

public class AdministradorBo implements IAdministradorDao {
	DaoFactory dao;
	
	public AdministradorBo(){
		dao = DaoFactory.getFactory(0);
	}
	
	
	@Override
	public void create(Administrador entity) throws Exception {
		dao.getadministradordao().create(entity);
		
	}

	@Override
	public Administrador retriveById(Long id) throws Exception {
		Administrador admi= new Administrador();
		admi=dao.getadministradordao().retriveById(id);
	//	dao.cerrar();
		return admi;
	}

	@Override
	public List<Administrador> retrieveAll() throws Exception {
		List<Administrador>listaAdministrador= new ArrayList<Administrador>();
		listaAdministrador=dao.getadministradordao().retrieveAll();
		//dao.cerrar();
		return listaAdministrador;
	}

	@Override
	public void update(Administrador entity) throws Exception {
		dao.getadministradordao().update(entity);
	}

	@Override
	public void delete(Administrador entity) throws Exception {
		dao.getadministradordao().delete(entity);
		//dao.cerrar();
	}

	@Override
	public Administrador login(String nombreUsuario, String clave)
			throws Exception {
		Administrador admi=null;
		try {
			admi= dao.getadministradordao().login(nombreUsuario, clave);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dao.cerrar();
	return admi;
	}

}
