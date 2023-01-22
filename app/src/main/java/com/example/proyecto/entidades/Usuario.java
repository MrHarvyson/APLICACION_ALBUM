package com.example.proyecto.entidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.example.proyecto.db.Db;

public class Usuario {
    private static String nombre;
    private String contraseña;
    private static Bitmap fotoBitmap;
    private static Uri fotoUri;
    public static final String TABLA_USUARIOS = "usuario";

    // constructor para usuarios registrados en la aplicacion
    public Usuario(String nombre, Bitmap fotoBitmap) {
        this.nombre = nombre;
        this.fotoBitmap = fotoBitmap;
    }

    // constructor para usuarios logeados en google
    public Usuario(String nombre, Uri fotoUri) {
        this.nombre = nombre;
        this.fotoUri = fotoUri;
    }

    public static String getNombre() {
        return nombre;
    }

    public void setNombre(String nombres) {
        this.nombre = nombres;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public static Bitmap getFotoBitmap() {
        return fotoBitmap;
    }

    public void setFotoBitmap(Bitmap foto) {
        this.fotoBitmap = foto;
    }

    public static Uri getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(Uri fotoUri) {
        this.fotoUri = fotoUri;
    }

    public static Bitmap getFotoUsuario(Context context, String nombre) {
        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();

        Cursor cursor = dbData.rawQuery("select * from " + TABLA_USUARIOS + " where nombre = '" + nombre + "'", null);
        byte[] image = new byte[0];
        while (cursor.moveToNext()) {
            image = cursor.getBlob(3);
        }
        byte[] bytess = image;
        return BitmapFactory.decodeByteArray(bytess, 0, bytess.length);
    }

    // para tener usuario conectado en todas pantallas
    // usuario base de datos
    public static void crearusuario(String nombre, Bitmap fotoBitmap) {
        Usuario usuario = new Usuario(nombre, fotoBitmap);
    }

    // usuario google
    public static void crearusuario(String nombre, Uri fotoUri) {
        Usuario usuario = new Usuario(nombre, fotoUri);
    }


}

