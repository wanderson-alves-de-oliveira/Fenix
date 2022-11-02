package com.example.jogodetabuleiro1.projetoz.generico.memoria;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.jogodetabuleiro1.R;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.ConvertBitimap;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.Vetor3;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by wanderson on 26/06/18.
 */

public class CarregarArquivo {
    private   MediaPlayer leaoxm;
    private   MediaPlayer tigrexm;
    private   MediaPlayer zebraxm;
    private   MediaPlayer elefantexm;
    private   MediaPlayer rinoxm;
    private   MediaPlayer hipoxm;
    private   MediaPlayer cameloxm;
    private   MediaPlayer cavaloxm;
    private   MediaPlayer vacaxm;
    private   MediaPlayer touroxm;
    private   MediaPlayer pandaxm;
    private   MediaPlayer girafaxm;
    private Bitmap leo;
    private Bitmap tigre;
    private Bitmap zebra;
    private Bitmap elefante;
    private Bitmap rino;
    private Bitmap hipo;
    private Bitmap camelo;
    private Bitmap cavalo;
    private Bitmap vaca;
    private Bitmap touro;
    private Bitmap panda;
    private Bitmap girafa;



    private ArrayList<Animaisx> animaisx;
    private ArrayList<Animaisx> animaisx2;
    private ArrayList<Animaisx> animaisx3;

    private ArrayList<com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d> Objeto3d;

