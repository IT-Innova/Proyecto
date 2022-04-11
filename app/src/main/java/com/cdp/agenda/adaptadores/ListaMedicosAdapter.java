package com.cdp.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.R;
import com.cdp.agenda.medicos_ver;
import com.cdp.agenda.entidades.Medicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListaMedicosAdapter extends RecyclerView.Adapter<ListaMedicosAdapter.ContactoViewHolder> {

    ArrayList<Medicos> listaMedicos;
    ArrayList<Medicos> listaOriginal;

    public ListaMedicosAdapter(ArrayList<Medicos> listaMedicos){
        this.listaMedicos = listaMedicos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaMedicos);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_medico,null,false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder,int position){
        holder.viewNombre.setText(listaMedicos.get(position).getNombre());
        holder.viewTelefono.setText(listaMedicos.get(position).getTelefono());
        holder.viewCorreo.setText(listaMedicos.get(position).getCorreoElectronico());
    }

    public void filtrado(final String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud == 0){
            listaMedicos.clear();
            listaMedicos.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                List<Medicos> collecion = listaMedicos.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaMedicos.clear();
                listaMedicos.addAll(collecion);
            } else {
                for (Medicos m : listaOriginal){
                    if (m.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaMedicos.add(m);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return listaMedicos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewCorreo;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombreMedico);
            viewTelefono = itemView.findViewById(R.id.viewTelefonoMedico);
            viewCorreo = itemView.findViewById(R.id.viewCorreoMedico);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, medicos_ver.class);
                    intent.putExtra("ID", listaMedicos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}