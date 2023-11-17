package com.example.universidad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.universidad.adapters.ListaEstudianteAdapter;
import com.example.universidad.model.DbEstudiante;
import com.example.universidad.model.MoEstudiante;

import java.util.ArrayList;

public class ListaEstudiantes extends AppCompatActivity {
    RecyclerView recyclerEstudiante;
    ListaEstudianteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estudiantes);
        recyclerEstudiante = findViewById(R.id.recyclerEstudiante);
        recyclerEstudiante.setLayoutManager(new LinearLayoutManager(this));

        DbEstudiante dbEstudiante = new DbEstudiante(this);

        adapter = new ListaEstudianteAdapter(dbEstudiante.mostrarEstudiante());
        recyclerEstudiante.setAdapter(adapter);
    }
}