package com.Escenario1.dao.sqlite;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

import com.Escenario1.dao.IVentasDao;
import com.Escenario1.dto.Vendedor;
import com.Escenario1.dto.Ventas;

public class VentasSqliteDao implements IVentasDao{

	@Override
	public void create(Ventas entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		ContentValues values = new ContentValues();
		values.put("total", entity.getTotal());
		values.put("fecha", String.valueOf(sdf.format(entity.getFecha().getTime())));
		values.put("cliente", String.valueOf(entity.getCliente()));
		values.put("vendedor", String.valueOf(entity.getVendedor()));
		values.put("administrador", String.valueOf(entity.getAdministrador()));
		database.insert("venta", null, values);
		
	}

	@Override
	public Ventas retriveById(Long id) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { String.valueOf(id) };
		Ventas vtas = null;
		Calendar calendario = Calendar.getInstance();
		Cursor cursor = database.query("venta", null,
				"codVenta=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				vtas = new Ventas();
				vtas.setCodVentas(cursor.getInt(0));
				vtas.setTotal(cursor.getFloat(1));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				calendario.setTime(df.parse(cursor.getString(2)));
				vtas.setFecha(calendario);
				vtas.setCliente(cursor.getInt(3));
				vtas.setVendedor(cursor.getInt(4));		
				vtas.setAdministrador(cursor.getInt(5));	
			} while (cursor.moveToNext());
		}
		cursor.close();
	//	daoFactory.cerrar();
		if (vtas == null) {
			return null;
		} else {
			return vtas;
		}
	}

	@Override
	public List<Ventas> retrieveAll() throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		List<Ventas> listaVentas = new ArrayList<Ventas>();
		Ventas vtas;
		Calendar calendario = Calendar.getInstance();
		Cursor cursor = database.rawQuery("SELECT * FROM venta", null);
		System.out.print("1");
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				vtas = new Ventas();
				vtas.setCodVentas(cursor.getInt(0));
				vtas.setTotal(cursor.getFloat(1));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				calendario.setTime(df.parse(cursor.getString(2)));
				vtas.setFecha(calendario);
				vtas.setCliente(cursor.getInt(3));
				vtas.setVendedor(cursor.getInt(4));	
				vtas.setAdministrador(cursor.getInt(5));
				listaVentas.add(vtas);
			} while (cursor.moveToNext());
		}

		cursor.close();
	//	daoFactory.cerrar();
		return listaVentas;
	}

	@Override
	public void update(Ventas entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		ContentValues values = new ContentValues();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		values.put("total", entity.getTotal());
		values.put("fecha", String.valueOf(sdf.format(entity.getFecha().getTime())));
		values.put("cliente", String.valueOf(entity.getCliente()));
		values.put("vendedor", String.valueOf(entity.getVendedor()));
		values.put("administrador", String.valueOf(entity.getAdministrador()));
		database.update("venta", values, "codVenta= ?",
				new String[] { String.valueOf(entity.getCodVentas()) });
		//daoFactory.cerrar();
	}

	@Override
	public void delete(Ventas entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		database.delete("venta", "codVenta= ?",
				new String[] { String.valueOf(entity.getCodVentas()) });
		
	}

	@Override
	public int ultimovalor(int index) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		int valor=0;
		Cursor contenido = database.rawQuery("SELECT MAX(codVenta) AS id FROM venta", null);
		// Nos aseguramos de que existe al menos un registro
		if (contenido.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
				valor=contenido.getInt(0);
			do {
				
			} while (contenido.moveToNext());
		}
		contenido.close();
		return valor;
	}

}
