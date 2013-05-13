package com.Escenario1.view.Producto;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.Escenario1.bo.CategoriaBo;
import com.Escenario1.bo.ClientesBo;
import com.Escenario1.bo.ProductosBo;
import com.Escenario1.dto.Categoria;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Productos;
import com.Escenario1.view.Categoria.CategoriaAdapters;
import com.Escenario1.view.Categoria.DialogCategoria;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.example.escenario1.R;

public class FrmAltaProducto extends Activity{
	
	private int modo;
	private Productos productoactualizar;
	private ProductosBo productobo;
	private EditText  txtcategoria;
	private EditText txtnombre;
	private EditText txtprecio;
	private EditText txtstock;
	private static final int DIALOGO_CATEGORIA=1;
	private CategoriaAdapters Adapter;
	private List<Categoria>listadecategoria;
	private CategoriaBo categoriabo;

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
		categoriabo= new CategoriaBo();
		try {
			listadecategoria=categoriabo.retrieveAll();
			CategoriaAdapters Adapter = new CategoriaAdapters(getApplicationContext(), 0, R.layout.lyt_categoriaitem,listadecategoria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		txtcategoria.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			     DialogCategoria myDialogFragment = DialogCategoria.newInstance();
			//     myDialogFragment.show(getFragmentManager(), "myDialogFragment");
			
				
				
				
			}
		});
		
		if(modo == FrmListadoProducto.MODO_UPDATE){
			productoactualizar = (Productos)b.getSerializable("producto");
			txtcategoria.setText(String.valueOf(productoactualizar.getCategoria()));
			txtnombre.setText(productoactualizar.getNombre());
			txtprecio.setText(String.valueOf(productoactualizar.getPrecio()));
			txtstock.setText(String.valueOf(productoactualizar.getStock()));
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
			productoactualizar.setStock(Integer.valueOf(txtstock.getText().toString()));
			
			try {
				productobo.update(productoactualizar);
				Intent intent = new Intent(this, FrmListadoProducto.class);
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
			productoactualizar.setStock(Integer.valueOf(txtstock.getText().toString()));
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
