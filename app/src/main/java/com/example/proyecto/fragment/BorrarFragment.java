package com.example.proyecto.fragment;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.Animacion;
import com.example.proyecto.MainActivity;
import com.example.proyecto.MainInicio;
import com.example.proyecto.MainLogin;
import com.example.proyecto.R;
import com.example.proyecto.db.Db;

public class BorrarFragment extends Fragment {
    private Button borrar;
    private EditText titulo;
    private final static String CHANNEL_ID = "canal";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_borrar, container, false);
        borrar = (Button) view.findViewById(R.id.btn_borrar);
        titulo = (EditText) view.findViewById(R.id.editText_borrar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        borrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getString(R.string.notificacion_desea_eliminar) + " " + titulo.getText().toString() + " ?").setPositiveButton(getString(R.string.notificacion_si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Db.eliminarAlbum(getContext(), titulo.getText().toString())) {

                            crearNotificacion(getString(R.string.notificacion_album) + " " + titulo.getText().toString() + " " + getString(R.string.notificacion_album_borrado));
                            titulo.setText("");
                            titulo.setHint(getString(R.string.entrada_titulo));

                            Intent intent = new Intent(getContext(), MainInicio.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), getString(R.string.notificacion_album) + " " + titulo.getText().toString() + " " + getString(R.string.notificacion_album_no_existe), Toast.LENGTH_SHORT).show();
                            titulo.setText("");
                            titulo.setHint(getString(R.string.entrada_titulo));
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

            }
        });
    }

    private void crearNotificacion(String titulo) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NEW", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext().getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_album)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(titulo)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setOngoing(false)
                    .setContentIntent(PendingIntent.getActivity(getContext().getApplicationContext(), 0, new Intent(), PendingIntent.FLAG_IMMUTABLE)); //eliminar al tocar

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext().getApplicationContext());
            managerCompat.notify(1, builder.build());

        } else {
            //setPendingIntent(MainInicio.class);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext().getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_album)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(titulo)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setOngoing(false)
                    .setContentIntent(PendingIntent.getActivity(getContext().getApplicationContext(), 0, new Intent(), PendingIntent.FLAG_IMMUTABLE)); //eliminar al tocar

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext().getApplicationContext());
            managerCompat.notify(1, builder.build());
        }
    }

}