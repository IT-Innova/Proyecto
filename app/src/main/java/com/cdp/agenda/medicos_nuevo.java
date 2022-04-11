package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.agenda.db.DbMedicos;

public class medicos_nuevo extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtCip;
    private EditText txtDni;
    private EditText txtFechaNacimiento;
    private EditText txtAnhosExperiencia;
    private EditText txtTelefono;
    private EditText txtCorreoElectronico;

    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos_nuevo);

        txtNombre = findViewById(R.id.txtNombreMedico);
        txtCip = findViewById(R.id.txtCipMedico);
        txtDni = findViewById(R.id.txtDniMedico);
        txtFechaNacimiento = findViewById(R.id.txtNacimientoMedico);
        txtAnhosExperiencia = findViewById(R.id.txtExperienciaMedico);
        txtTelefono = findViewById(R.id.txtTelefonoMedico);
        txtCorreoElectronico = findViewById(R.id.txtCorreoMedico);

        btnGuardar = findViewById(R.id.btnGuardaMedico);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().equals("") && !txtCip.getText().toString().equals("") &&
                    !txtDni.getText().toString().equals("") && !txtFechaNacimiento.getText().toString().equals("") &&
                    !txtAnhosExperiencia.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") &&
                    !txtCorreoElectronico.getText().toString().equals("")){

                    DbMedicos dbMedicos = new DbMedicos(medicos_nuevo.this);
                    long id = dbMedicos.insertarMedico(txtNombre.getText().toString(),txtCip.getText().toString(),
                                                        txtDni.getText().toString(),txtFechaNacimiento.getText().toString(),
                                                        txtAnhosExperiencia.getText().toString(),txtTelefono.getText().toString(),
                                                        txtCorreoElectronico.getText().toString());

                    if (id > 0) {
                        Toast.makeText(medicos_nuevo.this,"REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(medicos_nuevo.this,"ERROR AL GUARDAR REGISTRO",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(medicos_nuevo.this,"DEBE LLENAR TODOS LOS CAMPOS",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private  void limpiar(){
        txtNombre.setText("");
        txtCip.setText("");
        txtDni.setText("");
        txtFechaNacimiento.setText("");
        txtAnhosExperiencia.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}