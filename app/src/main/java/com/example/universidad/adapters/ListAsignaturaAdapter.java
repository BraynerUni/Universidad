package com.example.universidad.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universidad.Asignaturas;
import com.example.universidad.R;
import com.example.universidad.model.MoAsignatura;

import java.util.ArrayList;

public class ListAsignaturaAdapter extends RecyclerView.Adapter<ListAsignaturaAdapter.AsignaturaViewHolder> {

    View view;

    Asignaturas views;

    ArrayList<MoAsignatura> listaAsignatura;

    public ListAsignaturaAdapter(ArrayList<MoAsignatura> listaAsignatura, Asignaturas views) {
        this.listaAsignatura = listaAsignatura;
        this.views = views;
    }

    @NonNull
    @Override
    public ListAsignaturaAdapter.AsignaturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_asignatura, null, false);
        return new AsignaturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAsignaturaAdapter.AsignaturaViewHolder holder, int position) {
        holder.codigo.setText(String.valueOf(listaAsignatura.get(position).getCodigo()));
        holder.nombre.setText(String.valueOf(listaAsignatura.get(position).getAsignatura()));
    }

    @Override
    public int getItemCount() {
        return listaAsignatura.size();
    }

    public class AsignaturaViewHolder extends RecyclerView.ViewHolder {
        TextView codigo, nombre;

        public AsignaturaViewHolder(@NonNull View itemView) {
            super(itemView);

            codigo = itemView.findViewById(R.id.viewCodigoAsignatura);
            nombre = itemView.findViewById(R.id.viewNombreAsignatura);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    views.textCodigo.setText(String.valueOf(listaAsignatura.get(getAdapterPosition()).getCodigo()));
                    views.textAsignatura.setText(listaAsignatura.get(getAdapterPosition()).getAsignatura().trim());
                }
            });
        }
    }
}
