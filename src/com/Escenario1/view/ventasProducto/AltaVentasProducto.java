package com.Escenario1.view.ventasProducto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.Escenario1.bo.VentasProductosBo;
import com.Escenario1.dto.VentasProducto;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.example.escenario1.R;

public class AltaVentasProducto extends Activity{
	
	private int modo;
	private VentasProducto ventasproductoActualizar;
	private VentasProductosBo ventaproductobo;
	private EditText txtcantidad;
	private EditText txtprecioproducto;
	private EditText txtproducto;
	private EditText txtsubtotal;
	private EditText txtventa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		setContentView(R.layout.lyt_altacliente);
		modo = (Integer)b.getSerializable("modo");
		txtcantidad = (EditText)findViewById(R.id.txtapellidolylaltacliente);
		txtprecioproducto = (EditText)findViewById(R.id.txtdireccionlyl_Altacliente);
		txtproducto= (EditText)findViewById(R.id.txtlocalidadlyl_Altacliente);
		txtsubtotal = (EditText)findViewById(R.id.txtnombrelylaltacliente);
		txtventa= (EditText)findViewById(R.id.txtprovincialyl_Altacliente);		
		ventaproductobo = new VentasProductosBo();
		
		if(modo == frmListaVentasProducto.MODO_UPDATE){
			ventasproductoActualizar = (VentasProducto)b.getSerializable("ventaproducto");
			txtcantidad.setText(ventasproductoActualizar.getCantidad());
			txtprecioproducto.setText(String.valueOf(ventasproductoActualizar.getPrecioproducto()));
			txtproducto.setText(ventasproductoActualizar.getProducto());
			txtsubtotal.setText(String.valueOf(ventasproductoActualizar.getSubtotal()));
			txtventa.setText(ventasproductoActualizar.getVenta());
		}
	
		Button btnAceptar = (Button)findViewById(R.id.btnOklyl_listaproductoventa);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearVentaProducto();
			}
		});
		
		Button btnCancel = (Button)findViewById(R.id.btnCancellyl_listaproductoventa);
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
		
		if(modo == frmListaClientes.MODO_UPDATE){
			ventasproductoActualizar.setCantidad(Integer.valueOf(txtcantidad.getText().toString()));
			ventasproductoActualizar.setPrecioproducto(Integer.valueOf(txtprecioproducto.getText().toString()));
			ventasproductoActualizar.setProducto(Integer.valueOf(txtproducto.getText().toString()));
			ventasproductoActualizar.setSubtotal(Integer.valueOf(txtsubtotal.getText().toString()));
			ventasproductoActualizar.setVenta(Integer.valueOf(txtventa.getText().toString()));
			
			try {
				ventaproductobo.update(ventasproductoActualizar);
				Intent intent = new Intent(this, frmListaVentasProducto.class);
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
			ventasproductoActualizar.setPrecioproducto(Integer.valueOf(txtprecioproducto.getText().toString()));
			ventasproductoActualizar.setProducto(Integer.valueOf(txtproducto.getText().toString()));
			ventasproductoActualizar.setSubtotal(Integer.valueOf(txtsubtotal.getText().toString()));
			ventasproductoActualizar.setVenta(Integer.valueOf(txtventa.getText().toString()));
			try {
				ventaproductobo.create(ventasproductoActualizar);
				Intent intent = new Intent(this, frmListaVentasProducto.class);
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