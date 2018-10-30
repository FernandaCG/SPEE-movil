package com.example.erick.homedashboard.com.ipn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.notificaciones.modelo.Notificacion;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesAdapter extends RecyclerView.Adapter<NotificacionesAdapter.ViewHolder> {

    private List<Notificacion> items;
    private Context context;

    public NotificacionesAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public NotificacionesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_notificaciones, parent, false);

        return new NotificacionesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificacionesAdapter.ViewHolder viewHolder, int position) {
        viewHolder.vMotivo.setText(items.get(position).getMotivo());
        viewHolder.vFecha.setText(items.get(position).getFechaEnvio());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void agregarListaNotificaciones(ArrayList<Notificacion> listaNotificaciones) {
        items.addAll(listaNotificaciones);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vMotivo;
        TextView vFecha;

        public ViewHolder(View v) {
            super(v);
            vMotivo = v.findViewById(R.id.motivo_id);
            vFecha = v.findViewById(R.id.fecha_id);
        }
    }
}
