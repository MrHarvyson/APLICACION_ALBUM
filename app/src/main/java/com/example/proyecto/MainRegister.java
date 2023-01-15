package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.proyecto.db.Db;

public class MainRegister extends AppCompatActivity {

    private TextView text1, text2;
    private EditText textUsuario, textContrasena, textRecontrasena;
    private ImageView fondoVerde;
    private LottieAnimationView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_register);

        text1 = findViewById(R.id.textNombreAplicacion);
        text2 = findViewById(R.id.textEslogan);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.imgFondoVerde);
        textUsuario = findViewById(R.id.textUsu);
        textContrasena = findViewById(R.id.textContra);
        textRecontrasena = findViewById(R.id.textRecontrasena);
    }

    public void registrar(View view) {

        if (!Db.consultaUsuario(this, textUsuario.getText().toString(), textContrasena.getText().toString())) {
            if (textContrasena.getText().toString().equals(textRecontrasena.getText().toString())) {

                if (Db.crearUsuario(this, textUsuario.getText().toString(), textContrasena.getText().toString())) {
                    Toast.makeText(this, "USUARIO CREADO", Toast.LENGTH_SHORT).show();

                    Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
                    Intent intent = new Intent(MainRegister.this, MainActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainRegister.this, anim.animacion());
                    startActivity(intent, options.toBundle());
                }

            } else {
                Toast.makeText(this, "LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_SHORT).show();
                textContrasena.setText("");
                textRecontrasena.setText("");
            }
        } else {
            Toast.makeText(this, "USUARIO EXISTE", Toast.LENGTH_SHORT).show();
        }


    }


}