package com.Escenario1.dao;

import com.Escenario1.dao.sqlite.SqliteDaoFactory;



public abstract class DaoFactory {
	public static final int SQLITE = 0;

	public final static DaoFactory getFactory(int p_factory){
		switch (p_factory) {
		case SQLITE:
			return new SqliteDaoFactory();
			
		default:
			break;
		}
		return null;
	}

	public abstract Object abrir();

	public abstract void cerrar();

	public abstract void iniciarTransaccion();

	public abstract void commit();

	public abstract void rollback();

	public abstract void backup();

	public abstract void restore();
	
	public abstract IVendedorDao getvendedordao();
	public abstract IProductoDao getproductodao();
	public abstract ICategoriaDao getcategoriadao();
	public abstract IClienteDao getclientesdao();
	public abstract IVentasDao getventadao();
	public abstract IVentasProductoDao getventasproductodao();
	public abstract IAdministradorDao getadministradordao();
}
