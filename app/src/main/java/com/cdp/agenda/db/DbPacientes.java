package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.Pacientes;

import java.util.ArrayList;

public class DbPacientes extends DbHelper {

    Context context;

    public DbPacientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPaciente(String nombre, String dni, String fechaNacimiento, String direccion, String telefono, String correo_electronico,
                                 String nivelGlucosa, String descripcion) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("dni", dni);
            values.put("fechaNacimiento", fechaNacimiento);
            values.put("direccion", direccion);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);
            values.put("nivelGlucosa", nivelGlucosa);
            values.put("descripcion", descripcion);


            id = db.insert(TABLE_PACIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Pacientes> mostrarPacientes() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Pacientes> listaPacientes = new ArrayList<>();
        Pacientes paciente;
        Cursor cursorPacientes;

        cursorPacientes = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES + " ORDER BY nombre ASC", null);

        if (cursorPacientes.moveToFirst()) {
            do {
                paciente = new Pacientes();
                paciente.setId(cursorPacientes.getInt(0));
                paciente.setNombre(cursorPacientes.getString(1));
                paciente.setDni(cursorPacientes.getString(2));
                paciente.setFechaNacimiento(cursorPacientes.getString(3));
                paciente.setDireccion(cursorPacientes.getString(4));
                paciente.setTelefono(cursorPacientes.getString(5));
                paciente.setCorreo_electornico(cursorPacientes.getString(6));
                paciente.setNivelGlucosa(cursorPacientes.getString(7));
                paciente.setDescripcion(cursorPacientes.getString(8));

                listaPacientes.add(paciente);
            } while (cursorPacientes.moveToNext());
        }

        cursorPacientes.close();

        return listaPacientes;
    }

    public Pacientes verPacientes(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Pacientes paciente = null;
        Cursor cursorPacientes;

        cursorPacientes = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorPacientes.moveToFirst()) {
            paciente = new Pacientes();
            paciente.setId(cursorPacientes.getInt(0));
            paciente.setNombre(cursorPacientes.getString(1));
            paciente.setDni(cursorPacientes.getString(2));
            paciente.setFechaNacimiento(cursorPacientes.getString(3));
            paciente.setDireccion(cursorPacientes.getString(4));
            paciente.setTelefono(cursorPacientes.getString(5));
            paciente.setCorreo_electornico(cursorPacientes.getString(6));
            paciente.setNivelGlucosa(cursorPacientes.getString(7));
            paciente.setDescripcion(cursorPacientes.getString(8));
        }

        cursorPacientes.close();

        return paciente;
    }

    public boolean editarPaciente(int id, String nombre, String dni, String fechaNacimiento, String direccion, String telefono, String correo_electronico,
                                  String nivelGlucosa, String descripcion) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PACIENTES + " SET nombre = '" + nombre + "', dni = '" + dni + "', fechaNacimiento = '" + fechaNacimiento + "', direccion = '" + direccion + "', " +
                    "telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "', nivelGlucosa = '" + nivelGlucosa + "', descripcion = '" + descripcion + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarPaciente(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PACIENTES + " WHERE id = '" + id + "'");
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
