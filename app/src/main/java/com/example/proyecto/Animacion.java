package com.example.proyecto;

import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Animacion {

    private TextView text1, text2; //texto1 --> San Fernando  texto2 --> Biblioteca publica
    private ImageView fondoVerde;
    private LottieAnimationView logo;

    public Animacion(TextView text1, TextView text2, ImageView fondoVerde, LottieAnimationView logo) {
        this.text1 = text1;
        this.text2 = text2;
        this.fondoVerde = fondoVerde;
        this.logo = logo;
    }

    //creamos una lista de animaciones
    //como poder hacer clase para reusar
    //no puedo ponerlo en clase aparte --> no static variables

    public Pair[] animacion() {

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(this.fondoVerde, "fondo");
        pairs[1] = new Pair<View, String>(this.logo, "album");
        pairs[2] = new Pair<View, String>(this.text1, "texto1");
        pairs[3] = new Pair<View, String>(this.text2, "texto2");

        return pairs;
    }

}
