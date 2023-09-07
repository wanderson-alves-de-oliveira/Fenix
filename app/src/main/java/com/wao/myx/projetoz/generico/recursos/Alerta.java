package com.wao.myx.projetoz.generico.recursos;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.wao.myx.R;

import java.util.Locale;


/**
 * Created by wanderson on 24/08/19.
 */

public class Alerta extends AppCompatDialog {
    private AlertDialog alerta;
     private Button btSim;
    private ImageButton btNao;
    private FrameLayout frameLayout;
    private AdLoader.Builder builder;

    boolean isDestroyedAds = false;
private String espere = "WAIT";

    private String normal;

    private  final TextView mensagem ;

    private NativeAd nativeAd;

    Context context;
    private LayoutInflater li;
    private View view;

    private int cout = 0;
    private int time = 0;


    public Alerta(Context context,AdLoader.Builder  builder) {
        super(context);
        this.context=context;


        MobileAds.initialize(this.context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });


        this.builder = builder;

        li = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
         view = li.inflate( R.layout.caixa_nova, null );
        btSim = view.findViewById( R.id.btPositivo );
        btNao = view.findViewById( R.id.btPararAlarme);
        frameLayout = view.findViewById(R.id.fl_adplaceholder);
      refreshAd();
        mensagem = view.findViewById( R.id.tvMensagem );
        btSim.setVisibility( View.VISIBLE);
        mensagem.setText(normal);

    }



    public AlertDialog enviarAlerta(String msg,boolean btSimNao, boolean completo, boolean killAll,boolean life ) {

        final TextView texto = new TextView( view.getContext() );
        texto.setText( " " );
        texto.setBackgroundColor( Color.argb( 255, 0, 0, 0 ) );
        texto.setTextSize( 25 );
        mensagem.setText(msg);
        normal = msg;
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

    public void visualisar() {
    }
    public ImageButton getBtNao() {
        return this.btNao;
    }

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public void setFrameLayout(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }


    private void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        // Set the media view.
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every NativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every NativeAd, so it's important to
        // check before trying to display them.



        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
            this.btSim.setVisibility(view.VISIBLE);
            mensagem.setText(normal);

        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
            this.btSim.setVisibility(view.VISIBLE);
            mensagem.setText(normal);
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
            this.btSim.setVisibility(view.VISIBLE);
            mensagem.setText(normal);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
            this.btSim.setVisibility(view.VISIBLE);
            mensagem.setText(normal);
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
            this.btSim.setVisibility(view.VISIBLE);
            mensagem.setText(normal);
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
            this.btSim.setVisibility(view.VISIBLE);
            mensagem.setText(normal);

        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);

            this.btSim.setVisibility(view.VISIBLE);
            mensagem.setText(normal);

        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getMediaContent().getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (nativeAd.getMediaContent() != null && nativeAd.getMediaContent().hasVideoContent()) {

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.

                    super.onVideoEnd();
                }
            });
        } else {

        }
    }

    /**
     * Creates a request for a new native ad based on the boolean parameters and calls the
     * corresponding "populate" method when one is successfully returned.
     *
     */
    private void refreshAd() {


        builder.forNativeAd(
                new NativeAd.OnNativeAdLoadedListener() {
                    // OnLoadedListener implementation.
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        // If this callback occurs after the activity is destroyed, you must call
                        // destroy and return or you may get a memory leak.
                        boolean isDestroyed = false;
                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            isDestroyed =  cout>2001;

                             cout++;
                             if(cout>2000)
                                 cout=0;
                        }
                        if (isDestroyed  ) {
                            nativeAd.destroy();
                            return;
                        }
                        // You must call destroy on old ads when you are done with them,
                        // otherwise you will have a memory leak.
                        if (Alerta.this.nativeAd != null) {
                            Alerta.this.nativeAd.destroy();
                        }
                        Alerta.this.nativeAd = nativeAd;
                         NativeAdView adView =
                                (NativeAdView) getLayoutInflater().inflate(R.layout.ad_unified, frameLayout, false);
                        populateNativeAdView(nativeAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                    }
                });



        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                         String error =
                                                String.format(
                                                        Locale.getDefault(),
                                                        "domain: %s, code: %d, message: %s",
                                                        loadAdError.getDomain(),
                                                        loadAdError.getCode(),
                                                        loadAdError.getMessage());
                                        btSim.setVisibility(view.VISIBLE);
                                        mensagem.setText(normal);

                                    }
                                })


                        .build();

        adLoader.loadAd(new AdRequest.Builder().build());

     }


}
