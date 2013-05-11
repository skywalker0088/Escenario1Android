package com.Escenario1.view.Cliente;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.bo.ProductosBo;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Productos;
import com.Escenario1.view.Producto.ProductoAdapter;
import com.example.escenario1.R;

public class frmListaClientes extends Activity {

	ClientesBo clientebo;
	public static final int ACTIVITY_ALTA_Cliente = 0;
	private static final int ACTIVITY_MODIFICAR_Cliente = 1;
	private static final int ACTIVITY_ELIMINAR_Cliente = 2;
	public static final int MODO_UPDATE = 99;
	private String opcionFiltrado;
	ListView lstClientes;
	EditText txtFiltro;
	ClienteAdapter Adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listacliente);
		clientebo = new ClientesBo();
		lstClientes = (ListView) findViewById(R.id.lstClienteLytListacliente);
		List<Clientes> listadeclientes = null;
		try {
			listadeclientes = clientebo.retrieveAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// registro la lista de clientes con los menus contextuales
		registerForContextMenu(lstClientes);
		Adapter = new ClienteAdapter(this, 0, R.layout.lyt_clienteitem,
				listadeclientes);

		lstClientes.setAdapter(Adapter);

		txtFiltro = (EditText) findViewById(R.id.txtbuscarlistacliente);

		txtFiltro.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2,
					int arg3) {
				Adapter.getFilter().filter(text.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu p_menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_consultaclientevendedor, p_menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem p_item) {
		switch (p_item.getItemId()) {
		case R.id.tmAltamnuconsultaclienteVendedor:
			callActivityAlta();
			return true;
		case R.id.elcpellidomnuconsultaclienteVendedor:
			opcionFiltrado = "Apellido";
			return true;
		case R.id.elcnombremnuconsultaclienteVendedor:
			opcionFiltrado = "Nombre";
			return true;
		default:
			return super.onOptionsItemSelected(p_item);
		}
	}

	private void callActivityAlta() {
		Intent intent = new Intent(this, AltaCliente.class);
		intent.putExtra("modo", ACTIVITY_ALTA_Cliente);
		startActivityForResult(intent, ACTIVITY_ALTA_Cliente);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ACTIVITY_ALTA_Cliente && resultCode == RESULT_OK) {
			Clientes cliente = (Clientes) data.getExtras().getSerializable(
					"cliente");

			Adapter.add(cliente);

		} else if (requestCode == MODO_UPDATE && resultCode == RESULT_OK) {
			Clientes cliente = (Clientes) data.getExtras().getSerializable(
					"cliente");

			Adapter.add(cliente);

		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		// con info obtengo el dato de la lista

		/*
		 * menu.setHeaderTitle(
		 * lstClientes.getAdapter().getItem(info.position).toString());
		 */
		inflater.inflate(R.menu.menu_clienteabmvendedor, menu);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int pos = info.position;
		Clientes clienteSeleccionado;
		clienteSeleccionado = /* clientebo.retriveById(id) */Adapter.getItem(pos);

		System.out.println(clienteSeleccionado.getIdCliente());
		switch (item.getItemId()) {
		case R.id.tmModifcarmnuclienteabmven:
			Intent intent = new Intent(this, AltaCliente.class);
			intent.putExtra("modo", MODO_UPDATE);
			intent.putExtra("cliente", clienteSeleccionado);
			startActivityForResult(intent, MODO_UPDATE);
			return true;
		case R.id.tmEliminarmnuclienteabmven:
			try {
				clientebo.delete(clienteSeleccionado);
				Adapter.remove(clienteSeleccionado);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		default:
			return super.onContextItemSelected(item);
		}

	}

}