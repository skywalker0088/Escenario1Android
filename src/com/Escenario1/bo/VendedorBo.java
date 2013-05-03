package com.Escenario1.bo;

import java.util.ArrayList;
import java.util.List;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IVendedorDao;
import com.Escenario1.dao.sqlite.SqliteDaoFactory;
import com.Escenario1.dto.Vendedor;


public class VendedorBo implements IVendedorDao{
		DaoFactory dao;
		
		public VendedorBo(){
		dao = DaoFactory.getFactory(0);
		}
		
		
		@Override
		public void create(Vendedor entity) throws Exception {
			dao.getvendedordao().create(entity);
			//dao.cerrar();
		}

		@Override
		public Vendedor retriveById(Long id) throws Exception {
			Vendedor v= new Vendedor();
			v=dao.getvendedordao().retriveById(id);
		//	dao.cerrar();
			return v;
		}

		@Override
		public List<Vendedor> retrieveAll() throws Exception {
			List<Vendedor>listavendedor= new ArrayList<Vendedor>();
			listavendedor=dao.getvendedordao().retrieveAll();
			//dao.cerrar();
			return listavendedor;
		}

		@Override
		public void update(Vendedor entity) throws Exception {
			dao.getvendedordao().update(entity);
			//dao.cerrar();
		}

		@Override
		public void delete(Vendedor entity) throws Exception {
			dao.getvendedordao().delete(entity);
			//dao.cerrar();
		}

		@Override
		public Vendedor login(String nombreUsuario, String clave) {
				Vendedor vdor=null;
				try {
					vdor= dao.getvendedordao().login(nombreUsuario, clave);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//dao.cerrar();
			return vdor;
			
		}
		
		
		
		
	
}
