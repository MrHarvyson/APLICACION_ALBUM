package com.example.proyecto.fragment;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
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
import com.example.proyecto.MainLogin;
import com.example.proyecto.R;
import com.example.proyecto.db.Db;

public class CrearFragment extends Fragment {

    private Button agregar;
    private TextView titulo, autor, editorial;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        titulo = (TextView) view.findViewById(R.id.textTitulo);
        autor =(TextView) view.findViewById(R.id.textAutor);
        editorial=(TextView) view.findViewById(R.id.textEditorial);
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

                Db.crearLibro(getContext(), titulo.getText().toString(),autor.getText().toString(),editorial.getText().toString());
                Toast.makeText(getContext(), "seiaaaaaa", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), MainInicio.class);
                startActivity(intent);

            }
        });
    }

}