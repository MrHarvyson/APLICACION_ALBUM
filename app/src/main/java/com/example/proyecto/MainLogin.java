package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.proyecto.db.Db;
import com.example.proyecto.entidades.Usuario;


public class MainLogin extends AppCompatActivity {

    private TextView nombreAplicacion, textoEslogan;
    private EditText textUsuario,textContrasena;
    private ImageView fondoVerde,logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        nombreAplicacion = findViewById(R.id.textNombreAplicacion);
        textoEslogan = findViewById(R.id.textEslogan);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.imgFondoVerde);
        textUsuario = findViewById(R.id.textUsu);
        textContrasena = findViewById(R.id.textContra);

    }

    public void entrarLogin(View view){

        try{
            if(Db.consultaUsuario(MainLogin.this, textUsuario.getText().toString(),textContrasena.getText().toString())){
                Animacion anim = new Animacion(nombreAplicacion, textoEslogan, fondoVerde, logo);
                Intent intent = new Intent(this, MainInicio.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, anim.animacion());
                Usuario.crearusuario(textUsuario.getText().toString(),Usuario.getFotoUsuario(this,textUsuario.getText().toString()));
                startActivity(intent,options.toBundle());
                finish();

            }else{
                Toast.makeText(this, getString(R.string.notificacion_usuario), Toast.LENGTH_SHORT).show();
                textContrasena.setText("");
            }
        }catch (Exception ex){
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }

    }


}