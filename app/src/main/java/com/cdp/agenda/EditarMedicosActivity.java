package com.cdp.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.agenda.db.DbMedicos;
import com.cdp.agenda.db.DbPacientes;
import com.cdp.agenda.entidades.Medicos;
import com.cdp.agenda.entidades.Pacientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarMedicosActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtCip;
    private EditText txtDni;
    private EditText txtFechaNacimiento;
    private EditText txtAnhosExperiencia;
    private EditText txtTelefono;
    private EditText txtCorreoElectronico;

    Button btnGuardar;

    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    private Medicos medico;

    int id = 0;

    @SuppressLint("RestrictedApi")
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
        btnGuardar = findViewById(R.id.btnGuardaMedico);
        fabEditar = findViewById(R.id.fabEditarMedico);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminarMedico);
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

        final DbMedicos dbMedicos = new DbMedicos(EditarMedicosActivity.this);
        medico = dbMedicos.verMedicoByID(id);

        if (medico != null) {
            txtNombre.setText(medico.getNombre());
            txtCip.setText(medico.getCip());
            txtDni.setText(medico.getDni());
            txtFechaNacimiento.setText(medico.getFechaNacimiento());
            txtAnhosExperiencia.setText(medico.getAnhosExperiencia());
            txtTelefono.setText(medico.getTelefono());
            txtCorreoElectronico.setText(medico.getCorreoElectronico());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {
                    correcto = dbMedicos.editarMedico(id, txtNombre.getText().toString(), txtCip.getText().toString(), txtDni.getText().toString(),
                            txtFechaNacimiento.getText().toString(), txtAnhosExperiencia.getText().toString(), txtTelefono.getText().toString(),
                            txtCorreoElectronico.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarMedicosActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarMedicosActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarMedicosActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, medicos_ver.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}