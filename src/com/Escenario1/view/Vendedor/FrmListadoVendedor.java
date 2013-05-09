package com.Escenario1.view.Vendedor;

import java.util.List;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Vendedor;
import com.Escenario1.view.Cliente.AltaCliente;
import com.Escenario1.view.Cliente.ClienteAdapter;
import com.example.escenario1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FrmListadoVendedor extends Activity {

	VendedorBo vendedorbo;
	public static final int ACTIVITY_ALTA_Vendedor= 0;
	private static final int ACTIVITY_MODIFICAR_Vendedor= 1;
	private static final int ACTIVITY_ELIMINAR_Vendedor= 2;
	public static final int MODO_UPDATE = 99;
	private String opcionFiltrado;
	ListView lstVendedor;
	EditText txtFiltro;
	VendedorAdapter Adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listavendedor);
		vendedorbo= new VendedorBo();
		lstVendedor= (ListView) findViewById(R.id.lstVendedorLytlistaVendedor);
		List<Vendedor> listadevendedor= null;
		try {
			listadevendedor= vendedorbo.retrieveAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//registro la lista de clientes con los menus contextuales
		registerForContextMenu(lstVendedor);
		Adapter = new VendedorAdapter(this, 0, R.layout.lyt_vendedoritem,listadevendedor);
	
		lstVendedor.setAdapter(Adapter);

		
		
	    txtFiltro = (EditText)findViewById(R.id.txtbuscarlistaVendedor);
		
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
		inflater.inflate(R.menu.menu_consultavendedor, p_menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem p_item) {
		switch (p_item.getItemId()) {
        case R.id.tmAltamnuconsultaVendedor:
        	callActivityAlta();
            return true;
        case R.id.elapellidovmnuVendedor:  
        	opcionFiltrado="Apellido";
        	return true;
        case R.id.elemailmnuVendedor: 
        	opcionFiltrado="Email";
        	return true;
        default:
            return super.onOptionsItemSelected(p_item);
		}
	}
	private void callActivityAlta(){
		Intent intent = new Intent(this, FrmAltaVendedor.class);
		intent.putExtra("modo", ACTIVITY_ALTA_Vendedor);
		startActivityForResult(intent, ACTIVITY_ALTA_Vendedor);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ALTA_Vendedor&& resultCode == RESULT_OK){
			Vendedor vendedor = (Vendedor)data.getExtras().getSerializable("vendedor");
			Adapter.add(vendedor);
		}else if(requestCode == MODO_UPDATE && resultCode == RESULT_OK){
			Vendedor vendedor= (Vendedor)data.getExtras().getSerializable("vendedor");
			Adapter.add(vendedor);
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
		Vendedor vendedorSeleccionado;
		vendedorSeleccionado= /*clientebo.retriveById(id)*/Adapter.getItem(pos);
		
		
		switch (item.getItemId()) {
		case R.id.tmModifcar:
			Intent intent = new Intent(this, FrmAltaVendedor.class);
        	intent.putExtra("modo", MODO_UPDATE);
        	intent.putExtra("vendedor", vendedorSeleccionado);
    		startActivityForResult(intent, MODO_UPDATE);
			return true;
		case R.id.tmEliminar:
			try {
				vendedorbo.delete(vendedorSeleccionado);
				Adapter.remove(vendedorSeleccionado);
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