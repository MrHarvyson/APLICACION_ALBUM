package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.db.Db;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainRegister extends AppCompatActivity {

    private TextView nombreAplicacion, textoEslogan;
    private EditText textUsuario, textContrasena, textRecontrasena;
    private ImageView fondoVerde, logo;
    private CircleImageView avatar;
    private boolean hay_foto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_register);

        nombreAplicacion = findViewById(R.id.textNombreAplicacion);
        textoEslogan = findViewById(R.id.textEslogan);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.imgFondoVerde);
        textUsuario = findViewById(R.id.textUsu);
        textContrasena = findViewById(R.id.textContra);
        textRecontrasena = findViewById(R.id.textRecontrasena);
        avatar = findViewById(R.id.btn_camera);

        // seleccionar foto
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hay_foto = true;
                ImagePicker.Companion.with(MainRegister.this)
                        .cropSquare()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(101);
            }
        });

    }

    // muestra imagen en la pantalla
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri path = data.getData();
            avatar.setImageURI(path);
        }
    }

    // convierte imagen en byteArray para guardarla en la base de datos
    private byte[] ImageViewtoBite(CircleImageView avatar) {
        Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void registrar(View view) {

        if (textUsuario.getText().toString().equals("") || textContrasena.getText().toString().equals("") || textRecontrasena.getText().toString().equals("")) {

            Toast.makeText(this, getString(R.string.mensaje_completar_campos), Toast.LENGTH_SHORT).show();

        } else {

            if (!Db.consultaUsuario(this, textUsuario.getText().toString(), textContrasena.getText().toString())) {
                if (textContrasena.getText().toString().equals(textRecontrasena.getText().toString())) {

                    if (!hay_foto) {
                        avatar.setImageDrawable(getResources().getDrawable(R.drawable.logo_usuario));
                    }

                    if (Db.crearUsuario(this, textUsuario.getText().toString(), textContrasena.getText().toString(), ImageViewtoBite(avatar))) {
                        Toast.makeText(this, getString(R.string.usuario_creado), Toast.LENGTH_SHORT).show();
                        Animacion anim = new Animacion(nombreAplicacion, textoEslogan, fondoVerde, logo);
                        Intent intent = new Intent(MainRegister.this, MainActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainRegister.this, anim.animacion());
                        startActivity(intent, options.toBundle());
                    } else {
                        // crear string
                        Toast.makeText(this, "USUARIO NO CREADO", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, getString(R.string.contrasena_diferente), Toast.LENGTH_SHORT).show();
                    textContrasena.setText("");
                    textRecontrasena.setText("");
                }

            } else {
                Toast.makeText(this, getString(R.string.usuario_existe), Toast.LENGTH_SHORT).show();
            }

        }

    }

}