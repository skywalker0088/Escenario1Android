package com.Escenario1.view.Cliente;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.Escenario1.bo.ClientesBo;
import com.Escenario1.dto.Clientes;
import com.Escenario1.dto.Productos;
import com.example.escenario1.R;

public class ClienteAdapter extends ArrayAdapter<Clientes>{
	
	private List<Clientes>mClientes, mAllClientes;
	private ClientesBo clientebo;
	private int res;

	public ClienteAdapter(Context context, int resource,
			int textViewResourceId, List<Clientes> objects) {
		super(context, textViewResourceId, objects);
		this.res = textViewResourceId;
		this.mClientes = objects;
		this.mAllClientes = objects;
		clientebo= new ClientesBo();
	}

	@Override
	public int getCount() {
		return mClientes.size();
	}

	@Override
	public Clientes getItem(int pos) {
		return mClientes.get(pos);
	}
	
	@Override
	public void add(Clientes object) {
		boolean estado=false;
		for (int i=0;i<mClientes.size();i++) {
			if(object.getIdCliente()==mClientes.get(i).getIdCliente()){
				remove(mClientes.get(i));
				mClientes.add(i,object);
				estado=true;
			}
		}
		if(estado==false){
		mClientes.add(object);
		}
		notifyDataSetChanged();
	}
	
	@Override
	public void remove(Clientes object) {
		mClientes.remove(object);
		mAllClientes.remove(object);
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
			viewHolder.lblApellido = (TextView)convertView.findViewById(R.id.lblapellidolytclienteitem);
			viewHolder.lblDireccion = (TextView)convertView.findViewById(R.id.lvldireccionlytclienteitem);
			//viewHolder.lblfoto= (TextView)convertView.findViewById(R.id.ivFotolytclienteitem);
			viewHolder.lbllocalidad = (TextView)convertView.findViewById(R.id.lvllocalidadlytclienteitem);
			viewHolder.lblnombre= (TextView)convertView.findViewById(R.id.lvlnombrelytclienteitem);
			viewHolder.lblProvincia= (TextView)convertView.findViewById(R.id.lvlprovincialytclienteitem);
			viewHolder.lblrazonsocial= (TextView)convertView.findViewById(R.id.lvlrazonsociallytclienteitem);
			viewHolder.lbltelefono= (TextView)convertView.findViewById(R.id.lvltelefonolytclienteitem);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Clientes clientes= getItem(pos);
		
		
		viewHolder.lblApellido.setText(String.valueOf(clientes.getApellido()));
		viewHolder.lblDireccion.setText(String.valueOf(clientes.getDireccion()));
		//viewHolder.lblfoto.setText(String.valueOf(clientes.getFoto()));
		viewHolder.lbllocalidad.setText(String.valueOf(clientes.getLocalidad()));
		viewHolder.lblnombre.setText(String.valueOf(clientes.getNombre()));
		viewHolder.lblProvincia.setText(String.valueOf(clientes.getProvincia()));
		viewHolder.lblrazonsocial.setText(String.valueOf(clientes.getRazonsocial()));
		viewHolder.lbltelefono.setText(String.valueOf(clientes.getTelefono()));
		return convertView;
	}
	
	private static class ViewHolder{
		TextView lblApellido;
		TextView lblDireccion;
		TextView lblfoto;
		TextView lbllocalidad;
		TextView lblnombre;
		TextView lblProvincia;
		TextView lblrazonsocial;
		TextView lbltelefono;
		
	}

	private class ProductoFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			List<Clientes>clientesFiltrados = new ArrayList<Clientes>();
			if(constraint != null && constraint.length()> 0){
				constraint = constraint.toString().toLowerCase();
				
				for(Clientes clientes: mAllClientes){
					String texto =null;
					if(frmListaClientes.opcionFiltrado.equalsIgnoreCase("apellido")){
						texto =clientes.getApellido().toLowerCase();
					}else if(frmListaClientes.opcionFiltrado.equalsIgnoreCase("nombre")){
					texto =clientes.getNombre().toLowerCase();
					}
					System.out.println(texto);
					if(texto.contains(constraint)){
						clientesFiltrados.add(clientes);
					}
				}
				
			}else{
				clientesFiltrados = mAllClientes;
			}
			filterResults.count = clientesFiltrados.size();
			filterResults.values = clientesFiltrados;
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mClientes = (List<Clientes>) results.values;
			notifyDataSetChanged();
		}
		
	}

	
}