package com.wao.myx.projetoz.index;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;


import com.wao.myx.R;

import java.util.ArrayList;

/**
 * Created by wanderson on 13/11/17.
 */

public class Intro extends View implements Runnable {
    private final boolean rodando = true;
    //private final SurfaceHolder holder = getHolder();


    private int i = 20;

    private final Paint p = new Paint();
    private final Context contexto;
    //private MediaPlayer mm;
    private int valor = 7;
    private int time = 0;
    private String num = "1";
    DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
    int h = (int) (this.displayMetrics.heightPixels+(this.displayMetrics.heightPixels*0.1));
    int w = this.displayMetrics.widthPixels;
     public Bitmap wjm = BitmapFactory.decodeResource( this.getResources(), R.drawable.wao);
    public Bitmap fundo = BitmapFactory.decodeResource( this.getResources(), R.drawable.fenixintro);

    public void config() {
        this.wjm = Bitmap.createScaledBitmap( wjm, this.w / 100 * 80, this.h / 100 * 20,false);
        this.fundo = Bitmap.createScaledBitmap( fundo, this.w , this.h ,false);

    }

    private final ArrayList<Bitmap> numerosx = new ArrayList();
    int px = this.w / 100;
    int py = this.h / 100;

    public Intro(Context context) {
        super(context);
        Thread threadGame = new Thread(this);
        threadGame.setPriority(Thread.MIN_PRIORITY);
        threadGame.start();
        this.config();

        contexto = context;
    }


    @Override
    public void run() {

        while (this.rodando) {
            try {

                Thread.sleep(10);


            } catch (Exception e) {

                Log.e("Erro",e.getMessage());
            }
            this.atualizar();


        }
    }

    private void atualizar() {
        if(time==100){
            time=1000;
            Bundle param = new Bundle();
            param.putInt("nivel", this.valor );
            param.putBoolean("inico",true);

            Intent intent = new Intent( this.contexto.getApplicationContext(),Tartaruga.class);
            intent.putExtras(param);

            this.contexto.startActivities(new Intent[]{intent});
 System.exit(0);
        }
        if(time<100){
            time++;
        }

        this.postInvalidate();
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        c.drawColor(Color.rgb(255,255,255));
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP){
             p.setColor( Color.rgb( 255,2,2));

            c.drawRect( 0 ,0 ,
                    w,h,
                    this.p );

            p.setColor( Color.rgb( 0,0,0));
            p.setTextSize( (float) (25) );
            c.drawText( "Aplicativo não funciona nesta ",10,270,p);
            c.drawText( "versão do Android",10,300,p);


        } else{


        if(this.i >0){
            c.drawBitmap( this.wjm, this.w * 0.10f, this.h * 0.30f, this.p );

            this.i--;}
        else {

            c.drawBitmap( this.fundo,0, 0, this.p );



        }

        }



    }


}
