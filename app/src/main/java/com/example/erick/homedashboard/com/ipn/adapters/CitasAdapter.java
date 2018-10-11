package com.example.erick.homedashboard.com.ipn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.citas.modelo.Cita;

import java.util.ArrayList;
import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {

    private List<Cita> items;
    private Context context;

    public CitasAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public CitasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_citas, parent, false);

        return new CitasAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CitasAdapter.ViewHolder viewHolder, int position) {
        viewHolder.vTitle.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void agregarListaCitas(ArrayList<Cita> listaCitas) {
        items.addAll(listaCitas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vTitle;
        private ImageView fotoImageView;

        public ViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.book_title_id);
        }
    }
}