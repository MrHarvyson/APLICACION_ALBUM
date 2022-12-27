package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text1, text2; //texto1 --> San Fernando  texto2 --> Biblioteca publica
    private ImageView fondoVerde, logo;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        text1 = findViewById(R.id.textSanFernando);
        text2 = findViewById(R.id.textPublica);
        btnlogin = findViewById(R.id.btnEntrarInicio);
        logo = findViewById(R.id.imgLogo2);
        fondoVerde = findViewById(R.id.fondoVerde2);
        //ejemplo

    }

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

}