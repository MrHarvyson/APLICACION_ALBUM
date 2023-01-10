package com.example.proyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.proyecto.entidades.Libros;
import java.util.ArrayList;

public class Db extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "libros.db";
    public static final String TABLA_LIBROS = "libro";
    public static final String TABLA_USUARIOS = "usuario";

    public Db(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //crear tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_USUARIOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, contrasena TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + TABLA_LIBROS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, autor TEXT NOT NULL, editorial TEXT NOT NULL)");
    }

    //crear BD
    public static void crearBd(Context context) {
        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();
        if (dbData != null) {
            //Toast.makeText(context, "Base de datos creada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Base de datos no creada", Toast.LENGTH_SHORT).show();
        }
        dbData.close();
        db.close();
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
            dbData.close();
            db.close();
        } catch (Exception ex) {
            System.out.println("ERRRORR");
        }

        return id;
    }

    //crear libros
    public static long crearLibro(Context context,  String titulo, String autor, String editorial) {
        long id = 0;
        try {
            Db db = new Db(context);
            SQLiteDatabase dbData = db.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("titulo", titulo);
            values.put("autor", autor);
            values.put("editorial", editorial);

            dbData.insert(TABLA_LIBROS, null, values);
            dbData.close();
            db.close();
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
        Cursor fila = dbData.rawQuery("select * from " + TABLA_USUARIOS + " where nombre = '" + nombre + "'", null);

        if (fila.moveToFirst()) {
            String nomb = fila.getString(1);
            String contra = fila.getString(2);
            //divido este if para que me sirva comprobar el registro si existe con solo el nombre
            if (nomb.equals(nombre)) {
                if (contra.equals(contrasena)) {
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
        dbData.close();
        db.close();
        fila.close();
        return ok;
    }

    //consultar registros no usado pq vale consultarUsuario
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
        dbData.close();
        db.close();
        fila.close();
        return ok;
    }


    public ArrayList<Libros> mostrarLibros(Context context){

        Db db = new Db(context);
        SQLiteDatabase dbData = db.getWritableDatabase();

        ArrayList<Libros> listaLibros = new ArrayList<>();
        Libros libro = null;
        Cursor cursorLibros = null;

        cursorLibros = dbData.rawQuery("SELECT * FROM " + TABLA_LIBROS , null);

        if(cursorLibros.moveToFirst()){
            do{
                libro = new Libros();
                //libro.setId(cursorLibros.getInt(0));
                libro.setTitulo(cursorLibros.getString(1));
                libro.setAutor(cursorLibros.getString(2));
                libro.setEditorial(cursorLibros.getString(3));
                listaLibros.add(libro);
            }while (cursorLibros.moveToNext());
        }

        cursorLibros.close();
        dbData.close();
        db.close();
        return listaLibros;
    }

    //actualizar tablas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
