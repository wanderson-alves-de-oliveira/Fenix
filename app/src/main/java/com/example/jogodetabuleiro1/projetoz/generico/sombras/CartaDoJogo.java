package com.example.jogodetabuleiro1.projetoz.generico.sombras;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.jogodetabuleiro1.projetoz.generico.recursos.ConvertBitimap;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d;

import java.util.ArrayList;


/**
 * Created by wanderson on 04/03/18.
 */

public class CartaDoJogo implements Desenho {
    private double x;
    private double y;
    private int w;
    private int h;
    private boolean bateu;
    private boolean passou=true;
    private int valor;
    private int num;
    private Paint pintar;
    private boolean clicou=true;
    private Bitmap img;
    private Bitmap imgR;
    private String nome;
    private MediaPlayer som;
    private String cor="";
    private Objeto3d objeto3dx;
    private ArrayList<CartaDoJogo> silabas;
    private ArrayList<CartaDoJogo> letras;

    private String silaba="";
    private String letra="";


    public CartaDoJogo() {
    }
    public CartaDoJogo(double x,double y,int w,int h,boolean bateu,boolean passou,int valor,Bitmap img,Paint pintar) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.bateu = bateu;
        this.passou = passou;
        this.valor = valor;
        this.pintar = pintar;
        this.imgR =img;

