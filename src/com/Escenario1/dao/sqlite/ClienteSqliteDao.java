package com.Escenario1.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Escenario1.dao.IClienteDao;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Vendedor;

public class ClienteSqliteDao implements IClienteDao{

	@Override
	public void create(Clientes entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
	
		ContentValues values = new ContentValues();
		values.put("apellido", entity.getApellido());
		values.put("nombre", entity.getNombre());
		values.put("razonsocial", entity.getRazonsocial());
		values.put("direccion", entity.getDireccion());
		values.put("localidad", entity.getLocalidad());
		values.put("provincia", entity.getProvincia());
		values.put("telefono", entity.getTelefono());
		//values.put("foto", entity.getFoto());
		database.insert("cliente", null, values);
		
	}

	@Override
	public Clientes retriveById(Long id) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		String[] args = new String[] { String.valueOf(id) };
		Clientes cli = null;
		Cursor cursor = database.query("cliente", null,
				"idVendedor=?", args, null, null, null);
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				cli = new Clientes();
				cli.setIdCliente(cursor.getInt(0));
				cli.setApellido(cursor.getString(1));
				cli.setNombre(cursor.getString(2));
				cli.setRazonsocial(cursor.getString(3));
				cli.setDireccion(cursor.getString(4));
				cli.setLocalidad(cursor.getString(5));
				cli.setProvincia(cursor.getString(6));
				cli.setTelefono(cursor.getString(7));
				//cli.setFoto(cursor.getString(8));
			} while (cursor.moveToNext());
		}
		cursor.close();
	//	daoFactory.cerrar();
		if (cli == null) {
			return null;
		} else {
			return cli;
		}
	}

	@Override
	public List<Clientes> retrieveAll() throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		List<Clientes> listaClientes = new ArrayList<Clientes>();
		Clientes cli;
	
		Cursor cursor = database.rawQuery("SELECT * FROM cliente", null);
		System.out.print("1");
		// Nos aseguramos de que existe al menos un registro
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				cli = new Clientes();
				cli.setIdCliente(cursor.getInt(0));
				cli.setApellido(cursor.getString(1));
				cli.setNombre(cursor.getString(2));
				cli.setRazonsocial(cursor.getString(3));
				cli.setDireccion(cursor.getString(4));
				cli.setLocalidad(cursor.getString(5));
				cli.setProvincia(cursor.getString(6));
				cli.setTelefono(cursor.getString(7));
				//cli.setFoto(cursor.getString(8));
				listaClientes.add(cli);
			} while (cursor.moveToNext());
		}

		cursor.close();
	//	daoFactory.cerrar();
		return listaClientes;
	}

	@Override
	public void update(Clientes entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		ContentValues values = new ContentValues();
		values.put("apellido", entity.getApellido());
		values.put("nombre", entity.getNombre());
		values.put("razonsocial", entity.getRazonsocial());
		values.put("direccion", entity.getDireccion());
		values.put("localidad", entity.getLocalidad());
		values.put("provincia", entity.getProvincia());
		values.put("telefono", entity.getTelefono());
		values.put("foto", "qweqwe"/*entity.getFoto()*/);
		database.update("cliente", values, "idCliente= ?",
				new String[] { String.valueOf(entity.getIdCliente()) });
		//daoFactory.cerrar();
		
	}

	@Override
	public void delete(Clientes entity) throws Exception {
		SqliteDaoFactory daoFactory = new SqliteDaoFactory();
		SQLiteDatabase database = daoFactory.abrir();
		database.delete("cliente", "idCliente= ?" ,
				new String[] { String.valueOf(entity.getIdCliente()) });

	}

}
