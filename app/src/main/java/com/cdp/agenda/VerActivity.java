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

import com.cdp.agenda.db.DbPacientes;
import com.cdp.agenda.entidades.Pacientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtDni, txtFechaNacimiento, txtDireccion, txtTelefono, txtCorreo, txtNivelGlucosa, txtDescripcion;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Pacientes paciente;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtDni = findViewById(R.id.txtDni);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        txtNivelGlucosa = findViewById(R.id.txtNivelGlucosa);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuardaCorrer);
        btnGuarda.setVisibility(View.INVISIBLE);

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

        final DbPacientes dbPacientes = new DbPacientes(VerActivity.this);
        paciente = dbPacientes.verPacientes(id);

        if(paciente != null){
            txtNombre.setText(paciente.getNombre());
            txtDni.setText(paciente.getDni());
            txtFechaNacimiento.setText(paciente.getFechaNacimiento());
            txtDireccion.setText(paciente.getDireccion());
            txtTelefono.setText(paciente.getTelefono());
            txtCorreo.setText(paciente.getCorreo_electornico());
            txtNivelGlucosa.setText(paciente.getNivelGlucosa());
            txtDescripcion.setText(paciente.getDescripcion());

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtDni.setInputType(InputType.TYPE_NULL);
            txtFechaNacimiento.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
            txtNivelGlucosa.setInputType(InputType.TYPE_NULL);
            txtDescripcion.setInputType(InputType.TYPE_NULL);

        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbPacientes.eliminarPaciente(id)){
                                    lista();
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

    private void lista(){
        Intent intent = new Intent(this, ListarPacientes.class);
        startActivity(intent);
    }
}