package com.cdp.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    Button btnActividadFisica, btnAlimentacion, btnReportes, btnRecomendaciones, btnComunicarme, btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btnActividadFisica = findViewById(R.id.btnActividadFisica);


        btnActividadFisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actividadFisica();
            }
        });
    }

    //Mostramos menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    private void actividadFisica(){
        Intent intent = new Intent(this, ActividadFisicaActivity.class);
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