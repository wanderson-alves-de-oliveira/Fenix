package com.wao.fenix.projetoz.generico.recursos;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

/**
 * Created by wanderson on 01/03/18.
 */

public class ConvertBitimap {

    public ConvertBitimap() {
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private  Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmap(Context context, int drawableId, int cor1, int cor2, int cor3, boolean muda) {
        Drawable drawable = ContextCompat.getDrawable(context,drawableId);

        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(context.getResources(),drawableId);
        } else if (drawable instanceof VectorDrawable) {
            drawable.mutate().clearColorFilter();
            if(muda){
                PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.rgb( cor1,cor2,cor3 ), PorterDuff.Mode.MULTIPLY);
                drawable.mutate().setColorFilter(colorFilter);}
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("erro");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmap(Context context, int drawableId, int alp, int cor1, int cor2, int cor3, boolean muda) {
        Drawable drawable = ContextCompat.getDrawable(context,drawableId);

        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(context.getResources(),drawableId);
        } else if (drawable instanceof VectorDrawable) {
            drawable.mutate().clearColorFilter();
            if(muda){
                PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.argb( alp, cor1,cor2,cor3 ), PorterDuff.Mode.MULTIPLY);
                drawable.mutate().setColorFilter(colorFilter);}
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("erro");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmap(String vectorDrawable,int t,int p) {
        int largura=0;
        switch (vectorDrawable.length()){
            case 1:
                largura=200;
                break;

            case 2:
                largura=410;
                break;

            default:
                largura=600;
                break;

        }

        Paint pincel = new Paint(  );
        pincel.setColor( Color.argb( 90,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));


        Bitmap bitmap = Bitmap.createBitmap(largura,300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);



        canvas.drawRoundRect( 0 ,0 ,
                largura,300,
                70,70,pincel );


        pincel.setColor( Color.argb( 255,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));
        pincel.setTextSize( t );
        canvas.drawText( vectorDrawable,p,180,pincel);

        return bitmap;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmapBolha(String vectorDrawable, int t, int c,long ultimaPassada) {
        int largura=0;
        int p;
        largura=200;
        p=60;
        Paint pincel1 = new Paint(  );
        Paint pincel2 = new Paint(  );
        Paint pincel3 = new Paint(  );


        if(ultimaPassada>=Long.valueOf(vectorDrawable)){
            pincel1.setColor( Color.argb( 255,255,100,0));
            pincel2.setColor( Color.argb( 155,230,250,0));
            pincel3.setColor( Color.argb( 255,255,255,255));

        }else {
            pincel1.setColor( Color.argb( 255,82,0,255));
            pincel2.setColor( Color.argb( 155,230,250,230));
            pincel3.setColor( Color.argb( 255,0,0,0));

        }

        if(ultimaPassada+1==Long.valueOf(vectorDrawable)){
            pincel1.setColor( Color.argb( 255,243,174,62));
            pincel2.setColor( Color.argb( 155,150,116,255));
            pincel3.setColor( Color.argb( 255,255,255,255));

        }



        Bitmap bitmapx = Bitmap.createBitmap(largura,largura,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapx);

        canvas.drawRoundRect( 0 ,0 ,
                largura*0.95f,largura*0.95f,
                100,100,pincel1 );

        canvas.drawRoundRect( largura*0.1f ,largura*0.1f ,
                largura*0.85f,largura*0.85f,
                100,100,pincel2 );



        if(vectorDrawable.length()==1) {
            pincel3.setTextSize( t );
            canvas.drawText(vectorDrawable, 80, 140, pincel3);
        }else if(vectorDrawable.length()==2) {
            pincel3.setTextSize( t );
            canvas.drawText(vectorDrawable, 40, 140, pincel3);
        }else {
            pincel3.setTextSize( t *0.7f);
            canvas.drawText(vectorDrawable, 40, 120, pincel3);
        }
        return bitmapx;
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmapBolha(String vectorDrawable, int t) {
        int largura=0;
        int p;
        largura=200;
        p=60;
        Paint pincel1 = new Paint(  );
        Paint pincel2 = new Paint(  );
        Paint pincel3 = new Paint(  );

        pincel1.setColor( Color.argb( 255,255,100,0));
        pincel2.setColor( Color.argb( 155,230,250,0));
        pincel3.setColor( Color.argb( 255,255,255,255));

        Bitmap bitmapx = Bitmap.createBitmap(largura,largura,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapx);

        canvas.drawRoundRect( 0 ,0 ,
                largura*0.95f,largura*0.95f,
                100,100,pincel1 );

        canvas.drawRoundRect( largura*0.1f ,largura*0.1f ,
                largura*0.85f,largura*0.85f,
                100,100,pincel2 );

            pincel3.setTextSize( t );
            canvas.drawText(vectorDrawable, 80, 120, pincel3);

        return bitmapx;
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmapBolha2(String vectorDrawable, int t, int c) {
        int largura=0;
        int p;
        largura=200;
        p=60;
        Paint pincel = new Paint(  );
        Bitmap bitmapx = Bitmap.createBitmap(largura,largura,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapx);
        pincel.setColor( Color.argb( 255,250,90,90));
        canvas.drawRoundRect( 0 ,0 ,
                largura*0.95f,largura*0.95f,
                100,100,pincel );
        pincel.setColor( Color.argb( 155,250,200,100));
        canvas.drawRoundRect( largura*0.1f ,largura*0.1f ,
                largura*0.85f,largura*0.85f,
                100,100,pincel );
        pincel.setColor( Color.argb( 255,150,100,240));
        pincel.setTextSize( t );
        if(vectorDrawable.length()==1) {
            canvas.drawText(vectorDrawable, 80, 140, pincel);
        }else {
            canvas.drawText(vectorDrawable, 40, 140, pincel);
        }
        return bitmapx;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmapBarraxz(Bitmap img, int positionLetra, String palavra, int t) {
        int p=220;
        //img = Bitmap.createScaledBitmap(img,80,75, false);

        Paint pincel = new Paint(  );

        pincel.setColor( Color.argb( 10,23,23,23));


        Bitmap bitmap = Bitmap.createBitmap(600,150,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor( Color.rgb( 10,129,112 ) );


        canvas.drawRoundRect( 15 ,0 ,
                560,120,
                50,50,pincel );
        //canvas.drawBitmap( img,5,(float) 60,pincel );

        pincel.setColor( Color.argb( 255,251,251,251));
        pincel.setTextSize( t );
        canvas.drawText( palavra,p,70,pincel);


        return bitmap;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmapBarra(List<Bitmap> imag, int positionLetra, String palavra, int t, int vida, AssetManager asset) {
        int p=50;

        Bitmap  img = Bitmap.createScaledBitmap(imag.get( 0 ),80,75, false);
        Bitmap  imgs = Bitmap.createScaledBitmap(imag.get( 3 ),65,55, false);

        Paint pincel = new Paint(  );

        pincel.setColor( Color.rgb( 123,163,213));


        Bitmap bitmap = Bitmap.createBitmap(600,150,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor( Color.rgb( 200,129,112 ) );


        canvas.drawRoundRect( 15 ,0 ,
                560,120,
                50,50,pincel );
        canvas.drawBitmap( img,5,(float) 60,pincel );
        canvas.drawBitmap( imag.get( 2 ),89,(float) 70,pincel );
        canvas.drawBitmap( imgs,500,(float) 3,pincel );

        for(int i =0;i<vida;i++) {

            canvas.drawBitmap( imag.get( 1 ), 450+(i*imag.get( 1 ).getWidth()), (float) 70, pincel );
        }
        pincel.setColor( Color.argb( 255,251,251,251));
        pincel.setTextSize( t );
        canvas.drawText( palavra,p,60,pincel);

        String palavraAux="";
        for(int i =0;i<positionLetra;i++) {

            palavraAux=palavraAux+palavra.charAt( i );

        }

        pincel.setColor( Color.argb( 255,245,0,100));
        pincel.setTextSize( t );
        canvas.drawText( palavraAux,p,60,pincel);


        Typeface tf =Typeface.createFromAsset(asset,"fonts/font.otf");
        pincel.setTypeface(tf);


        pincel.setColor( Color.argb( 255,251,251,251));
        pincel.setTextSize( t-10 );
        palavra=palavra.toLowerCase();

        canvas.drawText( palavra,p+125,105,pincel);

        palavraAux="";
        for(int i =0;i<positionLetra;i++) {

            palavraAux=palavraAux+palavra.charAt( i );

        }
        palavraAux=palavraAux.toLowerCase();

        pincel.setColor( Color.argb( 255,245,0,100));
        pincel.setTextSize( t-10 );
        canvas.drawText( palavraAux,p+125,105,pincel);


        return bitmap;
    }





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmap(String palavra) {
        int largura=0;
        switch (palavra.length()){
            case 1:
                largura=200;
                break;

            case 2:
                largura=410;
                break;

            default:
                largura=600;
                break;

        }

        Paint pincel = new Paint(  );

        int r =(int) (175 * Math.random()) + 20;
        int g=(int) (105 * Math.random()) + 80;
        int b = (int) (175 * Math.random()) + 80;
        pincel.setColor( Color.rgb( r,g,b));


        Bitmap bitmap = Bitmap.createBitmap(largura,300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawRoundRect( 0 ,0 ,
                largura,300,
                70,70,pincel );

        pincel.setColor( Color.argb( 255,255,255,255));
        pincel.setTextSize( (float) (250) );
        canvas.drawText( palavra,10,270,pincel);

        return bitmap;
    }









    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  Bitmap getBitmap(float h, float w, long recordeVelhoD, long PalavrasConquistadas) {

        Paint pincel = new Paint();
        pincel.setColor( Color.argb( 120,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80 ) );


        Bitmap bitmap = Bitmap.createBitmap( (int)w,(int)h,Bitmap.Config.ARGB_8888 );
        Canvas canvas = new Canvas( bitmap );

        pincel.setColor( Color.argb( 255,55,130,240 ) );

        canvas.drawRect( 0,0,w,h,pincel );

        pincel.setTextSize( w / 100 * 10 );
        pincel.setColor( Color.argb( 255,255,250,250 ) );
        DecimalFormat formato = new DecimalFormat( "##.###" );
        Number numConvertido = 0;
        try {
            numConvertido = formato.parse( "" +recordeVelhoD );
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        canvas.drawText( "Recorde ",(float) ((float) w * 0.1),(float) (h * 0.4),pincel );
        canvas.drawText( numConvertido.toString() + " ",(float) ((float) w * 0.1),(float) (h * 0.53),pincel );

        canvas.drawText( "Conquista ",(float) ((float)w * 0.1),(float) (h * 0.66),pincel);
        canvas.drawText( PalavrasConquistadas + " ",(float) ((float) w * 0.1),(float) (h * 0.83),pincel );




        pincel.setTextSize( (w / 100) * 12 );
        pincel.setColor( Color.argb( 255,255,250,250 ) );
        canvas.drawText( "FIM DE JOGO ",(float) ((float) w * 0.1),(float) (h * 0.2),pincel );
        pincel.setTextSize( (w / 100) * 5 );


        return bitmap;
    }




    public  Bitmap getBitmapPlacar(Bitmap img,int acerto,int erro) {
        int largura=600;


        Paint pincel = new Paint(  );


        Bitmap bitmap = Bitmap.createBitmap(largura,300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);



        canvas.drawBitmap( img,0,0,pincel );




        pincel.setColor( Color.argb( 255,0,255,0));
        pincel.setTextSize( (float) (70) );
        canvas.drawText( "Acertos",10,70,pincel);
        pincel.setTextSize( (float) (220) );
        canvas.drawText( String.valueOf(acerto),10,270,pincel);

        pincel.setColor( Color.argb( 255,0,0,0));
        pincel.setTextSize( (float) (70) );
        canvas.drawText( "Erros",300,70,pincel);
        pincel.setTextSize( (float) (220) );
        canvas.drawText( String.valueOf(erro),300,270,pincel);
        return bitmap;
    }


    public  Bitmap getBitmapBase(int x , int y,Bitmap seta,String vectorDrawable, int t, int c) {
        int largura=840;
        int p=30;


        Paint pincel = new Paint(  );
        if(c==0) {
            pincel.setColor( Color.rgb( 140,205,205 ) );
        }else  if(c==1) {

            pincel.setColor( Color.rgb( 240,105,55 ) );

        }else  if(c==2) {

            pincel.setColor( Color.argb( 240,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));

        }else  if(c==3) {

            pincel.setColor( Color.argb( 0,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));

        }


        Bitmap bitmap = Bitmap.createBitmap(largura,400,Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas(bitmap);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect( 0 ,0 ,
                    largura,300,
                    0,0,pincel );
        }


        pincel.setColor( Color.argb( 255,255,255,255));
        pincel.setTextSize( t );
        canvas.drawText( vectorDrawable,p,180,pincel);
        canvas.drawBitmap( seta,x,y,pincel );

        return bitmap;
    }



}














