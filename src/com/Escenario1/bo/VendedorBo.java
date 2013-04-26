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
			
		}

		@Override
		public Vendedor retriveById(Long id) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Vendedor> retrieveAll() throws Exception {
			List<Vendedor>listavendedor= new ArrayList<Vendedor>();
			listavendedor=dao.getvendedordao().retrieveAll();
			return listavendedor;
		}

		@Override
		public void update(Vendedor entity) throws Exception {
			dao.getvendedordao().update(entity);
			
		}

		@Override
		public void delete(Vendedor entity) throws Exception {
			dao.getvendedordao().delete(entity);
			
		}

		@Override
		public Vendedor login(String nombreUsuario, String clave)
				throws Exception {
				Vendedor vdor=null;
				vdor= dao.getvendedordao().login(nombreUsuario, clave);
				if(vdor==null){
			return null;}else{
			return vdor;
			}
		}
		
		
		
		
	
}
