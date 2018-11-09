package com.example.erick.homedashboard.com.ipn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.servicios.modelo.CatalogoServicios;


import java.util.ArrayList;
import java.util.List;

public class CatalogoServiciosAdapter extends RecyclerView.Adapter<CatalogoServiciosAdapter.ViewHolder> {

    private static final String TAG = "SERVIOS ADAPTER: ";

    private List<CatalogoServicios> items;
    private Context context;
    private OnItemClickListener clickListener;

    public CatalogoServiciosAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_catalogo_servicios, parent, false);
               return new ViewHolder(itemView);
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.vClave.setText(items.get(position).getClave());
        viewHolder.vConcepto.setText(items.get(position).getDescripcion());
        viewHolder.vPrecio.setText(items.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void agregarListaServicios(ArrayList<CatalogoServicios> listaServicios) {
        items.addAll(listaServicios);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView vClave;
        private TextView vConcepto;
        private TextView vPrecio;
        private TextView vArea;

        public ViewHolder(View v) {
            super(v);
            vClave = v.findViewById(R.id.clave_id);
            vConcepto = v.findViewById(R.id.concepto_id);
            vPrecio = v.findViewById(R.id.precio_id);
            vArea = v.findViewById(R.id.area_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition());
        }
        
    }
}
