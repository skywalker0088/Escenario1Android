package com.Escenario1.view.Categoria;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.dto.Categoria;
import com.Escenario1.dto.Clientes;
import com.example.escenario1.R;

public class CategoriaAdapters extends ArrayAdapter<Categoria>{
	
	private List<Categoria>mCategoria, mAllCategoria;
	private int res;

	public CategoriaAdapters(Context context, int resource,
			int textViewResourceId, List<Categoria> objects) {
		super(context, textViewResourceId, objects);
		this.res = textViewResourceId;
		this.mCategoria = objects;
		this.mAllCategoria = objects;
	}

	@Override
	public int getCount() {
		return mCategoria.size();
	}

	@Override
	public Categoria getItem(int pos) {
		return mCategoria.get(pos);
	}
	
	@Override
	public void add(Categoria object) {
		mCategoria.add(object);
		notifyDataSetChanged();
	}
	
	@Override
	public void remove(Categoria object) {
		mCategoria.remove(object);
		mAllCategoria.remove(object);
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
			viewHolder.lblinflacion = (TextView)convertView.findViewById(R.id.lblinflacionlytcategoriaitem);
			viewHolder.lblnombre = (TextView)convertView.findViewById(R.id.lvlnombrelytcategoriaitem);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Categoria categoria= getItem(pos);
		
		
		viewHolder.lblinflacion.setText(String.valueOf(categoria.getInflacion()));
		viewHolder.lblnombre.setText(categoria.getNombre());
		
		return convertView;
	}
	
	private static class ViewHolder{
		TextView lblinflacion;
		TextView lblnombre;
		
		
	}

	private class ProductoFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			List<Categoria>categoriaFiltrados = new ArrayList<Categoria>();
			if(constraint != null && constraint.length()> 0){
				constraint = constraint.toString().toLowerCase();
				
				for(Categoria categoria: mAllCategoria){
					String texto = categoria.getNombre().toLowerCase();
					if(texto.contains(constraint)){
						categoriaFiltrados.add(categoria);
					}
				}
				
			}else{
				categoriaFiltrados = mAllCategoria;
			}
			filterResults.count = categoriaFiltrados.size();
			filterResults.values = categoriaFiltrados;
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mCategoria = (List<Categoria>) results.values;
			notifyDataSetChanged();
		}
		
	}

	
}