package com.cdp.agenda;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.adaptadores.ListaActividadFisicaAdapter;
import com.cdp.agenda.adaptadores.ListaPacientesAdapter;
import com.cdp.agenda.db.DbActividadFisica;
import com.cdp.agenda.db.DbPacientes;
import com.cdp.agenda.entidades.ActividadFisica;
import com.cdp.agenda.entidades.Pacientes;

import java.util.ArrayList;

public class ListarActividadesFisicasActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaActividadFisica;
    ArrayList<ActividadFisica> listaArrayActividadFisica;
    ListaActividadFisicaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_actividad);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaActividadFisica = findViewById(R.id.listaActividadFisica);
        listaActividadFisica.setLayoutManager(new LinearLayoutManager(this));

        DbActividadFisica dbActividadFisica = new DbActividadFisica(ListarActividadesFisicasActivity.this);

        listaArrayActividadFisica = new ArrayList<>();

        adapter = new ListaActividadFisicaAdapter(dbActividadFisica.mostrarActividadFisica());
        listaActividadFisica.setAdapter(adapter);



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