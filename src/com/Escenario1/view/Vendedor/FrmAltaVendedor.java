package com.Escenario1.view.Vendedor;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Vendedor;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.example.escenario1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class FrmAltaVendedor extends Activity{
	
	private int modo;
	private Vendedor vendedorActualizar;
	private EditText txtapellido;
	private EditText txtclave;
	private EditText txtemail;
	private ImageView txtfoto;
	private EditText txtnombre;
	VendedorBo vendedorbo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		setContentView(R.layout.lyt_altavendedor);
		modo = (Integer)b.getSerializable("modo");
		txtapellido = (EditText)findViewById(R.id.txtapellidolylaltaVendedor);
		txtclave = (EditText)findViewById(R.id.txtclavelyl_AltaVendedor);
		txtemail = (EditText)findViewById(R.id.txtemaillyl_AltaVendedor);
		txtfoto = (ImageView)findViewById(R.id.imgfotolylaltavendedor);
		txtnombre= (EditText)findViewById(R.id.txtnombrelylaltaVendedor);
		
		vendedorbo = new VendedorBo();
		
		if(modo == FrmListadoVendedor.MODO_UPDATE){
			vendedorActualizar= (Vendedor)b.getSerializable("vendedor");
			txtapellido.setText(vendedorActualizar.getApellido());
			txtclave.setText(vendedorActualizar.getClave());
		//	txtfoto.set(clienteActualizar.getFoto());
			txtemail.setText(vendedorActualizar.getEmail());
			txtnombre.setText(vendedorActualizar.getNombre());
		}
	
		Button btnAceptar = (Button)findViewById(R.id.btnOklyl_AltaVendedor);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearVendedor();
			}
		});
		
		Button btnCancel = (Button)findViewById(R.id.btnCancellyl_AltaVendedor);
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

	private void crearVendedor() {
		
		if(modo == FrmListadoVendedor.MODO_UPDATE){
			vendedorActualizar.setApellido(txtapellido.getText().toString());
			vendedorActualizar.setClave(txtclave.getText().toString());
			//clienteActualizar.setFoto(txtfoto.getText().toString());
			vendedorActualizar.setEmail(txtemail.getText().toString());
			vendedorActualizar.setNombre(txtnombre.getText().toString());
			
			
			try {
				vendedorbo.update(vendedorActualizar);
				Intent intent = new Intent(this, FrmListadoVendedor.class);
				intent.putExtra("vendedor", vendedorActualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			vendedorActualizar = new Vendedor();
			vendedorActualizar.setApellido(txtapellido.getText().toString());
			vendedorActualizar.setClave(txtclave.getText().toString());
			//clienteActualizar.setFoto(txtfoto.getText().toString());
			vendedorActualizar.setEmail(txtemail.getText().toString());
			vendedorActualizar.setNombre(txtnombre.getText().toString());
			try {
				vendedorbo.create(vendedorActualizar);
				Intent intent = new Intent(this, FrmListadoVendedor.class);
				intent.putExtra("vendedor", vendedorActualizar);
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