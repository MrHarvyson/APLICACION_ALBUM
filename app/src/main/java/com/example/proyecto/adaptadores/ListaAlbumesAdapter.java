package com.example.proyecto.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.entidades.Albumes;

import java.util.ArrayList;

public class ListaAlbumesAdapter extends RecyclerView.Adapter<ListaAlbumesAdapter.ListaViewHolder> {

    ArrayList<Albumes> listaAlbumes;

    public ListaAlbumesAdapter(ArrayList<Albumes> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_album, null, false);
        return new ListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {

        holder.viewTitulo.setText(listaAlbumes.get(position).getTitulo());
        holder.viewAutor.setText(listaAlbumes.get(position).getAutor());
        holder.viewDiscografica.setText(listaAlbumes.get(position).getDiscografica());

    }

    @Override
    public int getItemCount() {
        return listaAlbumes.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder {

        TextView viewTitulo, viewAutor, viewDiscografica;

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewTitulo = itemView.findViewById(R.id.viewTitulo);
            viewAutor = itemView.findViewById(R.id.viewAutor);
            viewDiscografica = itemView.findViewById(R.id.viewDiscografica);
        }
    }

}
