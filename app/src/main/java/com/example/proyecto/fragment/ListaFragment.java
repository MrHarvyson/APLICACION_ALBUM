package com.example.proyecto.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.R;
import com.example.proyecto.adaptadores.ListaAlbumesAdapter;
import com.example.proyecto.db.Db;
import com.example.proyecto.entidades.Albumes;

import java.util.ArrayList;

public class ListaFragment extends Fragment {

    private RecyclerView listaAlbumes;
    private ArrayList<Albumes> listaArrayAlbumes;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        listaArrayAlbumes = new ArrayList<>();
        listaAlbumes = (RecyclerView) view.findViewById(R.id.lista_albumes);
        listaAlbumes.setLayoutManager(new LinearLayoutManager(getContext()));
        Db db = new Db(getContext());
        ListaAlbumesAdapter adapter = new ListaAlbumesAdapter(db.mostrarAlbumes(getContext()));
        listaAlbumes.setAdapter(adapter);



        return view;

    }


}