package com.example.erick.homedashboard.com.ipn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.notas.modelo.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.ViewHolder> {

    private List<Nota> items;
    private Context context;

    public NotasAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public NotasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_notas, parent, false);

        return new NotasAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotasAdapter.ViewHolder viewHolder, int position) {
        viewHolder.vFecha.setText(items.get(position).getFechaEmision());
        viewHolder.vIdServicio.setText(items.get(position).getIdCatalogoServicio().toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void agregarListaNotas(ArrayList<Nota> listaNotas) {
        for (Nota n: listaNotas) {
            System.out.println(n);
        }
        items.addAll(listaNotas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vFecha;
        TextView vIdServicio;


        public ViewHolder(View v) {
            super(v);
            vFecha = (TextView) v.findViewById(R.id.fecha_nota_id);
            vIdServicio = (TextView) v.findViewById(R.id.servicio_nota_id);

        }
    }
}