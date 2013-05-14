package com.Escenario1.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Escenario1.dao.IAdministradorDao;
import com.Escenario1.dto.Administrador;
import com.Escenario1.dto.Vendedor;

public class AdministradorSqliteDao implements IAdministradorDao {

	@Override
	public void create(Administrador entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
	
		ContentValues values = new ContentValues();
		values.put("apellido", entity.getApellido());
		values.put("nombre", entity.getNombre());
		values.put("email", entity.getEmail());	
		values.put("clave", entity.getClave());
		values.put("estado", entity.isEstado());
		database.insert("administrador", null, values);
	//	daoFactory.cerrar();
		
	}

	@Override
	public Administrador retriveById(Long id) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { String.valueOf(id) };
		Administrador admin= null;
		Cursor cursor = database.query("administrador", null,
				"idAdministrador=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				admin = new Administrador();
				admin.setIdAdministrador(cursor.getInt(0));
				admin.setApellido(cursor.getString(1));
				admin.setNombre(cursor.getString(2));
				admin.setEmail(cursor.getString(3));
				admin.setClave(cursor.getString(4));
				if (cursor.getString(5).equalsIgnoreCase("true")) {
					admin.setEstado(true);
				} else {
					admin.setEstado(false);
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
	//	daoFactory.cerrar();
		if (admin == null) {
			return null;
		} else {
			return admin;
		}
	}

	@Override
	public List<Administrador> retrieveAll() throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		List<Administrador> listaAdministrador = new ArrayList<Administrador>();
		Administrador admin;
	
		Cursor cursor = database.rawQuery("SELECT * FROM administrador", null);
		System.out.print("1");
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				admin = new Administrador();
				admin.setIdAdministrador(cursor.getInt(0));
				admin.setApellido(cursor.getString(1));
				admin.setNombre(cursor.getString(2));
				admin.setEmail(cursor.getString(3));
				admin.setClave(cursor.getString(4));
				if (cursor.getString(5).equalsIgnoreCase("true")) {
					admin.setEstado(true);
				} else {
					admin.setEstado(false);
				}
				listaAdministrador.add(admin);
			} while (cursor.moveToNext());
		}

		cursor.close();
	//	daoFactory.cerrar();
		return listaAdministrador;
	}

	@Override
	public void update(Administrador entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		ContentValues values = new ContentValues();
		values.put("apellido", entity.getApellido());
		values.put("nombre", entity.getNombre());
		values.put("email", entity.getEmail());	
		values.put("clave", entity.getClave());
		values.put("estado", entity.isEstado());
		database.update("administrador", values, "idAdministrador= ?",
				new String[] { String.valueOf(entity.getIdAdministrador()) });
		//daoFactory.cerrar();
	}

	@Override
	public void delete(Administrador entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		database.delete("administrador", "idAdministrador= ?",
				new String[] { String.valueOf(entity.getIdAdministrador()) });

		
	}

	@Override
	public Administrador login(String nombreUsuario, String clave)
			throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { nombreUsuario, clave,"1" };
		Administrador admin = null;
		Cursor cursor = database.query("administrador", null,
				"email=? and clave=? and estado=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				admin = new Administrador();
				admin.setIdAdministrador(cursor.getInt(0));
				admin.setApellido(cursor.getString(1));
				admin.setNombre(cursor.getString(2));
				admin.setEmail(cursor.getString(3));
				admin.setClave(cursor.getString(4));
				if (cursor.getString(5).equalsIgnoreCase("true")) {
					admin.setEstado(true);
				} else {
					admin.setEstado(false);
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
	//	daoFactory.cerrar();
		if (admin == null) {
			return null;
		} else {
			return admin;
		}
	}

}
