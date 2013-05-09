package com.Escenario1.view.ventasProducto;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.Escenario1.bo.VentasProductosBo;
import com.Escenario1.dto.VentasProducto;
import com.example.escenario1.R;

public class VentasProductoAdapter extends ArrayAdapter<VentasProducto>{
	
	private List<VentasProducto>mVentasProducto, mAllVentasProducto;
	private VentasProductosBo ventaproductobo;
	private int res;

	public VentasProductoAdapter(Context context, int resource,
			int textViewResourceId, List<VentasProducto> objects) {
		super(context, textViewResourceId, objects);
		this.res = textViewResourceId;
		this.mVentasProducto = objects;
		this.mAllVentasProducto = objects;
		ventaproductobo= new VentasProductosBo();
	}

	@Override
	public int getCount() {
		return mVentasProducto.size();
	}

	@Override
	public VentasProducto getItem(int pos) {
		return mVentasProducto.get(pos);
	}
	
	@Override
	public void add(VentasProducto object) {
		mVentasProducto.add(object);
		notifyDataSetChanged();
	}
	
	@Override
	public void remove(VentasProducto object) {
		mVentasProducto.remove(object);
		mAllVentasProducto.remove(object);
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
			viewHolder.lblcantidad = (TextView)convertView.findViewById(R.id.txtcantidadlyproductoventaitem);
			viewHolder.lblprecioproducto = (TextView)convertView.findViewById(R.id.txtprecioUnitariolyproductoventaitem);
			viewHolder.lblproducto = (TextView)convertView.findViewById(R.id.txtProductolyproductoventaitem);
			viewHolder.lblsubtotal= (TextView)convertView.findViewById(R.id.txtsubtotallyproductoventaitem);
			viewHolder.lblventa= (TextView)convertView.findViewById(R.id.txtventalyproductoventaitem);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		VentasProducto ventaproducto= getItem(pos);
		
		
		viewHolder.lblcantidad.setText(String.valueOf(ventaproducto.getCantidad()));
		viewHolder.lblprecioproducto.setText(String.valueOf(ventaproducto.getPrecioproducto()));
		viewHolder.lblproducto.setText(String.valueOf(ventaproducto.getProducto()));
		viewHolder.lblsubtotal.setText(String.valueOf(ventaproducto.getSubtotal()));
		viewHolder.lblventa.setText(String.valueOf(ventaproducto.getVenta()));
		
		return convertView;
	}
	
	private static class ViewHolder{
		TextView lblcantidad;
		TextView lblprecioproducto;
		TextView lblproducto;
		TextView lblsubtotal;
		TextView lblventa;
	}

	private class ProductoFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			List<VentasProducto>clientesFiltrados = new ArrayList<VentasProducto>();
			if(constraint != null && constraint.length()> 0){
				constraint = constraint.toString().toLowerCase();
				
				for(VentasProducto productoventa: mAllVentasProducto){
					/*String texto = productoventa.getNombre().toLowerCase();
					System.out.println(texto);
					if(texto.contains(constraint)){
						clientesFiltrados.add(productoventa);
					}*/
				}
				
			}else{
				clientesFiltrados = mAllVentasProducto;
			}
			filterResults.count = clientesFiltrados.size();
			filterResults.values = clientesFiltrados;
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mVentasProducto = (List<VentasProducto>) results.values;
			notifyDataSetChanged();
		}
		
	}

	
}