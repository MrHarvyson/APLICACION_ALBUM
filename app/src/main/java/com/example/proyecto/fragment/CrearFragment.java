package com.example.proyecto.fragment;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.MainInicio;
import com.example.proyecto.MainLogin;
import com.example.proyecto.MainRegister;
import com.example.proyecto.R;
import com.example.proyecto.db.Db;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class CrearFragment extends Fragment {

    private Button agregar;
    private EditText titulo, autor, discografica;
    private CircleImageView portada;
    private final static String CHANNEL_ID = "canal";
    private boolean hay = false;
    private PendingIntent pendingIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        titulo = (EditText) view.findViewById(R.id.textTitulo);
        autor =(EditText) view.findViewById(R.id.textAutor);
        discografica =(EditText) view.findViewById(R.id.textDiscografica);
        agregar = (Button) view.findViewById(R.id.btn_agregar);
        portada =(CircleImageView) view.findViewById(R.id.btn_portada);
        // Inflate the layout for this fragment

        //seleccionar foto
        portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hay = true;
                ImagePicker.Companion.with(CrearFragment.this)
                        .cropSquare()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(101);
            }
        });

        //agregar album
        agregar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(!titulo.getText().toString().equals("") && !autor.getText().toString().equals("") && !discografica.getText().toString().equals("")){
                    if(Db.crearAlbum(getContext(), titulo.getText().toString(),autor.getText().toString(), discografica.getText().toString(),ImageViewtoBite(portada))){
                        Toast.makeText(getContext(), "HAY QUE PONER NOTIFICACION", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainInicio.class);
                        startActivity(intent);
                        crearNotificacion();
                    }else {
                        Toast.makeText(getContext(), "ERROR BASE DE DATOS", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "COMPLETE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            Uri path =data.getData();
            portada.setImageURI(path);
        }
    }

    //convertir imagen a bites
    private byte[] ImageViewtoBite(CircleImageView avatar){
        Bitmap bitmap =((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void crearNotificacion(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"NEW", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext().getApplicationContext(),CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_album)
                    .setContentTitle("TUNEHUB")
                    .setContentText("Álbum " + titulo.getText().toString() + " agregado.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setVibrate(new long[]{1000,1000,1000,1000,1000});
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext().getApplicationContext());
            managerCompat.notify(1,builder.build());

        }else{
            //setPendingIntent(MainInicio.class);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext().getApplicationContext(),CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_album)
                    .setContentTitle("TUNEHUB")
                    .setContentText("Álbum " + titulo.getText().toString() + " agregado.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setVibrate(new long[]{1000,1000,1000,1000,1000});;
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext().getApplicationContext());
            managerCompat.notify(1,builder.build());
        }
    }

}
