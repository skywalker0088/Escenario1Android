package com.Escenario1.view.ventas;

import java.util.Calendar;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.Escenario1.bo.VentasBo;
import com.Escenario1.dto.Ventas;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.example.escenario1.R;

public class AltaVenta extends Activity{
	
	private int modo;
	private Ventas ventasActualizar;
	private VentasBo ventabo;
	private EditText txtcliente;
	private EditText txtfecha;
	private EditText txttotal;
	private EditText txtvendedor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
	//	setContentView(R.layout.lyt_altaventa);
		modo = (Integer)b.getSerializable("modo");
		txtcliente = (EditText)findViewById(R.id.txtclientelyaltaventa);
		txtfecha = (EditText)findViewById(R.id.txtfechalyaltaventa);
		txttotal = (EditText)findViewById(R.id.txtotallyaltaventa);
		ventabo = new VentasBo();
		
		if(modo == frmListaClientes.MODO_UPDATE){
			ventasActualizar = (Ventas)b.getSerializable("venta");
			txtcliente.setText(ventasActualizar.getCliente());
			txtfecha.setText(String.valueOf(ventasActualizar.getFecha()));
			txtvendedor.setText(ventasActualizar.getVendedor());
			txttotal.setText(String.valueOf(ventasActualizar.getTotal()));
			
		}
	
		/*Button btnAceptar = (Button)findViewById(R.id.btnoklyl_Altaventa);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearVenta();
			}
		});*/
		
		/*Button btnCancel = (Button)findViewById(R.id.btnCancellyl_Altaventa);
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancel();
			}
		});*/
	}


	private void cancel() {
		setResult(RESULT_CANCELED);
		finish();
		
	}

	private void crearVenta() {
		
		if(modo == frmListaVentas.MODO_UPDATE){
			ventasActualizar.setCliente(Integer.valueOf(txtcliente.getText().toString()));
			//ventasActualizar.setFecha((txtfecha.getText().toString());
			ventasActualizar.setTotal(Integer.valueOf(txttotal.getText().toString()));
			ventasActualizar.setVendedor(Integer.valueOf(txtvendedor.getText().toString()));
			
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
			//ventasActualizar.setFecha((txtfecha.getText().toString());
			ventasActualizar.setTotal(Integer.valueOf(txttotal.getText().toString()));
			ventasActualizar.setVendedor(Integer.valueOf(txtvendedor.getText().toString()));
			try {
				ventabo.create(ventasActualizar);
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
	
}