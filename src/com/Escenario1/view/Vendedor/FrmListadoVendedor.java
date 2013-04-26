package com.Escenario1.view.Vendedor;

import java.util.List;

import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Vendedor;
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
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FrmListadoVendedor extends Activity {
	private VendedorBo mvendedorbo;
	private VendedorAdapter mAdapter;
	private Vendedor mVendedorEliminado;
	private static final int ACTIVITY_ALTA_VENDEDOR= 0;
	private static final int ACTIVITY_MODIFICAR_VENDEDOR = 1;
	public static final int MODO_UPDATE = 99;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_listavendedor);

		final ListView lstVendedor = (ListView)findViewById(R.id.lstVendedorLytlistaVendedor);
		lstVendedor.setTextFilterEnabled(true);
		registerForContextMenu(lstVendedor);

		this.setVendedorBo(new VendedorBo()) ;
		
		try {
			this.setmAdapter(new VendedorAdapter(this,R.layout.lyt_vendedoritem,this.getVendedorBo().retrieveAll()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lstVendedor.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent,android.view.View view, int posicion, long idItem) {

				Toast.makeText(getApplicationContext(), "" + posicion, Toast.LENGTH_LONG).show();
			}

		});

		lstVendedor.setAdapter(this.getmAdapter());

		EditText txtFiltro = (EditText)findViewById(R.id.txtbuscarlistaVendedor);

		txtFiltro.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
				mAdapter.getFilter().filter(text.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
		
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu p_menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_vendedor, p_menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem p_item) {
		switch (p_item.getItemId()) {
        case R.id.tmAltaVendorMenuvendedor:
        	this.callActivityAlta();
            return true;
        case R.id.tmFiltrarPorMenuvendedor:
        	Toast.makeText(getApplicationContext(), "Filtrar por", Toast.LENGTH_LONG).show();
            return true;       
        default:
            return super.onOptionsItemSelected(p_item);
		}
	}

	private void callActivityAlta(){

	//	Vendedor VendedorNuevo = new Cliente(0,"Nombre ", "Domicilio","379",
	//				"@gmail.com",new Localidad("Corrientes","Corrientes"),"Razón Social");
		
		Intent intent = new Intent(this, FrmAltaVendedor.class);
		intent.putExtra("modo",ACTIVITY_ALTA_VENDEDOR);
		//intent.putExtra("vendedor",clienteNuevo);
		startActivityForResult(intent, ACTIVITY_ALTA_VENDEDOR);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK){
			Vendedor vendedorNuevo = (Vendedor)data.getExtras().getSerializable("vendedor");

			if(requestCode == ACTIVITY_ALTA_VENDEDOR){
				this.getmAdapter().add(vendedorNuevo);
			}else if(requestCode == ACTIVITY_MODIFICAR_VENDEDOR){
				this.getmAdapter().remove(this.getmVendedorEliminado());
				this.getmAdapter().add(vendedorNuevo);
			}		
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		this.setmVendedorEliminado(this.getmAdapter().getItem(info.position));
//		Cliente clienteSeleccionado = this.getmAdapter().getItem(info.position);
	   switch (item.getItemId()) {
	        case R.id.tmModificarVendedorMenugestionVendedor:
//	        	this.setmClienteEliminado(clienteSeleccionado);			// agregado hoy 06-04-13
	        	Intent intent = new Intent(this, FrmAltaVendedor.class);
	        	intent.putExtra("modo", ACTIVITY_MODIFICAR_VENDEDOR);
	        	intent.putExtra("cliente", this.getmVendedorEliminado());
	    		startActivityForResult(intent, ACTIVITY_MODIFICAR_VENDEDOR);
	            return true;
	        case R.id.tmEliminarVendedorMenugestionVendedor:
	        	this.getmAdapter().remove(this.getmVendedorEliminado());
	            return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}

	public void onCreateContextMenu(ContextMenu menu, android.view.View v,ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		AdapterView.AdapterContextMenuInfo info =
	            (AdapterView.AdapterContextMenuInfo)menuInfo;

		menu.setHeaderTitle(this.getmAdapter().getItem(info.position).toString());
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_gestion_vendedor, menu);
	}

	private void setVendedorBo(VendedorBo p_vendedorBo){
		this.mvendedorbo = p_vendedorBo;
	}

	public VendedorBo getVendedorBo(){
		return this.mvendedorbo;
	}
	
	public VendedorAdapter getmAdapter() {
		return this.mAdapter;
	}

	public void setmAdapter(VendedorAdapter p_mAdapter) {
		this.mAdapter = p_mAdapter;
	}

	public static int getActivityAltaVendedor() {
		return ACTIVITY_ALTA_VENDEDOR;
	}

	public static int getActivityModificarVendedor() {
		return ACTIVITY_MODIFICAR_VENDEDOR;
	}

	public static int getModoUpDate() {
		return MODO_UPDATE;
	}

	public Vendedor getmVendedorEliminado() {
		return mVendedorEliminado;
	}

	public void setmVendedorEliminado(Vendedor p_VendedorEliminado) {
		this.mVendedorEliminado = p_VendedorEliminado;
	}

}
