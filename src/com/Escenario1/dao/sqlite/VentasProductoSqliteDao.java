package com.Escenario1.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Escenario1.dao.IVentasProductoDao;
import com.Escenario1.dto.Vendedor;
import com.Escenario1.dto.VentasProducto;

public class VentasProductoSqliteDao implements IVentasProductoDao{

	@Override
	public void create(VentasProducto entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
	
		ContentValues values = new ContentValues();
		values.put("venta", entity.getVenta());
		values.put("producto", entity.getProducto());
		values.put("cantidad", entity.getCantidad());
		values.put("subtotal", entity.getSubtotal());
		values.put("precioproducto", entity.getPrecioproducto());
		database.insert("productoventas", null, values);
	//	daoFactory.cerrar();
	}

	@Override
	public VentasProducto retriveById(Long id) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { String.valueOf(id) };
		VentasProducto vtaspro= null;
		Cursor cursor = database.query("productoventas", null,
				"idProductoVenta=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				vtaspro = new VentasProducto();
				vtaspro.setIdProductoVenta(cursor.getInt(0));
				vtaspro.setVenta(cursor.getInt(1));
				vtaspro.setProducto(cursor.getInt(2));
				vtaspro.setCantidad(cursor.getInt(3));
				vtaspro.setSubtotal(cursor.getFloat(4));
				vtaspro.setPrecioproducto(cursor.getFloat(5));
			} while (cursor.moveToNext());
		}
	//	daoFactory.cerrar();
		cursor.close();
		if (vtaspro == null) {
			return null;
		} else {
			return vtaspro;
		}
	}

	@Override
	public List<VentasProducto> retrieveAll() throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		List<VentasProducto> listaVentasProducto = new ArrayList<VentasProducto>();
		VentasProducto vtaspro;
	
		Cursor cursor = database.rawQuery("SELECT * FROM productoventas", null);
		System.out.print("1");
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				vtaspro = new VentasProducto();
				vtaspro.setIdProductoVenta(cursor.getInt(0));
				vtaspro.setVenta(cursor.getInt(1));
				vtaspro.setProducto(cursor.getInt(2));
				vtaspro.setCantidad(cursor.getInt(3));
				vtaspro.setSubtotal(cursor.getFloat(4));
				vtaspro.setPrecioproducto(cursor.getFloat(5));
				listaVentasProducto.add(vtaspro);
			} while (cursor.moveToNext());
		}

		cursor.close();
	//	daoFactory.cerrar();
		return listaVentasProducto;
	}

	@Override
	public void update(VentasProducto entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		ContentValues values = new ContentValues();
		values.put("venta", entity.getVenta());
		values.put("producto", entity.getProducto());
		values.put("cantidad", entity.getCantidad());
		values.put("subtotal", entity.getSubtotal());
		values.put("precioproducto", entity.getPrecioproducto());
		database.update("productoventas", values, "idProductoVenta= ?",
				new String[] { String.valueOf(entity.getIdProductoVenta()) });
		//daoFactory.cerrar();
	}

	@Override
	public void delete(VentasProducto entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		database.delete("productoventas", "idProductoVenta= ?",
				new String[] { String.valueOf(entity.getIdProductoVenta()) });

	}

	@Override
	public List<VentasProducto> restriveallforventa(int idventa)
			throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { String.valueOf(idventa) };
		List<VentasProducto> listaVentasProducto = new ArrayList<VentasProducto>();
		VentasProducto vtaspro= null;
		Cursor cursor = database.query("productoventas", null,
				"venta=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				vtaspro = new VentasProducto();
				vtaspro.setIdProductoVenta(cursor.getInt(0));
				vtaspro.setVenta(cursor.getInt(1));
				vtaspro.setProducto(cursor.getInt(2));
				vtaspro.setCantidad(cursor.getInt(3));
				vtaspro.setSubtotal(cursor.getFloat(4));
				vtaspro.setPrecioproducto(cursor.getFloat(5));
				listaVentasProducto.add(vtaspro);
			} while (cursor.moveToNext());
		}
	//	daoFactory.cerrar();
		cursor.close();
		
			return listaVentasProducto;
		
	}

}
