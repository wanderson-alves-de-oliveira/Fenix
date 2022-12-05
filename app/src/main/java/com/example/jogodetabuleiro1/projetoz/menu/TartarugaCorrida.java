package com.example.jogodetabuleiro1.projetoz.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jogodetabuleiro1.R;
import com.example.jogodetabuleiro1.projetoz.generico.memoria.Animaisx;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.ConvertBitimap;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.Cronograma;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.Esplosao;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.Objeto3d;
import com.example.jogodetabuleiro1.projetoz.generico.recursos.Vetor3;
import com.example.jogodetabuleiro1.projetoz.index.Tartaruga;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
public class TartarugaCorrida extends AppCompatActivity implements GLSurfaceView.Renderer, GLSurfaceView.OnTouchListener {
    private Objeto3d Fenix;
    private String alfabeto = "ABCDE";
    private Objeto3d barra;
    private Float giroyFenix = 0f;
    private Float giroyFenixInimigo = 0f;
    private int timeLine = 0;
    private int fase = 1;
    private int proximoIni = 0;
    private int recomecar = 0;
    private float localz = 62;
    private float localy = -17.00f;
    private float localx = 0;

    private int rotacinarFenix = 0;
    private float pontoDoEixoX = 0;
    private float pontoDoEixoY = 0;
    private float turbo = 0.1f;
    private boolean parado = false;
    private boolean vitoria = false;
    private boolean invulneravel = false;
    private final int QTD_DE_TIROS = 61;
    private int nivelTiro = 12;
    private int nivelTiroTempo = 3;
    private int nivelTiroIndex = 0;
    private int danoNoInimigo = 1;
    private float pontoDoEixoYInicio = 0;
    private float pontoDoEixoYFimm = 0;
    private float pontoDoEixoXInicio = 0;
    private float pontoDoEixoXFimm = 0;
    private boolean[] vaiPraCena = {false, false, false, false, false, false};
    private boolean[] ativarBoss = {false, false, false, false, false, false};

    private float giroy = 0;
    private float girox = 0;

    private boolean fogo = false;
    float veloz = 0f;
    float velox = 0f;
    float veloy = -0f;

    private Objeto3d mensagemBase;
    private GL10 gl2;

    private int xx;
    private int yy;
    private int xr;
    private int yr;

    private Objeto3d quadroInserirPalavra;
    private ArrayList<Objeto3d> inimigosC;
    private ArrayList<Objeto3d> inimigosB;
    private ArrayList<Objeto3d> inimigosX;
    private ArrayList<Objeto3d> inimigosE;
    private ArrayList<Objeto3d> boss;
    //private ArrayList<Objeto3d> sobrasObj;
    private ArrayList<Objeto3d> objCenario;
    private ArrayList<Esplosao> esplosaoArrayObj;

    private ArrayList<Objeto3d> asteroide;

    private ArrayList<Objeto3d> inimigosA;
    private ArrayList<Objeto3d> splosaoArrayNave;
    private ArrayList<Objeto3d> tiros;
    private Objeto3d ceu;
    private ArrayList<Cronograma> cronograma;

    private Objeto3d objeto3dxquadro;
    private int nivel = 1;
    private int tempoDeEspera = 1;
    private int tempoDisparo = 0;
    private int idDisparoQTD = 0;
    private int idDisparo = 0;
    private int idDisparo2 = 12;
    private int idDisparo3 = 24;
    private int idDisparo4 = 36;
    private int idDisparo5 = 48;

    private AssetManager asset;
    private long recordeVelhoD;
    private String arquivo = "FenixRun.txt";
    private File pegaArquivo;
    private long palavrasConquistadas = 0;
    private long palavrasConquistadasDeReset = 0;

    private String mensagem = "";
    private String tipo = "brinquedo";
    private final int variante = 6;
    private boolean venceu;
    private boolean perdeu = false;
    private boolean horaDoBoss = false;
    private boolean esplodirNave = false;
    private int timeEsplosaoNave = 0;
    private int esplosaoNaveId = 0;

    private boolean retornarQuadro = false;
    private int vida = 3;
    private int barraDeEnergia = 100;
    private MediaPlayer musica;
    private ArrayList<MediaPlayer> boom;
    private MediaPlayer disparo;
    private MediaPlayer victory;

    private boolean pause = true;
    private boolean inicio = true;
    private int modo = 0;
    private int modoE = 2;
    private int modoB = 0;
    private int modoX = 0;

    private int modoBoss = -1;

    private Animaisx animal;
    private MediaPlayer leaoxm;
    private float angle = 0;
    private int carga = -1;
    private Context context;
    private Bitmap bitmap;
    private int moduloR = 0;
    private int cont;
    private int time = 0;
    private int timeModoC = 0;
    private int timeModoE = 0;
    private int timeModoB = 0;
    private int timeModoX = 0;
    private boolean carregaModoX = true;
    private boolean carregaModoBoss = true;

    private int timeModoBoss = 0;

    private int quantidadeDeElementos = 3;
    private DisplayMetrics displayMetrics;
    private float h;
    private float w;
    private float[] rotatef = {90, 1f, 0, 0};

    private float wTela;
    private float dificuldade = 0.8f;

    private boolean carregado = false;
    private boolean colidiu = false;


    private int[] direcaoX = {-1, 2, 3, 3, 2, 0, 1};

    private int[] direcaoX2 = {0, 0, 0, 0, 2, 3, 3, 2};

//0=baixo
//1=cima
//2=direita
//3= esquerda
//4= baixo - direita
//5= baixo - esquerda
//6= cima - direita
//7=cima - esquerda
//8

    private int dr = 0;


    private final float velocidade = 0.012f;
    private final float velocidadeinimigo1 = 0.2f;
    private final float velocidadeTiro = 0.6f;
    private final float velocit = 0.6f;
    private boolean luping = false;
    private float escala = 0.2f;
    private final float DISTANCIA = 15f;
    private float moduloY = -15f;
    private float modulox = 0;
    private float moduloz = -35;
    private float acelerarando = 0;

    public Objeto3d getFenix() {
        return this.Fenix;
    }

    public void setFenix(Objeto3d Fenix) {
        this.Fenix = Fenix;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    int faseInit[] = {}; //Fully white

    private boolean x = false, y = false, z = false;
    float white[] = {0.3f, 0.3f, 0.3f, 1.0f}; //Fully white
    float green[] = {0.0f, 1.0f, 0.0f, 1.0f}; //Bright green
    float blue[] = {0.0f, 0.0f, 1.0f, 1.0f}; //Fully white
    float amarelo[] = {1.0f, 1.0f, 1.0f, 1.0f}; //Fully white
    float luzDifusa[] = {0.7f, 0.7f, 0.7f, 1.0f};//luz difusa
    private FloatBuffer corBufferG;

    public MediaPlayer getMusica() {
        return this.musica;
    }

    public void setMusica(MediaPlayer musica) {
        this.musica = musica;
    }

    public boolean isPause() {
        return this.pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

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

    public boolean isZ() {
        return this.z;
    }

    public void setZ(boolean z) {
        this.z = z;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TartarugaCorrida(Context context, AssetManager asset) throws IOException {

        //////////////INICIA VARIAVEIS BASICAS
        this.context = context;
        this.asset = asset;

        this.displayMetrics = context.getResources().getDisplayMetrics();
        this.h = this.displayMetrics.heightPixels;
        this.w = 720;
        this.wTela = this.displayMetrics.widthPixels;

        this.musica = MediaPlayer.create(context, R.raw.musics);
        this.musica.setLooping(true);
        this.musica.setVolume(0.4f, 0.4f);
        this.musica.start();
        this.barra = new Objeto3d(context,R.drawable.basenorm,asset, "fundob.obj",R.drawable.base, new Vetor3(1f, 1f, 1f), "");
        this.barra.setMudarTamanho(true);
        Bitmap seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.seta);
        seta = Bitmap.createScaledBitmap(seta, 122, 182, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mensagemBase = new Objeto3d(context,R.drawable.normal,asset, "quadroConta.obj",R.drawable.base, new Vetor3(0.5f, 0.4f, 1f), "");
        }
        this.mensagemBase.setMudarTamanho(true);
        mensagemBase.setPosition(new Vetor3(0, -1f, 1f));
        mensagemBase.setTransparente(true);

        animal = new Animaisx(0, 0, 0, 350, 320, 420, "leao", Fenix);
        /////////////////////////////////////////////////////
        faseInit = new int[]{0, 1, 2, 3, 4, 5, 2, 3, 4, 1, 5, 0, 2};
    }


    private void gravarRecorde() {
        ////////GRAVA PONTUAÇÃO APOSS PERDER UMA PARTIDA////////////////
        if (palavrasConquistadas > this.recordeVelhoD) {
            mensagem = "Novo Recorde";
            pegaArquivo = new File(context.getFilesDir(), arquivo);
            FileOutputStream outputStream;
            try {
                outputStream = context.openFileOutput(arquivo, Context.MODE_PRIVATE);
                outputStream.write(String.valueOf(palavrasConquistadas).toString().getBytes());
                outputStream.close();
                FileInputStream file = context.openFileInput(arquivo);
                int c;
                String temp = "";
                while ((c = file.read()) != -1) {
                    temp = temp + Character.toString((char) c);
                }
                this.recordeVelhoD = Long.valueOf(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ///////////////////////////////////////////////////////////
    }

    public void estatusDojogo(int v) throws IOException {
        ////MUDA O ESTATUS DO JOGO COMO INICIAL , FIM DE JOGO E REINICIAR
        if (v == 0) {
            this.nivel++;
            this.venceu = false;
            this.perdeu = false;


            if (this.vida <= 0) {
                this.vida = 3;

            }
        } else if (v == 1) {
            this.nivel = 1;
            this.inicio = false;
            this.perdeu = false;
            this.venceu = false;
            this.vida = 3;
            this.palavrasConquistadas = 0;
        } else if (v == 2) {
            if (nivel > 1) {
                this.venceu = false;
                this.perdeu = false;
            }
        }

        //////////////////////////////////////
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void carregar(String tipo) throws IOException {
        //////FAZ O CARREGAMENTO DOS ARQUIVOS 3D DO JOGO INTEIRO
        switch (carga) {
            case 0:

                Bitmap seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.setainvertida);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);
                mensagemBase.setPosition(new Vetor3(1, 1f, 1f));

             //   mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Até a letra certa da palavra", 40, 3));
             //   mensagemBase.loadGLTexture(true);
                ceu = new Objeto3d(context,R.drawable.nivelnorm,asset, "nivel.obj", R.drawable.nivel, new Vetor3(escala * 2, escala, escala), "");
              //  ceu.loadGLTexture(true);
                ceu.setPosition(new Vetor3(0, 14, -62));

                break;

            case 1:


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.nove);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);
                mensagemBase.setPosition(new Vetor3(1, -2f, 1f));

                mensagemBase.setTextura(R.drawable.esplosao);
               // mensagemBase.loadGLTexture(true);


                break;
            case 2:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.oito);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

             //   mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando..", 80, 3));
            //    mensagemBase.loadGLTexture(true);


                break;
            case 3:
                setFenix(new Objeto3d(context,R.drawable.naveanorm,asset, "navez.obj", R.drawable.navea, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), ""));
             //   Fenix.loadGLTexture(true);

                this.Fenix.setEstado("NBateu");
                Fenix.setPosition(new Vetor3(0, 16, -62));
                Fenix.setRefletir(false);
                Fenix.setRefletir(true);
                Fenix.setFenix(true);
                Fenix.setNomeRef("Fenix");
                splosaoArrayNave = splosao(Fenix);
                //this.Fenix.setGiroCentral(true);
                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.sete);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

            //    mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando...", 80, 3));
          //      mensagemBase.loadGLTexture(true);

                break;
            case 4:
                Bitmap quadroB = ConvertBitimap.getBitmap(context, R.drawable.sete, 0, 0, 0, false);

                quadroB = Bitmap.createScaledBitmap(quadroB, 400, 400, true);

                objeto3dxquadro = new Objeto3d(context,R.drawable.normal,asset, "figura.obj", R.drawable.navea, new Vetor3(escala * 35, escala * 35, escala * 35), "");


             //   objeto3dxquadro.loadGLTexture(true);

                objeto3dxquadro.setPosition(new Vetor3(0, 0f, 1f));
                objeto3dxquadro.setTransparente(true);

                objeto3dxquadro.setMudarTamanho(true);
                objeto3dxquadro.setGiro(-30);
                objeto3dxquadro.setGiroPosition(new Vetor3(1, 0f, 0f));


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.seis);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
                //mensagemBase.loadGLTexture(true);

                break;

            case 5:


                Bitmap menuB = ConvertBitimap.getBitmap(context, R.drawable.menu, 0, 0, 0, false);

                menuB = Bitmap.createScaledBitmap(menuB, 400, 400, true);

                Bitmap sin = ConvertBitimap.getBitmap(context, R.drawable.son, 0, 0, 0, false);
                Bitmap sout = ConvertBitimap.getBitmap(context, R.drawable.sonmudo, 0, 0, 0, false);

