package com.wao.fenix.projetoz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wao.fenix.projetoz.modelo.Nave;

import java.util.ArrayList;


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
        valores.put("escudo", v.getEscudo()==-1?0:v.getEscudo());
        valores.put("bomba", v.getBomba());
        valores.put("puchar", v.getPuchar());
        valores.put("escudo_nivel",  v.getEscudoNivel());
        valores.put("dano",  v.getDano());
        valores.put("defesa",  v.getDefesa());
        db.update( "upgade_nave", valores, "_id = ?", new String[]{"" + v.get_id()} );
        fechar();
    }

    public void atualizarNaveHabilitado(int id) {

        ContentValues valores = new ContentValues();
        valores.put("liberado", "S");
        db.update( "upgade_nave", valores, "_id = ?", new String[]{"" + id} );
        fechar();
    }



    public void inserirNave(Nave v) {

        ContentValues valores = new ContentValues();
        valores.put("nome", v.getNome());
        valores.put("habilitado", v.getHabilitado());
        valores.put("ataque", v.getAtaque());
        valores.put("escudo", v.getEscudo());
        valores.put("bomba", v.getBomba());
        valores.put("puchar", v.getPuchar());
        valores.put("escudo_nivel",  v.getEscudoNivel());
        valores.put("dano",  v.getDano());
        valores.put("defesa",  v.getDefesa());
        db.insert( "upgade_nave", null, valores );
        fechar();
    }

    public Nave buscar(final long id) {

        Cursor cursor = db.rawQuery("SELECT _id,nome,ataque,escudo,puchar,habilitado,bomba,escudo_nivel,dano,defesa,liberado FROM upgade_nave WHERE _id = " + id, null);
        cursor.moveToNext();
        Nave c = new Nave();
        c.set_id(cursor.getInt(0));
        c.setNome(cursor.getString(1));
        c.setAtaque(cursor.getInt(2));
        c.setEscudo(cursor.getInt(3));
        c.setPuchar(cursor.getInt(4));
        c.setHabilitado(cursor.getString(5));
        c.setBomba(cursor.getInt(6));
        c.setEscudoNivel(cursor.getInt(7));
        c.setDano(cursor.getInt(8));
        c.setDefesa(cursor.getInt(9));
        c.setLiberado(cursor.getString(10));

        cursor.close();
        fechar();
        return c;
    }


    public int buscarIdnaveSelecionada() {

        Cursor cursor = db.rawQuery("SELECT naveselecionada_num FROM upgade_nave LIMIT 1" , null);
        cursor.moveToNext();
        int c = (cursor.getInt(0));
        cursor.close();
        fechar();
        return c==0?1:c;
    }

    public void atualizarIdnaveSelecionada(int id) {

        ContentValues valores = new ContentValues();
        valores.put("naveselecionada_num", id);
        db.update( "upgade_nave", valores,null  , null  );
        fechar();
    }



    public Nave buscarHabilitado(final int habilitado) {

        Cursor cursor = db.rawQuery("SELECT _id,nome,ataque,escudo,puchar,habilitado,bomba,escudo_nivel,dano,defesa,liberado FROM upgade_nave WHERE habilitado = '" + habilitado+"'", null);
        cursor.moveToNext();
        Nave c = new Nave();
        try {


            c.set_id(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setAtaque(cursor.getInt(2));
            c.setEscudo(cursor.getInt(3));
            c.setPuchar(cursor.getInt(4));
            c.setHabilitado(cursor.getString(5));
            c.setBomba(cursor.getInt(6));
            c.setEscudoNivel(cursor.getInt(7));
            c.setDano(cursor.getInt(8));
            c.setDefesa(cursor.getInt(9));
            c.setLiberado(cursor.getString(10));
        }catch (Exception e){
            cursor.close();
            fechar();
            return  buscar(1);
        }
        cursor.close();
        fechar();
        return c;
    }
    public ArrayList<Nave> buscarLista() {
        ArrayList<Nave> lista= new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT _id,nome,ataque,escudo,puchar,habilitado,bomba,escudo_nivel,dano,defesa,liberado FROM upgade_nave ", null);
        cursor.moveToNext();


        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {
                Nave c = new Nave();
                c.set_id(cursor.getInt(0));
                c.setNome(cursor.getString(1));
                c.setAtaque(cursor.getInt(2));
                c.setEscudo(cursor.getInt(3));
                c.setPuchar(cursor.getInt(4));
                c.setHabilitado(cursor.getString(5));
                c.setBomba(cursor.getInt(6));
                c.setEscudoNivel(cursor.getInt(7));
                c.setDano(cursor.getInt(8));
                c.setDefesa(cursor.getInt(9));
                c.setLiberado(cursor.getString(10));

                lista.add(c);
            } while (cursor.moveToNext());
            cursor.close();

        }



        cursor.close();
        fechar();

        return lista;
    }


}

