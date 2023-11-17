package com.example.universidad;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universidad.adapters.ListAsignaturaAdapter;
import com.example.universidad.model.DbAsignatura;
import com.example.universidad.model.MoAsignatura;

import java.util.ArrayList;

public class Asignaturas extends AppCompatActivity {

    RecyclerView recyclerAsignatura;

    ListAsignaturaAdapter adapter;

    public EditText textAsignatura;
    public TextView textCodigo;

    String inputAsignatura;

    Button btnGuardar, btnModificar, btnEliminar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);
        instancias();
        recycler();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
                DbAsignatura dbAsignatura = new DbAsignatura(Asignaturas.this);

                long id = dbAsignatura.insertar(inputAsignatura);
                if (id > 0) {
                    Toast.makeText(Asignaturas.this, "Asignatura guarda", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Asignaturas.this, "Error", Toast.LENGTH_SHORT).show();
                }
                dbAsignatura.close();
                recycler();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void guardar() {
        inputAsignatura = textAsignatura.getText().toString().trim();
    }

    public void instancias() {
        btnGuardar = findViewById(R.id.btnGuardarAsig);
        btnEliminar = findViewById(R.id.btnEliminarAsig);
        btnModificar = findViewById(R.id.btnModificarAsig);
        btnSalir  = findViewById(R.id.btnGoBackSubRegAsigna);

        textAsignatura = findViewById(R.id.viewAsignatura);
        textCodigo = findViewById(R.id.viewCodigo);
       // textAsignatura.setInputType(InputType.TYPE_NULL);
    }

    public void recycler() {
        recyclerAsignatura = findViewById(R.id.recyclerAsignatura);
        recyclerAsignatura.setLayoutManager(new LinearLayoutManager(this));

        DbAsignatura dbAsignatura = new DbAsignatura(this);
        ArrayList<MoAsignatura> lista = dbAsignatura.mostrarAsignatura();
        adapter = new ListAsignaturaAdapter(lista, this);
        recyclerAsignatura.setAdapter(adapter);
        dbAsignatura.close();
        Integer total = lista.size();
        textCodigo.setText(String.valueOf(lista.get(total - 1).getCodigo()));
    }
}