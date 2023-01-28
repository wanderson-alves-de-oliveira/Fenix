package com.wao.fenix.projetoz.generico.recursos;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;

import com.wao.fenix.R;


/**
 * Created by wanderson on 24/08/19.
 */

public class Alerta extends AppCompatDialog {
    private AlertDialog alerta;
     private Button btSim;
    private ImageButton btNao;
    private ImageView lifee;
    private ImageView killall;
    private ImageView complit;
    private  final TextView mensagem ;
    Context context;
    private LayoutInflater li;
    private View view;
    public Alerta(Context context) {
        super(context);
        this.context=context;
          li = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
         view = li.inflate( R.layout.caixa_nova, null );
        btSim = view.findViewById( R.id.btPositivo );
        btNao = view.findViewById( R.id.btPararAlarme);
        lifee = view.findViewById( R.id.imfullLive);
        killall = view.findViewById( R.id.imkillall);
        complit = view.findViewById( R.id.imcompletlevel);

        mensagem = view.findViewById( R.id.tvMensagem );

    }



    public AlertDialog enviarAlerta(String msg,boolean btSimNao, boolean completo, boolean killAll,boolean life ) {

        final TextView texto = new TextView( view.getContext() );
        texto.setText( " " );
        texto.setBackgroundColor( Color.argb( 255, 0, 0, 0 ) );
        texto.setTextSize( 25 );
        mensagem.setText(msg);

          if (!btSimNao) {
            btSim.setEnabled( true );
            btNao.setEnabled( true );
            btNao.setVisibility( View.INVISIBLE );
            btSim.setVisibility( View.INVISIBLE );
        }
        Drawable drawable = ContextCompat.getDrawable(context,R.drawable.starfull);

        if(completo)
              complit.setBackground(drawable);

        if(killAll)
            killall.setBackground(drawable);
        if(life)
            lifee.setBackground(drawable);

        AlertDialog.Builder builder = new AlertDialog.Builder( view.getContext() );

        builder.setView( view );
     //   builder.setCustomTitle( texto );
        alerta = builder.create();

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