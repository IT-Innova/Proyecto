package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cdp.agenda.adaptadores.ListaMedicosAdapter;
import com.cdp.agenda.db.DbMedicos;
import com.cdp.agenda.entidades.Medicos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class medicos_main extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscar;
    RecyclerView listaMedicos;
    ArrayList<Medicos> listaArrayMedicos;
    ListaMedicosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos_main);
        txtBuscar = findViewById(R.id.medicos_txtBuscar);
        listaMedicos = findViewById(R.id.listaMedicos);
        listaMedicos.setLayoutManager(new LinearLayoutManager(this));

        DbMedicos dbMedicos = new DbMedicos(medicos_main.this);

        listaArrayMedicos = new ArrayList<>();

        adapter = new ListaMedicosAdapter(dbMedicos.mostrarMedicos());
        listaMedicos.setAdapter(adapter);

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
