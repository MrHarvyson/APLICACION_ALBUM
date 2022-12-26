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
import android.widget.Toast;

public class MainRegister extends AppCompatActivity {

    private TextView text1, text2;
    private ImageView fondoVerde,logo;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_register);

        text1 = findViewById(R.id.textSanFernando);
        text2 = findViewById(R.id.textPublica);
        btnlogin = findViewById(R.id.btnEntrar);
        logo = findViewById(R.id.imgLogo2);
        fondoVerde = findViewById(R.id.imgFondoVerde);
    }

    public void registrar(View view){
        Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
        Intent intent = new Intent(MainRegister.this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainRegister.this,anim.animacion());

        startActivity(intent,options.toBundle());

        //toast nos sirve para crear un mensaje emergente sin que se pueda presionar
        Toast.makeText(this, "USUARIO REGISTRADO", Toast.LENGTH_SHORT).show();

    }

}