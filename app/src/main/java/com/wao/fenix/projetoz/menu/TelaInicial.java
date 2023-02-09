package com.wao.fenix.projetoz.menu;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Build;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.wao.fenix.R;
import com.wao.fenix.projetoz.dao.BDEstatusFase;
import com.wao.fenix.projetoz.generico.recursos.Alerta;
import com.wao.fenix.projetoz.generico.recursos.ConvertBitimap;
import com.wao.fenix.projetoz.generico.recursos.Objeto3d;
import com.wao.fenix.projetoz.generico.recursos.SelecteControll;
import com.wao.fenix.projetoz.generico.recursos.Vetor3;
import com.wao.fenix.projetoz.modelo.EstatusFase;

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

    private boolean selectFase = true;
    private boolean comMusica = true;
    private boolean comSons = true;

    private TartarugaCorrida tut;


    private TelaResultados result;

    private float pontoDoEixoYInicio = 0;
    private float pontoDoEixoXInicio = 0;
    private int recolher = 1;
    private boolean iniciarTelaDeSelecao = true;


    private GL10 gl2;
    private int fasecarregada = 0;

    private int musicas = 0;
    private boolean musicaBoss = false;
    private boolean musicaInicioFase = false;

    //private Objeto3d quadroInserirPalavra;
    private Objeto3d bolhaRef;
    private Objeto3d btUpgrade;
    private Objeto3d btfundo;
    private Objeto3d btfundoup;
    private boolean iniciaraProxima = false;

    private Objeto3d btoptions;
    private Objeto3d btStart;


    private ArrayList<Objeto3d> bolhas;
    private ArrayList<Objeto3d> niveis;


    private AssetManager asset;


    private String tipo = "brinquedo";

    private MediaPlayer musica;

    private boolean regularSon = true;

    private boolean pause = true;


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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TelaInicial(Context context, AssetManager asset) throws IOException {

        //////////////INICIA VARIAVEIS BASICAS
        this.context = context;
        this.asset = asset;

        this.displayMetrics = context.getResources().getDisplayMetrics();
        this.h = this.displayMetrics.heightPixels;
        this.w = 720;
        this.wTela = this.displayMetrics.widthPixels;

        this.musica = MediaPlayer.create(context, R.raw.musica);
        this.musica.setLooping(true);
        this.musica.setVolume(0.4f, 0.4f);
        this.musica.start();

        /////////////////////////////////////////////////////
        faseInit = new int[]{0, 1, 2, 3, 4, 5, 2, 3, 4, 1, 5, 0, 2};


    }


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

                bolhas = new ArrayList<>();

                this.telaIntro = new Objeto3d(context, R.drawable.basenorm, asset, "intro.obj", R.drawable.fenixintro, new Vetor3(1f, 1f, 1f), "");
                this.telaIntro.setMudarTamanho(true);
                this.telaIntro.setPosition(new Vetor3(0, 0.02f, -0.9f));
                this.telaIntro.vezes(1.5f);
                this.telaIntro.setGiroPosition(new Vetor3(90, 0f, 0f));
                this.telaIntro.loadGLTexture();


                carga++;
                break;

            case 1:
                this.barra = new Objeto3d(context, R.drawable.basenorm, asset, "tiroc.obj", R.drawable.espacodd, new Vetor3(1f, 1f, 1f), "");
                this.barra.setMudarTamanho(true);
                this.barra.setPosition(new Vetor3(0, -0.05f, -0.9f));
                this.barra.vezes(50);
                this.barra.setGiroPosition(new Vetor3(95, 0f, 0f));
                this.barra.loadGLTexture();


                bolhaRef = new Objeto3d(context, R.drawable.naveanorm, asset, "tiroc.obj", R.drawable.wao, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "");
                bolhaRef.setValor(String.valueOf(0));
                bolhaRef.setGiroPosition(new Vetor3(95, 0f, 0f));
                bolhaRef.setTransparente(true);
                bolhaRef.setPosition(new Vetor3(-100, -0.08f, -0.1f));
                // selecao(0, 10, ultimaPassada);
                // bolhaRef.loadGLTexture();


                carga++;
                break;

            case 2:

                selecao(0, 10, ultimaPassada);
                btfundo = new Objeto3d(context, R.drawable.inimigonorm, asset, "btstart.obj", R.drawable.btfundo, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btfundo.setValor(String.valueOf(0));
                btfundo.setGiroPosition(new Vetor3(95, 0f, 0f));
                btfundo.setTransparente(true);
                btfundo.vezes(0.35f);
                btfundo.getGiroPosition().x = -200f;
                btfundo.loadGLTexture();
                btfundo.setPosition(new Vetor3(0f, -0.11f, -0.08f));

                btfundoup = new Objeto3d(context, R.drawable.inimigonorm, asset, "btstart.obj", R.drawable.btfundo, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btfundoup.setValor(String.valueOf(0));
                btfundoup.setGiroPosition(new Vetor3(95, 0f, 0f));
                btfundoup.setTransparente(true);
                btfundoup.vezes(0.35f);
                btfundoup.getGiroPosition().x = 200f;
                btfundoup.loadGLTexture();
                btfundoup.setPosition(new Vetor3(0f, 0.030f, -0.08f));


                btUpgrade = new Objeto3d(context, R.drawable.inimigonorm, asset, "button.obj", R.drawable.buttonup, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btUpgrade.setValor(String.valueOf(0));
                btUpgrade.setGiroPosition(new Vetor3(95, 0f, 0f));
                btUpgrade.setTransparente(true);
                btUpgrade.vezes(0.091f);
                btUpgrade.setPosition(new Vetor3(-0.025f, -0.086f, -0.089f));
                btUpgrade.loadGLTexture();

                btoptions = new Objeto3d(context, R.drawable.inimigonorm, asset, "btoption.obj", R.drawable.btoption, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btoptions.setValor(String.valueOf(0));
                btoptions.setGiroPosition(new Vetor3(95, 0f, 0f));
                btoptions.setTransparente(true);
                btoptions.vezes(0.091f);
                btoptions.setPosition(new Vetor3(0.025f, -0.086f, -0.089f));
                btoptions.loadGLTexture();

                btStart = new Objeto3d(context, R.drawable.inimigonorm, asset, "btstart.obj", R.drawable.btstart, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "upgrade");
                btStart.setValor(String.valueOf(0));
                btStart.setGiroPosition(new Vetor3(95, 0f, 0f));
                btStart.setTransparente(true);
                btStart.vezes(0.1f);
                btStart.setPosition(new Vetor3(0.00f, -0.09f, -0.089f));
                btStart.loadGLTexture();


                carga++;

                break;
            case 3:
                //  selecao(25, 40, ultimaPassada);

                carga++;
                break;
            case 4:


                // selecao(40, 64, ultimaPassada);
                if (ultimaPassada > 4) {
                    while (bolhas.get((int) (ultimaPassada - 1)).getPosition().y > -0.04f) {
                        for (Objeto3d ob : bolhas) {

                            ob.getPosition().y -= velocidade * 0.2f;
                        }
                    }

                } else {
                    for (Objeto3d ob : bolhas) {

                        ob.getPosition().y += velocidade * 1.8f;
                    }
                }
                carga++;
                break;


        }


    }


    public void selecao(int in, int on, long ultimaPassada) {
        ConvertBitimap convertBitimap = new ConvertBitimap();
        for (int p = in; p < on; p++) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    bolhas.add(new Objeto3d(context, R.drawable.naveanorm, asset, "tiroc.obj", convertBitimap.getBitmapBolha(String.valueOf(String.valueOf(p + 1)), 100, 0, ultimaPassada), new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            bolhas.get(p).setValor(String.valueOf(p));
            bolhas.get(p).setGiroPosition(new Vetor3(95, 0f, 0f));
            bolhas.get(p).setTransparente(true);
            bolhas.get(p).vezes(0.5f);
            float py = (p * 0.025f) - 0.03f;
            float px = 0.03f;
            if (p % 2 == 0) {
                py = (p * 0.025f) - 0.03f;
                px = -0.020f;
            }
            bolhas.get(p).setPosition(new Vetor3(px, py - 0.05f, 0.1f));
        }

    }


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
        } else if (fasecarregada == 2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                result.onSurfaceCreated(gl, this.eglConfig);

            }
            //  fasecarregada = false;
        }
        this.gl2 = gl;

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

                    tut = new TartarugaCorrida(context, asset, fase, comSons, comMusica);

                    tut.onSurfaceCreated(gl, this.eglConfig);

                } catch (Exception ioException) {
                    ioException.printStackTrace();
                }

                tut.onDrawFrame((GL11) gl);


            } else {


                tut.onDrawFrame((GL11) gl);

                if (tut.selectFase) {
                    fasecarregada = 0;
                    musicaInicioFase = false;
                    mudarMusica(-2);
                    try {
                        mudarLista();
                        tut.selectFase = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
            if (tut.isHoraDoBoss() && !musicaBoss) {
                mudarMusica(-1);

            } else if (!tut.isHoraDoBoss() && musicaBoss && !tut.isBossEliminado() && fasecarregada == 1) {
                mudarMusica(fase);
                musicaBoss = false;
            } else if (musicaInicioFase) {
                mudarMusica(fase);
                musicaBoss = false;
                musicaInicioFase = false;
            }


        } else if (fasecarregada == 2) {

            if (result == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Looper.myLooper() == null) {
                        Looper.prepare();
                    } else {
                        Looper.getMainLooper().getThread().interrupt();
                    }
                }
                try {
                    // musica.pause();

                    result = new TelaResultados(context, asset);

                    result.onSurfaceCreated(gl, this.eglConfig);

                } catch (Exception ioException) {
                    ioException.printStackTrace();
                }

                result.onDrawFrame((GL11) gl);

            } else {


                result.onDrawFrame((GL11) gl);

//                if (result.selectFase) {
//                    fasecarregada = 0;
//                    try {
//                        mudarLista();
//                        tut.selectFase = false;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }


            }


        } else {

            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);


            float totaly = ((this.displayMetrics.heightPixels / 2) / this.displayMetrics.scaledDensity) * 0.0001f;
            gl.glLoadIdentity();
            gl.glRotatef(0, 0, 0, 0);
            gl.glTranslatef(0, 0.04f, -0.01f);


            if (carga > 0 && carga <= 1) {

                this.telaIntro.draw((GL11) gl2);
            }
            if (carga > 1) {
                this.barra.draw((GL11) gl2);
                if (moverFundo == 0) {
                    this.barra.getPosition().x += 0.0002f;
                    if (this.barra.getPosition().x >= 0.5f) {
                        moverFundo = 1;
                    }
                } else {
                    this.barra.getPosition().x -= 0.0002f;
                    if (this.barra.getPosition().x <= -0.5f) {
                        moverFundo = 0;
                    }
                }
            }
            if (carga > 3) {


                controleDeTela();
                for (int p = 0; p < bolhas.size(); p++) {
                    bolhas.get(p).draw((GL11) gl2);
                }


                this.btUpgrade.draw((GL11) gl2);
                this.btStart.draw((GL11) gl2);
                this.btoptions.draw((GL11) gl2);
                this.btfundo.draw((GL11) gl2);
                this.btfundoup.draw((GL11) gl2);


            } else {
                if (bolhaRef != null) {
                    bolhaRef.setPosition(new Vetor3(0, -0.02f, -0.1f));
                    //   bolhaRef.setGiroPosition(new Vetor3(95, bolhaRef.getGiroPosition().y + 8, 0));
                    bolhaRef.draw((GL11) gl2);

                }
            }


            try {

                if (carga < 0)
                    carga++;
                carregar(tipo);
                ///  carga++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void mudarMusica(int num) {


        switch (num) {
            case 0:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    this.musica = null;
                }

                this.musica = MediaPlayer.create(context, R.raw.musicb);
                this.musica.setLooping(true);
                this.musica.setVolume(0.4f, 0.4f);
                this.musica.start();
                break;
            case 1:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    this.musica = null;
                }

                this.musica = MediaPlayer.create(context, R.raw.musicc);
                this.musica.setLooping(true);
                this.musica.setVolume(0.4f, 0.4f);
                this.musica.start();
                break;
            case 2:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    this.musica = null;
                }

                this.musica = MediaPlayer.create(context, R.raw.musicd);
                this.musica.setLooping(true);
                this.musica.setVolume(0.4f, 0.4f);
                this.musica.start();
                break;
            case -2:
                if (this.musica.isPlaying()) {
                    this.musica.pause();
                    this.musica = null;
                }

                this.musica = MediaPlayer.create(context, R.raw.musica);
                this.musica.setLooping(true);
                this.musica.setVolume(0.4f, 0.4f);
                this.musica.start();

                break;
            case -1:
                if (regularSon) {
                    volumeSon-=0.004;
                }else {
                    volumeSon+=0.004;
                }
                if (volumeSon <=0) {
                    if (this.musica.isPlaying()) {
                        this.musica.pause();
                        this.musica = null;
                    }

                    this.musica = MediaPlayer.create(context, R.raw.musice);
                    this.musica.setLooping(true);
                   // this.musica.setVolume(0.4f, 0.4f);
                    this.musica.start();
                    regularSon=false;
                }
                this.musica.setVolume(volumeSon, volumeSon);

                if (volumeSon >=0.40) {
                    musicaBoss=true;
                    regularSon=true;

                }
                break;
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void mudarLista() throws IOException {
        ConvertBitimap convertBitimap = new ConvertBitimap();
        EstatusFase e = new BDEstatusFase(context).buscarUltima();

        long ultimaPassada = e != null ? e.getId() : 1l;

        Objeto3d ob = new Objeto3d(context, R.drawable.naveanorm, asset, "tiroc.obj", convertBitimap.getBitmapBolha(String.valueOf(String.valueOf(fase + 1)), 100, 0, ultimaPassada), new Vetor3(escala * 5f, escala * 5f, escala * 5f), "");
        ob.setPosition(bolhas.get(fase).getPosition());
        ob.setGiroPosition(bolhas.get(fase).getGiroPosition());
        ob.setValor(bolhas.get(fase).getValor());
        ob.setTransparente(true);
        ob.vezes(0.5f);

        bolhas.set(fase, ob);

        if (ultimaPassada == fase + 1) {

            Objeto3d ob2 = new Objeto3d(context, R.drawable.naveanorm, asset, "tiroc.obj", convertBitimap.getBitmapBolha(String.valueOf(String.valueOf(fase + 2)), 100, 0, ultimaPassada), new Vetor3(escala * 5f, escala * 5f, escala * 5f), "");
            ob2.setPosition(bolhas.get(fase + 1).getPosition());
            ob2.setGiroPosition(bolhas.get(fase + 1).getGiroPosition());
            ob2.setValor(bolhas.get(fase + 1).getValor());
            ob2.setTransparente(true);
            ob2.vezes(0.5f);

            bolhas.set(fase + 1, ob2);

        }


    }

    private void controleDeTela() {
        if (iniciarTelaDeSelecao)
            surgirListagenDeFases(recolher);


    }

    public void surgirListagenDeFases(int recolher) {

        switch (recolher) {
            case 1:
                for (int p = 0; p < bolhas.size(); p++) {
                    if (bolhas.get(0).getPosition().z > -0.1f) {
                        bolhas.get(p).getPosition().z -= 0.008f;
                        btStart.getPosition().z = -0.092f;
                    } else {
                        iniciarTelaDeSelecao = false;
                        btStart.getPosition().z = -0.089f;

                    }
                }
                break;
            case 2:
                for (int p = 0; p < bolhas.size(); p++) {
                    if (bolhas.get(0).getPosition().z < 0.1f) {
                        bolhas.get(p).getPosition().z += 0.008f;
                        btUpgrade.getPosition().z = -0.092f;

                    } else {
                        iniciarTelaDeSelecao = false;
                        btUpgrade.getPosition().z = -0.089f;

                    }
                }
                break;
            case 3:
                for (int p = 0; p < bolhas.size(); p++) {
                    if (bolhas.get(0).getPosition().z < 0.1f) {
                        bolhas.get(p).getPosition().z += 0.008f;
                        btoptions.getPosition().z = -0.092f;

                    } else {
                        iniciarTelaDeSelecao = false;
                        btoptions.getPosition().z = -0.089f;

                    }
                }
                break;
        }
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
        float basex = ((x / this.displayMetrics.scaledDensity) * 0.000177f) - 0.0309f;


        bolhaRef.getPosition().y = (basey) * -1;
        bolhaRef.getPosition().x = basex;
        boolean sel = false;
        for (Objeto3d obj : bolhas) {
            double start = calculoarDistancia(obj, bolhaRef.getPosition());
            if (start <= 0.025 || iniciaraProxima) {
                sel = true;
                bolhaRef.getPosition().y = -100;
                bolhaRef.getPosition().x = -100;
                ConvertBitimap convertBitimap = new ConvertBitimap();
                BDEstatusFase bdEstatusFase = new BDEstatusFase(context);

                if (!iniciaraProxima)
                    fase = Integer.valueOf(obj.getValor());
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
                            selectFase = false;
                            alerta.fechar();
                            fasecarregada = 1;
                            musicaInicioFase = true;
                            if (tut != null) {
                                // tut.destroy();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (Looper.myLooper() == null) {
                                        Looper.prepare();
                                    } else {
                                        Looper.getMainLooper().getThread().interrupt();
                                    }
                                }
                                tut.tartarugaF(fase, comSons, comMusica);
                            }


                        }
                    });


                    alerta.getBtNao().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectFase = true;
                            alerta.fechar();
                        }
                    });
                }
                break;
            }
        }

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
    }


    private void moverListagemDefases(MotionEvent event) {

        if (event.getY() > pontoDoEixoYInicio) {

            if (bolhas.get(0).getPosition().y < -0.02f && bolhas.get(bolhas.size() - 1).getPosition().y > -0.02f) {

                for (int p = 0; p < bolhas.size(); p++) {
                    bolhas.get(p).getPosition().y -= velocidade * 0.0002f;
                }
            } else {
                pontoDoEixoYInicio = event.getY();
                for (int p = 0; p < bolhas.size(); p++) {
                    bolhas.get(p).getPosition().y += velocidade * 0.0002f;
                }
            }


        } else {
            if (bolhas.get(0).getPosition().y < -0.02f && bolhas.get(bolhas.size() - 1).getPosition().y > -0.02f) {

                for (int p = 0; p < bolhas.size(); p++) {
                    bolhas.get(p).getPosition().y += velocidade * 0.0002f;
                }
            } else {
                pontoDoEixoYInicio = event.getY();
                for (int p = 0; p < bolhas.size(); p++) {
                    bolhas.get(p).getPosition().y -= velocidade * 0.0002f;
                }
            }

        }

    }


    ///GERENCIA O TOQUE NA TELA
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if (fasecarregada == 0) {

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
                }

                return true;

            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {


                int index1 = Math.round(pontoDoEixoYInicio);
                int index2 = Math.round(event.getY());
                int res = index1 - index2;
                res = res < 0 ? -1 * res : res;
                res *= 4;


                for (int p = 1; p < res; p++) {
                    moverListagemDefases(event);

                }
                return true;

            }


        } else if (tut != null && fasecarregada == 1) {
            tut.onTouch(view, event);
        } else if (result != null && fasecarregada == 2) {
            result.onTouch(view, event);
        }


        return true;
    }


}



