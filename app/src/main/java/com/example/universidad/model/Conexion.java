package com.example.universidad.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion extends SQLiteOpenHelper {

    private static final int version_database = 1;
    private static final String name_database = "Universidad";
    public static final String name_table = "Estudiante";
    public static final String name_table_asignatura = "Asignatura";
    public static final String name_table_notas = "Notas";
    public static final String name_table_asignaturaxestudiante = "AsignaturaxEstudiante";

    public Conexion(@Nullable Context context) {
        super(context, name_database, null, version_database);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + name_table + "(" + "Documento INTEGER PRIMARY KEY," +
                "Name TEXT NOT NULL," +
                "Papellido TEXT NOT NULL," +
                "Sapellido TEXT NOT NULL," +
                "Correo TEXT NOT NULL," +
                "Telefono TEXT NOT NULL" + ")");

        sqLiteDatabase.execSQL("CREATE TABLE " + name_table_asignatura + "(" + "id_asignaturaEstudiante INTEGER PRIMARY KEY," +
                "Nombre TEXT NOT NULL" + ")");

        sqLiteDatabase.execSQL("CREATE TABLE " + name_table_asignaturaxestudiante + "(" + "id_asignaturaEstudiante INTEGER PRIMARY KEY," +
                "id_asignatura INTEGER," +
                "Documento INTEGER," +
                "foreign key (id_asignatura) references " + name_table_asignatura + "(id_asignatura)," +
                "foreign key (Documento) references " + name_table + "(Documento))");

        sqLiteDatabase.execSQL("CREATE TABLE " + name_table_notas + "(" + "id_notas INTEGER PRIMARY KEY," +
                "id_asignaturaEstudiante INTEGER," +
                "nota INTER NOT NULL," +
                "foreign key (id_asignaturaEstudiante) references " + name_table_asignaturaxestudiante + "(id_asignaturaEstudiante)) "
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + name_table);
        onCreate(sqLiteDatabase);
    }
}
