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
    private int proximoIni = 0;

    private int rotacinarFenix = 0;
    private float pontoDoEixoX = 0;
    private float pontoDoEixoY = 0;
    private float turbo = 0.1f;
    ;
    private float pontoDoEixozTela = 62;
    private float pontoDoEixoxTela = 0;
    private final int QTD_DE_TIROS = 61;
    private int nivelTiro = 36;
    private int nivelTiroIndex = 0;
    private int danoNoInimigo = 10;
    private float pontoDoEixoYInicio = 0;
    private float pontoDoEixoYFimm = 0;
    private float pontoDoEixoXInicio = 0;
    private float pontoDoEixoXFimm = 0;
    private boolean[] vaiPraCena = {false, false, false, false, false, false};
    private boolean[] ativarBoss = {false, false, false, false, false, false};

    private int indiceV = 0;
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
    private Objeto3d cubo;
    private ArrayList<Objeto3d> inimigosC;
    private ArrayList<Objeto3d> inimigosB;
    private ArrayList<Objeto3d> inimigosD;
    private ArrayList<Objeto3d> inimigosE;
    private ArrayList<Objeto3d> boss;

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

    private MediaPlayer musica;
    private MediaPlayer errado;
    private MediaPlayer acerto;
    private boolean pause = true;
    private boolean inicio = true;
    private int modo = 0;
    private int modoE = 2;
    private int modoB = 0;
    private int modoBoss = 0;

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
    private int timeModoBoss = 0;

    private int quantidadeDeElementos = 3;
    private DisplayMetrics displayMetrics;
    private float h;
    private float w;
    private float[] rotatef = {90, 1f, 0, 0};

    private float wTela;

    private boolean carregado = false;
    private boolean colidiu = false;


    private int[] direcaoX = {0, 7, 2, 0, 6, 3, 0, 5, 4, 6, 7, 6, 3, 1};


