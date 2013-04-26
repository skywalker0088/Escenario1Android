package com.Escenario1.view;

import java.util.List;

import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Vendedor;
import com.example.escenario1.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_loguin);
		VendedorBo vendedorbo= new VendedorBo();
		Vendedor v= new Vendedor();
		v.setApellido("pepe");
		v.setClave("12312213");
		v.setEmail("pepe@hotmail.com");
		v.setFoto("asdsad");
		v.setNombre("asdwsew");
	//	ListView listavendedor= (ListView)findViewById(R.id.lstVendedorLytlistaVendedor);
		List<Vendedor> listav = null;

		System.out.println(v.getApellido());
		try {
			
			
			vendedorbo.create(v);
			
			//listav = vendedorbo.retrieveAll();
			
			System.out.println("se creo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("no se creo");
			e.printStackTrace();
		}
	//	ArrayAdapter<Vendedor>Adapter= new ArrayAdapter<Vendedor>(this, android.R.layout.simple_list_item_1,listav);
		
		
		
		
	//	listavendedor.setAdapter(Adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
