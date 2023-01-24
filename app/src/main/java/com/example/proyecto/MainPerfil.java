package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.db.Db;
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
    private EditText textUsu, textContra, textReContra;

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
        textUsu = findViewById(R.id.textUsu);
        textContra = findViewById(R.id.textContra);
        textReContra = findViewById(R.id.textRecontrasena);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            textUsuario.setText(Usuario.getNombre());
            Picasso.get().load(Usuario.getFotoUri()).into(imgFoto);
        } else {
            textUsuario.setText(Usuario.getNombre());
            imgFoto.setImageBitmap(Usuario.getFotoBitmap());
        }

    }

    public void cambiar(View view) {
        String nombreNuevo = null;
        if (!textUsu.getText().toString().equals("")) {
            if (!Db.consultaRegistrarUsuario(this, textUsu.getText().toString())) {
                nombreNuevo = textUsu.getText().toString();
                Db.modificarNombreUsuario(this, Usuario.getNombre(), nombreNuevo);
                Intent intent = new Intent(this, MainInicio.class);
                Usuario.setNombre(nombreNuevo);
                startActivity(intent);
            } else {
                Toast.makeText(this, "NOMBRE USUARIO EXISTE", Toast.LENGTH_SHORT).show();
                textUsu.setText("");
            }
        }
        if (!textContra.getText().toString().equals("") && !textReContra.getText().toString().equals("")) {
            if (textContra.getText().toString().equals(textReContra.getText().toString())) {
                Db.modificarContrasenaUsuario(this, textUsuario.getText().toString(), textContra.getText().toString());
                Intent intent = new Intent(this, MainInicio.class);
                Usuario.setNombre(textUsuario.getText().toString());
                startActivity(intent);
            }
        }
        
    }

    public void eliminarCuenta(View view) {
        Db.eliminarUsuario(this, Usuario.getNombre());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        onBackPressed();
        finish();
    }


}