package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.ActividadFisica;
import com.cdp.agenda.entidades.Pacientes;

import java.util.ArrayList;

public class DbActividadFisica extends DbHelper {

    Context context;

    public DbActividadFisica(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarActividadFisica(String nombre, String distancia,
                                        String tiempo, String fecha) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("distancia", distancia);
            values.put("tiempo", tiempo);
            values.put("fecha", fecha);


            id = db.insert(TABLE_ACTIVIDAD_FISICA, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<ActividadFisica> mostrarActividadFisica() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<ActividadFisica> listaActividadFisica = new ArrayList<>();
        ActividadFisica actividadFisica;
        Cursor cursorActividadFisica;

        cursorActividadFisica = db.rawQuery("SELECT * FROM " + TABLE_ACTIVIDAD_FISICA + " ORDER BY nombre ASC", null);

        if (cursorActividadFisica.moveToFirst()) {
            do {
                actividadFisica = new ActividadFisica();
                actividadFisica.setId(cursorActividadFisica.getInt(0));
                actividadFisica.setNombre(cursorActividadFisica.getString(1));
                actividadFisica.setDistancia(cursorActividadFisica.getString(2));
                actividadFisica.setTiempo(cursorActividadFisica.getString(3));
                actividadFisica.setFecha(cursorActividadFisica.getString(4));

                listaActividadFisica.add(actividadFisica);
            } while (cursorActividadFisica.moveToNext());
        }

        cursorActividadFisica.close();

        return listaActividadFisica;
    }

    public ActividadFisica verActividadFisica(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ActividadFisica actividadFisica = null;
        Cursor cursorActividadFisica;

        cursorActividadFisica = db.rawQuery("SELECT * FROM " + TABLE_ACTIVIDAD_FISICA + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorActividadFisica.moveToFirst()) {
            actividadFisica = new ActividadFisica();
            actividadFisica.setId(cursorActividadFisica.getInt(0));
            actividadFisica.setNombre(cursorActividadFisica.getString(1));
            actividadFisica.setDistancia(cursorActividadFisica.getString(2));
            actividadFisica.setTiempo(cursorActividadFisica.getString(3));
            actividadFisica.setFecha(cursorActividadFisica.getString(4));
        }

        cursorActividadFisica.close();

        return actividadFisica;
    }

    public boolean editarActividadFisica(int id, String nombre, String distancia,
                                         String tiempo, String fecha) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_ACTIVIDAD_FISICA + " SET nombre = '" + nombre + "', distancia = '" + distancia + "', tiempo = '" + tiempo + "', " +
                    "fecha = '" + fecha + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarActividadFisica(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_ACTIVIDAD_FISICA + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
