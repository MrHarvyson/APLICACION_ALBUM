package com.example.proyecto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import com.example.proyecto.db.Db;

public class Usuario {
    private static String nombre;
    private String contraseña;
    private static Bitmap fotoBitmap;
    private static Uri fotoUri;
    public static final String TABLA_USUARIOS = "usuario";

    public Usuario() {
    }
    public Usuario(String nombre) {
        this.nombre = nombre;
    }
    public Usuario(String nombre, Bitmap fotoBitmap) {
        this.nombre = nombre;
        this.fotoBitmap = fotoBitmap;
    }
    public Usuario(String nombre, Uri fotoUri){
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
        ImageView foto = null;
        Bitmap bitmap = null;

        Cursor cursor = dbData.rawQuery("select * from " + TABLA_USUARIOS + " where nombre = '" + nombre + "'", null);
        boolean correcto = false;
        byte[] image = new byte[0];
        while (cursor.moveToNext()) {
            image = cursor.getBlob(3);
        }
        byte[] bytess = image;
        bitmap = BitmapFactory.decodeByteArray(bytess,0,bytess.length);
        return bitmap;
    }

    public static void crearusuario(String nombre){
        Usuario usuario = new Usuario(nombre);
    }

    // para tener usuario conectado en todas pantallas
    public static void crearusuario(String nombre, Bitmap fotoBitmap){
        Usuario usuario = new Usuario(nombre,fotoBitmap);
    }

    public static void crearusuario(String nombre, Uri fotoUri){
        Usuario usuario = new Usuario(nombre, fotoUri);
    }


}

