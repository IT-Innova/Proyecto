package com.cdp.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.adaptadores.ListaPacientesAdapter;
import com.cdp.agenda.db.DbPacientes;
import com.cdp.agenda.entidades.Pacientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListarPacientes extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaPacientes;
    ArrayList<Pacientes> listaArrayPacientes;
    ListaPacientesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaPacientes = findViewById(R.id.listaPacientes);
        listaPacientes.setLayoutManager(new LinearLayoutManager(this));

        DbPacientes dbPacientes = new DbPacientes(ListarPacientes.this);

        listaArrayPacientes = new ArrayList<>();

        adapter = new ListaPacientesAdapter(dbPacientes.mostrarPacientes());
        listaPacientes.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);
    }



    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}