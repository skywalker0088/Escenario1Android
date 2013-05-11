package com.Escenario1.view.Categoria;

import java.util.ArrayList;
import java.util.List;

import com.Escenario1.bo.CategoriaBo;
import com.Escenario1.dto.Categoria;
import com.example.escenario1.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DialogCategoria extends DialogFragment {

	private CategoriaBo categoriabo;
	private ListView lstlistado;
	private CategoriaAdapters Adapter;
	private List<Categoria> listadecategoria;
	private List<String>listanombres;
	
	static DialogCategoria newInstance() {
		String titulo = "Elegir Catagoria";
		DialogCategoria dc = new DialogCategoria();
		Bundle args = new Bundle();
		args.putString("titulo", titulo);
		dc.setArguments(args);
		return dc;
	}

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		categoriabo = new CategoriaBo();
		try {
			
			listadecategoria = categoriabo.retrieveAll();
			for(int i =0;i<listadecategoria.size();i++){
				listanombres.add(listadecategoria.get(i).getNombre());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final CharSequence[] items = {"Red", "Green", "Blue"};

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Colors");
		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		          public void onClick(DialogInterface dialog, int item) {
		                Toast.makeText(getActivity().getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
		            }
		        });
		AlertDialog alert = builder.create();
		alert.show();
        return alert;
		/*
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle(R.string.lblCategoria)
	           .setSingleChoiceItems(listanombres, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               // The 'which' argument contains the index position
	               // of the selected item
	           }
	    });
	    return builder.create();

		/*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.lblCategoria)
		.setSingleChoiceItems(listadecategoria, 1, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub

					}
				});
		
		builder.create();*/

	}

	public void show(FragmentManager fragmentManager, String string) {
		DialogCategoria.newInstance().setShowsDialog(true);
		
		
	}


}
