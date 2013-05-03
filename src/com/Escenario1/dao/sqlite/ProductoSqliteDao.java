package com.Escenario1.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Escenario1.dao.IProductoDao;
import com.Escenario1.dto.Productos;
import com.Escenario1.dto.Vendedor;

public class ProductoSqliteDao implements IProductoDao{

	@Override
	public void create(Productos entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
	
		ContentValues values = new ContentValues();
		values.put("categoria", entity.getCategoria());
		values.put("codProducto", entity.getCodProducto());
		values.put("nombre", entity.getNombre());
		values.put("precio", entity.getPrecio());
		values.put("stock", entity.getStock());
		database.insert("producto", null, values);		
	}

	@Override
	public Productos retriveById(Long id) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { String.valueOf(id) };
		Productos pro = null;
		Cursor cursor = database.query("producto", null,
				"codProducto=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				pro = new Productos();
				pro.setCodProducto(cursor.getInt(0));
				pro.setNombre(cursor.getString(1));
				pro.setCategoria(cursor.getInt(2));
				pro.setStock(cursor.getInt(3));
				pro.setPrecio(cursor.getFloat(4));
			} while (cursor.moveToNext());
		}
	//	daoFactory.cerrar();
		if (pro == null) {
			return null;
		} else {
			return pro;
		}
	}

	@Override
	public List<Productos> retrieveAll() throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		List<Productos> listaProductos = new ArrayList<Productos>();
		Productos pro;
	
		Cursor cursor = database.rawQuery("SELECT * FROM producto", null);
		System.out.print("1");
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				pro = new Productos();
				pro.setCodProducto(cursor.getInt(0));
				pro.setNombre(cursor.getString(1));
				pro.setCategoria(cursor.getInt(2));
				pro.setStock(cursor.getInt(3));
				pro.setPrecio(cursor.getFloat(4));
				
				listaProductos.add(pro);
			} while (cursor.moveToNext());
		}

		cursor.close();
	//	daoFactory.cerrar();
		return listaProductos;
	}

	@Override
	public void update(Productos entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		ContentValues values = new ContentValues();
		values.put("categoria", entity.getCategoria());
		values.put("codProducto", entity.getCodProducto());
		values.put("nombre", entity.getNombre());
		values.put("precio", entity.getPrecio());
		values.put("stock", entity.getStock());
		database.update("producto", values, "codProducto= ?",
				new String[] { String.valueOf(entity.getCodProducto()) });
		//daoFactory.cerrar();
		
	}

	@Override
	public void delete(Productos entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		database.delete("producto", "codProducto= ?" + entity.getCodProducto(),
				new String[] { String.valueOf(entity.getCodProducto()) });
		
	}

}