                List<Bitmap> imag = new ArrayList<>();
                imag.add(menuB);
                imag.add(sin);
                imag.add(sout);


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.cinco);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
              //  mensagemBase.loadGLTexture(true);
                break;
            case 6:
                inimigosA = new ArrayList<>();
                inimigosX = new ArrayList<>();
                inimigosC = new ArrayList<>();
                asteroide = new ArrayList<>();
                inimigosE = new ArrayList<>();
                inimigosB = new ArrayList<>();
                //  sobrasObj = new ArrayList<>();
                objCenario = new ArrayList<>();
                esplosaoArrayObj = new ArrayList<>();
                boss = new ArrayList<>();


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.quatro);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
            //    mensagemBase.loadGLTexture(true);
                break;

            case 7:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.tres);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
              //  mensagemBase.loadGLTexture(true);
                break;
            case 8:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.dois);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
            //    mensagemBase.loadGLTexture(true);
                break;
            case 9:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.um);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
              //  mensagemBase.loadGLTexture(true);
                break;

            case 10:
                float vidaX = 20f;
                float vidaA = 20f;
                float vidaAst = 40f;
                float vidaB = 20f;

                for (int p = 0; p < this.quantidadeDeElementos; p++) {
                    char v = alfabeto.charAt(p);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        inimigosB.add(new Objeto3d(context,R.drawable.inimigonorm,asset, "inimigo.obj", R.drawable.inimigo, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosB"));
                        inimigosA.add(new Objeto3d(context,R.drawable.basednorm,asset, "based.obj",R.drawable.based, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosA"));
                        inimigosX.add(new Objeto3d(context,R.drawable.inimigoxnorm,asset, "inimigox.obj", R.drawable.inimigox, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosX"));

                        asteroide.add(new Objeto3d(context,R.drawable.asteroidenorm,asset, "asteroid.obj", R.drawable.asteroide, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "asteroide"));

                        asteroide.get(p).setPosition(new Vetor3(0, DISTANCIA, -1 * (69 - p)));


                    }

                 //   inimigosX.get(p).loadGLTexture(true);
                    inimigosX.get(p).setValor(String.valueOf(v));


                    inimigosX.get(p).setVida(vidaX * dificuldade);
                    inimigosX.get(p).setRecoverVida(vidaX * dificuldade);
                    inimigosX.get(p).setId(p);

                    //   inimigosX.get(p).setSplosaoArrayNave(inimigosX.get(p).splosao(inimigosX.get(p), asset, context.getResources(), 1f));
                    inimigosX.get(p).setTiroNave(inimigosX.get(p).criarTiros(inimigosX.get(p),R.drawable.tiroxxnorm, 5, asset, "tiroc.obj", R.drawable.tiroxx, context.getResources()));
                    inimigosX.get(p).setRefletir(true);

                 //   asteroide.get(p).loadGLTexture(true);
                    asteroide.get(p).setValor(String.valueOf(v));
                    asteroide.get(p).setVida(vidaAst * dificuldade);
                    asteroide.get(p).setRecoverVida(vidaAst * dificuldade);

                    //esplosaoArrayObj.add(new Esplosao(asteroide.get(p), asset, context.getResources(), 1.3f,"asteroide", p));

                    //      asteroide.get(p).setSplosaoArrayNave(asteroide.get(p).splosao(asteroide.get(p), asset, context.getResources(), 1.3f));
                    asteroide.get(p).setRefletir(true);

                //    inimigosB.get(p).loadGLTexture(false);
                    inimigosB.get(p).setValor(String.valueOf(v));
                    inimigosB.get(p).setVida(vidaB * dificuldade);
                    inimigosB.get(p).setRecoverVida(vidaB * dificuldade);

                    // esplosaoArrayObj.add(new Esplosao(inimigosB.get(p), asset, context.getResources(), 0.5f,"inimigosB", p));

                    //     inimigosB.get(p).setSplosaoArrayNave(inimigosB.get(p).splosao(inimigosB.get(p), asset, context.getResources(), 0.5f));
                    inimigosB.get(p).setTiroNave(inimigosB.get(p).criarTiros(inimigosB.get(p),R.drawable.tirobnorm, 1, asset, "tiroc.obj", R.drawable.tirob, context.getResources()));
                    inimigosB.get(p).setRefletir(true);


                  //  inimigosA.get(p).loadGLTexture(true);

                    //  esplosaoArrayObj.add(new Esplosao(inimigosA.get(p), asset, context.getResources(), 0.8f,"inimigosA", p));

                    //   inimigosA.get(p).setSplosaoArrayNave(inimigosA.get(p).splosao(inimigosA.get(p), asset, context.getResources(), 0.8f));
                    inimigosA.get(p).setValor(String.valueOf(v));
                    inimigosA.get(p).setTiroNave(inimigosA.get(p).criarTiros(inimigosA.get(p),R.drawable.tiroanorm, 3, asset, "tiroc.obj", R.drawable.tiroa, context.getResources()));
                    inimigosA.get(p).setVida(vidaA * dificuldade);
                    inimigosA.get(p).setRecoverVida(vidaA * dificuldade);
                    inimigosA.get(p).setRefletir(true);
                }

                asteroide.get(0).setPosition(new Vetor3(-0.5f,
                        DISTANCIA, -68f));
                asteroide.get(1).setPosition(new Vetor3(0.0f,
                        DISTANCIA, -67));

                asteroide.get(2).setPosition(new Vetor3(0.5f,
                        DISTANCIA, -66));


                inimigosB.get(0).setPosition(new Vetor3(-0.3f,
                        DISTANCIA, -65));

                inimigosB.get(1).setPosition(new Vetor3(0.3f,
                        DISTANCIA, -65));

                inimigosB.get(2).setPosition(new Vetor3(0.0f,
                        DISTANCIA, -66));

//                inimigosB.get( 3 ).setPosition( new Vetor3( 0.5f,
//                        distancia, -64 ) );

                inimigosA.get(0).setPosition(new Vetor3(-0.2f,
                        DISTANCIA + 0.06f, -67));
                inimigosA.get(1).setPosition(new Vetor3(0.3f,
                        DISTANCIA + 0.06f, -67));
                inimigosA.get(2).setPosition(new Vetor3(0.0f,
                        DISTANCIA + 0.06f, -69));


                inimigosX.get(0).setPosition(new Vetor3(0.5f,
                        DISTANCIA + 0.03f, -65));

                inimigosX.get(1).setPosition(new Vetor3(0.0f,
                        DISTANCIA + 0.03f, -67));

                inimigosX.get(2).setPosition(new Vetor3(-0.5f,
                        DISTANCIA + 0.03f, -69));

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.zero);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
              //  mensagemBase.loadGLTexture(true);

//inimigo tipo c------------------------------------------------------------------
                float vidaCE = 10f;
                float vidaBos = 500f;
                for (int p = 0; p < 7; p++) {
                    // char v = alfabeto.charAt(p );
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        inimigosC.add(new Objeto3d(context,R.drawable.inimigocnorm,asset, "inimigoc.obj", R.drawable.inimigoc, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosC"));
                        inimigosE.add(new Objeto3d(context,R.drawable.inimigoenorm,asset, "inimigoc.obj", R.drawable.inimigoe, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosE"));

                    }
                 //   inimigosC.get(p).loadGLTexture(true);
                    inimigosC.get(p).setValor(String.valueOf("v"));
                    inimigosC.get(p).setVida(vidaCE * dificuldade);
                    inimigosC.get(p).setRecoverVida(vidaCE * dificuldade);

                    //  esplosaoArrayObj.add(new Esplosao(inimigosC.get(p), asset, context.getResources(), 0.3f,"inimigosC", p));

                    inimigosC.get(p).setTiroNave(inimigosC.get(p).criarTiros(inimigosC.get(p),R.drawable.tirocnorm, 1, asset, "tiroc.obj", R.drawable.tiroc, context.getResources()));
                    inimigosC.get(p).setTipo("C");
                    inimigosC.get(p).setPosition(new Vetor3(-1.5f, DISTANCIA, -63f));
                    inimigosC.get(p).setRefletir(true);
                    //    inimigosC.get( p ).vezes(0.3f);


                  //  inimigosE.get(p).loadGLTexture(true);
                    inimigosE.get(p).setValor(String.valueOf("v"));
                    inimigosE.get(p).setVida(vidaCE * dificuldade);
                    inimigosE.get(p).setRecoverVida(vidaCE * dificuldade);
                    //  inimigosE.get( p ).vezes(0.3f);


                    //     esplosaoArrayObj.add(new Esplosao(inimigosE.get(p), asset, context.getResources(), 0.3f,"inimigosE", p));

                    inimigosE.get(p).setTiroNave(inimigosE.get(p).criarTiros(inimigosE.get(p), R.drawable.tiroenorm,1, asset, "tiroc.obj", R.drawable.tiroe, context.getResources()));
                    inimigosE.get(p).setTipo("E");
                    inimigosE.get(p).setPosition(new Vetor3(-1.5f, DISTANCIA - 0.08f, -63f));
                    inimigosE.get(p).setRefletir(true);
                }


                boss.add(new Objeto3d(context,R.drawable.bossxanorm,asset, "bossxa.obj", R.drawable.bossxa, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "boss"));
               // boss.get(0).loadGLTexture(true);
                //    esplosaoArrayObj.add(new Esplosao(boss.get(0), asset, context.getResources(), 0.8f,"boss", 0));

                //   boss.get(0).setSplosaoArrayNave(boss.get(0).splosao(boss.get(0), asset, context.getResources(), 0.8f));
                boss.get(0).setValor(String.valueOf("A"));
                boss.get(0).setTiroNave(boss.get(0).criarTiros(boss.get(0),R.drawable.tirocnorm, 40, asset, "tiroc.obj", R.drawable.tiroc, context.getResources()));
                boss.get(0).setVida(vidaBos * dificuldade);
                boss.get(0).setRecoverVida(vidaBos * dificuldade);
                boss.get(0).setBoss(true);
                boss.get(0).setPosition(new Vetor3(0f,
                        DISTANCIA + 0.03f, -1000f));
                boss.get(0).setRefletir(true);


                objCenario.add(new Objeto3d(context,R.drawable.metalnorm,asset, "metal.obj",  R.drawable.metal, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
              //  objCenario.get(0).loadGLTexture(true);
                //    esplosaoArrayObj.add(new Esplosao(boss.get(0), asset, context.getResources(), 0.8f,"boss", 0));

                //   boss.get(0).setSplosaoArrayNave(boss.get(0).splosao(boss.get(0), asset, context.getResources(), 0.8f));
                objCenario.get(0).setValor(String.valueOf("A"));
                objCenario.get(0).setVida(150f * dificuldade);
                objCenario.get(0).setRecoverVida(150f * dificuldade);
                objCenario.get(0).setPosition(new Vetor3(0f,
                        14.3f, -62));
                objCenario.get(0).setTransparente(true);

                objCenario.get(0).setRefletir(true);
                //   objCenario.get(0).vezes(7);
                carregarCronologia(fase);
                break;
            case 11:
                int total = inimigosA.size();
                int po = 0;
                for (int i = 0; i < total; i++) {
                    esplosaoArrayObj.add(new Esplosao(context,inimigosA.get(po), asset, context.getResources(), 0.8f, "inimigosA", po));
                    esplosaoArrayObj.get(i).setId(po);
                    esplosaoArrayObj.get(i).setNome("inimigosA");

                    po++;
                }
                total += inimigosB.size();
                po = 0;
                for (int i = esplosaoArrayObj.size(); i < total; i++) {


                    esplosaoArrayObj.add(new Esplosao(context,inimigosB.get(po), asset, context.getResources(), 0.8f, "inimigosB", po));
                    po++;
                }

                total += inimigosC.size();
                po = 0;

                for (int i = esplosaoArrayObj.size(); i < total; i++) {
                    esplosaoArrayObj.add(new Esplosao(context,inimigosC.get(po), asset, context.getResources(), 0.5f, "inimigosC", po));

                    po++;
                }

                total += inimigosE.size();
                po = 0;
                for (int i = esplosaoArrayObj.size(); i < total; i++) {
                    esplosaoArrayObj.add(new Esplosao(context,inimigosE.get(po), asset, context.getResources(), 0.5f, "inimigosE", po));

                    po++;
                }

                total += inimigosX.size();
                po = 0;
                for (int i = esplosaoArrayObj.size(); i < total; i++) {


                    esplosaoArrayObj.add(new Esplosao(context,inimigosX.get(po), asset, context.getResources(), 0.8f, "inimigosX", po));

                    po++;
                }

                total += asteroide.size();
                po = 0;
                for (int i = esplosaoArrayObj.size(); i < total; i++) {
                    esplosaoArrayObj.add(new Esplosao(context,asteroide.get(po), asset, context.getResources(), 1f, "asteroide", po));

                    po++;
                }
                total += boss.size();
                po = 0;
                for (int i = esplosaoArrayObj.size(); i < total; i++) {
                    esplosaoArrayObj.add(new Esplosao(context,boss.get(po), asset, context.getResources(), 1.3f, "boss", po));

                    po++;
                }


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.zero);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
              //  mensagemBase.loadGLTexture(true);

                break;
            case 12:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.zero);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(R.drawable.esplosao);
               // mensagemBase.loadGLTexture(true);
                break;

            case 13:
                Bitmap imgq = ConvertBitimap.getBitmap(context, R.drawable.esplosao, 70, 140, 90, true);

                imgq = ConvertBitimap.getBitmapBarraxz(imgq, vida, "frase", (int) (this.wTela * 0.13f));

                quadroInserirPalavra = new Objeto3d(context,R.drawable.normal,asset, "quadroConta.obj",  R.drawable.metal, new Vetor3(escala * 3.6f, escala * 20.1f, escala * 5.5f), "");
              //  quadroInserirPalavra.loadGLTexture(true);
                quadroInserirPalavra.setPosition(new Vetor3(0f, 0f, 0f));
                quadroInserirPalavra.setTransparente(true);
                tiros = new ArrayList<>();

                for (int i = 0; i < QTD_DE_TIROS; i++) {


                    tiros.add(new Objeto3d(context,R.drawable.tironavenorm,asset, "tiroz.obj",R.drawable.tironave, new Vetor3(0.5f, 0.5f, 0.5f), ""));

                    tiros.get(i).vezes(1.8f);


                //    tiros.get(i).loadGLTexture(true);
                    //  tiros.get( i ).setMudarTamanho(true);
                    tiros.get(i).setMover("nulo");
                    //tiros.get(i ).setTransparente(true);
                }

                break;


        }


///////////////////////////////////////////////////////////////
        ///////MUDA A VISÃO DE MODO DE CARREGAMENTO PARA O INICIO DO JOGO
        if (tempoDeEspera <= 0) {
            carregado = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                configurar();
            }

/////////////////////////////////////////////////
        }


    }


    public void carregarCronologia(int fase) throws IOException {

        this.time = 0;
        this.timeModoC = 0;
        this.timeModoE = 0;
        this.timeModoB = 0;
        this.timeModoBoss = 0;
        this.timeModoX = 0;
        carregaModoX = true;
        carregaModoBoss = true;
        cronograma = new ArrayList<>();
        Cronograma c;
        switch (fase) {
            case 1:

                ceu = new Objeto3d(context,R.drawable.nivelnorm,asset, "nivel.obj", R.drawable.nivel, new Vetor3(escala * 2, escala, escala), "");
               // ceu.loadGLTexture(true);
                ceu.setPosition(new Vetor3(0, 14, -62));

                if (!this.musica.isPlaying()) {
                    this.musica.seekTo(0);
                    this.musica.start();
                }
                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(800);
                c.setTimeOUT(800);
                c.setTimeMode(1000);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1100);
                c.setTimeOUT(1400);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;


            case 2:


                ceu = new Objeto3d(context,R.drawable.nivelnorm,asset, "nivel.obj", R.drawable.nivel, new Vetor3(escala * 2, escala, escala), "");
               // ceu.loadGLTexture(true);
                ceu.setPosition(new Vetor3(0, 14, -62));

                this.musica.seekTo(0);
                this.musica.start();

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(300);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(4);
                c.setPerpetuo(false);
                c.setTimeIN(600);
                c.setTimeOUT(600);
                c.setTimeMode(800);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;
            case 3:

                ceu = new Objeto3d(context,R.drawable.nivelnorm,asset, "nivel.obj", R.drawable.nivel, new Vetor3(escala * 2, escala, escala), "");
               // ceu.loadGLTexture(true);
                ceu.setPosition(new Vetor3(0, 14, -62));

                this.musica.seekTo(0);
                this.musica.start();


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(300);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(1);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeMode(1000);
                c.setTimeOUT(1400);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(1400);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2500);
                c.setTimeMode(1000);
                c.setTimeOUT(2500);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(2600);
                c.setTimeOUT(2600);
                c.setTimeMode(1000);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(200);
                c.setTimeOUT(200);
                c.setTimeMode(1900);
                c.setModo(1);
                c.setAtivo(true);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(3500);
                c.setTimeOUT(25000);
                c.setTimeMode(100);
                c.setModo(0);
                c.setBoss(true);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(3000);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);
                break;
            case 4:

                ceu = new Objeto3d(context,R.drawable.nivelnorm,asset, "nivel.obj", R.drawable.nivel, new Vetor3(escala * 2, escala, escala), "");
              //  ceu.loadGLTexture(true);
                ceu.setPosition(new Vetor3(0, 14, -62));
                this.musica.seekTo(0);
                this.musica.start();


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(400);
                c.setTimeMode(1000);
                c.setTimeOUT(400);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(25000);
                c.setTimeMode(100);
                c.setModo(0);
                c.setBoss(true);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;


        }
        /////////////////////////////////////////////////////////////////////////////////


    }


    public ArrayList<Objeto3d> splosao(Objeto3d obj) throws IOException {
        ///CARREGA OS ARQUIS 3D DO ALFABETO
        ArrayList<Objeto3d> splosaoArray;

        splosaoArray = new ArrayList<>();
        float x = obj.getTamanho().getX();
        float y = obj.getTamanho().getY();
        float z = obj.getTamanho().getZ();
        splosaoArray.add(new Objeto3d(context,R.drawable.esplosaonorm,asset, "sp.obj", R.drawable.esplosao, new Vetor3(x, y, z), ""));
        splosaoArray.get(0).setTransparente(true);
    //    splosaoArray.get(0).loadGLTexture(true);

        splosaoArray.add(new Objeto3d(context,R.drawable.esplosaonorm,asset, "spa.obj", R.drawable.esplosao, new Vetor3(x, y, z), ""));
        splosaoArray.get(1).setTransparente(true);
     //   splosaoArray.get(1).loadGLTexture(true);

        splosaoArray.add(new Objeto3d(context,R.drawable.esplosaonorm,asset, "spb.obj",R.drawable.esplosao, new Vetor3(x, y, z), ""));
        splosaoArray.get(2).setTransparente(true);
       // splosaoArray.get(2).loadGLTexture(true);

        splosaoArray.add(new Objeto3d(context,R.drawable.esplosaonorm,asset, "spc.obj",R.drawable.esplosao, new Vetor3(x, y, z), ""));
        splosaoArray.get(3).setTransparente(true);
      //  splosaoArray.get(3).loadGLTexture(true);

        splosaoArray.add(new Objeto3d(context,R.drawable.esplosaonorm,asset, "spd.obj",R.drawable.esplosao, new Vetor3(x, y, z), ""));
        splosaoArray.get(4).setTransparente(true);
      //  splosaoArray.get(4).loadGLTexture(true);

        splosaoArray.add(new Objeto3d(context,R.drawable.esplosaonorm,asset, "spe.obj", R.drawable.esplosao, new Vetor3(x, y, z), ""));
        splosaoArray.get(5).setTransparente(true);
       // splosaoArray.get(5).loadGLTexture(true);

        splosaoArray.add(new Objeto3d(context,R.drawable.esplosaonorm,asset, "spf.obj", R.drawable.esplosao, new Vetor3(x, y, z), ""));
        splosaoArray.get(6).setTransparente(true);

      //  splosaoArray.get(6).loadGLTexture(true);
        for (int p = 0; p < splosaoArray.size(); p++) {
            splosaoArray.get(p).setTransparente(true);
            splosaoArray.get(p).setRefletir(true);
        }
        return splosaoArray;
    }
///////////////////////////////////////////////////////////


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void configurar() {
        //CONFIGURA AS VARIAVEIS PARA INICIAR O JOGO
        FileInputStream file = null;
        try {
            file = context.openFileInput(arquivo);
            int c;
            String temp = "";
            while ((c = file.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            this.recordeVelhoD = Long.valueOf(temp);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
            this.recordeVelhoD = 0;
        }
        this.palavrasConquistadas = 0;

        this.boom = new ArrayList<>();

        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));
        this.boom.add(MediaPlayer.create(context, R.raw.boom));

//        for(int i=0;i<boom.size();i++) {
//            this.boom.get(i).setVolume(0.4f, 0.4f);
//
//        }

        this.disparo = MediaPlayer.create(context, R.raw.tiro);
        this.victory = MediaPlayer.create(context, R.raw.victory);
        this.disparo.setVolume(0.4f, 0.4f);
        // this.musica.setVolume(0.2f, 0.2f);


/////////////////////////////////////////
    }

    private void son(int s) {
        ////////EXECUTA OS SONS DO JOGO
        if (perdeu == false) {
            switch (s) {
                case 1:
                    this.disparo.start();

                    if (this.disparo.isPlaying()) {
//                        this.disparo.seekTo(0);
//                        this.disparo.start();

                    } else {
                        this.disparo.start();
                    }
                    break;
                case 2:
                    for (int i = 0; i < boom.size(); i++) {
                        if (!boom.get(i).isPlaying() && i != boom.size() - 1) {
                            boom.get(i).start();
                            break;
                        } else {
                            if (!boom.get(i).isPlaying()) {
                                boom.get(i).start();
                                break;
                            } else {
                                boom.get(0).seekTo(0);
                                boom.get(0).start();
                                break;
                            }
                        }
                    }

                    break;

                case 3:
                    this.victory.start();

                    if (this.victory.isPlaying()) {
//                        this.disparo.seekTo(0);
//                        this.disparo.start();

                    } else {
                        this.victory.start();
                    }
                    break;

            }

        }
        //////////////////////////////////////////
    }


    private void sonDisparo() {
        ////////EXECUTA OS SONS DO JOGO
        if (perdeu == false) {

            this.disparo.start();

            if (this.disparo.isPlaying()) {
                this.disparo.seekTo(0);
                this.disparo.start();

            } else {
                this.disparo.start();
            }

        }
        //////////////////////////////////////////
    }


    public void parar() {
        // SAI DO JOGO PARA A TELA DE MENU
        this.musica.pause();
        this.musica.stop();
        Intent it = new Intent(context, Tartaruga.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        it.putExtra("SAIR", true);
        context.startActivity(it);
///////////////////////////////////////////

    }

    public Double calculoarDistancia(Objeto3d obj, Vetor3 v) {
        double d = Math.sqrt(Math.pow(obj.getPosition().x - (v.x), 2)
                + Math.pow(obj.getPosition().z - (v.z), 2)
                + Math.pow(obj.getPosition().y - (v.y), 2));
        return d;
    }

    public boolean calculoarColisaoInimigos(Objeto3d obj, Objeto3d obj2) {
        boolean vai = false;
        double d = Math.sqrt(Math.pow(obj.getPosition().x - (obj2.getPosition().x), 2)
                + Math.pow(obj.getPosition().z - (obj2.getPosition().z), 2)
                + Math.pow(obj.getPosition().y - (obj2.getPosition().y), 2));
        if (d <= 1.0f) {
            vai = true;
        }
        return vai;
    }

    public boolean calculoarColisaoI(Objeto3d obj, Objeto3d obj2, boolean danoTotal) {
        boolean vai = false;

        if (!obj.isEsplodiu()) {
            double d = Math.sqrt(Math.pow(obj.getPosition().x - (obj2.getPosition().x), 2)
                    + Math.pow(obj.getPosition().z - (obj2.getPosition().z), 2)
                    + Math.pow(obj.getPosition().y - (obj2.getPosition().y), 2));

            //   Fenix.setPosition( new Vetor3( 0, 18,-62 ));

            if (!obj2.getNomeRef().equals("") && !obj.getNomeRef().equals("") && !esplodirNave && !invulneravel) {
                if (d <= 0.2f) {
                    obj.setEsplodirNave(true);
                    obj2.setEsplodirNave(true);
                    obj.getPosition().z = -1000f;
                    obj.setAtivado(false);
                    obj.setAbatido(true);
                    esplodirNave = true;
                    vida--;
                    barraDeEnergia = 100;
                }
            } else if (obj2 == Fenix && !esplodirNave && !invulneravel) {
                if (d <= 0.2f) {
                    obj2.setImpacto(true);
                    obj.getPosition().z = -57;

                    if (obj.isDisparando() || obj.getNomeRef().equals(""))
                        barraDeEnergia -= 10;
                    if (barraDeEnergia <= 0) {
                        vida--;
                        barraDeEnergia = 100;
                        esplodirNave = true;
                    }


                }


            } else if (obj2.getPosition().z > -64.5f) {
                if (d <= 0.2f && fogo) {
                    for (Objeto3d p : tiros) {
                        if (obj2 == p) {
                            obj.setVida(obj.getVida() - danoNoInimigo);
                            // obj.vezes(0.1f);
                            obj.setImpacto(true);
                            if (obj.getVida() <= 0) {
                                obj.setEsplodirNave(true);
                                obj.setAtivado(false);
                                obj.setAbatido(true);

                            }

                            p.setPosition(new Vetor3(-1000, -100, -1002));
                            break;
                        }
                    }
                }
            }
        }
        return vai;
        /////////////////////////////////
    }

    private void esplodirNave(Objeto3d obj, Esplosao esplosao) {

        if (obj.isEsplodirNave() && !invulneravel) {


            if (obj.getTimeEsplosaoNave() < 26) {

                if (obj.getTimeEsplosaoNave() == 0) {
                    son(2);
                }

                if (obj.getEsplosaoNaveId() < esplosao.getSplosaoArrayNave().size())
                    esplosao.getSplosaoArrayNave().get(obj.getEsplosaoNaveId()).setPosition(new Vetor3(obj.getPosition().x, obj.getPosition().y, obj.getPosition().z));


                if (obj.getTimeEsplosaoNave() % 5 == 0 && obj.getEsplosaoNaveId() < esplosao.getSplosaoArrayNave().size()) {
                    obj.setEsplosaoNaveId(obj.getEsplosaoNaveId() + 1);
                }
                obj.setTimeEsplosaoNave(obj.getTimeEsplosaoNave() + 1);


            } else {

                obj.setEsplodirNave(false);
                obj.setTimeEsplosaoNave(0);
                obj.setEsplosaoNaveId(0);
                obj.getPosition().setZ(-1000);
                obj.getPosition().setX(obj.getPosition().x);
                obj.setVida(obj.getRecoverVida());
                obj.setGiro((float) (0));
                //   obj.setEsplodiu(true);

                obj.setGiroPosition(new Vetor3(0, 0, 0));


                alterarModoN(obj);
                for (int i = 0; i < tiros.size(); i++) {
                    tiros.get(i).setPosition(new Vetor3(-1000, obj.getPosition().y, -1000));
                }

                for (int i = 0; i < esplosao.getSplosaoArrayNave().size(); i++) {

                    esplosao.getSplosaoArrayNave().get(i).setPosition(new Vetor3(-1000, obj.getPosition().y, -1000));
                }

                if (obj.isBoss()) {
                    horaDoBoss = false;
                    ativarBoss[boss.indexOf(obj)] = false;
                    boss.get(boss.indexOf(obj)).setVida(boss.get(boss.indexOf(obj)).getRecoverVida());
                    boss.get(boss.indexOf(obj)).setEsplodirNave(false);
//                    timeLine = 0;
//                    try {
//                        carregarCronologia(fase);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                }

            }


        }

        if (obj.getPosition().z > -40) {
            obj.getPosition().setZ(-65);
            obj.getPosition().setX(obj.getPosition().x);
            obj.setVida(obj.getRecoverVida());
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void controle() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            timeLine();
        }
        for (int i = 0; i < inimigosC.size(); i++) {
            inidiceEsquadCE(inimigosC.get(i), modo, i);
            inidiceEsquadCE(inimigosE.get(i), modoE, i);
            moverInimigoC(modo, inimigosC.get(i).getTimeLineInidvidual(), i, inimigosC.get(i), 'C');
            moverInimigoC(modoE, inimigosE.get(i).getTimeLineInidvidual(), i, inimigosE.get(i), 'E');


        }
////////////MONITORA O JOGO REFERENTE A COLISÃO ,VITÓRIA E DERROTA
        if (esplodirNave) {
            if (timeEsplosaoNave < 26) {

                splosaoArrayNave.get(esplosaoNaveId).setPosition(new Vetor3(Fenix.getPosition().x, 15, Fenix.getPosition().z));
                if (timeEsplosaoNave % 5 == 0) {
                    esplosaoNaveId++;
                }
                timeEsplosaoNave++;
            } else {
                esplodirNave = false;
                timeEsplosaoNave = 0;
                esplosaoNaveId = 0;

                for (int i = 0; i < splosaoArrayNave.size(); i++) {
                    splosaoArrayNave.get(i).setPosition(new Vetor3(-100, -100, -100));
                }
                if (vida <= 0) {
                    gameOuver();
                }
            }
        }
//        switch (nivelTiro) {
//            case 48:
//                danoNoInimigo = 4;
//                break;
//            case 60:
//                danoNoInimigo = 6;
//                break;
//            default:
//                danoNoInimigo = 1;
//                break;
//
//        }


        if (true) {

            if (colidiu) {
                if (time > 50) {
                    veloy = (veloy) * -1;
                    velox = (velox) * -1;
                    veloz = (veloz) * -1;
                    time = 0;
                    colidiu = false;
                } else {
                    time++;
                }
            }


            //}
/// ALTERAR POSIÇÃO DA Fenix


            switch (rotacinarFenix) {
                case 1:
                    if (giroyFenix < 72) {
                        giroyFenix += 3.6f;

                    }
                    break;
                case 2:
                    if (giroyFenix > -72) {
                        giroyFenix -= 3.6f;

                    }
                    break;
                case 0:
                    if (giroyFenix > 1) {
                        giroyFenix -= 3.6f;
                    } else if (giroyFenix < -1) {
                        giroyFenix += 3.6f;

                    } else {
                        giroyFenix = 0f;

                    }
                    break;
            }

//
//            ceu.setGiro(ceu.getGiro() - 0.06f);
//            ceu.setGiroPosition(new Vetor3(ceu.getGiro(), 0, 0));

            moduloY -= veloy;
            modulox -= velox;
            moduloz += veloz;
            //  Fenix.giroTotal((GL11) gl2,girox,1,0,0);
            // Fenix.giroTotal((GL11) gl2,giroy,0,1,0);
            //  ceu.setPosition(new Vetor3(0 , 16, -62));


            Fenix.setGiroPosition(new Vetor3(0, 0, giroyFenix));


            for (int i = nivelTiroIndex; i < nivelTiro; i++) {
                if (tiros.get(i).getTime() >= 50 || tiros.get(i).getPosition().z < -64) {
                    tiros.get(i).setMover("nulo");
                    tiros.get(i).setTime(0);

                }
                if (tiros.get(i).getTime() == 1)
                    son(1);

                switch (tiros.get(i).getMover()) {
                    case "nulo":
                        //tiros.get(i).setGiroPosition(new Vetor3(0, 0,0));

                        switch (nivelTiro) {
                            case 12:
                                tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX(), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));

                                break;
                            case 24:
                                if (i < 12) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() - 0.05f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else {
                                    tiros.get(i).setPosition(new Vetor3((Fenix.getPosition().getX() + 0.05f), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                }
                                break;
                            case 36:
                                if (i < 12) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() - 0.1f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else if (i > 11 && i < 24) {
                                    tiros.get(i).setPosition(new Vetor3((Fenix.getPosition().getX() + 0.1f), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX(), Fenix.getPosition().getY(), (Fenix.getPosition().getZ()) - 0.1f));

                                }
                                break;

                            case 48:
                                if (i < 12) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() - 0.1f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else if (i > 11 && i < 24) {
                                    tiros.get(i).setPosition(new Vetor3((Fenix.getPosition().getX() + 0.1f), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else if (i > 23 && i < 36) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() - 0.05f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ()) - 0.1f));

                                } else {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() + 0.05f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ()) - 0.1f));

                                }
                                break;
                            case 60:
                                if (i < 12) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() - 0.1f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else if (i > 11 && i < 24) {
                                    tiros.get(i).setPosition(new Vetor3((Fenix.getPosition().getX() + 0.1f), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else if (i > 23 && i < 36) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() - 0.05f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ()) - 0.1f));

                                } else if (i > 35 && i < 48) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() + 0.0f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ()) - 0.1f));

                                } else {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() + 0.05f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ()) - 0.1f));

                                }
                                break;

                        }
                        break;
                    case "disparar":
                        // tiros.get(i).setGiroPosition(new Vetor3(0, 0,tiros.get(i).getPosition().z+0.2f));

                        float xxtiro = 0.0f;
                        if (nivelTiro == 36) {
                            if (i < 12) {
                                xxtiro = -0.005f;
                            }
                            if (i > 11 && i < 24) {
                                xxtiro = 0.005f;
                            }


                            if (i > 23 && i < 36) {
                                xxtiro = 0.0f;
                            }
                        }
                        if (nivelTiro > 36) {
                            if (i < 12) {
                                xxtiro = -0.01f;
                            }
                            if (i > 11 && i < 24) {
                                xxtiro = 0.01f;
                            }


                            if (i > 23 && i < 36) {
                                xxtiro = 0.0f;
                            }
                            if (i > 23 && i < 36) {
                                xxtiro = -0.005f;
                            }
                            if (i > 47) {
                                xxtiro = 0.005f;
                            }
                        }
                        tiros.get(i).setPosition(new Vetor3(tiros.get(i).getPosition().getX() + xxtiro,
                                tiros.get(i).getPosition().getY(),
                                tiros.get(i).getPosition().getZ() - (0.08f)));

                        tiros.get(i).setTime(tiros.get(i).getTime() + 1);

                        break;
                }


            }


            mensagemBase.setGiro(giroyFenix);
            mensagemBase.setGiroPosition(new Vetor3(0, 0, 1));
            mensagemBase.setPosition(new Vetor3(11, 30, Fenix.getPosition().getZ() - 10));


            //mmmmm
