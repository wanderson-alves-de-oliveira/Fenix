package com.example.jogodetabuleiro1.projetoz.generico.recursos;

import java.util.ArrayList;

public class Cronograma {
    private int id;
    private int timeIN;
    private int timeOUT;
    private int timeMode;
    private boolean boss = false;

    private int modo=0;
    private boolean perpetuo=false;
    private String sinal="=";
    private boolean emMovimento=false;
    private boolean avaliar=false;
    private boolean ativo=true;

    private   ArrayList<Objeto3d> obj;

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public Cronograma() {
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAvaliar() {
        return avaliar;
    }

    public void setAvaliar(boolean avaliar) {
        this.avaliar = avaliar;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Objeto3d> getObj() {
        return obj;
    }

    public void setObj(ArrayList<Objeto3d> obj) {
        this.obj = obj;
    }

    public int getModo() {
        return modo;
    }

    public int getTimeMode() {
        return timeMode;
    }

    public void setTimeMode(int timeMode) {
        this.timeMode = timeMode;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeIN() {
        return timeIN;
    }

    public void setTimeIN(int timeIN) {
        this.timeIN = timeIN;
    }

    public int getTimeOUT() {
        return timeOUT;
    }

    public void setTimeOUT(int timeOUT) {
        this.timeOUT = timeOUT;
    }

    public boolean isPerpetuo() {
        return perpetuo;
    }

    public void setPerpetuo(boolean perpetuo) {
        this.perpetuo = perpetuo;
    }

    public String getSinal() {
        return sinal;
    }

    public void setSinal(String sinal) {
        this.sinal = sinal;
    }

    public boolean isEmMovimento() {
        return emMovimento;
    }

    public void setEmMovimento(boolean emMovimento) {
        this.emMovimento = emMovimento;
    }

}
