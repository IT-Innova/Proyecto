package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.Medicos;
import com.cdp.agenda.medicos_nuevo;

import java.util.ArrayList;

public class DbMedicos extends DbHelper{

    Context context;

    public DbMedicos(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long insertarMedico(String nombre, String cip, String dni, String fechaNacimiento, String anhosExperiencia,
                               String telefono, String correoElectronico){
        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("cip",cip);
            values.put("dni",dni);
            values.put("fechaNacimiento",fechaNacimiento);
            values.put("anhosExperiencia",anhosExperiencia);
            values.put("telefono",telefono);
            values.put("correoElectronico",correoElectronico);

            id = db.insert(TABLE_MEDICOS,null,values);
        } catch(Exception exception) {
            exception.toString();
        }


        return id;
    }

    public ArrayList<Medicos> mostrarMedicos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Medicos> listaMedicos = new ArrayList<>();
        Medicos medico = null;
        Cursor cursorMedicos;

        cursorMedicos = db.rawQuery("SELECT * FROM " + TABLE_MEDICOS + " ORDER BY nombre ASC",null);

        if (cursorMedicos.moveToFirst()){
            do {
                medico = new Medicos();
                medico.setId(cursorMedicos.getInt(0));
                medico.setNombre(cursorMedicos.getString(1));
                medico.setCip(cursorMedicos.getString(2));
                medico.setDni(cursorMedicos.getString(3));
                medico.setFechaNacimiento(cursorMedicos.getString(4));
                medico.setAnhosExperiencia(cursorMedicos.getString(5));
                medico.setTelefono(cursorMedicos.getString(6));
                medico.setCorreoElectronico(cursorMedicos.getString(7));

                listaMedicos.add(medico);
            } while (cursorMedicos.moveToNext());
        }

        cursorMedicos.close();

        return listaMedicos;
    }

    public Medicos verMedicoByID(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Medicos medico = null;
        Cursor cursorMedicos;

        cursorMedicos = db.rawQuery("SELECT * FROM " + TABLE_MEDICOS + " WHERE id = " + id + " LIMIT 1",null);

        if (cursorMedicos.moveToFirst()){
            medico = new Medicos();

            medico = new Medicos();
            medico.setId(cursorMedicos.getInt(0));
            medico.setNombre(cursorMedicos.getString(1));
            medico.setCip(cursorMedicos.getString(2));
            medico.setDni(cursorMedicos.getString(3));
            medico.setFechaNacimiento(cursorMedicos.getString(4));
            medico.setAnhosExperiencia(cursorMedicos.getString(5));
            medico.setTelefono(cursorMedicos.getString(6));
            medico.setCorreoElectronico(cursorMedicos.getString(7));
        }

        cursorMedicos.close();

        return medico;
    }

    public boolean editarMedico(int id,String nombre, String cip, String dni, String fechaNacimiento, String anhosExperiencia,
                                String telefono, String correoElectronico){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_MEDICOS + " SET nombre = '" + nombre + "', cip = '" + cip + "', dni = '" + dni +
                    "', fechaNacimiento = '" + fechaNacimiento + "', anhosExperiencia = '" + anhosExperiencia +
                    "', telefono = '" + telefono + "', correoElectronico = '" + correoElectronico + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarMedico(int id){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_MEDICOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return  correcto;
    }
}