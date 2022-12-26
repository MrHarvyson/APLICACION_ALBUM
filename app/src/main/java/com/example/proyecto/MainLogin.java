package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();
    }

    public void entrar(View view){
        Intent intent = new Intent(MainLogin.this, MainInicio.class);
        startActivity(intent);
    }

}