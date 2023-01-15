package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import com.airbnb.lottie.LottieAnimationView;
import com.example.proyecto.databinding.ActivityMainInicioBinding;
import com.example.proyecto.fragment.AcercaFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;


public class MainInicio extends AppCompatActivity {

    //el nombre ActivityMainIncioBinding cambiara según la clase en la que estemos
    ActivityMainInicioBinding binding;
    //RecyclerView listaAlbumes;
    //ArrayList<Albumes> listaArrayAlbumes;

    private TextView text1, text2, textUsuario;
    private ImageView fondoVerde,imgFoto;
    private LottieAnimationView logo;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    private MediaPlayer mp; //para reproducir audio


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainInicioBinding.inflate(getLayoutInflater());

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(binding.getRoot());
        //replaceFragment(new ListaFragment());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.myNavHost);
        NavController navController = navHostFragment.getNavController();

        text1 = findViewById(R.id.textNombreAplicacion);
        text2 = findViewById(R.id.textEslogan);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.imgFondoVerde);
        textUsuario = findViewById(R.id.textUsuario);
        imgFoto = findViewById(R.id.imgFoto);
        //listaAlbumes = findViewById(R.id.lista_albumes);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        Uri foto = null;
        if (acct != null) {
            String nombre = acct.getEmail();
            foto = acct.getPhotoUrl();
            Usuario.crearusuario(nombre);
        }

        Picasso.get().load(foto).into(imgFoto);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.lista:
                    navController.navigate(R.id.listaFragment);
                    //replaceFragment(new ListaFragment());
                    reproducirMusica(R.id.lista);
                    break;
                case R.id.acerca:
                    navController.navigate(R.id.acercaFragment);
                    reproducirMusica(R.id.acerca);
                    break;
                case R.id.anadir:
                    navController.navigate(R.id.crearFragment);
                    reproducirMusica(R.id.anadir);
                    break;
                case R.id.borrar:
                    navController.navigate(R.id.borrarFragment);
                    reproducirMusica(R.id.borrar);
                    break;
                case R.id.salir:
                    reproducirMusica(R.id.salir);

                    Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
                    Intent intent = new Intent(MainInicio.this, MainActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainInicio.this, anim.animacion());

                    //cerrar sesion google
                    gsc.signOut().addOnCompleteListener(task -> {
                        finish();
                        startActivity(intent, options.toBundle());
                    });
                    //toast nos sirve para crear un mensaje emergente sin que se pueda presionar
                    Toast.makeText(this, "SESIÓN CERRADA", Toast.LENGTH_SHORT).show();
                    break;
            }

            return true;
        });

        textUsuario.setText(Usuario.getUsuario());
    }

    public void intoSesion(View view){
        Intent intent = new Intent(MainInicio.this, MainPerfil.class);
        startActivity(intent);
    }

    //reproduce la musica en bucle en la pantalla Acerca de y lo para en las demás
    private void reproducirMusica(int id){
        if (id != R.id.acerca) {
            if (mp != null) {
                mp.stop();
                mp = null;
            }
        } else {
            mp = MediaPlayer.create(MainInicio.this, R.raw.musica_acerca);
            mp.setLooping(true);
            mp.start();
        }
    }
}