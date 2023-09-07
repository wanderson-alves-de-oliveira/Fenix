package com.wao.myx.projetoz.generico.recursos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;

import com.wao.myx.R;


/**
 * Created by wanderson on 24/08/19.
 */

public class AlertaR extends AppCompatDialog {
    private AlertDialog alerta;
     private Button btSim;
    private ImageButton btNao;

    private  final TextView mensagem ;
    Context context;
    private LayoutInflater li;
    private View view;
    public AlertaR(Context context) {
        super(context);
        this.context=context;
          li = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
         view = li.inflate( R.layout.caixa_nova_r, null );
        btSim = view.findViewById( R.id.btPositivo );
        btNao = view.findViewById( R.id.btPararAlarme);

        mensagem = view.findViewById( R.id.tvMensagem );

    }



    public AlertDialog enviarAlerta(boolean btSimNao ) {

        final TextView texto = new TextView( view.getContext() );
        texto.setText( " " );
        texto.setBackgroundColor( Color.argb( 255, 0, 0, 0 ) );
        texto.setTextSize( 25 );

          if (!btSimNao) {
            btSim.setEnabled( true );
            btNao.setEnabled( true );
            btNao.setVisibility( View.INVISIBLE );
            btSim.setVisibility( View.INVISIBLE );
        }


        AlertDialog.Builder builder = new AlertDialog.Builder( view.getContext() );

        builder.setView( view );
     //   builder.setCustomTitle( texto );
        alerta = builder.create();
        alerta.setCanceledOnTouchOutside(false);
        return alerta;
    }

    public void fechar(){

        alerta.dismiss();
    }
    public Button getBtSim() {
        return this.btSim;
    }


    public ImageButton getBtNao() {
        return this.btNao;
    }



 }