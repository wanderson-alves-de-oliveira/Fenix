package com.example.jogodetabuleiro1.projetoz.generico.sombras;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

import com.example.jogodetabuleiro1.R;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.ConvertBitimap;

import java.util.ArrayList;

/**
 * Created by wanderson on 08/07/18.
 */

public class CarregarSombras {
    private Bitmap carta0;
    private Bitmap carta1;
    private Bitmap carta2;
    private Bitmap carta3;
    private Bitmap carta4;
    private Bitmap carta5;
    private Bitmap carta6;
    private Bitmap carta7;
    private Bitmap carta10;
    private Bitmap carta9;
    private Bitmap carta11;
    private Bitmap carta8;

    private Bitmap cartaB0;
    private Bitmap cartaB1;
    private Bitmap cartaB2;
    private Bitmap cartaB3;
    private Bitmap cartaB4;
    private Bitmap cartaB5;
    private Bitmap cartaB6;
    private Bitmap cartaB7;
    private Bitmap cartaB10;
    private Bitmap cartaB9;
    private Bitmap cartaB11;
    private Bitmap cartaB8;
    private Bitmap balao;

    private MediaPlayer cartaS0;
    private  MediaPlayer cartaS1;
    private  MediaPlayer cartaS2;
    private  MediaPlayer cartaS3;
    private  MediaPlayer cartaS4;
    private  MediaPlayer cartaS5;
    private  MediaPlayer cartaS6;
    private MediaPlayer cartaS7;
    private  MediaPlayer cartaS8;
    private  MediaPlayer cartaS9;
    private MediaPlayer cartaS10;
    private MediaPlayer cartaS11;
    private ArrayList<CartaDoJogo> cartas;

