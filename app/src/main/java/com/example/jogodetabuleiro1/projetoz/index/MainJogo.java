package com.example.jogodetabuleiro1.projetoz.index;


import android.os.Bundle;
import android.view.WindowManager.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class MainJogo extends AppCompatActivity {
    private Intro into;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();//tira a barra de titulo
        this.getWindow().setFlags( LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);//coloca em fullscreen
        this.getWindow().addFlags( LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.into = new Intro(this);
        this.setContentView( this.into );
//        into.getMm().pause();
        //      into.getMm().start();

        if(this.getIntent().getBooleanExtra("SAIR", false)){
            //into.getMm().stop();
           // finish();


        }
    }


}
