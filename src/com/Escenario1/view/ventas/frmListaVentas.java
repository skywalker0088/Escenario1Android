package com.Escenario1.view.ventas;

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

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.bo.VentasBo;
import com.Escenario1.bo.VentasProductosBo;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Ventas;
import com.Escenario1.dto.VentasProducto;
import com.Escenario1.view.Cliente.AltaCliente;
import com.Escenario1.view.Cliente.ClienteAdapter;
import com.example.escenario1.R;

public class frmListaVentas extends Activity {

	VentasBo ventasbo;
	VentasProductosBo ventasproductobo;
	public static final int ACTIVITY_ALTA_Ventas= 0;
	private static final int ACTIVITY_MODIFICAR_Ventas= 1;
	private static final int ACTIVITY_ELIMINAR_Ventas= 2;
	public static final int MODO_UPDATE = 99;
	public static String opcionFiltrado="fecha";
	ListView lstVenta;
	EditText txtFiltro;
	ventaAdapter Adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listaventa);
		ventasbo = new VentasBo();
		ventasproductobo= new VentasProductosBo();
		lstVenta= (ListView) findViewById(R.id.lstVentaLytlistaVenta);
		List<Ventas> listadeventas= null;
		try {
			listadeventas= ventasbo.retrieveAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//registro la lista de clientes con los menus contextuales
		registerForContextMenu(lstVenta);
		Adapter = new ventaAdapter(this, 0, R.layout.lyt_ventaitem,listadeventas);
	
		lstVenta.setAdapter(Adapter);

		
		
	    txtFiltro = (EditText)findViewById(R.id.txtbuscarlistaVenta);
		
		txtFiltro.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
				Adapter.getFilter().filter(text.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
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
		inflater.inflate(R.menu.menu_consultaventa, p_menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem p_item) {
		switch (p_item.getItemId()) {
        case R.id.tmAltamnuconsultaVenta:
        	callActivityAlta();
            return true;
        case R.id.elfechavmnuVenta:  
        	opcionFiltrado="Fecha";
        	return true;
        case R.id.elclientemnuVenta: 
        	opcionFiltrado="Cliente";
        	return true;
        default:
            return super.onOptionsItemSelected(p_item);
		}
	}
	private void callActivityAlta(){
		Intent intent = new Intent(this, tabVenta.class);
		intent.putExtra("modo", ACTIVITY_ALTA_Ventas);
		startActivityForResult(intent, ACTIVITY_ALTA_Ventas);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ALTA_Ventas&& resultCode == RESULT_OK){
			Ventas venta= (Ventas)data.getExtras().getSerializable("venta");
			Adapter.add(venta);
		}else if(requestCode == MODO_UPDATE && resultCode == RESULT_OK){
			Ventas venta= (Ventas)data.getExtras().getSerializable("venta");
			Adapter.add(venta);
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
		inflater.inflate(R.menu.menu_emergenteventa, menu);
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int pos = info.position;
		Ventas ventaSeleccionado;
		ventaSeleccionado= /*clientebo.retriveById(id)*/Adapter.getItem(pos);
		
		
		switch (item.getItemId()) {
		case R.id.tmModifcarmnuemergenteventa:
			Intent intent = new Intent(this, tabVenta.class);
        	intent.putExtra("modo", MODO_UPDATE);
        	intent.putExtra("venta", ventaSeleccionado);
    		startActivityForResult(intent, MODO_UPDATE);
			return true;
		case R.id.tmEliminarmnuemergenteventa:
			try {
				List<VentasProducto>listaventapro= ventasproductobo.retrieveAll();
				for(int i=0;i<listaventapro.size();i++){
					if(listaventapro.get(i).getVenta()==ventaSeleccionado.getCodVentas()){
						ventasproductobo.delete(listaventapro.get(i));
					}
				}
				ventasbo.delete(ventaSeleccionado);
				Adapter.remove(ventaSeleccionado);
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