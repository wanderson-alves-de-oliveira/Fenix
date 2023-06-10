package com.wao.fenix.projetoz.menu;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Build;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.wao.fenix.R;
import com.wao.fenix.projetoz.dao.BDEstatusFase;
import com.wao.fenix.projetoz.dao.BDNave;
import com.wao.fenix.projetoz.dao.BDRecompensa;
import com.wao.fenix.projetoz.generico.recursos.Alerta;
import com.wao.fenix.projetoz.generico.recursos.ConvertBitimap;
import com.wao.fenix.projetoz.generico.recursos.Objeto3d;
import com.wao.fenix.projetoz.generico.recursos.SelecteControll;
import com.wao.fenix.projetoz.generico.recursos.Vetor3;
import com.wao.fenix.projetoz.modelo.EstatusFase;
import com.wao.fenix.projetoz.modelo.Nave;
import com.wao.fenix.projetoz.modelo.Recompensa;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
public class TelaInicial extends AppCompatActivity implements GLSurfaceView.Renderer, GLSurfaceView.OnTouchListener {
    private Objeto3d barra;
    private Objeto3d telaIntro;

    private int moverFundo = 0;
    private int fase = 0;
    private float volumeSon = 0.40f;
    private Vetor3 posinicialF;
    // private boolean selectFase = true;
    private boolean comMusica = true;
    private boolean comSons = true;

    private TartarugaCorrida tut;


    private float pontoDoEixoYInicio = 0;
    private float pontoDoEixoXInicio = 0;
    private int recolher = 1;
    private boolean iniciarTelaDeSelecao = true;


    private GL10 gl2;
    private int fasecarregada = 0;
    private int musicaDafase = 0;
    private Bitmap texturaValores;

    private int musicas = 0;
    private boolean musicaBoss = false;
    private boolean musicaInicioFase = false;
    private boolean iniciomenu = true;
    private boolean carregouValores = false;

    //private Objeto3d quadroInserirPalavra;
    private Objeto3d bolhaRef;
    private Objeto3d painel_a;
    private Objeto3d painel_b;
    private Objeto3d painel_c;
    private Objeto3d bolhaRef2;

    private int tipoDeCard = 0;

    private ArrayList<Objeto3d> painelMode;

    private Objeto3d btUpgrade;
    private Objeto3d btfundo;
    private Objeto3d btfundoup;
    private boolean iniciaraProxima = false;

    private ArrayList<Objeto3d> nivelAtaque;
    private ArrayList<Objeto3d> nivelEscudo;
    private ArrayList<Objeto3d> nivelIma;
    private ArrayList<Objeto3d> nivelBomb;

    private Objeto3d valorAdiquiridoObj;
    private Objeto3d vedeorecompensa;

    private Objeto3d nivelAtaqueT;
    private Objeto3d nivelEscudoT;
    private Objeto3d nivelImaT;
    private Objeto3d nivelBombT;

    private ArrayList<Objeto3d> nivelAtaqueP;
    private ArrayList<Objeto3d> nivelEscudoP;
    private ArrayList<Objeto3d> nivelImaP;
    private ArrayList<Objeto3d> nivelBombaP;

    private int nivelAtaqueR = 4;
    private int nivelEscudoR = 2;
    private int nivelBombaR = 0;
    private int nivelImaR = 0;


    private Objeto3d btoptions;
    private Objeto3d btStart;
    private Objeto3d btback;


    // private ArrayList<Objeto3d> bolhas;
    private ArrayList<Objeto3d> niveis;


    private AssetManager asset;


    private String tipo = "brinquedo";

    private MediaPlayer musica;
    private MediaPlayer musicaBossFase;
    private Nave nivelNave;
    private Recompensa recompensa;


    private ArrayList<MediaPlayer> musicaFase;
    private boolean regularSon = true;

    private boolean pause = true;
    private int musicaAtual = -2;
    private int musicaAnterior = -2;


    private int carga = -1;
    private Context context;


    private DisplayMetrics displayMetrics;
    private float h;
    private float w;

    private float wTela;


    private EGLConfig eglConfig;


    private final float velocidade = 0.012f;

    private float escala = 0.2f;


    int faseInit[] = {}; //Fully white

    private boolean x = false, y = false, z = false;

    float amarelo[] = {1.0f, 1.0f, 1.0f, 1.0f}; //Fully white
    float luzDifusa[] = {0.7f, 0.7f, 0.7f, 1.0f};//luz difusa
    private FloatBuffer corBufferG;
    private Objeto3d Fenixt;
    private boolean liberado = false;


    public boolean isX() {
        return this.x;
    }

    public void setX(boolean x) {
        this.x = x;
    }

    public boolean isY() {
        return this.y;
    }

    public void setY(boolean y) {
        this.y = y;
    }

    private FrameLayout frame;
    private ImageButton btamostra;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TelaInicial(Context context, AssetManager asset) throws IOException {

        //////////////INICIA VARIAVEIS BASICAS
        this.context = context;
        this.asset = asset;
        this.displayMetrics = context.getResources().getDisplayMetrics();
        this.h = this.displayMetrics.heightPixels;
        this.w = 720;
        this.wTela = this.displayMetrics.widthPixels;
        this.musicaFase = new ArrayList<>();
        MediaPlayer m = MediaPlayer.create(context, R.raw.musicb);
        m.setLooping(true);
        this.musicaFase.add(m);
        MediaPlayer m1 = MediaPlayer.create(context, R.raw.musicc);
        m1.setLooping(true);
        this.musicaFase.add(m1);
        MediaPlayer m2 = MediaPlayer.create(context, R.raw.musicd);
        m2.setLooping(true);
        this.musicaFase.add(m2);
        MediaPlayer m3 = MediaPlayer.create(context, R.raw.musicf);
        m3.setLooping(true);
        this.musicaFase.add(m3);
        MediaPlayer m4 = MediaPlayer.create(context, R.raw.musics);
        m4.setLooping(true);
        this.musicaFase.add(m4);

        this.musicaBossFase = MediaPlayer.create(context, R.raw.musice);
        this.musicaBossFase.setLooping(true);
        this.musicaBossFase.setVolume(0.4f, 0.4f);


        this.musica = MediaPlayer.create(context, R.raw.musica);
        this.musica.setLooping(true);
        this.musica.setVolume(0.4f, 0.4f);
        this.musica.start();

        /////////////////////////////////////////////////////
        faseInit = new int[]{0, 1, 2, 3, 4, 5, 2, 3, 4, 1, 5, 0, 2};
        EstatusFase v = new BDEstatusFase(context).buscarUltima();
        int ult = Math.toIntExact(v.getId());
        BDNave BDN = new BDNave(context);
        nivelNave = BDN.buscar(1);
        nivelImaR = nivelNave.getPuchar();
        nivelEscudoR = nivelNave.getEscudo();
        nivelAtaqueR = nivelNave.getAtaque();
        nivelBombaR = nivelNave.getBomba();

        BDRecompensa BDR = new BDRecompensa(context);
        recompensa = BDR.buscar(1);
        texturaValores = ConvertBitimap.getBitmap(String.valueOf(recompensa.getValor()));
        tut = new TartarugaCorrida(context, asset, ult, comSons, comMusica, nivelNave);

        frame = new FrameLayout(this.context);

        btamostra = new ImageButton(this.context);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.p100);
        btamostra.setBackground(drawable);
        btamostra.setVisibility(View.VISIBLE);

