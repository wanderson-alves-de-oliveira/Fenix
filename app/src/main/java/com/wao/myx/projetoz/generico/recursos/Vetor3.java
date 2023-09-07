package com.wao.myx.projetoz.generico.recursos;

import com.wao.myx.projetoz.generico.recursos.Objeto3d;

import java.math.BigDecimal;

/**
 * Created by wanderson on 10/05/18.
 */

public class Vetor3 {
    BigDecimal big ;
    public float x,y,z;
    public Vetor3 position;
    public Vetor3(double v, Objeto3d v1, double v2) {
    }

    public Vetor3(Vetor3 v3) {
        this.x = v3.x;
        this.y = v3.y;
        this.z = v3.z;
    }

    public Vetor3(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;


    }

    public Vetor3 getPosition() {
        return new Vetor3(x,y,z);
    }

    public void setPosition(Vetor3 position) {
        this.position = position;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
