package com.example.proyecto;

public class Usuario {
    private static String nombre;
    private String contraseña;



    public Usuario() {
    }
    public Usuario(String nombre) {
        this.nombre = nombre;
    }
    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre() {
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

    public static String getUsuario(){
        return nombre;
    }
}