    private ArrayList<CartaDoJogo> sombrasx;
    private ArrayList<CartaDoJogo> baloes;
    private ArrayList<Double> yCartas;
    private ArrayList<Double> xCartas;
    private final DisplayMetrics displayMetrics  ;
    private final int h  ;
    private final int w  ;
    private  String tipo;
    Context context;
    private Paint pintar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CarregarSombras(Context context,DisplayMetrics dsp,Paint pintar,String tipo) {
        displayMetrics = dsp;
        this.context= context;
        this.tipo = tipo;
        this.pintar=pintar;
        h = this.displayMetrics.heightPixels;
        w = this.displayMetrics.widthPixels;

        cartas = null;
        sombrasx = null;
        baloes = null;

        cartas = new ArrayList<>();
        sombrasx = new ArrayList<>();
        baloes = new ArrayList<>();

        this.Carregar();
        sombrasx.add( new CartaDoJogo( 10,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,1, cartaB0,pintar ) );
        sombrasx.add( new CartaDoJogo( 220,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,2, cartaB3,pintar ) );
        sombrasx.add( new CartaDoJogo( 460,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,3, cartaB7,pintar ) );
        sombrasx.add( new CartaDoJogo( 10,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,4, cartaB5,pintar ) );
        sombrasx.add( new CartaDoJogo( 220,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,5, cartaB2,pintar ) );
        sombrasx.add( new CartaDoJogo( 460,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,6, cartaB1,pintar ) );
        sombrasx.add( new CartaDoJogo( 10,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,7, cartaB4,pintar ) );
        sombrasx.add( new CartaDoJogo( 220,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,8, cartaB6,pintar ) );
        sombrasx.add( new CartaDoJogo( 220,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,9, cartaB8,pintar ) );
        sombrasx.add( new CartaDoJogo( 220,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,10, cartaB10,pintar ) );
        sombrasx.add( new CartaDoJogo( 220,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,11, cartaB9,pintar ) );
        sombrasx.add( new CartaDoJogo( 220,(int) (h * 0.7), this.w / 3, this.w / 3,false,false,12, cartaB11,pintar ) );




        for (int i = 0; i <= 5; i++) {
            balao = ConvertBitimap.getBitmap( context, R.drawable.ic_balao,220,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80,true );

            baloes.add( new CartaDoJogo( (int) (w * 0.8 * Math.random()) + 0, (int) (this.h * 1.5 * Math.random()) + 0 + h * 1, this.w / 4, this.w / 3,true,true,i + 1, balao,pintar ) );

        }
        xCartas = new ArrayList<>();
        yCartas = new ArrayList<>();



        for (int i = 0; i <= 11; i++) {
            int ran = (int) (11 * Math.random());
            double x = cartas.get( i ).getX();
            double y = cartas.get( i ).getY();

            cartas.get( i ).setY( cartas.get( ran ).getY() );
            cartas.get( i ).setX( cartas.get( ran).getX() );

            cartas.get( ran ).setY(y );
            cartas.get( ran ).setX(x );



        }
        for (int i = 0; i <= 11; i++) {

            yCartas.add( cartas.get( i ).getY() );
            xCartas.add( cartas.get( i ).getX() );

        }


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Carregar () {
if(tipo.equals( "animal" )) {


   // Resources rex= context.getResources();

    //String[] img = rex.getStringArray(R.drawable.tipos_de_imagens );

    carta0 = ConvertBitimap.getBitmap( context, R.drawable.ic_leao,255,0,0,0,false );
    carta1 = ConvertBitimap.getBitmap( context, R.drawable.ic_tigre,255,0,0,0,false );
    carta2 = ConvertBitimap.getBitmap( context, R.drawable.ic_zebra_470305,255,0,0,0,false );
    carta3 = ConvertBitimap.getBitmap( context, R.drawable.ic_elephant_1842344,255,0,0,0,false );
    carta4 = ConvertBitimap.getBitmap( context, R.drawable.ic_rino,255,0,0,0,false );
    carta5 = ConvertBitimap.getBitmap( context, R.drawable.ic_hipo,255,0,0,0,false );
    carta6 = ConvertBitimap.getBitmap( context, R.drawable.ic_camelo,255,0,0,0,false );
    carta7 = ConvertBitimap.getBitmap( context, R.drawable.ic_cavalo,255,0,0,0,false );
    carta8 = ConvertBitimap.getBitmap( context, R.drawable.ic_girafa,255,0,0,0,false );
    carta9 = ConvertBitimap.getBitmap( context, R.drawable.ic_touro,255,0,0,0,false );
    carta10 = ConvertBitimap.getBitmap( context, R.drawable.ic_vaca,255,0,0,0,false );
    carta11 = ConvertBitimap.getBitmap( context, R.drawable.ic_panda,255,0,0,0,false );

    cartaB0 = ConvertBitimap.getBitmap( context, R.drawable.ic_leao,210,0,0,0,true );
    cartaB1 = ConvertBitimap.getBitmap( context, R.drawable.ic_tigre,210,0,0,0,true );
    cartaB2 = ConvertBitimap.getBitmap( context, R.drawable.ic_zebra_470305,210,0,0,0,true );
    cartaB3 = ConvertBitimap.getBitmap( context, R.drawable.ic_elephant_1842344,210,0,0,0,true );
    cartaB4 = ConvertBitimap.getBitmap( context, R.drawable.ic_rino,210,0,0,0,true );
    cartaB5 = ConvertBitimap.getBitmap( context, R.drawable.ic_hipo,210,0,0,0,true );
    cartaB6 = ConvertBitimap.getBitmap( context, R.drawable.ic_camelo,210,0,0,0,true );
    cartaB7 = ConvertBitimap.getBitmap( context, R.drawable.ic_cavalo,210,0,0,0,true );
    cartaB8 = ConvertBitimap.getBitmap( context, R.drawable.ic_girafa,210,0,0,0,true );
    cartaB9 = ConvertBitimap.getBitmap( context, R.drawable.ic_touro,210,0,0,0,true );
    cartaB10 = ConvertBitimap.getBitmap( context, R.drawable.ic_vaca,210,0,0,0,true );
    cartaB11 = ConvertBitimap.getBitmap( context, R.drawable.ic_panda,210,0,0,0,true );


    cartaS0 = MediaPlayer.create( context, R.raw.leao );
    cartaS1 = MediaPlayer.create( context, R.raw.tigre );
    cartaS2 = MediaPlayer.create( context, R.raw.zebra );
    cartaS3 = MediaPlayer.create( context, R.raw.elefante );
    cartaS4 = MediaPlayer.create( context, R.raw.rino );
    cartaS5 = MediaPlayer.create( context, R.raw.hipo );
    cartaS6 = MediaPlayer.create( context, R.raw.camelo );
    cartaS7 = MediaPlayer.create( context, R.raw.cavalo );
    cartaS8 = MediaPlayer.create( context, R.raw.vaca );
    cartaS9 = MediaPlayer.create( context, R.raw.touro );
    cartaS10 = MediaPlayer.create( context, R.raw.panda );
    cartaS11 = MediaPlayer.create( context, R.raw.girafa );

    cartas.add( new CartaDoJogo(context, (int) (w * 0.07),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,1, carta0, cartaS0,pintar,"LEÃO LE-ÃO" ,"120,120,222") );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.30),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,2, carta3, cartaS3,pintar,"ELEFANTE E-LE-FAN-TE","140,222,120" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.60),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,3, carta7, cartaS7,pintar,"CAVALO CA-VA-LO" ,"222,120,120") );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.05),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,4, carta5, cartaS5,pintar,"HIPOPÓTAMO HI-PO-PÓ-TA-MO","143,232,170" ) );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.30),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,5, carta2, cartaS2,pintar,"ZEBRA ZE-BRA" ,"120,220,100") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.60),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,6, carta1, cartaS1,pintar,"TIGRE TI-GRE","120,120,255" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.05),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,7, carta4, cartaS4,pintar,"RINOCERONTE RI-NO-CE-RON-TE","180,180,0" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.35),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,8, carta6, cartaS6,pintar,"CAMELO CA-ME-LO" ,"0,120,120") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.6),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,9, carta8, cartaS11,pintar,"GIRAFA GI-RA-FA" ,"190,0,170") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.06),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,10, carta10, cartaS8,pintar,"VACA VA-CA" ,"150,170,190") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.3),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,11, carta9, cartaS9,pintar,"TOURO TOU-RO" ,"100,200,255") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.6),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,12, carta11, cartaS10,pintar,"PANDA PAN-DA","111,123,198" ) );



}else if(tipo.equals( "brinquedo" )) {


   // Resources rex= context.getResources();

    //String[] img = rex.getStringArray(R.drawable.tipos_de_imagens );

    carta0 = ConvertBitimap.getBitmap( context, R.drawable.ic_bola,255,0,0,0,false );
    carta1 = ConvertBitimap.getBitmap( context, R.drawable.ic_boneca,255,0,0,0,false );
    carta2 = ConvertBitimap.getBitmap( context, R.drawable.ic_aviao,255,0,0,0,false );
    carta3 = ConvertBitimap.getBitmap( context, R.drawable.ic_pipa,255,0,0,0,false );
    carta4 = ConvertBitimap.getBitmap( context, R.drawable.ic_carro,255,0,0,0,false );
    carta5 = ConvertBitimap.getBitmap( context, R.drawable.ic_urso,255,0,0,0,false );
    carta6 = ConvertBitimap.getBitmap( context, R.drawable.ic_boliche,255,0,0,0,false );
    carta7 = ConvertBitimap.getBitmap( context, R.drawable.ic_domino,255,0,0,0,false );
    carta8 = ConvertBitimap.getBitmap( context, R.drawable.ic_dado,255,0,0,0,false );
    carta9 = ConvertBitimap.getBitmap( context, R.drawable.ic_bicicleta,255,0,0,0,false );
    carta10 = ConvertBitimap.getBitmap( context, R.drawable.ic_patinsx,255,0,0,0,false );
    carta11 = ConvertBitimap.getBitmap( context, R.drawable.ic_barco,255,0,0,0,false );

    cartaB0 = ConvertBitimap.getBitmap( context, R.drawable.ic_bola,210,0,0,0,true );
    cartaB1 = ConvertBitimap.getBitmap( context, R.drawable.ic_boneca,210,0,0,0,true );
    cartaB2 = ConvertBitimap.getBitmap( context, R.drawable.ic_aviao,210,0,0,0,true );
    cartaB3 = ConvertBitimap.getBitmap( context, R.drawable.ic_pipa,210,0,0,0,true );
    cartaB4 = ConvertBitimap.getBitmap( context, R.drawable.ic_carro,210,0,0,0,true );
    cartaB5 = ConvertBitimap.getBitmap( context, R.drawable.ic_urso,210,0,0,0,true );
    cartaB6 = ConvertBitimap.getBitmap( context, R.drawable.ic_boliche,210,0,0,0,true );
    cartaB7 = ConvertBitimap.getBitmap( context, R.drawable.ic_domino,210,0,0,0,true );
    cartaB8 = ConvertBitimap.getBitmap( context, R.drawable.ic_dado,210,0,0,0,true );
    cartaB9 = ConvertBitimap.getBitmap( context, R.drawable.ic_bicicleta,210,0,0,0,true );
    cartaB10 = ConvertBitimap.getBitmap( context, R.drawable.ic_patinsx,210,0,0,0,true );
    cartaB11 = ConvertBitimap.getBitmap( context, R.drawable.ic_barco,210,0,0,0,true );


    cartaS0 = MediaPlayer.create( context, R.raw.bola );
    cartaS1 = MediaPlayer.create( context, R.raw.boneca );
    cartaS2 = MediaPlayer.create( context, R.raw.aviao );
    cartaS3 = MediaPlayer.create( context, R.raw.pipa );
    cartaS4 = MediaPlayer.create( context, R.raw.carrinho );
    cartaS5 = MediaPlayer.create( context, R.raw.urso );
    cartaS6 = MediaPlayer.create( context, R.raw.boliche );
    cartaS7 = MediaPlayer.create( context, R.raw.domino );
    cartaS8 = MediaPlayer.create( context, R.raw.dado );
    cartaS9 = MediaPlayer.create( context, R.raw.bicicleta );
    cartaS10 = MediaPlayer.create( context, R.raw.patins );
    cartaS11 = MediaPlayer.create( context, R.raw.barco );


    cartas.add( new CartaDoJogo(context, (int) (w * 0.07),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,1, carta0, cartaS0,pintar,"BOLA BO-LA" ,"120,120,222") );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.30),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,2, carta3, cartaS3,pintar,"PIPA PI-PA","140,222,120" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.60),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,3, carta7, cartaS7,pintar,"DOMINÓ DO-MI-NÓ" ,"222,120,120") );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.05),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,4, carta5, cartaS5,pintar,"URSO UR-SO","243,232,170" ) );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.30),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,5, carta2, cartaS2,pintar,"AVIÃO A-VI-ÃO" ,"120,220,100") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.60),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,6, carta1, cartaS1,pintar,"BONECA BO-NE-CA","120,120,255" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.05),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,7, carta4, cartaS4,pintar,"CARRINHO CAR-RI-NHO","180,180,0" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.35),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,8, carta6, cartaS6,pintar,"BOLICHE BO-LI-CHE" ,"0,120,120") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.6),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,9, carta8, cartaS8,pintar,"DADO DA-DO" ,"190,0,170") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.06),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,10, carta10, cartaS10,pintar,"PATINS PA-TINS" ,"150,250,190") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.3),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,11, carta9, cartaS9,pintar,"BICICLETA BI-CI-CLE-TA" ,"200,200,255") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.6),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,12, carta11, cartaS11,pintar,"BARCO BAR-CO","111,123,198" ) );


}else if(tipo.equals( "fruta" )) {


    // Resources rex= context.getResources();

    //String[] img = rex.getStringArray(R.drawable.tipos_de_imagens );

    carta0 = ConvertBitimap.getBitmap( context, R.drawable.ic_maca,255,0,0,0,false );
    carta1 = ConvertBitimap.getBitmap( context, R.drawable.ic_banana,255,0,0,0,false );
    carta2 = ConvertBitimap.getBitmap( context, R.drawable.ic_pera,255,0,0,0,false );
    carta3 = ConvertBitimap.getBitmap( context, R.drawable.ic_goiaba,255,0,0,0,false );
    carta4 = ConvertBitimap.getBitmap( context, R.drawable.ic_uva,255,0,0,0,false );
    carta5 = ConvertBitimap.getBitmap( context, R.drawable.ic_melancia,255,0,0,0,false );
    carta6 = ConvertBitimap.getBitmap( context, R.drawable.ic_abacaxi,255,0,0,0,false );
    carta7 = ConvertBitimap.getBitmap( context, R.drawable.ic_morango,255,0,0,0,false );
    carta8 = ConvertBitimap.getBitmap( context, R.drawable.ic_cereja,255,0,0,0,false );
    carta9 = ConvertBitimap.getBitmap( context, R.drawable.ic_laranja,255,0,0,0,false );
    carta10 = ConvertBitimap.getBitmap( context, R.drawable.ic_abacate,255,0,0,0,false );
    carta11 = ConvertBitimap.getBitmap( context, R.drawable.ic_mamao,255,0,0,0,false );

    cartaB0 = ConvertBitimap.getBitmap( context, R.drawable.ic_maca,210,0,0,0,true );
    cartaB1 = ConvertBitimap.getBitmap( context, R.drawable.ic_banana,210,0,0,0,true );
    cartaB2 = ConvertBitimap.getBitmap( context, R.drawable.ic_pera,210,0,0,0,true );
    cartaB3 = ConvertBitimap.getBitmap( context, R.drawable.ic_goiaba,210,0,0,0,true );
    cartaB4 = ConvertBitimap.getBitmap( context, R.drawable.ic_uva,210,0,0,0,true );
    cartaB5 = ConvertBitimap.getBitmap( context, R.drawable.ic_melancia,210,0,0,0,true );
    cartaB6 = ConvertBitimap.getBitmap( context, R.drawable.ic_abacaxi,210,0,0,0,true );
    cartaB7 = ConvertBitimap.getBitmap( context, R.drawable.ic_morango,210,0,0,0,true );
    cartaB8 = ConvertBitimap.getBitmap( context, R.drawable.ic_cereja,210,0,0,0,true );
    cartaB9 = ConvertBitimap.getBitmap( context, R.drawable.ic_laranja,210,0,0,0,true );
    cartaB10 = ConvertBitimap.getBitmap( context, R.drawable.ic_abacate,210,0,0,0,true );
    cartaB11 = ConvertBitimap.getBitmap( context, R.drawable.ic_mamao,210,0,0,0,true );


    cartaS0 = MediaPlayer.create( context, R.raw.maca );
    cartaS1 = MediaPlayer.create( context, R.raw.banana );
    cartaS2 = MediaPlayer.create( context, R.raw.pera );
    cartaS3 = MediaPlayer.create( context, R.raw.goiaba );
    cartaS4 = MediaPlayer.create( context, R.raw.uva );
    cartaS5 = MediaPlayer.create( context, R.raw.melancia );
    cartaS6 = MediaPlayer.create( context, R.raw.abacaxi);
    cartaS7 = MediaPlayer.create( context, R.raw.morango );
    cartaS8 = MediaPlayer.create( context, R.raw.cereja );
    cartaS9 = MediaPlayer.create( context, R.raw.laranja );
    cartaS10 = MediaPlayer.create( context, R.raw.abacate );
    cartaS11 = MediaPlayer.create( context, R.raw.mamao );


    cartas.add( new CartaDoJogo(context, (int) (w * 0.07),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,1, carta0, cartaS0,pintar,"MAÇÃ MA-ÇÃ" ,"120,120,222") );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.30),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,2, carta3, cartaS3,pintar,"GOIABA GOI-A-BA","140,222,120" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.60),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,3, carta7, cartaS7,pintar,"MORANGO MO-RAN-GO" ,"222,120,120") );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.05),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,4, carta5, cartaS5,pintar,"MELANCIA ME-LAN-CI-A","143,232,170" ) );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.30),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,5, carta2, cartaS2,pintar,"PÊRA PÊ-RA" ,"120,220,100") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.60),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,6, carta1, cartaS1,pintar,"BANANA BA-NA-NA","120,120,255" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.05),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,7, carta4, cartaS4,pintar,"UVA U-VA","180,180,0" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.35),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,8, carta6, cartaS6,pintar,"ABACAXI A-BA-CA-XI" ,"0,120,120") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.6),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,9, carta8, cartaS8,pintar,"CEREJA CE-RE-JA" ,"190,0,170") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.06),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,10, carta10, cartaS10,pintar,"ABACATE A-BA-CA-TE" ,"150,170,190") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.3),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,11, carta9, cartaS9,pintar,"LARANJA LA-RAN-JA" ,"100,200,255") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.6),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,12, carta11, cartaS11,pintar,"MAMÃO MA-MÃO","111,123,198" ) );


}
else {


    // Resources rex= context.getResources();

    //String[] img = rex.getStringArray(R.drawable.tipos_de_imagens );

    carta0 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta1 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta2 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta3 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta4 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta5 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta6 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta7 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta8 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta9 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta10 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );
    carta11 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,255,0,0,0,false );

    cartaB0 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB1 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB2 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB3 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB4 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB5 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB6 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB7 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB8 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB9 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB10 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );
    cartaB11 = ConvertBitimap.getBitmap( context, R.drawable.ic_ico,210,0,0,0,true );


    cartaS0 = MediaPlayer.create( context, R.raw.acerto );
    cartaS1 = MediaPlayer.create( context, R.raw.acerto );
    cartaS2 = MediaPlayer.create( context, R.raw.acerto );
    cartaS3 = MediaPlayer.create( context, R.raw.acerto );
    cartaS4 = MediaPlayer.create( context, R.raw.acerto );
    cartaS5 = MediaPlayer.create( context, R.raw.acerto );
    cartaS6 = MediaPlayer.create( context, R.raw.acerto);
    cartaS7 = MediaPlayer.create( context, R.raw.acerto );
    cartaS8 = MediaPlayer.create( context, R.raw.acerto );
    cartaS9 = MediaPlayer.create( context, R.raw.acerto );
    cartaS10 = MediaPlayer.create( context, R.raw.acerto );
    cartaS11 = MediaPlayer.create( context, R.raw.acerto );


    cartas.add( new CartaDoJogo(context, (int) (w * 0.07),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,1, carta0, cartaS0,pintar,". ." ,"120,120,222") );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.30),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,2, carta3, cartaS3,pintar,". .","140,222,120" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.60),(int) (h * 0.03),(int) (w * 0.2),(int) (h * 0.1),false,false,3, carta7, cartaS7,pintar,". ." ,"222,120,120") );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.05),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,4, carta5, cartaS5,pintar,". .","143,232,170" ) );
    cartas.add( new CartaDoJogo(context, (int) (w * 0.30),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,5, carta2, cartaS2,pintar,". ." ,"120,220,100") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.60),(int) (h * 0.14),(int) (w * 0.2),(int) (h * 0.1),false,false,6, carta1, cartaS1,pintar,". .","120,120,255" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.05),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,7, carta4, cartaS4,pintar,". .","180,180,0" ) );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.35),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,8, carta6, cartaS6,pintar,". ." ,"0,120,120") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.6),(int) (h * 0.25),(int) (w * 0.2),(int) (h * 0.1),false,false,9, carta8, cartaS8,pintar,". ." ,"190,0,170") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.06),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,10, carta10, cartaS10,pintar,". ." ,"150,170,190") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.3),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,11, carta9, cartaS9,pintar,". ." ,"100,200,255") );
    cartas.add( new CartaDoJogo( context,(int) (w * 0.6),(int) (h * 0.35),(int) (w * 0.2),(int) (h * 0.1),false,false,12, carta11, cartaS11,pintar,". .","111,123,198" ) );


}


    }




        public ArrayList<CartaDoJogo> getCartas() {
        return this.cartas;
    }

    public void setCartas(ArrayList<CartaDoJogo> cartas) {
        this.cartas = cartas;
    }

    public ArrayList<CartaDoJogo> getSombrasx() {
        return this.sombrasx;
    }

    public void setSombrasx(ArrayList<CartaDoJogo> sombrasx) {
        this.sombrasx = sombrasx;
    }

    public ArrayList<CartaDoJogo> getBaloes() {
        return this.baloes;
    }

    public void setBaloes(ArrayList<CartaDoJogo> baloes) {
        this.baloes = baloes;
    }

    public ArrayList<Double> getyCartas() {
        return this.yCartas;
    }

    public void setyCartas(ArrayList<Double> yCartas) {
        this.yCartas = yCartas;
    }

    public ArrayList<Double> getxCartas() {
        return this.xCartas;
    }

    public void setxCartas(ArrayList<Double> xCartas) {
        this.xCartas = xCartas;
    }
}
