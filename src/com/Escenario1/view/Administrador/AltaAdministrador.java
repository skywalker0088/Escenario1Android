package com.Escenario1.view.Administrador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.Escenario1.bo.AdministradorBo;
import com.Escenario1.dto.Administrador;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.example.escenario1.R;

public class AltaAdministrador extends Activity{
	
	private int modo;
	private Administrador administradorActualizar;
	private AdministradorBo administradorbo;
	private EditText txtapellido;
	private EditText txtclave;
	private EditText txtemail;
	private EditText txtnombre;
	private ToggleButton txtestado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		setContentView(R.layout.lyt_altaadministrador);
		modo = (Integer)b.getSerializable("modo");
		txtapellido = (EditText)findViewById(R.id.txtapellidolylaltaadministrador);
		txtclave = (EditText)findViewById(R.id.txtclavelytaltaadministrador);
		txtemail= (EditText)findViewById(R.id.txtemaillyl_Altaadministrador);
		txtnombre = (EditText)findViewById(R.id.txtnombrelylaltaadministrador);
		txtestado=(ToggleButton)findViewById(R.id.btnestadolyl_Altaadministrador);
		
		administradorbo = new AdministradorBo();
		
		if(modo == frmListaClientes.MODO_UPDATE){
			administradorActualizar = (Administrador)b.getSerializable("administrador");
			txtapellido.setText(administradorActualizar.getApellido());
			txtclave.setText(administradorActualizar.getClave());
			txtemail.setText(administradorActualizar.getEmail());
			txtnombre.setText(administradorActualizar.getNombre());
			txtestado.setChecked(administradorActualizar.isEstado());
			
		}
	
		Button btnAceptar = (Button)findViewById(R.id.btnOklyl_Altaadministrador);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearAdministrador();
			}
		});
		
		Button btnCancel = (Button)findViewById(R.id.btnCancellyl_Altaadministrador);
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

	private void crearAdministrador() {
		
		if(modo == frmListaAdminsitrador.MODO_UPDATE){
			administradorActualizar.setApellido(txtapellido.getText().toString());
			administradorActualizar.setClave(txtclave.getText().toString());		
			administradorActualizar.setNombre(txtnombre.getText().toString());
			administradorActualizar.setEmail(txtemail.getText().toString());
			administradorActualizar.setEstado(txtestado.isChecked());
			
			try {
				administradorbo.update(administradorActualizar);
				Intent intent = new Intent(this, frmListaAdminsitrador.class);
				intent.putExtra("administrador", administradorActualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			administradorActualizar = new Administrador();
			administradorActualizar.setApellido(txtapellido.getText().toString());
			administradorActualizar.setClave(txtclave.getText().toString());		
			administradorActualizar.setNombre(txtnombre.getText().toString());
			administradorActualizar.setEmail(txtemail.getText().toString());
			administradorActualizar.setEstado(txtestado.isChecked());
			try {
				administradorbo.create(administradorActualizar);
				Intent intent = new Intent(this, frmListaClientes.class);
				intent.putExtra("administrador", administradorActualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		finish();
	}
	
}