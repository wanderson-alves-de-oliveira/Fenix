package com.example.jogodetabuleiro1.tartarugaMarinha;

/**
 * Created by wanderson on 11/10/17.
 */

public class Contas {
    private int n1, n2;
    private char operador;
    private int resultado;

    public Contas() {
    }

    public Contas(int n1, int n2, char operador) {
        this.n1 = n1;
        this.n2 = n2;
        this.operador = operador;
        this.setResultado();
    }

    public int getN1() {
        return this.n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return this.n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public char getOperador() {
        return this.operador;
    }

    public void setOperador(char operador) {
        this.operador = operador;
    }

    public int getResultado() {
        return this.resultado;
    }

    public void setResultado() {

        if (this.operador == '+') {
            this.resultado = this.n1 + this.n2;
       }

        else if (this.operador == '-') {
            if (this.n1 < this.n2) {
                int troca = this.n1;
                this.n1 = this.n2;
                this.n2 = troca;
            }

            this.resultado = this.n1 - this.n2;
        } /*else if (this.operador == '*') {
            this.resultado = this.n1 * this.n2;
        } else if (this.operador == '/') {

            if (this.n1 % this.n2 == 0) {
            } else {
                if (this.n2 % this.n1 == 0) {
                    int troca = this.n1;
                    this.n1 = this.n2;
                    this.n2 = troca;
                } else {
                    while (this.n1 % this.n2 != 0) {
                        this.n1++;
                    }
                }
            }

            this.resultado = this.n1 / this.n2;


        }

        */


    }


}




























