package com.example.jogodetabuleiro1.projetoz.generico.memoria;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;


/**
 * Created by wanderson on 16/11/17.
 */

public class Animaisx {
    private float x;
    private float y;
    private float x3d;
    private float y3d;

    private int w;
    private int h;
    private int cluic;
    private int alpha=255;
    private Bitmap img;
    private Bitmap imgP;
    private com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d Objeto3d;
    private boolean passou=true;
    private boolean clicou=true;

    private Paint pintar;
    private boolean bateu;
    private String valor="";
    MediaPlayer son;
    public Animaisx() {
    }

    public float getX3d() {
        return this.x3d;
    }

    public void setX3d(float x3d) {
        this.x3d = x3d;
    }

    public float getY3d() {
        return this.y3d;
    }

    public void setY3d(float y3d) {
        this.y3d = y3d;
    }

    public boolean isPassou() {
        return this.passou;
    }

    public void setPassou(boolean passou) {
        this.passou = passou;
    }

    public boolean isClicou() {
        return this.clicou;
    }

    public void setClicou(boolean clicou) {
        this.clicou = clicou;
    }

    public int getCluic() {
        return cluic;
    }

    public void setCluic(int cluic) {
        this.cluic = cluic;
    }

    public Bitmap getImgP() {
        return imgP;
    }

    public void setImgP(Bitmap imgP) {
        this.imgP = imgP;
    }

    public Animaisx(float x3d, float y3d, float x, float y, int w, int h, String valor, com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d Objeto3d) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.x3d = x3d;
        this.y3d = y3d;
        this.valor=valor;
  this.Objeto3d=Objeto3d;

        this.son=son;
    }
    public Animaisx(float x3d, float y3d, float x, float y, int w, int h, String valor,   MediaPlayer son) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.x3d = x3d;
        this.y3d = y3d;
        this.valor=valor;

        this.son=son;
    }
    public Animaisx(float x,float y,int w,int h,String valor,Bitmap img,MediaPlayer son) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.valor=valor;
        if (this.img != null) {
            img.recycle();img=null;}
        this.img = img;

        this.son=son;
    }


    public com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d getObjeto3d() {
        return this.Objeto3d;
    }

    public void setObjeto3d(com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d Objeto3d) {
        this.Objeto3d = Objeto3d;
    }

    public String getValor() {
        return valor;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public MediaPlayer getSon() {
        return son;
    }

    public void setSon(MediaPlayer son) {
        this.son = son;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean isBateu() {
        return bateu;
    }

    public void setBateu(boolean bateu) {
        this.bateu = bateu;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {

        if (this.img != null) {
            this.img.recycle();this.img=null;}

        this.img = img;
    }

    public void Son() {
        this.son.start();
    }

    public void onDraw(Canvas c) {
        c.drawBitmap( this.img, this.x, this.y, this.pintar );
    }
}
