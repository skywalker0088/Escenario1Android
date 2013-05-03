package com.Escenario1.view;

import java.util.List;

import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Vendedor;
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
	VendedorBo vendedorbo = new VendedorBo();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_loguin);
		
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
					if (vendedor!=null){
						abrirmenu();
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
	
	public void abrirmenu() {
		Intent i = new Intent(this, MenuActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
