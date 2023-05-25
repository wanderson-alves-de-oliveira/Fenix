package com.wao.fenix.projetoz.menu;

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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.wao.fenix.R;
import com.wao.fenix.projetoz.dao.BDEstatusFase;
import com.wao.fenix.projetoz.generico.memoria.CapituraEventosObj;
import com.wao.fenix.projetoz.generico.recursos.ConvertBitimap;
import com.wao.fenix.projetoz.generico.recursos.Cronograma;
import com.wao.fenix.projetoz.generico.recursos.Esplosao;
import com.wao.fenix.projetoz.generico.recursos.Fase;
import com.wao.fenix.projetoz.generico.recursos.Objeto3d;
import com.wao.fenix.projetoz.generico.recursos.Vetor3;
import com.wao.fenix.projetoz.index.Tartaruga;
import com.wao.fenix.projetoz.modelo.EstatusFase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
public class TartarugaCorrida extends AppCompatActivity implements GLSurfaceView.Renderer, GLSurfaceView.OnTouchListener {
    private Objeto3d Fenix;
    private Objeto3d btEspecial;
    private Objeto3d top;

    private ArrayList<Objeto3d> life;

    private String alfabeto = "ABCDE";
    private Objeto3d telaIntro;
    // private Objeto3d esfera;

    private Objeto3d barra;
    private Float giroyFenix = 0f;
    private Float giroyFenixInimigo = 0f;
    private int moverFundo = 0;
    private int inimigosGerados = 0;
    private int inimigosEliminados = 0;
    private Vetor3 positiomAnteriorBoss;
    private int total;
    private int indexOuro = 0;

    private int po = 0;
    private int timeLine = 0;
    // private int timeFim = 0;
    private int dificult = 1;
    private int fase = 0;
    private int proximoIni = 0;
    private int recomecar = 0;
    private float localz = 62;
    private float localy = -17.00f;
    private float localx = 0;
    private float rotation = 80;
    private float rotationy = 0;
    private int tiroAtualDoX = 0;
    private int tiroAtualDoA = 0;
    private int tiroAtualDoB = 0;
    private int tiroAtualDoC = 0;
    private int tiroAtualDoE = 0;
    private int tiroAtualDoC2 = 0;
    private int tiroAtualDoE2 = 0;
    private int tiroAtualDoD = 0;
    private int tiroAtualDoBoss = 0;


    private float ceoZ = 0;
    private float ceoZ2 = 0;

    private int rotacinarFenix = 0;
    private float pontoDoEixoX = 0;
    private float pontoDoEixoY = 0;
    private float turbo = 0.1f;
    private boolean parado = false;
    public boolean selectFase = false;
    public boolean acionado = false;
    public boolean carregamentoDireto = false;

    private boolean vitoria = false;
    private boolean exibirStatusVitoria = false;
    private boolean invulneravel = false;
    private final int QTD_DE_TIROS = 61;
    private int nivelTiro = 12;
    private int chefeDafase = 0;
    private int indiceLevel = 0;
    private int nivelTiroTempo = 15;
    private int nivelTiroTempoAtaqueEspecial = 15;

    private int nivelTiroIndex = 0;
    private int danoNoInimigo = 1;
    private float pontoDoEixoYInicio = 0;
    private float pontoDoEixoYFimm = 0;
    private float pontoDoEixoXInicio = 0;
    private float pontoDoEixoXFimm = 0;
    private boolean[] vaiPraCena = {false, false, false, false, false, false, false, false};
    private boolean[] ativarBoss = {false, false, false, false, false, false, false, false, false, false, false};

    private float giroy = 0;
    private float girox = 0;
    private int recolher = 1;
    private boolean iniciarTelaDeSelecao = true;

    private boolean fogo = false;
    float veloz = 0f;
    float velox = 0f;
    float veloy = -0f;


    private GL10 gl2;

    private int xx;
    private int yy;
    private int xr;
    private int yr;

    //private Objeto3d quadroInserirPalavra;
    private Objeto3d bolhaRef;
    private Objeto3d btUpgrade;
    private Objeto3d btfundo;
    private Objeto3d btfundoup;


    private Objeto3d btoptions;
    private Objeto3d btStart;
    private ArrayList<Objeto3d> inimigosC;
    private ArrayList<Objeto3d> inimigosB;
    private ArrayList<Objeto3d> inimigosX;
    private ArrayList<Objeto3d> inimigosE;
    private ArrayList<Objeto3d> ouro;


    private ArrayList<Objeto3d> inimigosC2;

    private ArrayList<Objeto3d> inimigosE2;
    private int posteste = 0;
    private int postesteTime = 0;

    private int indexF = 0;
    private ArrayList<Objeto3d> ataqueEspecial;

    private ArrayList<Objeto3d> boss;

    //  private Objeto3d bossAlt;

    //private ArrayList<Objeto3d> sobrasObj;

    private ArrayList<Objeto3d> bolhas;
    private ArrayList<Esplosao> esplosaoArrayObj;
    private ArrayList<Objeto3d> niveis;
    private ArrayList<Objeto3d> niveis2;

    private ArrayList<String> rastreio;

    public boolean isVitoria() {
        return vitoria;
    }

    public void setVitoria(boolean vitoria) {
        this.vitoria = vitoria;
    }

    private ArrayList<Objeto3d> asteroide;

    private ArrayList<Objeto3d> inimigosA;
    private ArrayList<Objeto3d> splosaoArrayNave;
    private ArrayList<Objeto3d> tiros;

    private ArrayList<ArrayList<Objeto3d>> objetosCenario;
    private ArrayList<Objeto3d> cena;

    private Objeto3d terreno;
    private Objeto3d terreno2;
    private ArrayList<Cronograma> cronograma;
    private boolean bossEliminado = false;
    //private Objeto3d objeto3dxquadro;
    private int nivel = 1;
    private int resgateOuro = 0;

    private int tempoDeEspera = 1;
    private int tempoDisparo = 0;
    private int tempoDisparoAtaqueEspecial = 0;
    private int idDisparoAtaqueEspecial = 0;
    private int idDisparoQTDAtaqueEspecial = 0;

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
    private int vida = 50000;
    private int barraDeEnergia = 100;
    //private MediaPlayer musica;
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

    private CapituraEventosObj animal;
    private MediaPlayer leaoxm;
    private float angle = 0;
    public int carga = -1;
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
    private boolean limpar = false;
    GL10 gl;
    EGLConfig eglConfig;

    private int[] direcaoX = {-1, 2, 3, 3, 2, 0, 1, 8, 8};

    private int[] direcaoX2 = {0, 0, 0, 0, 2, 3, 2, 3};

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
    private final float velocidade2 = 0.024f;

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

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public boolean isBossEliminado() {
        return bossEliminado;
    }

    public void setBossEliminado(boolean bossEliminado) {
        this.bossEliminado = bossEliminado;
    }

    int faseInit[] = {}; //Fully white
    private boolean comMusica = true;
    private boolean comSons = true;
    private boolean x = false, y = false, z = false;
    float white[] = {0.3f, 0.3f, 0.3f, 1.0f}; //Fully white
    float green[] = {0.0f, 1.0f, 0.0f, 1.0f}; //Bright green
    float blue[] = {0.0f, 0.0f, 1.0f, 1.0f}; //Fully white
    float amarelo[] = {1.0f, 1.0f, 1.0f, 1.0f}; //Fully white
    float luzDifusa[] = {0.7f, 0.7f, 0.7f, 1.0f};//luz difusa
    private FloatBuffer corBufferG;


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

    public TartarugaCorrida() {
    }

    public Objeto3d getBarra() {
        return barra;
    }

    public void setBarra(Objeto3d barra) {
        this.barra = barra;
    }

    public Float getGiroyFenix() {
        return giroyFenix;
    }

    public void setGiroyFenix(Float giroyFenix) {
        this.giroyFenix = giroyFenix;
    }

    public Float getGiroyFenixInimigo() {
        return giroyFenixInimigo;
    }

    public void setGiroyFenixInimigo(Float giroyFenixInimigo) {
        this.giroyFenixInimigo = giroyFenixInimigo;
    }

    public int getMoverFundo() {
        return moverFundo;
    }

    public void setMoverFundo(int moverFundo) {
        this.moverFundo = moverFundo;
    }

    public int getInimigosGerados() {
        return inimigosGerados;
    }

    public void setInimigosGerados(int inimigosGerados) {
        this.inimigosGerados = inimigosGerados;
    }

    public int getInimigosEliminados() {
        return inimigosEliminados;
    }

