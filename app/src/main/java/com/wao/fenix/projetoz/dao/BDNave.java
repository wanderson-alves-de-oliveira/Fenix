package com.wao.fenix.projetoz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wao.fenix.projetoz.modelo.Nave;


/**
 * Created by wanderson on 16/08/19.
 */

public class BDNave {

    private final SQLiteDatabase db;

    public BDNave(Context context) {
        ConectionDB aux = new ConectionDB(context);
        db = aux.getWritableDatabase();

    }

    public void fechar(){

        db.close();

    }


    public Long getId() {

        Cursor cursor = db.rawQuery("SELECT _id FROM upgade_nave ORDER BY _id DESC LIMIT 1;", null);
        cursor.moveToNext();
        cursor.close();
        fechar();
        return cursor.getLong(0);
    }

    public void atualizarNave(Nave v) {

        ContentValues valores = new ContentValues();
        valores.put("nome", v.getNome());
        valores.put("habilitado", v.getHabilitado());
        valores.put("ataque", v.getAtaque());
        valores.put("escudo", v.getEscudo());
        valores.put("puchar", v.getPuchar());
        db.update( "upgade_nave", valores, "_id = ?", new String[]{"" + v.get_id()} );
        fechar();
    }

    public void inserirNave(Nave v) {

        ContentValues valores = new ContentValues();
        valores.put("nome", v.getNome());
        valores.put("habilitado", v.getHabilitado());
        valores.put("ataque", v.getAtaque());
        valores.put("escudo", v.getEscudo());
        valores.put("puchar", v.getPuchar());
        db.insert( "upgade_nave", null, valores );
        fechar();
    }

    public Nave buscar(final long id) {

        Cursor cursor = db.rawQuery("SELECT _id,nome,ataque,escudo,puchar,habilitado FROM upgade_nave WHERE _id = " + id, null);
        cursor.moveToNext();
        Nave c = new Nave();
        c.set_id(cursor.getLong(0));
        c.setNome(cursor.getString(1));
        c.setAtaque(cursor.getInt(2));
        c.setEscudo(cursor.getInt(3));
        c.setPuchar(cursor.getInt(4));
        c.setHabilitado(cursor.getString(5));

        cursor.close();
        fechar();
        return c;
    }





}

