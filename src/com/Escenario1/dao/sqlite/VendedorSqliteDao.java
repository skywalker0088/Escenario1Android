package com.Escenario1.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.IVendedorDao;
import com.Escenario1.dto.Vendedor;

public class VendedorSqliteDao implements IVendedorDao {

	@Override
	public void create(Vendedor entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
	
		ContentValues values = new ContentValues();
		values.put("apellido", entity.getApellido());
		values.put("nombre", entity.getNombre());
		values.put("email", entity.getEmail());
		values.put("foto", entity.getFoto());
		values.put("clave", entity.getClave());
		database.insert("vendedore", null, values);
	//	daoFactory.cerrar();
	}

	@Override
	public Vendedor retriveById(Long id) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { String.valueOf(id) };
		Vendedor vdor = null;
		Cursor cursor = database.query("vendedore", null,
				"idVendedor=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				vdor = new Vendedor();
				vdor.setIdVendedor(cursor.getInt(0));
				vdor.setApellido(cursor.getString(1));
				vdor.setNombre(cursor.getString(2));
				vdor.setEmail(cursor.getString(3));
				vdor.setFoto(cursor.getString(4));
				vdor.setClave(cursor.getString(5));
			} while (cursor.moveToNext());
		}
	//	daoFactory.cerrar();
		if (vdor == null) {
			return null;
		} else {
			return vdor;
		}
	}

	@Override
	public List<Vendedor> retrieveAll() throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		List<Vendedor> listaVendedor = new ArrayList<Vendedor>();
		Vendedor vdor;
	
		Cursor cursor = database.rawQuery("SELECT * FROM vendedore", null);
		System.out.print("1");
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				vdor = new Vendedor();
				vdor.setIdVendedor(cursor.getInt(0));
				vdor.setApellido(cursor.getString(1));
				vdor.setNombre(cursor.getString(2));
				vdor.setEmail(cursor.getString(3));
				vdor.setFoto(cursor.getString(4));
				vdor.setClave(cursor.getString(5));
				listaVendedor.add(vdor);
			} while (cursor.moveToNext());
		}

		cursor.close();
	//	daoFactory.cerrar();
		return listaVendedor;
	}

	@Override
	public void update(Vendedor entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		ContentValues values = new ContentValues();
		values.put("apellido", entity.getApellido());
		values.put("nombre", entity.getNombre());
		values.put("email", entity.getEmail());
		values.put("foto", entity.getFoto());
		values.put("clave", entity.getClave());
		database.update("vendedore", values, "idVendedor= ?",
				new String[] { String.valueOf(entity.getIdVendedor()) });
		//daoFactory.cerrar();
	}
	
	@Override
	public void delete(Vendedor entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		database.delete("vendedore", "idVendedor= ?" + entity.getIdVendedor(),
				new String[] { String.valueOf(entity.getIdVendedor()) });

	}

	@Override
	public Vendedor login(String nombreUsuario, String clave) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { nombreUsuario, clave };
		Vendedor vdor = null;
		Cursor cursor = database.query("vendedore", null,
				"email=? and clave=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				vdor = new Vendedor();
				vdor.setIdVendedor(cursor.getInt(0));
				vdor.setApellido(cursor.getString(1));
				vdor.setNombre(cursor.getString(2));
				vdor.setEmail(cursor.getString(3));
				vdor.setFoto(cursor.getString(4));
				vdor.setClave(cursor.getString(5));
			} while (cursor.moveToNext());
		}
	//	daoFactory.cerrar();
		if (vdor == null) {
			return null;
		} else {
			return vdor;
		}
		
	}

}
