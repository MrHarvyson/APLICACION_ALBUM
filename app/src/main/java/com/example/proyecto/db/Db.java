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
        db.execSQL("CREATE TABLE " + TABLA_USUARIOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, contrasena TEXT NOT NULL, avatar BLOB)");
        db.execSQL("CREATE TABLE " + TABLA_ALBUMES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT NOT NULL, autor TEXT NOT NULL, discografica TEXT NOT NULL, portada BLOB)");
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
        } finally {
            dbData.close();
            db.close();
        }

        return correcto;
    }

    //consulta de usuario y contraseña
    public static boolean consultaEntrarUsuario(Context context, String nombre, String contrasena) {

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
                        correcto = true;
                    }
                } else {
                    System.out.println("CONTRASEÑAS DIFERENTES");
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL COMPROBAR USUARIO");
        } finally {
            fila.close();
            dbData.close();
            db.close();
        }

        return correcto;
    }

    // consulta si el usuario existe
    public static boolean consultaRegistrarUsuario(Context context, String nombre) {

        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        Cursor fila = dbData.rawQuery("select * from " + TABLA_USUARIOS + " where nombre = '" + nombre + "'", null);
        boolean correcto = false;

        try {
            if (fila.moveToFirst()) {
                String nomb = fila.getString(1);
                if (nomb.equals(nombre)) {
                    correcto = true;
                } else {
                    System.out.println("ERROR, NOMBRES IGUALES");
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL CONSULTAR REGISTRO USUARIO");
        } finally {
            fila.close();
            dbData.close();
            db.close();
        }
        return correcto;
    }

    // modificar nombre usuarios
    public static void modificarNombreUsuario(Context context, String nombreAntiguo, String nombreNuevo) {

        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        try{
            String up = ("UPDATE usuario set nombre= '" + nombreNuevo + "' where nombre= '" + nombreAntiguo + "'");
            dbData.execSQL(up);
        }catch (Exception ex){
            System.out.println("ERROR AL MODIFICAR NOMBRE USUARIO");
        }finally {
            dbData.close();
            db.close();
        }

    }

    // modificar contraseña usuarios
    public static void modificarContrasenaUsuario(Context context, String nombre, String contrasena) {
        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        try{
            String up = ("UPDATE usuario set contrasena= '" + contrasena + "' where nombre= '" + nombre + "'");
            dbData.execSQL(up);
        }catch (Exception ex){
            System.out.println("ERROR AL MODIFICAR CONTRASEÑA USUARIO");
        }finally {
            dbData.close();
            db.close();
        }
    }

    // eliminar usuario
    public static void eliminarUsuario(Context context, String nombre) {

        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        try{
            String eliminar = ("DELETE from usuario WHERE nombre='" + nombre + "'");
            dbData.execSQL(eliminar);
        }catch (Exception ex){
            System.out.println("ERROR AL ELIMINAR USUARIO");
        }finally {
            dbData.close();
            db.close();
        }

    }

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
                System.out.println("ALBUM EXISTE");
            }
        } catch (Exception ex) {
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
                System.out.println("NO EXISTE ALBUM");
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL ELIMINAR ALBUM");
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
            Cursor fila = dbData.rawQuery("SELECT titulo FROM " + TABLA_ALBUMES +
                    " WHERE titulo = '" + titulo + "'", null);
            if (fila.moveToFirst()) {
                titu = fila.getString(0);
                if (titu.equals(titulo)) {
                    correcto = true;
                } else {
                    System.out.println("ERROR CONSULTA");
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR CONSULTA ALBUM");
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

        try{
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
        }catch (Exception ex){
            System.out.println("ERROR CREAR LISTA ALBUM");
        }finally {
            dbData.close();
            db.close();
        }
        return listaAlbumes;
    }

    //actualizar tablas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
