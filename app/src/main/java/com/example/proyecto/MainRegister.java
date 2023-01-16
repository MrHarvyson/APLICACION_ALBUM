package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.proyecto.db.Db;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainRegister extends AppCompatActivity {

    private TextView text1, text2;
    private EditText textUsuario, textContrasena, textRecontrasena;
    private ImageView fondoVerde;
    private LottieAnimationView logo;
    private CircleImageView avatar;
    private boolean hay=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_register);

        text1 = findViewById(R.id.textNombreAplicacion);
        text2 = findViewById(R.id.textEslogan);
        logo = findViewById(R.id.animation_view);
        fondoVerde = findViewById(R.id.imgFondoVerde);
        textUsuario = findViewById(R.id.textUsu);
        textContrasena = findViewById(R.id.textContra);
        textRecontrasena = findViewById(R.id.textRecontrasena);
        avatar = findViewById(R.id.btn_camera);

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hay = true;
                ImagePicker.Companion.with(MainRegister.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(101);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            Uri path =data.getData();
            avatar.setImageURI(path);
        }
    }


    private byte[] ImageViewtoBite(CircleImageView avatar){
        Bitmap bitmap =((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void registrar(View view) {
        if(!hay){
            avatar.setImageDrawable(getResources().getDrawable(R.drawable.user2));
        }


        if (!Db.consultaUsuario(this, textUsuario.getText().toString(), textContrasena.getText().toString())) {
            if (textContrasena.getText().toString().equals(textRecontrasena.getText().toString())) {

                if (Db.crearUsuario(this, textUsuario.getText().toString(), textContrasena.getText().toString(), ImageViewtoBite(avatar))) {
                    Toast.makeText(this, "USUARIO CREADO", Toast.LENGTH_SHORT).show();
                    Animacion anim = new Animacion(text1, text2, fondoVerde, logo);
                    Intent intent = new Intent(MainRegister.this, MainActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainRegister.this, anim.animacion());
                    startActivity(intent, options.toBundle());
                }

            } else {
                Toast.makeText(this, "LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_SHORT).show();
                textContrasena.setText("");
                textRecontrasena.setText("");
            }
        } else {
            Toast.makeText(this, "USUARIO EXISTE", Toast.LENGTH_SHORT).show();
        }


    }


}