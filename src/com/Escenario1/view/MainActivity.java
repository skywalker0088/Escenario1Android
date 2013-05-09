package com.Escenario1.view;

import java.util.List;

import com.Escenario1.bo.AdministradorBo;
import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Administrador;
import com.Escenario1.dto.Vendedor;
import com.Escenario1.view.Administrador.MenuAdministrador;
import com.Escenario1.view.Vendedor.MenuVendedor;
import com.example.escenario1.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText txtemail;
	EditText txtclave;
	Button btnok;
	Button btncancel;
    Vendedor vendedor;
	Administrador administrador;
	VendedorBo vendedorbo;
	AdministradorBo administradorbo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_loguin);
		vendedorbo = new VendedorBo();
		administradorbo = new AdministradorBo();
		txtemail = (EditText) findViewById(R.id.txtemaillyloguin);
		txtclave = (EditText) findViewById(R.id.txtclavelyloguin);
		btnok= (Button)findViewById(R.id.btnOklyl_loguin);
		btncancel= (Button)findViewById(R.id.btnCancellyl_loguin);
		
		btnok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					vendedor = vendedorbo.login(txtemail.getText().toString(), txtclave
							.getText().toString());
					administrador= administradorbo.login(txtemail.getText().toString(), txtclave
							.getText().toString());
					if (vendedor!=null){
						abrirmenu("vendedor");
					}else if(administrador!=null){
						abrirmenu("administrador");
					}else{
						Toast.makeText(getApplicationContext(), R.string.lblerrorloguin, Toast.LENGTH_SHORT).show();
					}
					
				
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
		btncancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					finish();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});

	}
	
	public void abrirmenu(String _usu) {
		Intent i = null;
		if(_usu.equalsIgnoreCase("vendedor")){
		i = new Intent(this, MenuVendedor.class);
		}else if(_usu.equalsIgnoreCase("administrador")){
			i = new Intent(this, MenuAdministrador.class);
		}
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
