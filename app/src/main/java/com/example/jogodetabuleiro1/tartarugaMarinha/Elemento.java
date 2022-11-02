package com.example.jogodetabuleiro1.tartarugaMarinha;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by wanderson on 08/10/17.
 */

public class Elemento {
    private double x;
    private double y;
    private int w;
    private int h;
    private boolean bateu;
    private boolean passou=true;
    private Paint pintar;
    private Bitmap img01;
    private Bitmap img01A;
    private boolean proximidade;
    private Bitmap img02;
    private Bitmap img02A;
    private  int perigo;
    private Bitmap img03;
    private Bitmap img03A;
    private boolean Ima;
    private  int time;
    private float r1,r2,r3;
    //private Redimencionar redimencionar = new Redimencionar();
    private  double alteraTamanho= w *0.15;
    private boolean vai=true;
    private int velocidade=6;
    private  int rotacao ;
    public Elemento(double x,double y,double v,double v1,Bitmap img,Bitmap bitmap,Object img3,int rotacao) {
    }

     public Elemento(double x,double y,int w,int h,Bitmap img,Bitmap img2,Bitmap img3,int rotacao) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
         this.img01 = img;
         this.img02 =img2;
         this.img03 =img3;
         this.rotacao=rotacao;

    }

    public boolean isPassou() {
        return passou;
    }

    public void setPassou(boolean passou) {
        this.passou = passou;
    }

    public boolean isBateu() {
        return this.bateu;
    }

    public boolean isProximidade() {
        return this.proximidade;
    }

    public void setProximidade(boolean proximidade) {
        this.proximidade = proximidade;
    }

    public void setBateu(boolean bateu) {
        this.bateu = bateu;
    }


    public Bitmap getimg01() {
        return this.img01;
    }

    public void setimg01(Bitmap img01) {
        this.img01 = img01;
        this.img01 = Bitmap.createScaledBitmap(img01, this.w, this.h, false);

    }

    public boolean isIma() {
        return this.Ima;
    }

    public void setIma(boolean ima) {
        this.Ima = ima;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public Bitmap getimg02() {
        return this.img02;
    }

    public void setimg02(Bitmap img02) {
        this.img02 = img02;
    }

    public Bitmap getImg01() {
        return this.img01;
    }

    public void setImg01(Bitmap img01) {
        this.img01 = img01;
    }

    public Bitmap getImg01A() {
        return this.img01A;
    }

    public void setImg01A(Bitmap img01A) {
        this.img01A = img01A;
    }

    public Bitmap getImg02() {
        return this.img02;
    }

    public void setImg02(Bitmap img02) {
        this.img02 = img02;
    }

    public Bitmap getImg02A() {
        return this.img02A;
    }

    public void setImg02A(Bitmap img02A) {
        this.img02A = img02A;
    }

    public Bitmap getImg03() {
        return this.img03;
    }

    public void setImg03(Bitmap img03) {
        this.img03 = img03;
    }

    public Bitmap getImg03A() {
        return this.img03A;
    }

    public void setImg03A(Bitmap img03A) {
        this.img03A = img03A;
    }

    public int getRotacao() {
        return this.rotacao;
    }

    public void setRotacao(int rotacao) {
        this.rotacao = rotacao;
    }

    public void onDraw(Canvas c) {
        this.pintar = new Paint();

        if(this.proximidade || this.isIma()){


            this.perigo++;
            if(this.isIma()==false){
                pintar.setColor( Color.WHITE );
                pintar.setStyle( Paint.Style.STROKE );
                pintar.setStrokeWidth(4.5f);
            }
            else {
                pintar.setColor( Color.argb( 50,250,250,250 ) );
                pintar.setStyle( Paint.Style.FILL );
                pintar.setStrokeWidth(4.5f);
        }


                c.drawCircle( (float) (this.x + this.w /2),(float) this.y +(float) (this.getH()*0.2), this.getH()+ this.perigo -(float) (this.getH()*0.5), pintar );
                c.drawCircle( (float) (this.x + this.w /2),(float) this.y +(float) (this.getH()*0.2), this.getH()+ this.perigo -(float) (this.getH()*0.25), pintar );

                c.drawCircle( (float) (this.x + this.w /2),(float) this.y +(float) (this.getH()*0.2), this.getH()+ this.perigo, pintar );



            if(this.perigo >=40){
                perigo =0;
            }

        }
        pintar.setColor( Color.rgb( 250,250,250 ) );
        pintar.setStyle( Paint.Style.FILL );
        if(img02 ==null) {
            img01A =Bitmap.createScaledBitmap( img01, w, h, false);
            c.drawBitmap( this.img01A,(float) this.x,(float) this.y, this.pintar );
        }
        else if(img03 ==null) {
            if(vai) {
                if(time >= this.velocidade) {
                    if (alteraTamanho > -(w * 0.2)) {
                        alteraTamanho -= 2;
                    } else {
                        vai = false;
                    }
                    time =4;
                }
                else {
                    time++;
                }
            }else {
                if(time >= this.velocidade) {

                    if (alteraTamanho < w * 0.2) {
                        alteraTamanho += 2;
                    } else {
                        vai = true;
                    }
                    time =4;
                }
                else {
                    time++;
                }
            }


            img02A = Bitmap.createScaledBitmap( img02,(int) (w * 2 + alteraTamanho),(int) (h * 0.25 + alteraTamanho),false );
                c.drawBitmap( this.img02A,(float) ((float) this.x - this.w / 2 - alteraTamanho + this.w * 0.03),(float) ((float) this.y + h * 0.25), this.pintar );

            img01A =Bitmap.createScaledBitmap( img01, w, h, false);
            c.drawBitmap( this.img01A,(float) this.x,(float) this.y, this.pintar );

        }
        else {
            c.save();
            c.rotate( this.rotacao,(int) this.x + this.w /2,(int) this.y + this.h /2 );

            if(vai){
                r1 -=0.8;
                r2 = (float) (r1 -1.6);
                r3 = r2 +1;
                if(r1 <-10){
                    vai =false;}
            }
            else {
                r1 +=0.8;
                r2 = (float) (r1 +1.6);
                r3 = r2 -1;
                if (r1 > 10){
                    vai =true;
                }
            }



                c.save();
                c.rotate( this.r3,(int) this.x + this.w /2,(int) this.y + this.h /2 );
            img03A = Bitmap.createScaledBitmap( img03,(int) (w *0.5), h,false );
                c.drawBitmap( this.img03A,(float) ((float) this.x + w * 0.22),(float) ((float) this.y + this.h *1.3), this.pintar );
                c.restore();



            c.save();
            c.rotate( this.r1,(int) this.x + this.w /2,(int) this.y + this.h /2 );
            img01A =Bitmap.createScaledBitmap( img01,(int) (w *1.5), h, false);
            c.drawBitmap( this.img01A,(float) ((float) this.x - w *0.25),(float) this.y, this.pintar );
            c.restore();

            c.save();
            c.rotate( this.r2,(int) this.x + this.w /2,(int) this.y + this.h /2 );
            img02A = Bitmap.createScaledBitmap( img02, w, h,false );
            c.drawBitmap( this.img02A,(float) this.x,(float) ((float) this.y + this.h *0.6), this.pintar );
            c.restore();
            c.restore();

        }
    }
}
