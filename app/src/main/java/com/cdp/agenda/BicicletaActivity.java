package com.cdp.agenda;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.agenda.db.DbActividadFisica;

public class BicicletaActivity extends AppCompatActivity {

    EditText txtNombreActividad, txtDistancia, txtTiempo, txtFecha;
    Button btnGuardaCorrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        txtNombreActividad = findViewById(R.id.txtNombreActividad);
        txtDistancia = findViewById(R.id.txtDistancia);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtFecha = findViewById(R.id.txtFecha);

        btnGuardaCorrer = findViewById(R.id.btnGuardaCorrer);
        txtNombreActividad.setText("Bicicleta");
        txtNombreActividad.setInputType(InputType.TYPE_NULL);


        btnGuardaCorrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombreActividad.getText().toString().equals("") && !txtTiempo.getText().toString().equals("")) {

                    DbActividadFisica dbActividadFisica = new DbActividadFisica(BicicletaActivity.this);
                    long id = dbActividadFisica.insertarActividadFisica(txtNombreActividad.getText().toString(),txtDistancia.getText().toString(),
                            txtTiempo.getText().toString(), txtFecha.getText().toString());

                    if (id > 0) {
                        Toast.makeText(BicicletaActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(BicicletaActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(BicicletaActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void limpiar() {
        txtDistancia.setText("");
        txtTiempo.setText("");
        txtFecha.setText("");
    }

}