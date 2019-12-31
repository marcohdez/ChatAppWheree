package com.example.chatappwhere;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Menu extends AppCompatActivity {
    Button boton1;
    TextView boton2;
    ImageView ruleta;

    Random random = new Random();
    int angulo;
    Animation rotateAnimation;
    boolean restablecer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //Cambie el menu
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_menu_toolbar);

        ruleta = (ImageView) findViewById(R.id.ruleta);

        boton1 = (Button) findViewById(R.id.boton1);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, Login.class);
                startActivity(i);
            }
        });
        boton2 = (TextView) findViewById(R.id.boton2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(Menu.this, Registry.class);
                startActivity(s);
            }
        });


        rotateAnimation();

    }

    private void rotateAnimation() {

        rotateAnimation= AnimationUtils.loadAnimation(this,R.anim.rotate);
        ruleta.startAnimation(rotateAnimation);

    }
}

