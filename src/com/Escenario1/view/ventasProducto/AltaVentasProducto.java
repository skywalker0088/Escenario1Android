package com.Escenario1.view.ventasProducto;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Escenario1.bo.VentasProductosBo;
import com.Escenario1.dto.VentasProducto;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.Escenario1.view.ventas.tabVenta;
import com.example.escenario1.R;

public class AltaVentasProducto extends Activity{
	
	private int modo;
	private VentasProducto ventasproductoActualizar;
	private VentasProductosBo ventaproductobo;
	private EditText txtcantidad;
	private EditText txtprecioproducto;
	private EditText txtproducto;
	private TextView txtsubtotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		setContentView(R.layout.lyt_altaventaproducto);
		modo = (Integer)b.getSerializable("modo");
		txtcantidad = (EditText)findViewById(R.id.txtcantidadlylaltaventaproducto);
		txtprecioproducto = (EditText)findViewById(R.id.txtpreciolylaltaproductoventa);
		txtproducto= (EditText)findViewById(R.id.txtpreciolylaltaproductoventa);
		txtsubtotal = (TextView)findViewById(R.id.txtsubtotallylaltaproductoventa);
		ventaproductobo = new VentasProductosBo();
		txtsubtotal.setText("0");
		if(modo == tabVenta.MODO_UPDATE){
			ventasproductoActualizar = (VentasProducto)b.getSerializable("ventaproducto");
			txtcantidad.setText(String.valueOf(ventasproductoActualizar.getCantidad()));
			txtprecioproducto.setText(String.valueOf(ventasproductoActualizar.getPrecioproducto()));
			txtproducto.setText(String.valueOf(ventasproductoActualizar.getProducto()));
			txtsubtotal.setText(String.valueOf(ventasproductoActualizar.getSubtotal()));
			
		}
	
		Button btnAceptar = (Button)findViewById(R.id.btnOklylaltaproductoventa);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearVentaProducto();
			}
		});
		
		Button btnCancel = (Button)findViewById(R.id.btnCancellylaltaproductoventa);
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

	private void crearVentaProducto() {
		
		if(modo == tabVenta.MODO_UPDATE){
			ventasproductoActualizar.setCantidad(Integer.valueOf(txtcantidad.getText().toString()));
			ventasproductoActualizar.setPrecioproducto(Float.valueOf(txtprecioproducto.getText().toString()));
			ventasproductoActualizar.setProducto(Integer.valueOf(txtproducto.getText().toString()));
			ventasproductoActualizar.setSubtotal(Float.valueOf(txtprecioproducto.getText().toString())*Integer.valueOf(txtcantidad.getText().toString()));
					
			try {
				//ventaproductobo.update(ventasproductoActualizar);
				Intent intent = new Intent(this, tabVenta.class);
				intent.putExtra("ventaproducto", ventasproductoActualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			ventasproductoActualizar = new VentasProducto();
			ventasproductoActualizar.setCantidad(Integer.valueOf(txtcantidad.getText().toString()));
			ventasproductoActualizar.setPrecioproducto(Float.valueOf(txtprecioproducto.getText().toString()));
			ventasproductoActualizar.setProducto(Integer.valueOf(txtproducto.getText().toString()));
			ventasproductoActualizar.setSubtotal(Float.valueOf(txtprecioproducto.getText().toString())*Integer.valueOf(txtcantidad.getText().toString()));
			
			
			try {
			//	ventaproductobo.create(ventasproductoActualizar);
				Intent intent = new Intent(this, tabVenta.class);
				intent.putExtra("ventaproducto", ventasproductoActualizar);
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