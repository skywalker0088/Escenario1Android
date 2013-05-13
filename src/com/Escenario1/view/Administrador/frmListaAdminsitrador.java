package com.Escenario1.view.Administrador;

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

import com.Escenario1.bo.AdministradorBo;
import com.Escenario1.bo.ClientesBo;
import com.Escenario1.dto.Administrador;
import com.Escenario1.dto.Clientes;
import com.Escenario1.view.Cliente.AltaCliente;
import com.Escenario1.view.Cliente.ClienteAdapter;
import com.example.escenario1.R;

public class frmListaAdminsitrador extends Activity {

	AdministradorBo administradorbo;
	public static final int ACTIVITY_ALTA_Administrador= 0;
	private static final int ACTIVITY_MODIFICAR_Administrador= 1;
	private static final int ACTIVITY_ELIMINAR_Administrador= 2;
	public static final int MODO_UPDATE = 99;
	public static String opcionFiltrado="email";
	ListView lstAdministrador;
	EditText txtFiltro;
	AdministradorAdapter Adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listaadministrador);
		administradorbo = new AdministradorBo();
		lstAdministrador= (ListView) findViewById(R.id.lstAdminLytListaadministrador);
		List<Administrador> listadeadministrador= null;
		try {
			listadeadministrador= administradorbo.retrieveAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//registro la lista de clientes con los menus contextuales
		registerForContextMenu(lstAdministrador);
		Adapter = new AdministradorAdapter(this, 0, R.layout.lyt_administradoritem,listadeadministrador);
	
		lstAdministrador.setAdapter(Adapter);

		
		
	    txtFiltro = (EditText)findViewById(R.id.txtbuscarlistaadministrador);
		
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
		Intent intent = new Intent(this, AltaAdministrador.class);
		intent.putExtra("modo", ACTIVITY_ALTA_Administrador);
		startActivityForResult(intent, ACTIVITY_ALTA_Administrador);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == ACTIVITY_ALTA_Administrador && resultCode == RESULT_OK){
			Administrador administrador= (Administrador)data.getExtras().getSerializable("administrador");
			Adapter.add(administrador);
		}else if(requestCode == MODO_UPDATE && resultCode == RESULT_OK){
			Administrador administrador= (Administrador)data.getExtras().getSerializable("administrador");
			Adapter.add(administrador);
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
		Administrador administradorSeleccionado;
		administradorSeleccionado= /*clientebo.retriveById(id)*/Adapter.getItem(pos);
		
		switch (item.getItemId()) {
		case R.id.tmModifcar:
			Intent intent = new Intent(this, AltaAdministrador.class);
        	intent.putExtra("modo", MODO_UPDATE);
        	intent.putExtra("administrador", administradorSeleccionado);
    		startActivityForResult(intent, MODO_UPDATE);
			return true;
		case R.id.tmEliminar:
			try {
				administradorbo.delete(administradorSeleccionado);
				Adapter.remove(administradorSeleccionado);
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