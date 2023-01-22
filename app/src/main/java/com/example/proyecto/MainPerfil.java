package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.entidades.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

public class MainPerfil extends AppCompatActivity {
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private ImageView imgFoto;
    private TextView textUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_perfil);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        imgFoto = findViewById(R.id.imgFoto);
        textUsuario = findViewById(R.id.textUsuario);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            textUsuario.setText(Usuario.getNombre());
            Picasso.get().load(Usuario.getFotoUri()).into(imgFoto);
        } else {
            textUsuario.setText(Usuario.getNombre());
            imgFoto.setImageBitmap(Usuario.getFotoBitmap());
        }

    }

    public void back(View view) {
        onBackPressed();
        finish();
    }

}