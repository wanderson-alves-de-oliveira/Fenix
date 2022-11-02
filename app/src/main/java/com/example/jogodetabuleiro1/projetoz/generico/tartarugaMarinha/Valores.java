package com.example.jogodetabuleiro1.projetoz.generico.tartarugaMarinha;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;


/**
 * Created by wanderson on 11/10/17.
 */

public class Valores {
    private int x;
    private double y;
    private int w;
    private int h;
    private int valor;
    private int visita;
    private String num = "";
    private ArrayList<Bitmap> numeros = new ArrayList();
    private Paint pincel;
    private boolean errou;
    public Valores(
            int x, double y, int w, int h, int valor, ArrayList img) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        numeros = img;
        this.valor = valor;
        num = String.valueOf(valor);
        this.pincel = new Paint(  );
        this.pincel.setColor( Color.argb( 70,30,212,190 ) );
        this.pincel.setStyle( Paint.Style.STROKE );
        this.pincel.setStrokeWidth(4.5f);


        //img2 = redimencionar.redefinirTamanho(img,getW(),getH()) ;
    }



    public ArrayList<Bitmap> getNumeros() {
        return this.numeros;
    }

    public boolean isErrou() {
        return this.errou;
    }

    public void setErrou(boolean errou) {
        this.errou = errou;
    }

    public void setNumeros(ArrayList<Bitmap> numeros) {
        this.numeros = numeros;
    }

    public Paint getPincel() {
        return this.pincel;
    }

    public void setPincel(Paint pincel) {
        this.pincel = pincel;
    }

    public int getVisita() {
        return this.visita;
    }

    public void setVisita(int visita) {
        this.visita = visita;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
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

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
        num = String.valueOf(valor);
        this.w = this.num.length() * this.numeros.get(2).getWidth();

    }

    public void onDraw(Canvas c,Paint p) {
        p.setColor( Color.argb( 255,0,0,0 ) );
        p.setStyle( Paint.Style.STROKE );
        p.setStrokeWidth(4.5f);
        int hh= getH();
        if(this.num.length()>1 && this.valor >=0) {
            hh= this.h * this.num.length();
        }
        if(this.valor >=0) {
            c.drawCircle( (float) ((this.x + this.w / 2) - this.w * 0.02),(float) ((float) this.y + (float) (this.getH() * 0.2) - this.getH() * 0.02),
                    (float) (hh * 1),p );

            c.drawCircle( this.x + this.w / 2,(float) this.y + (float) (this.getH() * 0.2),(float) (hh * 0.8), pincel );

        }else {
            c.drawBitmap( this.numeros.get( numeros.size() - 1 ),(float) this.x,(float) this.y,p );

        }
        p.setStyle( Paint.Style.FILL );

        p.setColor(Color.argb(255, 0, 0, 0));
        if(this.valor >=0) {
            try {

                if (this.errou == false) {
                    for (int pp = 0; pp < this.num.length(); pp++) {
                        for (int i = 0; i < this.numeros.size(); i++) {
                            if (i == Integer.parseInt( String.valueOf( this.num.charAt( pp ) ) )) {
                                if (pp > 0) {

                                    c.drawBitmap( this.numeros.get( i ),(float) this.x + pp * this.w / 2,(float) this.y,p );
                                } else {
                                    c.drawBitmap( this.numeros.get( i ),(float) this.x,(float) this.y,p );

                                }

                            }
                        }
                    }
                } else {
                    c.drawBitmap( this.numeros.get( numeros.size() - 2 ),(float) this.x,(float) this.y,p );

                }
            } catch (Exception e) {
            }
        }




    }

}
