package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.databinding.ActivityMainInicioBinding;
import com.example.proyecto.fragment.ListaFragment;
import com.example.proyecto.fragment.ModificarFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainInicio extends AppCompatActivity {

    ActivityMainInicioBinding binding;

    private TextView text1, text2;
    private ImageView fondoVerde,logo;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainInicioBinding.inflate(getLayoutInflater());
        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(binding.getRoot());
        replaceFragment(new ListaFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.lista:
                    replaceFragment(new ListaFragment());
                    break;
                case R.id.modificar:
                    replaceFragment(new ModificarFragment());
                    break;
                case R.id.anadir:
                    break;
                case R.id.borrar:
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

        text1 = findViewById(R.id.textSanFernando);
        text2 = findViewById(R.id.textPublica);
        logo = findViewById(R.id.imgLogo2);
        fondoVerde = findViewById(R.id.imgFondoVerde);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}