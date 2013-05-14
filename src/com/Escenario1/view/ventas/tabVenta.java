package com.Escenario1.view.ventas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.Escenario1.bo.VentasBo;
import com.Escenario1.bo.VentasProductosBo;
import com.Escenario1.dto.Ventas;
import com.Escenario1.dto.VentasProducto;
import com.Escenario1.view.MainActivity;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.Escenario1.view.Producto.ProductoAdapter;
import com.Escenario1.view.ventasProducto.AltaVentasProducto;
import com.Escenario1.view.ventasProducto.VentasProductoAdapter;
import com.example.escenario1.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;

public class tabVenta extends Activity{
	private VentasProductoAdapter vpa;
	private VentasProductosBo ventasproductobo;
	private ListView lstVentasProducto;
	private VentasProductoAdapter Adapter;
	public static final int ACTIVITY_ALTA_ventaproducto= 0;
	private static final int ACTIVITY_MODIFICAR_ventaprodcuto= 1;
	private static final int ACTIVITY_ELIMINAR_ventaprodcuto= 2;
	public static final int MODO_UPDATE = 99;
	private int contador=0;
	private int modo;
	private Ventas ventasActualizar;
	private VentasBo ventabo;
	private EditText txtcliente;
	private EditText txtfecha;
	private TextView txttotal;
	private List<VentasProducto> listadeventasproducto= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_pestania);
		Bundle b = getIntent().getExtras();
		ventasproductobo = new VentasProductosBo();
		ventabo= new VentasBo();
		lstVentasProducto= (ListView) findViewById(R.id.lstproductoventaLylistaproductoventa);
		
		
		//registro la lista de clientes con los menus contextuales
		registerForContextMenu(lstVentasProducto);
		
		modo = (Integer)b.getSerializable("modo");
		txtcliente = (EditText)findViewById(R.id.txtclientelyaltaventa);
		txtfecha = (EditText)findViewById(R.id.txtfechalyaltaventa);
		txttotal = (TextView)findViewById(R.id.txtotallyaltaventa);
		txttotal.setText("0");
		if(modo == frmListaVentas.MODO_UPDATE){
			ventasActualizar = (Ventas)b.getSerializable("venta");
			txtcliente.setText(String.valueOf(ventasActualizar.getCliente()));
			txtfecha.setText(String.valueOf(ventasActualizar.getFecha().get(Calendar.YEAR)+'/'+ventasActualizar.getFecha().get(Calendar.MONTH)+'/'+ventasActualizar.getFecha().get(Calendar.DATE)));
			txttotal.setText(String.valueOf(ventasActualizar.getTotal()));
			try {
				listadeventasproducto= ventasproductobo.restriveallforventa(ventasActualizar.getCodVentas());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Adapter = new VentasProductoAdapter(this, 0, R.layout.lyt_productoventaitem,listadeventasproducto);
		}else{
			Adapter = new VentasProductoAdapter(this, 0, R.layout.lyt_productoventaitem,new ArrayList<VentasProducto>());
		}
	
		lstVentasProducto.setAdapter(Adapter);
		Resources res = getResources();
		TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
		tabs.setup();
		 
		TabHost.TabSpec spec=tabs.newTabSpec("Venta");
		spec.setContent(R.id.venta);
		spec.setIndicator("Datos Venta",
		    res.getDrawable(android.R.drawable.ic_btn_speak_now));
		tabs.addTab(spec);
		 
		spec=tabs.newTabSpec("DetallesVenta");
		spec.setContent(R.id.detalleventa);
		spec.setIndicator("Detalle Venta",
		    res.getDrawable(android.R.drawable.ic_dialog_map));
		
		tabs.addTab(spec);
		 
		tabs.setCurrentTab(0);
		
		
		Button btnAceptar = (Button)findViewById(R.id.btnoklyl_Altaventa);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearVenta();
			}
		});
		
		Button btnCancel = (Button)findViewById(R.id.btnCancellyl_Altaventa);
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancel();
			}
		});
	}


	private void cancel() {
		setResult(RESULT_CANCELED);
		finish();
		
	}

	private void crearVenta() {
		
		if(modo == frmListaVentas.MODO_UPDATE){
			ventasActualizar.setCliente(Integer.valueOf(txtcliente.getText().toString()));
			String startTime = txtfecha.getText().toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date1 = null;
			try {
				date1 = dateFormat.parse(startTime);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();  
			cal.setTime(date1);  
			ventasActualizar.setFecha(cal);
			ventasActualizar.setTotal(Float.valueOf(txttotal.getText().toString()));
			if(MainActivity.Usuario==0){
				ventasActualizar.setVendedor(MainActivity.idUsuario);
				ventasActualizar.setAdministrador(0);
			}else if(MainActivity.Usuario==1){
				ventasActualizar.setVendedor(0);
				ventasActualizar.setAdministrador(MainActivity.idUsuario);
			} 
			
			
			try {
				ventabo.update(ventasActualizar);
				Intent intent = new Intent(this, frmListaVentas.class);
				intent.putExtra("venta", ventasActualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			ventasActualizar = new Ventas();
			ventasActualizar.setCliente(Integer.valueOf(txtcliente.getText().toString()));
			String startTime = txtfecha.getText().toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date1 = null;
			try {
				date1 = dateFormat.parse(startTime);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();  
			cal.setTime(date1); 
			ventasActualizar.setFecha(cal);
			ventasActualizar.setTotal(Float.valueOf(txttotal.getText().toString()));
			if(MainActivity.Usuario==0){
				ventasActualizar.setVendedor(MainActivity.idUsuario);
				ventasActualizar.setAdministrador(0);
			}else if(MainActivity.Usuario==1){
				ventasActualizar.setVendedor(0);
				ventasActualizar.setAdministrador(MainActivity.idUsuario);
			} 
			
			try {
				ventabo.create(ventasActualizar);
				ventasActualizar=ventabo.retriveById(Long.valueOf(String.valueOf(ventabo.ultimovalor(0))));
				for (int i=0;i<Adapter.getCount();i++){
					Adapter.getItem(i).setVenta(ventasActualizar.getCodVentas());
					Adapter.getItem(i).setIdProductoVenta(0);
					ventasproductobo.create(Adapter.getItem(i));
					
				}
				Intent intent = new Intent(this, frmListaVentas.class);
				intent.putExtra("venta", ventasActualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		finish();
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
			contador++;
			vp.setIdProductoVenta(contador);
			Adapter.add(vp);
			txttotal.setText(String.valueOf(Float.valueOf(txttotal.getText().toString())+vp.getSubtotal()));
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
			//	ventasproductobo.delete(ventasproductoSeleccionado);
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
