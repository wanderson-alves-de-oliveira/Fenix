package com.wao.myx.projetoz.generico.recursos;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;


import com.wao.myx.R;

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

//    public int getEsplosaoNaveId() {
//        return esplosaoNaveId;
//    }
//
//    public void setEsplosaoNaveId(int esplosaoNaveId) {
//        this.esplosaoNaveId = esplosaoNaveId;
//    }
    public String getNome() {
        return nome;
    }

//    public void setNome(String nome) {
//        this.nome = nome;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  Esplosao (Context context,Objeto3d obj, AssetManager asset, Resources res, float xx, String nome, int id) throws IOException {
        ///CARREGA OS ARQUIS 3D DO ALFABETO
        this.nome=nome;
        this.id=id;
        splosaoArrayNave = new ArrayList<>();
        float x=obj.getTamanho().getX();
        float y=obj.getTamanho().getY();
        float z=obj.getTamanho().getZ();
        splosaoArrayNave.add(new Objeto3d(context,  asset, "v.obj", R.drawable.espp, new Vetor3(x,y,z),"esp"));

        for (int p = 0; p < splosaoArrayNave.size(); p++) {
            splosaoArrayNave.get(p).setMudarTamanho(true);
         splosaoArrayNave.get(p).vezes(xx/2);
            splosaoArrayNave.get(p).setTransparente(true);
            splosaoArrayNave.get(p).setRefletir(true);
            splosaoArrayNave.get(p).loadGLTexture();
        }

    }



}
