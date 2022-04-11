package com.cdp.agenda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "glucomed.db";
    public static final String TABLE_PACIENTES = "t_pacientes";
    public static final String TABLE_ACTIVIDAD_FISICA = "t_actividad_fisica";
    public static final String TABLE_MEDICOS = "t_medicos";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ACTIVIDAD_FISICA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "distancia TEXT NOT NULL," +
                "tiempo TEXT NOT NULL," +
                "fecha TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PACIENTES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "dni TEXT NOT NULL," +
                "fechaNacimiento TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo_electronico TEXT NOT NULL," +
                "nivelGlucosa TEXT NOT NULL," +
                "descripcion TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MEDICOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "cip TEXT NOT NULL," +
                "dni TEXT NOT NULL," +
                "fechaNacimiento TEXT NOT NULL," +
                "anhosExperiencia TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correoElectronico TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PACIENTES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ACTIVIDAD_FISICA);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MEDICOS);

        onCreate(sqLiteDatabase);

    }
}