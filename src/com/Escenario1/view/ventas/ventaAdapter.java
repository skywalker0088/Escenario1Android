package com.Escenario1.view.ventas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.dto.Ventas;
import com.Escenario1.dto.Ventas;
import com.example.escenario1.R;

public class ventaAdapter extends ArrayAdapter<Ventas>{
	
	private List<Ventas>mVentas, mAllVentas;
	private int res;

	public ventaAdapter(Context context, int resource,
			int textViewResourceId, List<Ventas> objects) {
		super(context, textViewResourceId, objects);
		this.res = textViewResourceId;
		this.mVentas = objects;
		this.mAllVentas = objects;
	}

	@Override
	public int getCount() {
		return mVentas.size();
	}

	@Override
	public Ventas getItem(int pos) {
		return mVentas.get(pos);
	}
	
	@Override
	public void add(Ventas object) {
		boolean estado=false;
		for (int i=0;i<mVentas.size();i++) {
			if(object.getCodVentas()==mVentas.get(i).getCodVentas()){
				remove(mVentas.get(i));
				mVentas.add(i,object);
				estado=true;
			}
		}
		if(estado==false){
		mVentas.add(object);
		}
		
		notifyDataSetChanged();
	}
	
	@Override
	public void remove(Ventas object) {
		mVentas.remove(object);
		mAllVentas.remove(object);
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
			viewHolder.lblCliente = (TextView)convertView.findViewById(R.id.lblclientelytventaitem);
			viewHolder.lblfecha = (TextView)convertView.findViewById(R.id.lblfechalytventaitem);
			viewHolder.lbltotal = (TextView)convertView.findViewById(R.id.lbltotallytventaitem);
			viewHolder.lblvendedor= (TextView)convertView.findViewById(R.id.lblvendedorlytventaitem);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Ventas ven= getItem(pos);
		
		
		viewHolder.lblCliente.setText(String.valueOf(ven.getCliente()));
		viewHolder.lblfecha.setText(String.valueOf(ven.getFecha().get(Calendar.YEAR)+'/'+ven.getFecha().get(Calendar.MONTH)+'/'+ven.getFecha().get(Calendar.DATE)));
		viewHolder.lbltotal.setText(String.valueOf(ven.getTotal()));
		viewHolder.lblvendedor.setText(String.valueOf(ven.getVendedor()));

		return convertView;
	}
	
	private static class ViewHolder{
		TextView lblCliente;
		TextView lblfecha;
		TextView lbltotal;
		TextView lblvendedor;
		
		
	}

	private class ProductoFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			List<Ventas>ventasFiltrado = new ArrayList<Ventas>();
			if(constraint != null && constraint.length()> 0){
				constraint = constraint.toString().toLowerCase();
				
				for(Ventas venta: mAllVentas){
					String texto =null;
					if(frmListaVentas.opcionFiltrado.equalsIgnoreCase("fecha")){
						 texto = String.valueOf(venta.getFecha().get(Calendar.YEAR)+'/'+venta.getFecha().get(Calendar.MONTH)+'/'+venta.getFecha().get(Calendar.DATE));
					}else if(frmListaVentas.opcionFiltrado.equalsIgnoreCase("cliente")){
						 texto = String.valueOf(venta.getCliente());
					}
					
					
					if(texto.contains(constraint)){
						ventasFiltrado.add(venta);
					}
				}
				
			}else{
				ventasFiltrado = mAllVentas;
			}
			filterResults.count = ventasFiltrado.size();
			filterResults.values = ventasFiltrado;
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mVentas = (List<Ventas>) results.values;
			notifyDataSetChanged();
		}
		
	}

	
}