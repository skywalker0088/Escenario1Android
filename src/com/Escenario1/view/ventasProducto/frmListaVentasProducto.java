package com.Escenario1.view.ventasProducto;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.Escenario1.bo.VentasProductosBo;
import com.Escenario1.dto.VentasProducto;
import com.example.escenario1.R;

public class frmListaVentasProducto extends Activity {

	VentasProductosBo ventasproductobo;
	public static final int ACTIVITY_ALTA_ventaproducto= 0;
	private static final int ACTIVITY_MODIFICAR_ventaprodcuto= 1;
	private static final int ACTIVITY_ELIMINAR_ventaprodcuto= 2;
	public static final int MODO_UPDATE = 99;
	private String opcionFiltrado;
	ListView lstVentasProducto;
	EditText txtFiltro;
	VentasProductoAdapter Adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listaproductoventa);
		ventasproductobo = new VentasProductosBo();
		lstVentasProducto= (ListView) findViewById(R.id.lstproductoventaLylistaproductoventa);
		List<VentasProducto> listadeventasproducto= null;
		try {
			listadeventasproducto= ventasproductobo.retrieveAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//registro la lista de clientes con los menus contextuales
		registerForContextMenu(lstVentasProducto);
		Adapter = new VentasProductoAdapter(this, 0, R.layout.lyt_productoventaitem,listadeventasproducto);
	
		lstVentasProducto.setAdapter(Adapter);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu p_menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_alta, p_menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem p_item) {
		switch (p_item.getItemId()) {
        case R.id.tmAlta:
        	callActivityAlta();
            return true;
        default:
            return super.onOptionsItemSelected(p_item);
		}
	}
	private void callActivityAlta(){
		Intent intent = new Intent(this, AltaVentasProducto.class);
		intent.putExtra("modo", ACTIVITY_ALTA_ventaproducto);
		startActivityForResult(intent, ACTIVITY_ALTA_ventaproducto);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ALTA_ventaproducto && resultCode == RESULT_OK){
			VentasProducto vp = (VentasProducto)data.getExtras().getSerializable("ventaproducto");
			Adapter.add(vp);
		}else if(requestCode == MODO_UPDATE && resultCode == RESULT_OK){
			VentasProducto vp = (VentasProducto)data.getExtras().getSerializable("ventaproducto");
			Adapter.add(vp);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		AdapterView.AdapterContextMenuInfo info =
	            (AdapterView.AdapterContextMenuInfo)menuInfo;
		//con info obtengo el dato de la lista
	       
		/*	menu.setHeaderTitle(
	        		lstClientes.getAdapter().getItem(info.position).toString());*/
		inflater.inflate(R.menu.menu_bm, menu);
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int pos = info.position;
		VentasProducto ventasproductoSeleccionado;
		ventasproductoSeleccionado = /*clientebo.retriveById(id)*/Adapter.getItem(pos);
		
		
		switch (item.getItemId()) {
		case R.id.tmModifcar:
			Intent intent = new Intent(this, AltaVentasProducto.class);
        	intent.putExtra("modo", MODO_UPDATE);
        	intent.putExtra("ventaproducto", ventasproductoSeleccionado);
    		startActivityForResult(intent, MODO_UPDATE);
			return true;
		case R.id.tmEliminar:
			try {
				ventasproductobo.delete(ventasproductoSeleccionado);
				Adapter.remove(ventasproductoSeleccionado);
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