    public void setInimigosEliminados(int inimigosEliminados) {
        this.inimigosEliminados = inimigosEliminados;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPo() {
        return po;
    }

    public void setPo(int po) {
        this.po = po;
    }

    public int getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(int timeLine) {
        this.timeLine = timeLine;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getProximoIni() {
        return proximoIni;
    }

    public void setProximoIni(int proximoIni) {
        this.proximoIni = proximoIni;
    }

    public int getRecomecar() {
        return recomecar;
    }

    public void setRecomecar(int recomecar) {
        this.recomecar = recomecar;
    }

    public float getLocalz() {
        return localz;
    }

    public void setLocalz(float localz) {
        this.localz = localz;
    }

    public float getLocaly() {
        return localy;
    }

    public void setLocaly(float localy) {
        this.localy = localy;
    }

    public float getLocalx() {
        return localx;
    }

    public void setLocalx(float localx) {
        this.localx = localx;
    }

    public float getCeoZ() {
        return ceoZ;
    }

    public void setCeoZ(float ceoZ) {
        this.ceoZ = ceoZ;
    }

    public int getRotacinarFenix() {
        return rotacinarFenix;
    }

    public void setRotacinarFenix(int rotacinarFenix) {
        this.rotacinarFenix = rotacinarFenix;
    }

    public float getPontoDoEixoX() {
        return pontoDoEixoX;
    }

    public void setPontoDoEixoX(float pontoDoEixoX) {
        this.pontoDoEixoX = pontoDoEixoX;
    }

    public float getPontoDoEixoY() {
        return pontoDoEixoY;
    }

    public void setPontoDoEixoY(float pontoDoEixoY) {
        this.pontoDoEixoY = pontoDoEixoY;
    }

    public float getTurbo() {
        return turbo;
    }

    public void setTurbo(float turbo) {
        this.turbo = turbo;
    }

    public boolean isParado() {
        return parado;
    }

    public void setParado(boolean parado) {
        this.parado = parado;
    }

    public boolean isSelectFase() {
        return selectFase;
    }

    public void setSelectFase(boolean selectFase) {
        this.selectFase = selectFase;
    }

    public boolean isInvulneravel() {
        return invulneravel;
    }

    public void setInvulneravel(boolean invulneravel) {
        this.invulneravel = invulneravel;
    }

    public int getQTD_DE_TIROS() {
        return QTD_DE_TIROS;
    }

    public int getNivelTiro() {
        return nivelTiro;
    }

    public void setNivelTiro(int nivelTiro) {
        this.nivelTiro = nivelTiro;
    }

    public int getNivelTiroTempo() {
        return nivelTiroTempo;
    }

    public void setNivelTiroTempo(int nivelTiroTempo) {
        this.nivelTiroTempo = nivelTiroTempo;
    }

    public int getNivelTiroIndex() {
        return nivelTiroIndex;
    }

    public void setNivelTiroIndex(int nivelTiroIndex) {
        this.nivelTiroIndex = nivelTiroIndex;
    }

    public int getDanoNoInimigo() {
        return danoNoInimigo;
    }

    public void setDanoNoInimigo(int danoNoInimigo) {
        this.danoNoInimigo = danoNoInimigo;
    }

    public float getPontoDoEixoYInicio() {
        return pontoDoEixoYInicio;
    }

    public void setPontoDoEixoYInicio(float pontoDoEixoYInicio) {
        this.pontoDoEixoYInicio = pontoDoEixoYInicio;
    }

    public float getPontoDoEixoYFimm() {
        return pontoDoEixoYFimm;
    }

    public void setPontoDoEixoYFimm(float pontoDoEixoYFimm) {
        this.pontoDoEixoYFimm = pontoDoEixoYFimm;
    }

    public float getPontoDoEixoXInicio() {
        return pontoDoEixoXInicio;
    }

    public void setPontoDoEixoXInicio(float pontoDoEixoXInicio) {
        this.pontoDoEixoXInicio = pontoDoEixoXInicio;
    }

    public float getPontoDoEixoXFimm() {
        return pontoDoEixoXFimm;
    }

    public void setPontoDoEixoXFimm(float pontoDoEixoXFimm) {
        this.pontoDoEixoXFimm = pontoDoEixoXFimm;
    }

    public boolean[] getVaiPraCena() {
        return vaiPraCena;
    }

    public void setVaiPraCena(boolean[] vaiPraCena) {
        this.vaiPraCena = vaiPraCena;
    }

    public boolean[] getAtivarBoss() {
        return ativarBoss;
    }

    public void setAtivarBoss(boolean[] ativarBoss) {
        this.ativarBoss = ativarBoss;
    }

    public float getGiroy() {
        return giroy;
    }

    public void setGiroy(float giroy) {
        this.giroy = giroy;
    }

    public float getGirox() {
        return girox;
    }

    public void setGirox(float girox) {
        this.girox = girox;
    }

    public int getRecolher() {
        return recolher;
    }

    public void setRecolher(int recolher) {
        this.recolher = recolher;
    }

    public boolean isIniciarTelaDeSelecao() {
        return iniciarTelaDeSelecao;
    }

    public void setIniciarTelaDeSelecao(boolean iniciarTelaDeSelecao) {
        this.iniciarTelaDeSelecao = iniciarTelaDeSelecao;
    }

    public boolean isFogo() {
        return fogo;
    }

    public void setFogo(boolean fogo) {
        this.fogo = fogo;
    }

    public float getVeloz() {
        return veloz;
    }

    public void setVeloz(float veloz) {
        this.veloz = veloz;
    }

    public float getVelox() {
        return velox;
    }

    public void setVelox(float velox) {
        this.velox = velox;
    }

    public float getVeloy() {
        return veloy;
    }

    public void setVeloy(float veloy) {
        this.veloy = veloy;
    }

    public GL10 getGl2() {
        return gl2;
    }

    public void setGl2(GL10 gl2) {
        this.gl2 = gl2;
    }

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public int getXr() {
        return xr;
    }

    public void setXr(int xr) {
        this.xr = xr;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
    }

    public Objeto3d getBolhaRef() {
        return bolhaRef;
    }

    public void setBolhaRef(Objeto3d bolhaRef) {
        this.bolhaRef = bolhaRef;
    }

    public Objeto3d getBtUpgrade() {
        return btUpgrade;
    }

    public void setBtUpgrade(Objeto3d btUpgrade) {
        this.btUpgrade = btUpgrade;
    }

    public Objeto3d getBtfundo() {
        return btfundo;
    }

    public void setBtfundo(Objeto3d btfundo) {
        this.btfundo = btfundo;
    }

    public Objeto3d getBtfundoup() {
        return btfundoup;
    }

    public void setBtfundoup(Objeto3d btfundoup) {
        this.btfundoup = btfundoup;
    }

    public Objeto3d getBtoptions() {
        return btoptions;
    }

    public void setBtoptions(Objeto3d btoptions) {
        this.btoptions = btoptions;
    }

    public Objeto3d getBtStart() {
        return btStart;
    }

    public void setBtStart(Objeto3d btStart) {
        this.btStart = btStart;
    }

    public ArrayList<Objeto3d> getInimigosC() {
        return inimigosC;
    }

    public void setInimigosC(ArrayList<Objeto3d> inimigosC) {
        this.inimigosC = inimigosC;
    }

    public ArrayList<Objeto3d> getInimigosB() {
        return inimigosB;
    }

    public void setInimigosB(ArrayList<Objeto3d> inimigosB) {
        this.inimigosB = inimigosB;
    }

    public ArrayList<Objeto3d> getInimigosX() {
        return inimigosX;
    }

    public void setInimigosX(ArrayList<Objeto3d> inimigosX) {
        this.inimigosX = inimigosX;
    }

    public ArrayList<Objeto3d> getInimigosE() {
        return inimigosE;
    }

    public void setInimigosE(ArrayList<Objeto3d> inimigosE) {
        this.inimigosE = inimigosE;
    }

    public ArrayList<Objeto3d> getBoss() {
        return boss;
    }

    public void setBoss(ArrayList<Objeto3d> boss) {
        this.boss = boss;
    }

    public ArrayList<Objeto3d> getBolhas() {
        return bolhas;
    }

    public void setBolhas(ArrayList<Objeto3d> bolhas) {
        this.bolhas = bolhas;
    }

    public ArrayList<Esplosao> getEsplosaoArrayObj() {
        return esplosaoArrayObj;
    }

    public void setEsplosaoArrayObj(ArrayList<Esplosao> esplosaoArrayObj) {
        this.esplosaoArrayObj = esplosaoArrayObj;
    }

    public ArrayList<Objeto3d> getNiveis() {
        return niveis;
    }

    public void setNiveis(ArrayList<Objeto3d> niveis) {
        this.niveis = niveis;
    }

    public ArrayList<String> getRastreio() {
        return rastreio;
    }

    public void setRastreio(ArrayList<String> rastreio) {
        this.rastreio = rastreio;
    }

    public ArrayList<Objeto3d> getAsteroide() {
        return asteroide;
    }

    public void setAsteroide(ArrayList<Objeto3d> asteroide) {
        this.asteroide = asteroide;
    }

    public ArrayList<Objeto3d> getInimigosA() {
        return inimigosA;
    }

    public void setInimigosA(ArrayList<Objeto3d> inimigosA) {
        this.inimigosA = inimigosA;
    }

    public ArrayList<Objeto3d> getSplosaoArrayNave() {
        return splosaoArrayNave;
    }

    public void setSplosaoArrayNave(ArrayList<Objeto3d> splosaoArrayNave) {
        this.splosaoArrayNave = splosaoArrayNave;
    }

    public ArrayList<Objeto3d> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Objeto3d> tiros) {
        this.tiros = tiros;
    }


    public Objeto3d getTerreno() {
        return terreno;
    }

    public void setTerreno(Objeto3d terreno) {
        this.terreno = terreno;
    }

    public ArrayList<Cronograma> getCronograma() {
        return cronograma;
    }

    public void setCronograma(ArrayList<Cronograma> cronograma) {
        this.cronograma = cronograma;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getTempoDeEspera() {
        return tempoDeEspera;
    }

    public void setTempoDeEspera(int tempoDeEspera) {
        this.tempoDeEspera = tempoDeEspera;
    }

    public int getTempoDisparo() {
        return tempoDisparo;
    }

    public void setTempoDisparo(int tempoDisparo) {
        this.tempoDisparo = tempoDisparo;
    }

    public int getIdDisparoQTD() {
        return idDisparoQTD;
    }

    public void setIdDisparoQTD(int idDisparoQTD) {
        this.idDisparoQTD = idDisparoQTD;
    }

    public int getIdDisparo() {
        return idDisparo;
    }

    public void setIdDisparo(int idDisparo) {
        this.idDisparo = idDisparo;
    }

    public int getIdDisparo2() {
        return idDisparo2;
    }

    public void setIdDisparo2(int idDisparo2) {
        this.idDisparo2 = idDisparo2;
    }

    public int getIdDisparo3() {
        return idDisparo3;
    }

    public void setIdDisparo3(int idDisparo3) {
        this.idDisparo3 = idDisparo3;
    }

    public int getIdDisparo4() {
        return idDisparo4;
    }

    public void setIdDisparo4(int idDisparo4) {
        this.idDisparo4 = idDisparo4;
    }

    public int getIdDisparo5() {
        return idDisparo5;
    }

    public void setIdDisparo5(int idDisparo5) {
        this.idDisparo5 = idDisparo5;
    }

    public AssetManager getAsset() {
        return asset;
    }

    public void setAsset(AssetManager asset) {
        this.asset = asset;
    }

    public long getRecordeVelhoD() {
        return recordeVelhoD;
    }

    public void setRecordeVelhoD(long recordeVelhoD) {
        this.recordeVelhoD = recordeVelhoD;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public File getPegaArquivo() {
        return pegaArquivo;
    }

    public void setPegaArquivo(File pegaArquivo) {
        this.pegaArquivo = pegaArquivo;
    }

    public long getPalavrasConquistadas() {
        return palavrasConquistadas;
    }

    public void setPalavrasConquistadas(long palavrasConquistadas) {
        this.palavrasConquistadas = palavrasConquistadas;
    }

    public long getPalavrasConquistadasDeReset() {
        return palavrasConquistadasDeReset;
    }

    public void setPalavrasConquistadasDeReset(long palavrasConquistadasDeReset) {
        this.palavrasConquistadasDeReset = palavrasConquistadasDeReset;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getVariante() {
        return variante;
    }

    public boolean isVenceu() {
        return venceu;
    }

    public void setVenceu(boolean venceu) {
        this.venceu = venceu;
    }

    public boolean isPerdeu() {
        return perdeu;
    }

    public void setPerdeu(boolean perdeu) {
        this.perdeu = perdeu;
    }

    public boolean isHoraDoBoss() {
        return horaDoBoss;
    }

    public void setHoraDoBoss(boolean horaDoBoss) {
        this.horaDoBoss = horaDoBoss;
    }

    public boolean isEsplodirNave() {
        return esplodirNave;
    }

    public void setEsplodirNave(boolean esplodirNave) {
        this.esplodirNave = esplodirNave;
    }

    public int getTimeEsplosaoNave() {
        return timeEsplosaoNave;
    }

    public void setTimeEsplosaoNave(int timeEsplosaoNave) {
        this.timeEsplosaoNave = timeEsplosaoNave;
    }

    public int getEsplosaoNaveId() {
        return esplosaoNaveId;
    }

    public void setEsplosaoNaveId(int esplosaoNaveId) {
        this.esplosaoNaveId = esplosaoNaveId;
    }

    public boolean isRetornarQuadro() {
        return retornarQuadro;
    }

    public void setRetornarQuadro(boolean retornarQuadro) {
        this.retornarQuadro = retornarQuadro;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getBarraDeEnergia() {
        return barraDeEnergia;
    }

    public void setBarraDeEnergia(int barraDeEnergia) {
        this.barraDeEnergia = barraDeEnergia;
    }

    public ArrayList<MediaPlayer> getBoom() {
        return boom;
    }

    public void setBoom(ArrayList<MediaPlayer> boom) {
        this.boom = boom;
    }

    public MediaPlayer getDisparo() {
        return disparo;
    }

    public void setDisparo(MediaPlayer disparo) {
        this.disparo = disparo;
    }

    public MediaPlayer getVictory() {
        return victory;
    }

    public void setVictory(MediaPlayer victory) {
        this.victory = victory;
    }

    public boolean isInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public int getModoE() {
        return modoE;
    }

    public void setModoE(int modoE) {
        this.modoE = modoE;
    }

    public int getModoB() {
        return modoB;
    }

    public void setModoB(int modoB) {
        this.modoB = modoB;
    }

    public int getModoX() {
        return modoX;
    }

    public void setModoX(int modoX) {
        this.modoX = modoX;
    }

    public int getModoBoss() {
        return modoBoss;
    }

    public void setModoBoss(int modoBoss) {
        this.modoBoss = modoBoss;
    }

    public CapituraEventosObj getAnimal() {
        return animal;
    }

    public void setAnimal(CapituraEventosObj animal) {
        this.animal = animal;
    }

    public MediaPlayer getLeaoxm() {
        return leaoxm;
    }

    public void setLeaoxm(MediaPlayer leaoxm) {
        this.leaoxm = leaoxm;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getModuloR() {
        return moduloR;
    }

    public void setModuloR(int moduloR) {
        this.moduloR = moduloR;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTimeModoC() {
        return timeModoC;
    }

    public void setTimeModoC(int timeModoC) {
        this.timeModoC = timeModoC;
    }

    public int getTimeModoE() {
        return timeModoE;
    }

    public void setTimeModoE(int timeModoE) {
        this.timeModoE = timeModoE;
    }

    public int getTimeModoB() {
        return timeModoB;
    }

    public void setTimeModoB(int timeModoB) {
        this.timeModoB = timeModoB;
    }

    public int getTimeModoX() {
        return timeModoX;
    }

    public void setTimeModoX(int timeModoX) {
        this.timeModoX = timeModoX;
    }

    public boolean isCarregaModoX() {
        return carregaModoX;
    }

    public void setCarregaModoX(boolean carregaModoX) {
        this.carregaModoX = carregaModoX;
    }

    public boolean isCarregaModoBoss() {
        return carregaModoBoss;
    }

    public void setCarregaModoBoss(boolean carregaModoBoss) {
        this.carregaModoBoss = carregaModoBoss;
    }

    public int getTimeModoBoss() {
        return timeModoBoss;
    }

    public void setTimeModoBoss(int timeModoBoss) {
        this.timeModoBoss = timeModoBoss;
    }

    public int getQuantidadeDeElementos() {
        return quantidadeDeElementos;
    }

    public void setQuantidadeDeElementos(int quantidadeDeElementos) {
        this.quantidadeDeElementos = quantidadeDeElementos;
    }

    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    public void setDisplayMetrics(DisplayMetrics displayMetrics) {
        this.displayMetrics = displayMetrics;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float[] getRotatef() {
        return rotatef;
    }

    public void setRotatef(float[] rotatef) {
        this.rotatef = rotatef;
    }

    public float getwTela() {
        return wTela;
    }

    public void setwTela(float wTela) {
        this.wTela = wTela;
    }

    public float getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(float dificuldade) {
        this.dificuldade = dificuldade;
    }

    public boolean isCarregado() {
        return carregado;
    }

    public void setCarregado(boolean carregado) {
        this.carregado = carregado;
    }

    public boolean isColidiu() {
        return colidiu;
    }

    public void setColidiu(boolean colidiu) {
        this.colidiu = colidiu;
    }

    public GL10 getGl() {
        return gl;
    }

    public void setGl(GL10 gl) {
        this.gl = gl;
    }

    public EGLConfig getEglConfig() {
        return eglConfig;
    }

    public void setEglConfig(EGLConfig eglConfig) {
        this.eglConfig = eglConfig;
    }

    public int[] getDirecaoX() {
        return direcaoX;
    }

    public void setDirecaoX(int[] direcaoX) {
        this.direcaoX = direcaoX;
    }

    public int[] getDirecaoX2() {
        return direcaoX2;
    }

    public void setDirecaoX2(int[] direcaoX2) {
        this.direcaoX2 = direcaoX2;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public float getVelocidadeinimigo1() {
        return velocidadeinimigo1;
    }

    public float getVelocidadeTiro() {
        return velocidadeTiro;
    }

    public float getVelocit() {
        return velocit;
    }

    public boolean isLuping() {
        return luping;
    }

    public void setLuping(boolean luping) {
        this.luping = luping;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }

    public float getDISTANCIA() {
        return DISTANCIA;
    }

    public float getModuloY() {
        return moduloY;
    }

    public void setModuloY(float moduloY) {
        this.moduloY = moduloY;
    }

    public float getModulox() {
        return modulox;
    }

    public void setModulox(float modulox) {
        this.modulox = modulox;
    }

    public float getModuloz() {
        return moduloz;
    }

    public void setModuloz(float moduloz) {
        this.moduloz = moduloz;
    }

    public float getAcelerarando() {
        return acelerarando;
    }

    public void setAcelerarando(float acelerarando) {
        this.acelerarando = acelerarando;
    }

    public int[] getFaseInit() {
        return faseInit;
    }

    public void setFaseInit(int[] faseInit) {
        this.faseInit = faseInit;
    }

    public float[] getWhite() {
        return white;
    }

    public void setWhite(float[] white) {
        this.white = white;
    }

    public float[] getGreen() {
        return green;
    }

    public void setGreen(float[] green) {
        this.green = green;
    }

    public float[] getBlue() {
        return blue;
    }

    public void setBlue(float[] blue) {
        this.blue = blue;
    }

    public float[] getAmarelo() {
        return amarelo;
    }

    public void setAmarelo(float[] amarelo) {
        this.amarelo = amarelo;
    }

    public float[] getLuzDifusa() {
        return luzDifusa;
    }

    public void setLuzDifusa(float[] luzDifusa) {
        this.luzDifusa = luzDifusa;
    }

    public FloatBuffer getCorBufferG() {
        return corBufferG;
    }

    public void setCorBufferG(FloatBuffer corBufferG) {
        this.corBufferG = corBufferG;
    }

    public float getDistanciaR() {
        return distanciaR;
    }

    public void setDistanciaR(float distanciaR) {
        this.distanciaR = distanciaR;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TartarugaCorrida(Context context, AssetManager asset, int fase, boolean comSons, boolean comMusica) throws IOException {

        //////////////INICIA VARIAVEIS BASICAS
        this.context = context;
        this.asset = asset;
        this.fase = fase;
        this.displayMetrics = context.getResources().getDisplayMetrics();
        this.h = this.displayMetrics.heightPixels;
        this.w = 720;
        this.wTela = this.displayMetrics.widthPixels;
        this.comSons = comSons;
        this.comMusica = comMusica;

        rastreio = new ArrayList<>();

        animal = new CapituraEventosObj(0, 0, 0, 350, 320, 420, "leao", Fenix);
        /////////////////////////////////////////////////////
        faseInit = new int[]{0, 1, 2, 3, 4, 5, 2, 3, 4, 1, 5, 0, 2};
        this.limpar = false;

    }

    public void tartarugaF(int fase, boolean comSons, boolean comMusica) {
        tempoDeEspera = 1;
        this.fase = fase;
        this.comSons = comSons;
        this.comMusica = comMusica;


        rastreio = new ArrayList<>();
        this.carregado = false;
        this.carga = -1;
        animal = new CapituraEventosObj(0, 0, 0, 350, 320, 420, "leao", Fenix);
        /////////////////////////////////////////////////////
        faseInit = new int[]{0, 1, 2, 3, 4, 5, 2, 3, 4, 1, 5, 0, 2};
        this.limpar = false;
        restart();
        gameOuver();

        onSurfaceCreated(gl2, this.eglConfig);
        onSurfaceChanged(gl2, (int) wTela, (int) h);

        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.nuvem);
        for (Objeto3d o : asteroide) {

            o.LoadTexture(bitmap2);

        }


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
                this.vida = 50000;

            }
        } else if (v == 1) {
            this.nivel = 1;
            this.inicio = false;
            this.perdeu = false;
            this.venceu = false;
            this.vida = 50000;
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
    public void carregar() throws IOException {
        //////FAZ O CARREGAMENTO DOS ARQUIVOS 3D DO JOGO INTEIRO
        ConvertBitimap convertBitimap = new ConvertBitimap();

        EstatusFase e = new BDEstatusFase(context).buscarUltima();

        long ultimaPassada = e != null ? e.getId() : 1l;
        float ly = 16.0f;
        float lz = -63.1f;
        switch (carga) {
            case 0:


                if (!carregamentoDireto) {
                    this.telaIntro = new Objeto3d(context, R.drawable.basenorm, asset, "intro.obj", R.drawable.fenixload, new Vetor3(1f, 1f, 1f), "");
                    this.telaIntro.setMudarTamanho(true);
                    this.telaIntro.setPosition(new Vetor3(0, 0.02f, -0.9f));
                    this.telaIntro.vezes(1.3f);
                    this.telaIntro.setGiroPosition(new Vetor3(90, 0f, 0f));
                    //   this.telaIntro.loadGLTexture();

                }
                //this.Fenix.setGiroCentral(true);
                //  selecao(30, 40, ultimaPassada);
                carga++;
                break;
            case 1:
                if (!carregamentoDireto) {
                    if (inimigosA == null)
                        inimigosA = new ArrayList<>();
                    if (ouro == null)
                        ouro = new ArrayList<>();
                    if (inimigosX == null)
                        inimigosX = new ArrayList<>();
                    if (inimigosC == null)
                        inimigosC = new ArrayList<>();
                    if (inimigosC2 == null)
                        inimigosC2 = new ArrayList<>();
                    if (asteroide == null)
                        asteroide = new ArrayList<>();
                    if (inimigosE == null)
                        inimigosE = new ArrayList<>();
                    if (inimigosE2 == null)
                        inimigosE2 = new ArrayList<>();
                    if (inimigosB == null)
                        inimigosB = new ArrayList<>();
                    if (boss == null) {
                        boss = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            boss.add(null);
                        }
                    }
                    if (esplosaoArrayObj == null)
                        esplosaoArrayObj = new ArrayList<>();

                }
                carga++;
                break;
            case 2:
                if (!carregamentoDireto) {
                    if (niveis == null) {
                        niveis = new ArrayList<>();
                        niveis2 = new ArrayList<>();

                        niveis.add(null);
                        niveis.add(null);
                        niveis.add(null);
                        niveis.add(null);
                        niveis.add(null);

                        niveis2.add(null);
                        niveis2.add(null);
                        niveis2.add(null);
                        niveis2.add(null);
                        niveis2.add(null);


                    }
                }
                carga++;
                break;
            case 3:
                if (!carregamentoDireto) {
                    cena = new ArrayList<>();
                    if (objetosCenario == null) {
                        objetosCenario = new ArrayList<>();
                        objetosCenario.add(null);
                        objetosCenario.add(null);
                        objetosCenario.add(null);
                        objetosCenario.add(null);
                        objetosCenario.add(null);


                    }
                }
                carga++;

                break;
            case 4:
                if (!carregamentoDireto) {
                    if (Fenix == null) {
                        setFenix(new Objeto3d(context, R.drawable.naveanorm, asset, "navez.obj", R.drawable.navea, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), ""));
                        //   Fenix.loadGLTexture(true);

                        this.Fenix.setEstado("NBateu");
                        Fenix.setPosition(new Vetor3(0, 15f, -62));
                        Fenix.setRefletir(true);
                        Fenix.setFenix(true);
                        Fenix.setNomeRef("Fenix");
                        //  Fenix.setTransparente(true);
                        //  Fenix.loadGLTexture();


                        ataqueEspecial = new ArrayList<>();
                        ataqueEspecial();

                        splosaoArrayNave = splosao(Fenix);
                    }


                }
                carga++;

                break;
            case 5:

                if (!carregamentoDireto) {

                    btEspecial = new Objeto3d(context, R.drawable.naveanorm, asset, "micel.obj", R.drawable.micel, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");

                    this.btEspecial.setEstado("NBateu");
                    btEspecial.setPosition(new Vetor3(0, 15.5f, -61.8f));
                    btEspecial.setRefletir(true);
                    btEspecial.setFenix(true);
                    btEspecial.vezes(0.15f);

                    btEspecial.setNomeRef("btEspecial");
                }

                carga++;
                break;
            case 6:
                if (!carregamentoDireto) {

                    top = new Objeto3d(context, R.drawable.naveanorm, asset, "top.obj", R.drawable.top, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");

                    this.top.setEstado("NBateu");
                    top.setPosition(new Vetor3(0, 15.5f, -63.6f));
                    top.setRefletir(true);
                    top.setFenix(true);
                    top.vezes(0.4f);

                    top.setNomeRef("top");
                }
                carga++;
                break;
            case 7:
                if (!carregamentoDireto) {


                    life = new ArrayList<>();
                    Objeto3d l = new Objeto3d(context, R.drawable.life, asset, "top.obj", R.drawable.life, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    l.setEstado("NBateu");
                    l.setPosition(new Vetor3(0, ly, lz));
                    l.setRefletir(true);
                    l.setFenix(true);
                    l.vezes(0.15f);
                    l.setNomeRef("life");
                    life.add(l);
                    Objeto3d l2 = new Objeto3d(context, R.drawable.lifee, asset, "top.obj", R.drawable.lifee, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    l2.setEstado("NBateu");
                    l2.setPosition(new Vetor3(0, ly, lz));
                    l2.setRefletir(true);
                    l2.setFenix(true);
                    l2.vezes(0.15f);
                    l2.setNomeRef("life");
                    life.add(l2);
                    Objeto3d l3 = new Objeto3d(context, R.drawable.lifeee, asset, "top.obj", R.drawable.lifeee, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    l3.setEstado("NBateu");
                    l3.setPosition(new Vetor3(0, ly, lz));
                    l3.setRefletir(true);
                    l3.setFenix(true);
                    l3.vezes(0.15f);
                    l3.setNomeRef("life");
                    life.add(l3);

                }
                carga++;
                break;
            case 8:
                if (!carregamentoDireto) {
                    Objeto3d l4 = new Objeto3d(context, R.drawable.lifeeee, asset, "top.obj", R.drawable.lifeeee, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    l4.setEstado("NBateu");
                    l4.setPosition(new Vetor3(0, ly, lz));
                    l4.setRefletir(true);
                    l4.setFenix(true);
                    l4.vezes(0.15f);
                    l4.setNomeRef("life");
                    life.add(l4);

                    Objeto3d l5 = new Objeto3d(context, R.drawable.lifeeeee, asset, "top.obj", R.drawable.lifeeeee, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), "");
                    l5.setEstado("NBateu");
                    l5.setPosition(new Vetor3(0, ly, lz));
                    l5.setRefletir(true);
                    l5.setFenix(true);
                    l5.vezes(0.15f);
                    l5.setNomeRef("life");
                    life.add(l5);
                }
                carga++;
                break;
            case 9:
                validarToque();
                carga++;
                break;
            case 10:
                carga++;
                break;
            case 11:
                carga++;
                break;
            case 12:
                if (tiros == null) {
                    tiros = new ArrayList<>();
                }
                carga++;
                break;
            case 13:

                if (!carregamentoDireto) {
                    if (tiros.size() < QTD_DE_TIROS) {
                        int i = tiros.size();
                        tiros.add(new Objeto3d(context, R.drawable.tironavenorm, asset, "tiroz.obj", R.drawable.tironave, new Vetor3(0.5f, 0.5f, 0.5f), ""));
                        tiros.get(i).vezes(1.8f);
                        tiros.get(i).setCores(new Vetor3(1f, 0f, 0f));

                        tiros.get(i).setOrigem("Fenix1");
                        tiros.get(i).setMover("nulo");
                    } else {
                        carga++;
                    }
                } else {
                    carga++;
                }

                break;
            case 14:
                colorir();

                carga++;
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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void carregarP() throws IOException {

        if (ouro == null)
            ouro = new ArrayList<>();
        if (inimigosA == null)
            inimigosA = new ArrayList<>();
        if (inimigosX == null)
            inimigosX = new ArrayList<>();
        if (inimigosC == null)
            inimigosC = new ArrayList<>();
        if (inimigosC2 == null)
            inimigosC2 = new ArrayList<>();
        if (asteroide == null)
            asteroide = new ArrayList<>();
        if (inimigosE == null)
            inimigosE = new ArrayList<>();
        if (inimigosE2 == null)
            inimigosE2 = new ArrayList<>();
        if (inimigosB == null)
            inimigosB = new ArrayList<>();
        if (boss == null)
            boss = new ArrayList<>();
        if (esplosaoArrayObj == null)
            esplosaoArrayObj = new ArrayList<>();
        if (tiros == null)
            tiros = new ArrayList<>();

        if (Fenix == null) {
            setFenix(new Objeto3d(context, R.drawable.naveanorm, asset, "navez.obj", R.drawable.navea, new Vetor3(escala * 0.6f, escala * 0.6f, escala * 0.6f), ""));
            //   Fenix.loadGLTexture(true);

            this.Fenix.setEstado("NBateu");
            Fenix.setPosition(new Vetor3(0, 15f, -62));
            Fenix.setRefletir(true);
            Fenix.setFenix(true);
            Fenix.setNomeRef("Fenix");
            //  Fenix.setTransparente(true);
            //     Fenix.loadGLTexture();


            splosaoArrayNave = splosao(Fenix);
        }


        if (inimigosA.size() == 0)
            inimigosA();

        if (inimigosB.size() == 0)
            inimigosB();

        if (inimigosC.size() == 0)
            inimigosC();
        if (inimigosC2.size() == 0)
            inimigosC2();
        if (inimigosX.size() == 0)
            inimigosX();

        if (ouro.size() == 0)
            ouro();

//        if (asteroide.size() == 0)
//            asteroide();

        if (inimigosE.size() == 0)
            inimigosE();
        if (inimigosE2.size() == 0)
            inimigosE2();
        int faseBoss = (fase + 1) / 10;

        if (boss.get(faseBoss) == null)
            boss();


    }

    public void colorir() {

        indexF = indexFase();

        for (Objeto3d o : inimigosA) {
            switch (indexF) {
                case 0:
                    o.setCores(new Vetor3(1f, 0.4f, 0));
                    break;
                case 1:
                    o.setCores(new Vetor3(0, 0, 1f));
                    break;
                case 2:
                    o.setCores(new Vetor3(1f, 1f, 0));
                    break;
                case 3:
                    o.setCores(new Vetor3(1f, 0.2f, 1f));
                    break;
                case 4:
                    o.setCores(new Vetor3(0f, 1f, 0.1f));
                    break;

            }

        }
        for (Objeto3d o : inimigosB) {
            switch (indexF) {
                case 0:
                    o.setCores(new Vetor3(0, 0, 1f));
                    break;
                case 1:
                    o.setCores(new Vetor3(0, 1f, 0.2f));
                    break;
                case 2:
                    o.setCores(new Vetor3(0, 1f, 0));
                    break;
                case 3:
                    o.setCores(new Vetor3(0, 0.2f, 1f));
                    break;
                case 4:
                    o.setCores(new Vetor3(0, 0, 1f));
                    break;

            }

        }
        for (Objeto3d o : inimigosC) {
//        switch (indexF) {
//            case 0:
//                o.setCores(new Vetor3(1f,0,0));
//                break;
//            case 1:
//                o.setCores(new Vetor3(1f,0,0));
//                break;
//            case 2:
//                o.setCores(new Vetor3(0,0,1f));
//                break;
//
//        }
            o.setCores(new Vetor3(1f, 0, 0));

        }
        for (Objeto3d o : inimigosC2) {
            switch (indexF) {
                case 0:
                    o.setCores(new Vetor3(0, 1f, 0));
                    break;
                case 1:
                    o.setCores(new Vetor3(1f, 0, 0.2f));
                    break;
                case 2:
                    o.setCores(new Vetor3(0, 0, 1f));
                    break;
                case 3:
                    o.setCores(new Vetor3(1f, 0.2f, 1f));
                    break;
                case 4:
                    o.setCores(new Vetor3(1f, 0, 0));
                    break;
            }


        }
        for (Objeto3d o : inimigosE) {
//        switch (indexF) {
//            case 0:
//                o.setCores(new Vetor3(0,0,1f));
//                break;
//            case 1:
//                o.setCores(new Vetor3(1f,0,0));
//                break;
//            case 2:
//                o.setCores(new Vetor3(0,0,1f));
//                break;
//
//        }

            o.setCores(new Vetor3(0, 0, 1f));

        }
        for (Objeto3d o : inimigosE2) {
            switch (indexF) {
                case 0:
                    o.setCores(new Vetor3(0, 1f, 0));
                    break;
                case 1:
                    o.setCores(new Vetor3(1f, 0, 0.2f));
                    break;
                case 2:
                    o.setCores(new Vetor3(0, 0, 1f));
                    break;
                case 3:
                    o.setCores(new Vetor3(1f, 0.2f, 1f));
                    break;
                case 4:
                    o.setCores(new Vetor3(1f, 0, 0));
                    break;

            }


        }
        for (Objeto3d o : inimigosX) {
//        switch (indexF) {
//            case 0:
//                o.setCores(new Vetor3(0, 0, 1f));
//                break;
//            case 1:
//                o.setCores(new Vetor3(0, 1f, 0));
//                break;
//            case 2:
//                o.setCores(new Vetor3(0, 0, 1f));
//                break;
//
//        }

            o.setCores(new Vetor3((float) Math.random(), 1f, (float) Math.random()));

        }

    }

    public void inimigosB() throws IOException {
        float vidaB = 20f;
        for (int p = 0; p < this.quantidadeDeElementos; p++) {
            char v = alfabeto.charAt(p);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inimigosB.add(new Objeto3d(context, R.drawable.inimigonorm, asset, "inimigo.obj", R.drawable.inimigo, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosB"));
            }
            //    inimigosB.get(p).loadGLTexture(false);
            inimigosB.get(p).setValor(String.valueOf(v));
            inimigosB.get(p).setVida(vidaB * dificuldade);
            inimigosB.get(p).setRecoverVida(vidaB * dificuldade);
            inimigosB.get(p).setTiroNave(inimigosB.get(p).criarTiros(inimigosB.get(p), R.drawable.tirobnorm, 5, asset, "tiroc.obj", R.drawable.tirob, context.getResources()));
            inimigosB.get(p).setRefletir(true);
            // inimigosB.get(p).loadGLTexture();
            esplosaoArrayObj.add(new Esplosao(context, inimigosB.get(p), asset, context.getResources(), 0.8f, "inimigosB", p));

        }
        inimigosB.get(0).setPosition(new Vetor3(-0.3f,
                DISTANCIA, -65));
        inimigosB.get(1).setPosition(new Vetor3(0.3f,
                DISTANCIA, -65));
        inimigosB.get(2).setPosition(new Vetor3(0.0f,
                DISTANCIA, -66));

    }

    public void inimigosA() throws IOException {
        float vidaA = 20f;
        for (int p = 0; p < this.quantidadeDeElementos; p++) {
            char v = alfabeto.charAt(p);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inimigosA.add(new Objeto3d(context, R.drawable.basednorm, asset, "based.obj", R.drawable.based, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosA"));
            }
            inimigosA.get(p).setValor(String.valueOf(v));
            inimigosA.get(p).setTiroNave(inimigosA.get(p).criarTiros(inimigosA.get(p), R.drawable.tiroanorm, 5, asset, "tiroc.obj", R.drawable.tiroa, context.getResources()));
            inimigosA.get(p).setVida(vidaA * dificuldade);
            inimigosA.get(p).setRecoverVida(vidaA * dificuldade);
            inimigosA.get(p).setRefletir(true);
            // inimigosA.get(p).loadGLTexture();

            esplosaoArrayObj.add(new Esplosao(context, inimigosA.get(p), asset, context.getResources(), 0.8f, "inimigosA", p));


        }

        inimigosA.get(0).setPosition(new Vetor3(-0.2f,
                DISTANCIA + 0.06f, -67));
        inimigosA.get(1).setPosition(new Vetor3(0.3f,
                DISTANCIA + 0.06f, -67));
        inimigosA.get(2).setPosition(new Vetor3(0.0f,
                DISTANCIA + 0.06f, -69));

    }


    public void inimigosC() throws IOException {

        float vidaCE = 10f;
        for (int p = 0; p < 6; p++) {
            // char v = alfabeto.charAt(p );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inimigosC.add(new Objeto3d(context, R.drawable.inimigocnorm, asset, "inimigoc.obj", R.drawable.inimigoe, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosC"));
                esplosaoArrayObj.add(new Esplosao(context, inimigosC.get(p), asset, context.getResources(), 0.8f, "inimigosC", p));

            }


            inimigosC.get(p).setValor(String.valueOf("v"));
            inimigosC.get(p).setVida(vidaCE * dificuldade);
            inimigosC.get(p).setRecoverVida(vidaCE * dificuldade);
            inimigosC.get(p).setTiroNave(inimigosC.get(p).criarTiros(inimigosC.get(p), R.drawable.tirocnorm, 5, asset, "tiroc.obj", R.drawable.tiroc, context.getResources()));
            inimigosC.get(p).setTipo("C");
            inimigosC.get(p).setPosition(new Vetor3(-1.5f, DISTANCIA, -63f));
            inimigosC.get(p).setRefletir(true);
            //  inimigosC.get(p).loadGLTexture();
        }

    }

    public void inimigosE() throws IOException {

        float vidaCE = 10f;
        for (int p = 0; p < 6; p++) {
            // char v = alfabeto.charAt(p );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inimigosE.add(new Objeto3d(context, R.drawable.inimigoenorm, asset, "inimigoc.obj", R.drawable.inimigoe, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosE"));
                esplosaoArrayObj.add(new Esplosao(context, inimigosE.get(p), asset, context.getResources(), 0.8f, "inimigosE", p));

            }
            inimigosE.get(p).setValor(String.valueOf("v"));
            inimigosE.get(p).setVida(vidaCE * dificuldade);
            inimigosE.get(p).setRecoverVida(vidaCE * dificuldade);
            inimigosE.get(p).setTiroNave(inimigosE.get(p).criarTiros(inimigosE.get(p), R.drawable.tiroenorm, 5, asset, "tiroc.obj", R.drawable.tiroe, context.getResources()));
            inimigosE.get(p).setTipo("E");
            inimigosE.get(p).setPosition(new Vetor3(-1.5f, DISTANCIA - 0.08f, -63f));
            inimigosE.get(p).setRefletir(true);
            // inimigosE.get(p).loadGLTexture();
        }
    }


    public void inimigosC2() throws IOException {

        float vidaCE = 10f;
        for (int p = 0; p < 3; p++) {
            // char v = alfabeto.charAt(p );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inimigosC2.add(new Objeto3d(context, R.drawable.inimigocnorm, asset, "inimigod.obj", R.drawable.inimigoee, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosC2"));

                inimigosC2.get(p).vezes(1.5f);

                esplosaoArrayObj.add(new Esplosao(context, inimigosC2.get(p), asset, context.getResources(), 0.8f, "inimigosC2", p));

            }


            inimigosC2.get(p).setValor(String.valueOf("v"));
            inimigosC2.get(p).setVida(vidaCE * dificuldade);
            inimigosC2.get(p).setRecoverVida(vidaCE * dificuldade);
            inimigosC2.get(p).setTiroNave(inimigosC2.get(p).criarTiros(inimigosC2.get(p), R.drawable.tirocnorm, 5, asset, "tiroc.obj", R.drawable.tironaveboss, context.getResources()));
            inimigosC2.get(p).setTipo("C");
            inimigosC2.get(p).setPosition(new Vetor3(-1.5f, DISTANCIA, -63f));
            inimigosC2.get(p).setRefletir(true);
            //  inimigosC.get(p).loadGLTexture();
        }

    }

    public void inimigosE2() throws IOException {

        float vidaCE = 10f;
        for (int p = 0; p < 3; p++) {
            // char v = alfabeto.charAt(p );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inimigosE2.add(new Objeto3d(context, R.drawable.inimigoenorm, asset, "inimigod.obj", R.drawable.inimigoee, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosE2"));
                inimigosE2.get(p).vezes(1.5f);
                esplosaoArrayObj.add(new Esplosao(context, inimigosE2.get(p), asset, context.getResources(), 0.8f, "inimigosE2", p));

            }
            inimigosE2.get(p).setValor(String.valueOf("v"));
            inimigosE2.get(p).setVida(vidaCE * dificuldade);
            inimigosE2.get(p).setRecoverVida(vidaCE * dificuldade);
            inimigosE2.get(p).setTiroNave(inimigosE2.get(p).criarTiros(inimigosE2.get(p), R.drawable.tiroenorm, 5, asset, "tiroc.obj", R.drawable.tironaveboss, context.getResources()));
            inimigosE2.get(p).setTipo("E");
            inimigosE2.get(p).setPosition(new Vetor3(-1.5f, DISTANCIA - 0.08f, -63f));
            inimigosE2.get(p).setRefletir(true);
            // inimigosE.get(p).loadGLTexture();
        }
    }

    public void ouro() throws IOException {

        float vidaCE = 10f;
        for (int p = 0; p < 12; p++) {
            // char v = alfabeto.charAt(p );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ouro.add(new Objeto3d(context, R.drawable.inimigoxnorm, asset, "ouro.obj", R.drawable.ouro, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "ouro"));
            }
            ouro.get(p).setValor(String.valueOf("o"));
            ouro.get(p).setVida(10);
            ouro.get(p).setTipo("o");
            ouro.get(p).setPosition(new Vetor3(-1000.5f, DISTANCIA - 0.08f, -63f));
           // ouro.get(p).setRefletir(true);
            ouro.get(p).setCores(new Vetor3(1f,1f,1f));
            // inimigosE.get(p).loadGLTexture();
        }
    }

    public void ataqueEspecial() throws IOException {
        float vidaX = 20f;

        for (int p = 0; p < 1; p++) {
            char v = alfabeto.charAt(p);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ataqueEspecial.add(new Objeto3d(context, R.drawable.inimigoxnorm, asset, "sp.obj", R.drawable.esppx, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                ataqueEspecial.get(p).vezes(6f);
                ataqueEspecial.get(p).setValor(String.valueOf(v));
                ataqueEspecial.get(p).setVida(vidaX * dificuldade);
                ataqueEspecial.get(p).setRecoverVida(vidaX * dificuldade);
                ataqueEspecial.get(p).setId(p);
                ataqueEspecial.get(p).setTiroNave(ataqueEspecial.get(p).criarTiros(ataqueEspecial.get(p), R.drawable.tiroxxnorm, 5, asset, "tiroc.obj", R.drawable.tiroxx, context.getResources()));
                ataqueEspecial.get(p).setRefletir(true);
                ataqueEspecial.get(p).setAtivado(false);
                ataqueEspecial.get(p).setMover("nulo");
                ataqueEspecial.get(p).setAtivado(false);
                ataqueEspecial.get(p).setOrigem("Fenix2");

                //  inimigosX.get(p).loadGLTexture();
            }

        }


        ataqueEspecial.get(0).setPosition(new Vetor3(0.5f,
                DISTANCIA + 0.03f, -65));

//        ataqueEspecial.get(1).setPosition(new Vetor3(0.0f,
//                DISTANCIA + 0.03f, -67));
//
//        ataqueEspecial.get(2).setPosition(new Vetor3(-0.5f,
//                DISTANCIA + 0.03f, -69));

    }


    public int indexFase() {

        String[] fasex = String.valueOf(fase).split("");
        int indice = 1;
        if (fasex.length == 1) {
            indice = 0;
        } else {
            indice = Integer.parseInt(fasex[0]);
        }
        return indice;
    }


    public void inimigosX() throws IOException {
        float vidaX = 20f;

        for (int p = 0; p < this.quantidadeDeElementos; p++) {
            char v = alfabeto.charAt(p);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inimigosX.add(new Objeto3d(context, R.drawable.inimigoxnorm, asset, "inimigox.obj", R.drawable.inimigox, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "inimigosX"));

                inimigosX.get(p).setValor(String.valueOf(v));
                inimigosX.get(p).setVida(vidaX * dificuldade);
                inimigosX.get(p).setRecoverVida(vidaX * dificuldade);
                inimigosX.get(p).setId(p);
                inimigosX.get(p).setTiroNave(inimigosX.get(p).criarTiros(inimigosX.get(p), R.drawable.tiroxxnorm, 5, asset, "tiroc.obj", R.drawable.tiroxx, context.getResources()));
                inimigosX.get(p).setRefletir(true);
                inimigosX.get(p).setAtivado(false);
                //  inimigosX.get(p).loadGLTexture();
            }
            esplosaoArrayObj.add(new Esplosao(context, inimigosX.get(p), asset, context.getResources(), 0.8f, "inimigosX", p));
        }


        inimigosX.get(0).setPosition(new Vetor3(0.5f,
                DISTANCIA + 0.03f, -65));

        inimigosX.get(1).setPosition(new Vetor3(0.0f,
                DISTANCIA + 0.03f, -67));

        inimigosX.get(2).setPosition(new Vetor3(-0.5f,
                DISTANCIA + 0.03f, -69));

    }


    public void asteroide() throws IOException {

        float vidaAst = 40f;
        for (int p = 0; p < this.quantidadeDeElementos; p++) {
            char v = alfabeto.charAt(p);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                asteroide.add(new Objeto3d(context, R.drawable.asteroidenorm, asset, "nuvem.obj", R.drawable.nuvem, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "asteroide"));
                asteroide.get(p).setPosition(new Vetor3(0, DISTANCIA, -1 * (69 - p)));

                esplosaoArrayObj.add(new Esplosao(context, asteroide.get(p), asset, context.getResources(), 0.8f, "asteroide", p));

            }
            asteroide.get(p).setValor(String.valueOf(v));
            asteroide.get(p).setVida(vidaAst * dificuldade);
            asteroide.get(p).setRecoverVida(vidaAst * dificuldade);
            asteroide.get(p).setRefletir(true);
            //   asteroide.get(p).loadGLTexture();
        }


        asteroide.get(0).setPosition(new Vetor3(-0.5f,
                DISTANCIA, -68f));
        asteroide.get(1).setPosition(new Vetor3(0.0f,
                DISTANCIA, -67));

        asteroide.get(2).setPosition(new Vetor3(0.5f,
                DISTANCIA, -66));

    }


    public void boss() throws IOException {


        float vidaBos = 1500f;

        switch (chefeDafase) {

            case 0:
                boss.set(chefeDafase, new Objeto3d(context, R.drawable.bossxanorm, asset, "inimigoc.obj", R.drawable.inimigoeea, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "boss"));
                boss.get(chefeDafase).vezes(5);
                boss.get(chefeDafase).setTiroNave(boss.get(chefeDafase).criarTiros(boss.get(chefeDafase), R.drawable.tirocnorm, 20, asset, "tiroc.obj", R.drawable.tiroe, context.getResources()));
                boss.get(chefeDafase).setCores(new Vetor3(1f, 0, 0));
                break;
            case 1:
                boss.set(chefeDafase, new Objeto3d(context, R.drawable.bossxanorm, asset, "inimigod.obj", R.drawable.inimigod, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "boss"));
                boss.get(chefeDafase).vezes(7);
                boss.get(chefeDafase).setValor(String.valueOf("BOSS"));
                boss.get(chefeDafase).setTiroNave(boss.get(chefeDafase).criarTiros(boss.get(chefeDafase), R.drawable.tirocnorm, 4, asset, "tiroc.obj", R.drawable.tironaveboss, context.getResources()));
                boss.get(chefeDafase).setCores(new Vetor3(1f, 0f, 0));

                for (Objeto3d o : boss.get(chefeDafase).getTiroNave()) {
                    o.vezes(4);

                }
                break;
            case 2:
                boss.set(chefeDafase, new Objeto3d(context, R.drawable.bossxanorm, asset, "bossxa.obj", R.drawable.bossxab, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "boss"));
                boss.get(chefeDafase).vezes(7);
                boss.get(chefeDafase).setValor(String.valueOf("BOSS"));
                boss.get(chefeDafase).setTiroNave(boss.get(chefeDafase).criarTiros(boss.get(chefeDafase), R.drawable.tirocnorm, 20, asset, "tiroc.obj", R.drawable.tiroc, context.getResources()));
                boss.get(chefeDafase).setCores(new Vetor3(1f, 0, 0));

                break;
            case 3:
                boss.set(chefeDafase, new Objeto3d(context, R.drawable.bossxanorm, asset, "inimigox.obj", R.drawable.inimigoxb, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "boss"));
                boss.get(chefeDafase).vezes(5);
                boss.get(chefeDafase).setTiroNave(boss.get(chefeDafase).criarTiros(boss.get(chefeDafase), R.drawable.tirocnorm, 20, asset, "inimigox.obj", R.drawable.inimigox, context.getResources()));
                boss.get(chefeDafase).setCores(new Vetor3(1f, 0f, 0));
                for (Objeto3d o : boss.get(chefeDafase).getTiroNave()) {
                    o.vezes(0.2f);

                }
                break;
            case 4:
                boss.set(chefeDafase, new Objeto3d(context, R.drawable.bossxanorm, asset, "bossxa.obj", R.drawable.bossxab, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "boss"));
                boss.get(chefeDafase).vezes(7);
                boss.get(chefeDafase).setValor(String.valueOf("BOSS"));
                boss.get(chefeDafase).setTiroNave(boss.get(chefeDafase).criarTiros(boss.get(chefeDafase), R.drawable.tirocnorm, 20, asset, "tiroc.obj", R.drawable.tiroc, context.getResources()));
                boss.get(chefeDafase).setCores(new Vetor3(1f, 0, 0));

                break;
            case 5:
                boss.set(chefeDafase, new Objeto3d(context, R.drawable.bossxanorm, asset, "inimigod.obj", R.drawable.inimigod, new Vetor3(escala * 5f, escala * 5f, escala * 5f), "boss"));
                boss.get(chefeDafase).vezes(4);
                boss.get(chefeDafase).setValor(String.valueOf("BOSS"));
                boss.get(chefeDafase).setTiroNave(boss.get(chefeDafase).criarTiros(boss.get(chefeDafase), R.drawable.tirocnorm, 5, asset, "tiroc.obj", R.drawable.tironaveboss, context.getResources()));

                boss.get(chefeDafase).setCores(new Vetor3(1f, 0, 0));

                for (Objeto3d o : boss.get(chefeDafase).getTiroNave()) {
                    o.vezes(3);

                }


                break;

        }


        boss.get(chefeDafase).setVida(vidaBos * dificuldade);
        boss.get(chefeDafase).setRecoverVida(vidaBos * dificuldade);
        boss.get(chefeDafase).setBoss(true);
        boss.get(chefeDafase).setPosition(new Vetor3(0f,
                DISTANCIA + 0.03f, -1000f));
        boss.get(chefeDafase).setRefletir(true);
        //boss.get(fase).loadGLTexture();
        //bossAlt=boss.get(fase);
        esplosaoArrayObj.add(new Esplosao(context, boss.get(chefeDafase), asset, context.getResources(), 2.8f, "boss", 0));

    }


    public void carregarFase(int stg) throws IOException {


        switch (stg) {

            case 0:
                if (objetosCenario.get(0) == null) {
                    ArrayList<Objeto3d> cena;
                    cena = new ArrayList<>();
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "arvores.obj", R.drawable.nuvem_mar, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(0).setValor(String.valueOf("A"));
                    cena.get(0).setVida(150f * dificuldade);
                    cena.get(0).setRecoverVida(150f * dificuldade);
                    cena.get(0).setTransparente(true);
                    cena.get(0).setRefletir(true);

//
//
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "arvores.obj", R.drawable.nuvem_mar, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(1).setValor(String.valueOf("A"));
                    cena.get(1).setVida(150f * dificuldade);
                    cena.get(1).setRecoverVida(150f * dificuldade);
                    cena.get(1).setPosition(new Vetor3(0.6f, 14.9f, -64));
                    cena.get(1).setTransparente(true);
                    cena.get(1).setRefletir(true);
                    //  cena.get(0).loadGLTexture();
                    //   cena.get(1).loadGLTexture();
                    objetosCenario.set(0, cena);

                }
                if (niveis.get(0) == null) {
                    niveis.set(0, new Objeto3d(context, R.drawable.predionorm, asset, "n.obj", R.drawable.sd, new Vetor3(escala * 2, escala, escala), ""));
                    niveis2.set(0, new Objeto3d(context, R.drawable.predionorm, asset, "n.obj", R.drawable.sd, new Vetor3(escala * 2, escala, escala), ""));

                }
                break;
            case 1:
                if (objetosCenario.get(1) == null) {
                    ArrayList<Objeto3d> cena;

                    cena = new ArrayList<>();
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "arvores.obj", R.drawable.nuvem_mar, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(0).setValor(String.valueOf("A"));
                    cena.get(0).setVida(150f * dificuldade);
                    cena.get(0).setRecoverVida(150f * dificuldade);
                    cena.get(0).setTransparente(true);
                    cena.get(0).setRefletir(true);
//
//
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "arvores.obj", R.drawable.nuvem_mar, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(1).setValor(String.valueOf("A"));
                    cena.get(1).setVida(150f * dificuldade);
                    cena.get(1).setRecoverVida(150f * dificuldade);
                    cena.get(1).setPosition(new Vetor3(0.6f, 14.9f, -64));
                    cena.get(1).setTransparente(true);
                    cena.get(1).setRefletir(true);
                    //  cena.get(0).loadGLTexture();
                    //  cena.get(1).loadGLTexture();
                    objetosCenario.set(1, cena);
                }
                if (niveis.get(1) == null) {
                    niveis.set(1, new Objeto3d(context, R.drawable.nivelnorm, asset, "nb.obj", R.drawable.bg, new Vetor3(escala * 2, escala, escala), ""));
                    niveis2.set(1, new Objeto3d(context, R.drawable.nivelnorm, asset, "nb.obj", R.drawable.bg, new Vetor3(escala * 2, escala, escala), ""));

                }
                break;
            case 3:
                if (objetosCenario.get(3) == null) {
                    ArrayList<Objeto3d> cena;

                    cena = new ArrayList<>();
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "terrenotop.obj", R.drawable.eruptiona, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(0).setValor(String.valueOf("A"));
                    cena.get(0).setVida(150f * dificuldade);
                    cena.get(0).setRecoverVida(150f * dificuldade);
                    cena.get(0).setPosition(new Vetor3(0.6f, 14.9f, cena.get(0).getPosition().z));

                    cena.get(0).setTransparente(true);
                    cena.get(0).setRefletir(true);
//
//
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "terrenotop.obj", R.drawable.eruptiona, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(1).setValor(String.valueOf("A"));
                    cena.get(1).setVida(150f * dificuldade);
                    cena.get(1).setRecoverVida(150f * dificuldade);
                    cena.get(1).setPosition(new Vetor3(0.6f, 14.9f, -64));
                    cena.get(1).setTransparente(true);
                    cena.get(1).setRefletir(true);
                    //   cena.get(0).loadGLTexture();
                    //   cena.get(1).loadGLTexture();
                    objetosCenario.set(3, cena);
                }

                if (niveis.get(3) == null) {
                    niveis.set(3, new Objeto3d(context, R.drawable.nivelnorm, asset, "n.obj", R.drawable.nivelbb, new Vetor3(escala * 2, escala, escala), ""));
                    niveis2.set(3, new Objeto3d(context, R.drawable.nivelnorm, asset, "n.obj", R.drawable.nivelbb, new Vetor3(escala * 2, escala, escala), ""));

                }
                break;
            case 2:
                if (objetosCenario.get(2) == null) {
                    ArrayList<Objeto3d> cena;

                    cena = new ArrayList<>();
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "terrenotop.obj", R.drawable.pedra, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(0).setValor(String.valueOf("A"));
                    cena.get(0).setVida(150f * dificuldade);
                    cena.get(0).setRecoverVida(150f * dificuldade);
                    cena.get(0).setPosition(new Vetor3(0.6f, 15.9f, cena.get(0).getPosition().z));

                    cena.get(0).setTransparente(true);
                    cena.get(0).setRefletir(true);

//
//
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "arvores.obj", R.drawable.nuvem_mar, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(1).setValor(String.valueOf("A"));
                    cena.get(1).setVida(150f * dificuldade);
                    cena.get(1).setRecoverVida(150f * dificuldade);
                    cena.get(1).setPosition(new Vetor3(0.6f, 15.9f, -64.2f));
                    cena.get(1).setTransparente(true);
                    cena.get(1).setRefletir(true);
                    //   cena.get(0).loadGLTexture();
                    //   cena.get(1).loadGLTexture();
                    objetosCenario.set(2, cena);
                }

                if (niveis.get(2) == null) {
                    niveis.set(2, new Objeto3d(context, R.drawable.nivelnorm, asset, "n.obj", R.drawable.mar, new Vetor3(escala * 2, escala, escala), ""));
                    niveis2.set(2, new Objeto3d(context, R.drawable.nivelnorm, asset, "n.obj", R.drawable.mar, new Vetor3(escala * 2, escala, escala), ""));

                }
                break;
            case 4:
                if (objetosCenario.get(4) == null) {
                    ArrayList<Objeto3d> cena;

                    cena = new ArrayList<>();
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "arvores.obj", R.drawable.spc, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(0).setValor(String.valueOf("A"));
                    cena.get(0).setVida(150f * dificuldade);
                    cena.get(0).setRecoverVida(150f * dificuldade);
                    cena.get(0).setPosition(new Vetor3(0.6f, 15.9f, cena.get(0).getPosition().z));

                    cena.get(0).setTransparente(true);
                    cena.get(0).setRefletir(true);

//
//
                    cena.add(new Objeto3d(context, R.drawable.objetosnorm, asset, "arvores.obj", R.drawable.spc, new Vetor3(escala * 5f, escala * 5f, escala * 5f), ""));
                    cena.get(1).setValor(String.valueOf("A"));
                    cena.get(1).setVida(150f * dificuldade);
                    cena.get(1).setRecoverVida(150f * dificuldade);
                    cena.get(1).setPosition(new Vetor3(0.6f, 15.9f, -64.2f));
                    cena.get(1).setTransparente(true);
                    cena.get(1).setRefletir(true);
                    //   cena.get(0).loadGLTexture();
                    //   cena.get(1).loadGLTexture();
                    objetosCenario.set(4, cena);
                }

                if (niveis.get(4) == null) {
                    niveis.set(4, new Objeto3d(context, R.drawable.nivelnorm, asset, "n.obj", R.drawable.diff, new Vetor3(escala * 2, escala, escala), ""));
                    niveis2.set(4, new Objeto3d(context, R.drawable.nivelnorm, asset, "n.obj", R.drawable.diff, new Vetor3(escala * 2, escala, escala), ""));

                }
                break;

        }
    }


    public void selecao(long ultimaPassada) {
        ConvertBitimap convertBitimap = new ConvertBitimap();

        int p = bolhas.size();

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


    public void carregarCronologia(int nivel) throws IOException {

        this.time = 0;
        this.timeModoC = 0;
        this.timeModoE = 0;
        this.timeModoB = 0;
        this.timeModoBoss = 0;
        this.timeModoX = 0;
        carregaModoX = true;
        carregaModoBoss = true;
        //   cronograma = new ArrayList<>();
        Cronograma c;
        Fase f = new Fase();
        nivelTiroTempo = 3;
        nivelTiro = 60;
        String[] fasex = String.valueOf(nivel).split("");
        int indice = 1;

        if (fasex.length > 1) {
            indice = Integer.parseInt(fasex[1]);
            indiceLevel = Integer.parseInt(fasex[0]);

//            if (indiceLevel == 0) {
//                chefeDafase = 10;
//            }

        } else {
            indice = Integer.parseInt(fasex[0]);
        }
        int faseBoss = (fase + 1) / 10;

        chefeDafase = faseBoss != 0 ? faseBoss - 1 : 0;

        carregarFase(indiceLevel);
        cena = objetosCenario.get(indiceLevel);
        //     this.musica = MediaPlayer.create(context, R.raw.musicc);

        cronograma = new ArrayList<>();
        cronograma = f.gerarFase(fase);
        dificult--;
        ArrayList<Integer> selecao = new ArrayList<>();

        for (Cronograma cros : cronograma) {
            boolean tem = true;
            for (int s : selecao) {
                if (s != cros.getId()) {
                    tem = false;
                } else {
                    tem = true;
                    break;
                }
            }
            if (!tem) {
                selecao.add(cros.getId());
            }

            if (selecao.size() == 0)
                selecao.add(cros.getId());


        }

        if (inimigosA.size() == 0)
            inimigosA();

        if (inimigosB.size() == 0)
            inimigosB();

        if (inimigosC.size() == 0)
            inimigosC();

        if (inimigosX.size() == 0)
            inimigosX();

        if (ouro.size() == 0)
            ouro();


//        if (asteroide.size() == 0)
//            asteroide();

        if (inimigosE.size() == 0)
            inimigosE();

        if (boss.get(chefeDafase) == null) {
            boss();
        }

        if (inimigosC2.size() == 0)
            inimigosC2();

        if (inimigosE2.size() == 0)
            inimigosE2();


        inimigosGerados = 0;
        inimigosEliminados = 0;
        for (Cronograma crono : cronograma) {
            inimigosGerados += verificarLista(crono.getId());
        }


        try {


            switch (indice) {
                case 0:
                    nivelTiroTempo = 5;

                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);  // ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(0, 10, ceoZ));

                    terreno2 = niveis2.get(indiceLevel);  // ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(0, 10, ceoZ2));

                    break;


                case 1:
                    ceoZ = -62;
                    ceoZ2 = -103;

                    terreno = niveis.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-5, 10, ceoZ));


                    terreno2 = niveis2.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-5, 10, ceoZ2));

                    break;
                case 2:

                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);                //  ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-10, 10, ceoZ));

                    terreno2 = niveis2.get(indiceLevel);                //  ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-10, 10, ceoZ2));

                    break;

                case 3:
                    nivelTiroTempo = 5;


                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-15, 10, ceoZ));

                    terreno2 = niveis2.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-15, 10, ceoZ2));

                    break;


                case 4:
                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-20, 10, ceoZ));

                    terreno2 = niveis2.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-20, 10, ceoZ2));

