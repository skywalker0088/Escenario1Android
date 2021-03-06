package com.Escenario1.view.Administrador;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.Escenario1.bo.AdministradorBo;
import com.Escenario1.bo.ClientesBo;
import com.Escenario1.dto.Administrador;
import com.Escenario1.dto.Clientes;
import com.example.escenario1.R;

public class AdministradorAdapter extends ArrayAdapter<Administrador>{
	
	private List<Administrador>mAdministrador, mAllAdministrador;
	private int res;

	public AdministradorAdapter(Context context, int resource,
			int textViewResourceId, List<Administrador> objects) {
		super(context, textViewResourceId, objects);
		this.res = textViewResourceId;
		this.mAdministrador = objects;
		this.mAllAdministrador = objects;
	}

	@Override
	public int getCount() {
		return mAdministrador.size();
	}

	@Override
	public Administrador getItem(int pos) {
		return mAdministrador.get(pos);
	}
	
	@Override
	public void add(Administrador object) {
		boolean estado=false;
		for (int i=0;i<mAdministrador.size();i++) {
			if(object.getIdAdministrador()==mAdministrador.get(i).getIdAdministrador()){
				remove(mAdministrador.get(i));
				mAdministrador.add(i,object);
				estado=true;
			}
		}
		if(estado==false){
		mAdministrador.add(object);
		}
		
		notifyDataSetChanged();
	}
	
	@Override
	public void remove(Administrador object) {
		mAdministrador.remove(object);
		mAllAdministrador.remove(object);
		notifyDataSetChanged();
	}
	
	@Override
	public Filter getFilter() {
		return new ProductoFilter();
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup viewGroup) {
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(res, null);
			viewHolder.lblApellido = (TextView)convertView.findViewById(R.id.lblapellidolytadministradoritem);
			viewHolder.lblClave = (TextView)convertView.findViewById(R.id.lvlclavelytadministradoritem);
			viewHolder.lblemail = (TextView)convertView.findViewById(R.id.lvlemaillytadministradoritem);
			viewHolder.lblnombre= (TextView)convertView.findViewById(R.id.lvlnombrelytadministradoritem);
			viewHolder.lblestado= (TextView)convertView.findViewById(R.id.lvlestadolytadministradoritem);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Administrador administrador= getItem(pos);
		
		
		viewHolder.lblApellido.setText(String.valueOf(administrador.getApellido()));
		viewHolder.lblClave.setText(String.valueOf(administrador.getClave()));
		viewHolder.lblemail.setText(String.valueOf(administrador.getEmail()));
		viewHolder.lblnombre.setText(String.valueOf(administrador.getNombre()));
		if(administrador.isEstado()==false){
			viewHolder.lblestado.setText(R.string.lbldesactivado);
		}else{
			viewHolder.lblestado.setText(R.string.lblactivado);
		}
		return convertView;
	}
	
	private static class ViewHolder{
		TextView lblApellido;
		TextView lblClave;
		TextView lblemail;
		TextView lblnombre;
		TextView lblestado;
		
	}

	private class ProductoFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			List<Administrador>administradorFiltrados = new ArrayList<Administrador>();
			if(constraint != null && constraint.length()> 0){
				constraint = constraint.toString().toLowerCase();
				
				for(Administrador administrador: mAllAdministrador){
					String texto = null;
					if(frmListaAdminsitrador.opcionFiltrado.equalsIgnoreCase("Apellido")){
					 texto = administrador.getApellido().toLowerCase();
					}else if(frmListaAdminsitrador.opcionFiltrado.equalsIgnoreCase("Email")){
						 texto = administrador.getEmail().toLowerCase();
					}
					System.out.println(texto);
					if(texto.contains(constraint)){
						administradorFiltrados.add(administrador);
					}
				}
				
			}else{
				administradorFiltrados = mAllAdministrador;
			}
			filterResults.count = administradorFiltrados.size();
			filterResults.values = administradorFiltrados;
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mAdministrador = (List<Administrador>) results.values;
			notifyDataSetChanged();
		}
		
	}

	
}