package com.Escenario1.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Escenario1.dao.DaoFactory;
import com.Escenario1.dao.ICategoriaDao;
import com.Escenario1.dto.Categoria;
import com.Escenario1.dto.Vendedor;

public class CategoriaSqliteDao implements ICategoriaDao {

	@Override
	public void create(Categoria entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
	
		ContentValues values = new ContentValues();
		values.put("inflacion", entity.getInflacion());
		values.put("nombre", entity.getNombre());
		database.insert("categoria", null, values);
		
	}

	@Override
	public Categoria retriveById(Long id) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { String.valueOf(id) };
		Categoria cate = null;
		Cursor cursor = database.query("categoria", null,
				"idCategoria=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				cate = new Categoria();
				cate.setIdCategoria(cursor.getInt(0));
				cate.setInflacion(cursor.getFloat(1));
				cate.setNombre(cursor.getString(2));
			} while (cursor.moveToNext());
		}
	//	daoFactory.cerrar();
		if (cate == null) {
			return null;
		} else {
			return cate;
		}
	}

	@Override
	public List<Categoria> retrieveAll() throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		List<Categoria> listaCategoria = new ArrayList<Categoria>();
		Categoria cate;
	
		Cursor cursor = database.rawQuery("SELECT * FROM categoria", null);
		System.out.print("1");
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				cate = new Categoria();
				cate.setIdCategoria(cursor.getInt(0));
				cate.setInflacion(cursor.getFloat(1));
				cate.setNombre(cursor.getString(2));
				listaCategoria.add(cate);
			} while (cursor.moveToNext());
		}

		cursor.close();
	//	daoFactory.cerrar();
		return listaCategoria;
	}

	@Override
	public void update(Categoria entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		ContentValues values = new ContentValues();
		values.put("inflacion", entity.getInflacion());
		values.put("nombre", entity.getNombre());
		
		database.update("categoria", values, "idCategoria= ?",
				new String[] { String.valueOf(entity.getIdCategoria()) });
		//daoFactory.cerrar();
		
	}

	@Override
	public void delete(Categoria entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		database.delete("categoria", "idCategoria= ?" + entity.getIdCategoria(),
				new String[] { String.valueOf(entity.getIdCategoria()) });

		
	}
	
}