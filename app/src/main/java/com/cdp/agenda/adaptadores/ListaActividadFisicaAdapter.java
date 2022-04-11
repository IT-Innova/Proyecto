package com.cdp.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.R;
import com.cdp.agenda.verActividadesFisicasActivity;
import com.cdp.agenda.entidades.ActividadFisica;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaActividadFisicaAdapter extends RecyclerView.Adapter<ListaActividadFisicaAdapter.ContactoViewHolder> {

    ArrayList<ActividadFisica> listaActividadFisica;
    ArrayList<ActividadFisica> listaOriginal;

    public ListaActividadFisicaAdapter(ArrayList<ActividadFisica> listaActividadFisica) {
        this.listaActividadFisica = listaActividadFisica;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaActividadFisica);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_actividad, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewNombreActividad.setText(listaActividadFisica.get(position).getNombre());
        holder.viewDistancia.setText(listaActividadFisica.get(position).getDistancia());
        holder.viewFecha.setText(listaActividadFisica.get(position).getFecha());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaActividadFisica.clear();
            listaActividadFisica.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<ActividadFisica> collecion = listaActividadFisica.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaActividadFisica.clear();
                listaActividadFisica.addAll(collecion);
            } else {
                for (ActividadFisica c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaActividadFisica.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaActividadFisica.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreActividad, viewDistancia, viewFecha;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombreActividad = itemView.findViewById(R.id.viewNombreActividad);
            viewDistancia = itemView.findViewById(R.id.viewDistancia);
            viewFecha = itemView.findViewById(R.id.viewFecha);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, verActividadesFisicasActivity.class);
                    intent.putExtra("ID", listaActividadFisica.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
