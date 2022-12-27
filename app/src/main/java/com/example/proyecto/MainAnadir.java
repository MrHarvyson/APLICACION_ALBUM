package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainAnadir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_anadir);
    }
}