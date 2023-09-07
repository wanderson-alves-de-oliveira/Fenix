package com.wao.myx.projetoz.generico.recursos;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.wao.myx.projetoz.dao.BDNave;
import com.wao.myx.projetoz.menu.TartarugaCorrida;
import com.wao.myx.projetoz.modelo.Nave;

import java.io.IOException;

public class TarefaAsync extends AsyncTask{
private Context context;
    private AssetManager asset;
    public TarefaAsync(Context context,AssetManager asset) {
        this.context=context;
        this.asset=asset;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected TartarugaCorrida doInBackground(Object[] objects) {
        TartarugaCorrida tut = null;
        try {

            BDNave BDN= new BDNave(context);
            Nave nivelNave = BDN.buscar(1);
              tut = new TartarugaCorrida(context, asset, 0, true, true,nivelNave);

                 tut.carregarP();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return tut;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }




    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
