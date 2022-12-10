package com.example.jogodetabuleiro1.projetoz.index;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.view.WindowManager.LayoutParams;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jogodetabuleiro1.projetoz.menu.TartarugaCorrida;

import java.io.IOException;

public class Tartaruga extends AppCompatActivity {
    private TartarugaCorrida jogo;
    private GLSurfaceView glSurfaceView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();//tira a barra de titulo
        this.getWindow().setFlags( LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);//coloca em fullscreen
        this.getWindow().addFlags( LayoutParams.FLAG_KEEP_SCREEN_ON);

        try {
            this.jogo = new TartarugaCorrida(this, getAssets());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Intent it = this.getIntent();
        glSurfaceView = new GLSurfaceView( this );

        // set our renderer to be the main renderer with
        // the current activity context

        glSurfaceView.setOnTouchListener( jogo );
        glSurfaceView.setRenderer( jogo );

        setContentView( glSurfaceView );

    }
    @Override
    public void onBackPressed() {
        if(this.getIntent().getBooleanExtra("SAIR", false)){
            // setContentView(menu);
            finish();

        }

    }
    @Override
    protected void onResume() {
        super.onResume();


    }
    @Override
    public void onPause() {
        super.onPause();
       //jogo.pausar(true);


    }
    @Override
    public void onStop() {
        super.onStop();

    }
    @Override
public void onDestroy(){
    super.onDestroy();
   Process.killProcess( Process.myPid() );



}

}