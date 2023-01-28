package com.wao.fenix.projetoz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wao.fenix.projetoz.modelo.EstatusFase;


/**
 * Created by wanderson on 16/08/19.
 */

public class BDEstatusFase {

    private SQLiteDatabase db;

    public BDEstatusFase(Context context) {
        ConectionDB aux = new ConectionDB(context);
        db = aux.getWritableDatabase();

    }

    public void fechar(){

        db.close();

    }


    public Long getId() {

        Cursor cursor = db.rawQuery("SELECT _id FROM estatus_fase ORDER BY _id DESC LIMIT 1;", null);
        cursor.moveToNext();
        cursor.close();
        fechar();
        return cursor.getLong(0);
    }

    public void atualizarFase(EstatusFase v) {

        ContentValues valores = new ContentValues();
        valores.put("saude", v.getSaude());
        valores.put("inimigosEliminados", v.getInimigosEliminados());
        valores.put("inimigosGerados", v.getInimigosGerados());
        valores.put("progresso", v.getProgresso());
        db.update( "estatus_fase", valores, "_id = ?", new String[]{"" + v.getId()} );
        fechar();
    }


    public void SalvarFase(EstatusFase v) {

        ContentValues valores = new ContentValues();
        valores.put("saude", v.getSaude());
        valores.put("inimigosEliminados", v.getInimigosEliminados());
        valores.put("inimigosGerados", v.getInimigosGerados());
        valores.put("progresso", v.getProgresso());

        db.insert("estatus_fase", null, valores);
        fechar();
    }


    public EstatusFase buscar(final long id) {

        Cursor cursor = db.rawQuery("SELECT _id,saude,inimigosEliminados,inimigosGerados,progresso FROM estatus_fase WHERE _id = " + id, null);
        cursor.moveToNext();
        EstatusFase c = new EstatusFase();
        c.setId(cursor.getLong(0));
        c.setSaude(cursor.getInt(1));
        c.setInimigosEliminados(cursor.getInt(2));
        c.setInimigosGerados(cursor.getInt(3));
        c.setProgresso(cursor.getString(4));

        cursor.close();
        fechar();
        return c;
    }

    public EstatusFase buscarUltima() {
        EstatusFase c = new EstatusFase();
        try {

        Cursor cursor = db.rawQuery("SELECT _id,saude,inimigosEliminados,inimigosGerados,progresso FROM estatus_fase where progresso='COMPLETA' ORDER  by _id DESC limit 1", null);
        cursor.moveToNext();

        c.setId(cursor.getLong(0));
        c.setSaude(cursor.getInt(1));
        c.setInimigosEliminados(cursor.getInt(2));
        c.setInimigosGerados(cursor.getInt(3));
        c.setProgresso(cursor.getString(4));

        cursor.close();

        }catch (Exception e){

            c.setId(0l);
            c.setSaude(100);
            c.setInimigosEliminados(0);
            c.setInimigosGerados(0);
            c.setProgresso("COMPLETA");

        }

        fechar();
        return c;
    }



}

