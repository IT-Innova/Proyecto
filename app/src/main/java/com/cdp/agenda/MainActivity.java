package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.cdp.agenda.adaptadores.ListaPacientesAdapter;
import com.cdp.agenda.entidades.Pacientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Button btnIngresar, btnRegistrar, btnVerPacientes, btnRegistrarMedico, btnVerMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVerPacientes = findViewById(R.id.btnVerPacientes);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrarMedico = findViewById(R.id.btnRegistrarMedico);
        btnVerMedicos = findViewById(R.id.btnVerMedicos);



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });
        btnRegistrarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoMedicoRegistro();
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresarPerfil();
            }
        });

        btnVerPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verPacientes();
            }
        });
        btnVerMedicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verMedicos();
            }
        });

    }

    //Mostramos menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
    private void nuevoMedicoRegistro(){
        Intent intent = new Intent(this, medicos_nuevo.class);
        startActivity(intent);
    }
    private void ingresarPerfil(){
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }
    private void verPacientes(){
        Intent intent = new Intent(this, ListarPacientes.class);
        startActivity(intent);
    }
    private void verMedicos(){
        Intent intent = new Intent(this, medicos_main.class);
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