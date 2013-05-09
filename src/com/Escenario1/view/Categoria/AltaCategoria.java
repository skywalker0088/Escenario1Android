package com.Escenario1.view.Categoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.Escenario1.bo.CategoriaBo;
import com.Escenario1.bo.ClientesBo;
import com.Escenario1.dto.Categoria;
import com.Escenario1.dto.Clientes;
import com.Escenario1.view.Cliente.frmListaClientes;
import com.example.escenario1.R;

public class AltaCategoria  extends Activity{
	
	private int modo;
	private Categoria categoriaActualizar;
	private CategoriaBo categoriabo;
	private EditText txtinflacion;
	private EditText txtnombre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		setContentView(R.layout.lyt_altacategoria);
		modo = (Integer)b.getSerializable("modo");
		txtinflacion = (EditText)findViewById(R.id.txtinflacionlylaltaCategoria);
		txtnombre = (EditText)findViewById(R.id.txtnombrelylaltacategoria);
		categoriabo = new CategoriaBo();
		
		if(modo == frmListaCategoria.MODO_UPDATE){
			categoriaActualizar = (Categoria)b.getSerializable("categoria");
			txtinflacion.setText(String.valueOf(categoriaActualizar.getInflacion()));
			txtnombre.setText(categoriaActualizar.getNombre());
		}
	
		Button btnAceptar = (Button)findViewById(R.id.btnOklyl_Altacategoria);
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				crearCategoria();
			}
		});
		
		Button btnCancel = (Button)findViewById(R.id.btnCancellyl_Altacategoria);
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

	private void crearCategoria() {
		
		if(modo == frmListaCategoria.MODO_UPDATE){
			categoriaActualizar.setInflacion(Float.valueOf(txtinflacion.getText().toString()));
			categoriaActualizar.setNombre(txtnombre.getText().toString());
			
			try {
				categoriabo.update(categoriaActualizar);
				Intent intent = new Intent(this, frmListaCategoria.class);
				intent.putExtra("categoria", categoriaActualizar);
				intent.putExtra("modo", modo);
				setResult(RESULT_OK, intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			categoriaActualizar = new Categoria();
			categoriaActualizar.setInflacion(Float.valueOf(txtinflacion.getText().toString()));
			categoriaActualizar.setNombre(txtnombre.getText().toString());			
			try {
				categoriabo.create(categoriaActualizar);
				Intent intent = new Intent(this, frmListaCategoria.class);
				intent.putExtra("categoria", categoriaActualizar);
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
