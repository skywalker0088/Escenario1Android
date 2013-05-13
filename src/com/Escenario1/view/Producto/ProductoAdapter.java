package com.Escenario1.view.Producto;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.Escenario1.dto.Productos;
import com.example.escenario1.R;

public class ProductoAdapter extends ArrayAdapter<Productos>{
	
	private List<Productos>mProducto, mAllProducto;
	private int res;

	public ProductoAdapter(Context context, int resource,
			int textViewResourceId, List<Productos> objects) {
		super(context, textViewResourceId, objects);
		this.res = textViewResourceId;
		this.mProducto = objects;
		this.mAllProducto = objects;
	}

	@Override
	public int getCount() {
		return mProducto.size();
	}

	@Override
	public Productos getItem(int pos) {
		return mProducto.get(pos);
	}
	
	@Override
	public void add(Productos object) {
		boolean estado=false;
		for (int i=0;i<mProducto.size();i++) {
			if(object.getCodProducto()==mProducto.get(i).getCodProducto()){
				remove(mProducto.get(i));
				mProducto.add(i,object);
				estado=true;
			}
		}
		if(estado==false){
		mProducto.add(object);
		}
		notifyDataSetChanged();
	}
	
	@Override
	public void remove(Productos object) {
		mProducto.remove(object);
		mAllProducto.remove(object);
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
			viewHolder.lblCategoria = (TextView)convertView.findViewById(R.id.lvlcategorialytproductoitem);
			viewHolder.lblcodProducto = (TextView)convertView.findViewById(R.id.lblcodproductolytproductoitem);
			viewHolder.lblnombre= (TextView)convertView.findViewById(R.id.lblnombrelytproductoitem);
			viewHolder.lblprecio = (TextView)convertView.findViewById(R.id.lvlpreciolytproductoitem);
			viewHolder.lblstock= (TextView)convertView.findViewById(R.id.lvlcantidadlytproductoitem);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Productos producto= getItem(pos);
		
		
		viewHolder.lblCategoria.setText(String.valueOf(producto.getCategoria()));
		viewHolder.lblcodProducto.setText(String.valueOf(producto.getCodProducto()));
		viewHolder.lblnombre.setText(String.valueOf(producto.getNombre()));
		viewHolder.lblprecio.setText(String.valueOf(producto.getPrecio()));
		viewHolder.lblstock.setText(String.valueOf(producto.getStock()));
				
		return convertView;
	}
	
	private static class ViewHolder{
		TextView lblCategoria;
		TextView lblcodProducto;
		TextView lblnombre;
		TextView lblprecio;
		TextView lblstock;
		
		
	}

	private class ProductoFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			List<Productos>productosFiltrados = new ArrayList<Productos>();
			if(constraint != null && constraint.length()> 0){
				constraint = constraint.toString().toLowerCase();
				
				for(Productos producto: mAllProducto){
					String texto =null;
					if(FrmListadoProducto.opcionFiltrado.equalsIgnoreCase("nombre")){
					texto = producto.getNombre().toLowerCase();
					}else if(FrmListadoProducto.opcionFiltrado.equalsIgnoreCase("categoria")){
						texto = String.valueOf(producto.getCategoria());
					}
					System.out.println(texto);
					if(texto.contains(constraint)){
						productosFiltrados.add(producto);
					}
				}
				
			}else{
				productosFiltrados = mAllProducto;
			}
			filterResults.count = productosFiltrados.size();
			filterResults.values = productosFiltrados;
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mProducto = (List<Productos>) results.values;
			notifyDataSetChanged();
		}
		
	}
}