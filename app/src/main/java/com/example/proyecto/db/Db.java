package com.example.proyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyecto.entidades.Albumes;

import java.util.ArrayList;

public class Db extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "albumes.db";
    public static final String TABLA_ALBUMES = "album";
    public static final String TABLA_USUARIOS = "usuario";

    public Db(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //crear tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_USUARIOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, contrasena TEXT NOT NULL, avatar BLOB)");
        db.execSQL("CREATE TABLE " + TABLA_ALBUMES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, autor TEXT NOT NULL, discografica TEXT NOT NULL, portada BLOB)");
    }

    //crear usuarios
    public static boolean crearUsuario(Context context, String nombre, String contrasena, byte[] avatar) {
        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        boolean correcto = false;

        try {
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("contrasena", contrasena);
            values.put("avatar", avatar);
            dbData.insert(TABLA_USUARIOS, null, values);
            correcto = true;
        } catch (Exception ex) {
            System.out.println("ERROR CREAR USUARIO");
            correcto = false;
        } finally {
            dbData.close();
            db.close();
        }

        return correcto;
    }

    //consulta de usuario
    public static boolean consultaUsuario(Context context, String nombre, String contrasena) {

        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        Cursor fila = dbData.rawQuery("select * from " + TABLA_USUARIOS + " where nombre = '" + nombre + "'", null);
        boolean correcto = false;

        try {
            if (fila.moveToFirst()) {
                String nomb = fila.getString(1);
                String contra = fila.getString(2);
                if (nomb.equals(nombre)) {
                    if (contra.equals(contrasena)) {
                        System.out.println("Contraseñas idénticas");
                        correcto = true;
                    }
                } else {
                    correcto = false;
                    System.out.println("Contraseñas diferentes");
                }
            }
        } catch (Exception ex) {
            correcto = false;
        } finally {
            fila.close();
            dbData.close();
            db.close();
        }

        return correcto;
    }

    // eliminar usuarios

    //crear albumes
    public static boolean crearAlbum(Context context, String titulo, String autor, String discografica, byte[] portada) {
        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        boolean correcto = false;

        try {
            if (!consultaAlbum(context, titulo)) {
                ContentValues values = new ContentValues();
                values.put("titulo", titulo);
                values.put("autor", autor);
                values.put("discografica", discografica);
                values.put("portada", portada);

                dbData.insert(TABLA_ALBUMES, null, values);
                correcto = true;
            } else {
                correcto = false;
            }
        } catch (Exception ex) {
            correcto = false;
            System.out.println("ERROR CREAR ÁLBUMES");
        } finally {
            dbData.close();
            db.close();
        }
        return correcto;
    }

    // eliminar album
    public static boolean eliminarAlbum(Context context, String titulo) {
        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        boolean correcto = false;

        try {
            if (consultaAlbum(context, titulo)) {
                dbData.execSQL("DELETE FROM " + TABLA_ALBUMES + " WHERE titulo = '" + titulo + "'");
                correcto = true;
            } else {
                correcto = false;
            }
        } catch (Exception ex) {
            correcto = false;
        } finally {
            dbData.close();
            db.close();
        }

        return correcto;
    }

    // consulta album

    public static boolean consultaAlbum(Context context, String titulo) {

        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        boolean correcto = false;
        String titu;

        try {
            Cursor fila = dbData.rawQuery("SELECT titulo FROM " + TABLA_ALBUMES + " WHERE titulo = '" + titulo + "'", null);
            if (fila.moveToFirst()) {
                titu = fila.getString(0);
                if (titu.equals(titulo)) {
                    correcto = true;
                } else {
                    correcto = false;
                }
            }
        } catch (Exception ex) {
            correcto = false;
        } finally {
            dbData.close();
            db.close();
        }
        return correcto;
    }

    //creamos una lista que guarde todos los álbumes que tenemos en la base de datos

    public ArrayList<Albumes> mostrarAlbumes(Context context) {

        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();

        ArrayList<Albumes> listaAlbumes = new ArrayList<>();
        Albumes album = null;
        Cursor cursorAlbumes = null;

        cursorAlbumes = dbData.rawQuery("SELECT * FROM " + TABLA_ALBUMES, null);

        if (cursorAlbumes.moveToFirst()) {
            do {
                album = new Albumes();
                //album.setId(cursorAlbumes.getInt(0)); //no hace falta este dato por ahora
                album.setTitulo(cursorAlbumes.getString(1));
                album.setAutor(cursorAlbumes.getString(2));
                album.setDiscografica(cursorAlbumes.getString(3));
                album.setPortada(cursorAlbumes.getBlob(4));
                listaAlbumes.add(album);
            } while (cursorAlbumes.moveToNext());
        }

        cursorAlbumes.close();
        dbData.close();
        db.close();
        return listaAlbumes;
    }

    //actualizar tablas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
