package com.Escenario1.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IVendedorDao;

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
	public IVendedorDao getvendedordao() {
		// TODO Auto-generated method stub
		return null;
	}
	


	

}
