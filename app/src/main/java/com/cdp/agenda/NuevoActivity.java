package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.agenda.db.DbPacientes;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtDni, txtFechaNacimiento, txtDireccion, txtTelefono, txtCorreoElectronico, txtNivelGlucosa, txtDescripcion;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtDni = findViewById(R.id.txtDni);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtNivelGlucosa = findViewById(R.id.txtNivelGlucosa);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        btnGuarda = findViewById(R.id.btnGuardaCorrer);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {

                    DbPacientes dbPacientes = new DbPacientes(NuevoActivity.this);
                    long id = dbPacientes.insertarPaciente(txtNombre.getText().toString(),txtDni.getText().toString(),txtFechaNacimiento.getText().toString(), txtDireccion.getText().toString(),
                            txtTelefono.getText().toString(), txtCorreoElectronico.getText().toString(), txtNivelGlucosa.getText().toString(),txtDescripcion.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtDni.setText("");
        txtFechaNacimiento.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
        txtNivelGlucosa.setText("");
        txtDescripcion.setText("");

    }
}