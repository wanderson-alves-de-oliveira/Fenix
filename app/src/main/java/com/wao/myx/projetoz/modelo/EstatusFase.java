package com.wao.myx.projetoz.modelo;

public class EstatusFase {

    private int id;
    private int saude;
    private int inimigosEliminados;
    private int inimigosGerados;
    private String progresso="PENDENTE";

    public int getInimigosGerados() {
        return inimigosGerados;
    }

    public void setInimigosGerados(int inimigosGerados) {
        this.inimigosGerados = inimigosGerados;
    }

    public EstatusFase() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSaude() {
        return saude;
    }

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public int getInimigosEliminados() {
        return inimigosEliminados;
    }

    public void setInimigosEliminados(int inimigosEliminados) {
        this.inimigosEliminados = inimigosEliminados;
    }

    public String getProgresso() {
        return progresso;
    }

    public void setProgresso(String progresso) {
        this.progresso = progresso;
    }



}
