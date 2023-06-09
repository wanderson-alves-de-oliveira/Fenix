package com.wao.fenix.projetoz.modelo;

import java.util.Objects;

public class Nave {

    private String nome,habilitado;
    private long _id;
    private int ataque;
    private int escudo;
    private int puchar;
    private int bomba;

    public Nave() {
    }

    public String getNome() {
        return nome;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getEscudo() {
        return escudo;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public int getPuchar() {
        return puchar;
    }

    public void setPuchar(int puchar) {
        this.puchar = puchar;
    }

    public int getBomba() {
        return bomba;
    }

    public void setBomba(int bomba) {
        this.bomba = bomba;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nave nave = (Nave) o;
        return _id == nave._id && ataque == nave.ataque && escudo == nave.escudo && puchar == nave.puchar && Objects.equals(nome, nave.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, _id, ataque, escudo, puchar);
    }

    @Override
    public String toString() {
        return "Nave{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
