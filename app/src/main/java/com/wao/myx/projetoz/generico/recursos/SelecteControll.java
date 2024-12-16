package com.wao.myx.projetoz.generico.recursos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;

import com.wao.myx.R;


/**
 * Created by wanderson on 24/08/19.
 */

public class SelecteControll extends AppCompatDialog {
    private AlertDialog alerta;
     private ImageButton btMusic;
    private ImageButton btSon;
    private ImageButton btfechar;
    Context context;
    private LayoutInflater li;
    private View view;
    public SelecteControll(Context context) {
        super(context);
        this.context=context;
          li = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
         view = li.inflate( R.layout.select_control, null );
        btMusic = view.findViewById( R.id.btMusic );
        btSon = view.findViewById( R.id.btSon);
        btfechar= view.findViewById( R.id.btfechar);

    }



    public AlertDialog enviarAlerta(  ) {

        final TextView texto = new TextView( view.getContext() );
        texto.setText( " " );
        texto.setBackgroundColor( Color.argb( 255, 0, 0, 0 ) );
        texto.setTextSize( 25 );


        AlertDialog.Builder builder = new AlertDialog.Builder( view.getContext() );

        builder.setView( view );
        alerta = builder.create();

        return alerta;
    }

    public void fechar(){

        alerta.dismiss();
    }
    public ImageButton getBtMusic() {
        return this.btMusic;
    }


    public ImageButton getBtSon() {
        return this.btSon;
    }

    public ImageButton getBtfechar() {
        return btfechar;
    }
}