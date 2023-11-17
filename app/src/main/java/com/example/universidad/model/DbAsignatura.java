package com.example.universidad.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbAsignatura extends Conexion {

    Context context;

    public DbAsignatura(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertar(String nombre) {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("nombre", nombre);

        long id = db.insert(name_table_asignatura, null, value);
        return id;
    }

    public ArrayList<MoAsignatura> mostrarAsignatura() {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ArrayList<MoAsignatura> list = new ArrayList<>();
        MoAsignatura asignatura = null;
        Cursor cursor = null;

        cursor = db.rawQuery(" SELECT * FROM " + name_table_asignatura, null);
        if (cursor.moveToFirst()) {
            do {
                asignatura = new MoAsignatura();
                asignatura.setCodigo(cursor.getInt(0));
                asignatura.setAsignatura(cursor.getString(1));
                list.add(asignatura);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public MoAsignatura buscarAsignatura(int codigo) {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();

        MoAsignatura asignatura = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM "+name_table_asignatura + " WHERE id_asignaturaEstudiante= " + codigo, null);
        if(cursor.moveToFirst()){
            asignatura = new MoAsignatura();
            asignatura.setCodigo(cursor.getInt(0));
            asignatura.setAsignatura(cursor.getString(1));
        }
        cursor.close();
        return asignatura;
    }
    public boolean updateEstudiante(Integer codigo, String nombre) {
        boolean update = false;
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + name_table + " SET Nombre = '" + nombre + "' WHERE id_asignaturaEstudiante = " + codigo);
            update = true;
        }catch (Exception ex){
            ex.toString();
        }

        db.close();
        return update;
    }

}