    public CarregarArquivo() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CarregarArquivo(Context context, AssetManager asset)throws IOException {
        this.animaisx = new ArrayList();
        this.animaisx2 = new ArrayList();

        this.Objeto3d = new ArrayList();


        leo = ConvertBitimap.getBitmap( context, R.drawable.ic_leom,0,0,0,false );
        tigre = ConvertBitimap.getBitmap( context, R.drawable.ic_tigrem,0,0,0,false );
        zebra = ConvertBitimap.getBitmap( context, R.drawable.ic_zebram,0,0,0,false );
        elefante = ConvertBitimap.getBitmap( context, R.drawable.ic_elephantm,0,0,0,false );
        rino = ConvertBitimap.getBitmap( context, R.drawable.ic_rinom,0,0,0,false );
        hipo = ConvertBitimap.getBitmap( context, R.drawable.ic_hipom,0,0,0,false );
        camelo = ConvertBitimap.getBitmap( context, R.drawable.ic_camelom,0,0,0,false );
        cavalo = ConvertBitimap.getBitmap( context, R.drawable.ic_cavalom,0,0,0,false );
        vaca = ConvertBitimap.getBitmap( context, R.drawable.ic_vacam,0,0,0,false );
        touro = ConvertBitimap.getBitmap( context, R.drawable.ic_tourom,0,0,0,false );
        panda = ConvertBitimap.getBitmap( context, R.drawable.ic_pandam,0,0,0,false );
        girafa = ConvertBitimap.getBitmap( context, R.drawable.ic_girafam,0,0,0,false );


        leo = Bitmap.createScaledBitmap( leo,400,400,true ) ;
        tigre = Bitmap.createScaledBitmap( tigre,400,400,true ) ;
        zebra = Bitmap.createScaledBitmap( zebra,400,400,true ) ;
        elefante = Bitmap.createScaledBitmap( elefante,400,400,true ) ;
        rino =Bitmap.createScaledBitmap( rino,400,400,true ) ;
        hipo = Bitmap.createScaledBitmap( hipo,400,400,true ) ;
        camelo = Bitmap.createScaledBitmap( camelo,400,400,true ) ;
        cavalo = Bitmap.createScaledBitmap( cavalo,400,400,true ) ;
        vaca = Bitmap.createScaledBitmap( vaca,400,400,true ) ;
        touro = Bitmap.createScaledBitmap( touro,400,400,true ) ;
        panda = Bitmap.createScaledBitmap( panda,400,400,true ) ;
        girafa = Bitmap.createScaledBitmap( girafa,400,400,true ) ;


        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.leo,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.tigre,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.zebra,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.elefante,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.rino,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.hipo,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.camelo,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.cavalo,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.vaca,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.touro,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.panda,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.girafa,new Vetor3( 1.0f,1.0f,1.0f )) );

        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.leo,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.tigre,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.zebra,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.elefante,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.rino,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.hipo,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.camelo,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.cavalo,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.vaca,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.touro,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.panda,new Vetor3( 1.0f,1.0f,1.0f )) );
        this.Objeto3d.add( new Objeto3d(asset,"quadro.obj", this.girafa,new Vetor3( 1.0f,1.0f,1.0f )) );


        this.leaoxm = MediaPlayer.create( context, R.raw.leao );
        this.tigrexm = MediaPlayer.create( context, R.raw.tigre );
        this.zebraxm = MediaPlayer.create( context, R.raw.zebra );
        this.elefantexm = MediaPlayer.create( context, R.raw.elefante );
        this.rinoxm = MediaPlayer.create( context, R.raw.rino );
        this.hipoxm = MediaPlayer.create( context, R.raw.hipo );
        this.cameloxm = MediaPlayer.create( context, R.raw.camelo );
        this.cavaloxm = MediaPlayer.create( context, R.raw.cavalo );
        this.vacaxm = MediaPlayer.create( context, R.raw.vaca );
        this.touroxm = MediaPlayer.create( context, R.raw.touro );
        this.pandaxm = MediaPlayer.create( context, R.raw.panda );
        this.girafaxm = MediaPlayer.create( context, R.raw.girafa );

        Animaisx leaox = new Animaisx(0,0, 0,0,320,420,"leao", this.Objeto3d.get( 0 ), this.leaoxm );
        Animaisx tigrex = new Animaisx(0,0, 0,0,320,420,"tigre", this.Objeto3d.get( 1 ), this.tigrexm );
        Animaisx zebrax = new Animaisx( 0,0,0,0,320,420,"zebra", this.Objeto3d.get( 2 ), this.zebraxm );
        Animaisx elefantex = new Animaisx(0,0, 0,0,320,420,"elefante", this.Objeto3d.get( 3 ), this.elefantexm );
        Animaisx rinox = new Animaisx( 0,0,0,0,320,420,"rinoceronte", this.Objeto3d.get( 4 ), this.rinoxm );
        Animaisx hipox = new Animaisx( 0,0,0,0,320,420,"hipopótamo", this.Objeto3d.get( 5 ), this.hipoxm );
        Animaisx camelox = new Animaisx( 0,0,0,0,320,420,"camelo", this.Objeto3d.get( 6 ), this.cameloxm );
        Animaisx cavalox = new Animaisx( 0,0,0,0,320,420,"cavalo", this.Objeto3d.get( 7 ), this.cavaloxm );
        Animaisx vacax = new Animaisx( 0,0,0,0,320,420,"vaca", this.Objeto3d.get( 8 ), this.vacaxm );
        Animaisx tourox = new Animaisx(0,0, 0,0,320,420,"touro", this.Objeto3d.get( 9 ), this.touroxm );
        Animaisx pandax = new Animaisx( 0,0,0,0,320,420,"panda", this.Objeto3d.get( 10 ), this.pandaxm );
        Animaisx girafax = new Animaisx( 0,0,0,0,320,420,"girafa", this.Objeto3d.get( 11 ), this.girafaxm );


        Animaisx leaox2 = new Animaisx( 0,0,0,0,320,420,"leao", this.Objeto3d.get( 12 ), this.leaoxm );
        Animaisx tigrex2 = new Animaisx( 0,0,0,0,320,420,"tigre", this.Objeto3d.get( 13 ), this.tigrexm );
        Animaisx zebrax2 = new Animaisx( 0,0,0,0,320,420,"zebra", this.Objeto3d.get( 14 ), this.zebraxm );
        Animaisx elefantex2 = new Animaisx( 0,0,0,0,320,420,"elefante", this.Objeto3d.get( 15 ), this.elefantexm );
        Animaisx rinox2 = new Animaisx( 0,0,0,0,320,420,"rinoceronte", this.Objeto3d.get( 16 ), this.rinoxm );
        Animaisx hipox2 = new Animaisx( 0,0,0,0,320,420,"hipopótamo", this.Objeto3d.get( 17 ), this.hipoxm );
        Animaisx camelox2 = new Animaisx( 0,0,0,0,320,420,"camelo", this.Objeto3d.get( 18 ), this.cameloxm );
        Animaisx cavalox2 = new Animaisx(0,0, 0,0,320,420,"cavalo", this.Objeto3d.get( 19 ), this.cavaloxm );
        Animaisx vacax2 = new Animaisx( 0,0,0,0,320,420,"vaca", this.Objeto3d.get( 20 ), this.vacaxm );
        Animaisx tourox2 = new Animaisx( 0,0,0,0,320,420,"touro", this.Objeto3d.get( 21 ), this.touroxm );
        Animaisx pandax2 = new Animaisx(0,0, 0,0,320,420,"panda", this.Objeto3d.get( 22 ), this.pandaxm );
        Animaisx girafax2 = new Animaisx(0,0, 0,0,320,420,"girafa", this.Objeto3d.get( 23 ), this.girafaxm );


        this.animaisx.add( leaox );
        this.animaisx.add( tigrex );
        this.animaisx.add( zebrax );
        this.animaisx.add( elefantex );
        this.animaisx.add( rinox );
        this.animaisx.add( hipox );
        this.animaisx.add( camelox );
        this.animaisx.add( cavalox );
        this.animaisx.add( vacax );
        this.animaisx.add( tourox );
        this.animaisx.add( pandax );
        this.animaisx.add( girafax );

        this.animaisx2.add( leaox2 );
        this.animaisx2.add( tigrex2 );
        this.animaisx2.add( zebrax2 );
        this.animaisx2.add( elefantex2 );
        this.animaisx2.add( rinox2 );
        this.animaisx2.add( hipox2 );
        this.animaisx2.add( camelox2 );
        this.animaisx2.add( cavalox2 );
        this.animaisx2.add( vacax2 );
        this.animaisx2.add( tourox2 );
        this.animaisx2.add( pandax2 );
        this.animaisx2.add( girafax2 );



    }

    public ArrayList<Animaisx> getAnimaisx3() {
        return this.animaisx3;
    }

    public void setAnimaisx3(ArrayList<Animaisx> animaisx3) {
        this.animaisx3 = animaisx3;
    }

    public ArrayList<Animaisx> getAnimaisx() {
        return this.animaisx;
    }

    public void setAnimaisx(ArrayList<Animaisx> animaisx) {
        this.animaisx = animaisx;
    }

    public ArrayList<Animaisx> getAnimaisx2() {
        return this.animaisx2;
    }

    public void setAnimaisx2(ArrayList<Animaisx> animaisx2) {
        this.animaisx2 = animaisx2;
    }

    public ArrayList<com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d> getObjeto3d() {
        return this.Objeto3d;
    }

    public void setObjeto3d(ArrayList<com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d> Objeto3d) {
        this.Objeto3d = Objeto3d;
    }
}
