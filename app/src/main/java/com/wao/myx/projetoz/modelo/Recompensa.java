package com.wao.myx.projetoz.modelo;

import java.util.Objects;

public class Recompensa {

    private String nome;
    private long _id;
    private int valor;

    public Recompensa() {
    }

    public String getNome() {
        return nome;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recompensa that = (Recompensa) o;
        return _id == that._id && valor == that.valor && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, _id, valor);
    }

    @Override
    public String toString() {
        return "Recompensa{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
