package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.db.Db;

public class MainLogin extends AppCompatActivity {

    private TextView text1, text2, textUsuario, textContrasena;
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
        textUsuario = findViewById(R.id.textUsu);
        textContrasena = findViewById(R.id.textContra);
    }

    public void entrar(View view){
        try{
            Boolean ok =  Db.consultaUsuario(MainLogin.this, textUsuario.getText().toString(),textContrasena.getText().toString());

            if(ok){
                Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
                Intent intent = new Intent(this, MainInicio.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, anim.animacion());
                startActivity(intent,options.toBundle());
            }else{
                Toast.makeText(this, "CONTRASEÑA/USUARIO INCORRECTO", Toast.LENGTH_SHORT).show();
                textContrasena.setText("");
            }


        }catch (Exception ex){
            Toast.makeText(this, "SUPER ERROR", Toast.LENGTH_SHORT).show();
        }

    }

}