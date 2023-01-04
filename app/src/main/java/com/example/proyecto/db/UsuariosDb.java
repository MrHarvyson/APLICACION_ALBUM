package com.example.proyecto.db;

import android.content.Context;

import androidx.annotation.Nullable;

public class UsuariosDb extends Db{

    Context context;

    public UsuariosDb(@Nullable Context context) {
        super(context);
        this.context = context;
    }


}
