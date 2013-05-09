package com.Escenario1.view.Categoria;

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

import com.Escenario1.bo.CategoriaBo;
import com.Escenario1.bo.ClientesBo;
import com.Escenario1.dto.Categoria;
import com.Escenario1.dto.Clientes;
import com.Escenario1.view.Cliente.AltaCliente;
import com.Escenario1.view.Cliente.ClienteAdapter;
import com.example.escenario1.R;

public class frmListaCategoria extends Activity{

	CategoriaBo categoriabo;
	public static final int ACTIVITY_ALTA_Categoria= 0;
	private static final int ACTIVITY_MODIFICAR_Categoria= 1;
	private static final int ACTIVITY_ELIMINAR_Categoria= 2;
	public static final int MODO_UPDATE = 99;
	private String opcionFiltrado;
	ListView lstCategoria;
	EditText txtFiltro;
	CategoriaAdapters Adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listacategoria);
		categoriabo = new CategoriaBo();
		lstCategoria= (ListView) findViewById(R.id.lstCategoriaLytlistacategoria);
		List<Categoria> listadecategoria= null;
		try {
			listadecategoria= categoriabo.retrieveAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//registro la lista de clientes con los menus contextuales
		registerForContextMenu(lstCategoria);
		Adapter = new CategoriaAdapters(this, 0, R.layout.lyt_categoriaitem,listadecategoria);
	
		lstCategoria.setAdapter(Adapter);

		
		
	    txtFiltro = (EditText)findViewById(R.id.txtbuscarlistacategoria);
		
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
		Intent intent = new Intent(this, AltaCategoria.class);
		intent.putExtra("modo", ACTIVITY_ALTA_Categoria);
		startActivityForResult(intent, ACTIVITY_ALTA_Categoria);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ALTA_Categoria && resultCode == RESULT_OK){
			Categoria categoria= (Categoria)data.getExtras().getSerializable("categoria");
			Adapter.add(categoria);
		}else if(requestCode == MODO_UPDATE && resultCode == RESULT_OK){
			Categoria categoria= (Categoria)data.getExtras().getSerializable("categoria");
			Adapter.add(categoria);
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
		Categoria categoriaSeleccionado;
		categoriaSeleccionado= /*clientebo.retriveById(id)*/Adapter.getItem(pos);
		
		switch (item.getItemId()) {
		case R.id.tmModifcar:
			Intent intent = new Intent(this, AltaCategoria.class);
        	intent.putExtra("modo", MODO_UPDATE);
        	intent.putExtra("categoria", categoriaSeleccionado);
    		startActivityForResult(intent, MODO_UPDATE);
			return true;
		case R.id.tmEliminar:
			try {
				categoriabo.delete(categoriaSeleccionado);
				Adapter.remove(categoriaSeleccionado);
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