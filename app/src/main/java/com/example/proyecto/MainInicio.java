package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.proyecto.databinding.ActivityMainInicioBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class MainInicio extends AppCompatActivity {

    //el nombre ActivityMainIncioBinding cambiara según la clase en la que estemos
    ActivityMainInicioBinding binding;
    //RecyclerView listaAlbumes;
    //ArrayList<Albumes> listaArrayAlbumes;

    private TextView text1, text2, textUsuario;
    private ImageView fondoVerde;
    private LottieAnimationView logo;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;


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
        //listaAlbumes = findViewById(R.id.lista_albumes);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {
            String nombre = acct.getEmail();
            Uri foto = acct.getPhotoUrl();
            Usuario.crearusuario(nombre);
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.lista:
                    navController.navigate(R.id.listaFragment);
                    //replaceFragment(new ListaFragment());
                    break;
                case R.id.acerca:
                    navController.navigate(R.id.acercaFragment);
                    break;
                case R.id.anadir:
                    navController.navigate(R.id.crearFragment);
                    break;
                case R.id.borrar:
                    navController.navigate(R.id.borrarFragment);
                    break;
                case R.id.salir:
                    Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
                    Intent intent = new Intent(MainInicio.this, MainActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainInicio.this, anim.animacion());

                    //cerrar sesion google
                    gsc.signOut().addOnCompleteListener(task -> {
                        finish();
                        startActivity(intent,options.toBundle());
                    });
                    //toast nos sirve para crear un mensaje emergente sin que se pueda presionar
                    Toast.makeText(this, "SESIÓN CERRADA", Toast.LENGTH_SHORT).show();
                    break;
            }

            return true;
        });

        textUsuario.setText(Usuario.getUsuario());
    }

}