///////////////////////////////////////////////////////////////////////////////

            if (fogo) {
                atirar();
            }


        } else {
            //  this.pause = false;
            this.perdeu = true;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                objeto3dxquadro.setTextura(R.drawable.esplosao);
            }
          //  objeto3dxquadro.loadGLTexture(true);

        }
//////////////////////////////////////////
        if (retornarQuadro) {


            retornarQuadro = false;

        }


    }

    private void inidiceEsquadCE(Objeto3d objArray, int modo, int i) {
        if (objArray.getTimeLineInidvidual() == 0 || objArray.getPosition().z <= -1000f) {
            switch (modo) {
                case 0:
                    objArray.setPosition(new Vetor3(-1.5f, DISTANCIA, -63f));
                    objArray.setTipo("C");


                    break;
                case 1:
                    objArray.setPosition(new Vetor3(1.5f, DISTANCIA, -63f));
                    objArray.setTipo("D");


                    break;
                case 2:
                    objArray.setPosition(new Vetor3(1.5f, DISTANCIA, -63.6f));
                    objArray.setTipo("E");


                    break;

                case 3:
                    objArray.setPosition(new Vetor3(-1.5f, DISTANCIA, -63.6f));
                    objArray.setTipo("F");


                    break;
                case 4:
                    objArray.setTipo("G");
                    objArray.setGiroPosition(new Vetor3(0, 0, 0));
                    switch (i) {
                        case 0:
                            objArray.setPosition(new Vetor3(-0.3f, DISTANCIA, -65.8f));
                            break;
                        case 1:
                            objArray.setPosition(new Vetor3(0.3f, DISTANCIA, -65.8f));
                            break;
                        case 2:
                            objArray.setPosition(new Vetor3(-0.1f, DISTANCIA, -65.5f));
                            break;
                        case 3:
                            objArray.setPosition(new Vetor3(0.1f, DISTANCIA, -65.5f));
                            break;
                        case 4:
                            objArray.setPosition(new Vetor3(0.35f, DISTANCIA, -66.1f));
                            break;
                        case 5:
                            objArray.setPosition(new Vetor3(0.0f, DISTANCIA, -65.8f));
                            break;
                        case 6:
                            objArray.setPosition(new Vetor3(-0.35f, DISTANCIA, -66.1f));
                            break;
                    }
                    objArray.setVida(1f);
                    for (Objeto3d pp : objArray.getTiroNave()) {
                        objArray.setAtirando(false);
                        objArray.setAtirar(0, false);
                        pp.setPosition(new Vetor3(objArray.getPosition().x, DISTANCIA, objArray.getPosition().z));

                    }


                    break;
            }
        }


    }

    private void gameOuver() {
        parado = true;
        this.perdeu = true;
        localz = -1000f;
        vida = 3;
        barraDeEnergia = 100;
        try {
            carregarCronologia(fase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newLevel() {
        if (fase < 4) {
            fase++;
        } else {
            fase = 1;
        }
        parado = true;
        this.venceu = true;
        localz = -1000f;
        vida = 3;
        barraDeEnergia = 100;
        try {
            timeLine = 0;
            carregarCronologia(fase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void timeLine() {
        timeLine++;
//        if(timeLine%200==0){
//            if(nivelTiroTempo>3) {
//                nivelTiroTempo--;
//            }
//        }

        if (timeLine == 0) {
            nivelTiro = 12;
            alterarNivelDeTiro(12);
        } else if (timeLine == 400) {
            alterarNivelDeTiro(24);
        } else if (timeLine == 800) {
            alterarNivelDeTiro(36);
        } else if (timeLine == 1200) {
            alterarNivelDeTiro(48);
        } else if (timeLine == 1600) {
            alterarNivelDeTiro(60);
        }


        for (int i = 0; i < cronograma.size(); i++) {
            Cronograma c = cronograma.get(i);
            ArrayList<Objeto3d> objeto3ds = new ArrayList<>();
            String nome = "";
            boolean ativo = false;
            //   if (!c.isBoss()) {

            if (c.getTimeIN() <= timeLine /*&& c.getTimeOUT() > timeLine*/) {
                if (c.isFim() && cronograma.size() == 1) {

                    vitoria();
                } else if (c.isBoss() && cronograma.size() == 2) {

                    ativarBoss[c.getId()] = true;
                    horaDoBoss = true;
                }

                //           Log.e("TIME","TIME : "+timeLine);

                if (!c.isBoss()) {

                    switch (c.getId()) {
                        case 0:
                            objeto3ds = inimigosA;
                            nome = "inimigosA";
                            break;
                        case 1:
                            objeto3ds = inimigosB;
                            nome = "inimigosB";

                            break;
                        case 2:
                            objeto3ds = inimigosC;
                            nome = "inimigosC";

                            break;
                        case 3:
                            objeto3ds = inimigosX;
                            nome = "inimigosX";

                            c.setAtivo(false);
                            break;
                        case 4:
                            objeto3ds = asteroide;
                            nome = "asteroide";

                            break;
                        case 5:
                            objeto3ds = inimigosE;
                            nome = "inimigosE";

                            break;

                    }

                } else {
                    switch (c.getId()) {
                        case 0:
                            objeto3ds = boss;
                            nome = "boss";
                            break;
                    }


                }
                c.setEmMovimento(true);
                if (c.getId() > -1 && !c.isBoss()) {
                    vaiPraCena[c.getId()] = true;

                    if (c.getTimeIN() == timeLine) {
                        for (Objeto3d o : objeto3ds) {

                            o.setAtivado(true);
                            o.setAbatido(false);
                            o.setTimeLineInidvidual(0);
                        }
                    }
                    int eliminados = 0;
                    for (Objeto3d o : objeto3ds) {

                        if (!o.isAbatido()) {
                            o.setAtivado(true);

                        } else {
                            eliminados++;
                        }

                    }

                    if (eliminados == objeto3ds.size()) {

                        cronograma.remove(c);
                        if (cronograma.size() == 1)
                            cronograma.get(0).setTimeIN(timeLine + 100);

                    }
                }
                if (timeLine < c.getTimeIN() && c.isBoss() && cronograma.size() == 2) {

                    c.setTimeIN(timeLine);

                }

                if (timeLine % c.getTimeMode() == 0 && c.isPerpetuo()) {
                    switch (c.getId()) {
                        case 2:

                            modo = c.getModo();
                            int m = c.getModo() + 1;
                            String letra = "C";
                            if (m < 5) {
                                switch (m) {
                                    case 0:
                                        letra = "C";
                                        break;
                                    case 1:
                                        letra = "D";
                                        break;
                                    case 2:
                                        letra = "E";
                                        break;
                                    case 3:
                                        letra = "F";
                                        break;
                                    case 4:
                                        letra = "G";
                                        break;

                                }
                                //  cronograma.get(i).setModo(m);
                                for (Objeto3d o : objeto3ds) {
                                    if (o.getTipo().equals("N"))
                                        o.setTipo(letra);
                                }
                            } else {
                                // cronograma.get(i).setModo(0);
                                for (Objeto3d o : objeto3ds) {
                                    o.setTipo("C");
                                }

                            }


                            break;
                        case 5:
                            modoE = c.getModo();
                            int me = c.getModo() + 1;

                            String letrae = "C";
                            if (me < 5) {
                                switch (me) {
                                    case 0:
                                        letrae = "C";
                                        break;
                                    case 1:
                                        letrae = "D";
                                        break;
                                    case 2:
                                        letrae = "E";
                                        break;
                                    case 3:
                                        letrae = "F";
                                        break;
                                    case 4:
                                        letrae = "G";
                                        break;

                                }
                                //  cronograma.get(i).setModo(m);
                                for (Objeto3d o : objeto3ds) {
                                    if (o.getTipo().equals("N"))
                                        o.setTipo(letrae);
                                }
                            } else {
                                // cronograma.get(i).setModo(0);
                                for (Objeto3d o : objeto3ds) {
                                    o.setTipo("C");
                                }

                            }

                            break;
                    }
                }

                if (!c.isPerpetuo()) {

                    switch (c.getId()) {
                        case 1:
                            modoB = c.getModo();
                            break;
                        case 2:
                            modo = c.getModo();
                            break;
                        case 3:
                            modoX = c.getModo();
                            break;
                        case 5:
                            modoE = c.getModo();
                            break;
                    }
                }


            } else if (c.isEmMovimento()) {

                switch (c.getId()) {
                    case 0:
                        objeto3ds = inimigosA;
                        nome = "inimigosA";
                        break;
                    case 1:
                        objeto3ds = inimigosB;
                        nome = "inimigosB";

                        break;
                    case 2:
                        objeto3ds = inimigosC;
                        nome = "inimigosC";

                        break;
                    case 3:
                        objeto3ds = inimigosX;
                        nome = "inimigosX";

                        c.setAtivo(false);
                        break;
                    case 4:
                        objeto3ds = asteroide;
                        nome = "asteroide";

                        break;
                    case 5:
                        objeto3ds = inimigosE;
                        nome = "inimigosE";

                        break;

                }
                if (c.getId() > -1 && !c.isBoss()) {
                    vaiPraCena[c.getId()] = false;
                }
//                for (Objeto3d o : objeto3ds) {
//                    o.setEsplodirNave(true);
//                    o.setVida(0);
//                    o.setTimeEsplosaoNave(0);
//
//                    String finalNome = nome;
//                    Esplosao esp = filtro(finalNome, objeto3ds.indexOf(o));
//                    esplodirNave(o, esp);
//
//                }
                c.setEmMovimento(false);
                if (c.getId() > -1 && c.getTimeOUT() <= timeLine) {

//                    for (Objeto3d o : objeto3ds) {
//                        if (o.getPosition().z > -65.02f) {
//                            sobrasObj.add(o);
//                        }
//                    }

                    cronograma.remove(c);
                    cronograma.get(0).setTimeIN(timeLine);
                }
                break;
            }


        }


        for (int p = 0; p < inimigosC.size(); p++) {

            if (inimigosC.get(p).getVida() <= 0) {

                Esplosao esp = filtro("inimigosC", p);

                esplodirNave(inimigosC.get(p), esp);
            }

            if (inimigosE.get(p).getVida() <= 0) {
                //       esplodirNave(inimigosE.get(p));

                String finalNome = "inimigosE";
                Esplosao esp = filtro(finalNome, p);
                esplodirNave(inimigosE.get(p), esp);
            }
            if (p < inimigosA.size()) {

                if (inimigosA.get(p).getVida() <= 0) {
                    //         esplodirNave(inimigosA.get(p));
                    String finalNome = "inimigosA";
                    Esplosao esp = filtro(finalNome, p);
                    esplodirNave(inimigosA.get(p), esp);
                }

                if (inimigosX.get(p).getVida() <= 0) {
                    //         esplodirNave(inimigosX.get(p));
                    String finalNome = "inimigosX";
                    Esplosao esp = filtro(finalNome, p);
                    esplodirNave(inimigosX.get(p), esp);
                }

                if (inimigosB.get(p).getVida() <= 0) {
                    //         esplodirNave(inimigosB.get(p));
                    String finalNome = "inimigosB";
                    Esplosao esp = filtro(finalNome, p);
                    esplodirNave(inimigosB.get(p), esp);
                }

                if (asteroide.get(p).getVida() <= 0) {
                    //         esplodirNave(asteroide.get(p));
                    String finalNome = "asteroide";
                    Esplosao esp = filtro(finalNome, p);
                    esplodirNave(asteroide.get(p), esp);
                }
                calculoarColisaoI(inimigosB.get(p), Fenix, true);
                calculoarColisaoI(inimigosA.get(p), Fenix, true);
                calculoarColisaoI(inimigosX.get(p), Fenix, true);
                calculoarColisaoI(asteroide.get(p), Fenix, true);


                for (Objeto3d pp : tiros) {
                    calculoarColisaoI(inimigosA.get(p), pp, false);
                    calculoarColisaoI(inimigosB.get(p), pp, false);
                    calculoarColisaoI(inimigosX.get(p), pp, false);
                    calculoarColisaoI(asteroide.get(p), pp, false);

                }
                for (Objeto3d pp : inimigosB.get(p).getTiroNave()) {
                    calculoarColisaoI(pp, Fenix, false);
                }
                for (Objeto3d pp : inimigosX.get(p).getTiroNave()) {
                    calculoarColisaoI(pp, Fenix, false);
                }

                Float positionx = inimigosB.get(p).getPosition().getX();
                Float giroybolhas = inimigosB.get(p).getGiro();

                if (inimigosB.get(p).getPosition().getZ() < Fenix.getPosition().getZ()) {

                    if (positionx < Fenix.getPosition().getX()
                    ) {
                        positionx += (velocidadeinimigo1 * 0.6f);
                        if (giroybolhas < 30) {
                            giroybolhas += 3.0f;
                        }
                    } else if (positionx > Fenix.getPosition().getX() + Fenix.getTamanho().getX()
                    ) {
                        positionx -= (velocidadeinimigo1 * 0.6f);
                        if (giroybolhas > -30) {
                            giroybolhas -= 3.0f;
                        }
                    } else {
                        if (giroybolhas > 0) {
                            giroybolhas -= 3.0f;
                        } else {
                            giroybolhas += 3.0f;

                        }


                    }
                } else {
                    if (giroybolhas > 0) {
                        giroybolhas -= 3.0f;
                    } else {
                        giroybolhas += 3.0f;

                    }


                }
                moverInimigoX(modoX, timeModoX, giroybolhas, p);
                moverInimigoB(modoB, timeModoB, giroybolhas, p);
                moverInimigoA(0, 0, giroybolhas, p);
                moverAsteroide(0, 0, giroybolhas, p);
             //   moverObjCenario(objCenario.get(0), p);


            }

        }

        if (horaDoBoss) {

            if (!boss.get(0).isAbatido()) {
                boss.get(0).setAtivado(true);
            }


            for (int p = 0; p < inimigosC.size(); p++) {
                if (p < inimigosA.size()) {
                    moverInimigoX(modoX, timeModoX, 0, p);
                    moverInimigoB(modoB, timeModoB, 0, p);
                    moverInimigoA(0, 0, 0, p);
                    moverAsteroide(0, 0, 0, p);
               //     moverObjCenario(objCenario.get(0), p);

                }
            }
            if (boss.get(0).getVida() <= 0) {

                String finalNome = "boss";
                Esplosao esp = filtro(finalNome, 0);
                esplodirNave(boss.get(0), esp);
                for (Objeto3d pp : boss.get(0).getTiroNave()) {
                    pp.getPosition().z = -1000f;
                }
                for (Cronograma c : cronograma) {
                    if (c.isBoss()) {
                        cronograma.remove(c);
                        cronograma.get(0).setTimeIN(timeLine + 100);
                        break;
                    }
                }


            }

            for (Objeto3d pp : tiros) {
                calculoarColisaoI(boss.get(0), pp, false);
            }

            moverBoss(modoBoss, timeModoBoss, 0, boss.get(0), 0);


        }
        moverFase();
    }

    private void vitoria() {

        vitoria = true;
        invulneravel = true;
        if (!this.victory.isPlaying()) {
            this.victory.start();
            this.musica.pause();
            this.musica.seekTo(0);
        }
        acelerarando += 0.002f;
        Fenix.getPosition().z -= acelerarando;
        Fenix.setAtirando(false);
        if (Fenix.getPosition().z <= -65f) {
            vitoria = false;
            acelerarando = 0;
            timeLine = 0;
            Fenix.getPosition().z = -62f;
            restart();
            newLevel();

        }


    }

    private void alterarNivelDeTiro(int nivel) {

        nivelTiro = nivel;
        for (Objeto3d o : tiros) {
            o.setGiroPosition(new Vetor3(0, 0, 0));

        }

    }

//    private void moverObjSobra(Objeto3d pp, int p) {
//
//        if (pp.getPosition().z > -57) {
//            sobrasObj.remove(pp);
//
//        } else {
//            pp.getPosition().z += velocidade;
//
//        }
//
//
//    }

    private void moverObjCenario(Objeto3d pp, int p) {

        if (pp.getPosition().z < -57) {
            pp.setPosition(new Vetor3(pp.getPosition().x, pp.getPosition().getY(), pp.getPosition().z + (velocidade / 2)  ));

        } else {
            pp.getPosition().setZ(-68);

        }

        girarOBJ(pp, 1000, 'y', velocidade);

    }

    private void limpar(ArrayList<Objeto3d> A, String finalNome) {

        for (Objeto3d o : A) {
            o.setVida(-100);
            o.setEsplodirNave(true);
            Esplosao esp = filtro(finalNome, A.indexOf(o));
            esplodirNave(o, esp);
        }

    }

    private void moverBoss(int modoBoss, int time, int i, Objeto3d pp, int index) {

        if (pp.isAtivado()) {


            if (pp.getPosition().z < -65f) {
                pp.setPosition(new Vetor3(0f,
                        DISTANCIA + 0.03f, -65f));
                pp.getPosition().setX(0);

            }

            if (pp.getPosition().z < -63.5f) {
                pp.getPosition().setZ(pp.getPosition().z + velocidade);
            } else {
                carregaModoBoss = false;

            }


            if (!carregaModoBoss) {


                if (time >= 100) {

                    timeModoBoss = 0;
                    if (this.dr < direcaoX.length) {
                        this.modoBoss = direcaoX[this.dr];
                        this.dr++;
                    } else {
                        this.dr = 0;
                        this.modoBoss = direcaoX[this.dr];

                    }
                    return;

                }

                pp.setGiro(0);
                for (Objeto3d ppp : pp.getTiroNave()) {
                    calculoarColisaoI(ppp, Fenix, false);
                }
                if (true) {
                    pp.setDisparando(true);
                    moverObjFixoPadrao(pp, modoBoss, 'z', 1f, 90);

                }
                timeModoBoss++;
            }
        }
        disparandoOBJ(pp, 20);

    }


    private void moverObjFixoPadrao(Objeto3d pp, int modo, char dir, float velocidadeG, int limit) {

        switch (modo) {
            case 0:
                pp.setPosition(new Vetor3(pp.getPosition().x, pp.getPosition().getY(), pp.getPosition().z + velocidade));
                girarOBJ(pp, (limit) * -1, dir, velocidadeG);

                break;
            case 1:
                pp.setPosition(new Vetor3(pp.getPosition().x, pp.getPosition().getY(), pp.getPosition().z - velocidade));
                girarOBJ(pp, (limit) * -1, dir, velocidadeG);

                break;
            case 2:
                pp.setPosition(new Vetor3(pp.getPosition().x + velocidade, pp.getPosition().getY(), pp.getPosition().z));
                girarOBJ(pp, (limit) * -1, dir, velocidadeG);
                break;
            case 3:
                pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z));
                girarOBJ(pp, limit, dir, velocidadeG);

                break;
            case 4:
                pp.setPosition(new Vetor3(pp.getPosition().x + velocidade, pp.getPosition().getY(), pp.getPosition().z + velocidade));
                girarOBJ(pp, (limit) * -1, dir, velocidadeG);

                break;
            case 5:
                pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z + velocidade));
                girarOBJ(pp, limit, dir, velocidadeG);

                break;
            case 6:
                pp.setPosition(new Vetor3(pp.getPosition().x + velocidade, pp.getPosition().getY(), pp.getPosition().z - velocidade));
                girarOBJ(pp, (limit) * -1, dir, velocidadeG);

                break;
            case 7:
                pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z - velocidade));
                girarOBJ(pp, limit, dir, velocidadeG);

                break;
        }

    }


    public void moverInimigoA(int modoB, int timeB, float giroybolhas, int p) {

        if (inimigosA.get(p).isAtivado()) {
            inimigosA.get(p).setGiro(giroybolhas);
            inimigosA.get(p).setPosition(new Vetor3(inimigosA.get(p).getPosition().x, inimigosA.get(p).getPosition().getY(), inimigosA.get(p).getPosition().z + velocidade));
            for (Objeto3d pp : inimigosA.get(p).getTiroNave()) {
                calculoarColisaoI(pp, Fenix, false);
            }
            if (inimigosA.get(p).getPosition().z > -58) {
                for (Objeto3d pp : inimigosA.get(p).getTiroNave()) {
                    pp.setPosition(new Vetor3(inimigosA.get(p).getPosition().x, DISTANCIA + 0.06f, inimigosA.get(p).getPosition().z));
                }

                inimigosA.get(p).setPosition(new Vetor3(-0.3f,
                        DISTANCIA, -1000));
                inimigosA.get(p).setAtirando(false);
                inimigosA.get(p).setAtirar(0, false);
                inimigosA.get(p).setAbatido(true);
                inimigosA.get(p).setAtivado(false);
                inimigosA.get(p).setEsplodiu(false);
            } else {
                if (inimigosA.get(p).getPosition().z < -59f) {
                    inimigosA.get(p).setDisparando(true);
                }
            }
            if (inimigosA.get(p).getPosition().z > -58) {
                inimigosA.get(0).setPosition(new Vetor3(-0.2f,
                        DISTANCIA + 0.06f, -67));
                inimigosA.get(1).setPosition(new Vetor3(0.3f,
                        DISTANCIA + 0.06f, -67));
                inimigosA.get(2).setPosition(new Vetor3(0.0f,
                        DISTANCIA + 0.06f, -69));

                inimigosA.get(0).setEsplodiu(false);
                inimigosA.get(1).setEsplodiu(false);
                inimigosA.get(2).setEsplodiu(false);

                proximoInimigo(0);
            }

        }

        disparandoOBJ(inimigosA.get(p), 30);
    }

    private void proximoInimigo(int i) {

        vaiPraCena[i] = false;
//        vaiPraCena[faseInit[proximoIni]]=true;
//        if(proximoIni<faseInit.length)
//               proximoIni++;
    }


    private void girarOBJ(Objeto3d obj, int giro, char dir, float velocidadeG) {
        if (giro != 1000 && giro != -1000) {

            if (giro > 0 && obj.getGiro() <= giro) {
                obj.setGiro(obj.getGiro() + velocidadeG);
            } else if (giro < 0 && obj.getGiro() >= giro) {
                obj.setGiro(obj.getGiro() - velocidadeG);

            }
        } else {
            if (giro > 0) {
                obj.setGiro(obj.getGiro() + velocidadeG);
            } else if (giro < 0) {
                obj.setGiro(obj.getGiro() - velocidadeG);

            }
        }

        switch (dir) {
            case 'z':
                obj.setGiroPosition(new Vetor3(0, 0, obj.getGiro()));
                break;
            case 'x':
                obj.setGiroPosition(new Vetor3(obj.getGiro(), 0, 0));
                break;
            case 'y':
                obj.setGiroPosition(new Vetor3(0, obj.getGiro(), 0));
                break;

        }


    }


    public void moverInimigoB(int modo, int time, float giroybolhas, int p) {
        if (inimigosB.get(p).isAtivado()) {


            if (time >= 2000) {

                timeModoB = 0;


                time = 0;
                proximoInimigo(1);
                return;

            }

            if (time == 0) {
                switch (modo) {
                    case 0:
                        inimigosB.get(0).setPosition(new Vetor3(-0.3f,
                                DISTANCIA, -65));

                        inimigosB.get(1).setPosition(new Vetor3(0.3f,
                                DISTANCIA, -65));

                        inimigosB.get(2).setPosition(new Vetor3(0.0f,
                                DISTANCIA, -66));
                        for (int i = 0; i < inimigosB.size(); i++) {
                            inimigosB.get(i).setTipo("B0");
                            inimigosB.get(i).setGiro(0);
                            inimigosB.get(i).setGiroPosition(new Vetor3(0, inimigosB.get(i).getGiro(), 0));

                        }
                        break;
                    case 1:
                        inimigosB.get(0).setPosition(new Vetor3(-1f,
                                DISTANCIA, -62.5f));

                        inimigosB.get(1).setPosition(new Vetor3(-1.5f,
                                DISTANCIA, -62.5f));

                        inimigosB.get(2).setPosition(new Vetor3(-2f,
                                DISTANCIA, -62.5f));

                        for (int i = 0; i < inimigosB.size(); i++) {
                            inimigosB.get(i).setTipo("B1");
                            inimigosB.get(i).setGiro(90f);
                            inimigosB.get(i).setGiroPosition(new Vetor3(0, inimigosB.get(i).getGiro(), 0));

                        }
                        break;
                    case 2:
                        inimigosB.get(0).setPosition(new Vetor3(1f,
                                DISTANCIA, -62.5f));

                        inimigosB.get(1).setPosition(new Vetor3(1.5f,
                                DISTANCIA, -62.5f));

                        inimigosB.get(2).setPosition(new Vetor3(2f,
                                DISTANCIA, -62.5f));

                        for (int i = 0; i < inimigosB.size(); i++) {
                            inimigosB.get(i).setTipo("B1");
                            inimigosB.get(i).setGiro(270f);
                            inimigosB.get(i).setGiroPosition(new Vetor3(0, inimigosB.get(i).getGiro(), 0));

                        }
                        break;
                }

            }
            switch (modo) {
                case 0:
                    inimigosB.get(p).setGiro(giroybolhas);
                    inimigosB.get(p).setPosition(new Vetor3(inimigosB.get(p).getPosition().x, inimigosB.get(p).getPosition().getY(), inimigosB.get(p).getPosition().z + velocidade));
                    if (inimigosB.get(p).getPosition().z < -59f) {

                        inimigosB.get(p).setDisparando(true);
                    }
                    if (inimigosB.get(p).getPosition().z > -59) {
                        inimigosB.get(p).setPosition(new Vetor3(-0.3f,
                                DISTANCIA, -1000));

                        inimigosB.get(p).setAbatido(true);
                        inimigosB.get(p).setAtivado(false);
                        inimigosB.get(p).setEsplodiu(false);


                    }
                    break;
                case 1:
                    inimigosB.get(p).setPosition(new Vetor3(inimigosB.get(p).getPosition().x + velocidade, inimigosB.get(p).getPosition().getY(), inimigosB.get(p).getPosition().z));
                    if (inimigosB.get(p).getPosition().z < -59f) {

                        inimigosB.get(p).setDisparando(true);
                    }
                    if ((inimigosB.get(p).getPosition().z > -59 || inimigosB.get(p).getPosition().x > 1)) {

                        inimigosB.get(p).setPosition(new Vetor3(-0.3f,
                                DISTANCIA, -1000));

                        inimigosB.get(p).setAbatido(true);
                        inimigosB.get(p).setAtivado(false);
                        inimigosB.get(p).setEsplodiu(false);
                    }
                    break;
                case 2:
                    inimigosB.get(p).setPosition(new Vetor3(inimigosB.get(p).getPosition().x - velocidade, inimigosB.get(p).getPosition().getY(), inimigosB.get(p).getPosition().z));

                    if (inimigosB.get(p).getPosition().z < -59f) {
                        inimigosB.get(p).setDisparando(true);
                    }
                    if ((inimigosB.get(p).getPosition().z > -59 || inimigosB.get(p).getPosition().x < -1)) {

                        inimigosB.get(p).setPosition(new Vetor3(-0.3f,
                                DISTANCIA, -1000));

                        inimigosB.get(p).setAbatido(true);
                        inimigosB.get(p).setAtivado(false);
                        inimigosB.get(p).setEsplodiu(false);

                    }
                    break;
            }


            timeModoB++;

        }

        disparandoOBJ(inimigosB.get(p), 30);

    }

    public void moverFase() {
        if (timeLine > 0 && !vitoria && !perdeu && !horaDoBoss) {
            ceu.setPosition(new Vetor3(0, 14, ceu.getPosition().z = ceu.getPosition().z + (velocidade / 2)));



            if (objCenario.get(0).getPosition().z < -57) {
                objCenario.get(0).setPosition(new Vetor3(objCenario.get(0).getPosition().x, objCenario.get(0).getPosition().getY(), objCenario.get(0).getPosition().z + (velocidade / 2)  ));

            } else {
                objCenario.get(0).getPosition().setZ(-68);

            }



        }




    }

    public void moverInimigoX(int modo, int time, float giroybolhas, int p) {
        if (inimigosX.get(p).isAtivado()) {
            if (inimigosX.get(p).isCarregamentoIndividual()) {
                inimigosX.get(p).setCarregamentoIndividual(false);
                float[] vz = {- -65, -65.5f, -66, -66.5f};

                switch (modo) {

                    case 0:


                        inimigosX.get(p).setPosition(new Vetor3(0.5f,
                                DISTANCIA + 0.03f, vz[(int) (Math.random() * 4)]));


                    case 1:


                        inimigosX.get(p).setPosition(new Vetor3(0.0f,
                                DISTANCIA + 0.03f, vz[(int) (Math.random() * 4)]));


                        break;
                }

            }

            if (inimigosX.get(p).getPosition().z < -59f) {
                inimigosX.get(p).setDisparando(true);
            } else {
                for (Objeto3d o : inimigosX.get(p).getTiroNave()) {
                    o.getPosition().z = -100;
                }
            }

            switch (modo) {

                case 0:
                    inimigosX.get(p).setGiro(giroybolhas);
                    inimigosX.get(p).setPosition(new Vetor3(inimigosX.get(p).getPosition().x, inimigosX.get(p).getPosition().getY(), inimigosX.get(p).getPosition().z + (velocidade * 0.65f)));

                    if (inimigosX.get(p).getPosition().z < -59f) {
                        inimigosX.get(p).setDisparando(true);
                    } else {

                        inimigosX.get(p).setAbatido(true);
                        inimigosX.get(p).setAtivado(false);
                    }
                    break;

                case 1:
                    if (time >= 100) {

                        timeModoX = 0;
                        if (inimigosX.get(p).getDr() < direcaoX2.length - 1) {
                            inimigosX.get(p).setDr(inimigosX.get(p).getDr() + 1);
                        } else {
                            inimigosX.get(p).setDr(0);

                        }
                        return;

                    }

                    if (inimigosX.get(p).getPosition().z <= -900) {
                        inimigosX.get(p).setCarregamentoIndividual(true);
                    }
                    timeModoX++;
                    inimigosX.get(p).setDisparando(true);
                    break;


            }

            if (inimigosX.get(p).getPosition().z > -59) {

                inimigosX.get(p).setPosition(new Vetor3(inimigosX.get(p).getPosition().x,
                        DISTANCIA + 0.03f, -65));
                inimigosX.get(p).setEsplodiu(false);


                inimigosX.get(p).setAbatido(true);
                inimigosX.get(p).setAtivado(false);


            }
        }


        if (inimigosX.get(p).isDisparando()) {
            moverObjFixoPadrao(inimigosX.get(p), direcaoX2[inimigosX.get(p).getDr()], 'y', 15f, 1000);
        }

        disparandoOBJ(inimigosX.get(p), 30);
    }

    private void disparandoOBJ(Objeto3d ob, int tempoDisparo) {

        if (ob.getTiroNave().get(ob.getIdTiroAux()).isDisparando()) {
            ob.atirando(Fenix, velocidade * 2, true, tempoDisparo);

        }


    }

    public void moverAsteroide(int modoB, int timeB, float giroybolhas, int p) {
        if (asteroide.get(p).isAtivado()) {
            asteroide.get(p).setGiro(giroybolhas);

            if (asteroide.get(0).getPosition().z < -68f) {
                asteroide.get(0).setPosition(new Vetor3(-0.5f,
                        DISTANCIA, -68f));
            }
            if (asteroide.get(1).getPosition().z < -67f) {
                asteroide.get(1).setPosition(new Vetor3(0.0f,
                        DISTANCIA, -67));
            }
            if (asteroide.get(2).getPosition().z < -66f) {
                asteroide.get(2).setPosition(new Vetor3(0.5f,
                        DISTANCIA, -66));
            }


            asteroide.get(p).setPosition(new Vetor3(asteroide.get(p).getPosition().x, asteroide.get(p).getPosition().getY(), asteroide.get(p).getPosition().z + velocidade));

            if (asteroide.get(p).getPosition().z > -59) {
                float[] vz = {- -65, -65.5f, -66};


                asteroide.get(p).setAbatido(true);
                asteroide.get(p).setAtivado(false);

                asteroide.get(p).setEsplodiu(false);

                asteroide.get(p).setPosition(new Vetor3(-0.5f,
                        DISTANCIA, vz[(int) (Math.random() * 3)]));

            }
        }
    }

    public void moverInimigoC(int modo, int time, int i, Objeto3d obj, char tipo) {
        if (obj.isAtivado()) {

            if (((time >= i * 100 && (modo == 0 || modo == 1)) || (time >= i * 50 && (modo == 2 || modo == 3)) || modo == 4)) {

                if (time >= 1000) {

                    obj.setTimeLineInidvidual(0);

                    return;

                }


                switch (modo) {
                    case 0:


                        if (obj.getPosition().x > 0)
                            obj.setAtirando(true);

                        obj.setDisparando(true);

                        calculoarColisaoI(obj, Fenix, true);
                        for (Objeto3d pp : tiros) {
                            calculoarColisaoI(obj, pp, false);
                        }
                        for (Objeto3d pp : obj.getTiroNave()) {
                            calculoarColisaoI(pp, Fenix, false);
                        }


                        if (obj.getTipo().equals("C")) {
                            float x = obj.getPosition().x;
                            float z = obj.getPosition().z;
                            double distancia = calculoarDistancia(obj, new Vetor3(-0.5f, this.DISTANCIA, -61.5f));
                            if (x < 0.0f) {
                                obj.setGiro((float) (obj.getGiro() + distancia));
                            }

                            obj.setGiroPosition(new Vetor3(0, obj.getGiro(), 0));
                            if (x < -0.5f) {
                                x += distancia * 0.01;
                                z += distancia * (0.01 * distancia);

                            } else {
                                x += distancia * 0.01;
                                z -= distancia * (0.01 * distancia);

                            }

                            obj.setPosition(new Vetor3(x, obj.getPosition().getY(), z));

                            if (obj.getPosition().z < -65.5) {
                                obj.setPosition(new Vetor3(-1.5f, this.DISTANCIA, -63));
                                obj.setGiro((float) (0));
                                obj.setGiroPosition(new Vetor3(0, 0, 0));

                                alterarModoN(obj);
                            }

                            for (Objeto3d pp : obj.getTiroNave()) {
                                float xx = pp.getPosition().x;
                                if (pp.getPosition().z > -58 && (xx < -2 || xx > 2)) {
                                    obj.setAtirando(false);
                                    obj.setAtirar(0, false);

                                    pp.setPosition(obj.getPosition());
                                    obj.setEsplodiu(false);

                                }
                            }


                        }

                        if (obj.getPosition().x > 1.0f && obj.getPosition().z < -65f) {
                            obj.setAbatido(true);
                            obj.setAtivado(false);
                        }
                        break;


                    case 1:


                        if (obj.getPosition().x < 0)
                            obj.setAtirando(true);

                        obj.setDisparando(true);
                        calculoarColisaoI(obj, Fenix, true);
                        for (Objeto3d pp : tiros) {
                            calculoarColisaoI(obj, pp, false);
                        }
                        for (Objeto3d pp : obj.getTiroNave()) {
                            calculoarColisaoI(pp, Fenix, false);
                        }


                        if (obj.getTipo().equals("D")) {
                            float x = obj.getPosition().x;
                            float z = obj.getPosition().z;
                            double distancia = calculoarDistancia(obj, new Vetor3(0.5f, this.DISTANCIA, -61.5f));
                            if (x > 0.1f) {
                                obj.setGiro((float) (obj.getGiro() - distancia));
                            }

                            obj.setGiroPosition(new Vetor3(0, obj.getGiro(), 0));
                            if (x > 0.5f) {
                                x -= distancia * 0.01;
                                z += distancia * (0.01 * distancia);

                            } else {
                                x -= distancia * 0.01;
                                z -= distancia * (0.01 * distancia);

                            }

                            obj.setPosition(new Vetor3(x, obj.getPosition().getY(), z));

                            if (obj.getPosition().z < -65.5) {
                                obj.setPosition(new Vetor3(1.5f, this.DISTANCIA, -63));
                                obj.setGiro((float) (0));
                                obj.setGiroPosition(new Vetor3(0, 0, 0));

                                alterarModoN(obj);
                            }

                            for (Objeto3d pp : obj.getTiroNave()) {
                                float xx = pp.getPosition().x;
                                if (pp.getPosition().z > -58 && (xx < -2 || xx > 2)) {
                                    obj.setAtirando(false);
                                    obj.setAtirar(0, false);
                                    pp.setPosition(obj.getPosition());
                                    obj.setEsplodiu(false);


                                }
                            }
                        }

                        if (obj.getPosition().x < -1.0f && obj.getPosition().z < -65f) {
                            obj.setAbatido(true);
                            obj.setAtivado(false);
                        }
                        break;

                    case 2:


                        calculoarColisaoI(obj, Fenix, true);
                        for (Objeto3d pp : tiros) {
                            calculoarColisaoI(obj, pp, false);
                        }
                        for (Objeto3d pp : obj.getTiroNave()) {
                            calculoarColisaoI(pp, Fenix, false);
                        }


                        if (obj.getTipo().equals("E")) {
                            float x = obj.getPosition().x;
                            float z = obj.getPosition().z;
                            Vetor3 v = new Vetor3(-1, this.DISTANCIA, -60f);
                            double distancia = calculoarDistancia(obj, v);

                            obj.setGiro(315f);
                            obj.setGiroPosition(new Vetor3(0, obj.getGiro(), 0));
                            if (distancia > 0) {
                                x -= velocidade * 2;
                                z += velocidade * 2;

                            }

                            obj.setPosition(new Vetor3(x, obj.getPosition().getY(), z));

                            if (obj.getPosition().z >= -59) {
                                obj.setPosition(new Vetor3(1.5f, this.DISTANCIA, -63.3f));
                                obj.setGiro((float) (0));
                                obj.setGiroPosition(new Vetor3(0, 0, 0));
                                obj.setEsplodiu(false);

                                alterarModoN(obj);
                                obj.setAtirando(false);
                                obj.setAtirar(0, false);
                                for (Objeto3d pp : obj.getTiroNave()) {
                                    pp.setPosition(new Vetor3(-100, this.DISTANCIA, -61.5f));
                                }
                            }
                        }
                        if (obj.getPosition().z > -60) {
                            obj.setAbatido(true);
                            obj.setAtivado(false);
                        }

                        break;
                    case 3:
                        calculoarColisaoI(obj, Fenix, true);
                        for (Objeto3d pp : tiros) {
                            calculoarColisaoI(obj, pp, false);
                        }
                        for (Objeto3d pp : obj.getTiroNave()) {
                            calculoarColisaoI(pp, Fenix, false);
                        }


                        if (obj.getTipo().equals("F")) {
                            float x = obj.getPosition().x;
                            float z = obj.getPosition().z;
                            Vetor3 v = new Vetor3(1, this.DISTANCIA, -60f);
                            double distancia = calculoarDistancia(obj, v);

                            obj.setGiro(45f);
                            obj.setGiroPosition(new Vetor3(0, obj.getGiro(), 0));
                            if (distancia > 0) {
                                x += velocidade * 2;
                                z += velocidade * 2;

                            }

                            obj.setPosition(new Vetor3(x, obj.getPosition().getY(), z));

                            if (obj.getPosition().z >= -59) {
                                obj.setPosition(new Vetor3(-1.5f, this.DISTANCIA, -63.3f));
                                obj.setGiro((float) (0));
                                obj.setGiroPosition(new Vetor3(0, 0, 0));
                                obj.setEsplodiu(false);

                                alterarModoN(obj);
                                obj.setAtirando(false);
                                obj.setAtirar(0, false);
                                for (Objeto3d pp : obj.getTiroNave()) {
                                    pp.setPosition(new Vetor3(-100, this.DISTANCIA, -61.5f));
                                }
                            }
                        }

                        if (obj.getPosition().z > -60) {
                            obj.setAbatido(true);
                            obj.setAtivado(false);
                        }
                        break;


                    case 4:
                        obj.setDisparando(true);

                        calculoarColisaoI(obj, Fenix, true);
                        for (Objeto3d pp : tiros) {
                            calculoarColisaoI(obj, pp, false);
                        }
                        for (Objeto3d pp : obj.getTiroNave()) {
                            calculoarColisaoI(pp, Fenix, false);
                        }


                        //                  if (obj.getTipo().equals("G")) {
                        float x = obj.getPosition().x;
                        float z = obj.getPosition().z;

                        z += velocidade * 2;

                        obj.setPosition(new Vetor3(x, obj.getPosition().getY(), z));

                        if (obj.getPosition().z >= -59) {
                            obj.setPosition(new Vetor3(1.5f, this.DISTANCIA, -65));
                            obj.setGiro((float) (0));
                            obj.setGiroPosition(new Vetor3(0, 0, 0));
                            obj.setEsplodiu(false);

                            alterarModoN(obj);

                        }

                        for (Objeto3d pp : obj.getTiroNave()) {
                            float xx = pp.getPosition().x;
                            if (pp.getPosition().z > -59 || xx < -2 || xx > 2) {
                                obj.setAtirando(false);
                                obj.setAtirar(0, false);
                                obj.setEsplodiu(false);

                                pp.setPosition(new Vetor3(obj.getPosition().x, this.DISTANCIA, -65));
                            }

                            if (pp.getPosition().z > -63f && pp.getPosition().z < -59f)
                                obj.setAtirando(true);


                        }
                        //   }
                        if (obj.getPosition().z > -60) {
                            obj.setAbatido(true);
                            obj.setAtivado(false);
                        }

                        break;

                }
            }
            obj.setTimeLineInidvidual(obj.getTimeLineInidvidual() + 1);


        }


        if (obj.isDisparando())
            obj.atirandoC(Fenix, velocidade * 2);


    }


    private void alterarModoN(Objeto3d o) {
        o.setTipo("N");
    }


