package com.example.proyecto.adaptadores;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.entidades.Libros;

import java.util.ArrayList;

public class ListaLibrosAdapter extends RecyclerView.Adapter<ListaLibrosAdapter.ListaViewHolder> {

    ArrayList<Libros> listaLibros;

    public ListaLibrosAdapter(ArrayList<Libros> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_libro, null, false);
        return new ListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {

        holder.viewTitulo.setText(listaLibros.get(position).getTitulo());
        holder.viewAutor.setText(listaLibros.get(position).getAutor());
        holder.viewEditorial.setText(listaLibros.get(position).getEditorial());

    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder {

        TextView viewTitulo, viewAutor, viewEditorial;

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewTitulo = itemView.findViewById(R.id.viewTitulo);
            viewAutor = itemView.findViewById(R.id.viewAutor);
            viewEditorial = itemView.findViewById(R.id.viewEditorial);
        }
    }

}