//0=baixo
//1=cima
//2=direita
//3= esquerda
//4= baixo - direita
//5= baixo - esquerda
//6= cima - direita
//7=cima - esquerda


    private int dr = 0;


    private final float velocidade = 0.006f;
    private final float velocidadeinimigo1 = 0.2f;
    private final float velocidadeTiro = 0.6f;
    private final float velocit = 0.6f;
    private boolean luping = false;
    private float escala = 0.2f;
    private float distancia = 15f;
    private float moduloY = -15f;
    private float modulox = 0;
    private float moduloz = -35;

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

        this.musica = MediaPlayer.create(context, R.raw.top);
        this.musica.setLooping(true);
        this.musica.setVolume(0.2f, 0.2f);

        this.barra = new Objeto3d(asset, "fundob.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.base), new Vetor3(1f, 1f, 1f));
        this.barra.setMudarTamanho(true);
        Bitmap seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.seta);
        seta = Bitmap.createScaledBitmap(seta, 122, 182, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mensagemBase = new Objeto3d(asset, "quadroConta.obj",
                    ConvertBitimap.getBitmapBase(390, 190, seta, "Arraste com o dedo para \nguiar a Fenix", 40, 3), new Vetor3(0.5f, 0.4f, 1f));
        }
        this.mensagemBase.setMudarTamanho(true);
        mensagemBase.setPosition(new Vetor3(0, -1f, 1f));
        mensagemBase.setTransparente(true);

        this.leaoxm = MediaPlayer.create(context, R.raw.leao);
        animal = new Animaisx(0, 0, 0, 350, 320, 420, "leao", Fenix, this.leaoxm);
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

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Até a letra certa da palavra", 40, 3));
                mensagemBase.loadGLTexture(true);


                break;

            case 1:


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.nove);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);
                mensagemBase.setPosition(new Vetor3(1, -2f, 1f));

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando.", 80, 3));
                mensagemBase.loadGLTexture(true);

                ceu = new Objeto3d(asset, "ceu.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.espaco), new Vetor3(escala * 2, escala, escala));
                ceu.loadGLTexture(true);
                ceu.setPosition(new Vetor3(0, 16, -62));

                cubo = new Objeto3d(asset, "maze.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.plano), new Vetor3(escala * 2, escala, escala));
                cubo.vezes(3);
                cubo.loadGLTexture(true);
                cubo.setCalculosDeImpacto();
                cubo.setPosition(new Vetor3(0, 0f, 0));

                break;
            case 2:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.oito);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando..", 80, 3));
                mensagemBase.loadGLTexture(true);


                break;
            case 3:
                setFenix(new Objeto3d(asset, "navez.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.navea), new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f)));
                Fenix.loadGLTexture(true);

                this.Fenix.setEstado("NBateu");
                Fenix.setPosition(new Vetor3(0, 16, -62));
                Fenix.setRefletir(false);
                splosaoArrayNave = splosao(Fenix);
                //this.Fenix.setGiroCentral(true);
                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.sete);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando...", 80, 3));
                mensagemBase.loadGLTexture(true);

                break;
            case 4:
                Bitmap quadroB = ConvertBitimap.getBitmap(context, R.drawable.quadro, 0, 0, 0, false);

                quadroB = Bitmap.createScaledBitmap(quadroB, 400, 400, true);

                objeto3dxquadro = new Objeto3d(asset, "figura.obj", quadroB, new Vetor3(escala * 35, escala * 35, escala * 35));


                objeto3dxquadro.loadGLTexture(true);

                objeto3dxquadro.setPosition(new Vetor3(0, 0f, 1f));
                objeto3dxquadro.setTransparente(true);

                objeto3dxquadro.setMudarTamanho(true);
                objeto3dxquadro.setGiro(-30);
                objeto3dxquadro.setGiroPosition(new Vetor3(1, 0f, 0f));


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.seis);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando...", 80, 3));
                mensagemBase.loadGLTexture(true);

                break;

            case 5:


                Bitmap menuB = ConvertBitimap.getBitmap(context, R.drawable.menu, 0, 0, 0, false);

                menuB = Bitmap.createScaledBitmap(menuB, 400, 400, true);
                Bitmap img = ConvertBitimap.getBitmap(context, R.drawable.ic_bola, 255, 0, 0, 0, false);

                Bitmap hp = ConvertBitimap.getBitmap(context, R.drawable.ic_coracao, 0, 0, 0, false);
                Bitmap sin = ConvertBitimap.getBitmap(context, R.drawable.son, 0, 0, 0, false);
                Bitmap sout = ConvertBitimap.getBitmap(context, R.drawable.sonmudo, 0, 0, 0, false);

                hp = Bitmap.createScaledBitmap(hp, 80, 70, true);
                hp = Bitmap.createScaledBitmap(hp, 30, 25, true);
                List<Bitmap> imag = new ArrayList<>();
                imag.add(menuB);
                imag.add(hp);
                imag.add(sin);
                imag.add(sout);


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.cinco);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando", 80, 3));
                mensagemBase.loadGLTexture(true);
                break;
            case 6:
                inimigosA = new ArrayList<>();
                inimigosD = new ArrayList<>();
                inimigosC = new ArrayList<>();
                asteroide = new ArrayList<>();
                inimigosE = new ArrayList<>();
                boss = new ArrayList<>();


                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.quatro);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando.", 80, 3));
                mensagemBase.loadGLTexture(true);
                break;

            case 7:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.tres);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando..", 80, 3));
                mensagemBase.loadGLTexture(true);
                break;
            case 8:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.dois);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Carregando...", 80, 3));
                mensagemBase.loadGLTexture(true);
                break;
            case 9:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.um);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Falta pouco", 80, 3));
                mensagemBase.loadGLTexture(true);
                break;

            case 10:
                inimigosB = new ArrayList<>();

                for (int p = 0; p < this.quantidadeDeElementos; p++) {
                    char v = alfabeto.charAt(p);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        inimigosB.add(new Objeto3d(asset, "inimigo.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.inimigo), new Vetor3(escala * 5f, escala * 5f, escala * 5f)));
                        inimigosA.add(new Objeto3d(asset, "based.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.based), new Vetor3(escala * 5f, escala * 5f, escala * 5f)));
                        inimigosD.add(new Objeto3d(asset, "inimigod.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.inimigod), new Vetor3(escala * 5f, escala * 5f, escala * 5f)));

                        asteroide.add(new Objeto3d(asset, "asteroid.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroide), new Vetor3(escala * 5f, escala * 5f, escala * 5f)));


                    }

                    inimigosD.get(p).loadGLTexture(false);
                    inimigosD.get(p).setValor(String.valueOf(v));
                    inimigosD.get(p).setVida(65f);
                    inimigosD.get(p).setRecoverVida(65f);
                    inimigosD.get(p).setSplosaoArrayNave(inimigosD.get(p).splosao(inimigosD.get(p), asset, context, 1f));
                    inimigosD.get(p).setTiroNave(inimigosD.get(p).criarTiros(inimigosD.get(p), 1, asset, "tiroz.obj", R.drawable.tirox, context));
                    inimigosD.get(p).setRefletir(true);

                    asteroide.get(p).loadGLTexture(false);
                    asteroide.get(p).setValor(String.valueOf(v));
                    asteroide.get(p).setVida(95f);
                    asteroide.get(p).setRecoverVida(95f);
                    asteroide.get(p).setSplosaoArrayNave(asteroide.get(p).splosao(asteroide.get(p), asset, context, 1.3f));
                    asteroide.get(p).setRefletir(true);

                    inimigosB.get(p).loadGLTexture(false);
                    inimigosB.get(p).setValor(String.valueOf(v));
                    inimigosB.get(p).setVida(25f);
                    inimigosB.get(p).setRecoverVida(25f);
                    inimigosB.get(p).setSplosaoArrayNave(inimigosB.get(p).splosao(inimigosB.get(p), asset, context, 0.5f));
                    inimigosB.get(p).setTiroNave(inimigosB.get(p).criarTiros(inimigosB.get(p), 1, asset, "tiroc.obj", R.drawable.tiroc, context));
                    inimigosB.get(p).setRefletir(true);


                    inimigosA.get(p).loadGLTexture(false);
                    inimigosA.get(p).setSplosaoArrayNave(inimigosA.get(p).splosao(inimigosA.get(p), asset, context, 0.8f));
                    inimigosA.get(p).setValor(String.valueOf(v));
                    inimigosA.get(p).setTiroNave(inimigosA.get(p).criarTiros(inimigosA.get(p), 3, asset, "tiroc.obj", R.drawable.tiroc, context));
                    inimigosA.get(p).setVida(35f);
                    inimigosA.get(p).setRecoverVida(35f);
                    inimigosA.get(p).setRefletir(true);
                }

                inimigosB.get(0).setPosition(new Vetor3(-0.3f,
                        distancia, -65));

                inimigosB.get(1).setPosition(new Vetor3(0.3f,
                        distancia, -65));

                inimigosB.get(2).setPosition(new Vetor3(0.0f,
                        distancia, -64));

//                inimigosB.get( 3 ).setPosition( new Vetor3( 0.5f,
//                        distancia, -64 ) );

                inimigosA.get(0).setPosition(new Vetor3(-0.2f,
                        distancia + 0.06f, -67));
                inimigosA.get(1).setPosition(new Vetor3(0.3f,
                        distancia + 0.06f, -67));
                inimigosA.get(2).setPosition(new Vetor3(0.0f,
                        distancia + 0.06f, -69));


                inimigosD.get(0).setPosition(new Vetor3(0.5f,
                        distancia + 0.03f, -65));

                inimigosD.get(1).setPosition(new Vetor3(0.0f,
                        distancia + 0.03f, -67));

                inimigosD.get(2).setPosition(new Vetor3(-0.5f,
                        distancia + 0.03f, -69));

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.zero);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Ta quase lá", 80, 3));
                mensagemBase.loadGLTexture(true);

//inimigo tipo c------------------------------------------------------------------

                for (int p = 0; p < 7; p++) {
                    // char v = alfabeto.charAt(p );
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        inimigosC.add(new Objeto3d(asset, "inimigoc.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.inimigoc), new Vetor3(escala * 5f, escala * 5f, escala * 5f)));
                        inimigosE.add(new Objeto3d(asset, "inimigoc.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.inimigoe), new Vetor3(escala * 5f, escala * 5f, escala * 5f)));

                    }
                    inimigosC.get(p).loadGLTexture(false);
                    inimigosC.get(p).setValor(String.valueOf("v"));
                    inimigosC.get(p).setVida(10f);
                    inimigosC.get(p).setRecoverVida(10f);

                    inimigosC.get(p).setSplosaoArrayNave(inimigosC.get(p).splosao(inimigosC.get(p), asset, context, 0.3f));
                    inimigosC.get(p).setTiroNave(inimigosC.get(p).criarTiros(inimigosC.get(p), 1, asset, "tiroc.obj", R.drawable.tiroc, context));
                    inimigosC.get(p).setTipo("C");
                    inimigosC.get(p).setPosition(new Vetor3(-1.0f, distancia, -63f));
                    inimigosC.get(p).setRefletir(true);
                    //    inimigosC.get( p ).vezes(0.3f);


                    inimigosE.get(p).loadGLTexture(false);
                    inimigosE.get(p).setValor(String.valueOf("v"));
                    inimigosE.get(p).setVida(10f);
                    inimigosE.get(p).setRecoverVida(10f);
                    //  inimigosE.get( p ).vezes(0.3f);

                    inimigosE.get(p).setSplosaoArrayNave(inimigosE.get(p).splosao(inimigosE.get(p), asset, context, 0.3f));
                    inimigosE.get(p).setTiroNave(inimigosE.get(p).criarTiros(inimigosE.get(p), 1, asset, "tiroc.obj", R.drawable.tiroc, context));
                    inimigosE.get(p).setTipo("E");
                    inimigosE.get(p).setPosition(new Vetor3(-1.0f, distancia, -63f));
                    inimigosE.get(p).setRefletir(true);
                }


                boss.add(new Objeto3d(asset, "inimigod.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.inimigoee), new Vetor3(escala * 5f, escala * 5f, escala * 5f)));
                boss.get(0).loadGLTexture(false);
                boss.get(0).setSplosaoArrayNave(boss.get(0).splosao(boss.get(0), asset, context, 0.8f));
                boss.get(0).setValor(String.valueOf("A"));
                boss.get(0).setTiroNave(boss.get(0).criarTiros(boss.get(0), 30, asset, "tiroz.obj", R.drawable.tiroc, context));
                boss.get(0).setVida(150f);
                boss.get(0).setRecoverVida(150f);
                boss.get(0).setBoss(true);
                boss.get(0).setPosition(new Vetor3(0f,
                        distancia + 0.03f, -64));
                boss.get(0).setRefletir(true);

                carregarCronologia();
                break;
            case 11:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.zero);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Ja vai...", 80, 3));
                mensagemBase.loadGLTexture(true);

                break;
            case 12:

                seta = BitmapFactory.decodeResource(context.getResources(), R.drawable.zero);
                seta = Bitmap.createScaledBitmap(seta, 122, 182, false);

                mensagemBase.setTextura(ConvertBitimap.getBitmapBase((int) (w * 0.69f), 0, seta, "Pronto!", 80, 3));
                mensagemBase.loadGLTexture(true);
                break;

            case 13:
                Bitmap imgq = ConvertBitimap.getBitmap(context, R.drawable.ic_panda, 70, 140, 90, true);

                imgq = ConvertBitimap.getBitmapBarraxz(imgq, vida, "frase", (int) (this.wTela * 0.13f));

                quadroInserirPalavra = new Objeto3d(asset, "quadroConta.obj", imgq, new Vetor3(escala * 3.6f, escala * 20.1f, escala * 5.5f));
                quadroInserirPalavra.loadGLTexture(true);
                quadroInserirPalavra.setPosition(new Vetor3(0f, 0f, 0f));
                quadroInserirPalavra.setTransparente(true);
                tiros = new ArrayList<>();

                for (int i = 0; i < QTD_DE_TIROS; i++) {

                    if (i < 36) {
                        tiros.add(new Objeto3d(asset, "tiroz.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.tironave), new Vetor3(0.5f, 0.5f, 0.5f)));

                        tiros.get(i).vezes(1.8f);
                    } else if (i > 35 && i < 48) {
                        tiros.add(new Objeto3d(asset, "tiro.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.tiro), new Vetor3(0.5f, 0.5f, 0.5f)));

                    } else {
                        tiros.add(new Objeto3d(asset, "tiro.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.tirox), new Vetor3(0.5f, 0.5f, 0.5f)));

                    }


                    tiros.get(i).loadGLTexture(true);
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


    public void carregarCronologia(){


        cronograma = new ArrayList<>();
        Cronograma c = new Cronograma();
        c.setId(2);
        c.setPerpetuo(true);
        c.setTimeIN(100);
        c.setTimeMode(1000);
        c.setTimeOUT(4000);
        c.setModo(0);
        cronograma.add(c);

        c = new Cronograma();
        c.setId(5);
        c.setPerpetuo(true);
        c.setTimeIN(200);
        c.setTimeOUT(4000);
        c.setTimeMode(1000);
        c.setModo(1);
        cronograma.add(c);

        c = new Cronograma();
        c.setId(1);
        c.setPerpetuo(false);
        c.setTimeIN(400);
        c.setTimeOUT(1400);
        c.setTimeMode(2000);
        c.setModo(0);
        cronograma.add(c);

        c = new Cronograma();
        c.setId(1);
        c.setPerpetuo(false);
        c.setTimeIN(2400);
        c.setTimeOUT(3400);
        c.setTimeMode(2000);
        c.setModo(1);
        cronograma.add(c);

        c = new Cronograma();
        c.setId(1);
        c.setPerpetuo(false);
        c.setTimeIN(4400);
        c.setTimeOUT(5400);
        c.setTimeMode(2000);
        c.setModo(2);
        cronograma.add(c);


        c = new Cronograma();
        c.setId(0);
        c.setPerpetuo(true);
        c.setTimeIN(4000);
        c.setTimeOUT(6140);
        c.setTimeMode(Integer.MAX_VALUE);
        cronograma.add(c);
        c = new Cronograma();

        c.setId(4);
        c.setPerpetuo(true);
        c.setTimeIN(4500);
        c.setTimeOUT(8140);
        c.setTimeMode(Integer.MAX_VALUE);
        cronograma.add(c);

        c = new Cronograma();
        c.setId(2);
        c.setPerpetuo(false);
        c.setTimeIN(6000);
        c.setTimeMode(1000);
        c.setTimeOUT(7000);
        c.setModo(4);
        cronograma.add(c);

        c = new Cronograma();
        c.setId(5);
        c.setPerpetuo(false);
        c.setTimeIN(7000);
        c.setTimeMode(1000);
        c.setTimeOUT(8000);
        c.setModo(0);
        cronograma.add(c);

        c = new Cronograma();
        c.setId(1);
        c.setPerpetuo(false);
        c.setTimeIN(7000);
        c.setTimeMode(800);
        c.setTimeOUT(8000);
        c.setModo(0);
        cronograma.add(c);


        c = new Cronograma();
        c.setId(2);
        c.setPerpetuo(true);
        c.setTimeIN(7000);
        c.setTimeMode(1000);
        c.setTimeOUT(10000);
        c.setModo(0);
        cronograma.add(c);

        c = new Cronograma();
        c.setId(5);
        c.setPerpetuo(true);
        c.setTimeIN(8000);
        c.setTimeOUT(10000);
        c.setTimeMode(1000);
        c.setModo(1);
        cronograma.add(c);


        c = new Cronograma();
        c.setId(0);
        c.setPerpetuo(true);
        c.setTimeIN(10000);
        c.setTimeOUT(25000);
        c.setTimeMode(100);
        c.setModo(0);
        c.setBoss(true);
        cronograma.add(c);

        /////////////////////////////////////////////////////////////////////////////////


    }




    public ArrayList<Objeto3d> splosao(Objeto3d obj) throws IOException {
        ///CARREGA OS ARQUIS 3D DO ALFABETO
        ArrayList<Objeto3d> splosaoArray;

        splosaoArray = new ArrayList<>();
        float x = obj.getTamanho().getX();
        float y = obj.getTamanho().getY();
        float z = obj.getTamanho().getZ();
        splosaoArray.add(new Objeto3d(asset, "sp.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.esplosao), new Vetor3(x, y, z)));
        splosaoArray.get(0).setTransparente(true);
        splosaoArray.get(0).loadGLTexture(false);

        splosaoArray.add(new Objeto3d(asset, "spa.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.esplosao), new Vetor3(x, y, z)));
        splosaoArray.get(1).setTransparente(true);
        splosaoArray.get(1).loadGLTexture(false);

        splosaoArray.add(new Objeto3d(asset, "spb.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.esplosao), new Vetor3(x, y, z)));
        splosaoArray.get(2).setTransparente(true);
        splosaoArray.get(2).loadGLTexture(false);

        splosaoArray.add(new Objeto3d(asset, "spc.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.esplosao), new Vetor3(x, y, z)));
        splosaoArray.get(3).setTransparente(true);
        splosaoArray.get(3).loadGLTexture(false);

        splosaoArray.add(new Objeto3d(asset, "spd.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.esplosao), new Vetor3(x, y, z)));
        splosaoArray.get(4).setTransparente(true);
        splosaoArray.get(4).loadGLTexture(false);

        splosaoArray.add(new Objeto3d(asset, "spe.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.esplosao), new Vetor3(x, y, z)));
        splosaoArray.get(5).setTransparente(true);
        splosaoArray.get(5).loadGLTexture(false);

        splosaoArray.add(new Objeto3d(asset, "spf.obj", BitmapFactory.decodeResource(context.getResources(), R.drawable.esplosao), new Vetor3(x, y, z)));
        splosaoArray.get(6).setTransparente(true);

        splosaoArray.get(6).loadGLTexture(false);
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


        this.errado = MediaPlayer.create(context, R.raw.errado);
        this.acerto = MediaPlayer.create(context, R.raw.acerto);

        this.errado.setVolume(0.4f, 0.4f);
        this.acerto.setVolume(0.4f, 0.4f);
        this.musica.setVolume(0.2f, 0.2f);


/////////////////////////////////////////
    }

    private void son(int s) {
        ////////EXECUTA OS SONS DO JOGO
        if (perdeu == false) {
            switch (s) {
                case 1:
                    this.errado.start();

                    if (this.errado.isPlaying()) {
                        this.errado.seekTo(0);
                        this.errado.start();

                    } else {
                        this.errado.start();
                    }
                    break;
                case 2:
                    if (this.acerto.isPlaying()) {
                        this.acerto.seekTo(0);
                        this.acerto.start();

                    } else {
                        this.acerto.start();
                    }
                    break;

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

    public boolean calculoarColisaoI(Objeto3d obj, Objeto3d obj2) {
        boolean vai = false;
        double d = Math.sqrt(Math.pow(obj.getPosition().x - (obj2.getPosition().x), 2)
                + Math.pow(obj.getPosition().z - (obj2.getPosition().z), 2)
                + Math.pow(obj.getPosition().y - (obj2.getPosition().y), 2));

        //   Fenix.setPosition( new Vetor3( 0, 18,-62 ));
        if (obj2 == Fenix) {
            if (d <= 0.2f) {
                esplodirNave = true;
            }
        } else if (obj2.getPosition().z > -64) {
            if (d <= 0.2f) {
                for (Objeto3d p : tiros) {
                    if (obj2 == p) {
                        obj.setVida(obj.getVida() - danoNoInimigo);
                        // obj.vezes(0.1f);
                        obj.setImpacto(true);
                        if (obj.getVida() <= 0) {
                            obj.setEsplodirNave(true);

                        }

//                        if (nivelTiro / 12 < 4) {
                        p.setPosition(new Vetor3(-100, -100, -1002));
//                        }
                        break;
                    }
                }
            }
        }

        return vai;
        /////////////////////////////////
    }

    private void esplodirNave(Objeto3d obj) {
        if (obj.isEsplodirNave()) {
            if (obj.getTimeEsplosaoNave() < 26) {

                if (obj.getEsplosaoNaveId() < obj.getSplosaoArrayNave().size())
                    obj.getSplosaoArrayNave().get(obj.getEsplosaoNaveId()).setPosition(new Vetor3(obj.getPosition().x, obj.getPosition().y, obj.getPosition().z));

                if (obj.getTimeEsplosaoNave() % 5 == 0 && obj.getEsplosaoNaveId() < obj.getSplosaoArrayNave().size()) {
                    obj.setEsplosaoNaveId(obj.getEsplosaoNaveId() + 1);
                }
                obj.setTimeEsplosaoNave(obj.getTimeEsplosaoNave() + 1);
            } else {
                obj.setEsplodirNave(false);
                obj.setTimeEsplosaoNave(0);
                obj.setEsplosaoNaveId(0);
                obj.getPosition().setZ(-65);
                obj.getPosition().setX(obj.getPosition().x);
                obj.setVida(obj.getRecoverVida());
                obj.setGiro((float) (0));
                obj.setEsplodiu(true);

                obj.setGiroPosition(new Vetor3(0, 0, 0));

                obj.setTipo("N");
                for (int i = 0; i < tiros.size(); i++) {
                    tiros.get(i).setPosition(new Vetor3(-100, obj.getPosition().y, -100));
                }
                for (int i = 0; i < obj.getSplosaoArrayNave().size(); i++) {
                    obj.getSplosaoArrayNave().get(i).setPosition(new Vetor3(-100, obj.getPosition().y, -100));


                }


            }


        }
        if (obj.isBoss()) {
            horaDoBoss = false;
            ativarBoss[boss.indexOf(obj)] = false;
            timeLine=0;
            carregarCronologia();

        }
        if (obj.getPosition().z > -40) {
            obj.getPosition().setZ(-65);
            obj.getPosition().setX(obj.getPosition().x);
            obj.setVida(obj.getRecoverVida());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void controle() {
        timeLine();
        moverInimigoC(modo, timeModoC, 2, inimigosC);
        moverInimigoC(modoE, timeModoE, 5, inimigosE);
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
            }
        }
        switch (nivelTiro) {
            case 48:
                danoNoInimigo = 4;
                break;
            case 60:
                danoNoInimigo = 6;
                break;
            default:
                danoNoInimigo = 1;
                break;

        }


        if (vida > 0) {
            calculoarColisao(cubo, Fenix);

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


            ceu.setGiro(ceu.getGiro() - 0.06f);
            ceu.setGiroPosition(new Vetor3(ceu.getGiro(), 0, 0));

            moduloY -= veloy;
            modulox -= velox;
            moduloz += veloz;
            //  Fenix.giroTotal((GL11) gl2,girox,1,0,0);
            // Fenix.giroTotal((GL11) gl2,giroy,0,1,0);
            // ceu.setPosition(new Vetor3(0 , 16, -62));


            Fenix.setGiroPosition(new Vetor3(0, 0, giroyFenix));


            for (int i = nivelTiroIndex; i < nivelTiro; i++) {
                if (tiros.get(i).getTime() >= 50 || tiros.get(i).getPosition().z < -64) {
                    tiros.get(i).setMover("nulo");
                    tiros.get(i).setTime(0);
                }
                switch (tiros.get(i).getMover()) {
                    case "nulo":
                        //tiros.get(i).setGiroPosition(new Vetor3(0, 0,0));

                        switch (nivelTiro) {
                            case 12:
                            case 48:
                            case 60:
                                tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX(), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                break;
                            case 24:
                                if (i < 12) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() - 0.1f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else {
                                    tiros.get(i).setPosition(new Vetor3((Fenix.getPosition().getX() + 0.1f), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                }
                                break;
                            case 36:
                                if (i < 12) {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX() - 0.1f, Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else if (i > 12 && i < 24) {
                                    tiros.get(i).setPosition(new Vetor3((Fenix.getPosition().getX() + 0.1f), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                                } else {
                                    tiros.get(i).setPosition(new Vetor3(Fenix.getPosition().getX(), Fenix.getPosition().getY(), (Fenix.getPosition().getZ()) - 0.1f));

                                }
                                break;
                        }
                        break;
                    case "disparar":
                        // tiros.get(i).setGiroPosition(new Vetor3(0, 0,tiros.get(i).getPosition().z+0.2f));

                        tiros.get(i).setPosition(new Vetor3(tiros.get(i).getPosition().getX(),
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
            this.pause = false;
            this.perdeu = true;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                objeto3dxquadro.setTextura(ConvertBitimap.getBitmap(this.wTela, this.h, recordeVelhoD, palavrasConquistadas));
            }
            objeto3dxquadro.loadGLTexture(true);

        }
//////////////////////////////////////////
        if (retornarQuadro) {


            retornarQuadro = false;

        }


    }

    private void timeLine() {
        timeLine++;


        for (int i = 0; i < cronograma.size(); i++) {
            Cronograma c = cronograma.get(i);
            ArrayList<Objeto3d> objeto3ds = new ArrayList<>();
            if (!c.isBoss()) {
                switch (c.getId()) {
                    case 0:
                        objeto3ds = inimigosA;
                        break;
                    case 1:
                        objeto3ds = inimigosB;
                        break;
                    case 2:
                        objeto3ds = inimigosC;
                        break;
                    case 3:
                        objeto3ds = inimigosD;
                        break;
                    case 4:
                        objeto3ds = asteroide;
                        break;
                    case 5:
                        objeto3ds = inimigosE;
                        break;

                }
                if ( c.getTimeIN()<timeLine && c.getTimeOUT() > timeLine  ) {
                    c.setEmMovimento(true);
                    c.setAvaliar(true);
                    vaiPraCena[c.getId()] = true;
                    for (Objeto3d o : objeto3ds) {
                        o.setEsplodiu(false);

                    }

                    if (timeLine % c.getTimeMode() == 0 && c.isPerpetuo()) {
                        switch (c.getId()) {
                            case 2:
                                modo = c.getModo();
                                int m = c.getModo() + 1;
                                if (m < 5) {
                                    cronograma.get(i).setModo(m);
                                } else {
                                    cronograma.get(i).setModo(0);

                                }

                                break;
                            case 5:
                                modoE = c.getModo();
                                int me = c.getModo() + 1;
                                if (me < 5) {
                                    cronograma.get(i).setModo(me);
                                } else {
                                    cronograma.get(i).setModo(0);

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
                            case 5:
                                modoE = c.getModo();
                                break;
                        }
                    }
                } else if (c.isEmMovimento()) {

                    //
                    vaiPraCena[c.getId()] = false;

                    for (Objeto3d o : objeto3ds) {
                        o.setEsplodirNave(true);
                        o.setVida(0);
                        o.setTimeEsplosaoNave(0);
                        //     esplodirNave(o);
                        //  o.getPosition().setZ(-65);
                        if (o.getTiroNave() != null) {
                            for (Objeto3d oo : o.getTiroNave()) {
                                oo.getPosition().setZ(-65);
                            }
                        }
                    }
                    c.setEmMovimento(false);
                    cronograma.remove(c);
                    break;
                }

            } else {
                if (timeLine > 10000) {
                    objeto3ds = boss;
                    horaDoBoss = true;
                    ativarBoss[c.getId()] = true;
                }

            }


        }


        if (!horaDoBoss) {
            for (int p = 0; p < inimigosC.size(); p++) {

                if (inimigosC.get(p).getVida() <= 0) {
                    esplodirNave(inimigosC.get(p));
                }

                if (inimigosE.get(p).getVida() <= 0) {
                    esplodirNave(inimigosE.get(p));
                }
                if (p < inimigosA.size()) {

                    if (inimigosA.get(p).getVida() <= 0)
                        esplodirNave(inimigosA.get(p));


                    if (inimigosD.get(p).getVida() <= 0)
                        esplodirNave(inimigosD.get(p));


                    if (inimigosB.get(p).getVida() <= 0)
                        esplodirNave(inimigosB.get(p));


                    if (asteroide.get(p).getVida() <= 0)
                        esplodirNave(asteroide.get(p));

                    calculoarColisaoI(inimigosB.get(p), Fenix);
                    calculoarColisaoI(inimigosA.get(p), Fenix);
                    calculoarColisaoI(inimigosD.get(p), Fenix);
                    calculoarColisaoI(asteroide.get(p), Fenix);


                    for (Objeto3d pp : tiros) {
                        calculoarColisaoI(inimigosA.get(p), pp);
                        calculoarColisaoI(inimigosB.get(p), pp);
                        calculoarColisaoI(inimigosD.get(p), pp);
                        calculoarColisaoI(asteroide.get(p), pp);

                    }
                    for (Objeto3d pp : inimigosB.get(p).getTiroNave()) {
                        calculoarColisaoI(pp, Fenix);
                    }
                    for (Objeto3d pp : inimigosD.get(p).getTiroNave()) {
                        calculoarColisaoI(pp, Fenix);
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
                    moverInimigoD(0, 0, giroybolhas, p);
                    moverInimigoB(modoB, timeModoB, giroybolhas, p);
                    moverInimigoA(0, 0, giroybolhas, p);
                    moverAsteroide(0, 0, giroybolhas, p);


                }

            }

        } else {

            if (boss.get(0).getVida() <= 0) {
                esplodirNave(boss.get(0));
            }

            for (Objeto3d pp : tiros) {
                calculoarColisaoI(boss.get(0), pp);
            }

            moverBoss(modoBoss, timeModoBoss, 0, boss.get(0), 0);


        }

    }

    private void moverBoss(int modoBoss, int time, int i, Objeto3d pp, int index) {

        if (ativarBoss[index]) {

            if (time >= 100) {

                timeModoBoss = 0;
                if (this.dr < direcaoX.length) {
                    this.modoBoss = direcaoX[this.dr];
                    this.dr++;
                } else {
                    this.dr = 0;
                    this.modoBoss = direcaoX[this.dr];

                }
                time = 0;
                return;

            }

            pp.setGiro(0);
            for (Objeto3d ppp : pp.getTiroNave()) {
                calculoarColisaoI(ppp, Fenix);
            }
            if (true) {
                pp.atirando(Fenix, velocidade * 2, true);
                switch (this.modoBoss) {
                    case 0:
                        pp.setPosition(new Vetor3(pp.getPosition().x, pp.getPosition().getY(), pp.getPosition().z + velocidade));

                        break;
                    case 1:
                        pp.setPosition(new Vetor3(pp.getPosition().x, pp.getPosition().getY(), pp.getPosition().z - velocidade));

                        break;
                    case 2:
                        pp.setPosition(new Vetor3(pp.getPosition().x + velocidade, pp.getPosition().getY(), pp.getPosition().z));

                        break;
                    case 3:
                        pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z));

                        break;
                    case 4:
                        pp.setPosition(new Vetor3(pp.getPosition().x + velocidade, pp.getPosition().getY(), pp.getPosition().z + velocidade));

                        break;
                    case 5:
                        pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z + velocidade));

                        break;
                    case 6:
                        pp.setPosition(new Vetor3(pp.getPosition().x + velocidade, pp.getPosition().getY(), pp.getPosition().z - velocidade));

                        break;
                    case 7:
                        pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z - velocidade));

                        break;
                }

            }
            timeModoBoss++;
        }

    }


    public void moverInimigoA(int modoB, int timeB, float giroybolhas, int p) {

        if (vaiPraCena[0]) {
            inimigosA.get(p).setGiro(giroybolhas);
            inimigosA.get(p).setPosition(new Vetor3(inimigosA.get(p).getPosition().x, inimigosA.get(p).getPosition().getY(), inimigosA.get(p).getPosition().z + velocidade));
            for (Objeto3d pp : inimigosA.get(p).getTiroNave()) {
                calculoarColisaoI(pp, Fenix);
            }
            if (inimigosA.get(p).getPosition().z > -58) {
                for (Objeto3d pp : inimigosA.get(p).getTiroNave()) {
                    inimigosA.get(p).setAtirando(false);
                    inimigosA.get(p).setAtirar(0, false);
                    pp.setPosition(new Vetor3(inimigosA.get(p).getPosition().x, distancia + 0.06f, inimigosA.get(p).getPosition().z));
                }
            } else {
                inimigosA.get(p).atirando(Fenix, velocidade * 2, true);

            }
            if (inimigosA.get(p).getPosition().z > -58) {
                inimigosA.get(0).setPosition(new Vetor3(-0.2f,
                        distancia + 0.06f, -67));
                inimigosA.get(1).setPosition(new Vetor3(0.3f,
                        distancia + 0.06f, -67));
                inimigosA.get(2).setPosition(new Vetor3(0.0f,
                        distancia + 0.06f, -69));


                proximoInimigo(0);
            }

        }
    }

    private void proximoInimigo(int i) {

        vaiPraCena[i] = false;
//        vaiPraCena[faseInit[proximoIni]]=true;
//        if(proximoIni<faseInit.length)
//               proximoIni++;
    }


    public void moverInimigoB(int modo, int time, float giroybolhas, int p) {
        if (vaiPraCena[1]) {


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
                                distancia, -65));

                        inimigosB.get(1).setPosition(new Vetor3(0.3f,
                                distancia, -65));

                        inimigosB.get(2).setPosition(new Vetor3(0.0f,
                                distancia, -64));
                        inimigosB.get(0).setTipo("B0");
                        inimigosB.get(1).setTipo("B0");
                        inimigosB.get(2).setTipo("B0");
                        break;
                    case 1:
                        inimigosB.get(0).setPosition(new Vetor3(-1f,
                                distancia, -62.5f));

                        inimigosB.get(1).setPosition(new Vetor3(-1.5f,
                                distancia, -62.5f));

                        inimigosB.get(2).setPosition(new Vetor3(-2f,
                                distancia, -62.5f));

                        for (int i = 0; i < inimigosB.size(); i++) {
                            inimigosB.get(i).setTipo("B1");
                            inimigosB.get(i).setGiro(90f);
                            inimigosB.get(i).setGiroPosition(new Vetor3(0, inimigosB.get(i).getGiro(), 0));

                        }
                        break;
                    case 2:
                        inimigosB.get(0).setPosition(new Vetor3(1f,
                                distancia, -62.5f));

                        inimigosB.get(1).setPosition(new Vetor3(1.5f,
                                distancia, -62.5f));

                        inimigosB.get(2).setPosition(new Vetor3(2f,
                                distancia, -62.5f));

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
                    inimigosB.get(p).atirando(Fenix, velocidade * 2, true);

                    if (inimigosB.get(p).getPosition().z > -58 && p == inimigosB.size() - 1) {
                        inimigosB.get(0).setPosition(new Vetor3(-0.3f,
                                distancia, -65));

                        inimigosB.get(1).setPosition(new Vetor3(0.3f,
                                distancia, -65));

                        inimigosB.get(2).setPosition(new Vetor3(0.0f,
                                distancia, -64));
                        proximoInimigo(1);
                    }
                    break;
                case 1:
                    inimigosB.get(p).setPosition(new Vetor3(inimigosB.get(p).getPosition().x + velocidade, inimigosB.get(p).getPosition().getY(), inimigosB.get(p).getPosition().z));
                    inimigosB.get(p).atirando(Fenix, velocidade * 2, true);

                    if ((inimigosB.get(p).getPosition().z > -58 || inimigosB.get(p).getPosition().x > 1) && p == inimigosB.size() - 1) {
                        inimigosB.get(0).setPosition(new Vetor3(-0.3f,
                                distancia, -65));

                        inimigosB.get(1).setPosition(new Vetor3(0.3f,
                                distancia, -65));

                        inimigosB.get(2).setPosition(new Vetor3(0.0f,
                                distancia, -64));
                        proximoInimigo(1);
                    }
                    break;
                case 2:
                    inimigosB.get(p).setPosition(new Vetor3(inimigosB.get(p).getPosition().x - velocidade, inimigosB.get(p).getPosition().getY(), inimigosB.get(p).getPosition().z));
                    inimigosB.get(p).atirando(Fenix, velocidade * 2, true);

                    if ((inimigosB.get(p).getPosition().z > -58 || inimigosB.get(p).getPosition().x < -1) && p == inimigosB.size() - 1) {
                        inimigosB.get(0).setPosition(new Vetor3(-0.3f,
                                distancia, -65));

                        inimigosB.get(1).setPosition(new Vetor3(0.3f,
                                distancia, -65));

                        inimigosB.get(2).setPosition(new Vetor3(0.0f,
                                distancia, -64));
                        proximoInimigo(1);
                    }
                    break;
            }


            timeModoB++;

        }
    }


    public void moverInimigoD(int modoB, int timeB, float giroybolhas, int p) {
        if (vaiPraCena[3] && p < inimigosD.size()) {
            inimigosD.get(p).setGiro(giroybolhas);
            inimigosD.get(p).setPosition(new Vetor3(inimigosD.get(p).getPosition().x, inimigosD.get(p).getPosition().getY(), inimigosD.get(p).getPosition().z + (velocidade * 0.65f)));
            inimigosD.get(p).atirando(Fenix, velocidade * 3, false);

            if (inimigosD.get(p).getPosition().z > -58) {

                inimigosD.get(p).setPosition(new Vetor3(inimigosD.get(p).getPosition().x,
                        distancia + 0.03f, -65));


                proximoInimigo(3);

            }
        }
    }


    public void moverAsteroide(int modoB, int timeB, float giroybolhas, int p) {
        if (vaiPraCena[4] && p < asteroide.size()) {
            asteroide.get(p).setGiro(giroybolhas);
            asteroide.get(p).setPosition(new Vetor3(asteroide.get(p).getPosition().x, asteroide.get(p).getPosition().getY(), asteroide.get(p).getPosition().z + velocidade));

            if (asteroide.get(p).getPosition().z > -58 && p == asteroide.size() - 1) {
                float[] vz = {- -65, -65.5f, -66};
                asteroide.get(0).setPosition(new Vetor3(-0.5f,
                        distancia, vz[(int) (Math.random() * 3)]));

                asteroide.get(1).setPosition(new Vetor3(0.0f,
                        distancia, vz[(int) (Math.random() * 3)]));

                asteroide.get(2).setPosition(new Vetor3(0.5f,
                        distancia, vz[(int) (Math.random() * 3)]));
                proximoInimigo(4);
            }
        }
    }

    public void moverInimigoC(int modo, int time, int cena, ArrayList<Objeto3d> objArray) {
        if (vaiPraCena[cena]) {
            if (time >= 1000) {

                timeModoE = 0;
                timeModoC = 0;

                time = 0;
                proximoInimigo(cena);
                return;

            }

            if (time == 0) {
                switch (modo) {
                    case 0:
                        for (int i = 0; i < objArray.size(); i++) {
                            objArray.get(i).setPosition(new Vetor3(-1.0f, distancia, -63f));
                            objArray.get(i).setTipo("C");

                        }
                        break;
                    case 1:
                        for (int i = 0; i < objArray.size(); i++) {
                            objArray.get(i).setPosition(new Vetor3(1.0f, distancia, -63f));
                            objArray.get(i).setTipo("D");

                        }
                        break;
                    case 2:
                        for (int i = 0; i < objArray.size(); i++) {
                            objArray.get(i).setPosition(new Vetor3(1.0f, distancia, -63.3f));
                            objArray.get(i).setTipo("E");

                        }
                        break;

                    case 3:
                        for (int i = 0; i < objArray.size(); i++) {
                            objArray.get(i).setPosition(new Vetor3(-1.0f, distancia, -63.3f));
                            objArray.get(i).setTipo("F");

                        }
                        break;
                    case 4:
                        objArray.get(0).setTipo("G");
                        objArray.get(1).setTipo("G");
                        objArray.get(2).setTipo("G");
                        objArray.get(3).setTipo("G");
                        objArray.get(4).setTipo("G");
                        objArray.get(5).setTipo("G");
                        objArray.get(6).setTipo("G");
                        objArray.get(0).setPosition(new Vetor3(-0.3f, distancia, -65.8f));
                        objArray.get(1).setPosition(new Vetor3(0.3f, distancia, -65.8f));
                        objArray.get(2).setPosition(new Vetor3(-0.1f, distancia, -65.5f));
                        objArray.get(3).setPosition(new Vetor3(0.1f, distancia, -65.5f));

                        objArray.get(4).setPosition(new Vetor3(0.35f, distancia, -66.1f));
                        objArray.get(5).setPosition(new Vetor3(0.0f, distancia, -65.8f));
                        objArray.get(6).setPosition(new Vetor3(-0.35f, distancia, -66.1f));

                        for (int i = 0; i < objArray.size(); i++) {
                            objArray.get(i).setVida(1f);
                            for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                objArray.get(i).setAtirando(false);
                                objArray.get(i).setAtirar(0, false);
                                pp.setPosition(new Vetor3(objArray.get(i).getPosition().x, distancia, objArray.get(i).getPosition().z));

                            }
                        }

                        break;
                }
            } else {

                switch (modo) {
                    case 0:
                        for (int i = 0; i < objArray.size(); i++) {


                            if (objArray.get(i).getPosition().x > 0)
                                objArray.get(i).setAtirando(true);

                            objArray.get(i).atirandoC(Fenix, velocidade * 2);

                            calculoarColisaoI(objArray.get(i), Fenix);
                            for (Objeto3d pp : tiros) {
                                calculoarColisaoI(objArray.get(i), pp);
                            }
                            for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                calculoarColisaoI(pp, Fenix);
                            }


                            if (time > i * 70 && objArray.get(i).getTipo().equals("C")) {
                                float x = objArray.get(i).getPosition().x;
                                float z = objArray.get(i).getPosition().z;
                                double distancia = calculoarDistancia(objArray.get(i), new Vetor3(0, this.distancia, -61.5f));
                                if (x < 0.4f) {
                                    objArray.get(i).setGiro((float) (objArray.get(i).getGiro() + distancia));
                                }

                                objArray.get(i).setGiroPosition(new Vetor3(0, objArray.get(i).getGiro(), 0));
                                if (x < 0) {
                                    x += distancia * 0.01;
                                    z += distancia * (0.01 * distancia);

                                } else {
                                    x += distancia * 0.01;
                                    z -= distancia * (0.01 * distancia);

                                }

                                objArray.get(i).setPosition(new Vetor3(x, objArray.get(i).getPosition().getY(), z));

                                if (objArray.get(i).getPosition().z < -65.5) {
                                    objArray.get(i).setPosition(new Vetor3(-1.0f, this.distancia, -63));
                                    objArray.get(i).setGiro((float) (0));
                                    objArray.get(i).setGiroPosition(new Vetor3(0, 0, 0));

                                    objArray.get(i).setTipo("N");

                                }

                                for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                    float xx = pp.getPosition().x;
                                    if (pp.getPosition().z > -58 && (xx < -2 || xx > 2)) {
                                        objArray.get(i).setAtirando(false);
                                        objArray.get(i).setAtirar(0, false);
                                        pp.setPosition(objArray.get(i).getPosition());

                                    }
                                }


                            }
                        }

                        break;


                    case 1:
                        for (int i = 0; i < objArray.size(); i++) {


                            if (objArray.get(i).getPosition().x < 0)
                                objArray.get(i).setAtirando(true);

                            objArray.get(i).atirandoC(Fenix, velocidade * 2);

                            calculoarColisaoI(objArray.get(i), Fenix);
                            for (Objeto3d pp : tiros) {
                                calculoarColisaoI(objArray.get(i), pp);
                            }
                            for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                calculoarColisaoI(pp, Fenix);
                            }


                            if (time > i * 70 && objArray.get(i).getTipo().equals("D")) {
                                float x = objArray.get(i).getPosition().x;
                                float z = objArray.get(i).getPosition().z;
                                double distancia = calculoarDistancia(objArray.get(i), new Vetor3(0, this.distancia, -61.5f));
                                if (x > -0.4f) {
                                    objArray.get(i).setGiro((float) (objArray.get(i).getGiro() - distancia));
                                }

                                objArray.get(i).setGiroPosition(new Vetor3(0, objArray.get(i).getGiro(), 0));
                                if (x > 0) {
                                    x -= distancia * 0.01;
                                    z += distancia * (0.01 * distancia);

                                } else {
                                    x -= distancia * 0.01;
                                    z -= distancia * (0.01 * distancia);

                                }

                                objArray.get(i).setPosition(new Vetor3(x, objArray.get(i).getPosition().getY(), z));

                                if (objArray.get(i).getPosition().z < -65.5) {
                                    objArray.get(i).setPosition(new Vetor3(1.0f, this.distancia, -63));
                                    objArray.get(i).setGiro((float) (0));
                                    objArray.get(i).setGiroPosition(new Vetor3(0, 0, 0));

                                    objArray.get(i).setTipo("N");

                                }

                                for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                    float xx = pp.getPosition().x;
                                    if (pp.getPosition().z > -58 && (xx < -2 || xx > 2)) {
                                        objArray.get(i).setAtirando(false);
                                        objArray.get(i).setAtirar(0, false);
                                        pp.setPosition(objArray.get(i).getPosition());


                                    }
                                }
                            }
                        }

                        break;

                    case 2:
                        for (int i = 0; i < objArray.size(); i++) {


                            calculoarColisaoI(objArray.get(i), Fenix);
                            for (Objeto3d pp : tiros) {
                                calculoarColisaoI(objArray.get(i), pp);
                            }
                            for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                calculoarColisaoI(pp, Fenix);
                            }


                            if (time > i * 50 && objArray.get(i).getTipo().equals("E")) {
                                float x = objArray.get(i).getPosition().x;
                                float z = objArray.get(i).getPosition().z;
                                Vetor3 v = new Vetor3(-1, this.distancia, -60f);
                                double distancia = calculoarDistancia(objArray.get(i), v);

                                objArray.get(i).setGiro(315f);
                                objArray.get(i).setGiroPosition(new Vetor3(0, objArray.get(i).getGiro(), 0));
                                if (distancia > 0) {
                                    x -= velocidade * 2;
                                    z += velocidade * 2;

                                }

                                objArray.get(i).setPosition(new Vetor3(x, objArray.get(i).getPosition().getY(), z));

                                if (objArray.get(i).getPosition().z >= -59) {
                                    objArray.get(i).setPosition(new Vetor3(1.0f, this.distancia, -63.3f));
                                    objArray.get(i).setGiro((float) (0));
                                    objArray.get(i).setGiroPosition(new Vetor3(0, 0, 0));

                                    objArray.get(i).setTipo("N");
                                    objArray.get(i).setAtirando(false);
                                    objArray.get(i).setAtirar(0, false);
                                    for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                        pp.setPosition(new Vetor3(-100, this.distancia, -61.5f));
                                    }
                                }
                            }
                        }

                        break;

                    case 3:
                        for (int i = 0; i < objArray.size(); i++) {


                            calculoarColisaoI(objArray.get(i), Fenix);
                            for (Objeto3d pp : tiros) {
                                calculoarColisaoI(objArray.get(i), pp);
                            }
                            for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                calculoarColisaoI(pp, Fenix);
                            }


                            if (time > i * 50 && objArray.get(i).getTipo().equals("F")) {
                                float x = objArray.get(i).getPosition().x;
                                float z = objArray.get(i).getPosition().z;
                                Vetor3 v = new Vetor3(1, this.distancia, -60f);
                                double distancia = calculoarDistancia(objArray.get(i), v);

                                objArray.get(i).setGiro(45f);
                                objArray.get(i).setGiroPosition(new Vetor3(0, objArray.get(i).getGiro(), 0));
                                if (distancia > 0) {
                                    x += velocidade * 2;
                                    z += velocidade * 2;

                                }

                                objArray.get(i).setPosition(new Vetor3(x, objArray.get(i).getPosition().getY(), z));

                                if (objArray.get(i).getPosition().z >= -59) {
                                    objArray.get(i).setPosition(new Vetor3(-1.0f, this.distancia, -63.3f));
                                    objArray.get(i).setGiro((float) (0));
                                    objArray.get(i).setGiroPosition(new Vetor3(0, 0, 0));

                                    objArray.get(i).setTipo("N");
                                    objArray.get(i).setAtirando(false);
                                    objArray.get(i).setAtirar(0, false);
                                    for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                        pp.setPosition(new Vetor3(-100, this.distancia, -61.5f));
                                    }
                                }
                            }
                        }

                        break;


                    case 4:
                        for (int i = 0; i < objArray.size(); i++) {
                            objArray.get(i).atirandoC(Fenix, velocidade * 3);


                            calculoarColisaoI(objArray.get(i), Fenix);
                            for (Objeto3d pp : tiros) {
                                calculoarColisaoI(objArray.get(i), pp);
                            }
                            for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                calculoarColisaoI(pp, Fenix);
                            }


                            if (objArray.get(i).getTipo().equals("G")) {
                                float x = objArray.get(i).getPosition().x;
                                float z = objArray.get(i).getPosition().z;

                                z += velocidade * 2;

                                objArray.get(i).setPosition(new Vetor3(x, objArray.get(i).getPosition().getY(), z));

                                if (objArray.get(i).getPosition().z >= -59) {
                                    objArray.get(i).setPosition(new Vetor3(1.0f, this.distancia, -65));
                                    objArray.get(i).setGiro((float) (0));
                                    objArray.get(i).setGiroPosition(new Vetor3(0, 0, 0));

                                    objArray.get(i).setTipo("N");

                                }

                                for (Objeto3d pp : objArray.get(i).getTiroNave()) {
                                    float xx = pp.getPosition().x;
                                    if (pp.getPosition().z > -59 || xx < -2 || xx > 2) {
                                        objArray.get(i).setAtirando(false);
                                        objArray.get(i).setAtirar(0, false);
                                        pp.setPosition(new Vetor3(objArray.get(i).getPosition().x, this.distancia, -65));
                                    }

                                    if (pp.getPosition().z > -63f)
                                        objArray.get(i).setAtirando(true);


                                }
                            }
                        }

                        break;

                }
            }
            if (objArray.equals(inimigosC)) {
                timeModoC++;

            } else {
                timeModoE++;

            }
        }


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
        barra.loadGLTexture(true);

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
            splosaoArrayNave.get(esplosaoNaveId).draw((GL11) gl2);
            cubo.draw((GL11) gl2);
            for (int i = 0; i < tiros.size(); i++) {

                if (!tiros.get(i).getMover().equals("nulo")) {
                    tiros.get(i).draw((GL11) gl2);
                }


            }

            if (!horaDoBoss) {
                for (int p = 0; p < inimigosC.size(); p++) {
                    if (inimigosC.get(p).getPassou().equals("N") && inimigosC.get(p).isTroca() == false) {
                        if (!inimigosC.get(p).isEsplodirNave()) {
                            inimigosC.get(p).draw((GL11) gl2);

                            for (int pp = 0; pp < inimigosC.get(p).getTiroNave().size(); pp++) {
                                inimigosC.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                inimigosC.get(p).getTiroNave().get(pp).setImpacto(false);

                            }

                        } else {
                            if (inimigosC.get(p).getEsplosaoNaveId() < inimigosC.get(p).getSplosaoArrayNave().size()) {
                                inimigosC.get(p).getSplosaoArrayNave().get(inimigosC.get(p).getEsplosaoNaveId()).draw((GL11) gl2);
                                inimigosC.get(p).getSplosaoArrayNave().get(inimigosC.get(p).getEsplosaoNaveId()).setImpacto(false);
                            }
                            for (int pp = 0; pp < inimigosC.get(p).getTiroNave().size(); pp++) {
                                inimigosC.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                inimigosC.get(p).getTiroNave().get(pp).setImpacto(false);

                            }
                        }
                        inimigosC.get(p).setImpacto(false);
                    }


                    if (inimigosE.get(p).getPassou().equals("N") && inimigosE.get(p).isTroca() == false) {
                        if (!inimigosE.get(p).isEsplodirNave()) {
                            inimigosE.get(p).draw((GL11) gl2);

                            for (int pp = 0; pp < inimigosE.get(p).getTiroNave().size(); pp++) {
                                inimigosE.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                inimigosE.get(p).getTiroNave().get(pp).setImpacto(false);

                            }

                        } else {
                            if (inimigosE.get(p).getEsplosaoNaveId() < inimigosE.get(p).getSplosaoArrayNave().size()) {
                                inimigosE.get(p).getSplosaoArrayNave().get(inimigosE.get(p).getEsplosaoNaveId()).draw((GL11) gl2);
                                inimigosE.get(p).getSplosaoArrayNave().get(inimigosE.get(p).getEsplosaoNaveId()).setImpacto(false);
                            }
                            for (int pp = 0; pp < inimigosE.get(p).getTiroNave().size(); pp++) {
                                inimigosE.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                inimigosE.get(p).getTiroNave().get(pp).setImpacto(false);

                            }
                        }
                        inimigosE.get(p).setImpacto(false);
                    }


                    try {

                        if (p < inimigosB.size()) {
                            if (inimigosB.get(p).getPassou().equals("N") && inimigosB.get(p).isTroca() == false) {
                                if (!inimigosB.get(p).isEsplodirNave()) {
                                    inimigosB.get(p).draw((GL11) gl2);

                                    for (int pp = 0; pp < inimigosB.get(p).getTiroNave().size(); pp++) {
                                        inimigosB.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                        inimigosB.get(p).getTiroNave().get(pp).setImpacto(false);
                                    }

                                } else {
                                    if (inimigosB.get(p).getEsplosaoNaveId() < inimigosB.get(p).getSplosaoArrayNave().size()) {
                                        inimigosB.get(p).getSplosaoArrayNave().get(inimigosB.get(p).getEsplosaoNaveId()).draw((GL11) gl2);
                                        inimigosB.get(p).getSplosaoArrayNave().get(inimigosB.get(p).getEsplosaoNaveId()).setImpacto(false);
                                    }
                                    for (int pp = 0; pp < inimigosB.get(p).getTiroNave().size(); pp++) {
                                        inimigosB.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                        inimigosB.get(p).getTiroNave().get(pp).setImpacto(false);
                                    }
                                }

                            }


                            if (inimigosA.get(p).getPassou().equals("N") && inimigosA.get(p).isTroca() == false) {
                                if (!inimigosA.get(p).isEsplodirNave()) {
                                    inimigosA.get(p).draw((GL11) gl2);

                                    for (int pp = 0; pp < inimigosA.get(p).getTiroNave().size(); pp++) {
                                        inimigosA.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                        inimigosA.get(p).getTiroNave().get(pp).setImpacto(false);
                                    }

                                } else {
                                    if (inimigosA.get(p).getEsplosaoNaveId() < inimigosA.get(p).getSplosaoArrayNave().size()) {
                                        inimigosA.get(p).getSplosaoArrayNave().get(inimigosA.get(p).getEsplosaoNaveId()).draw((GL11) gl2);
                                        inimigosA.get(p).getSplosaoArrayNave().get(inimigosA.get(p).getEsplosaoNaveId()).setImpacto(false);
                                    }

                                    for (int pp = 0; pp < inimigosA.get(p).getTiroNave().size(); pp++) {
                                        inimigosA.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                        inimigosA.get(p).getTiroNave().get(pp).setImpacto(false);
                                    }
                                }

                            }
                            if (inimigosD.get(p).getPassou().equals("N") && inimigosD.get(p).isTroca() == false) {
                                if (!inimigosD.get(p).isEsplodirNave()) {
                                    inimigosD.get(p).draw((GL11) gl2);

                                    for (int pp = 0; pp < inimigosD.get(p).getTiroNave().size(); pp++) {
                                        inimigosD.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                        inimigosD.get(p).getTiroNave().get(pp).setImpacto(false);
                                    }

                                } else {
                                    if (inimigosD.get(p).getEsplosaoNaveId() < inimigosD.get(p).getSplosaoArrayNave().size()) {
                                        inimigosD.get(p).getSplosaoArrayNave().get(inimigosD.get(p).getEsplosaoNaveId()).draw((GL11) gl2);
                                        inimigosD.get(p).getSplosaoArrayNave().get(inimigosD.get(p).getEsplosaoNaveId()).setImpacto(false);
                                    }
                                    for (int pp = 0; pp < inimigosD.get(p).getTiroNave().size(); pp++) {
                                        inimigosD.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                        inimigosD.get(p).getTiroNave().get(pp).setImpacto(false);
                                    }
                                }

                            }


                            if (asteroide.get(p).getPassou().equals("N") && asteroide.get(p).isTroca() == false) {
                                if (!asteroide.get(p).isEsplodirNave()) {
                                    asteroide.get(p).draw((GL11) gl2);

                                } else {
                                    if (asteroide.get(p).getEsplosaoNaveId() < asteroide.get(p).getSplosaoArrayNave().size()) {
                                        asteroide.get(p).getSplosaoArrayNave().get(asteroide.get(p).getEsplosaoNaveId()).draw((GL11) gl2);
                                        asteroide.get(p).getSplosaoArrayNave().get(asteroide.get(p).getEsplosaoNaveId()).setImpacto(false);
                                    }
                                }

                            }

                            inimigosA.get(p).setImpacto(false);
                            inimigosB.get(p).setImpacto(false);
                            inimigosD.get(p).setImpacto(false);
                            asteroide.get(p).setImpacto(false);

                        }

                    } catch (Exception e) {
                        p = 0;
                    }
                }
            } else {

                for (int p = 0; p < boss.size(); p++) {
                    if (boss.get(p).getPassou().equals("N") && boss.get(p).isTroca() == false) {
                        if (!boss.get(p).isEsplodirNave()) {
                            boss.get(p).draw((GL11) gl2);

                            for (int pp = 0; pp < boss.get(p).getTiroNave().size(); pp++) {
                                boss.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                boss.get(p).getTiroNave().get(pp).setImpacto(false);

                            }

                        } else {
                            if (boss.get(p).getEsplosaoNaveId() < boss.get(p).getSplosaoArrayNave().size()) {
                                boss.get(p).getSplosaoArrayNave().get(boss.get(p).getEsplosaoNaveId()).draw((GL11) gl2);
                                boss.get(p).getSplosaoArrayNave().get(boss.get(p).getEsplosaoNaveId()).setImpacto(false);
                            }
                            for (int pp = 0; pp < boss.get(p).getTiroNave().size(); pp++) {
                                boss.get(p).getTiroNave().get(pp).draw((GL11) gl2);
                                boss.get(p).getTiroNave().get(pp).setImpacto(false);

                            }
                        }
                        boss.get(p).setImpacto(false);
                    }
                }

            }

            gl.glPopMatrix();

            gl.glLoadIdentity();

//            gl.glRotatef( (giroy)*-1, 0, 1, 0 );
//
            // pontoDoEixozTela+=0.02;
            gl.glRotatef(85, 1, 0, 0);
//globo


            gl.glTranslatef(0, -16.5f, pontoDoEixozTela);


            controle();
            if (this.pause) {
            } else if (this.pause == false) {

                gl.glTranslatef(0, -2, -3);
                objeto3dxquadro.draw((GL11) gl2);
            }


        } else if (carregado == false) {
            gl.glLoadIdentity();

            gl.glTranslatef(0, 0, -10);
            if (carga >= 13) {
                //   gl.glClearColor(0.4f, 0.1f, 0.1f, 0.1f );
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    quadroInserirPalavra.setTextura(ConvertBitimap.getBitmapBolha("   COMEÇAR", 70, 3));
                }
                quadroInserirPalavra.loadGLTexture(true);
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


        if (this.xx + this.xr < this.w * 0.23 && this.xx + this.xr > (this.w * 0.23) * -1) {
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
                nivelTiroIndex = 36;
                for (int i = 0; i < 36; i++) {
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
        //aumentarNivelTiro(5);

        if (tempoDisparo >= 3) {
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

                        tempoDisparo = 0;
                        tiros.get(idDisparo4).setMover("disparar");
                        if (idDisparoQTD < 11) {
                            idDisparo4++;
                            idDisparoQTD++;
                        } else {
                            idDisparo4 = 36;
                            idDisparoQTD = 0;
                        }
                        break;

                    case 60:

                        tempoDisparo = 0;

                        tiros.get(idDisparo5).setMover("disparar");
                        if (idDisparoQTD < 11 && idDisparo5 < 60) {
                            idDisparo5++;
                            idDisparoQTD++;
                        } else {
                            idDisparo5 = 48;
                            idDisparoQTD = 0;
                        }


                        break;

                }

            }
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
