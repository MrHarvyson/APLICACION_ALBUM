package com.example.proyecto.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "libros.db";
    public static final String TABLA_LIBROS = "libros";
    public static final String TABLA_USUARIOS = "usuarios";

    public Db(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //crear tablas
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLA_USUARIOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, contrasena TEXT NOT NULL)");

    }

    //actualizar tablas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
