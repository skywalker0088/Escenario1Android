package com.Escenario1.view.Cliente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.dto.Clientes;
import com.example.escenario1.R;

public class AltaCliente extends Activity{
	
	private int modo;
	private Clientes clienteActualizar;
	private ClientesBo clientebo;
	private EditText txtapellido;
	private EditText txtdireccion;
	private ImageView txtfoto;
	private EditText txtlocalidad;
	private EditText txtnombre;
	private EditText txtprovincia;
	private EditText txtrazonsocial;
	private EditText txttelefono;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		setContentView(R.layout.lyt_altacliente);
		modo = (Integer)b.getSerializable("modo");
		txtapellido = (EditText)findViewById(R.id.txtapellidolylaltacliente);
		txtdireccion = (EditText)findViewById(R.id.txtdireccionlyl_Altacliente);
		txtfoto = (ImageView)findViewById(R.id.imgfotolylaltacliente);
		txtlocalidad= (EditText)findViewById(R.id.txtlocalidadlyl_Altacliente);
		txtnombre = (EditText)findViewById(R.id.txtnombrelylaltacliente);
		txtprovincia= (EditText)findViewById(R.id.txtprovincialyl_Altacliente);
		txtrazonsocial= (EditText)findViewById(R.id.txtRazonSociallyl_Altacliente);
		txttelefono= (EditText)findViewById(R.id.txttelefonolyl_Altacliente);
		clientebo = new ClientesBo();
		
		if(modo == frmListaClientes.MODO_UPDATE){
			clienteActualizar = (Clientes)b.getSerializable("cliente");
			txtapellido.setText(clienteActualizar.getApellido());
			txtdireccion.setText(clienteActualizar.getDireccion());
		//	txtfoto.set(clienteActualizar.getFoto());
			txtlocalidad.setText(clienteActualizar.getLocalidad());
			txtnombre.setText(clienteActualizar.getNombre());
			txtprovincia.setText(clienteActualizar.getProvincia());
			txtrazonsocial.setText(clienteActualizar.getRazonsocial());
			txttelefono.setText(clienteActualizar.getTelefono());
		}
	
		Button btnAceptar = (Button)findViewById(R.id.btnOklyl_Altacliente);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearCliente();
			}
		});
		
		Button btnCancel = (Button)findViewById(R.id.btnCancellyl_Altacliente);
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

	private void crearCliente() {
		
		if(modo == frmListaClientes.MODO_UPDATE){
			clienteActualizar.setApellido(txtapellido.getText().toString());
			clienteActualizar.setNombre(txtdireccion.getText().toString());
			//clienteActualizar.setFoto(txtfoto.getText().toString());
			clienteActualizar.setLocalidad(txtlocalidad.getText().toString());
			clienteActualizar.setNombre(txtnombre.getText().toString());
			clienteActualizar.setProvincia(txtprovincia.getText().toString());
			clienteActualizar.setRazonsocial(txtrazonsocial.getText().toString());
			clienteActualizar.setTelefono(txttelefono.getText().toString());
			clienteActualizar.setDireccion(txtdireccion.getText().toString());
			try {
				clientebo.update(clienteActualizar);
				Intent intent = new Intent(this, frmListaClientes.class);
				intent.putExtra("cliente", clienteActualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			clienteActualizar = new Clientes();
			clienteActualizar.setApellido(txtapellido.getText().toString());
			clienteActualizar.setNombre(txtdireccion.getText().toString());
			//clienteActualizar.setFoto(txtfoto.getText().toString());
			clienteActualizar.setLocalidad(txtlocalidad.getText().toString());
			clienteActualizar.setNombre(txtnombre.getText().toString());
			clienteActualizar.setProvincia(txtprovincia.getText().toString());
			clienteActualizar.setRazonsocial(txtrazonsocial.getText().toString());
			clienteActualizar.setTelefono(txttelefono.getText().toString());
			clienteActualizar.setDireccion(txtdireccion.getText().toString());
			try {
				clientebo.create(clienteActualizar);
				Intent intent = new Intent(this, frmListaClientes.class);
				intent.putExtra("cliente", clienteActualizar);
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
