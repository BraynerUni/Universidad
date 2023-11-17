package com.example.universidad.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbEstudiante extends Conexion {

    Context context;

    public DbEstudiante(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEstudiante(Integer cc, String nombre, String papellido, String sapellido, String correo, String telefono) {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("Documento", cc);
        value.put("Name", nombre);
        value.put("Papellido", papellido);
        value.put("Sapellido", sapellido);
        value.put("Correo", correo);
        value.put("Telefono", telefono);

        long id = db.insert(name_table, null, value);
        db.close();
        return id;
    }

    public ArrayList<MoEstudiante> mostrarEstudiante() {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ArrayList<MoEstudiante> list = new ArrayList<>();
        MoEstudiante estudiante = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + name_table, null);
        if (cursor.moveToFirst()) {
            do {
                estudiante = new MoEstudiante();
                estudiante.setDocumento(cursor.getInt(0));
                estudiante.setNombre(cursor.getString(1));
                estudiante.setPapellido(cursor.getString(2));
                estudiante.setSapellido(cursor.getString(3));
                estudiante.setCorreo(cursor.getString(4));
                estudiante.setTelefonno(cursor.getString(5));
                list.add(estudiante);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public MoEstudiante buscarEstudiante(int doc) {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();

        MoEstudiante estudiante = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + name_table + " WHERE Documento = " + doc, null);
        if (cursor.moveToFirst()) {
            estudiante = new MoEstudiante();
            estudiante.setDocumento(cursor.getInt(0));
            estudiante.setNombre(cursor.getString(1));
            estudiante.setPapellido(cursor.getString(2));
            estudiante.setSapellido(cursor.getString(3));
            estudiante.setCorreo(cursor.getString(4));
            estudiante.setTelefonno(cursor.getString(5));
        }

        cursor.close();
        return estudiante;
    }

    public boolean eliminarEstudiante(int doc) {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();
        boolean eliminar = false;
        try {
            db.execSQL("DELETE FROM " + name_table + " WHERE Documento = " + doc, null);
            eliminar = true;
        }catch (Exception ex){
            ex.toString();
        }
        db.close();
        return eliminar;
    }

    public boolean updateEstudiante(Integer cc, String nombre, String papellido, String sapellido, String correo, String telefono) {
        boolean update = false;
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + name_table + " SET Name = '" + nombre + "', Papellido = '" + papellido + "', Sapellido = '" + sapellido + "', Correo = '" + correo + "', Telefono = '" + telefono + "' " + " WHERE Documento = " + cc);
            update = true;
        }catch (Exception ex){
            ex.toString();
        }

        db.close();
        return update;
    }
}
