package com.wao.myx.projetoz.index;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.wao.myx.projetoz.generico.recursos.ConfigTela;
import com.wao.myx.projetoz.menu.TelaInicial;

import java.io.IOException;

public class Tartaruga extends AppCompatActivity implements ConfigTela {
    private TelaInicial jogo;
    private GLSurfaceView glSurfaceView;
    protected View decorView;

    protected final String BANNER_FENIX = "ca-app-pub-1070048556704742/4752828027";



    DisplayMetrics dm ;
    int w;
    static int h;
    static AdView adView ;
    static FrameLayout layout;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();//tira a barra de titulo
        this.getWindow().setFlags( LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);//coloca em fullscreen
        this.getWindow().addFlags( LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.getWindow().setFlags(LayoutParams.FLAG_LAYOUT_NO_LIMITS, LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        try {
            this.jogo = new TelaInicial(this, getAssets());
        } catch (IOException e) {
            e.printStackTrace();
        }
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });


         layout = new FrameLayout(this);



        Intent it = this.getIntent();
        glSurfaceView = new GLSurfaceView( this );
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float h = displayMetrics.heightPixels;
        float w = displayMetrics.widthPixels;
        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        // set our renderer to be the main renderer with
        // the current activity context

        glSurfaceView.setOnTouchListener( jogo );
        glSurfaceView.setRenderer( jogo );

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(BANNER_FENIX);
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        adView.loadAd(adRequestBuilder.build());
        adView.setY((float) ((h*0.48)));
        adView.loadAd(adRequestBuilder.build());



        layout.addView(glSurfaceView);
        layout.addView(adView);

        setContentView( layout );

    }





    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    // Método hideSystemBars

    protected int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
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
        jogo.pausar(false);

            hideSystemUI();

    }
    @Override
    public void onPause() {
        super.onPause();
        jogo.pausar(true);
        hideSystemUI();

    }
    @Override
    public void onStop() {
        super.onStop();
        hideSystemUI();
    }
    @Override
public void onDestroy(){
        hideSystemUI();
    super.onDestroy();
   Process.killProcess( Process.myPid() );



}

    public static void tirarBunner() {
        layout.getChildAt(1).setVisibility(View.INVISIBLE);

    }
    public static void colocarBunner() {
        layout.getChildAt(1).setVisibility(View.VISIBLE);
     //   layout.getChildAt(1).setY((float) ((h*0.50)));
    }

    public static void colocarBunnerTop() {
        layout.getChildAt(1).setVisibility(View.VISIBLE);
   //   adView.setY((float) ((h*-0.48)));
    }
    public static void fechar() {
         System.exit(0);

    }


    }