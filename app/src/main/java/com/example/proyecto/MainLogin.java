package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.proyecto.db.Db;


public class MainLogin extends AppCompatActivity {

    private TextView text1, text2;
    private EditText textUsuario;
    private EditText textContrasena;
    private ImageView fondoVerde;
    private LottieAnimationView logo;
    private final static String CHANNEL_ID = "canal";

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        text1 = findViewById(R.id.textNombreAplicacion);
        text2 = findViewById(R.id.textEslogan);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.imgFondoVerde);
        textUsuario = findViewById(R.id.textUsu);
        textContrasena = findViewById(R.id.textContra);

    }

    public void entrar(View view){

        try{
            if(Db.consultaUsuario(MainLogin.this, textUsuario.getText().toString(),textContrasena.getText().toString())){
                //crearNotificacion();
                Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
                Intent intent = new Intent(this, MainInicio.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, anim.animacion());
                Usuario.crearusuario(textUsuario.getText().toString());
                startActivity(intent,options.toBundle());

            }else{
                Toast.makeText(this, getString(R.string.notificacion_usuario), Toast.LENGTH_SHORT).show();
                textContrasena.setText("");
            }
        }catch (Exception ex){
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }

    }


}