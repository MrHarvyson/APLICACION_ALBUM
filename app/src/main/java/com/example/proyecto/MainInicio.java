package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.proyecto.databinding.ActivityMainInicioBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class MainInicio extends AppCompatActivity {

    //el nombre ActivityMainIncioBinding cambiara según la clase en la que estemos
    ActivityMainInicioBinding binding;
    //RecyclerView listaLibros;
    //ArrayList<Libros> listaArrayLibros;

    private TextView text1, text2;
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

        text1 = findViewById(R.id.textSanFernando);
        text2 = findViewById(R.id.textPublica);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.imgFondoVerde);
        //listaLibros = findViewById(R.id.lista_libros);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.lista:
                    navController.navigate(R.id.listaFragment);
                    //replaceFragment(new ListaFragment());
                    break;
                case R.id.modificar:
                    navController.navigate(R.id.modificarFragment);
                   // replaceFragment(new ModificarFragment());
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

                    //Db.clo();

                    //cerrar sesion google
                    gsc.signOut().addOnCompleteListener(task -> {
                        //finish();
                        startActivity(intent,options.toBundle());
                    });

                    //toast nos sirve para crear un mensaje emergente sin que se pueda presionar
                    Toast.makeText(this, "SESIÓN CERRADA", Toast.LENGTH_SHORT).show();
                    break;
            }

            return true;
        });



/*
        listaLibros.setLayoutManager(new LinearLayoutManager(MainInicio.this));

        Db db = new Db(MainInicio.this);
        listaArrayLibros = new ArrayList<>();

        ListaLibrosAdapter adapter = new ListaLibrosAdapter(db.mostrarLibros(MainInicio.this));
        listaLibros.setAdapter(adapter);
*/
    }

    public void cerrarSesion(View view){
        Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
        Intent intent = new Intent(MainInicio.this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainInicio.this, anim.animacion());

        //Db.clo();

        //cerrar sesion google
        gsc.signOut().addOnCompleteListener(task -> {
            //finish();
            startActivity(intent,options.toBundle());
        });

        //toast nos sirve para crear un mensaje emergente sin que se pueda presionar
        Toast.makeText(this, "SESIÓN CERRADA", Toast.LENGTH_SHORT).show();
    }
/*
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
*/
}