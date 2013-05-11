package com.Escenario1.view.Administrador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Escenario1.view.Categoria.frmListaCategoria;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.Escenario1.view.Producto.FrmListadoProducto;
import com.Escenario1.view.Vendedor.FrmListadoVendedor;
import com.Escenario1.view.ventas.frmListaVentas;
import com.Escenario1.view.ventas.tabVenta;
import com.example.escenario1.R;

public class MenuAdministrador extends Activity{
	Button btnventas;
	Button btncliente;
	Button btnproducto;
	Button btnadministrador;
	Button btncategoria;
	Button btnvendedor;
 @Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lyt_menuadmin);
	btnventas= (Button)findViewById(R.id.btnventaslyl_MenuAdmi);
	btncliente= (Button)findViewById(R.id.btnregistrarusuariolyl_menuAdmi);
	btnproducto= (Button)findViewById(R.id.btnproductolyl_menuAdmi);
	btnadministrador= (Button)findViewById(R.id.btnadministradorlyl_MenuAdmi);
	btncategoria= (Button)findViewById(R.id.btncategorialyl_MenuAdmi);
	btnvendedor= (Button)findViewById(R.id.btnvendedorlyl_MenuAdmi);
	
	
	
	
	btnventas.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try {
				Intent i = new Intent(getApplicationContext(), frmListaVentas.class);
				startActivity(i);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	});
	btncliente.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try {
				Intent i = new Intent(getApplicationContext(), frmListaClientes.class);
				startActivity(i);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	});
	btnproducto.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try {
				Intent i = new Intent(getApplicationContext(), FrmListadoProducto.class);
				startActivity(i);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	});
	btncategoria.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try {
				Intent i = new Intent(getApplicationContext(), frmListaCategoria.class);
				startActivity(i);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	});
	btnadministrador.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try {
				Intent i = new Intent(getApplicationContext(), frmListaAdminsitrador.class);
				startActivity(i);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	});
	btnvendedor.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try {
				Intent i = new Intent(getApplicationContext(), FrmListadoVendedor.class);
				startActivity(i);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	});
}
 
 
 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
}
