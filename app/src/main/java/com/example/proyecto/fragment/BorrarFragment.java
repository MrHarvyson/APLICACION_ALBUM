package com.example.proyecto.fragment;

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
import android.widget.Toast;

import com.example.proyecto.MainActivity;
import com.example.proyecto.MainInicio;
import com.example.proyecto.MainLogin;
import com.example.proyecto.R;
import com.example.proyecto.db.Db;

public class BorrarFragment extends Fragment {
    private Button borrar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borrar, container, false);
        borrar = (Button) view.findViewById(R.id.btn_borrar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        borrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), MainInicio.class);
                startActivity(intent);

            }
        });
    }

}