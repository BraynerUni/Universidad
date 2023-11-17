package com.example.universidad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universidad.adapters.ListaEstudianteAdapter;
import com.example.universidad.model.DbEstudiante;
import com.example.universidad.model.MoEstudiante;

public class Estudiante extends AppCompatActivity {
    Button btnGuardar, btnBuscar, btnModificar, btnEliminar, btnSalir;
    EditText documento, name, papellido, sapellido, correo, telefono;

    String inpName, inpPapellido, inpSapellido, inpCorreo, inpTelefono;
    Integer inpDocumento;
    RecyclerView recyclerEstudiante;
    int doc = 0;
    MoEstudiante estudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);

        instancias();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            doc = extras.getInt("id");
            editar(doc);
            documento.setEnabled(false);
        }

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Estudiante.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEstudiante dbEstudiante = new DbEstudiante(Estudiante.this);
                if (dbEstudiante.eliminarEstudiante(Integer.parseInt(documento.getText().toString().trim()))) {
                    Toast.makeText(Estudiante.this, "Estudiante Eliminado", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(Estudiante.this, "Error al Eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar(true)) {
                    DbEstudiante dbEstudiante = new DbEstudiante(Estudiante.this);

                    long id = dbEstudiante.insertarEstudiante(inpDocumento, inpName, inpPapellido, inpSapellido, inpCorreo, inpTelefono);
                    if (id > 0) {
                        Toast.makeText(Estudiante.this, "Estudiante Guardado", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else {
                        Toast.makeText(Estudiante.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    dbEstudiante.close();
                }
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_lista_estudiantes);
                recyclerEstudiante = findViewById(R.id.recyclerEstudiante);
                recyclerEstudiante.setLayoutManager(new LinearLayoutManager(Estudiante.this));

                DbEstudiante dbEstudiante = new DbEstudiante(Estudiante.this);

                ListaEstudianteAdapter adapter = new ListaEstudianteAdapter(dbEstudiante.mostrarEstudiante());
                recyclerEstudiante.setAdapter(adapter);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEstudiante dbEstudiante = new DbEstudiante(Estudiante.this);
                if (validar(false)) {
                    boolean db = dbEstudiante.updateEstudiante(inpDocumento, inpName, inpPapellido, inpSapellido, inpCorreo, inpTelefono);
                    if (db) {
                        Toast.makeText(Estudiante.this, "Estudiante Actualizado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Estudiante.this, "Error al Actualizar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void guardar() {
        inpDocumento = Integer.valueOf(documento.getText().toString().trim());
        inpName = name.getText().toString().trim();
        inpPapellido = papellido.getText().toString().trim();
        inpSapellido = sapellido.getText().toString().trim();
        inpCorreo = correo.getText().toString().trim();
        inpTelefono = telefono.getText().toString().trim();
    }
    public void limpiar() {
        documento.setText("");
        name.setText("");
        papellido.setText("");
        sapellido.setText("");
        correo.setText("");
        telefono.setText("");
    }
    public Boolean validar(boolean revisar) {
        estudiante = null;
        if (revisar) {
            DbEstudiante dbEstudiante = new DbEstudiante(this);
            estudiante = dbEstudiante.buscarEstudiante(Integer.parseInt(documento.getText().toString().trim()));
            dbEstudiante.close();
        }

        if (estudiante != null) {
            Toast.makeText(Estudiante.this, "El Usuario ya esta registrado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(documento.getText().toString().trim()) >= 1 && !name.getText().toString().trim().isEmpty() && !papellido.getText().toString().trim().isEmpty()
                && !correo.getText().toString().trim().isEmpty() && !telefono.getText().toString().trim().isEmpty()) {
            guardar();
            return true;
        } else {
            Toast.makeText(Estudiante.this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public void editar(int doc) {
        DbEstudiante dbEstudiante = new DbEstudiante(this);
        estudiante = dbEstudiante.buscarEstudiante(doc);
        documento.setText(String.valueOf(estudiante.getDocumento()));
        name.setText(estudiante.getNombre());
        papellido.setText(estudiante.getPapellido());
        sapellido.setText(estudiante.getSapellido());
        correo.setText(estudiante.getCorreo());
        telefono.setText(estudiante.getTelefonno());
        dbEstudiante.close();
    }
    public void instancias(){
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnModificar = findViewById(R.id.btnModificar);
        btnSalir = findViewById(R.id.btnGoBackSubReg);

        documento = findViewById(R.id.inputDocumento);
        name = findViewById(R.id.inputName);
        papellido = findViewById(R.id.inputLastName);
        sapellido = findViewById(R.id.inputSecondName);
        correo = findViewById(R.id.inputEmail);
        telefono = findViewById(R.id.inputTelefono);
    }
}