                    break;
                case 5:
                    // this.musica = MediaPlayer.create(context, R.raw.musicd);
                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);                //  ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-25, 10, ceoZ));
                    terreno2 = niveis2.get(indiceLevel);                //  ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-25, 10, ceoZ2));

                    break;


                case 6:

                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-30, 10, ceoZ));

                    terreno2 = niveis2.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-30, 10, ceoZ2));

                    break;

                case 7:

                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-35, 10, ceoZ));
                    terreno2 = niveis2.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-35, 10, ceoZ2));

                    break;
                case 8:

                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-40, 10, ceoZ));

                    terreno2 = niveis2.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-40, 10, ceoZ2));

                    break;

                case 9:


                    ceoZ = -62;
                    ceoZ2 = -103;
                    terreno = niveis.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno.setPosition(new Vetor3(-45, 10, ceoZ));

                    terreno2 = niveis2.get(indiceLevel);                // ceu.loadGLTexture(true);
                    terreno2.setPosition(new Vetor3(-45, 10, ceoZ2));

                    break;


            }
        } catch (Exception e) {
            carregarCronologia(nivel);
        }
        //   terreno.loadGLTexture();
        /////////////////////////////////////////////////////////////////////////////////

    }


    public ArrayList<Objeto3d> splosao(Objeto3d obj) throws IOException {
        ///CARREGA OS ARQUIS 3D DO ALFABETO
        ArrayList<Objeto3d> splosaoArray;

        splosaoArray = new ArrayList<>();
        float x = obj.getTamanho().getX();
        float y = obj.getTamanho().getY();
        float z = obj.getTamanho().getZ();
        splosaoArray.add(new Objeto3d(context, R.drawable.esplosaonorm, asset, "sp.obj", R.drawable.espp, new Vetor3(x, y, z), ""));
        splosaoArray.get(0).setTransparente(true);

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


        this.disparo = MediaPlayer.create(context, R.raw.tiro);
        this.victory = MediaPlayer.create(context, R.raw.victory);
        this.disparo.setVolume(0.4f, 0.4f);

/////////////////////////////////////////
    }

    private void son(int s) {
        ////////EXECUTA OS SONS DO JOGO
        if (perdeu == false && comSons) {
            switch (s) {
                case 1:

                    if (this.disparo.isPlaying()) {


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
                                if (boom.get(0).getCurrentPosition() > 0) {
                                    boom.get(0).seekTo(0);
                                    boom.get(0).start();
                                }
                                break;
                            }
                        }
                    }

                    break;

                case 3:

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


    public boolean calculoarColisaoOuro(Objeto3d obj, Objeto3d obj2) {
        boolean vai = false;
        try {

            double d = Math.sqrt(Math.pow(obj.getPosition().x - (obj2.getPosition().x), 2)
                    + Math.pow(obj.getPosition().z - (obj2.getPosition().z), 2)
                    + Math.pow(obj.getPosition().y - (obj2.getPosition().y), 2));


                 if (d < 0.2f) {
                     obj2.getPosition().setX(-1000f);
                     resgateOuro+=100;
                }


        }catch (Exception e) {
            return false;
        }
        return vai;

    }

    public boolean calculoarColisaoI(Objeto3d obj, Objeto3d obj2, boolean danoTotal) {
        boolean vai = false;
        try {

            if (!obj.isEsplodiu()) {
                double d = Math.sqrt(Math.pow(obj.getPosition().x - (obj2.getPosition().x), 2)
                        + Math.pow(obj.getPosition().z - (obj2.getPosition().z), 2)
                        + Math.pow(obj.getPosition().y - (obj2.getPosition().y), 2));

                //   Fenix.setPosition( new Vetor3( 0, 18,-62 ));

                if (!obj2.getNomeRef().equals("") && !obj.getNomeRef().equals("") && !esplodirNave && !invulneravel) {
                    if (d < 0.2f) {
                        obj.setEsplodirNave(true);
                        obj2.setEsplodirNave(true);
                        if (!obj.getOrigem().equals("BOSS"))
                            obj.getPosition().z = -1000f;
                        obj.setAtivado(false);
                        obj.setAbatido(true);
                        esplodirNave = true;
                        vida--;
                        barraDeEnergia = 100;
                    }
                } else if (obj2 == Fenix && !esplodirNave && !invulneravel) {
                    if (d < 0.2f) {
                        obj2.setImpacto(true);
                        if (!obj.getOrigem().equals("BOSS"))
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
                    if (d < 0.2f && fogo && obj2.getOrigem().equals("Fenix1")) {
                        //       for (Objeto3d p : tiros) {
                        // if (obj2.getOrigem().equals("Fenix1")) {
                        obj.setVida(obj.getVida() - danoNoInimigo);
                        // obj.vezes(0.1f);
                        obj.setImpacto(true);
                        if (obj.getVida() <= 0) {
                            if (!obj.isAbatido()) {
                                inimigosEliminados++;
                                rastreio.add(obj.getNomeRef());
                            }
                            obj.setEsplodirNave(true);
                            obj.setAtivado(false);
                            obj.setAbatido(true);
                            obj.setAbatidoPelaNave(true);
                            positiomAnteriorBoss = new Vetor3(obj.getPosition());

                        }

                        obj2.setPosition(new Vetor3(-1000, obj2.getPosition().y, -1002));
                        ;
                        //  }
                        // }
                    }

                    if (d < 3f && fogo && obj2.getOrigem().equals("Fenix2")) {
                        //       for (Objeto3d p : tiros) {
                        // if (obj2.getOrigem().equals("Fenix1")) {
                        obj.setVida(obj.getVida() - (danoNoInimigo * 10));
                        // obj.vezes(0.1f);
                        obj.setImpacto(true);
                        if (obj.getVida() <= 0) {
                            if (!obj.isAbatido()) {
                                inimigosEliminados++;
                                rastreio.add(obj.getNomeRef());
                            }
                            obj.setEsplodirNave(true);
                            obj.setAtivado(false);
                            obj.setAbatido(true);
                            obj.setAbatidoPelaNave(true);
                            positiomAnteriorBoss = new Vetor3(obj.getPosition());

                            limparTiros(inimigosX);
                            limparTiros(inimigosA);
                            limparTiros(inimigosB);
                            limparTiros(inimigosC);
                            limparTiros(inimigosE);
                            limparTiros(inimigosE2);
                            limparTiros(inimigosC2);


                        }

                        //      obj2.setPosition(new Vetor3(-1000, obj2.getPosition().y, -1002));
                        ;
                        //  }
                        // }
                    }

                }
            }

        } catch (Exception e) {
            return false;
        }
        return vai;
        /////////////////////////////////
    }

    public void limparTiros(ArrayList<Objeto3d> x) {
        for (Objeto3d ob : x) {
            for (Objeto3d obb : ob.getTiroNave()) {
                obb.getPosition().z = -57;
            }

        }
    }

    private void esplodirNave(Objeto3d obj, Esplosao esplosao) {

        if (obj.isEsplodirNave() && !invulneravel) {
            int limit = 13;
            if (obj.isBoss()) {
                limit = 39;
            }

            if (obj.getTimeEsplosaoNave() < limit) {
                try {
                    if (obj.getTimeEsplosaoNave() == 0) {
                        son(2);
                    }

                    if (obj.isBoss()) {
                        esplosao.getSplosaoArrayNave().get(0).setPosition(positiomAnteriorBoss);
                    } else {
                        esplosao.getSplosaoArrayNave().get(0).setPosition(new Vetor3(obj.getPosition().x, obj.getPosition().y, obj.getPosition().z));

                    }
                    esplosao.getSplosaoArrayNave().get(0).setMudarTamanho(true);

                    obj.setTimeEsplosaoNave(obj.getTimeEsplosaoNave() + 1);
                } catch (Exception e) {
                    Log.e("TC", e.getMessage());
                }

            } else {

                obj.setEsplodirNave(false);
                if (indexOuro >= ouro.size()) {
                    indexOuro = 0;
                }
                ouro.get(indexOuro).setPosition(new Vetor3(obj.getPosition().x,obj.getPosition().y,obj.getPosition().z));//obj.getPosition()
                obj.setTimeLineInidvidual(0);
                obj.setTimeEsplosaoNave(0);
                obj.setEsplosaoNaveId(0);
                obj.getPosition().setZ(-1000);
                obj.getPosition().setX(obj.getPosition().x);
                obj.setVida(obj.getRecoverVida());
                obj.setGiro((float) (0));


                obj.setGiroPosition(new Vetor3(0, 0, 0));

                indexOuro++;
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

            if (i < 3) {
                moverInimigoC(0, inimigosE2.get(i).getTimeLineInidvidual(), i, inimigosE2.get(i), 'C');
                moverInimigoC(1, inimigosC2.get(i).getTimeLineInidvidual(), i, inimigosC2.get(i), 'D');
            }

        }
////////////MONITORA O JOGO REFERENTE A COLISÃO ,VITÓRIA E DERROTA
        if (esplodirNave) {
            if (timeEsplosaoNave < 26) {

                splosaoArrayNave.get(0).setPosition(new Vetor3(Fenix.getPosition().x, 15, Fenix.getPosition().z));

                timeEsplosaoNave++;
            } else {
                esplodirNave = false;
                timeEsplosaoNave = 0;
                esplosaoNaveId = 0;

                splosaoArrayNave.get(0).setPosition(new Vetor3(-100, -100, -100));

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
                    if (giroyFenix < 25) {
                        giroyFenix += 3.6f;

                    }
                    break;
                case 2:
                    if (giroyFenix > -25) {
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


            //  Fenix.setGiroPosition(new Vetor3(0, 0, giroyFenix));

            for (int i = 0; i < ataqueEspecial.size(); i++) {
                if (ataqueEspecial.get(i).getTime() >= 750) {
                    ataqueEspecial.get(i).setMover("nulo");
                    ataqueEspecial.get(i).setTime(0);
                    btEspecial.setTimeBtEspecial(0);
                    acionado = false;


                }

                switch (ataqueEspecial.get(i).getMover()) {
                    case "nulo":
                        ataqueEspecial.get(i).setPosition(new Vetor3(Fenix.getPosition().getX(), Fenix.getPosition().getY(), (Fenix.getPosition().getZ())));
                        break;
                    case "disparar":

                        ataqueEspecial.get(i).setPosition(new Vetor3(ataqueEspecial.get(i).getPosition().getX(),
                                ataqueEspecial.get(i).getPosition().getY(),
                                ataqueEspecial.get(i).getPosition().getZ() - (0.08f)));

                        ataqueEspecial.get(i).setTime(ataqueEspecial.get(i).getTime() + 1);
                        break;
                }
            }


            Fenix.getGiroPosition().z = giroyFenix;
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


            //mmmmm
///////////////////////////////////////////////////////////////////////////////

            if (fogo) {
                atirar();
            }


        } else {
            //  this.pause = false;
            this.perdeu = true;


        }
//////////////////////////////////////////
        if (retornarQuadro) {


            retornarQuadro = false;

        }


    }

    private void inidiceEsquadCE(Objeto3d objArray, int modo, int i) {
        if (objArray.getTimeLineInidvidual() == 0 && objArray.isAtivado() || objArray.getPosition().z <= -1000f && objArray.isAtivado()) {
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
                    if (objArray.isAtivado()) {
                        objArray.setPosition(new Vetor3(1.5f, DISTANCIA, -63.6f));
                        objArray.setTipo("E");
                    }

                    break;

                case 3:
                    if (objArray.isAtivado()) {
                        objArray.setPosition(new Vetor3(-1.5f, DISTANCIA, -63.6f));
                        objArray.setTipo("F");
                    } else {

                        objArray.setPosition(new Vetor3(-1000.5f, DISTANCIA, -63.6f));
                        objArray.setTipo("N");
                    }

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
        ceoZ = -62;
        ceoZ2 = -65;
        localz = -1000f;
        vida = 50000;
        barraDeEnergia = 100;
        try {
            carregarCronologia(fase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newLevel() {

        parado = true;
        this.venceu = true;
        localz = -1000f;
        vida = 50000;
        barraDeEnergia = 100;
        try {
            timeLine = 0;
            carregarCronologia(fase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void newLevelK() {

        parado = true;
        this.venceu = true;
        localz = -1000f;
        vida = 50000;
        barraDeEnergia = 100;
        timeLine = 0;

    }


    public int verificarLista(int c) {
        ArrayList<Objeto3d> objeto3ds = new ArrayList<>();
        switch (c) {
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
                objeto3ds = inimigosX;

                break;
            case 4:
                objeto3ds = asteroide;

                break;
            case 5:
                objeto3ds = inimigosE;

                break;
            case 6:
                objeto3ds = inimigosE2;

                break;
            case 7:
                objeto3ds = inimigosC2;

                break;

        }

        return objeto3ds.size();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void timeLine() {
        timeLine++;
        alterarNivelDeTiro(60);

        for (int i = 0; i < cronograma.size(); i++) {
            Cronograma c = cronograma.get(i);
            ArrayList<Objeto3d> objeto3ds = new ArrayList<>();
            String nome = "";
            boolean ativo = false;
            //   if (!c.isBoss()) {
            //  Collections.sort(cronograma);


            if (c.getTimeIN() <= timeLine || cronograma.size() == 1 /*&& c.getTimeOUT() > timeLine*/) {

                if (c.isFim() && cronograma.size() == 1 && dificult > 0 && !bossEliminado) {

                    cronograma = new ArrayList<>();
                    cronograma = new Fase().gerarFase(chefeDafase);
                    dificult--;
                    for (Cronograma o : cronograma) {
                        o.setTimeIN(o.getTimeIN() + (timeLine + 50));
                        o.setTimeOUT(o.getTimeOUT() + (timeLine + 50));
                    }

                }

                if (c.isFim() && cronograma.size() == 1) {

                    vitoria();

                } else if (c.isBoss() && cronograma.size() == 2) {

                    ativarBoss[100 - c.getId()] = true;
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
                        case 6:
                            objeto3ds = inimigosE2;
                            nome = "inimigosE2";

                            break;
                        case 7:
                            objeto3ds = inimigosC2;
                            nome = "inimigosC2";

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
                        //if (cronograma.size() == 1) {

//                        for (int g=1;g<cronograma.size();g++) {
//                            if(cronograma.get(g).getTimeIN()<=timeLine && cronograma.get(0).getId()==cronograma.get(g).getId() && cronograma.size()>2){
//                                Cronograma a = cronograma.get(0);
//                                a.setTimeIN(a.getTimeIN()+50);
//
//                                cronograma.add(a);
//                                cronograma.remove(0);
//
//                            }
//                        }

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
                                    case 6:
                                        letra = "C";
                                        break;
                                    case 1:
                                    case 7:
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
                                    case 6:
                                        letrae = "C";
                                        break;
                                    case 1:
                                    case 7:
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
                    case 6:
                        objeto3ds = inimigosE2;
                        nome = "inimigosE2";

                        break;
                    case 7:
                        objeto3ds = inimigosC2;
                        nome = "inimigosC2";

                        break;


                }
                if (c.getId() > -1 && !c.isBoss()) {
                    vaiPraCena[c.getId()] = false;
                }
                for (Objeto3d o : objeto3ds) {
                    o.setEsplodirNave(true);
                    o.setVida(0);
                    o.setTimeEsplosaoNave(0);

                    String finalNome = nome;
                    Esplosao esp = filtro(finalNome, objeto3ds.indexOf(o));
                    esplodirNave(o, esp);

                }
                c.setEmMovimento(false);
                if (c.getId() > -1 && c.getTimeOUT() <= timeLine) {


                    cronograma.remove(c);

                    cronograma.get(0).setTimeIN(timeLine);
                }
                break;
            }


        }

        for (int p = 0; p < 6; p++) {
            if (inimigosC.size() > 0) {
                if (inimigosC.get(p).getVida() <= 0) {
                    Esplosao esp = filtro("inimigosC", p);
                    esplodirNave(inimigosC.get(p), esp);
                }
            }


            impactoTiro(ataqueEspecial, inimigosC.get(p), acionado);
            impactoTiro(tiros, inimigosC.get(p), true);


        }
        for (int p = 0; p < 6; p++) {
            if (inimigosE.size() > 0) {
                if (inimigosE.get(p).getVida() <= 0) {
                    String finalNome = "inimigosE";
                    Esplosao esp = filtro(finalNome, p);
                    esplodirNave(inimigosE.get(p), esp);
                }
            }


            impactoTiro(ataqueEspecial, inimigosE.get(p), acionado);
            impactoTiro(tiros, inimigosE.get(p), true);
        }


        for (int p = 0; p < inimigosC2.size(); p++) {
            if (inimigosC2.size() > 0) {
                if (inimigosC2.get(p).getVida() <= 0) {
                    Esplosao esp = filtro("inimigosC2", p);
                    esplodirNave(inimigosC2.get(p), esp);
                }
            }


            impactoTiro(ataqueEspecial, inimigosC2.get(p), acionado);
            impactoTiro(tiros, inimigosC2.get(p), true);

        }
        for (int p = 0; p < inimigosE2.size(); p++) {
            if (inimigosE2.size() > 0) {
                if (inimigosE2.get(p).getVida() <= 0) {
                    String finalNome = "inimigosE2";
                    Esplosao esp = filtro(finalNome, p);
                    esplodirNave(inimigosE2.get(p), esp);
                }
            }


            impactoTiro(ataqueEspecial, inimigosE2.get(p), acionado);
            impactoTiro(tiros, inimigosE2.get(p), true);
        }

        for (Objeto3d o : ouro) {
            calculoarColisaoOuro(Fenix, o);
        }
        for (int p = 0; p < inimigosA.size(); p++) {
            if (inimigosA.get(p).getVida() <= 0) {
                String finalNome = "inimigosA";
                Esplosao esp = filtro(finalNome, p);
                esplodirNave(inimigosA.get(p), esp);
            }
            moverInimigoA(0, 0, 0, p);
            calculoarColisaoI(inimigosA.get(p), Fenix, true);


            impactoTiro(ataqueEspecial, inimigosA.get(p), acionado);
            impactoTiro(tiros, inimigosA.get(p), true);

            for (Objeto3d pp : inimigosA.get(p).getTiroNave()) {
                calculoarColisaoI(pp, Fenix, false);
            }


        }

        for (int p = 0; p < inimigosX.size(); p++) {
            if (inimigosX.get(p).getVida() <= 0) {
                String finalNome = "inimigosX";
                Esplosao esp = filtro(finalNome, p);
                esplodirNave(inimigosX.get(p), esp);
            }

            moverInimigoX(modoX, timeModoX, 0, p);
            calculoarColisaoI(inimigosX.get(p), Fenix, true);

            for (Objeto3d pp : inimigosX.get(p).getTiroNave()) {

                calculoarColisaoI(pp, Fenix, false);
            }

            for (Objeto3d pp : tiros) {
                try {
                    if (!horaDoBoss)
                        calculoarColisaoI(inimigosX.get(p), pp, false);
                } catch (Exception e) {
                }
            }


            impactoTiro(ataqueEspecial, inimigosX.get(p), acionado);
        }

        for (int p = 0; p < inimigosB.size(); p++) {
            if (inimigosB.get(p).getVida() <= 0) {
                String finalNome = "inimigosB";
                Esplosao esp = filtro(finalNome, p);
                esplodirNave(inimigosB.get(p), esp);
            }
            moverInimigoB(modoB, timeModoB, 0, p);
            calculoarColisaoI(inimigosB.get(p), Fenix, true);


            impactoTiro(ataqueEspecial, inimigosB.get(p), acionado);
            impactoTiro(tiros, inimigosB.get(p), true);
            for (Objeto3d ppp : inimigosB.get(p).getTiroNave()) {
                calculoarColisaoI(ppp, Fenix, false);

            }

        }

        for (int p = 0; p < asteroide.size(); p++) {
            if (asteroide.get(p).getVida() <= 0) {
                String finalNome = "asteroide";
                Esplosao esp = filtro(finalNome, p);
                esplodirNave(asteroide.get(p), esp);
            }


            moverAsteroide(0, 0, 0, p);
            calculoarColisaoI(asteroide.get(p), Fenix, true);


            impactoTiro(ataqueEspecial, asteroide.get(p), acionado);
            impactoTiro(tiros, asteroide.get(p), true);
        }


        if (horaDoBoss) {

            if (!boss.get(chefeDafase).isAbatido()) {
                boss.get(chefeDafase).setAtivado(true);
            }


            for (int p = 0; p < 3; p++) {
                try {

                    moverInimigoX(modoX, timeModoX, 0, p);
                } catch (Exception e) {
                }
                try {
                    moverInimigoB(modoB, timeModoB, 0, p);
                } catch (Exception e) {
                }
                try {
                    moverInimigoA(0, 0, 0, p);
                } catch (Exception e) {
                }
                try {
                    moverAsteroide(0, 0, 0, p);
                } catch (Exception e) {
                }


            }
            if (boss.get(chefeDafase).getVida() <= 0) {

                String finalNome = "boss";
                Esplosao esp = filtro(finalNome, 0);
                esplodirNave(boss.get(chefeDafase), esp);
                boss.get(chefeDafase).setAtivado(false);
                boss.get(chefeDafase).setAbatido(true);
                for (Objeto3d pp : boss.get(chefeDafase).getTiroNave()) {
                    pp.getPosition().z = -1000f;
                }
                for (Cronograma c : cronograma) {
                    if (c.isBoss()) {
                        cronograma.remove(c);
                        cronograma.get(0).setTimeIN(timeLine + 100);
                        bossEliminado = true;
                        break;
                    }
                }


            }

            for (Objeto3d pp : tiros) {
                calculoarColisaoI(boss.get(chefeDafase), pp, false);
            }
            impactoTiro(ataqueEspecial, boss.get(chefeDafase), acionado);

            moverBoss(modoBoss, timeModoBoss, 0, boss.get(chefeDafase), 0);


        }
        //  if (timeFim==0)
        moverFase();
    }

    private void impactoTiro(ArrayList<Objeto3d> ataqueEspecial, Objeto3d objeto3d, boolean acionado) {
        if (acionado) {
            for (Objeto3d pp : ataqueEspecial) {
                try {
                    calculoarColisaoI(objeto3d, pp, false);
                } catch (Exception e) {
                }
            }
        }

    }

    private void vitoria() {

        vitoria = true;
        invulneravel = true;


        BDEstatusFase statusFase = new BDEstatusFase(context);


        if (!this.victory.isPlaying() && comMusica) {
            this.victory.start();
        }


        acelerarando += 0.002f;
        // Fenix.getPosition().x+= acelerarando;
        Fenix.getPosition().z -= acelerarando;

        Fenix.setAtirando(false);
        if (Fenix.getPosition().z <= -75f) {
            //  timeFim = 0;
            rotation = 80;
            rotationy = 0;
            //      Fenix.getGiroPosition().y=0;
            acelerarando = 0;
            timeLine = 0;
            Fenix.getPosition().z = -62f;

            EstatusFase v = new EstatusFase();
            v.setId((long) fase + 1);
            v.setInimigosEliminados(inimigosEliminados);
            v.setInimigosGerados(inimigosGerados);
            v.setSaude(barraDeEnergia);
            v.setProgresso("COMPLETA");

            //    boss.get(fase).descarregar();
            //  boss.removeAll(boss);
            bossEliminado = false;
            selectFase = true;
            for (int i = 0; i < ataqueEspecial.size(); i++) {
                ataqueEspecial.get(i).setMover("nulo");
                ataqueEspecial.get(i).setTime(0);
                btEspecial.setTimeBtEspecial(0);
                acionado = false;

            }
            if (fase + 1 >= 50) {
                indiceLevel = 0;
                statusFase.zerarFase(v);
            } else {
                statusFase.atualizarFase(v);
            }


        }


    }

    private void alterarNivelDeTiro(int nivel) {

        nivelTiro = nivel;
        for (Objeto3d o : tiros) {
            o.setGiroPosition(new Vetor3(0, 0, 0));

        }

    }


    private void moverObjCenario(Objeto3d pp, int p) {

        if (pp.getPosition().z < -57) {
            pp.setPosition(new Vetor3(pp.getPosition().x, pp.getPosition().getY(), pp.getPosition().z + (velocidade / 2)));

        } else {
            pp.getPosition().setZ(-68);

        }

        girarOBJ(pp, 1000, 'y', velocidade);

    }


    private void moverBoss(int modoBoss, int time, int i, Objeto3d pp, int index) {

        if (pp.isAtivado()) {
            pp.setAbatidoPelaNave(false);


            if (pp.getPosition().z < -70f) {
                pp.setPosition(new Vetor3(0f,
                        DISTANCIA + 0.03f, -70f));

            }

            if (pp.getPosition().z < -63.5f) {
                pp.getPosition().setZ(pp.getPosition().z + velocidade);
            } else {
                carregaModoBoss = false;
                if (pp.getPosition().z >= -57f) {
                    pp.getPosition().setZ(-68f);
                }

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


                for (Objeto3d ppp : pp.getTiroNave()) {
                    calculoarColisaoI(ppp, Fenix, false);

                }
                if (true) {
                    pp.setDisparando(true);
                    switch (chefeDafase) {
                        case 3:
                            girarOBJ(pp, (1000) * -1, 'y', 15f);
                            moverObjFixoPadrao(pp, modoBoss, 'y', 15f, 1000);


                            break;
                        default:
                            moverObjFixoPadrao(pp, modoBoss, 'z', 1f, 90);
                            pp.setGiro(0);
                            break;

                    }


                }
                timeModoBoss++;
                //  tipoDisparaBoss(pp.getTiroNave(),pp.getTiroNave().get(tiroAtualDoBoss), 2, 4f);

//058 147 236
                //    disparandoOBJ(Fenix.getPosition(), pp, 30, true, 4);1
                switch (chefeDafase) {
                    case 0:
                        tipoDisparaBoss(pp.getTiroNave(), pp.getTiroNave().get(pp.getIdTiroAux()), 2, 2f);
                        pp.setIdTiroAux();
                        break;
                    case 1:
                        disparandoOBJ(Fenix.getPosition(), pp, 15, true, 2);

                        break;
                    case 2:
                        disparandoOBJ(Fenix.getPosition(), pp, 15, true, 2);

                        break;
                    case 3:
                        if (modoBoss == 2 || modoBoss == 0 || modoBoss == 1 || modoBoss == -1) {
                            tipoDisparaBoss(pp.getTiroNave(), pp.getTiroNave().get(pp.getIdTiroAux()), 2, 2f);
                            pp.setIdTiroAux();
                        }
                        for (Objeto3d o : boss.get(chefeDafase).getTiroNave()) {
                            girarOBJ(o, (1000) * -1, 'y', 15f);
                            moverObjFixoPadrao(o, 0, 'y', 15f, 1000);


                        }
                        break;
                    default:
                        disparandoOBJ(Fenix.getPosition(), pp, 15, true, 2);

                        break;

                }
            }
        } else {
            pp.getPosition().z = -70f;
        }


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
                if (pp.getPosition().x >= 1.1f)
                    pp.setDr(3);
                break;
            case 3:
                pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z));
                girarOBJ(pp, limit, dir, velocidadeG);
                if (pp.getPosition().x <= -1.1f)
                    pp.setDr(2);
                break;
            case 4:
                pp.setPosition(new Vetor3(pp.getPosition().x + velocidade, pp.getPosition().getY(), pp.getPosition().z + velocidade));
                girarOBJ(pp, (limit) * -1, dir, velocidadeG);
                if (pp.getPosition().x >= 1.1f)
                    pp.setDr(5);
                break;
            case 5:
                pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z + velocidade));
                girarOBJ(pp, limit, dir, velocidadeG);
                if (pp.getPosition().x <= -1.1f)
                    pp.setDr(4);
                break;
            case 6:
                pp.setPosition(new Vetor3(pp.getPosition().x + velocidade, pp.getPosition().getY(), pp.getPosition().z - velocidade));
                girarOBJ(pp, (limit) * -1, dir, velocidadeG);

                break;
            case 7:
                pp.setPosition(new Vetor3(pp.getPosition().x - velocidade, pp.getPosition().getY(), pp.getPosition().z - velocidade));
                girarOBJ(pp, limit, dir, velocidadeG);

                break;

            case 8:
                if (pp.getPosition().x < Fenix.getPosition().x) {
                    pp.getPosition().x += velocidade * 0.5f;
                } else {
                    pp.getPosition().x -= velocidade * 0.5f;

                }
                girarOBJ(pp, limit, dir, velocidadeG);

                break;
        }

    }


    public void moverInimigoA(int modoB, int timeB, float giroybolhas, int p) {

        if (inimigosA.get(p).isAtivado()) {
            inimigosA.get(p).setAbatidoPelaNave(false);

            inimigosA.get(p).setGiro(giroybolhas);
            inimigosA.get(p).setPosition(new Vetor3(inimigosA.get(p).getPosition().x, inimigosA.get(p).getPosition().getY(), inimigosA.get(p).getPosition().z + velocidade));

            if (inimigosA.get(p).getPosition().z <= -80) {
                inimigosA.get(p).getPosition().z = (float) ((-65 - p) - (Math.random() * 2));
                inimigosA.get(p).getPosition().x = (float) (-1 + (Math.random() * 2));
            }


            if (inimigosA.get(p).getPosition().z > -58) {


                inimigosA.get(p).setPosition(new Vetor3(-0.3f,
                        DISTANCIA, -68));

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


        if (!horaDoBoss) {
            //inimigosA.get(p).setDisparando(true);
            disparandoOBJ(Fenix.getPosition(), inimigosA.get(p), 30, true, 2);

        } else {
            if (horaDoBoss) {

                tipoDisparaBoss(inimigosA.get(0).getTiroNave(), inimigosA.get(0).getTiroNave().get(tiroAtualDoA), 1, 0.5f);
                tipoDisparaBoss(inimigosA.get(1).getTiroNave(), inimigosA.get(1).getTiroNave().get(tiroAtualDoA), 1, 0.5f);
                tipoDisparaBoss(inimigosA.get(2).getTiroNave(), inimigosA.get(2).getTiroNave().get(tiroAtualDoA), 1, 0.5f);


            }
        }


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

            inimigosB.get(p).setAbatidoPelaNave(false);


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

                    if (inimigosB.get(p).getPosition().z <= -990) {

                        switch (p) {
                            case 0:

                                inimigosB.get(0).setPosition(new Vetor3(-0.3f,
                                        DISTANCIA, -65));


                                break;
                            case 1:

                                inimigosB.get(1).setPosition(new Vetor3(0.3f,
                                        DISTANCIA, -65));


                                break;
                            case 2:


                                inimigosB.get(2).setPosition(new Vetor3(0.0f,
                                        DISTANCIA, -66));
                                break;
                        }
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

        disparandoOBJ(Fenix.getPosition(), inimigosB.get(p), 30, true, 2);

    }

    public void moverFase() {
        if (timeLine > 0 && !vitoria && !perdeu) {
            ceoZ = terreno.getPosition().z + (velocidade2 / 2);
            ceoZ2 = terreno2.getPosition().z + (velocidade2 / 2);

//            if(posteste==0 && terreno.getPosition().x>-45f){
//                terreno.getPosition().x-=(velocidade2 / 4);
//                terreno2.getPosition().x-=(velocidade2 / 4);
//
//            }else if(posteste==1 && terreno.getPosition().x<0){
//                terreno.getPosition().x+=(velocidade2 / 4);
//                terreno2.getPosition().x+=(velocidade2 / 4);
//            }
//
//            postesteTime++;
//
//            if(postesteTime>=100){
//                posteste= new Random().nextInt(4);
//                postesteTime=0;
//            }


            terreno.setPosition(new Vetor3(terreno.getPosition().x, terreno.getPosition().y, ceoZ));
            terreno2.setPosition(new Vetor3(terreno2.getPosition().x, terreno2.getPosition().y, ceoZ2));


            if (cena != null) {
                cena.get(0).setPosition(new Vetor3(terreno.getPosition().x, terreno.getPosition().y, terreno.getPosition().z));
                cena.get(1).setPosition(new Vetor3(terreno2.getPosition().x, terreno2.getPosition().y, terreno2.getPosition().z));

            }
            if (terreno.getPosition().z > -20) {

                terreno.setPosition(new Vetor3(terreno.getPosition().x, terreno.getPosition().y, -103));

            } else if (terreno2.getPosition().z > -20) {


                terreno2.setPosition(new Vetor3(terreno2.getPosition().x, terreno2.getPosition().y, -103));

            }


        }
//        else {
//            ceoZ = terreno.getPosition().z + (velocidade2 / 2);
//            ceoZ2 = terreno2.getPosition().z + (velocidade2 / 2);
//            terreno.setPosition(new Vetor3(terreno.getPosition().x, terreno.getPosition().y, ceoZ));
//            terreno2.setPosition(new Vetor3(terreno2.getPosition().x, terreno2.getPosition().y, ceoZ2));
//
//            if (cena != null) {
//                cena.get(0).setPosition(new Vetor3(terreno.getPosition().x, terreno.getPosition().y, terreno.getPosition().z));
//                cena.get(1).setPosition(new Vetor3(terreno2.getPosition().x, terreno2.getPosition().y, terreno2.getPosition().z));
//
//            }
//        }

    }

    public void moverInimigoX(int modo, int time, float giroybolhas, int p) {


        if (inimigosX.get(p).isAtivado()) {
            inimigosX.get(p).setAbatidoPelaNave(false);

            if (inimigosX.get(p).isCarregamentoIndividual()) {
                inimigosX.get(p).setCarregamentoIndividual(false);
                float[] vz = {- -65, -65.5f, -66, -66.5f};

                switch (modo) {

                    case 0:
                    case 2:
                        if (inimigosX.get(p).getPosition().z <= -65) {
                            inimigosX.get(0).setPosition(new Vetor3(-0.5f, DISTANCIA + 0.03f, vz[(int) (Math.random() * 4)]));
                            inimigosX.get(1).setPosition(new Vetor3(0, DISTANCIA + 0.03f, vz[(int) (Math.random() * 4)]));
                            inimigosX.get(2).setPosition(new Vetor3(0.5f, DISTANCIA + 0.03f, vz[(int) (Math.random() * 4)]));

                        }
                    case 1:
                        if (inimigosX.get(p).getPosition().z <= -65) {
                            inimigosX.get(p).setPosition(new Vetor3(-0.5f,
                                    DISTANCIA + 0.03f, vz[(int) (Math.random() * 4)]));
                        }

                        break;
                }

            }

            if (inimigosX.get(p).getPosition().z < -59f) {
                // inimigosX.get(p).setDisparando(true);
            } else {
                for (Objeto3d o : inimigosX.get(p).getTiroNave()) {
                    o.getPosition().z = -100;
                }
            }

            switch (modo) {

                case 0:
                    //  inimigosX.get(p).setGiro(giroybolhas);
                    //   inimigosX.get(p).setPosition(new Vetor3(inimigosX.get(p).getPosition().x, inimigosX.get(p).getPosition().getY(), inimigosX.get(p).getPosition().z + (velocidade * 0.65f)));
                    if (inimigosX.get(p).getPosition().z <= -900) {
                        inimigosX.get(p).setCarregamentoIndividual(true);
                    }
                    if (inimigosX.get(p).getPosition().z < -59f) {
                        inimigosX.get(p).setDisparando(true);
                    } else {

                        inimigosX.get(p).setAbatido(true);
                        inimigosX.get(p).setAtivado(false);
                    }

                    if (time >= 100) {

                        timeModoX = 0;
                    }
                    timeModoX++;


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

                    break;
                case 2:

                    if (inimigosX.get(p).getPosition().z < Fenix.getPosition().z && inimigosX.get(p).getPosition().z < -63f) {
                        if (inimigosX.get(p).getPosition().z < -66) {
                            inimigosX.get(p).getPosition().z = -66;

                        }
                        inimigosX.get(p).getPosition().z += velocidade * 2;
                    } else {
                        inimigosX.get(p).getPosition().z -= velocidade;

                    }
                    if (inimigosX.get(p).getPosition().x > Fenix.getPosition().x) {
                        inimigosX.get(p).getPosition().x -= velocidade;
                    } else {
                        inimigosX.get(p).getPosition().x += velocidade;

                    }


                    if (time >= 100) {

                        timeModoX = 0;
                    }
                    timeModoX++;

                    if (inimigosX.get(p).getPosition().z <= -900) {
                        inimigosX.get(p).setCarregamentoIndividual(true);
                    }
                    timeModoX++;

                    break;

            }
            if (inimigosX.get(p).getPosition().z > -59) {

                inimigosX.get(p).setPosition(new Vetor3(inimigosX.get(p).getPosition().x,
                        DISTANCIA + 0.03f, -65));
                inimigosX.get(p).setEsplodiu(false);


                inimigosX.get(p).setAbatido(true);
                inimigosX.get(p).setAtivado(false);


            }


            if (inimigosX.get(p).isDisparando() && modo == 1) {
                moverObjFixoPadrao(inimigosX.get(p), direcaoX2[inimigosX.get(p).getDr()], 'y', 15f, 1000);
            } else if (modo == 0) {
                moverObjFixoPadrao(inimigosX.get(p), 0, 'y', 15f, 1000);


            } else {
                girarOBJ(inimigosX.get(p), (1000) * -1, 'y', 15f);
                moverObjFixoPadrao(inimigosX.get(p), 0, 'y', 15f, 1000);

            }

        }


        if (!horaDoBoss) {
            // inimigosX.get(p).setDisparando(true);
            disparandoOBJ(Fenix.getPosition(), inimigosX.get(p), 15, true, 2);

            //     tipoDisparaBoss(inimigosX.get(0).getTiroNave(), inimigosX.get(0).getTiroNave().get(tiroAtualDoX), 2, 1f);
            // inimigosX.get(p).getPosition().x=-10000000f;
            //   inimigosX.get(p).invulneravel = false;

        } else {
//            if (horaDoBoss) {
//                tipoDisparaBoss(inimigosX.get(0).getTiroNave(), 1, 0.2f);
//                tipoDisparaBoss(inimigosX.get(1).getTiroNave(), 1, 0.2f);
//                tipoDisparaBoss(inimigosX.get(2).getTiroNave(), 1, 0.2f);
//                inimigosX.get(p).invulneravel = true;
//            }

            if (horaDoBoss) {
                //       tipoDisparaBoss(inimigosX.get(0).getTiroNave(), inimigosX.get(0).getTiroNave().get(tiroAtualDoX), 2, 1f);

            }
        }


    }

    private void tipoDisparaBoss(ArrayList<Objeto3d> o, Objeto3d ob, int i, float vel) {
        float posx = 0;
        float pos = -2f;
        int faseBoss = ((fase + 1) / 10) - 1;
        switch (i) {
            case 0:

                for (Objeto3d oo : o) {
                    // oo.setDisparando(true);
                    disparandoOBJ(new Vetor3(pos, oo.getPosition().y, -52f), oo, 30, true, 0.2f);
                    oo.setPosition(boss.get(chefeDafase).getPosition());
                    pos += 2;
                }
                break;

            case 1:
                //   for (Objeto3d oo: o ) {
                float media = (o.size() / 2);
                Vetor3 alvo;

                for (int j = 0; j < o.size(); j++) {

                    posx = (j * 1f) - media;
                    //oo.setAtirando(true);

                    alvo = new Vetor3(posx, boss.get(faseBoss).getPosition().y, -60f);


                    o.get(j).atirandoUm(alvo, boss.get(faseBoss).getPosition(), velocidade * vel);
                    //  ooo.setPosition(boss.get(fase).getPosition());

                }


                // }
                break;


            case 2:
                float media2 = (o.size() / 4);
                Vetor3 alvo2;
                for (int j = 0; j < o.size(); j++) {
                    posx = (j * 1.5f) - media2;
                    alvo2 = new Vetor3(posx, boss.get(faseBoss).getPosition().y, -60f);
                    o.get(j).atirandoUm(alvo2, boss.get(faseBoss).getPosition(), velocidade * vel);
                }


                break;

        }

    }

    private void disparandoOBJ(Vetor3 alvo, Objeto3d ob, int tempoDisparo, boolean pereguir, float vel) {

        if (ob.getTiroNave().get(ob.getIdTiroAux()).isDisparando() || ob.getTiroNave().get(ob.getIdTiroAux()).getPosition().z < -58 && ob.getTiroNave().get(ob.getIdTiroAux()).getPosition().z > -68) {


            ob.atirando(alvo, velocidade * vel, pereguir, tempoDisparo, ob.getPosition());

        } else {
            ob.setIdTiroAux();

        }


    }

    public void moverAsteroide(int modoB, int timeB, float giroybolhas, int p) {
        if (asteroide.get(p).isAtivado()) {
            asteroide.get(p).setGiro(giroybolhas);
            asteroide.get(p).setAbatidoPelaNave(false);

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
            obj.setAbatidoPelaNave(false);

            if (((time >= i * 100 && (modo == 0 || modo == 1)) || (time >= i * 50 && (modo == 2 || modo == 3)) || modo == 4)) {

                if (time >= 1100) {

                    obj.setEsplodirNave(true);

                    // return;

                }


                switch (modo) {
                    case 0:


                        calculoarColisaoI(obj, Fenix, true);
                        for (Objeto3d pp : tiros) {
                            calculoarColisaoI(obj, pp, false);
                        }


                        if (true) {
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
                        if (obj.getPosition().z < -58.5 && obj.getPosition().z > -64.5) {
                            obj.setDisparando(true);
                            obj.setAtirando(true);

                        }

                        break;


                    case 1:


                        calculoarColisaoI(obj, Fenix, true);
                        for (Objeto3d pp : tiros) {
                            calculoarColisaoI(obj, pp, false);
                        }


                        if (true) {
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

                        if (obj.getPosition().z < -58.5 && obj.getPosition().z > -64.5) {
                            obj.setDisparando(true);
                            obj.setAtirando(true);

                        }

                        break;

                    case 2:


                        calculoarColisaoI(obj, Fenix, true);
                        for (Objeto3d pp : tiros) {
                            calculoarColisaoI(obj, pp, false);
                        }

                        obj.setDisparando(true);


                        if (true) {
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

                        obj.setDisparando(true);


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
        for (Objeto3d pp : obj.getTiroNave()) {
            calculoarColisaoI(pp, Fenix, false);
        }

        if (!horaDoBoss) {
            if (!obj.getNomeRef().equals("inimigosE2") && !obj.getNomeRef().equals("inimigosC2")) {
                // obj.atirandoC(Fenix, velocidade * 2);
                disparandoOBJ(Fenix.getPosition(), obj, 15, true, 2);

            } else {

                disparandoOBJ(Fenix.getPosition(), obj, 15, true, 2);
            }
        } else {

            if (obj.timeLimpar) {
                for (Objeto3d o : obj.getTiroNave()) {
                    o.getPosition().z = -1000;
                }
                obj.timeLimpar = false;
            }


        }

    }


    private void alterarModoN(Objeto3d o) {
        o.setTipo("N");
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        this.eglConfig = eglConfig;
        this.gl = gl;
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


        this.gl2 = gl;

    }


    float distanciaR = 0f;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void destroy() {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glFinish();
        this.gl.glFinish();
        this.gl2.glFinish();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDrawFrame(GL10 gl) {
        // limpa a tela e os buffer
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        if (limpar) {
            gl.glFinish();
            this.gl.glFinish();
            this.gl2.glFinish();
            onDestroy();
            return;
        }

        if (carregado) {

            terreno.draw((GL11) gl2);
            terreno2.draw((GL11) gl2);
            if (chefeDafase < 4) {
                for (int p = 0; p < cena.size(); p++) {
                    cena.get(p).draw((GL11) gl2);
                }
            }
            Fenix.draw((GL11) gl2);


            if (horaDoBoss) {
                desenhar(boss.get(chefeDafase), "boss", chefeDafase);
                boss.get(chefeDafase).setImpacto(false);

            }
            //  if (timeFim>0)
            //      this.esfera.draw((GL11) gl2);
            splosaoArrayNave.get(0).draw((GL11) gl2);
            for (int i = 0; i < nivelTiro; i++) {
                if (!tiros.get(i).getMover().equals("nulo")) {
                    tiros.get(i).draw((GL11) gl2);
                }
            }

            for (int i = 0; i < ataqueEspecial.size(); i++) {
                if (!ataqueEspecial.get(i).getMover().equals("nulo")) {
                    ataqueEspecial.get(i).draw((GL11) gl2);
                }
            }

            if (!this.perdeu && !this.venceu) {
                for (int p = 0; p < 6; p++) {
                    if (inimigosC.size() > 0) {
                        desenhar(inimigosC.get(p), "inimigosC", p);
                        desenhar(inimigosE.get(p), "inimigosE", p);
                        inimigosC.get(p).setImpacto(false);
                        inimigosE.get(p).setImpacto(false);

                        if (p < 3) {
                            desenhar(inimigosC2.get(p), "inimigosC2", p);
                            desenhar(inimigosE2.get(p), "inimigosE2", p);
                            inimigosC2.get(p).setImpacto(false);
                            inimigosE2.get(p).setImpacto(false);
                        }

                    }
                }

                for (int p = 0; p < 3; p++) {
                    if (inimigosB.size() > 0) {
                        desenhar(inimigosB.get(p), "inimigosB", p);
                        inimigosB.get(p).setImpacto(false);

                    }
                    if (inimigosA.size() > 0) {
                        desenhar(inimigosA.get(p), "inimigosA", p);
                        inimigosA.get(p).setImpacto(false);

                    }
                    if (inimigosX.size() > 0) {
                        desenhar(inimigosX.get(p), "inimigosX", p);
                        inimigosX.get(p).setImpacto(false);

                    }

                    if (asteroide.size() > 0) {
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
                        asteroide.get(p).setImpacto(false);

                    }
                }

            }
            for (Objeto3d o : ouro) {
                if (o.getPosition().z > -68f && o.getPosition().z < -60f && o.getPosition().x > -1.5f && o.getPosition().x < 1.5f) {
                    o.draw((GL11) gl2);
                    o.setGiro(o.getGiro() + 5f);
                    o.setGiroPosition(new Vetor3(0, 0, o.getGiro()));
                }
            }

            gl.glPopMatrix();
            gl.glLoadIdentity();
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
            gl.glRotatef(rotation, 1, 0, 0);
            gl.glRotatef(rotationy, 0, 1, 0);
            gl.glTranslatef(localx, localy, localz);
            btEspecial.getPosition().x = (localx + 0.5f) * -1;
            top.getPosition().x = (localx) * -1;
//zzzzzzzzzzz
            for (Objeto3d o : life) {
                o.getPosition().x = (localx) * -1;
            }

            if (this.pause) {
            } else if (this.pause == false || this.perdeu) {
                gl.glTranslatef(0, -2, -3);
                //  objeto3dxquadro.draw((GL11) gl2);
            }

            btEspecial.draw((GL11) gl2);

            top.draw((GL11) gl2);

            switch (vida) {
                case 1:
                    life.get(0).draw((GL11) gl2);
                    break;

                case 2:
                    life.get(1).draw((GL11) gl2);

                    break;
                case 3:
                    life.get(2).draw((GL11) gl2);

                    break;
                case 4:
                    life.get(3).draw((GL11) gl2);

                    break;
                case 5:
                    life.get(4).draw((GL11) gl2);

                    break;
            }


        } else {

            float totaly = ((this.displayMetrics.heightPixels / 2) / this.displayMetrics.scaledDensity) * 0.0001f;
            gl.glLoadIdentity();

            if (carga < 14) {
                gl.glRotatef(0, 0, 0, 0);
                gl.glTranslatef(0, 0.04f, -0.01f);
                if (carga > 0) {
                    this.telaIntro.draw((GL11) gl2);
                }
            }

            if (carga > 14) {
                gl.glRotatef(rotation, 1, 0, 0);
                gl.glTranslatef(localx, localy, localz);
                tempoDeEspera--;


            } else {


            }
            try {

                if (carga < 0)
                    carga++;

                carregar();
                ///  carga++;

            } catch (IOException e) {
                e.printStackTrace();
            }
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


    private void desenhar(Objeto3d o, String nome, int p) {
        try {
            if ((((o.getPosition().z > -65 && o.getPosition().x > -1.5f && o.getPosition().x < 1.5f) && !o.isBoss()) || (o.getPosition().z > -68 && o.isBoss())) && o.getPosition().z < -59 && o.getPassou().equals("N")) {
                if (!o.isEsplodirNave()) {
                    o.draw((GL11) gl2);


                } else {
                    Esplosao esp;
                    if (o.isBoss()) {
                        esp = filtro(nome, 0);
                    } else {
                        esp = filtro(nome, p);

                    }

                    //  if (o.getEsplosaoNaveId() < esp.getSplosaoArrayNave().size()) {
                    esp.getSplosaoArrayNave().get(o.getEsplosaoNaveId()).draw((GL11) gl2);

                    //        esp.getSplosaoArrayNave().get(o.getEsplosaoNaveId()).setImpacto(false);


                }
            }


            if (o.isEsplodirNave() && o.isBoss()) {

                Esplosao esp;

                esp = filtro(nome, 0);

                esp.getSplosaoArrayNave().get(o.getEsplosaoNaveId()).draw((GL11) gl2);

            }
            int ppp = 0;

            if (!horaDoBoss) {
                switch (nome) {
                    case "inimigosX":
                        ppp = this.tiroAtualDoX;
                        this.tiroAtualDoX++;
                        if (this.tiroAtualDoX >= o.getTiroNave().size())
                            this.tiroAtualDoX = 0;
                        DrawTiros(o, nome, ppp);

                        break;
                    case "inimigosA":
                        ppp = tiroAtualDoA;
                        tiroAtualDoA++;
                        if (tiroAtualDoA >= o.getTiroNave().size())
                            tiroAtualDoA = 0;
                        DrawTiros(o, nome, ppp);
                        break;
                    case "inimigosB":
                        ppp = tiroAtualDoB;
                        tiroAtualDoB++;
                        if (tiroAtualDoB >= o.getTiroNave().size())
                            tiroAtualDoB = 0;
                        DrawTiros(o, nome, ppp);
                        break;
                    case "inimigosC":
                        ppp = tiroAtualDoC;
                        tiroAtualDoC++;
                        if (tiroAtualDoC >= o.getTiroNave().size())
                            tiroAtualDoC = 0;
                        DrawTiros(o, nome, ppp);
                        break;
                    case "inimigosD":
                        ppp = tiroAtualDoD;
                        tiroAtualDoD++;
                        if (tiroAtualDoD >= o.getTiroNave().size())
                            tiroAtualDoD = 0;
                        DrawTiros(o, nome, ppp);
                        break;
                    case "inimigosE":
                        ppp = tiroAtualDoE;
                        tiroAtualDoE++;
                        if (tiroAtualDoE >= o.getTiroNave().size())
                            tiroAtualDoE = 0;
                        DrawTiros(o, nome, ppp);
                        break;
                    case "inimigosE2":
                        ppp = tiroAtualDoE2;
                        tiroAtualDoE2++;
                        if (tiroAtualDoE2 >= o.getTiroNave().size())
                            tiroAtualDoE2 = 0;
                        DrawTiros(o, nome, ppp);
                        break;
                    case "inimigosC2":
                        ppp = tiroAtualDoC2;
                        tiroAtualDoC2++;
                        if (tiroAtualDoC2 >= o.getTiroNave().size())
                            tiroAtualDoC2 = 0;
                        DrawTiros(o, nome, ppp);
                        break;
                    case "boss":
                        ppp = tiroAtualDoBoss;
                        tiroAtualDoBoss++;
                        if (tiroAtualDoBoss >= o.getTiroNave().size())
                            tiroAtualDoBoss = 0;
                        DrawTiros(o, nome, ppp);
                        break;


                }
            } else {
                switch (nome) {
                    case "inimigosA":
                        ppp = tiroAtualDoA;
                        tiroAtualDoA++;
                        if (tiroAtualDoA >= o.getTiroNave().size())
                            tiroAtualDoA = 0;
                        DrawTiros(o, nome, ppp);
                        break;

                    case "boss":
                        ppp = tiroAtualDoBoss;
                        tiroAtualDoBoss++;
                        if (tiroAtualDoBoss >= o.getTiroNave().size())
                            tiroAtualDoBoss = 0;
                        DrawTiros(o, nome, ppp);
                        break;


                }
            }


        } catch (Exception e) {
            Log.e("TC", e.getMessage());
        }
    }

    private void DrawTiros(Objeto3d o, String nome, int ppp) {

//        if (nome.equals("inimigosE") || nome.equals("inimigosC") || nome.equals("inimigosE2") || nome.equals("inimigosC2") || nome.equals("inimigosX") || nome.equals("boss")) {
        for (int pppp = 0; pppp < o.getTiroNave().size(); pppp++) {
            if (o.getTiroNave().get(pppp).getPosition().z > -65 && o.getTiroNave().get(pppp).getPosition().z < -59 &&
                    o.getTiroNave().get(pppp).getPosition().x > -1.5f &&
                    o.getTiroNave().get(pppp).getPosition().x < 1.5f) {

                o.getTiroNave().get(pppp).draw((GL11) gl2);
                o.getTiroNave().get(pppp).setImpacto(false);

            }
        }
//        } else {
//
//            if (o.getTiroNave().get(ppp).getPosition().z > -65 && o.getTiroNave().get(ppp).getPosition().z < -59 &&
//                    o.getTiroNave().get(ppp).getPosition().x > -1.5f &&
//                    o.getTiroNave().get(ppp).getPosition().x < 1.5f) {
//
//                o.getTiroNave().get(ppp).draw((GL11) gl2);
//                o.getTiroNave().get(ppp).setImpacto(false);
//
//            }
//        }
    }

    private void restart() {

        recomecar++;
        vitoria = false;
        dificult = indiceLevel;

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
            resaetarTudo(inimigosC2);
            resaetarTudo(inimigosE2);
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
                23.0f);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset the modelview matrix
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void validarToque() {


        BDEstatusFase bdEstatusFase = new BDEstatusFase(context);


        if (fase <= bdEstatusFase.buscarUltima().getId()) {
            vitoria = false;
            acelerarando = 0;
            timeLine = 0;
            Fenix.getPosition().z = -62f;
            restart();
            newLevel();
            selectFase = false;


        }

        bdEstatusFase.fechar();
    }


    public void moverPersonagem(MotionEvent event) {

        if (vitoria || this.perdeu) return;

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


        if (event.getY() > pontoDoEixoY) girox -= 1.2;
        else girox += 1.2;
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
        veloz = ((this.animal.getY() * 0.2f) * (0.2f * turbo)) - 63;
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

    private void dispararAtaqueEspecial() {


        // tempoDisparoAtaqueEspecial++;
        //   if (tempoDisparoAtaqueEspecial >= nivelTiroTempoAtaqueEspecial * 100) {
        if (ataqueEspecial.get(idDisparoAtaqueEspecial).getMover().equals("nulo")) {
            acionado = true;
            tempoDisparoAtaqueEspecial = 0;
            ataqueEspecial.get(idDisparoAtaqueEspecial).setMover("disparar");
            btEspecial.setTimeBtEspecial(100);
            if (idDisparoQTDAtaqueEspecial < ataqueEspecial.size() - 1) {
                idDisparoAtaqueEspecial++;
                idDisparoQTDAtaqueEspecial++;
            } else {
                idDisparoAtaqueEspecial = 0;
                idDisparoQTDAtaqueEspecial = 0;
            }

        }

        //   }


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

            if (o != null) {
                o.setVida(o.getRecoverVida());
                if (o.isBoss()) {
                    o.getPosition().z = -68f;
                } else {
                    o.getPosition().z = -65f;

                }
                o.setGiro(0);
                o.setGiro((float) (0));
                o.setReset(true);
                o.timeLimpar = true;
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
    }


    ///GERENCIA O TOQUE NA TELA
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouch(View view, MotionEvent event) {


        if (!this.venceu && carregado && !this.perdeu) {


            int posX = (int) (this.wTela / 100);
            int posY = (int) (this.h / 100);


            //  if (event.getPointerCount() == 1 && event.getPointerId(0) == 0) {


            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                fogo = true;

                if (event.getPointerCount() == 1 && event.getPointerId(0) == 0) {
                    if ((int) event.getX() > posX * 1 && (int) event.getX() < posX * 20 && (int) event.getY(event.getPointerId(0)) > posY * 70 && (int) event.getY(event.getPointerId(0)) < posY * 90) {
                        dispararAtaqueEspecial();

                    } else {

                    }
                }

                if (event.getPointerCount() > 1) {
                    if (event.getPointerId(1) == 1) {
                        if ((int) event.getX(event.getPointerId(1)) > posX * 1 && (int) event.getX(event.getPointerId(1)) < posX * 20 && (int) event.getY(event.getPointerId(0)) > posY * 70 && (int) event.getY(event.getPointerId(0)) < posY * 90) {
                            dispararAtaqueEspecial();

                        } else {

                        }
                    }

                }

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


                        //    quadroInserirPalavra.setPosition(new Vetor3(0f, 2f, 0f));


                    } else if ((int) event.getX() > posX * 0 && (int) event.getX() < posX * 15 && (int) event.getY(event.getPointerId(0)) > posY * 10 && (int) event.getY(event.getPointerId(0)) < posY * 20) {

                        if (this.pause) {
                            this.pause = false;

                        } else {
                            this.pause = true;

                        }

                    } else if (this.pause == false && (int) event.getX() > this.wTela * 0.5 && (int) event.getY(event.getPointerId(0)) > posY * 50) {

                        this.pause = true;


                    } else if (this.pause == false && (int) event.getX() > this.wTela * 0 && (int) event.getX() < this.wTela * 0.5 && (int) event.getY(event.getPointerId(0)) > posY * 50) {

                        this.parar();


                    } else if ((int) event.getX() > posX * 85 && (int) event.getX() < posX * 100 && (int) event.getY(event.getPointerId(0)) > posY * 0 && (int) event.getY(event.getPointerId(0)) < posY * 20) {


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
                    vida = 50000;
                    //  gravarRecorde();

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


                if (event.getPointerCount() > 1) {
                    if (event.getPointerId(1) == 1) {
                        if ((int) event.getX(event.getPointerId(1)) > posX * 1 && (int) event.getX(event.getPointerId(1)) < posX * 20 && (int) event.getY(event.getPointerId(1)) > posY * 70 && (int) event.getY(event.getPointerId(1)) < posY * 90) {
                            dispararAtaqueEspecial();

                        } else {

                        }
                    }

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
            // }


        }


        return true;
    }


    public interface Factory {

        @NonNull
        <T extends TartarugaCorrida> T create(@NonNull Class<T> modelClass);
    }


}



