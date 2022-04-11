package com.cdp.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cdp.agenda.db.DbMedicos;
import com.cdp.agenda.entidades.Medicos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class medicos_ver extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtCip;
    private EditText txtDni;
    private EditText txtFechaNacimiento;
    private EditText txtAnhosExperiencia;
    private EditText txtTelefono;
    private EditText txtCorreoElectronico;

    private Button btnGuardar;

    private FloatingActionButton fabEditar;
    private FloatingActionButton fabEliminar;

    private Medicos medico;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos_ver);

        txtNombre = findViewById(R.id.txtNombreMedico);
        txtCip = findViewById(R.id.txtCipMedico);
        txtDni = findViewById(R.id.txtDniMedico);
        txtFechaNacimiento = findViewById(R.id.txtNacimientoMedico);
        txtAnhosExperiencia = findViewById(R.id.txtExperienciaMedico);
        txtTelefono = findViewById(R.id.txtTelefonoMedico);
        txtCorreoElectronico = findViewById(R.id.txtCorreoMedico);
        fabEditar = findViewById(R.id.fabEditarMedico);
        fabEliminar = findViewById(R.id.fabEliminarMedico);
        btnGuardar = findViewById(R.id.btnGuardaMedico);
        btnGuardar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbMedicos dbMedicos = new DbMedicos(medicos_ver.this);
        medico = dbMedicos.verMedicoByID(id);

        if (medico != null){
            txtNombre.setText(medico.getNombre());
            txtCip.setText(medico.getCip());
            txtDni.setText(medico.getDni());
            txtFechaNacimiento.setText(medico.getFechaNacimiento());
            txtAnhosExperiencia.setText(medico.getAnhosExperiencia());
            txtTelefono.setText(medico.getTelefono());
            txtCorreoElectronico.setText(medico.getCorreoElectronico());

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtCip.setInputType(InputType.TYPE_NULL);
            txtDni.setInputType(InputType.TYPE_NULL);
            txtFechaNacimiento.setInputType(InputType.TYPE_NULL);
            txtAnhosExperiencia.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreoElectronico.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(medicos_ver.this,EditarMedicosActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);

            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(medicos_ver.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dbMedicos.eliminarMedico(id)){
                                    returnMain();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }

    private void returnMain(){
        Intent intent = new Intent(this, medicos_main.class);
        startActivity(intent);
    }
}