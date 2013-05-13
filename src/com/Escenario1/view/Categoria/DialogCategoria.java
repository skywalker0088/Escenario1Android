package com.Escenario1.view.Categoria;

import com.example.escenario1.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogCategoria extends DialogFragment{

	
	
	public static DialogCategoria newInstance() {
		  
		  String title = "My Fragment";
		  
		  DialogCategoria f = new DialogCategoria();
		        Bundle args = new Bundle();
		        args.putString("title", title);
		        f.setArguments(args);
		        return f;
		    }

		 @Override
		 public Dialog onCreateDialog(Bundle savedInstanceState) {
		  String title = getArguments().getString("title");
		  Dialog myDialog = new AlertDialog.Builder(getActivity())
		     .setIcon(R.drawable.ic_launcher)
		     .setTitle(title)
		     .setPositiveButton("OK", 
		       new DialogInterface.OnClickListener() {
		        
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		       
		        }
		       })
		     .setNegativeButton("Cancel", 
		       new DialogInterface.OnClickListener() {
		        
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        
		        }
		       })
		     .create();

		  return myDialog;
		 }
		 
		 
		 
}
