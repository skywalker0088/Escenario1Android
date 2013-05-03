package com.Escenario1.view.Producto;

import java.util.List;

import com.Escenario1.bo.ProductosBo;
import com.Escenario1.dto.Productos;
import com.example.escenario1.R;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class FrmListadoProducto extends Activity {

	ProductosBo productobo;
	private static final int ACTIVITY_ALTA_PRODUCTO = 0;
	private static final int ACTIVITY_MODIFICAR_Producto = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listaproducto);
		productobo = new ProductosBo();
		final ListView lstProductos = (ListView) findViewById(R.id.lstProductoLytlistaproducto);
		List<Productos> listadeproducto = null;
		try {
			listadeproducto = productobo.retrieveAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		ArrayAdapter<Productos> Adapter = new ArrayAdapter<Productos>(this,
				android.R.layout.simple_list_item_1, listadeproducto);
		lstProductos.setAdapter(Adapter);

		lstProductos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), "" + arg2,
						Toast.LENGTH_LONG).show();

			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
}
/*
 * 
 * private ProductosBo mProductoBo; private ProductoAdapter mAdapter; private
 * static final int ACTIVITY_ALTA_PRODUCTO= 0; private static final int
 * ACTIVITY_MODIFICAR_Producto = 1; public static final int MODO_UPDATE = 99;
 * 
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState); setContentView(R.layout.listadoproducto);
 * 
 * final ListView lstProductos = (ListView)findViewById(R.id.lstProducto);
 * lstProductos.setTextFilterEnabled(true);
 * registerForContextMenu(lstProductos);
 * 
 * mProductoBo = new ProductosBo(); List<Productos> productos=
 * mProductoBo.retriveProducto(); mAdapter = new ProductoAdapter(this,
 * R.layout.itemproducto, productos);
 * 
 * lstProductos.setOnItemClickListener(new OnItemClickListener() {
 * 
 * @Override public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
 * long arg3) { Toast.makeText(getApplicationContext(), "" + arg2,
 * Toast.LENGTH_LONG).show();
 * 
 * } }); lstProductos.setAdapter(mAdapter);
 * 
 * EditText txtFiltro = (EditText)findViewById(R.id.txtFiltro);
 * 
 * txtFiltro.addTextChangedListener(new TextWatcher() {
 * 
 * @Override public void onTextChanged(CharSequence text, int arg1, int arg2,
 * int arg3) { mAdapter.getFilter().filter(text.toString()); }
 * 
 * @Override public void beforeTextChanged(CharSequence arg0, int arg1, int
 * arg2, int arg3) { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void afterTextChanged(Editable arg0) { // TODO
 * Auto-generated method stub
 * 
 * } });
 * 
 * 
 * }
 * 
 * @Override public boolean onCreateOptionsMenu(Menu menu) { MenuInflater
 * inflater = getMenuInflater(); inflater.inflate(R.menu., menu); return true; }
 * 
 * 
 * @Override public boolean onOptionsItemSelected(MenuItem item) { switch
 * (item.getItemId()) { case R.id.: callActivityAlta(); return true; case
 * R.id.tmFiltrarPor: Toast.makeText(getApplicationContext(), "Filtrar por",
 * Toast.LENGTH_LONG).show(); return true; case R.id.tmOrdenarPor:
 * Toast.makeText(getApplicationContext(), "Ordenar por",
 * Toast.LENGTH_LONG).show(); return true; default: return
 * super.onOptionsItemSelected(item); } }
 * 
 * private void callActivityAlta(){ Intent intent = new Intent(this,
 * FrmAltaProducto.class); startActivityForResult(intent,
 * ACTIVITY_ALTA_PRODUCTO); }
 * 
 * @Override protected void onActivityResult(int requestCode, int resultCode,
 * Intent data) { super.onActivityResult(requestCode, resultCode, data);
 * if(requestCode == ACTIVITY_ALTA_PRODUCTO && resultCode == RESULT_OK){
 * Productos productoNuevo =
 * (Productos)data.getExtras().getSerializable("producto");
 * mAdapter.add(clienteNuevo); }else if(requestCode ==
 * ACTIVITY_MODIFICAR_Producto && resultCode == RESULT_OK){ //actualizar } }
 * 
 * @Override public void onCreateContextMenu(ContextMenu menu, View v,
 * ContextMenuInfo menuInfo) { super.onCreateContextMenu(menu, v, menuInfo);
 * menu.setHeaderTitle("Opciones"); MenuInflater inflater = getMenuInflater();
 * inflater.inflate(R.menu.mn_gestion_cliente, menu); }
 * 
 * @Override public boolean onContextItemSelected(MenuItem item) {
 * AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
 * int pos = info.position; Cliente clienteSeleccionado = mAdapter.getItem(pos);
 * switch (item.getItemId()) { case R.id.tmModificarCliente: Intent intent = new
 * Intent(this, FrmAltaCliente.class); intent.putExtra("modo", MODO_UPDATE);
 * intent.putExtra("cliente", clienteSeleccionado);
 * startActivityForResult(intent, ACTIVITY_ALTA_CLIENTE); return true; case
 * R.id.tmEliminarCliente: mAdapter.remove(clienteSeleccionado); return true;
 * case R.id.tmRegistrarPedido: Toast.makeText(getApplicationContext(),
 * "Registrar pedido " + clienteSeleccionado, Toast.LENGTH_LONG).show(); return
 * true; default: return super.onContextItemSelected(item); } }
 */