//cria e renderisa objetos 3d

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glEnable(GL10.GL_TEXTURE_2D);                  //abilita mapeamento de textura
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);       //  Background preto
        gl.glClearDepthf(1.0f);                              //Depth Buffer Setup
        gl.glDepthFunc(GL10.GL_LEQUAL);                  //The Type Of Depth Testing To Do
        gl.glEnable(GL10.GL_LIGHTING);//ABILITA A LUZ

        gl.glEnable(GL10.GL_LIGHT1);
        gl.glEnable(GL10.GL_LIGHT0);
        gl.glEnable(GL10.GL_DEPTH_TEST);                  //Enables Depth Testing
        gl.glShadeModel(GL10.GL_SMOOTH);                  //abilita sombreamento

        gl.glEnable(GL10.GL_COLOR_MATERIAL);//abilita uso de matrial
        GLES20.glCullFace(GLES20.GL_FRONT);//desenha somente a parte de fora do objeto

        float[] ambientLight = {1.0f, 1.0f, 1.0f, 1.0f};//cor amarela do ambiente


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


        this.gl2 = gl;
      //  barra.loadGLTexture(true);

        // mensagemBase.loadGLTexture(true);

    }

    public void pausar(boolean p) {

        if (p) {
            this.pause = false;
            this.musica.pause();
            // pausar( true );

        } else {
            this.pause = true;
            this.musica.start();
            //pausar( false );
        }


    }

    float distanciaR = 0f;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDrawFrame(GL10 gl) {
        // limpa a tela e os buffer
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);





        if (carregado) {

            gl.glPushMatrix();


            ceu.draw((GL11) gl2);
            mensagemBase.draw((GL11) gl2);
            Fenix.draw((GL11) gl2);

            for (int p = 0; p < objCenario.size(); p++) {
                objCenario.get(p).draw((GL11) gl2);


            }
            splosaoArrayNave.get(esplosaoNaveId).draw((GL11) gl2);
            for (int i = 0; i < nivelTiro; i++) {

                if (!tiros.get(i).getMover().equals("nulo")) {
                    tiros.get(i).draw((GL11) gl2);
                }


            }


            if (!this.perdeu && !this.venceu) {
                for (int p = 0; p < inimigosC.size(); p++) {

                    desenhar(inimigosC.get(p), "inimigosC", p);

                    desenhar(inimigosE.get(p), "inimigosE", p);
                    inimigosC.get(p).setImpacto(false);
                    inimigosE.get(p).setImpacto(false);

                    try {

                        if (p < inimigosB.size()) {
                            desenhar(inimigosB.get(p), "inimigosB", p);
                            desenhar(inimigosA.get(p), "inimigosA", p);
                            desenhar(inimigosX.get(p), "inimigosX", p);


                            if (asteroide.get(p).getPassou().equals("N") && asteroide.get(p).isTroca() == false) {
                                if (!asteroide.get(p).isEsplodirNave()) {
                                    asteroide.get(p).draw((GL11) gl2);

                                } else {
                                    Esplosao esp = filtro("asteroide", p);
                                    if (asteroide.get(p).getEsplosaoNaveId() < esp.getSplosaoArrayNave().size()) {

                                        esp.getSplosaoArrayNave().get(asteroide.get(p).getEsplosaoNaveId()).draw((GL11) gl2);
                                        esp.getSplosaoArrayNave().get(asteroide.get(p).getEsplosaoNaveId()).setImpacto(false);
                                    }


                                }
                            }

                            inimigosA.get(p).setImpacto(false);
                            inimigosB.get(p).setImpacto(false);
                            inimigosX.get(p).setImpacto(false);
                            asteroide.get(p).setImpacto(false);

                        }


                    } catch (Exception e) {
                        p = 0;
                    }
                }


                for (int p = 0; p < boss.size(); p++) {
                    desenhar(boss.get(p), "boss", p);
                    boss.get(p).setImpacto(false);
                }


            }
            gl.glPopMatrix();

            gl.glLoadIdentity();

//            gl.glRotatef( (giroy)*-1, 0, 1, 0 );
//
            // pontoDoEixozTela+=0.02;


            if (!parado) {
                controle();
                if (timeLine == 1) {
                    localz = 62f;
                    localy = -17.00f;
                    localx = Fenix.getPosition().x * -1;

                }
            } else {


                restart();

            }
            gl.glRotatef(80, 1, 0, 0);
//globo

            gl.glTranslatef(localx, localy, localz);


            if (this.pause) {
            } else if (this.pause == false || this.perdeu) {

                gl.glTranslatef(0, -2, -3);
                objeto3dxquadro.draw((GL11) gl2);
            }


        } else if (carregado == false) {
            gl.glLoadIdentity();

            gl.glTranslatef(0, 0, -10);
            if (carga >= 13) {
                //   gl.glClearColor(0.4f, 0.1f, 0.1f, 0.1f );
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    quadroInserirPalavra.setTextura(R.drawable.esplosao);
                }
               // quadroInserirPalavra.loadGLTexture(true);
                quadroInserirPalavra.draw((GL11) gl2);
                tempoDeEspera--;
            } else {
                barra.draw((GL11) gl2);
                mensagemBase.draw((GL11) gl2);
                carga++;
            }
            try {
                carregar(tipo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void desenhar(Objeto3d o, String nome, int p) {
        if (o.getPassou().equals("N") && o.isTroca() == false && o.getPosition().x > -1.5f && o.getPosition().x < 1.5f) {
            if (!o.isEsplodirNave()) {
                o.draw((GL11) gl2);

                for (int pp = 0; pp < o.getTiroNave().size(); pp++) {
                    o.getTiroNave().get(pp).draw((GL11) gl2);
                    o.getTiroNave().get(pp).setImpacto(false);
                }

            } else {
                Esplosao esp = filtro(nome, p);

                if (o.getEsplosaoNaveId() < esp.getSplosaoArrayNave().size()) {

                    esp.getSplosaoArrayNave().get(o.getEsplosaoNaveId()).draw((GL11) gl2);
                    //        esp.getSplosaoArrayNave().get(o.getEsplosaoNaveId()).setImpacto(false);
                }

                for (int pp = 0; pp < o.getTiroNave().size(); pp++) {
                    o.getTiroNave().get(pp).draw((GL11) gl2);
                    o.getTiroNave().get(pp).setImpacto(false);
                }

            }
        }
    }


    private void restart() {

        recomecar++;
        vitoria = false;
        if (recomecar == 100) {
            parado = false;
            this.perdeu = false;
            this.venceu = false;
            recomecar = 0;
        }

        if (recomecar == 5) {
            //parado = false;


            resaetarTudo(inimigosA);
            resaetarTudo(inimigosB);
            resaetarTudo(inimigosC);
            resaetarTudo(inimigosE);
            resaetarTudo(inimigosX);
            //  resaetarTudo(sobrasObj);
            resaetarTudo(boss);

            timeModoBoss = 0;
            time = 0;
            timeLine = 0;
            timeModoB = 0;
            timeModoC = 0;
            timeModoE = 0;
            timeModoX = 0;
            alterarNivelDeTiro(12);
            horaDoBoss = false;
            this.animal.setX(0);
            velox = 0;
            veloz = -62f;
            Fenix.getPosition().x = 0;
            Fenix.getPosition().z = -62f;
            invulneravel = false;
        //    ceu.setPosition(new Vetor3(0, -16, -65f));

            for (int i = 0; i < vaiPraCena.length; i++) {
                vaiPraCena[i] = false;
            }
            for (int i = 0; i < ativarBoss.length; i++) {
                ativarBoss[i] = false;
            }
        }

    }

    private Esplosao filtro(String nome, int p) {
        Esplosao x = null;
        for (Esplosao esp : esplosaoArrayObj) {
            if (esp.getNome().equals(nome) && esp.getId() == p) {
                x = esp;
                break;

            }
        }
        return x;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean calculoarColisao(Objeto3d obj, Objeto3d obj2) {
        boolean vai = false;
        float d = obj.testImpacto(obj2);

        if (d <= 1) {
            colidiu = true;
            veloy = (veloy) * -1;
            velox = (velox) * -1;
            veloz = (veloz) * -1;

        } else {

        }
        return vai;
        /////////////////////////////////
    }

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


    public void moverPersonagem(MotionEvent event) {

        if (vitoria) {
            return;
        }
//        if(Fenix.getPosition().z>-60.9f){
//            Fenix.getPosition().z=-61f;
//            return;
//        }
        if (event.getX() > pontoDoEixoX) {
            rotacinarFenix = 2;
            giroy -= 1.2;
            if (giroy < 0) {
                giroy = 360;
            }

        } else {
            rotacinarFenix = 1;
            giroy += 1.2;
            if (giroy > 360) {
                giroy = 0;
            }
        }


////////////////////////////////////////

        if (event.getY() > pontoDoEixoY) {
            //  rotacinarFenix=2;

            girox -= 1.2;


        } else {
            // rotacinarFenix=1;

            girox += 1.2;


        }


        this.yy = (int) event.getY();
        this.xx = (int) event.getX();


        if (this.yy < this.animal.getY() && this.cont == 0) {
            this.yr = (int) this.animal.getY() - this.yy;
            this.xr = (int) this.animal.getX() - this.xx;
            rotacinarFenix = 2;
            this.cont = 1;
        } else if (this.yy > this.animal.getY() && this.cont == 0) {
            this.yr = (int) this.animal.getY() - this.yy;
            this.xr = (int) this.animal.getX() - this.xx;
            this.cont = 1;
            rotacinarFenix = 1;
        }


        if (this.xx + this.xr < this.w * 0.24 && this.xx + this.xr > (this.w * 0.24
        ) * -1) {

            localx = ((this.animal.getX() * 0.14f) * (0.14f * turbo)) * -1;
        }

        if (this.xx + this.xr < this.w * 0.40 && this.xx + this.xr > (this.w * 0.40) * -1) {
            this.animal.setX(this.xx + this.xr);


        } else {
            this.xr = (int) this.animal.getX() - this.xx;


        }


        this.animal.setY(this.yy + this.yr);


        if (girox <= 45 || girox >= 315) {


            veloy = (girox / 45 > 1) ?
                    (-2 * (((girox / 180) - 2f) * 2))
                    : (-2 * (((girox / 180)) * 2));

        } else {
            if (girox > 45 && girox < 180) {

                girox = 44;
            } else {
                girox = 316;

            }
        }


        //girox=0;

        veloz = ((this.animal.getY() * 0.2f) * (0.2f * turbo)) - 62;
        velox = ((this.animal.getX() * 0.2f) * (0.2f * turbo));
        veloy = veloy * (0.2f * turbo);

        pontoDoEixoX = event.getX();
        pontoDoEixoY = event.getY();

        Fenix.setPosition(new Vetor3(
                velox,
                15f,
                veloz));

    }


    /////////////////////////////////////////////////////
    int tempo = 0;

    private void luping() {


        if (tempo <= 90) {
            if (giroy < 0) {
                giroy = 358;
            }
            giroy -= 2;
            tempo++;

        } else {
            tempo = 0;
            luping = false;
            veloz = veloz * -1;
            velox = velox * -1;
            distanciaR = 0f;

            if (moduloR == 0) {
                moduloR = 1;

            } else {
                moduloR = 0;

            }
        }


    }

    private void aumentarNivelTiro(int nivel) {
        switch (nivel) {
            case 2:
                nivelTiro = 24;
                nivelTiroIndex = 0;
                idDisparo = 0;
                idDisparo2 = 12;
                idDisparo3 = 24;
                idDisparo4 = 36;
                idDisparo5 = 48;
                break;
            case 3:
                nivelTiro = 36;
                nivelTiroIndex = 0;
                idDisparo = 0;
                idDisparo2 = 12;
                idDisparo3 = 24;
                idDisparo4 = 36;
                idDisparo5 = 48;
                break;
            case 4:
                nivelTiro = 48;
                nivelTiroIndex = 0;
                idDisparo = 0;
                idDisparo2 = 12;
                idDisparo3 = 24;
                idDisparo4 = 36;
                idDisparo5 = 48;
//                nivelTiroIndex = 36;
//                for (int i = 0; i < 36; i++) {
//                    tiros.get(i).setPosition(new Vetor3(-1000f,
//                            tiros.get(i).getPosition().getY(),
//                            tiros.get(i).getPosition().getZ() - (0.2f)));
//
//                    tiros.get(i).setMover("nulo");
//                    tiros.get(i).setTime(0);
//                    idDisparo = 0;
//                    idDisparo2 = 12;
//                    idDisparo3 = 24;
//                    idDisparo4 = 36;
//                    idDisparo5 = 48;
//                }
                break;
            case 5:
                nivelTiro = 60;
                nivelTiroIndex = 48;

                for (int i = 36; i < 48; i++) {
                    tiros.get(i).setPosition(new Vetor3(-1000f,
                            tiros.get(i).getPosition().getY(),
                            tiros.get(i).getPosition().getZ() - (0.2f)));
                    tiros.get(i).setMover("nulo");
                    tiros.get(i).setTime(0);
                    idDisparo = 0;
                    idDisparo2 = 12;
                    idDisparo3 = 24;
                    idDisparo4 = 36;
                    idDisparo5 = 48;
                }
                break;
            default:
                nivelTiro = 12;
                nivelTiroIndex = 0;
                for (int i = 48; i < 60; i++) {
                    tiros.get(i).setPosition(new Vetor3(-1000f,
                            tiros.get(i).getPosition().getY(),
                            tiros.get(i).getPosition().getZ() - (0.2f)));
                    tiros.get(i).setMover("nulo");
                    tiros.get(i).setTime(0);

                }
                break;

        }
    }

    private void atirar() {
        tempoDisparo++;
        //aumentarNivelTiro(4);

        if (tempoDisparo >= nivelTiroTempo) {
            if (tiros.get(idDisparo).getMover().equals("nulo")) {
                switch (nivelTiro) {
                    case 12:

                        tempoDisparo = 0;
                        tiros.get(idDisparo).setMover("disparar");
                        if (idDisparoQTD < 11) {
                            idDisparo++;
                            idDisparoQTD++;
                        } else {
                            idDisparo = 0;
                            idDisparoQTD = 0;
                        }


                        break;
                    case 24:
                        tiros.get(idDisparo).setMover("disparar");
                        tiros.get(idDisparo2).setMover("disparar");
                        tempoDisparo = 0;
                        if (idDisparoQTD < 11) {
                            idDisparo++;
                            idDisparo2++;
                            idDisparoQTD++;
                        } else {
                            idDisparo = 0;
                            idDisparo2 = 12;
                            idDisparoQTD = 0;
                        }
                        break;
                    case 36:
                        tiros.get(idDisparo).setMover("disparar");
                        tiros.get(idDisparo2).setMover("disparar");
                        tiros.get(idDisparo3).setMover("disparar");

                        tiros.get(idDisparo).setGiroPosition(new Vetor3(tiros.get(idDisparo).getGiroPosition().x, 5.0f, tiros.get(idDisparo).getGiroPosition().z));
                        tiros.get(idDisparo2).setGiroPosition(new Vetor3(tiros.get(idDisparo2).getGiroPosition().x, -5.0f, tiros.get(idDisparo2).getGiroPosition().z));

                        tempoDisparo = 0;
                        if (idDisparoQTD < 11) {
                            idDisparo++;
                            idDisparo2++;
                            idDisparo3++;
                            idDisparoQTD++;
                        } else {
                            idDisparo = 0;
                            idDisparo2 = 12;
                            idDisparo3 = 24;
                            idDisparoQTD = 0;
                        }
                        break;

                    case 48:

                        tiros.get(idDisparo).setGiroPosition(new Vetor3(tiros.get(idDisparo).getGiroPosition().x, 10.0f, tiros.get(idDisparo).getGiroPosition().z));
                        tiros.get(idDisparo2).setGiroPosition(new Vetor3(tiros.get(idDisparo2).getGiroPosition().x, -10.0f, tiros.get(idDisparo2).getGiroPosition().z));

                        tiros.get(idDisparo).setMover("disparar");
                        tiros.get(idDisparo2).setMover("disparar");
                        tiros.get(idDisparo3).setMover("disparar");
                        tiros.get(idDisparo4).setMover("disparar");

                        tempoDisparo = 0;
                        if (idDisparoQTD < 11) {
                            idDisparo++;
                            idDisparo2++;
                            idDisparo3++;
                            idDisparo4++;
                            idDisparoQTD++;
                        } else {
                            idDisparo = 0;
                            idDisparo2 = 12;
                            idDisparo3 = 24;
                            idDisparo4 = 36;
                            idDisparoQTD = 0;
                        }
                        break;

                    case 60:
                        tiros.get(idDisparo).setGiroPosition(new Vetor3(tiros.get(idDisparo).getGiroPosition().x, 10.0f, tiros.get(idDisparo).getGiroPosition().z));
                        tiros.get(idDisparo2).setGiroPosition(new Vetor3(tiros.get(idDisparo2).getGiroPosition().x, -10.0f, tiros.get(idDisparo2).getGiroPosition().z));
                        tiros.get(idDisparo3).setGiroPosition(new Vetor3(tiros.get(idDisparo).getGiroPosition().x, 5.0f, tiros.get(idDisparo).getGiroPosition().z));
                        tiros.get(idDisparo5).setGiroPosition(new Vetor3(tiros.get(idDisparo2).getGiroPosition().x, -5.0f, tiros.get(idDisparo2).getGiroPosition().z));

                        tiros.get(idDisparo).setMover("disparar");
                        tiros.get(idDisparo2).setMover("disparar");
                        tiros.get(idDisparo3).setMover("disparar");
                        tiros.get(idDisparo4).setMover("disparar");
                        tiros.get(idDisparo5).setMover("disparar");
                        tempoDisparo = 0;
                        if (idDisparoQTD < 11 && idDisparo5 < 60) {
                            idDisparo++;
                            idDisparo2++;
                            idDisparo3++;
                            idDisparo4++;
                            idDisparo5++;
                            idDisparoQTD++;
                        } else {
                            idDisparo = 0;
                            idDisparo2 = 12;
                            idDisparo3 = 24;
                            idDisparo4 = 36;
                            idDisparo5 = 48;
                            idDisparoQTD = 0;
                        }


                        break;

                }

            }
        }
    }

    private void resaetarTudo(ArrayList<Objeto3d> objeto3ds) {
        for (Objeto3d o : objeto3ds) {

            o.setVida(o.getRecoverVida());
            o.getPosition().z = -65f;
            o.setGiro(0);
            o.setGiro((float) (0));
            o.setReset(true);

            o.setGiroPosition(new Vetor3(0, 0, 0));
            Esplosao esp = filtro(o.getNomeRef(), objeto3ds.indexOf(o));
            o.setAtirando(false);
            if (o.getTiroTime() != null) {
                for (int i = 0; i < o.getTiroTime().length; i++) {

                    o.getTiroTime()[i] = 0;
                }
            }

            if (o.getTiroNave() != null) {
                for (Objeto3d t : o.getTiroNave()) {

                    t.getPosition().z = -1000f;
                }
            }
            o.setTimeLineInidvidual(0);
            o.setEsplosaoNaveId(0);
            o.setTimeEsplosaoNave(0);
            o.setEsplodiu(false);
            o.setEsplodirNave(false);
            o.setAtivado(false);
            o.setAbatido(false);
        }
        horaDoBoss = false;
        for (int i = 0; i < vaiPraCena.length; i++) {
            vaiPraCena[i] = false;
        }
        for (int i = 0; i < ativarBoss.length; i++) {
            ativarBoss[i] = false;
        }

    }

    ///GERENCIA O TOQUE NA TELA
    @Override
    public boolean onTouch(View view, MotionEvent event) {


        if (!this.venceu && carregado) {
            if (event.getPointerCount() == 1 && event.getPointerId(0) == 0) {

                int posX = (int) (this.wTela / 100);
                int posY = (int) (this.h / 100);

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fogo = true;
                    pontoDoEixoYInicio = event.getY();
                    pontoDoEixoXInicio = event.getX();

                    if (this.perdeu == false) {

                        if ((int) event.getX() > posX * 1 && (int) event.getX() < posX * 20 && (int) event.getY(event.getPointerId(0)) > posY * 0 && (int) event.getY(event.getPointerId(0)) < posY * 20) {
                            tempo = 0;

                            luping = true;

                        } else {
                            luping = false;

                        }


                        if ((int) event.getX() > posX * 90 && (int) event.getX() < posX * 120 && (int) event.getY(event.getPointerId(0)) > posY * 10 && (int) event.getY(event.getPointerId(0)) < posY * 20) {


                            quadroInserirPalavra.setPosition(new Vetor3(0f, 2f, 0f));


                        } else if ((int) event.getX() > posX * 0 && (int) event.getX() < posX * 15 && (int) event.getY(event.getPointerId(0)) > posY * 10 && (int) event.getY(event.getPointerId(0)) < posY * 20) {

                            if (this.pause) {
                                this.pause = false;
                                this.musica.pause();
                            } else {
                                this.pause = true;
                                this.musica.start();
                            }

                        } else if (this.pause == false && (int) event.getX() > this.wTela * 0.5 && (int) event.getY(event.getPointerId(0)) > posY * 50) {

                            this.pause = true;

                            this.musica.start();


                        } else if (this.pause == false && (int) event.getX() > this.wTela * 0 && (int) event.getX() < this.wTela * 0.5 && (int) event.getY(event.getPointerId(0)) > posY * 50) {

                            this.parar();


                        } else if ((int) event.getX() > posX * 85 && (int) event.getX() < posX * 100 && (int) event.getY(event.getPointerId(0)) > posY * 0 && (int) event.getY(event.getPointerId(0)) < posY * 20) {


                            if (this.musica.isPlaying()) {
                                this.musica.pause();

                            } else {
                                this.musica.start();

                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                retornarQuadro = true;
                            }
                        }
                        if (inicio == false && (int) event.getX() > posX * 0 && (int) event.getY(event.getPointerId(0)) > posY * 90) {
                            this.inicio = true;
                            try {
                                this.estatusDojogo(2);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            this.animal.setY((int) (this.h * 0.5));
                            this.animal.setX((int) (this.w * 0.5));

                        }
                        if (inicio == false && (int) event.getX() > posX * 0 && (int) event.getY(event.getPointerId(0)) > posY * 70
                                && (int) event.getY(event.getPointerId(0)) < posY * 90) {
                            this.inicio = true;
                            // this.estatusDojogo( 0 );
                            this.nivel = 1;
                            this.animal.setY((int) (this.h * 0.5));
                            this.animal.setX((int) (this.w * 0.5));
                            this.mensagem = "";

                        }


                    } else {
                        this.pause = true;

                        perdeu = false;
                        venceu = false;
                        vida = 3;
                        gravarRecorde();

                        palavrasConquistadas = palavrasConquistadas - palavrasConquistadasDeReset;
                        palavrasConquistadasDeReset = 0;

                        retornarQuadro = true;


                    }

                    if ((int) event.getX() > posX * 0 && (int) event.getX() < posX * 35 && (int) event.getY(event.getPointerId(0)) > posY * 0 && (int) event.getY(event.getPointerId(0)) < posY * 10) {
                        rotatef[0] = 90;
                    } else if ((int) event.getX() > posX * 65 && (int) event.getX() < posX * 120 && (int) event.getY(event.getPointerId(0)) > posY * 0 && (int) event.getY(event.getPointerId(0)) < posY * 10) {
                        rotatef[0] = 30;

                    }

                    return true;

                } else if (event.getAction() == MotionEvent.ACTION_MOVE && this.pause) {
                    if (luping == false) {
                        this.pontoDoEixoYFimm = event.getY();
                        this.pontoDoEixoXFimm = event.getX();
                        this.moverPersonagem(event);
                    }
                    return true;


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    this.cont = 0;
                    rotacinarFenix = 0;
                    fogo = false;
                    this.xr = 0;
                    this.yr = 0;
                    this.xx = 0;
                    this.yy = 0;
                    //veloy= 0;
                    this.pontoDoEixoYFimm = 0;
                    this.pontoDoEixoXFimm = 0;
                    this.pontoDoEixoYInicio = 0;
                    this.pontoDoEixoXInicio = 0;
                    if (perdeu == false && this.pause == false && (int) event.getX() > this.w * 0 && (int) event.getX() < this.w * 0.4 && (int) event.getY(event.getPointerId(0)) > posY * 50 && (int) event.getY(event.getPointerId(0)) < posY * 60) {

                        this.parar();


                        return true;
                    }
                }
            }


        }

        return true;
        //////////////////////////////////////////////////
    }
}
