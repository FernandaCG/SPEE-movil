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

    private static final String TAG = "PAGOS_ADAPTER: ";

    private List<Pago> items;
    private Context context;
    private OnItemClickListener clickListener;


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

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.vFecha.setText(items.get(position).getFechaEnvio());
        viewHolder.vFolio.setText(items.get(position).getFolioOperacion());
        if(items.get(position).getIdEstadoPago().toString().equals("1")) {
            viewHolder.verde.setVisibility(View.VISIBLE);
            viewHolder.rojo.setVisibility(View.INVISIBLE);
            viewHolder.amarillo.setVisibility(View.INVISIBLE);
        }else if(items.get(position).getIdEstadoPago().toString().equals("2")) {
            viewHolder.verde.setVisibility(View.INVISIBLE);
            viewHolder.rojo.setVisibility(View.INVISIBLE);
            viewHolder.amarillo.setVisibility(View.VISIBLE);
        }else if(items.get(position).getIdEstadoPago().toString().equals("3")) {
            viewHolder.verde.setVisibility(View.INVISIBLE);
            viewHolder.rojo.setVisibility(View.VISIBLE);
            viewHolder.amarillo.setVisibility(View.INVISIBLE);
        }
        viewHolder.vConcepto.setText(items.get(position).getCatalogoServicio().getDescripcion());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void agregarListaPagos(ArrayList<Pago> listaPagos) {
        items.addAll(listaPagos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView vFecha;
        private TextView vFolio;
        private TextView vEstado;
        private TextView vConcepto;
        private ImageView verde;
        private ImageView amarillo;
        private ImageView rojo;


        public ViewHolder(View v) {
            super(v);
            vFecha = v.findViewById(R.id.id_fecha);
            vFolio = v.findViewById(R.id.id_folio_pago);
            vEstado = v.findViewById(R.id.id_estado_pago);
            vConcepto = v.findViewById(R.id.id_concepto);
            verde = v.findViewById(R.id.verde_id);
            amarillo = v.findViewById(R.id.amarillo_id);
            rojo = v.findViewById(R.id.rojo_id);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition());
        }

    }
}

