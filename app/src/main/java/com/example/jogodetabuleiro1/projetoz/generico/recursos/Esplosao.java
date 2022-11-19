package com.example.jogodetabuleiro1.projetoz.generico.recursos;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.jogodetabuleiro1.R;

import java.io.IOException;
import java.util.ArrayList;

public class Esplosao {

    private String nome="";
    private int id=-1;
    private ArrayList<Objeto3d> splosaoArrayNave;
    private int esplosaoNaveId = 0;

    public ArrayList<Objeto3d> getSplosaoArrayNave() {
        return splosaoArrayNave;
    }

    public int getEsplosaoNaveId() {
        return esplosaoNaveId;
    }

    public void setEsplosaoNaveId(int esplosaoNaveId) {
        this.esplosaoNaveId = esplosaoNaveId;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  Esplosao (Objeto3d obj, AssetManager asset, Resources res, float xx, String nome, int id) throws IOException {
        ///CARREGA OS ARQUIS 3D DO ALFABETO
        this.nome=nome;
        this.id=id;
        splosaoArrayNave = new ArrayList<>();
        float x=obj.getTamanho().getX();
        float y=obj.getTamanho().getY();
        float z=obj.getTamanho().getZ();
        splosaoArrayNave.add(new Objeto3d(asset, "sp.obj", BitmapFactory.decodeResource(res, R.drawable.esplosao), new Vetor3(x,y,z)));
        splosaoArrayNave.get(0).setTransparente(true);
        splosaoArrayNave.get(0).loadGLTexture(false);

        splosaoArrayNave.add(new Objeto3d(asset, "spa.obj", BitmapFactory.decodeResource(res, R.drawable.esplosao), new Vetor3(x,y,z)));
        splosaoArrayNave.get(1).setTransparente(true);
        splosaoArrayNave.get(1).loadGLTexture(false);

        splosaoArrayNave.add(new Objeto3d(asset, "spb.obj", BitmapFactory.decodeResource(res, R.drawable.esplosao), new Vetor3(x,y,z)));
        splosaoArrayNave.get(2).setTransparente(true);
        splosaoArrayNave.get(2).loadGLTexture(false);

        splosaoArrayNave.add(new Objeto3d(asset, "spc.obj", BitmapFactory.decodeResource(res, R.drawable.esplosao), new Vetor3(x,y,z)));
        splosaoArrayNave.get(3).setTransparente(true);
        splosaoArrayNave.get(3).loadGLTexture(false);

        splosaoArrayNave.add(new Objeto3d(asset, "spd.obj", BitmapFactory.decodeResource(res, R.drawable.esplosao), new Vetor3(x,y,z)));
        splosaoArrayNave.get(4).setTransparente(true);
        splosaoArrayNave.get(4).loadGLTexture(false);

        splosaoArrayNave.add(new Objeto3d(asset, "spe.obj", BitmapFactory.decodeResource(res, R.drawable.esplosao), new Vetor3(x,y,z)));
        splosaoArrayNave.get(5).setTransparente(true);
        splosaoArrayNave.get(5).loadGLTexture(false);

        splosaoArrayNave.add(new Objeto3d(asset, "spf.obj", BitmapFactory.decodeResource(res, R.drawable.esplosao), new Vetor3(x,y,z)));
        splosaoArrayNave.get(6).setTransparente(true);

        splosaoArrayNave.get(6).loadGLTexture(false);
        for (int p = 0; p < splosaoArrayNave.size(); p++) {
            splosaoArrayNave.get(p).setMudarTamanho(true);
            splosaoArrayNave.get(p).vezes(xx);
            splosaoArrayNave.get(p).setTransparente(true);
            splosaoArrayNave.get(p).setRefletir(true);
        }

    }



}
