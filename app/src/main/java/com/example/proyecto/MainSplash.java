package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainSplash extends AppCompatActivity {

    Thread timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_splash);

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        wait(2000);
                    }

                }catch (Exception ex){
                    System.out.println(ex);
                }finally {
                    Intent intent = new Intent(MainSplash.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };
        timer.start();
    }
}