package com.Escenario1.view.Vendedor;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Vendedor;
import com.example.escenario1.R;

public class VendedorAdapter extends ArrayAdapter<Vendedor> {
	
	private List<Vendedor>mVendedores, mAllVendedores;
	private int res;

	public VendedorAdapter(Context context, int textViewResourceId,	List<Vendedor> p_vendedores) {
		super(context, textViewResourceId, p_vendedores);
		this.setRes(textViewResourceId);
		this.setVendedor(p_vendedores);
		this.setAllVendedores(p_vendedores);
	}

	@Override
	public int getCount() {
		return this.getVendedor().size();
	}

	@Override
	public Vendedor getItem(int pos) {
		return this.getVendedor().get(pos);
	}

	@Override
	public Filter getFilter() {
		return new VendedorFilter();
	}

	@Override
	public void add(Vendedor p_cliente) {
		this.getVendedor().add(p_cliente);
		notifyDataSetChanged();
	}

	@Override
	public void remove(Vendedor p_cliente) {
		this.getVendedor().remove(p_cliente);
		this.getAllVendedor().remove(p_cliente);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup viewGroup) {
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(this.getRes(), null);
			viewHolder.lblapellido = (TextView)convertView.findViewById(R.id.ivFotolyvendedoritem);
			viewHolder.lblclave = (TextView)convertView.findViewById(R.id.lvlclavelyvendedoritem);
			viewHolder.lblemail = (TextView)convertView.findViewById(R.id.lvlemaillyvendedoritem);
			viewHolder.lblfoto = (TextView)convertView.findViewById(R.id.lvlfoto);
			viewHolder.lblnombre = (TextView)convertView.findViewById(R.id.lvlnombre);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}


		Vendedor vendedor = getItem(pos);

		viewHolder.lblapellido.setText(String.valueOf(vendedor.getApellido()));
		viewHolder.lblclave.setText(vendedor.getClave());
		viewHolder.lblemail.setText(vendedor.getEmail());
		viewHolder.lblfoto.setText(vendedor.getFoto());
		viewHolder.lblnombre.setText(vendedor.getNombre());
//		view.setOnClickListener(this);

		return convertView;
	}

	private static class ViewHolder{
		TextView lblapellido;
		TextView lblclave;
		TextView lblemail;
		TextView lblfoto;
		TextView lblnombre;
	}

	private class VendedorFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			List<Vendedor>clientesFiltrados = new ArrayList<Vendedor>();
			if(constraint != null && constraint.length()> 0){
				constraint = constraint.toString().toLowerCase();
				
				for(Vendedor vendedor: mAllVendedores){
					String texto = vendedor.toString().toLowerCase();
					if(texto.contains(constraint)){
						clientesFiltrados.add(vendedor);
					}
				}
				
			}else{
				clientesFiltrados = mAllVendedores;
			}
			filterResults.count = clientesFiltrados.size();
			filterResults.values = clientesFiltrados;
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mVendedores = (List<Vendedor>) results.values;
			notifyDataSetChanged();
		}
	}


	private void setVendedor(List<Vendedor> p_vendedor){
		this.mVendedores = p_vendedor;
	}

	private void setAllVendedores(List<Vendedor> p_vendedor){
		this.mAllVendedores = p_vendedor;
	}


	public int getRes() {
		return this.res;
	}

	private void setRes(int res) {
		this.res = res;
	}

	public List<Vendedor> getVendedor(){
		return this.mVendedores;
	}

	public List<Vendedor> getAllVendedor(){
		return this.mAllVendedores;
	}
}
