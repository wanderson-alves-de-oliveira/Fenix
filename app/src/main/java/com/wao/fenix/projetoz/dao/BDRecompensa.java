package com.wao.fenix.projetoz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wao.fenix.projetoz.modelo.Recompensa;


/**
 * Created by wanderson on 16/08/19.
 */

public class BDRecompensa {

    private final SQLiteDatabase db;

    public BDRecompensa(Context context) {
        ConectionDB aux = new ConectionDB(context);
        db = aux.getWritableDatabase();

    }

    public void fechar(){

        db.close();

    }


    public Long getId() {

        Cursor cursor = db.rawQuery("SELECT _id FROM recompensa ORDER BY _id DESC LIMIT 1;", null);
        cursor.moveToNext();
        cursor.close();
        fechar();
        return cursor.getLong(0);
    }

    public void atualizarRecompensa(Recompensa v) {

        ContentValues valores = new ContentValues();
        valores.put("nome", v.getNome());
        valores.put("valor", v.getValor());
        db.update( "recompensa", valores, "_id = ?", new String[]{"" + v.get_id()} );
        fechar();
    }

    public void inserirRecompensa(Recompensa v) {

        ContentValues valores = new ContentValues();
        valores.put("nome", v.getNome());
         valores.put("valor", v.getValor());
        db.insert( "recompensa", null, valores );
        fechar();
    }

    public Recompensa buscar(final long id) {

        Cursor cursor = db.rawQuery("SELECT _id,nome,valor FROM recompensa WHERE _id = " + id, null);
        cursor.moveToNext();
        Recompensa c = new Recompensa();
        c.set_id(cursor.getLong(0));
        c.setNome(cursor.getString(1));
        c.setValor(cursor.getInt(2));
        cursor.close();
        fechar();
        return c;
    }





}

