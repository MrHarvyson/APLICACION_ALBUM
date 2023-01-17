package com.example.proyecto;

import android.widget.ImageView;

import com.example.proyecto.db.Db;

public class Usuario {
    private static String nombre;
    private String contraseña;
    private ImageView foto;



    public Usuario() {
    }
    public Usuario(String nombre) {
        this.nombre = nombre;
    }
    public Usuario(String nombre, ImageView foto) {
        this.nombre = nombre;
        this.foto = foto;
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

    public static void crearusuario(String nombre){
        Usuario usuario = new Usuario(nombre);
    }

}

