package com.example.universidad.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universidad.Estudiante;
import com.example.universidad.R;
import com.example.universidad.model.MoEstudiante;

import java.util.ArrayList;


public class ListaEstudianteAdapter extends RecyclerView.Adapter<ListaEstudianteAdapter.EstudiantViewHolder> {
    View view;
    ArrayList<MoEstudiante> listaEstudiante;

    public ListaEstudianteAdapter(ArrayList<MoEstudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    @NonNull
    @Override
    public ListaEstudianteAdapter.EstudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tarjeta_estudiante, null, false);
        return new EstudiantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEstudianteAdapter.EstudiantViewHolder holder, int position) {
        holder.documento.setText(String.valueOf(listaEstudiante.get(position).getDocumento()));
        holder.name.setText(listaEstudiante.get(position).getNombre());
        holder.papellido.setText(listaEstudiante.get(position).getPapellido() + " " + listaEstudiante.get(position).getSapellido());
        holder.correo.setText(listaEstudiante.get(position).getCorreo());
        holder.telefono.setText(listaEstudiante.get(position).getTelefonno());
    }

    @Override
    public int getItemCount() {
        return listaEstudiante.size();
    }

    public class EstudiantViewHolder extends RecyclerView.ViewHolder {

        TextView documento, name, papellido, correo, telefono;

        public EstudiantViewHolder(@NonNull View itemView) {
            super(itemView);

            documento = itemView.findViewById(R.id.viewDocumento);
            name = itemView.findViewById(R.id.viewNombre);
            papellido = itemView.findViewById(R.id.viewApellidos);
            correo = itemView.findViewById(R.id.viewCorreo);
            telefono = itemView.findViewById(R.id.viewTelefono);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();
                    Intent i = new Intent(context, Estudiante.class);
                    i.putExtra("id", listaEstudiante.get(getAdapterPosition()).getDocumento());
                    context.startActivity(i);
                }
            });
        }
    }
}