        this.img = Bitmap.createScaledBitmap(img, w, h, false);




    }


    public CartaDoJogo(double x,double y,int w,int h,boolean bateu,boolean passou,int valor,Bitmap img,Paint pintar,String silabaOuLetra) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.bateu = bateu;
        this.passou = passou;
        this.valor = valor;
        this.pintar = pintar;
        this.imgR =img;

        this.img = Bitmap.createScaledBitmap(img, w, h, false);

        this.silaba =silabaOuLetra;
        this.letra =silabaOuLetra;


    }

    public ArrayList<CartaDoJogo> getLetras() {
        return this.letras;
    }

    public void setLetras(ArrayList<CartaDoJogo> letras) {
        this.letras = letras;
    }

    public String getLetra() {
        return this.letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CartaDoJogo(Context context,double x,double y,int w,int h,boolean bateu,boolean passou,int valor,Bitmap img,MediaPlayer som,Paint pintar,String nome,String cor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.cor=cor;
        silabas = new ArrayList<>();
        letras = new ArrayList<>();

        this.bateu = bateu;
        this.passou = passou;
        this.valor = valor;
        this.pintar = pintar;
        this.imgR =img;
        this.img = Bitmap.createScaledBitmap(img, w, h, false);
        this.nome=nome;
        this.som = som;

        String currentLine=nome.split(" ")[1];
        String[] silabasx =new String[currentLine.split("-").length];

        for(int i = 0;i<silabasx.length;i++){

            silabasx[i]=currentLine.split("-")[i];

             Bitmap bitmap= ConvertBitimap.getBitmap(silabasx[i]);

            silabas.add( new CartaDoJogo( 30, h * 1,(int) (this.w *0.65),(int) (this.w *0.65),false,false,12,bitmap,pintar,silabasx[i] ));

        }
         currentLine=nome.split(" ")[0];

        for(int i = 0;i<currentLine.length();i++){

            Bitmap bitmap= ConvertBitimap.getBitmap( String.valueOf( currentLine.charAt( i ) ) );

            letras.add( new CartaDoJogo( 30, h * 1,(int) (this.w *0.65),(int) (this.w *0.65),false,false,12,bitmap,pintar,String.valueOf( currentLine.charAt( i ) ) ));

        }


    }
    public void setObjeto(CartaDoJogo carta){
        this.x = carta.getX();
        this.y = carta.getY();
        this.w = carta.getW();
        this.h = carta.getH();
        this.bateu = carta.isBateu();
        this.passou = carta.isPassou();
        this.valor = carta.getValor();
        this.pintar = carta.getPintar();
        this.img = carta.getImg();

    }

    public Bitmap getImgR() {
        return this.imgR;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setImgR(Bitmap imgR) {
        this.imgR = imgR;
    }

    public MediaPlayer getSom() {
        return this.som;
    }

    public String getNome() {
        return this.nome;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isClicou() {
        return this.clicou;
    }

    public void setClicou(boolean clicou) {
        this.clicou = clicou;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
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

    public void setSom(MediaPlayer som) {
        this.som = som;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isBateu() {
        return this.bateu;
    }

    public void setBateu(boolean bateu) {
        this.bateu = bateu;
    }

    public boolean isPassou() {
        return this.passou;
    }

    public void setPassou(boolean passou) {
        this.passou = passou;
    }

    public Paint getPintar() {
        return this.pintar;
    }

    public void setPintar(Paint pintar) {
        this.pintar = pintar;
    }

    public Bitmap getImg() {
        return this.img;
    }

    public void setImg(Bitmap img) {
        this.img = Bitmap.createScaledBitmap( imgR, w, h, false);

    }

    public ArrayList<CartaDoJogo> getSilabas() {
        return this.silabas;
    }

    public String getSilaba() {
        return this.silaba;
    }

    public void setSilaba(String silaba) {
        this.silaba = silaba;
    }

    public void setSilabas(ArrayList<CartaDoJogo> silabas) {
        this.silabas = silabas;
    }

    @Override
    public String toString() {
        return "CartaDoJogo{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                ", bateu=" + bateu +
                ", passou=" + passou +
                ", valor=" + valor +
                ", pintar=" + pintar +
                ", img=" + img +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CartaDoJogo bola = (CartaDoJogo) o;

        if (Double.compare( bola.x, this.x ) != 0) return false;
        if (Double.compare( bola.y, this.y ) != 0) return false;
        if (this.w != bola.w) return false;
        if (this.h != bola.h) return false;
        if (this.bateu != bola.bateu) return false;
        if (this.passou != bola.passou) return false;
        if (this.valor != bola.valor) return false;
        if (this.pintar != null ? !this.pintar.equals( bola.pintar ) : bola.pintar != null) return false;
        return this.img.equals( bola.img );

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits( this.x );
        result = (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits( this.y );
        result = 31 * result + (int) (temp ^ temp >>> 32);
        result = 31 * result + this.w;
        result = 31 * result + this.h;
        result = 31 * result + (this.bateu ? 1 : 0);
        result = 31 * result + (this.passou ? 1 : 0);
        result = 31 * result + this.valor;
        result = 31 * result + this.pintar.hashCode();
        result = 31 * result + (this.clicou ? 1 : 0);
        result = 31 * result + this.img.hashCode();
        return result;
    }

    @Override
    public void onDraw(Canvas canvas,Paint p) {

       // Paint pincel = new Paint(  );
       // pincel.setColor( Color.RED );
       // canvas.drawCircle( (float) this.getX()+100,(float) this.getY()+100,100,pintar );
       // canvas.drawRect((float) getX()+(getW()/2),(float) getY()+(getH()/2),(float) getX()+(getW()/2)+20,(float) getY()+(getH()/2)+20,pincel );

       // pincel.setTextSize( 64 );
      //  canvas.drawText( String.valueOf( this.valor ),((float)this.x),((float)this.y),pincel);

        canvas.drawBitmap( this.img, (float) this.x, (float) this.y, this.pintar );
    }


    public void onDraw(Paint pintar,Canvas canvas) {

        Paint pincel = new Paint(  );
        pincel.setColor( Color.argb( 120, 255,255,255));
        // canvas.drawCircle( (float) this.getX()+100,(float) this.getY()+100,100,pintar );
        // canvas.drawRect((float) getX()+(getW()/2),(float) getY()+(getH()/2),(float) getX()+(getW()/2)+20,(float) getY()+(getH()/2)+20,pincel );

        pincel.setTextSize( (float) (this.w *0.40) );

        canvas.drawBitmap( this.img, (float) this.x, (float) this.y,this.pintar);
        canvas.drawText( String.valueOf( this.valor ),(float) ((float) this.x + this.w /3.5), (float) this.y + this.h /2,pincel);
    }
}