        btamostra.setLayoutParams(new FrameLayout.LayoutParams(200, 200));


        frame.setVisibility(View.VISIBLE);

        frame.addView(btamostra);
    }


    float girar = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void carregar(String tipo) throws IOException {
        //////FAZ O CARREGAMENTO DOS ARQUIVOS 3D DO JOGO INTEIRO
        ConvertBitimap convertBitimap = new ConvertBitimap();

        EstatusFase e = new BDEstatusFase(context).buscarUltima();

        long ultimaPassada = e != null ? e.getId() : 1l;

        switch (carga) {
            case 0:

//                TarefaAsync t = new TarefaAsync(context,asset);
//
//                t.execute(tut);

                // bolhas = new ArrayList<>();


                Fenixt = new Objeto3d(context, asset, "navez.obj", R.drawable.navea, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                //   Fenix.loadGLTexture(true);

                this.Fenixt.setEstado("NBateu");
                Fenixt.setPosition(new Vetor3(0, -0.05f, -0.5f));
                Fenixt.setGiroPosition(new Vetor3(95, 0, 0f));
                Fenixt.setRefletir(true);
                Fenixt.setFenix(true);
                Fenixt.setNomeRef("Fenix");
                posinicialF = new Vetor3(Fenixt.getPosition());

                this.telaIntro = new Objeto3d(context, asset, "intro.obj", R.drawable.fenixintro, new Vetor3(1f, 1f, 1f), "");
                this.telaIntro.setMudarTamanho(true);
                this.telaIntro.setPosition(new Vetor3(0, 0.02f, -0.9f));
                this.telaIntro.vezes(1.5f);
                this.telaIntro.setGiroPosition(new Vetor3(90, 0f, 0f));
                this.telaIntro.loadGLTexture();
                tut.carga = 0;
                tut.carregar();
                carga++;
                break;

            case 1:
                this.barra = new Objeto3d(context, asset, "tiroc.obj", R.drawable.spb, new Vetor3(1f, 1f, 1f), "");
                this.barra.setMudarTamanho(true);
                this.barra.setPosition(new Vetor3(0, -0.05f, -0.9f));
                this.barra.vezes(50);
                this.barra.setGiroPosition(new Vetor3(95, 0f, 0f));
                this.barra.loadGLTexture();


                bolhaRef = new Objeto3d(context, asset, "tiroc.obj", R.drawable.wao, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "");
                bolhaRef.setValor(String.valueOf(0));
                bolhaRef.setGiroPosition(new Vetor3(95, 0f, 0f));
                bolhaRef.setTransparente(true);
                bolhaRef.setPosition(new Vetor3(-100, -0.08f, -0.1f));


                bolhaRef2 = new Objeto3d(context, asset, "texto.obj", R.drawable.p100, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                bolhaRef2.setValor(String.valueOf(0));
                bolhaRef2.setGiroPosition(new Vetor3(90, 0f, 0f));
                bolhaRef2.setTransparente(true);
                bolhaRef2.vezes(0.06f);
                bolhaRef2.setPosition(new Vetor3(5.017f, -0.032f, -0.089f));
                bolhaRef2.setNomeRef("nivelT");
                bolhaRef2.loadGLTexture();

                tut.carga = 1;
                tut.carregar();

                painel_a = new Objeto3d(context, asset, "painel_a.obj", R.drawable.nota, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                painel_a.setValor(String.valueOf(0));
                painel_a.setGiroPosition(new Vetor3(90, 0f, 0f));
                painel_a.setTransparente(true);
                painel_a.vezes(0.60f);
                painel_a.setPosition(new Vetor3(5f, 0.040f, -0.79f));
                painel_a.setNomeRef("nivelT");
                painel_a.setCores(new Vetor3(1f, 1f, 1f));
                painel_a.loadGLTexture();


                painel_b = new Objeto3d(context, asset, "painel_a.obj", R.drawable.notago, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                painel_b.setValor(String.valueOf(0));
                painel_b.setGiroPosition(new Vetor3(90, 0f, 0f));
                painel_b.setTransparente(true);
                painel_b.vezes(0.02f);
                painel_b.setPosition(new Vetor3(5.017f, 0.00f, -0.089f));
                painel_b.setNomeRef("nivelT");
                painel_b.setCores(new Vetor3(1f, 1f, 1f));
                painel_b.loadGLTexture();

                painel_c = new Objeto3d(context, asset, "painel_a.obj", R.drawable.notavc, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                painel_c.setValor(String.valueOf(0));
                painel_c.setGiroPosition(new Vetor3(90, 0f, 0f));
                painel_c.setTransparente(true);
                painel_c.vezes(0.02f);
                painel_c.setPosition(new Vetor3(5.017f, 0.00f, -0.089f));
                painel_c.setNomeRef("nivelT");
                painel_c.setCores(new Vetor3(1f, 1f, 1f));
                painel_c.loadGLTexture();


                painelMode = new ArrayList<>();

                painelMode.add(painel_a);
                painelMode.add(painel_b);
                painelMode.add(painel_c);

                carga++;
                break;

            case 2:

                //selecao(0, 10, ultimaPassada);
                btfundo = new Objeto3d(context, asset, "btstart.obj", R.drawable.btfundo, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btfundo.setValor(String.valueOf(0));
                btfundo.setGiroPosition(new Vetor3(95, 0f, 0f));
                btfundo.setTransparente(true);
                btfundo.vezes(0.35f);
                btfundo.getGiroPosition().x = -200f;
                btfundo.loadGLTexture();
                btfundo.setPosition(new Vetor3(0f, -0.11f, -0.08f));

                btfundoup = new Objeto3d(context, asset, "btstart.obj", R.drawable.btfundo, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btfundoup.setValor(String.valueOf(0));
                btfundoup.setGiroPosition(new Vetor3(95, 0f, 0f));
                btfundoup.setTransparente(true);
                btfundoup.vezes(0.35f);
                btfundoup.getGiroPosition().x = 200f;
                btfundoup.loadGLTexture();
                btfundoup.setPosition(new Vetor3(0f, 0.030f, -0.08f));


                btUpgrade = new Objeto3d(context, asset, "button.obj", R.drawable.buttonup, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btUpgrade.setValor(String.valueOf(0));
                btUpgrade.setGiroPosition(new Vetor3(70, 0f, 0f));
                btUpgrade.setTransparente(true);
                btUpgrade.vezes(0.091f);
                btUpgrade.setPosition(new Vetor3(-0.025f, -0.086f, -0.089f));
                btUpgrade.loadGLTexture();

                btoptions = new Objeto3d(context, asset, "btoption.obj", R.drawable.btoption, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btoptions.setValor(String.valueOf(0));
                btoptions.setGiroPosition(new Vetor3(70, 0f, 0f));
                btoptions.setTransparente(true);
                btoptions.vezes(0.091f);
                btoptions.setPosition(new Vetor3(0.025f, -0.086f, -0.089f));
                btoptions.loadGLTexture();


                btback = new Objeto3d(context, asset, "btoption.obj", R.drawable.btback, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btback.setValor(String.valueOf(0));
                btback.setGiroPosition(new Vetor3(70, 0f, 0f));
                btback.setTransparente(true);
                btback.vezes(0.091f);
                btback.setPosition(new Vetor3(4.975f, -0.092f, -0.089f));
                btback.loadGLTexture();

                btStart = new Objeto3d(context, asset, "btstart.obj", R.drawable.btstart, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btStart.setValor(String.valueOf(0));
                btStart.setGiroPosition(new Vetor3(70, 0f, 0f));
                btStart.setTransparente(true);
                btStart.vezes(0.1f);
                btStart.setPosition(new Vetor3(0.00f, -0.09f, -0.089f));
                btStart.loadGLTexture();

                tut.carga = 2;
                tut.carregar();
                carga++;

                break;
            case 3:
                //  selecao(25, 40, ultimaPassada);

                nivelAtaque = new ArrayList<>();
                nivelEscudo = new ArrayList<>();
                nivelIma = new ArrayList<>();
                nivelBomb = new ArrayList<>();

                nivelAtaqueP = new ArrayList<>();
                nivelEscudoP = new ArrayList<>();
                nivelImaP = new ArrayList<>();
                nivelBombaP = new ArrayList<>();
                Objeto3d es = new Objeto3d(context, asset, "texto.obj", R.drawable.p100, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                es.setValor(String.valueOf(0));
                es.setGiroPosition(new Vetor3(90, 0f, 0f));
                es.setTransparente(true);
                es.vezes(0.06f);
                es.setPosition(new Vetor3(5.017f, -0.032f, -0.089f));
                es.setNomeRef("nivelT");
                es.loadGLTexture();
                nivelEscudoP.add(es);


                Objeto3d ess = new Objeto3d(context, asset, "texto.obj", R.drawable.max, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                ess.setValor(String.valueOf(0));
                ess.setGiroPosition(new Vetor3(90, 0f, 0f));
                ess.setTransparente(true);
                ess.vezes(0.06f);
                ess.setPosition(new Vetor3(5.017f, -0.032f, -0.089f));
                ess.setNomeRef("nivelT");
                ess.loadGLTexture();
                nivelEscudoP.add(ess);

                Objeto3d esa = new Objeto3d(context, asset, "texto.obj", R.drawable.p100, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                esa.setValor(String.valueOf(0));
                esa.setGiroPosition(new Vetor3(90, 0f, 0f));
                esa.setTransparente(true);
                esa.vezes(0.06f);
                esa.setPosition(new Vetor3(5.017f, -0.018f, -0.089f));
                esa.setNomeRef("nivelT");
                esa.loadGLTexture();
                nivelAtaqueP.add(esa);


                Objeto3d essa = new Objeto3d(context, asset, "texto.obj", R.drawable.max, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                essa.setValor(String.valueOf(0));
                essa.setGiroPosition(new Vetor3(90, 0f, 0f));
                essa.setTransparente(true);
                essa.vezes(0.06f);
                essa.setPosition(new Vetor3(5.017f, -0.018f, -0.089f));
                essa.setNomeRef("nivelT");
                essa.loadGLTexture();
                nivelAtaqueP.add(essa);


                Objeto3d esb = new Objeto3d(context, asset, "texto.obj", R.drawable.p100, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                esb.setValor(String.valueOf(0));
                esb.setGiroPosition(new Vetor3(90, 0f, 0f));
                esb.setTransparente(true);
                esb.vezes(0.06f);
                esb.setPosition(new Vetor3(5.017f, -0.058f, -0.089f));
                esb.setNomeRef("nivelT");
                esb.loadGLTexture();
                nivelBombaP.add(esb);


                Objeto3d essb = new Objeto3d(context, asset, "texto.obj", R.drawable.max, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                essb.setValor(String.valueOf(0));
                essb.setGiroPosition(new Vetor3(90, 0f, 0f));
                essb.setTransparente(true);
                essb.vezes(0.06f);
                essb.setPosition(new Vetor3(5.017f, -0.058f, -0.089f));
                essb.setNomeRef("nivelT");
                essb.loadGLTexture();
                nivelBombaP.add(essb);

                for (int i = 0; i < 5; i++) {

                    int reff = 0;

                    switch (i) {
                        case 0:
                            reff = R.drawable.p150;
                            break;
                        case 1:
                            reff = R.drawable.p300;
                            break;
                        case 2:
                            reff = R.drawable.p450;
                            break;
                        case 3:
                            reff = R.drawable.p600;
                            break;
                        case 4:
                            reff = R.drawable.max;
                            break;
                    }


                    Objeto3d ccc = new Objeto3d(context, asset, "texto.obj", reff, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    ccc.setValor(String.valueOf(0));
                    ccc.setGiroPosition(new Vetor3(90, 0f, 0f));
                    ccc.setTransparente(true);
                    ccc.vezes(0.06f);
                    ccc.setPosition(new Vetor3(5.017f, -0.045f, -0.089f));
                    ccc.setNomeRef("nivelT");
                    ccc.loadGLTexture();
                    nivelImaP.add(ccc);


                }


                nivelAtaqueT = new Objeto3d(context, asset, "texto.obj", R.drawable.nivelataquet, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                nivelAtaqueT.setValor(String.valueOf(0));
                nivelAtaqueT.setGiroPosition(new Vetor3(90, 0f, 0f));
                nivelAtaqueT.setTransparente(true);
                nivelAtaqueT.vezes(0.07f);
                nivelAtaqueT.setPosition(new Vetor3(4.985f, -0.018f, -0.089f));
                nivelAtaqueT.setNomeRef("nivelT");
                nivelAtaqueT.loadGLTexture();


                nivelEscudoT = new Objeto3d(context, asset, "texto.obj", R.drawable.nivelescudot, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                nivelEscudoT.setValor(String.valueOf(0));
                nivelEscudoT.setGiroPosition(new Vetor3(90, 0f, 0f));
                nivelEscudoT.setTransparente(true);
                nivelEscudoT.vezes(0.07f);
                nivelEscudoT.setPosition(new Vetor3(4.985f, -0.030f, -0.089f));
                nivelEscudoT.setNomeRef("nivelT");
                nivelEscudoT.loadGLTexture();

                nivelBombT = new Objeto3d(context, asset, "texto.obj", R.drawable.nivelbombt, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                nivelBombT.setValor(String.valueOf(0));
                nivelBombT.setGiroPosition(new Vetor3(90, 0f, 0f));
                nivelBombT.setTransparente(true);
                nivelBombT.vezes(0.07f);
                nivelBombT.setPosition(new Vetor3(4.985f, -0.054f, -0.089f));
                nivelBombT.setNomeRef("nivelT");
                nivelBombT.loadGLTexture();


                nivelImaT = new Objeto3d(context, asset, "texto.obj", R.drawable.nivelpullt, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                nivelImaT.setValor(String.valueOf(0));
                nivelImaT.setGiroPosition(new Vetor3(90, 0f, 0f));
                nivelImaT.setTransparente(true);
                nivelImaT.vezes(0.07f);
                nivelImaT.setPosition(new Vetor3(4.985f, -0.042f, -0.089f));
                nivelImaT.setNomeRef("nivelT");
                nivelImaT.loadGLTexture();


                valorAdiquiridoObj = new Objeto3d(context, asset, "texto.obj", R.drawable.nivelpullt, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");

                valorAdiquiridoObj.setValor(String.valueOf(0));
                valorAdiquiridoObj.setGiroPosition(new Vetor3(90, 0f, 0f));
                valorAdiquiridoObj.setTransparente(true);
                valorAdiquiridoObj.vezes(0.1f);
                valorAdiquiridoObj.setPosition(new Vetor3(4.985f, -0.01f, -0.089f));
                valorAdiquiridoObj.setNomeRef("nivelT");

                valorAdiquiridoObj.LoadTexture(texturaValores);
                // valorAdiquiridoObj.loadGLTexture();


                vedeorecompensa = new Objeto3d(context, asset, "texto.obj", R.drawable.vedeorecompensa, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                vedeorecompensa.setValor(String.valueOf(0));
                vedeorecompensa.setGiroPosition(new Vetor3(90, 0f, 0f));
                vedeorecompensa.setTransparente(true);
                vedeorecompensa.vezes(0.1f);
                vedeorecompensa.setPosition(new Vetor3(5.017f, -0.092f, -0.089f));
                vedeorecompensa.setNomeRef("nivelT");


                for (int i = 0; i < 5; i++) {

                    int reff = 0;

                    switch (i) {
                        case 0:
                            reff = R.drawable.life;
                            break;
                        case 1:
                            reff = R.drawable.lifee;
                            break;
                        case 2:
                            reff = R.drawable.lifeee;
                            break;
                        case 3:
                            reff = R.drawable.lifeeee;
                            break;
                        case 4:
                            reff = R.drawable.lifeeeee;
                            break;
                    }

                    Objeto3d c = new Objeto3d(context, asset, "top.obj", reff, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    c.setValor(String.valueOf(0));
                    c.setGiroPosition(new Vetor3(90, 0f, 0f));
                    c.setTransparente(true);
                    c.vezes(0.009f);
                    c.setPosition(new Vetor3(4.99f, -0.02f, -0.089f));
                    c.setNomeRef("nivel");
                    c.loadGLTexture();
                    nivelAtaque.add(c);

                    Objeto3d cc = new Objeto3d(context, asset, "top.obj", reff, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    cc.setValor(String.valueOf(0));
                    cc.setGiroPosition(new Vetor3(90, 0f, 0f));
                    cc.setTransparente(true);
                    cc.vezes(0.009f);
                    cc.setPosition(new Vetor3(4.99f, -0.032f, -0.089f));
                    cc.setNomeRef("nivel");
                    cc.loadGLTexture();
                    nivelEscudo.add(cc);

                    Objeto3d ccc = new Objeto3d(context, asset, "top.obj", reff, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    ccc.setValor(String.valueOf(0));
                    ccc.setGiroPosition(new Vetor3(90, 0f, 0f));
                    ccc.setTransparente(true);
                    ccc.vezes(0.009f);
                    ccc.setPosition(new Vetor3(4.99f, -0.045f, -0.089f));
                    ccc.setNomeRef("nivel");
                    ccc.loadGLTexture();
                    nivelIma.add(ccc);

                    Objeto3d cccc = new Objeto3d(context, asset, "top.obj", reff, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    cccc.setValor(String.valueOf(0));
                    cccc.setGiroPosition(new Vetor3(90, 0f, 0f));
                    cccc.setTransparente(true);
                    cccc.vezes(0.009f);
                    cccc.setPosition(new Vetor3(4.99f, -0.058f, -0.089f));
                    cccc.setNomeRef("nivel");
                    cccc.loadGLTexture();
                    nivelBomb.add(cccc);


                }


                tut.carga = 3;
                tut.carregar();
                carga++;
                break;
            case 4:

//
//                // selecao(40, 64, ultimaPassada);
//                if (ultimaPassada > 4) {
//                    while (bolhas.get((int) (ultimaPassada - 1)).getPosition().y > -0.04f) {
//                        for (Objeto3d ob : bolhas) {
//
//                            ob.getPosition().y -= velocidade * 0.2f;
//                        }
//                    }
//
//                } else {
//                    for (Objeto3d ob : bolhas) {
//
//                        ob.getPosition().y += velocidade * 1.8f;
//                    }
//                }

                tut.carga = 4;
                tut.carregar();
                carga++;
                break;
            default:
                //  selecao(25, 40, ultimaPassada);
                if (!tut.carregamentoDireto) {
                    //  tut.carga++;
                    tut.carregar();
                    if (tut.carga >= 15) {
                        tut.carregamentoDireto = true;


                        liberado = true;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (Looper.myLooper() == null) {
                                    Looper.prepare();
                                } else {
                                    Looper.getMainLooper().getThread().interrupt();
                                }
                            }
                            tut.onSurfaceCreated(gl2, this.eglConfig);

                        }
                    }
                } else {
                    liberado = true;
                }

                carga++;
                break;

        }


    }


//    public void selecao(int in, int on, long ultimaPassada) {
//        ConvertBitimap convertBitimap = new ConvertBitimap();
//        for (int p = in; p < on; p++) {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                try {
//                    bolhas.add(new Objeto3d(context, R.drawable.naveanorm, asset, "tiroc.obj", convertBitimap.getBitmapBolha(String.valueOf(String.valueOf(p + 1)), 100, 0, ultimaPassada), new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//            bolhas.get(p).setValor(String.valueOf(p));
//            bolhas.get(p).setGiroPosition(new Vetor3(95, 0f, 0f));
//            bolhas.get(p).setTransparente(true);
//            bolhas.get(p).vezes(0.3f);
//            float py = (p * 0.010f) - 0.03f;
//            float px = 0.03f;
//            if (p % 2 == 0) {
//                py = (p * 0.010f) - 0.03f;
//                px = -0.020f;
//            }
//            bolhas.get(p).setPosition(new Vetor3(px, py - 0.05f, 0.1f));
//        }
//
//    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        this.eglConfig = config;

        if (fasecarregada == 0) {
            gl.glEnable(GL10.GL_TEXTURE_2D);                  //abilita mapeamento de textura
            gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);       //  Background preto
            gl.glClearDepthf(1.0f);                              //Depth Buffer Setup
            gl.glDepthFunc(GL10.GL_LEQUAL);                  //The Type Of Depth Testing To Do
            gl.glEnable(GL10.GL_LIGHTING);//ABILITA A LUZ
            gl.glEnable(GL10.GL_LIGHT1);
            gl.glEnable(GL10.GL_LIGHT0);
            gl.glEnable(GL10.GL_DEPTH_TEST);                  //Enables Depth Testing
            gl.glShadeModel(GL10.GL_SMOOTH);                  //abilita sombreamento
            gl.glEnable(GL10.GL_COLOR_MATERIAL);//abilita uso de matrial
            GLES20.glCullFace(GLES20.GL_FRONT);//desenha somente a parte de fora do objeto
            float[] ambientLight = new float[]{1.0f, 1.0f, 1.0f, 1.0f};//cor amarela do ambiente
            ByteBuffer vbg = ByteBuffer.allocateDirect(amarelo.length * 4);
            vbg.order(ByteOrder.nativeOrder());
            corBufferG = vbg.asFloatBuffer();
            corBufferG.put(amarelo);
            corBufferG.position(0);
            float posicaoLuz[] = {0, -30, 0, 1};
            gl.glLightfv(gl.GL_COLOR_MATERIAL, gl.GL_FRONT, corBufferG);
            float especularidade[] = {1.0f, 1.0f, 1.0f, 1.0f};
            int especMaterial = 120;
// Define a concentração do brilho
            gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, especMaterial);
            gl.glMaterialf(gl.GL_FRONT, gl.GL_EMISSION, especMaterial);
            gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);

            gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, especularidade, 0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, luzDifusa, 0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, ambientLight, 0);

            gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, ambientLight, 0);
            gl.glLightfv(gl.GL_LIGHT1, gl.GL_DIFFUSE, luzDifusa, 0);
            gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPECULAR, especularidade, 0);
            gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, posicaoLuz, 0);


            gl.glMatrixMode(gl.GL_PROJECTION);

            gl.glMatrixMode(GL10.GL_MODELVIEW);

            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                    GL10.GL_CLAMP_TO_EDGE);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                    GL10.GL_CLAMP_TO_EDGE);
            //Really Nice Perspective Calculations
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_CLAMP_TO_EDGE);


        } else if (fasecarregada == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                tut.onSurfaceCreated(gl, this.eglConfig);

            }
            //  fasecarregada = false;
        } else if (fasecarregada == 2000) {

        }
        this.gl2 = gl;

    }

    public void pausar(boolean p) {

        pauseMusica(musicaAtual, p);

    }


    float distanciaX = 0f;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDrawFrame(GL10 gl) {
        if (fasecarregada == 1) {

            if (tut == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Looper.myLooper() == null) {
                        Looper.prepare();
                    } else {
                        Looper.getMainLooper().getThread().interrupt();
                    }
                }
                try {
                    //     musica.pause();

                    tut = new TartarugaCorrida(context, asset, fase, comSons, comMusica, nivelNave);

                    tut.onSurfaceCreated(gl, this.eglConfig);

                } catch (Exception ioException) {
                    ioException.printStackTrace();
                }

                tut.onDrawFrame((GL11) gl);


            } else {


                tut.onDrawFrame((GL11) gl);

                if (tut.selectFase) {
                    fasecarregada = 2;
                    BDRecompensa BDR = new BDRecompensa(context);
                    recompensa = BDR.buscar(1);
                    tipoDeCard = tut.getTipoDeCard();
                    BDNave BDN = new BDNave(context);
                    nivelNave = BDN.buscar(1);
                    nivelImaR = nivelNave.getPuchar();
                    int ecudoaux = nivelNave.getEscudo();
                    nivelNave.setEscudo(ecudoaux == -1 ? 0 : ecudoaux);
                    nivelEscudoR = nivelNave.getEscudo();
                    nivelBombaR = nivelNave.getBomba();
                    nivelAtaqueR = nivelNave.getAtaque();
                    BDN = new BDNave(context);
                    BDN.atualizarNave(nivelNave);
                    carregouValores = false;
                    musicaInicioFase = false;
                    mudarMusica(-2, false, true);
//                    try {
//                      //  mudarLista();
                    tut.selectFase = false;
//                    } catch (IOException e) {
//                        e.printStackTrace();
                    //  }
                }


            }

            String[] fasex = String.valueOf(fase).split("");
            int indiceLevel = 0;
            if (fasex.length > 1) {
                indiceLevel = Integer.parseInt(fasex[0]);
            } else {
                indiceLevel = 0;
            }
            if (tut.isHoraDoBoss() && !musicaBoss) {
                mudarMusica(-1, true, false);

            } else if (musicaInicioFase || (!tut.isHoraDoBoss() && musicaBoss && !tut.isBossEliminado() && fasecarregada == 1)) {
                mudarMusica(indiceLevel, false, false);

            }

//            if (tut.isLimitVelocidadeBoo()) {
//                pausar(true);
//            } else {
//                pausar(false);
//
//            }

        } else if (fasecarregada == 2000) {


        } else {

            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);


            float totaly = ((this.displayMetrics.heightPixels / 2) / this.displayMetrics.scaledDensity) * 0.0001f;
            gl.glLoadIdentity();
            gl.glRotatef(0, 0, 0, 0);
            gl.glTranslatef(distanciaX, 0.04f, -0.01f);

            moverTela();
//            girar+=0.1f;
//            if(Fenixt!=null) {
//                Fenixt.setGiroPosition(new Vetor3(30, girar, 0f));
//            }
            if (!musicaInicioFase) {
                mudarMusica(-2, false, true);

            }
            if (carga > 1 && tut.carga < 4 || tut.carga > 7 && tut.carga < 14) {

                this.telaIntro.draw((GL11) gl2);
            } else if (tut.carga >= 14) {
                this.barra.draw((GL11) gl2);
//                if (moverFundo == 0) {
//                    this.barra.getPosition().x += 0.0002f;
//                    if (this.barra.getPosition().x >= 0.5f) {
//                        moverFundo = 1;
//                    }
//                } else {
//                    this.barra.getPosition().x -= 0.0002f;
//                    if (this.barra.getPosition().x <= -0.5f) {
//                        moverFundo = 0;
//                    }
//                }
            }
            if (tut.carga == 4) {
                Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.fenixload); // 1

                this.telaIntro.vezes(0.8f);

                this.telaIntro.LoadTexture(image);


            }
            if (tut.carga > 13) {


                //controleDeTela();
//                for (int p = 0; p < bolhas.size(); p++) {
//                   bolhas.get(p).draw((GL11) gl2);
//                }
                Fenixt.draw((GL11) gl2);

                this.btUpgrade.draw((GL11) gl2);
                this.btStart.draw((GL11) gl2);
                this.btoptions.draw((GL11) gl2);
                this.btfundo.draw((GL11) gl2);
                this.btfundoup.draw((GL11) gl2);
                float x = nivelEscudoP.get(0).getPosition().x;
                float y = nivelEscudoP.get(0).getPosition().y;
                float z = nivelEscudoP.get(0).getPosition().z;

                //   nivelEscudoP.get(0).setPosition(new Vetor3(x,y,z-velocidade));
                if (distanciaX * -1 > 0) {
                    painelMode.get(0).draw((GL11) gl2);
                    if (tipoDeCard != 0)
                        painelMode.get(tipoDeCard == -1 ? 1 : 2).draw((GL11) gl2);

                    this.btback.draw((GL11) gl2);
                    nivelAtaqueT.draw((GL11) gl2);
                    nivelEscudoT.draw((GL11) gl2);
                    nivelBombT.draw((GL11) gl2);


                    nivelImaT.draw((GL11) gl2);
                    valorAdiquiridoObj.draw((GL11) gl2);
                    vedeorecompensa.draw((GL11) gl2);
                    nivelAtaqueP.get(nivelAtaqueR == 4 ? 1 : 0).draw((GL11) gl2);
                    nivelBombaP.get(nivelBombaR == 4 ? 1 : 0).draw((GL11) gl2);


                    nivelImaP.get(nivelImaR).draw((GL11) gl2);
                    nivelAtaque.get(nivelAtaqueR).draw((GL11) gl2);
                    nivelEscudo.get(nivelEscudoR).draw((GL11) gl2);
                    nivelBomb.get(nivelBombaR).draw((GL11) gl2);
                    nivelEscudoP.get(nivelEscudoR == 4 ? 1 : 0).draw((GL11) gl2);

                    nivelIma.get(nivelImaR).draw((GL11) gl2);
                    bolhaRef2.draw((GL11) gl2);

                }

            } else {
                if (bolhaRef != null && tut.carga > 3 && tut.carga < 8) {
                    bolhaRef.setPosition(new Vetor3(0, -0.02f, -0.1f));
                    //   bolhaRef.setGiroPosition(new Vetor3(95, bolhaRef.getGiroPosition().y + 8, 0));
                    bolhaRef.draw((GL11) gl2);

                }
            }


            try {

                if (carga < 0)
                    carga++;
                if (tut.carga < 15)
                    carregar(tipo);
                ///  carga++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void moverTela() {
        if (fasecarregada == 2) {
            distanciaX = -5;
            Fenixt.setPosition(new Vetor3(4.84f, 0.34f, -0.78f));
            if (!carregouValores) {
                texturaValores = ConvertBitimap.getBitmap(String.valueOf(recompensa.getValor()));
                valorAdiquiridoObj.LoadTexture(texturaValores);
                carregouValores = true;
            }
            if (barra != null)
                barra.getPosition().setX(distanciaX * -1);
        } else {
            distanciaX = 0;
            if (posinicialF != null)
                Fenixt.setPosition(posinicialF);
            if (barra != null)
                barra.getPosition().setX(distanciaX * -1);

        }
    }

    private void mudarMusica(int num, boolean mboss, boolean mMusica) {


        switch (num) {
            case 0:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    musicaAnterior = -2;
                } else if (this.musicaBossFase.isPlaying()) {
                    this.musicaBossFase.pause();
                    musicaAnterior = -1;

                }

                musicaAtual = 0;
                if (musicaAnterior == -1) {
                    regularSontul(this.musicaBossFase, this.musicaFase.get(0), mboss, mMusica);
                } else {
                    regularSontul(this.musica, this.musicaFase.get(0), mboss, mMusica);

                }
                break;
            case 1:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    musicaAnterior = -2;

                } else if (this.musicaBossFase.isPlaying()) {
                    this.musicaBossFase.pause();
                    musicaAnterior = -1;

                }


                if (musicaAnterior == -1) {
                    regularSontul(this.musicaBossFase, this.musicaFase.get(1), mboss, mMusica);
                } else {
                    regularSontul(this.musica, this.musicaFase.get(1), mboss, mMusica);

                }
                musicaAtual = 1;

                break;
            case 2:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    musicaAnterior = -2;

                } else if (this.musicaBossFase.isPlaying()) {
                    this.musicaBossFase.pause();
                    musicaAnterior = -1;

                }

                if (musicaAnterior == -1) {
                    regularSontul(this.musicaBossFase, this.musicaFase.get(2), mboss, mMusica);
                } else {
                    regularSontul(this.musica, this.musicaFase.get(2), mboss, mMusica);

                }
                musicaAtual = 2;

                break;
            case 3:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    musicaAnterior = -2;

                } else if (this.musicaBossFase.isPlaying()) {
                    this.musicaBossFase.pause();
                    musicaAnterior = -1;

                }

                if (musicaAnterior == -1) {
                    regularSontul(this.musicaBossFase, this.musicaFase.get(3), mboss, mMusica);
                } else {
                    regularSontul(this.musica, this.musicaFase.get(3), mboss, mMusica);

                }
                musicaAtual = 3;

                break;
            case 4:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    musicaAnterior = -2;

                } else if (this.musicaBossFase.isPlaying()) {
                    this.musicaBossFase.pause();
                    musicaAnterior = -1;

                }

                if (musicaAnterior == -1) {
                    regularSontul(this.musicaBossFase, this.musicaFase.get(4), mboss, mMusica);
                } else {
                    regularSontul(this.musica, this.musicaFase.get(4), mboss, mMusica);

                }
                musicaAtual = 4;

                break;
            case -2:
                //    regularSontul(this.musicaBossFase, this.musica, mboss, mMusica);

                if (musicaBossFase.isPlaying()) {
                    musicaBossFase.pause();
                }
                //  musica.setVolume(0.40f, 0.40f);
                musica.seekTo(0);
                musica.start();

                musicaBoss = false;
                musicaInicioFase = true;
                regularSon = true;


                //  mudarMusica(-2, false, true);


                musicaAtual = -2;

                break;
            case -1:
                regularSontul(this.musicaFase.get(musicaDafase), this.musicaBossFase, mboss, mMusica);
                musicaAtual = -1;

                break;
        }


    }


    private void pauseMusica(int num, boolean p) {


        switch (num) {
            case 0:
                if (p) {
                    this.pause = false;
                    if (this.musicaFase.get(0).isPlaying())
                        this.musicaFase.get(0).pause();
                    // pausar( true );

                } else {
                    this.pause = true;
                    if (!this.musicaFase.get(0).isPlaying())
                        this.musicaFase.get(0).start();
                    //pausar( false );
                }


                break;
            case 1:


                if (p) {
                    this.pause = false;
                    this.musicaFase.get(1).pause();
                    // pausar( true );

                } else {
                    this.pause = true;
                    this.musicaFase.get(1).start();
                    //pausar( false );
                }


                break;
            case 2:


                if (p) {
                    this.pause = false;
                    this.musicaFase.get(2).pause();
                    // pausar( true );

                } else {
                    this.pause = true;
                    this.musicaFase.get(2).start();
                    //pausar( false );
                }
                break;
            case 3:


                if (p) {
                    this.pause = false;
                    this.musicaFase.get(3).pause();
                    // pausar( true );

                } else {
                    this.pause = true;
                    this.musicaFase.get(3).start();
                    //pausar( false );
                }
                break;
            case 4:


                if (p) {
                    this.pause = false;
                    this.musicaFase.get(4).pause();
                    // pausar( true );

                } else {
                    this.pause = true;
                    this.musicaFase.get(4).start();
                    //pausar( false );
                }
                break;
            case -2:
                if (p) {
                    this.pause = false;
                    this.musica.pause();
                    // pausar( true );

                } else {
                    this.pause = true;
                    this.musica.start();
                    //pausar( false );
                }


                break;
            case -1:


                if (p) {
                    this.pause = false;
                    this.musicaBossFase.pause();
                    // pausar( true );

                } else {
                    this.pause = true;
                    this.musicaBossFase.start();
                    //pausar( false );
                }
                break;
        }


    }

    private void regularSontul(MediaPlayer m1, MediaPlayer m2, boolean mboss, boolean mMusica) {

        if (regularSon) {
            volumeSon -= 0.004;

        } else {
            volumeSon += 0.004;

        }
        if (volumeSon <= 0.008) {
            if (m1.isPlaying()) {
                m1.pause();
            }

            //volumeSon += 0.004;
            m1.setVolume(volumeSon, volumeSon);
            m2.setVolume(volumeSon, volumeSon);
            m2.seekTo(0);
            m2.start();
            regularSon = false;
        }
        m1.setVolume(volumeSon, volumeSon);
        m2.setVolume(volumeSon, volumeSon);

        if (volumeSon >= 0.40) {
            musicaBoss = mboss;
            musicaInicioFase = mMusica;
            regularSon = true;

        }
    }


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void mudarLista() throws IOException {
//        ConvertBitimap convertBitimap = new ConvertBitimap();
//        EstatusFase e = new BDEstatusFase(context).buscarUltima();
//
//        long ultimaPassada = e != null ? e.getId() : 1l;
//
////        Objeto3d ob = new Objeto3d(context, R.drawable.naveanorm, asset, "tiroc.obj", convertBitimap.getBitmapBolha(String.valueOf(String.valueOf(fase + 1)), 100, 0, ultimaPassada), new Vetor3(escala * 5f, escala * 5f, escala * 5f), "");
////        ob.setPosition(bolhas.get(fase).getPosition());
////        ob.setGiroPosition(bolhas.get(fase).getGiroPosition());
////        ob.setValor(bolhas.get(fase).getValor());
////        ob.setTransparente(true);
////        ob.vezes(0.5f);
////
////        bolhas.set(fase, ob);
//
//        if (ultimaPassada == fase + 1) {
//if(fase>=9){
//    fase=0;
//}
//            Objeto3d ob2 = new Objeto3d(context, R.drawable.naveanorm, asset, "tiroc.obj", convertBitimap.getBitmapBolha(String.valueOf(String.valueOf(fase + 2)), 100, 0, ultimaPassada), new Vetor3(escala * 5f, escala * 5f, escala * 5f), "");
//            ob2.setPosition(bolhas.get(fase + 1).getPosition());
//            ob2.setGiroPosition(bolhas.get(fase + 1).getGiroPosition());
//            ob2.setValor(bolhas.get(fase + 1).getValor());
//            ob2.setTransparente(true);
//            ob2.vezes(0.5f);
//
//            bolhas.set(fase + 1, ob2);
//
//        }
//
//
//    }

//    private void controleDeTela() {
//        if (iniciarTelaDeSelecao)
//            surgirListagenDeFases(recolher);
//
//
//    }

//    public void surgirListagenDeFases(int recolher) {
//
//        switch (recolher) {
//            case 1:
//                for (int p = 0; p < bolhas.size(); p++) {
//                    if (bolhas.get(0).getPosition().z > -0.1f) {
//                        bolhas.get(p).getPosition().z -= 0.008f;
//                        btStart.getPosition().z = -0.092f;
//                    } else {
//                        iniciarTelaDeSelecao = false;
//                        btStart.getPosition().z = -0.089f;
//
//                    }
//                }
//                break;
//            case 2:
//                for (int p = 0; p < bolhas.size(); p++) {
//                    if (bolhas.get(0).getPosition().z < 0.1f) {
//                        bolhas.get(p).getPosition().z += 0.008f;
//                        btUpgrade.getPosition().z = -0.092f;
//
//                    } else {
//                        iniciarTelaDeSelecao = false;
//                        btUpgrade.getPosition().z = -0.089f;
//
//                    }
//                }
//                break;
//            case 3:
//                for (int p = 0; p < bolhas.size(); p++) {
//                    if (bolhas.get(0).getPosition().z < 0.1f) {
//                        bolhas.get(p).getPosition().z += 0.008f;
//                        btoptions.getPosition().z = -0.092f;
//
//                    } else {
//                        iniciarTelaDeSelecao = false;
//                        btoptions.getPosition().z = -0.089f;
//
//                    }
//                }
//                break;
//        }
//    }

    //FAZ ATUALIZAÇÕES NA ESTRUTURA DOS OBJETOS
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {


        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl.glLoadIdentity();

        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 80.0f, (float) width / (float) height, 0.01f,
                25.0f);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset the modelview matrix
    }

    public Double calculoarDistancia(Objeto3d obj, Vetor3 v) {
        double d = Math.sqrt(Math.pow(obj.getPosition().x - (v.x), 2)
                + Math.pow(obj.getPosition().z - (v.z), 2)
                + Math.pow(obj.getPosition().y - (v.y), 2));
        return d;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void validarToque(MotionEvent event) {
        int totaly = this.displayMetrics.heightPixels;
        int totalx = this.displayMetrics.widthPixels;
        float x = event.getX();
        float y = event.getY();
        float basey = ((y / this.displayMetrics.scaledDensity) * 0.000177f) - 0.0309f;
        float basex = (-1 * distanciaX) + ((x / this.displayMetrics.scaledDensity) * 0.000177f) - 0.0309f;

        float basey2 = ((y / this.displayMetrics.scaledDensity) * 0.000177f) - 0.0309f;
        float basex2 = (-1 * distanciaX) + ((x / this.displayMetrics.scaledDensity) * 0.000177f) - 0.034f;

        bolhaRef.getPosition().y = (basey) * -1;
        bolhaRef.getPosition().x = basex;


        bolhaRef2.getPosition().y = (basey2) * -1;
        bolhaRef2.getPosition().x = basex2;


        boolean sel = false;
        //    for (Objeto3d obj : bolhas) {
        //  double start = calculoarDistancia(obj, bolhaRef.getPosition());
        if (/*start <= 0.025 || */iniciaraProxima && distanciaX == 0) {
            sel = true;
            bolhaRef.getPosition().y = -100;
            bolhaRef.getPosition().x = -100;
            ConvertBitimap convertBitimap = new ConvertBitimap();
            BDEstatusFase bdEstatusFase = new BDEstatusFase(context);

//                if (!iniciaraProxima)
            //                  fase = 1;
            iniciaraProxima = false;

            long i = bdEstatusFase.buscarUltima().getId();
            bdEstatusFase.fechar();
            if (fase <= i) {

                EstatusFase v = new BDEstatusFase(context).buscar(fase + 1);


                boolean completa = v.getProgresso().equals("COMPLETA");
                boolean killAll = v.getInimigosEliminados() >= v.getInimigosGerados() && v.getInimigosGerados() > 0;
                boolean life80 = v.getSaude() >= 80;
                Alerta alerta = new Alerta(context);
                alerta.enviarAlerta("STAGE - " + (fase + 1), true, completa, killAll, life80).show();


                alerta.getBtSim().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //    selectFase = false;
                        alerta.fechar();


                        String[] fasex = String.valueOf(fase).split("");

                        if (fasex.length > 1) {
                            musicaDafase = Integer.parseInt(fasex[0]);

                        } else {
                            musicaDafase = 0;
                        }

                        fasecarregada = 1;


                        musicaInicioFase = true;
                        iniciomenu = false;

//                            fasecarregada = 0;
//                            musicaInicioFase = false;
//                            mudarMusica(-2, false, true);

                        if (tut != null) {
                            // tut.destroy();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (Looper.myLooper() == null) {
                                    Looper.prepare();
                                } else {
                                    Looper.getMainLooper().getThread().interrupt();
                                }
                            }
                            tut.tartarugaF(fase, comSons, comMusica, nivelNave);
                            carregouValores = false;

                        }


                    }
                });


                alerta.getBtNao().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  selectFase = true;
                        alerta.fechar();
                    }
                });
            }
            //break;
        }
        //  }

        double option = calculoarDistancia(btoptions, bolhaRef.getPosition());

        if (option <= 0.015 && !sel) {
            SelecteControll selecteControll = new SelecteControll(context);
            selecteControll.enviarAlerta().show();

            if (comMusica) {
                selecteControll.getBtMusic().getBackground().setTint(Color.parseColor("#127127"));

            } else {
                selecteControll.getBtMusic().getBackground().setTint(Color.parseColor("#999999"));

            }
            if (comSons) {
                selecteControll.getBtSon().getBackground().setTint(Color.parseColor("#127127"));

            } else {
                selecteControll.getBtSon().getBackground().setTint(Color.parseColor("#999999"));

            }

            selecteControll.getBtMusic().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (comMusica) {
                        selecteControll.getBtMusic().getBackground().setTint(Color.parseColor("#999999"));
                        comMusica = false;
                        if (musica.isPlaying())
                            musica.pause();
                    } else {
                        selecteControll.getBtMusic().getBackground().setTint(Color.parseColor("#127127"));
                        comMusica = true;
                        if (!musica.isPlaying())
                            musica.start();
                    }

                }
            });

            selecteControll.getBtSon().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comSons) {
                        selecteControll.getBtSon().getBackground().setTint(Color.parseColor("#999999"));
                        comSons = false;
                    } else {
                        selecteControll.getBtSon().getBackground().setTint(Color.parseColor("#127127"));
                        comSons = true;
                    }

                }
            });

            selecteControll.getBtfechar().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selecteControll.fechar();
                }
            });


        }
        double upgrade = calculoarDistancia(btUpgrade, bolhaRef.getPosition());


        if (upgrade <= 0.02f) {


            fasecarregada = 2;
            tipoDeCard = 0;
//            }

        }

        double back = calculoarDistancia(btback, bolhaRef.getPosition());
        if (back <= 0.02f) {

            fasecarregada = 0;
        }

        BDRecompensa BDR = new BDRecompensa(context);
        recompensa = BDR.buscar(1);
        double plusestrelas = calculoarDistancia(vedeorecompensa, bolhaRef.getPosition());

        if (plusestrelas <= 0.015f) {
            recompensa.setValor(recompensa.getValor() + 450);
            new BDRecompensa(context).atualizarRecompensa(recompensa);
            carregouValores = false;
        }
    }

    private void loja(int num) {
        BDNave BDN = new BDNave(context);
        BDRecompensa BDR = new BDRecompensa(context);
        recompensa = BDR.buscar(1);


//        double plusPull = calculoarDistancia(nivelImaP.get(nivelImaR), bolhaRef2.getPosition());
//        double plusEc = calculoarDistancia(nivelEscudoP.get(0), bolhaRef2.getPosition());
//        double plusAt = calculoarDistancia(nivelAtaqueP.get(0), bolhaRef2.getPosition());
//        double plusBb = calculoarDistancia(nivelBombaP.get(0), bolhaRef2.getPosition());

        switch (num) {
            case 1:
                if (nivelAtaqueR < 4) {
                    int preco = 100;
                    if (preco != -1 && preco <= recompensa.getValor()) {
                        recompensa.setValor(recompensa.getValor() - preco);
                        new BDRecompensa(context).atualizarRecompensa(recompensa);


                        carregouValores = false;

                        nivelAtaqueR++;
                        nivelNave.setAtaque(nivelAtaqueR);
                    } else {
                        Toast.makeText(context, "No stars", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Max level", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (nivelEscudoR < 4) {
                    int preco = 100;
                    if (preco != -1 && preco <= recompensa.getValor()) {

                        recompensa.setValor(recompensa.getValor() - preco);
                        new BDRecompensa(context).atualizarRecompensa(recompensa);

                        carregouValores = false;

                        nivelEscudoR++;
                        nivelNave.setEscudo(nivelEscudoR);
                    } else {
                        Toast.makeText(context, "No stars", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Max level", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (nivelImaR < 4) {
                    int preco = buscarPreco(nivelImaR);
                    if (preco != -1 && preco <= recompensa.getValor()) {
                        recompensa.setValor(recompensa.getValor() - preco);
                        new BDRecompensa(context).atualizarRecompensa(recompensa);

                        carregouValores = false;
                        nivelImaR++;
                        nivelNave.setPuchar(nivelImaR);
                    } else {
                        Toast.makeText(context, "No stars", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Max level", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if (nivelBombaR < 4) {
                    int preco = 100;
                    if (preco != -1 && preco <= recompensa.getValor()) {
                        recompensa.setValor(recompensa.getValor() - preco);
                        new BDRecompensa(context).atualizarRecompensa(recompensa);

                        carregouValores = false;
                        nivelBombaR++;
                        nivelNave.setBomba(nivelBombaR);
                    } else {
                        Toast.makeText(context, "No stars", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Max level", Toast.LENGTH_SHORT).show();
                }
                break;
            case 0:

                break;


        }
        BDN.atualizarNave(nivelNave);
    }




//    private void moverListagemDefases(MotionEvent event) {
//
//        if (event.getY() > pontoDoEixoYInicio) {
//
//            if (bolhas.get(0).getPosition().y < -0.02f && bolhas.get(bolhas.size() - 1).getPosition().y > -0.02f) {
//
//                for (int p = 0; p < bolhas.size(); p++) {
//                    bolhas.get(p).getPosition().y -= velocidade * 0.0002f;
//                }
//            } else {
//                pontoDoEixoYInicio = event.getY();
//                for (int p = 0; p < bolhas.size(); p++) {
//                    bolhas.get(p).getPosition().y += velocidade * 0.0002f;
//                }
//            }
//
//
//        } else {
//            if (bolhas.get(0).getPosition().y < -0.02f && bolhas.get(bolhas.size() - 1).getPosition().y > -0.02f) {
//
//                for (int p = 0; p < bolhas.size(); p++) {
//                    bolhas.get(p).getPosition().y += velocidade * 0.0002f;
//                }
//            } else {
//                pontoDoEixoYInicio = event.getY();
//                for (int p = 0; p < bolhas.size(); p++) {
//                    bolhas.get(p).getPosition().y -= velocidade * 0.0002f;
//                }
//            }
//
//        }
//
//    }

    private int buscarPreco(int n) {
        int preco = 0;
        switch (n + 1) {
            case 1:
                preco = 150;
                break;
            case 2:
                preco = 300;
                break;
            case 3:
                preco = 450;
                break;
            case 4:
                preco = 600;
                break;
            default:
                preco = -1;
                break;
        }
        return preco;
    }

    ///GERENCIA O TOQUE NA TELA
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if (liberado) {
            if (fasecarregada == 0 || fasecarregada == 2) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {


                    pontoDoEixoYInicio = event.getY();
                    pontoDoEixoXInicio = event.getX();

                    if (event.getY() < h * 0.75) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            validarToque(event);
                        }
                    }
                    if (event.getY() > h * 0.75 && event.getX() < this.wTela * 0.3) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            recolher = 2;
                            validarToque(event);
                            //       iniciarTelaDeSelecao = true;
                        }
                    } else if (event.getY() > h * 0.75 && event.getX() > this.wTela * 0.3 && event.getX() < this.wTela * 0.6) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            recolher = 1;
                            //     iniciarTelaDeSelecao = true;
                            fase = Integer.valueOf(new BDEstatusFase(context).buscarUltima().getId().toString());
                            iniciaraProxima = true;
                            validarToque(event);
                        }
                    } else if (event.getY() > h * 0.75 && event.getX() > this.wTela * 0.6) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            recolher = 3;
                            validarToque(event);
                        }
                    } else if ((distanciaX * -1) > 0 && event.getX() > this.wTela * 0.4) {
                        if (event.getY() > h * 0.3 && event.getY() < h * 0.4) {
                          //  Toast.makeText(context, "40", Toast.LENGTH_SHORT).show();
                            loja(1);
                        } else if (event.getY() > h * 0.4 && event.getY() < h * 0.5) {
                           // Toast.makeText(context, "50", Toast.LENGTH_SHORT).show();
                            loja(2);
                        } else if (event.getY() > h * 0.5 && event.getY() < h * 0.6) {
                           // Toast.makeText(context, "60", Toast.LENGTH_SHORT).show();
                            loja(3);
                        } else if (event.getY() > h * 0.6 && event.getY() < h * 0.7) {
                          //  Toast.makeText(context, "70", Toast.LENGTH_SHORT).show();
                            loja(4);
                        }
                    }

                    return true;

                }
//            else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//
//
//                int index1 = Math.round(pontoDoEixoYInicio);
//                int index2 = Math.round(event.getY());
//                int res = index1 - index2;
//                res = res < 0 ? -1 * res : res;
//                res *= 4;
//
//
//                for (int p = 1; p < res; p++) {
//                    moverListagemDefases(event);
//
//                }
//                return true;
//
//            }


            } else if (tut != null && fasecarregada == 1) {
                tut.onTouch(view, event);
            }

        }
        return true;

    }

}



