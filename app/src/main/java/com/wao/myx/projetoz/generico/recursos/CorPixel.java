package com.wao.myx.projetoz.generico.recursos;

import java.util.Objects;

public class CorPixel {
    private int x, y;
float red,gree,blue;

    public CorPixel(int x, int y, float red, float gree, float blue) {
        this.x = x;
        this.y = y;
        this.red = red;
        this.gree = gree;
        this.blue = blue;
    }

    public CorPixel() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGree() {
        return gree;
    }

    public void setGree(float gree) {
        this.gree = gree;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "CorPixel{" +
                "x=" + x +
                ", y=" + y +
                ", red=" + red +
                ", gree=" + gree +
                ", blue=" + blue +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorPixel pixel = (CorPixel) o;
        return x == pixel.x &&
                y == pixel.y &&
                Float.compare(pixel.red, red) == 0 &&
                Float.compare(pixel.gree, gree) == 0 &&
                Float.compare(pixel.blue, blue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, red, gree, blue);
    }
}

