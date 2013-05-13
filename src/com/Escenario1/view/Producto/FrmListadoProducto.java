package com.Escenario1.view.Producto;

import java.util.List;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.bo.ProductosBo;
import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Productos;
import com.Escenario1.dto.Vendedor;
import com.Escenario1.view.MainActivity;
import com.Escenario1.view.Cliente.AltaCliente;
import com.Escenario1.view.Cliente.ClienteAdapter;
import com.Escenario1.view.Vendedor.FrmAltaVendedor;
import com.Escenario1.view.Vendedor.VendedorAdapter;
import com.Escenario1.view.ventasProducto.AltaVentasProducto;
import com.example.escenario1.R;

import android.app.Activity;
import android.app.Dialog;
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

public class FrmListadoProducto extends Activity{

	ProductosBo productobo;
	public static final int ACTIVITY_ALTA_Producto= 0;
	private static final int ACTIVITY_MODIFICAR_Producto= 1;
	private static final int ACTIVITY_ELIMINAR_Producto= 2;
	public static final int MODO_UPDATE = 99;
	public static String opcionFiltrado="nombre";
	ListView lstProducto;
	EditText txtFiltro;
	ProductoAdapter Adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listaproducto);
		productobo = new ProductosBo();
		lstProducto= (ListView) findViewById(R.id.lstProductoLytlistaproducto);
		List<Productos> listadeproductos= null;
		try {
			listadeproductos= productobo.retrieveAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//registro la lista de clientes con los menus contextuales
		registerForContextMenu(lstProducto);
		Adapter = new ProductoAdapter(this, 0, R.layout.lyt_productoitem,listadeproductos);
	
		lstProducto.setAdapter(Adapter);

		
		
	    txtFiltro = (EditText)findViewById(R.id.txtbuscarlistaproducto);
		
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
		if(MainActivity.Usuario==0){
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.menu_consultaproductovendedor, p_menu);
			return true;
		}else if(MainActivity.Usuario==1){
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.menu_consultaproductoadministrador, p_menu);
			return true;
		}
		return super.onCreateOptionsMenu(p_menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem p_item) {
		switch (p_item.getItemId()) {
        case R.id.tmAltamnuconsultaproductoadmin:
        	callActivityAlta();
            return true;
        case R.id.elcnombremnuconsultaproductoVendedor:  
        	opcionFiltrado="Nombre";
        	return true;
        case R.id.elccategoriamnuconsultaproductoVendedor: 
        	opcionFiltrado="Categoria";
        	return true;
        default:
            return super.onOptionsItemSelected(p_item);
		}
	}
	private void callActivityAlta(){
		Intent intent = new Intent(this, FrmAltaProducto.class);
		intent.putExtra("modo", ACTIVITY_ALTA_Producto);
		startActivityForResult(intent, ACTIVITY_ALTA_Producto);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ALTA_Producto && resultCode == RESULT_OK){
			Productos producto= (Productos)data.getExtras().getSerializable("producto");
			Adapter.add(producto);
		}else if(requestCode == MODO_UPDATE && resultCode == RESULT_OK){
			Productos producto= (Productos)data.getExtras().getSerializable("producto");
			Adapter.add(producto);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if(MainActivity.Usuario==1){
			super.onCreateContextMenu(menu, v, menuInfo);
			MenuInflater inflater = getMenuInflater();
			AdapterView.AdapterContextMenuInfo info =
		            (AdapterView.AdapterContextMenuInfo)menuInfo;
			//con info obtengo el dato de la lista
		       
			/*	menu.setHeaderTitle(
		        		lstClientes.getAdapter().getItem(info.position).toString());*/
			inflater.inflate(R.menu.menu_bm, menu);
		}else{
		
		super.onCreateContextMenu(menu, v, menuInfo);
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int pos = info.position;
		Productos productoSeleccionado;
		productoSeleccionado = /*clientebo.retriveById(id)*/Adapter.getItem(pos);
		
		
		
		switch (item.getItemId()) {
		case R.id.tmModifcar:
			Intent intent = new Intent(this, FrmAltaProducto.class);
        	intent.putExtra("modo", MODO_UPDATE);
        	intent.putExtra("producto", productoSeleccionado);
    		startActivityForResult(intent, MODO_UPDATE);
			return true;
		case R.id.tmEliminar:
			try {
				productobo.delete(productoSeleccionado);
				Adapter.remove(productoSeleccionado);
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
