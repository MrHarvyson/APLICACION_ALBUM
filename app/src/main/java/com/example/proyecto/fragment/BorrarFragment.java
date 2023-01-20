package com.example.proyecto.fragment;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.proyecto.Animacion;
import com.example.proyecto.MainActivity;
import com.example.proyecto.MainInicio;
import com.example.proyecto.MainLogin;
import com.example.proyecto.R;
import com.example.proyecto.db.Db;

public class BorrarFragment extends Fragment {
    private Button borrar;
    private EditText titulo;
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
        titulo = (EditText) view.findViewById(R.id.editText_borrar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        borrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getString(R.string.notificacion_desea_eliminar) + " " + titulo.getText().toString() + " ?").setPositiveButton(getString(R.string.notificacion_si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Db.eliminarAlbum(getContext(),titulo.getText().toString())){
                            Toast.makeText(getContext(), getString(R.string.notificacion_borrado), Toast.LENGTH_SHORT).show();
                            titulo.setText(" ");
                            Intent intent = new Intent(getContext(), MainInicio.class);
                            startActivity(intent);
                        }else{
                            titulo.setText(" ");
                            Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

            }
        });
    }

}