package com.wao.fenix.projetoz.generico.recursos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

import com.wao.fenix.R;

import java.util.ArrayList;

public class MudaTextura {
    CorPixel p1;
    private ArrayList<CorPixel> cores = new ArrayList<>();
    private ArrayList<ArrayList<CorPixel>> desenho = new ArrayList<>();

    private final DisplayMetrics displayMetrics;
    private int h;
    private int w;
    private Bitmap cartaANIMAL;
    Context context;

    public MudaTextura(Context context) {
        p1 = new CorPixel(0, 0, 1f, 0f, 0f);
        cores.add(p1);
        this.context = context;
        desenho = new ArrayList<>();
        displayMetrics = context.getResources().getDisplayMetrics();
        h = (int) (this.displayMetrics.heightPixels);
        int w = (int) (this.displayMetrics.widthPixels);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bitmap mudarCorCarimbo(int bib, int r, int g, int b) {
        prepararImagem(bib);
        int c = cor();
        int cc = cor();
        for (int j = 0; j < cartaANIMAL.getHeight(); j++) {
            for (int i = 0; i < cartaANIMAL.getWidth(); i++) {
//                Color cor = null;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
//                    cor = cartaANIMAL.getColor(i, j);
//                }
//
//                if (cor.alpha() >= 1) {
                if (j % 2 == 0 && i % 2 == 0) {
                    setcor(i, j, c);
                } else if (j % 2 != 0 && i % 2 != 0) {
                    setcor(i, j, c);
                } else {
                    setcor(i, j, cc);
                }

            }

            //    }

            setcor((int) Math.random() * cartaANIMAL.getWidth(), (int) Math.random() * cartaANIMAL.getHeight(), cor());

        }

        return cartaANIMAL;
    }

    private void setcor(int i, int j, int c) {
        if (i < cartaANIMAL.getWidth() && j < cartaANIMAL.getHeight())
            cartaANIMAL.setPixel(i, j, c);
    }

    public Bitmap novoTexture(int desenhox) {

        Bitmap bt = BitmapFactory.decodeResource(Resources.getSystem(), desenhox);


        return bt;
    }

    private int cor() {

        return Color.rgb((int) (175 * Math.random()) + 20, (int) (105 * Math.random()) + 80, (int) (175 * Math.random()) + 80);
    }


    private boolean inserirPixel(int k, int x, int y, float r, float g, float b) {
        boolean vai = true;
        Bitmap bt = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.modea);

        if (r == cores.get(k).getRed() &&
                g == cores.get(k).getGree() &&
                b == cores.get(k).getBlue()) {
            //   Log.i("COR",""+cor.toArgb());

            desenho.get(k).add(new CorPixel(x, y, 255, 255, 255));


            bt.setPixel(x, y, Color.rgb(255, 255, 255));

            vai = true;
        } else {
            if (k < cores.size() - 1) {
                inserirPixel(k + 1, x, y, r, g, b);
            }
            vai = false;
        }
        return vai;
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void prepararImagem(int d) {
        float wwt = (float) (w * 1);
        float hht = (float) (w * 1);
        int c = cor();
        int cc = cor();
        cartaANIMAL = ConvertBitimap.getBitmap(context, d, 255, 255, 0, 0, true);
        //   Resources res = getContext().getResources();
        //    this.cartaANIMAL = BitmapFactory.decodeResource(res, ic_elephantm);

        this.cartaANIMAL = Bitmap.createScaledBitmap(cartaANIMAL, 250, 250, true);

        int meioh = cartaANIMAL.getHeight() / 2;
        int meiow = cartaANIMAL.getWidth() / 2;
        int meioh2 = cartaANIMAL.getHeight() / 2;
        int meiow2 = cartaANIMAL.getWidth() / 2;
        int meioh3 = cartaANIMAL.getHeight() / 2;
        int meiow3 = cartaANIMAL.getWidth() / 2;
        int meioh4 = cartaANIMAL.getHeight() / 2;
        int meiow4 = cartaANIMAL.getWidth() / 2;

        int hh = (cartaANIMAL.getHeight() / 4);
        int ww = (cartaANIMAL.getWidth() / 4);

        int hh2 = (cartaANIMAL.getHeight() / 2);
        int ww2 = 3 * (cartaANIMAL.getWidth() / 4);

        for (int i = 0; i < 1 + cartaANIMAL.getHeight() / 4; i++) {
            meiow = cartaANIMAL.getWidth() / 2;
            meiow2 = cartaANIMAL.getWidth() / 2;
            meiow3 = cartaANIMAL.getWidth() / 2;
            meiow4 = cartaANIMAL.getWidth() / 2;
            ww = 5 + (cartaANIMAL.getWidth() / 4);
            ww2 = (cartaANIMAL.getWidth() - 1);


            for (int j = 0; j < 1 + cartaANIMAL.getWidth() / 4; j++) {
                Color cor = cartaANIMAL.getColor(i, j);
                Color cor2 = cartaANIMAL.getColor(i, (cartaANIMAL.getWidth() - 1) - j);
                Color cor3 = cartaANIMAL.getColor(meioh, (cartaANIMAL.getWidth() - 1) - j);
                Color cor4 = cartaANIMAL.getColor(meioh4, j);

                Color cor5 = cartaANIMAL.getColor(meioh, meiow);
                Color cor6 = cartaANIMAL.getColor(meioh2, meiow2);
                Color cor7 = cartaANIMAL.getColor(meioh3, meiow3);
                Color cor8 = cartaANIMAL.getColor(meioh4, meiow4);
                Color cor9 = cartaANIMAL.getColor(i, meiow);
                Color cor10 = cartaANIMAL.getColor(i, meiow4);
                Color cor11 = cartaANIMAL.getColor(hh, ww);
                Color cor12 = cartaANIMAL.getColor(hh2, ww2);
                Color cor13 = cartaANIMAL.getColor((cartaANIMAL.getHeight() - 1) - i, (cartaANIMAL.getWidth() - 1) - j);
                Color cor14 = cartaANIMAL.getColor((cartaANIMAL.getHeight() - 1) - i, meiow);
                Color cor15 = cartaANIMAL.getColor((cartaANIMAL.getHeight() - 1) - i, ww);
                Color cor16 = cartaANIMAL.getColor((cartaANIMAL.getHeight() - 1) - i, meiow2);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean vai = true;
                    if (vai) {

                        inserirPixel(0, meioh4, j, cor4.red(), cor4.green(), cor4.blue());
                        inserirPixel(0, meioh, (cartaANIMAL.getWidth() - 1) - j, cor3.red(), cor3.green(), cor3.blue());

                        inserirPixel(0, i, j, cor.red(), cor.green(), cor.blue());
                        inserirPixel(0, i, (cartaANIMAL.getWidth() - 1) - j, cor2.red(), cor2.green(), cor2.blue());
                        inserirPixel(0, meioh, meiow, cor5.red(), cor5.green(), cor5.blue());
                        inserirPixel(0, meioh2, meiow2, cor6.red(), cor6.green(), cor6.blue());
                        inserirPixel(0, meioh3, meiow3, cor7.red(), cor7.green(), cor7.blue());
                        inserirPixel(0, meioh4, meiow4, cor8.red(), cor8.green(), cor8.blue());
                        inserirPixel(0, i, meiow, cor9.red(), cor9.green(), cor9.blue());
                        inserirPixel(0, i, meiow4, cor10.red(), cor10.green(), cor10.blue());
                        inserirPixel(0, hh, ww, cor11.red(), cor11.green(), cor11.blue());
                        inserirPixel(0, hh2, ww2, cor12.red(), cor12.green(), cor12.blue());
                        inserirPixel(0, (cartaANIMAL.getHeight() - 1) - i, (cartaANIMAL.getWidth() - 1) - j, cor13.red(), cor13.green(), cor13.blue());
                        inserirPixel(0, (cartaANIMAL.getHeight() - 1) - i, meiow, cor14.red(), cor14.green(), cor14.blue());
                        inserirPixel(0, (cartaANIMAL.getHeight() - 1) - i, ww, cor15.red(), cor15.green(), cor15.blue());


                        inserirPixel(0, (cartaANIMAL.getHeight() - 1) - i, meiow2, cor16.red(), cor16.green(), cor16.blue());


                        meiow++;
                        meiow2--;
                        meiow3++;
                        meiow4--;
                        ww--;
                        ww2--;

                    } else {
                        cartaANIMAL.setPixel(i, j, Color.rgb(0, 0, 0));

                    }
                }


            }
            meioh++;
            meioh2--;
            meioh3--;
            meioh4++;
            hh++;
            hh2--;

        }

        for (int i = 0; i < cartaANIMAL.getHeight(); i++) {
            for (int j = 0; j < cartaANIMAL.getWidth(); j++) {
                Color cor = cartaANIMAL.getColor(i, j);
                if (j % 2 == 0 && i % 2 == 0) {
                    setcor(i, j, c);
                } else if (j % 2 != 0 && i % 2 != 0) {
                    setcor(i, j, c);
                } else {
                    setcor(i, j, cc);
                }

            }
        }

    }


    private void limpar() {

        desenho = new ArrayList<>();
        desenho.add(null);

    }
}
