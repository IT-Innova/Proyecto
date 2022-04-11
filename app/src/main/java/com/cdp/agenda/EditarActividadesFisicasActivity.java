package com.cdp.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.agenda.db.DbActividadFisica;
import com.cdp.agenda.db.DbPacientes;
import com.cdp.agenda.entidades.ActividadFisica;
import com.cdp.agenda.entidades.Pacientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActividadesFisicasActivity extends AppCompatActivity {

    EditText txtNombreActividad, txtDistancia, txtFecha, txtTiempo;
    Button btnGuardaCorrer;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    ActividadFisica actividadFisica;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_actividades);

        txtNombreActividad = findViewById(R.id.txtNombreActividad);
        txtDistancia = findViewById(R.id.txtDistancia);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtFecha = findViewById(R.id.txtFecha);
        btnGuardaCorrer = findViewById(R.id.btnGuardaCorrer);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbActividadFisica dbActividadFisica = new DbActividadFisica(EditarActividadesFisicasActivity.this);
        actividadFisica = dbActividadFisica.verActividadFisica(id);

        if (actividadFisica != null) {
            txtNombreActividad.setText(actividadFisica.getNombre());
            txtDistancia.setText(actividadFisica.getDistancia());
            txtTiempo.setText(actividadFisica.getTiempo());
            txtFecha.setText(actividadFisica.getFecha());
        }

        btnGuardaCorrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtDistancia.getText().toString().equals("") && !txtTiempo.getText().toString().equals("")) {
                    correcto = dbActividadFisica.editarActividadFisica(id, txtNombreActividad.getText().toString(), txtDistancia.getText().toString(),
                            txtTiempo.getText().toString(), txtFecha.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActividadesFisicasActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActividadesFisicasActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActividadesFisicasActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, verActividadesFisicasActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}