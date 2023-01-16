package com.example.proyecto.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.R;

public class AcercaFragment extends Fragment {

    private TextView descripcion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        botonLista = getView().findViewById(R.id.btn_acerca_lista);
        botonAgregar = getView().findViewById(R.id.btn_acerca_agregar);
        botonBorrar = getView().findViewById(R.id.btn_acerca_borrar);
        botonVersion = getView().findViewById(R.id.btn_acerca_version);
        botonInfo = getView().findViewById(R.id.btn_acerca_info);

        descripcion = getView().findViewById(R.id.textView_acerca_descr);

        botonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje; //= getString(R.string.hello);
                descripcion.setText(mensaje = getString(R.string.texto_acerca_lista));
            }
        });
        */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Cargas la vista
        View view = inflater.inflate(R.layout.fragment_acerca, container, false);
        // A partir de la vista mandas a traer los botones correspondientes.
        descripcion = view.findViewById(R.id.textView_acerca_descr);
        //Esta lógica se replica para los demas botones.

        return view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_acerca, container, false);
    }
}