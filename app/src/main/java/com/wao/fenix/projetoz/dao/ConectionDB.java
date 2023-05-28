package com.wao.fenix.projetoz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wanderson on 08/08/19.
 */

public class ConectionDB extends SQLiteOpenHelper {
    private static final String nomeDB = "Fenix2";
    private static final int vers = 1;
    private Context context;


    public ConectionDB(Context context) {
        super(context, nomeDB, null, vers);
        this.context = context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table  if not exists estatus_fase(_id integer primary key autoincrement," +
                "saude integer," +
                "inimigosEliminados integer," +
                "inimigosGerados integer," +
                "progresso text); ");

        db.execSQL("create table  if not exists upgade_nave(_id integer primary key autoincrement," +
                "nome text," +
                "habilitado text," +
                "ataque integer," +
                "escudo integer," +
                "puchar integer); ");

        db.execSQL("create table  if not exists recompensa(_id integer primary key autoincrement," +
                "nome text," +
                "valor integer); ");


        Calendar calendar = Calendar.getInstance();
        Date data = new Date();
        try {
            calendar.setTime(data);
        } catch (Exception er) {
            er.printStackTrace();
        }

        //      if (new BDEstatusFase(context).getbase() == 0) {
        for (int i = 0; i < 100; i++) {
            ContentValues valores = new ContentValues();
            valores.put("saude", 0);
            valores.put("inimigosEliminados", 0);
            valores.put("inimigosGerados", 0);
            valores.put("progresso", "PENDENTE");
            db.insert("estatus_fase", null, valores);
        }


        ContentValues valores = new ContentValues();
        valores.put("nome", "F001");
        valores.put("habilitado", "S");
        valores.put("ataque", 0);
        valores.put("escudo", 0);
        valores.put("puchar", 0);
        db.insert("upgade_nave", null, valores);


        valores = new ContentValues();
        valores.put("nome", "estrelas");
        valores.put("valor", 0);
        db.insert("recompensa", null, valores);


    }

    @Override
    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        if (Build.VERSION.SDK_INT >= 28) {
            File dbshm = new File(database.getPath() + "-shm");
            File dbwal = new File(database.getPath() + "-wal");
            if (dbshm.exists()) {
                dbshm.delete();
            }
            if (dbwal.exists()) {
                dbwal.delete();
            }


            database.disableWriteAheadLogging();

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL( "drop table materiaPrima;drop table receita;drop table vendas" );

        // onCreate( db );

    }


}
