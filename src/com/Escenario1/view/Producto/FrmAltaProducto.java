package com.Escenario1.view.Producto;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.bo.ProductosBo;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Productos;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.example.escenario1.R;

public class FrmAltaProducto extends Activity{
	
	private int modo;
	private Productos productoactualizar;
	private ProductosBo productobo;
	private EditText txtcategoria;
	private EditText txtnombre;
	private EditText txtprecio;
	private EditText txtstock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		setContentView(R.layout.lyt_altaproducto);
		modo = (Integer)b.getSerializable("modo");
		txtcategoria = (EditText)findViewById(R.id.txtCategorialyl_AltaProducto);
		txtnombre = (EditText)findViewById(R.id.txtnombrelylaltaproducto);
		txtprecio= (EditText)findViewById(R.id.txtPreciolyl_AltaProducto);
		txtstock = (EditText)findViewById(R.id.txtCantidadlyl_AltaProducto);
		productobo = new ProductosBo();
		
		if(modo == frmListaClientes.MODO_UPDATE){
			productoactualizar = (Productos)b.getSerializable("producto");
			txtcategoria.setText(productoactualizar.getCategoria());
			txtnombre.setText(productoactualizar.getNombre());
			txtprecio.setText(String.valueOf(productoactualizar.getPrecio()));
			txtstock.setText(productoactualizar.getStock());
		}
	
		Button btnAceptar = (Button)findViewById(R.id.btnOklyl_AltaProducto);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearProducto();
			}
		});
		
		Button btnCancel = (Button)findViewById(R.id.btnCancellyl_AltaProducto);
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

	private void crearProducto() {
		
		if(modo == FrmListadoProducto.MODO_UPDATE){
			productoactualizar.setCategoria(Integer.valueOf(txtcategoria.getText().toString()));
			productoactualizar.setNombre(txtnombre.getText().toString());
			productoactualizar.setPrecio(Float.valueOf(txtprecio.getText().toString()));
			productoactualizar.setStock(Integer.valueOf(txtnombre.getText().toString()));
			
			try {
				productobo.update(productoactualizar);
				Intent intent = new Intent(this, frmListaClientes.class);
				intent.putExtra("producto", productoactualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			productoactualizar = new Productos();
			productoactualizar.setCategoria(Integer.valueOf(txtcategoria.getText().toString()));
			productoactualizar.setNombre(txtnombre.getText().toString());
			productoactualizar.setPrecio(Float.valueOf(txtprecio.getText().toString()));
			productoactualizar.setStock(Integer.valueOf(txtnombre.getText().toString()));
			try {
				productobo.create(productoactualizar);
				Intent intent = new Intent(this, FrmListadoProducto.class);
				intent.putExtra("producto", productoactualizar);
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
