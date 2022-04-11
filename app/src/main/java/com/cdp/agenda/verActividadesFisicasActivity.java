package com.cdp.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cdp.agenda.db.DbActividadFisica;
import com.cdp.agenda.entidades.ActividadFisica;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class verActividadesFisicasActivity extends AppCompatActivity {

    EditText txtNombreActividad, txtDistancia, txtFecha, txtTiempo;
    Button btnGuardaCorrer;
    FloatingActionButton fabEditar, fabEliminar;

    ActividadFisica actividadFisica;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_actividades);

        txtNombreActividad = findViewById(R.id.txtNombreActividad);
        txtDistancia = findViewById(R.id.txtDistancia);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtFecha = findViewById(R.id.txtFecha);

        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuardaCorrer = findViewById(R.id.btnGuardaCorrer);
        btnGuardaCorrer.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbActividadFisica dbActividadFisica = new DbActividadFisica(verActividadesFisicasActivity.this);
        actividadFisica = dbActividadFisica.verActividadFisica(id);

        if(actividadFisica != null){
            txtNombreActividad.setText(actividadFisica.getNombre());
            txtDistancia.setText(actividadFisica.getDistancia());
            txtTiempo.setText(actividadFisica.getTiempo());
            txtFecha.setText(actividadFisica.getFecha());


            txtNombreActividad.setInputType(InputType.TYPE_NULL);
            txtDistancia.setInputType(InputType.TYPE_NULL);
            txtTiempo.setInputType(InputType.TYPE_NULL);
            txtFecha.setInputType(InputType.TYPE_NULL);

        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(verActividadesFisicasActivity.this, EditarActividadesFisicasActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(verActividadesFisicasActivity.this);
                builder.setMessage("¿Desea eliminar esta actividad?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbActividadFisica.eliminarActividadFisica(id)){
                                    listaActividades();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void listaActividades(){
        Intent intent = new Intent(this, ListarActividadesFisicasActivity.class);
        startActivity(intent);
    }
}