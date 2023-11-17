package com.example.universidad;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.universidad.model.Conexion;

public class MainActivity extends AppCompatActivity {
    private Button btnEstudiante, btnNotas, btnMaterias, btnProfesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexion();
        btnNotas = findViewById(R.id.btnNot);
        btnEstudiante = findViewById(R.id.btnEst);
        btnMaterias = findViewById(R.id.btnMat);
        btnProfesor = findViewById(R.id.btnProf);
        Botones();
    }

    public void conexion() {
        Conexion conexion = new Conexion(this);
        SQLiteDatabase db = conexion.getWritableDatabase();
        if (db != null) {
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error en la creacion", Toast.LENGTH_SHORT).show();
        }
        conexion.close();
    }

    public void Botones() {

        btnEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Estudiante.class);
                startActivity(i);
            }
        });

        btnNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Notas.class);
                startActivity(i);
            }
        });
        btnMaterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Asignaturas.class);
                startActivity(i);
            }
        });
        btnProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Asignaturas.class);
                startActivity(i);
            }
        });


    }
}