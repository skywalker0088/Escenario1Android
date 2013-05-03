package com.Escenario1.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IAdministradorDao;
import com.Escenario1.dao.ICategoriaDao;
import com.Escenario1.dao.IClienteDao;
import com.Escenario1.dao.IProductoDao;
import com.Escenario1.dao.IVendedorDao;
import com.Escenario1.dao.IVentasDao;
import com.Escenario1.dao.IVentasProductoDao;

public class SqliteDaoFactory extends DaoFactory{

	private SQLiteDatabase database;

	@Override
	public SQLiteDatabase abrir() {
		SQLiteDatabase database = null;
		if(database == null){
			String dbPath = "mnt/sdcard/Escenario1.sqlite";
			this.setDatabase(SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.NO_LOCALIZED_COLLATORS));
		}
		return this.getDatabase();			
	}
	public SQLiteDatabase getDatabase() {
		return database;
	}
	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}
	
	@Override
	public void cerrar() {
		database.close();
		
	}

	@Override
	public void iniciarTransaccion() {
		database.beginTransaction();
		
	}

	@Override
	public void commit() {
		database.setTransactionSuccessful();
		database.endTransaction();
	}

	@Override
	public void rollback() {
		database.endTransaction();
	}

	@Override
	public void backup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VendedorSqliteDao getvendedordao() {
		VendedorSqliteDao v= new VendedorSqliteDao();
		return v;
	}
	@Override
	public ProductoSqliteDao getproductodao() {
		ProductoSqliteDao p = new ProductoSqliteDao();
		return p;
	}
	@Override
	public CategoriaSqliteDao getcategoriadao() {
		CategoriaSqliteDao c = new CategoriaSqliteDao();
		return c;
	}
	@Override
	public ClienteSqliteDao getclientesdao() {
		ClienteSqliteDao clie = new ClienteSqliteDao();
		return clie;
	}
	@Override
	public VentasSqliteDao getventadao() {
		VentasSqliteDao vtas= new VentasSqliteDao();
		return vtas;
	}
	@Override
	public VentasProductoSqliteDao getventasproductodao() {
		VentasProductoSqliteDao vtasprod = new VentasProductoSqliteDao();
		return vtasprod;
	}
	@Override
	public AdministradorSqliteDao getadministradordao() {
		AdministradorSqliteDao admi = new AdministradorSqliteDao();
		return admi;
	}
	


	

}
