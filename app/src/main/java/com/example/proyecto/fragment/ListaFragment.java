package com.example.proyecto.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.MainInicio;
import com.example.proyecto.R;
import com.example.proyecto.adaptadores.ListaLibrosAdapter;
import com.example.proyecto.db.Db;
import com.example.proyecto.entidades.Libros;

import java.util.ArrayList;

public class ListaFragment extends Fragment {

    RecyclerView listaLibros;
    ArrayList<Libros> listaArrayLibros;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_lista, container, false);
        listaArrayLibros = new ArrayList<>();

        listaLibros = (RecyclerView) view.findViewById(R.id.lista_libros);

        listaLibros.setLayoutManager(new LinearLayoutManager(getContext()));

        Db db = new Db(getContext());

        ListaLibrosAdapter adapter = new ListaLibrosAdapter(db.mostrarLibros(getContext()));
        listaLibros.setAdapter(adapter);



        return view;

    }



}