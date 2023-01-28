package com.wao.fenix.projetoz.generico.recursos;

/**
 * Created by wanderson on 02/06/18.
 */

public class PositionVetor {

    int pos;
    float valor;

    public int getPos() {
        return this.pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public float getValor() {
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public PositionVetor(int pos,float valor) {
        this.pos = pos;
        this.valor = valor;
    }
}
