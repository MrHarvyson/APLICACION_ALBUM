package com.example.proyecto.entidades;

public class Albumes {
    private int id;
    private String titulo;
    private String autor;
    private String discografica;
    private byte[] portada;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getDiscografica() {
        return discografica;
    }
    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }
    public byte[] getPortada() {
        return portada;
    }
    public void setPortada(byte[] portada) {
        this.portada = portada;
    }
}
