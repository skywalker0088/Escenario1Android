package com.Escenario1.view;

import com.Escenario1.view.Producto.FrmListadoProducto;
import com.example.escenario1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity{
	Button btnventas;
	Button btncliente;
	Button btnproducto;
 @Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lyt_menu);
	btnventas= (Button)findViewById(R.id.btnventaslyl_Menu);
	btncliente= (Button)findViewById(R.id.btnregistrarusuariolyl_menu);
	btnproducto= (Button)findViewById(R.id.btnproductolyl_menu);
	
	btnventas.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try {
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	});
	btncliente.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try {
				
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
}
 
 
 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
}
