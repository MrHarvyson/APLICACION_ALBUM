package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainLogin extends AppCompatActivity {

    private TextView text1, text2;
    private ImageView fondoVerde,logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        text1 = findViewById(R.id.textSanFernando);
        text2 = findViewById(R.id.textPublica);
        logo = findViewById(R.id.imgLogo2);
        fondoVerde = findViewById(R.id.imgFondoVerde);
    }

    public void entrar(View view){
        Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
        Intent intent = new Intent(this, MainInicio.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, anim.animacion());
        startActivity(intent,options.toBundle());
    }

}