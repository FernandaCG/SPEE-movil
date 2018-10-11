package com.example.erick.homedashboard.com.ipn.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.homedashboard.AdjuntarPago;
import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.servicios.modelo.CatalogoServicios;

import java.util.ArrayList;
import java.util.List;

public class CatalogoServiciosAdapter extends RecyclerView.Adapter<CatalogoServiciosAdapter.ViewHolder> {

    private List<CatalogoServicios> items;
    private Context context;

    public CatalogoServiciosAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_catalogo_servicios, parent, false);
       //agregar onclick para redirigir a AdjuntarPago.class activity
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.vTitle.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void agregarListaServicios(ArrayList<CatalogoServicios> listaServicios) {
        items.addAll(listaServicios);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vTitle;

        public ViewHolder(View v) {
            super(v);
            vTitle = v.findViewById(R.id.concepto_id);
        }
    }
}
