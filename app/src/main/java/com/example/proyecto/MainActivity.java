package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private TextView text1, text2;
    private ImageView fondoVerde;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private LottieAnimationView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        text1 = findViewById(R.id.textNombreAplicacion);
        text2 = findViewById(R.id.textEslogan);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.fondoVerde);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(MainActivity.this, gso);

    }

    public void entrar(View view) {
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