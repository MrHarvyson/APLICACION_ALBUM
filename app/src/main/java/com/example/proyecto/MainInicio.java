package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import com.example.proyecto.databinding.ActivityMainInicioBinding;
import com.example.proyecto.entidades.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;


public class MainInicio extends AppCompatActivity {

    //el nombre ActivityMainIncioBinding cambiara según la clase en la que estemos
    ActivityMainInicioBinding binding;

    private TextView nombreAplicacion, textoEslogan, textUsuario, tituloAcerca;
    private ImageView fondoVerde, imgFoto, volumen, logo;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private MediaPlayer mp;
    boolean iconON = true;
    private Button btnInto;
    boolean acerca = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mp = MediaPlayer.create(MainInicio.this, R.raw.musica_acerca);
        mp.setLooping(true);

        binding = ActivityMainInicioBinding.inflate(getLayoutInflater());

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.myNavHost);
        NavController navController = navHostFragment.getNavController();

        nombreAplicacion = findViewById(R.id.textNombreAplicacion);
        textoEslogan = findViewById(R.id.textEslogan);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.imgFondoVerde);
        textUsuario = findViewById(R.id.textUsuario);
        imgFoto = findViewById(R.id.imgFoto);
        volumen = findViewById(R.id.volumen_click);
        volumen.setVisibility(View.INVISIBLE);
        btnInto = findViewById(R.id.btnInto);
        tituloAcerca = findViewById(R.id.tituloAcerca);
        tituloAcerca.setVisibility(View.INVISIBLE);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);


        // lo usamos para acceder a la información del usuario de google
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            btnInto.setVisibility(View.INVISIBLE);
            Usuario.crearusuario(acct.getGivenName(), acct.getPhotoUrl());
            textUsuario.setText(Usuario.getNombre());
            Picasso.get().load(Usuario.getFotoUri()).into(imgFoto);
        } else {
            textUsuario.setText(Usuario.getNombre());
            imgFoto.setImageBitmap(Usuario.getFotoBitmap());
        }

        // al tocar icono musica se para o reproduce la musica
        volumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mp.isPlaying()) {
                    volumen.setImageResource(R.drawable.boton_volumen_off);
                    iconON = false;
                    mp.pause();
                } else {
                    volumen.setImageResource(R.drawable.boton_volumen_on);
                    iconON = true;
                    mp.start();
                }

            }
        });

        // botones del navigationView
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.lista:
                    navController.navigate(R.id.listaFragment);
                    reproducirMusica(R.id.lista);
                    mostrarControles(R.id.lista);
                    acerca = false;
                    break;
                case R.id.acerca:
                    navController.navigate(R.id.acercaFragment);
                    reproducirMusica(R.id.acerca);
                    mostrarControles(R.id.acerca);
                    acerca = true;

                    break;
                case R.id.anadir:
                    navController.navigate(R.id.crearFragment);
                    reproducirMusica(R.id.anadir);
                    mostrarControles(R.id.anadir);
                    acerca = false;
                    break;
                case R.id.borrar:
                    navController.navigate(R.id.borrarFragment);
                    reproducirMusica(R.id.borrar);
                    mostrarControles(R.id.borrar);
                    acerca = false;
                    break;
                case R.id.salir:
                    reproducirMusica(R.id.salir);

                    Animacion anim = new Animacion(nombreAplicacion, textoEslogan, fondoVerde, logo);
                    Intent intent = new Intent(MainInicio.this, MainActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainInicio.this, anim.animacion());

                    //cerrar sesion google
                    gsc.signOut().addOnCompleteListener(task -> {
                        finish();
                    });
                    startActivity(intent, options.toBundle());
                    //toast nos sirve para crear un mensaje emergente sin que se pueda presionar
                    Toast.makeText(this, getString(R.string.notificacion_sesion), Toast.LENGTH_SHORT).show();
                    //finish();
                    break;
            }

            return true;
        });

    }

    // entra en la pantalla de usuario
    public void entrarUsuario(View view) {
        Intent intent = new Intent(MainInicio.this, MainPerfil.class);
        startActivity(intent);
    }

    // reproduce la musica en bucle en la pantalla Acerca de y lo para en las demás
    private void reproducirMusica(int id) {

        if (id == R.id.acerca) {

            if (iconON) {
                mp.start();
            } else {
                mp.pause();
            }

        } else {
            if (mp != null) {
                if (mp.isPlaying()) {
                    mp.pause();
                }
            }
        }
    }

    // mostrar controles en la parte superior del MainInicio
    private void mostrarControles(int id) {

        if (id == R.id.acerca) {
            textUsuario.setVisibility(View.INVISIBLE);
            imgFoto.setVisibility(View.INVISIBLE);
            btnInto.setVisibility(View.INVISIBLE);
            tituloAcerca.setVisibility(View.VISIBLE);
            volumen.setVisibility(View.VISIBLE);
        } else {
            textUsuario.setVisibility(View.VISIBLE);
            imgFoto.setVisibility(View.VISIBLE);
            btnInto.setVisibility(View.VISIBLE);
            tituloAcerca.setVisibility(View.INVISIBLE);
            volumen.setVisibility(View.INVISIBLE);
        }
    }

    // no se pueda usar el boton de retroceder en esta pantalla
    @Override
    public void onBackPressed() {

    }

    // pare la musica al minimizar la aplicacion
    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.pause();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (acerca && iconON) {
            mp.start();
        }
    }
}