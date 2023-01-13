package com.example.proyecto.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.MainInicio;
import com.example.proyecto.R;
import com.example.proyecto.db.Db;

public class CrearFragment extends Fragment {

    private Button agregar;
    private TextView titulo, autor, discografica;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        titulo = (TextView) view.findViewById(R.id.textTitulo);
        autor =(TextView) view.findViewById(R.id.textAutor);
        discografica =(TextView) view.findViewById(R.id.textDiscografica);
        agregar = (Button) view.findViewById(R.id.btn_Agregar);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        agregar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Db.crearAlbum(getContext(), titulo.getText().toString(),autor.getText().toString(), discografica.getText().toString());
                Toast.makeText(getContext(), "seiaaaaaa", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), MainInicio.class);
                startActivity(intent);

            }
        });
    }

}