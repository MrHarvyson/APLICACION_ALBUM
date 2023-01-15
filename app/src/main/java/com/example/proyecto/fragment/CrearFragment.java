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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.MainInicio;
import com.example.proyecto.R;
import com.example.proyecto.db.Db;

public class CrearFragment extends Fragment {

    private Button agregar;
    private EditText titulo, autor, discografica;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        titulo = (EditText) view.findViewById(R.id.textTitulo);
        autor =(EditText) view.findViewById(R.id.textAutor);
        discografica =(EditText) view.findViewById(R.id.textDiscografica);
        agregar = (Button) view.findViewById(R.id.btn_agregar);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        agregar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(!titulo.getText().toString().equals("") && !autor.getText().toString().equals("") && !discografica.getText().toString().equals("")){
                    if(Db.crearAlbum(getContext(), titulo.getText().toString(),autor.getText().toString(), discografica.getText().toString())){
                        Toast.makeText(getContext(), "HAY QUE PONER NOTIFICACION", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainInicio.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "ERROR BASE DE DATOS", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "COMPLETE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}