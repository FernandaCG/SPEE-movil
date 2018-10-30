package com.example.erick.homedashboard.com.ipn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {

    private List<Pago> items;
    private Context context;

    public PagosAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_pagos, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.vClave.setText(items.get(position).getFechaEnvio());
        viewHolder.vFecha.setText(items.get(position).getFolioOperacion());
//        viewHolder.vEstado.setText(items.get(position).getIdEstadoPago());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void agregarListaPagos(ArrayList<Pago> listaPagos) {
        items.addAll(listaPagos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vClave;
        TextView vFecha;
      //  TextView vEstado;

        private ImageView fotoImageView;


        public ViewHolder(View v) {
            super(v);
            vFecha = (TextView) v.findViewById(R.id.fecha_evio_id);
            vClave = (TextView) v.findViewById(R.id.clave_id);
          //  vEstado = (TextView) v.findViewById(R.id.estado_id);
            fotoImageView = (ImageView) itemView.findViewById(R.id.book_img_id);

        }
    }
}
