package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.proyecto.db.Db;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private TextView text1, text2; //texto1 --> San Fernando  texto2 --> Biblioteca publica
    private ImageView fondoVerde, btnGoogle;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private LottieAnimationView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        text1 = findViewById(R.id.textSanFernando);
        text2 = findViewById(R.id.textPublica);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.fondoVerde2);
        //btnGoogle = findViewById(R.id.btnGoogle);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(MainActivity.this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
            Intent intent = new Intent(MainActivity.this, MainInicio.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, anim.animacion());
            startActivity(intent);
        }

        //crear la base de datos

/*
        Db.crearBd(MainActivity.this);
        Db.crearLibro(this, "El señor de lerus", "Paco Cabezas","El piquito");
        Db.crearLibro(this, "La fabrica de 'Chocolate9'", "Luios Botry","Unfoil");
        Db.crearLibro(this, "El hombre lobo", "Krenories Urtyue","Editorial Vapor");
        Db.crearLibro(this, "El Cabezon", "Casa Botry","Casa");
        Db.crearLibro(this, "El hombre lobo", "Krenories Urtyue","Editorial Vapor");
        Db.crearLibro(this, "Las siete muertes", "María Acosta","Anaya");
        Db.crearLibro(this, "Harry Potter y la piedra filosofal", "Matry","Unoixter");
        Db.crearLibro(this, "En el nombre del viento", "Krenories Urtyue","Editorial Vapor");
        Db.crearUsuario(this, "Admin", "123");

         */

    }

    /*
    private void crearBd() {
        Db db = new Db(MainActivity.this);
        SQLiteDatabase dbData = db.getWritableDatabase();
        if (dbData != null) {
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Base de datos no creada", Toast.LENGTH_SHORT).show();
        }
    }

     */

    public void login(View view) {
        Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
        Intent intent = new Intent(MainActivity.this, MainLogin.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, anim.animacion());
        startActivity(intent, options.toBundle());
    }

    public void registrarInicio(View view) {
        Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
        Intent intent = new Intent(MainActivity.this, MainRegister.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, anim.animacion());
        startActivity(intent, options.toBundle());
    }

    public void google(View view) {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Intent intent = new Intent(MainActivity.this, MainInicio.class);
                startActivity(intent);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

}