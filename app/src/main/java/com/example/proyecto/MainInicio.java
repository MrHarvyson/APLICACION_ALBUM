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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainInicio extends AppCompatActivity {

    private TextView text1, text2;
    private ImageView fondoVerde,logo;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_inicio);

        text1 = findViewById(R.id.textSanFernando);
        text2 = findViewById(R.id.textPublica);
        logo = findViewById(R.id.imgLogo2);
        fondoVerde = findViewById(R.id.imgFondoVerde);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
    }

    public void lista(View view){

        //devuelve ultima activity
        //finish();
        Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
        Intent intent = new Intent(MainInicio.this, MainLista.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainInicio.this, anim.animacion());
        startActivity(intent,options.toBundle());

    }

    public void anadir(View view){

        //devuelve ultima activity
        //finish();
        Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
        Intent intent = new Intent(MainInicio.this, MainAnadir.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainInicio.this, anim.animacion());
        startActivity(intent,options.toBundle());

    }

    public  void salir(View view){

        Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
        Intent intent = new Intent(MainInicio.this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainInicio.this, anim.animacion());

        //cerrar sesion google
        gsc.signOut().addOnCompleteListener(task -> {
            //finish();
            startActivity(intent,options.toBundle());
        });

        //toast nos sirve para crear un mensaje emergente sin que se pueda presionar
        Toast.makeText(this, "SESIÓN CERRADA", Toast.LENGTH_SHORT).show();

    }

}