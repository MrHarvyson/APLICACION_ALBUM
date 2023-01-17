package com.example.proyecto.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.R;

public class AcercaFragment extends Fragment {

    private TextView descripcion;
    private RecyclerView lista;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Cargas la vista
        View view = inflater.inflate(R.layout.fragment_acerca_de, container, false);
//        lista = view.findViewById(R.id.recycler_view_lista);
//        lista.setHasFixedSize(true);
//        lista.setLayoutManager(new LinearLayoutManager(this.getActivity()));



        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_acerca, container, false);

        return view;
    }
}