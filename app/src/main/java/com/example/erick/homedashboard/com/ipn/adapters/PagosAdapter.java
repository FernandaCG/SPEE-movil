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
        viewHolder.vTitle.setText(items.get(position).getName());
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

        TextView vTitle;
        private ImageView fotoImageView;

        public ViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.book_title_id);
            fotoImageView = (ImageView) itemView.findViewById(R.id.book_img_id);

        }
    }
}
