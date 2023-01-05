package com.example.proyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.proyecto.MainActivity;
import com.example.proyecto.MainLogin;

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

    public static void crearBd(Context context) {
        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        if (dbData != null) {
            Toast.makeText(context, "Base de datos creada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Base de datos no creada", Toast.LENGTH_SHORT).show();
        }
    }


    //crear usuarios
    public static long crearUsuario(Context context, String nombre, String contrasena) {
        long id = 0;
        try {
            Db db = new Db(context);
            SQLiteDatabase dbData = db.getWritableDatabase();
            //Usuario usuario = new Usuario(nombre, contrasena);

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("contrasena", contrasena);

            id = dbData.insert(TABLA_USUARIOS, null, values);

        } catch (Exception ex) {
            System.out.println("ERRRORR");
        }
        return id;
    }

    //consulta de usuario
    public static boolean consultaUsuario(Context context, String nombre, String contrasena) {

        Db db = new Db(context);
        boolean ok = false;

        SQLiteDatabase dbData = db.getWritableDatabase();
        Cursor fila = dbData.rawQuery("select * from usuarios where nombre = '" + nombre + "'", null);

        if(fila.moveToFirst()){
            String nomb = fila.getString(1);
            String contra = fila.getString(2);
            //divido este if para que me sirva comprobar el registro si existe con solo el nombre
            if (nomb.equals(nombre) ) {
                if (contra.equals(contrasena)){
                    System.out.println("correcto");
                    ok = true;
                }
            } else {
                ok = false;
                System.out.println("Incorrecto");
            }
        }

        /* Este metodo es mas util para query con varios resultados
        while (fila.moveToNext()) {
            String nomb = fila.getString(1);
            String contra = fila.getString(2);
            //divido este if para que me sirva comprobar el registro si existe con solo el nombre
            if (nomb.equals(nombre) ) {
                if (contra.equals(contrasena)){
                    System.out.println("correcto");
                    ok = true;
                }
            } else {
                ok = false;
                System.out.println("Incorrecto");
            }
        }
        */

        fila.close();
        return ok;
    }

    public static boolean consultaRegistro(Context context, String nombre) {

        Db db = new Db(context);
        boolean ok = false;

        SQLiteDatabase dbData = db.getWritableDatabase();
        Cursor fila = dbData.rawQuery("select * from usuarios where nombre = '" + nombre + "'", null);

        while (fila.moveToNext()) {
            String nomb = fila.getString(1);
            //divido este if para que me sirva comprobar el registro si existe con solo el nombre
            if (nomb.equals(nombre)) {
                ok = true;
                System.out.println("Existe");
            } else {
                ok = false;
                System.out.println("no existe");
            }
        }
        fila.close();
        return ok;
    }

    //actualizar tablas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
/*
    public static void clo(){
        dbData.close();
        db.close();
    }
*/
}
