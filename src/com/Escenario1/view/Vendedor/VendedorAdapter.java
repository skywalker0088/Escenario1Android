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

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Vendedor;
import com.example.escenario1.R;

public class VendedorAdapter extends ArrayAdapter<Vendedor>{
	
	private List<Vendedor>mVendedor, mAllVendedor;
	private VendedorBo vendedorbo;
	private int res;

	public VendedorAdapter(Context context, int resource,
			int textViewResourceId, List<Vendedor> objects) {
		super(context, textViewResourceId, objects);
		this.res = textViewResourceId;
		this.mVendedor = objects;
		this.mAllVendedor = objects;
		vendedorbo= new VendedorBo();
	}

	@Override
	public int getCount() {
		return mVendedor.size();
	}

	@Override
	public Vendedor getItem(int pos) {
		return mVendedor.get(pos);
	}
	
	@Override
	public void add(Vendedor object) {
		
		boolean estado=false;
		for (int i=0;i<mVendedor.size();i++) {
			if(object.getIdVendedor()==mVendedor.get(i).getIdVendedor()){
				remove(mVendedor.get(i));
				mVendedor.add(object);
				estado=true;
			}
		}
		if(estado==false){
		mVendedor.add(object);
		}
		notifyDataSetChanged();
	}
	
	@Override
	public void remove(Vendedor object) {
		mVendedor.remove(object);
		mAllVendedor.remove(object);
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
			viewHolder.lblApellido = (TextView)convertView.findViewById(R.id.lblapellidolyvendedoritem);
			viewHolder.lblClave = (TextView)convertView.findViewById(R.id.lvlclavelyvendedoritem);
			//viewHolder.lblfoto= (TextView)convertView.findViewById(R.id.ivFotolytclienteitem);
			viewHolder.lblemail = (TextView)convertView.findViewById(R.id.lvlemaillyvendedoritem);
			viewHolder.lblnombre= (TextView)convertView.findViewById(R.id.lvlnombrelyvendedoritem);
			viewHolder.lblestado= (TextView)convertView.findViewById(R.id.lvlestadolyvendedoritem);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Vendedor vendedor= getItem(pos);
		
		
		viewHolder.lblApellido.setText(String.valueOf(vendedor.getApellido()));
		viewHolder.lblClave.setText(String.valueOf(vendedor.getClave()));
		//viewHolder.lblfoto.setText(String.valueOf(clientes.getFoto()));
		viewHolder.lblemail.setText(String.valueOf(vendedor.getEmail()));
		viewHolder.lblnombre.setText(String.valueOf(vendedor.getNombre()));
		if(vendedor.isEstado()==false){
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
		TextView lblfoto;
		TextView lblnombre;
		TextView lblestado;
		
		
	}

	private class ProductoFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			List<Vendedor>vendedorFiltrados = new ArrayList<Vendedor>();
			if(constraint != null && constraint.length()> 0){
				constraint = constraint.toString().toLowerCase();
				
				for(Vendedor vendedor: mAllVendedor){
					String texto = null;
					if(FrmListadoVendedor.opcionFiltrado.equalsIgnoreCase("email")){
						texto=vendedor.getEmail().toLowerCase();
					}else if(FrmListadoVendedor.opcionFiltrado.equalsIgnoreCase("apellido")){
						texto=vendedor.getApellido().toLowerCase();
					}
					System.out.println(texto);
					if(texto.contains(constraint)){
						vendedorFiltrados.add(vendedor);
					}
				}
				
			}else{
				vendedorFiltrados = mAllVendedor;
			}
			filterResults.count = vendedorFiltrados.size();
			filterResults.values = vendedorFiltrados;
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mVendedor = (List<Vendedor>) results.values;
			notifyDataSetChanged();
		}
		
	}

	
}