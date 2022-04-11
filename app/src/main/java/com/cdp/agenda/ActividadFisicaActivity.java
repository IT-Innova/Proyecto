package com.cdp.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadFisicaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    Button btnCorrer, btnCaminar, btnBicicleta, btnOtro, btnVerActividadesFisicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_fisica);
        btnCorrer = findViewById(R.id.btnCorrer);
        btnCaminar = findViewById(R.id.btnCaminar);
        btnBicicleta = findViewById(R.id.btnBicicleta);
        btnOtro = findViewById(R.id.btnOtro);
        btnVerActividadesFisicas = findViewById(R.id.btnVerActividadesFisicas);


        btnCorrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarCorrer();
            }
        });

        btnBicicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarBicicleta();
            }
        });
        btnCaminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarCaminar();
            }
        });
        btnOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarOtro();
            }
        });

        btnVerActividadesFisicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verActividades();
            }
        });



    }

    //Mostramos menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    private void agregarCorrer(){
        Intent intent = new Intent(this, CorrerActivity.class);
        startActivity(intent);
    }
    private void agregarBicicleta(){
        Intent intent = new Intent(this, BicicletaActivity.class);
        startActivity(intent);
    }
    private void agregarCaminar(){
        Intent intent = new Intent(this, CaminarActivity.class);
        startActivity(intent);
    }
    private void agregarOtro(){
        Intent intent = new Intent(this, OtraActividadActivity.class);
        startActivity(intent);
    }
    private void verActividades(){
        Intent intent = new Intent(this, ListarActividadesFisicasActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}