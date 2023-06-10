package com.wao.fenix.projetoz.generico.recursos;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.util.Log;

import com.wao.fenix.projetoz.generico.pegarGrauDeLocalizacao;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;


/*
 December 30, 2010 by Per-Erik Bergman in Android, Embedded, Java | 105 Comments
 https://blog.jayway.com/2013/05/09/opengl-es-2-0-tutorial-for-android-part-i-getting-started/
https://blog.jayway.com/2009/12/04/opengl-es-tutorial-for-android-part-ii-building-a-polygon/
https://blog.jayway.com/2010/01/01/opengl-es-tutorial-for-android-part-iii-transformations/
https://blog.jayway.com/2010/01/14/opengl-es-tutorial-for-android-part-iv-adding-colors/
https://blog.jayway.com/2010/02/15/opengl-es-tutorial-for-android-part-v/
https://blog.jayway.com/2010/12/30/opengl-es-tutorial-for-android-part-vi-textures/

 */
public class Objeto3d implements Serializable {
 //   private static String LOGTAG = "NormalMapping";
  //  private static String assetDirectory = null;
    private boolean atirando = false;
    private boolean abatidoPelaNave = false;
   // public boolean invulneravel = false;
    public boolean retorno = false;
    public boolean  timeLimpar= true;
    private int timeBtEspecial = 0;
    private Vetor3 cores=new Vetor3(0,0,0);


    private boolean Fenix = false;
    private boolean reset = false;
    private boolean disparando = false;
    private boolean esplodirNave = false;
    private int timeEsplosaoNave = 0;
    private int timeLineInidvidual = 0;
 //   private int  normalMap=0;
    private Context context;
    private int esplosaoNaveId = 0;
    private boolean refletir = false;
    private int refletirTime = 0;
    private int indice = -1;
    private int direcaoZdoTiro = 0;
    private int idTiroAux = 0;
    private boolean ativado = false;
    private boolean abatido = false;
    FloatBuffer m_ColorData;
    private ArrayList<Objeto3d> tiroNave;
    private int[] tiroTime;
    private int tiroTimex=0;
    private ArrayList<Boolean> atirar;
    private Boolean atirarx =true;
    private String origem="";
    private boolean boss = false;
    private int idTiro = 0;
    private boolean impacto = false;
    public short[] indicesDeVertices;
    private short[] indicesNormais;
    private int textura;
    private int qtdtextura=1;
    float texture[];
   // private float[] verticesNormais;
    private int[] textures = new int[2];
    public FloatBuffer[] verticeBuffer;

    private float positionYdoObj = 0;
    public int time = 0;
    private boolean esplodiu = false;
    private boolean  imageID = true;
    private FloatBuffer[] NormaisBuffer;
    private ShortBuffer indiceBuffer;
    private ShortBuffer indiceNormaisBuffer;
    private FloatBuffer texturaBuffer;
    private LeitorDeObj leitorDeObj;
    private Vetor3 position = new Vetor3(0, 0, 0);
    private Vetor3 tamanho = new Vetor3(0, 0, 0);
    private Bitmap texturaBitimap;
    private String tipo = "";
    private String nome = "";
    private String nomeRef = "";
   // private   int m_BumpmapID;
   // private   int m_MainTexture;

   // static int level = 0;

    public boolean m_UseMipmapping = true;
    private int dr = 0;
    private int id = -1;

    private int quadrosDeanimacao;
    private Vetor3 giroPosition = new Vetor3(0, 0, 0);
    private int cont = 0;
    private String valor = "";
    private float escudo;
    private float vida = 100f;
    private float recoverVida = 0f;

    private float velocidadeHorizontal = 0f;

    private int toque = 0;
    private int visita = 0;
    private int positionAnteriorZ = 0;
    private float positionAnteriorZCorrent = 0;
    private float positionXCorrent = 0;
    private ArrayList<Float> calculosDeImpacto;

    private float giro = 0;

    private boolean vai = false;
    private boolean carregamentoIndividual = true;


    private String estado = "NBateu";
    private String passou = "N";
    private String mover = "Decer";
    private boolean troca = false;
    private boolean Transparente = false;
    private boolean mudarTamanho = false;
    private float estoraBolha = 100;

//    public boolean isAbatidoPelaNave() {
//        return abatidoPelaNave;
//    }

    public void setAbatidoPelaNave(boolean abatidoPelaNave) {
        this.abatidoPelaNave = abatidoPelaNave;
    }

//    public Vetor3 getCores() {
//        return cores;
//    }

    public void setCores(Vetor3 cores) {
        this.cores = cores;
    }

//    public int getTimeBtEspecial() {
//        return timeBtEspecial;
//    }

    public void setTimeBtEspecial(int timeBtEspecial) {
        this.timeBtEspecial = timeBtEspecial;
    }

//    public Bitmap getTexturaBitimap() {
//        return texturaBitimap;
//    }
//
//    public void setTexturaBitimap(Bitmap texturaBitimap) {
//        this.texturaBitimap = texturaBitimap;
//    }
//
//    public boolean isReset() {
//        return reset;
//    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }

    public boolean isCarregamentoIndividual() {
        return carregamentoIndividual;
    }

    public boolean isAbatido() {
        return abatido;
    }

    public void setAbatido(boolean abatido) {
        this.abatido = abatido;
    }

    public void setCarregamentoIndividual(boolean carregamentoIndividual) {
        this.carregamentoIndividual = carregamentoIndividual;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public String getNomeRef() {
        return nomeRef;
    }

    public void setNomeRef(String nomeRef) {
        this.nomeRef = nomeRef;
    }

    private boolean pause = false;

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public int getIdTiroAux() {
        return idTiroAux;
    }

    public void setIdTiroAux() {
        if (idTiroAux < tiroNave.size() - 1) {
            idTiroAux++;
        } else {
            idTiroAux = 0;
        }
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

//    public boolean isVai() {
//        return vai;
//    }
//
//    public void setVai(boolean vai) {
//        this.vai = vai;
//    }
//
//    public boolean isAtirando() {
//        return atirando;
//    }

    public void setAtirando(boolean atirando) {
        this.atirando = atirando;
    }

//    public boolean isImpacto() {
//        return impacto;
//    }

    public boolean isEsplodiu() {
        return esplodiu;
    }

    public void setEsplodiu(boolean esplodiu) {
        this.esplodiu = esplodiu;
    }

    public int getDirecaoZdoTiro() {
        return direcaoZdoTiro;
    }

    public void setDirecaoZdoTiro(int direcaoZdoTiro) {
        this.direcaoZdoTiro = direcaoZdoTiro;
    }

    public void setImpacto(boolean impacto) {
        this.impacto = impacto;
    }
//
//    public boolean isRefletir() {
//        return refletir;
//    }

    public void setRefletir(boolean refletir) {
        this.refletir = refletir;
    }

//    public int getIndice() {
//        return indice;
//    }

    public boolean isFenix() {
        return Fenix;
    }

    public void setFenix(boolean fenix) {
        Fenix = fenix;
    }
//
//    public void setIndice(int indice) {
//        this.indice = indice;
//    }

//    public void setCalculosDeImpacto() {
//        calculosDeImpacto = new ArrayList<>();
//        for (int i = 0; i < verticeBuffer[0].capacity() - 3; i += 3) {
//
//            float d1 = verticeBuffer[0].get(i + 2);
//            float d2 = verticeBuffer[0].get(i + 1);
//            float d3 = verticeBuffer[0].get(i);
//
//            float d = (float) ((d1 + d2 + d3));
//
//            calculosDeImpacto.add(d);
//            Log.i("W", "d" + i + " =" + d);
//
//        }
//
//    }

//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

//    public ArrayList<Boolean> isAtirar() {
//        return atirar;
//    }

    public void setAtirar(int pos, boolean b) {
        this.atirar.set(pos, b);
    }

    public int[] getTiroTime() {
        return tiroTime;
    }

    public void setTiroTime(int pos, int i) {
        this.tiroTime[pos] = i;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public float getRecoverVida() {
        return recoverVida;
    }

    public void setRecoverVida(float vida) {
        this.recoverVida = vida;
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

    public int getTimeLineInidvidual() {
        return timeLineInidvidual;
    }

    public void setTimeLineInidvidual(int timeLineInidvidual) {
        this.timeLineInidvidual = timeLineInidvidual;
    }

    public int getEsplosaoNaveId() {
        return esplosaoNaveId;
    }

    public void setEsplosaoNaveId(int esplosaoNaveId) {
        this.esplosaoNaveId = esplosaoNaveId;
    }


    public ArrayList<Objeto3d> getTiroNave() {
        return tiroNave;
    }

    public void setTiroNave(ArrayList<Objeto3d> tiroNave) {
        this.tiroNave = tiroNave;
    }

    public float getVelocidadeHorizontal() {
        return velocidadeHorizontal;
    }

    public void setVelocidadeHorizontal(float velocidadeHorizontal) {
        this.velocidadeHorizontal = velocidadeHorizontal;
    }

//    public float getEscudo() {
//        return escudo;
//    }
//
//    public void setEscudo(float escudo) {
//        this.escudo = escudo;
//    }

    public boolean isTroca() {
        return this.troca;
    }
//
//    public void setTroca(boolean troca) {
//        this.troca = troca;
//    }
//
//    public float getEstoraBolha() {
//        return this.estoraBolha;
//    }
//
//    public void setEstoraBolha(float estoraBolha) {
//        this.estoraBolha = estoraBolha;
//    }
//
//    public int getVisita() {
//        return this.visita;
//    }
//
//    public void setVisita(int visita) {
//        this.visita = visita;
//    }
//
//    public float getPositionXCorrent() {
//        return this.positionXCorrent;
//    }

    public void setPositionXCorrent(float positionXCorrent) {
        this.positionXCorrent = positionXCorrent;
    }

    public boolean isDisparando() {
        return disparando;
    }

    public void setDisparando(boolean disparandoax) {
        this.disparando = disparandoax;
        if (disparandoax) {
            for (Objeto3d o : tiroNave) {
                o.setDisparandoSub(true);
            }
        }else {

            this.disparando = disparandoax;


        }
    }

    public void setDisparandoSub(boolean disparandoax) {
        this.disparando = disparandoax;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

//    public LeitorDeObj getLeitorDeObj() {
//        return this.leitorDeObj;
//    }
//
//    public void setLeitorDeObj(LeitorDeObj leitorDeObj) {
//        this.leitorDeObj = leitorDeObj;
//    }
//
//    public float getPositionAnteriorZCorrent() {
//        return this.positionAnteriorZCorrent;
//    }
//
//    public void setPositionAnteriorZCorrent(float positionAnteriorZCorrent) {
//        this.positionAnteriorZCorrent = positionAnteriorZCorrent;
//    }
//
//    public int getPositionAnteriorZ() {
//        return this.positionAnteriorZ;
//    }
//
//    public void setPositionAnteriorZ(int positionAnteriorZ) {
//        this.positionAnteriorZ = positionAnteriorZ;
//    }
//
//
//    public boolean isPause() {
//        return this.pause;
//    }

//    public void setPause(boolean pause) {
//        this.pause = pause;
//    }
//
//    public float getPositionYdoObj() {
//        return this.positionYdoObj;
//    }
//
//    public void setPositionYdoObj(float positionYdoObj) {
//        this.positionYdoObj = positionYdoObj;
//    }
//

    public void setMover(String mover) {
        this.mover = mover;
    }

    public String getMover() {
        return mover;
    }


    public String getPassou() {
        return this.passou;
    }
//
//    public int getToque() {
//        return this.toque;
//    }
//
//    public void setToque(int toque) {
//        this.toque = toque;
//    }
//
//    public void setPassou(String passou) {
//        this.passou = passou;
//    }


    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

//    public void setTextura(int textura) {
//        this.textura = textura;
//    }
//
//    public int getTextura() {
//        return textura;
//    }

    public Vetor3 getTamanho() {
        return this.tamanho;
    }

//    public void setTamanho(Vetor3 tamanho) {
//        this.tamanho = tamanho;
//    }


    public Vetor3 getGiroPosition() {
        return this.giroPosition;
    }

    public void setGiroPosition(Vetor3 giroPosition) {
        this.giroPosition = giroPosition;
    }

    public Vetor3 getPosition() {
        return this.position;
    }


//    public double distanciaP(Objeto3d v1, Objeto3d v2) {
//
//        double d = Math.sqrt(Math.pow(v1.getPosition().x - (v2.getPosition().x), 2)
//                + Math.pow(v1.getPosition().y - (v2.getPosition().z), 2)
//                + Math.pow(v1.getPosition().z - (v2.getPosition().y), 2));
//        if (d < 10) {
//            d = 1;
//        } else {
//            d = 0;
//        }
//        return d;
//
//    }

    public void vezes(float x) {
        for (FloatBuffer vrt : verticeBuffer)
            for (int i = 0; i < vrt.capacity(); i++) {
                //  if (distanciaP(new Vetor3(dx, dy, dz), new Vetor3(verticeBuffer[0].get(i), verticeBuffer[0].get(i + 4), verticeBuffer[0].get(i + 8))) < 10) {
                vrt.put(i, vrt.get(i) * x);


            }

    }


//    public void divide(float x) {
//        for (FloatBuffer vrt : verticeBuffer)
//            for (int i = 0; i < vrt.capacity(); i++) {
//                //  if (distanciaP(new Vetor3(dx, dy, dz), new Vetor3(verticeBuffer[0].get(i), verticeBuffer[0].get(i + 4), verticeBuffer[0].get(i + 8))) < 10) {
//                vrt.put(i, vrt.get(i) / x);
//
//
//            }
//
//    }


//    public ArrayList<Float> maiorValor(ArrayList<Float> lista) {
//        float aux = 0;
//        int inicio = 1;
//        for (int i = 0; i < lista.size() - 1; i++) {
//
//            for (int j = inicio; j < lista.size(); j++) {
//                if (lista.get(i) > lista.get(j)) {
//                    aux = lista.get(i);
//                    lista.set(i, lista.get(j));
//                    lista.set(j, aux);
//
//                }
//
//            }
//            inicio++;
//
//        }
//        return lista;
//    }

//    public float testImpactorrrr(Objeto3d obj2) {
//        float dz = obj2.getPosition().z;
//        float dy = obj2.getPosition().y;
//        float dx = obj2.getPosition().x;
//        float mudar = 10000;
//
//        for (int i = 0; i < verticeBuffer[0].capacity() - 9; i += 9) {
//
//            ArrayList<Float> pz = new ArrayList<>();
//            pz.add(verticeBuffer[0].get(i));
//            pz.add(verticeBuffer[0].get(i + 1));
//            pz.add(verticeBuffer[0].get(i + 2));
//
//            pz = maiorValor(pz);
//            ArrayList<Float> py = new ArrayList<>();
//            py.add(verticeBuffer[0].get(i + 3));
//            py.add(verticeBuffer[0].get(i + 4));
//            py.add(verticeBuffer[0].get(i + 5));
//            py = maiorValor(py);
//
//            ArrayList<Float> px = new ArrayList<>();
//            px.add(verticeBuffer[0].get(i + 6));
//            px.add(verticeBuffer[0].get(i + 7));
//            px.add(verticeBuffer[0].get(i + 8));
//            px = maiorValor(px);
//
//            boolean vz, vy, vx;
//            vx = dx < px.get(2) && dx > px.get(0) ? true : false;
//            vy = dy < py.get(2) && dy > py.get(0) ? true : false;
//            vz = dz < pz.get(2) && dz > pz.get(0) ? true : false;
//
//            if (vz && vx && vy) {
//                mudar = 0;
//                break;
//            } else {
//                mudar = 10000;
//            }
//        }
//
//        return mudar;
//    }

    public float testImpacto(Objeto3d obj2) {
        float dz = obj2.getPosition().z;
        float dy = obj2.getPosition().y;
        float dx = obj2.getPosition().x;
        float mudar = 10000;
        for (int i = 0; i < verticeBuffer[0].capacity() - 9; i += 9) {
            //  if (distanciaP(new Vetor3(dx, dy, dz), new Vetor3(verticeBuffer[0].get(i), verticeBuffer[0].get(i + 4), verticeBuffer[0].get(i + 8))) < 10) {
            if (dx < verticeBuffer[0].get(i) && dx > verticeBuffer[0].get(i + 3) || dx > verticeBuffer[0].get(i) && dx < verticeBuffer[0].get(i + 3) ||
                    dx < verticeBuffer[0].get(i + 3) && dx > verticeBuffer[0].get(i + 6) || dx > verticeBuffer[0].get(i + 3) && dx < verticeBuffer[0].get(i + 6) ||
                    dx < verticeBuffer[0].get(i) && dx > verticeBuffer[0].get(i + 6) || dx > verticeBuffer[0].get(i) && dx < verticeBuffer[0].get(i + 6)) {

                if (dy < verticeBuffer[0].get(i + 1) && dy > verticeBuffer[0].get(i + 4) || dy > verticeBuffer[0].get(i + 1) && dy < verticeBuffer[0].get(i + 4) ||
                        dy < verticeBuffer[0].get(i + 4) && dy > verticeBuffer[0].get(i + 7) || dy > verticeBuffer[0].get(i + 4) && dy < verticeBuffer[0].get(i + 7) ||
                        dy < verticeBuffer[0].get(i + 1) && dy > verticeBuffer[0].get(i + 7) || dy > verticeBuffer[0].get(i + 1) && dy < verticeBuffer[0].get(i + 7)) {

                    if (dz < verticeBuffer[0].get(i + 2) && dz > verticeBuffer[0].get(i + 5) || dz > verticeBuffer[0].get(i + 2) && dz < verticeBuffer[0].get(i + 5) ||
                            dz < verticeBuffer[0].get(i + 5) && dz > verticeBuffer[0].get(i + 8) || dz > verticeBuffer[0].get(i + 5) && dz < verticeBuffer[0].get(i + 8) ||
                            dz < verticeBuffer[0].get(i + 2) && dz > verticeBuffer[0].get(i + 8) || dz > verticeBuffer[0].get(i + 2) && dz < verticeBuffer[0].get(i + 8)) {
                        mudar = 0;

                        break;

                    }

                }


            } else {
                mudar = 10000;
            }

        }

        // }

        return mudar;

    }


    public ArrayList<Objeto3d> criarTiros(Objeto3d obj, int qtd, AssetManager asset, String objFile, int texturaObj, Resources res) throws IOException {
        ///CARREGA OS ARQUIS 3D DO ALFABETO
        ArrayList<Objeto3d> tiroArray;
         tiroArray = new ArrayList<>();
        tiroTime = new int[qtd];
        atirar = new ArrayList<>();
        float x = obj.getTamanho().getX();
        float y = obj.getTamanho().getY();
        float z = obj.getTamanho().getZ();
        for (int t = 0; t < qtd; t++) {

            tiroArray.add(new Objeto3d(context,  asset, objFile, texturaObj, new Vetor3(x, y, z), ""));
             tiroArray.get(t).setMudarTamanho(true);
            tiroArray.get(t).setTransparente(true);
            tiroArray.get(t).setOrigem(obj.getValor());
            tiroArray.get(t).vezes(3);
            tiroArray.get(t).loadGLTexture();
           //  tiroArray.get(t).loadGLTexture(false);
            tiroTime[t] = 0;
            atirar.add(false);
        }


        return tiroArray;
    }


    public void atirando(Vetor3 alvo, float velocidade, boolean perseguir, int tempoDisparo, Vetor3 posit) {

        if (!reset) {
            if (getTiroNave().size() > 0) {
                if (time >= tempoDisparo) {
                    setAtirar(idTiro, true);
                    if (idTiro < getTiroNave().size() - 1) {
                        idTiro++;
                    } else {
                        idTiro = 0;
                    }
                    time = 0;
                } else {
                    time++;
                }
                for (int i = 0; i < getTiroNave().size(); i++) {
                    if (atirar.get(i) == false) {
                        getTiroNave().get(i).setPosition(new Vetor3(getPosition().x, getPosition().getY(), getPosition().z));
                        float distanciaZFI = (alvo.getPosition().z - getTiroNave().get(i).getPosition().z) / velocidade;
                        float distanciaXFI = alvo.getPosition().x - getTiroNave().get(i).getPosition().x;
                        float veloX = distanciaXFI / distanciaZFI;
                        if (alvo.getPosition().z < getPosition().z) {
                            getTiroNave().get(i).setDirecaoZdoTiro(1);
                        } else {
                            getTiroNave().get(i).setDirecaoZdoTiro(0);

                        }
                        if (veloX < 0) {

                            if (veloX < (-1 * velocidade)) {
                                veloX = (-1 * velocidade);
                                getTiroNave().get(i).setDirecaoZdoTiro(2);
                            }
                        } else {
                            if (veloX > velocidade) {
                                veloX = velocidade;
                                getTiroNave().get(i).setDirecaoZdoTiro(2);
                            }
                        }
                        getTiroNave().get(i).setVelocidadeHorizontal(veloX);


                        if (alvo.getPosition().x >= getTiroNave().get(i).getPosition().x) {
                            getTiroNave().get(i).setPositionXCorrent(1);
                        } else {
                            getTiroNave().get(i).setPositionXCorrent(0);
                        }
                        if (perseguir) {
                            pegarGrauDeLocalizacao pg = new pegarGrauDeLocalizacao();
                            getTiroNave().get(i).setGiroPosition(new Vetor3(0f, (float) pg.grauDeGiro(getPosition(), alvo.getPosition()), 0f));

                        }
                    } else {

                        setTiroTime(i, tiroTime[i] + 1);
                        float controleX;
                        if (getTiroNave().get(i).getVelocidadeHorizontal() > 0) {
                            controleX = getTiroNave().get(i).getVelocidadeHorizontal() > velocidade ? velocidade : 0;

                        } else {
                            controleX = getTiroNave().get(i).getVelocidadeHorizontal() < (-1 * velocidade) ? 0 : velocidade;


                        }


                        if (perseguir) {


                            if (getTiroNave().get(i).getDirecaoZdoTiro() == 1) {
                                getTiroNave().get(i).getPosition().setZ(getTiroNave().get(i).getPosition().z - velocidade);
                                getTiroNave().get(i).getPosition().setX(getTiroNave().get(i).getPosition().x - getTiroNave().get(i).getVelocidadeHorizontal());

                            } else if (getTiroNave().get(i).getDirecaoZdoTiro() == 0) {
                                if(getTiroNave().get(i).getPosition().z<=-900){
                                    getTiroNave().get(i).getPosition().setZ(posit.z);
                                }
                                getTiroNave().get(i).getPosition().setZ(getTiroNave().get(i).getPosition().z + velocidade);
                                getTiroNave().get(i).getPosition().setX(getTiroNave().get(i).getPosition().x + getTiroNave().get(i).getVelocidadeHorizontal());

                            } else {
                                getTiroNave().get(i).getPosition().setZ(getTiroNave().get(i).getPosition().z + 0.002f);
                                getTiroNave().get(i).getPosition().setX(getTiroNave().get(i).getPosition().x + getTiroNave().get(i).getVelocidadeHorizontal());

                            }


                        } else {
                            getTiroNave().get(i).getPosition().setZ(getTiroNave().get(i).getPosition().z + velocidade);

                        }

                        if (tiroTime[i] == 1000|| getTiroNave().get(i).getPosition().z>-59f) {
                            setAtirar(i, false);
                            setTiroTime(i, 0);
                        }


                    }

                    if (getTiroNave().get(i).getPosition().z > -58f || getTiroNave().get(i).getPosition().x > 1.49f || getTiroNave().get(i).getPosition().x < -1.49f) {
                        getTiroNave().get(i).setDisparando(false);
                    }

                }
            }
        } else {
            for (int i = 0; i < getTiroNave().size(); i++) {
                getTiroNave().get(i).getPosition().setZ(getTiroNave().get(i).getPosition().z + 1000f);
                getTiroNave().get(i).setGiroPosition(new Vetor3(0f, 0f, 0f));
                if (getTiroNave().get(i).getPosition().z > -58f || getTiroNave().get(i).getPosition().x > 1.49f || getTiroNave().get(i).getPosition().x < -1.49f) {
                    getTiroNave().get(i).setDisparando(false);
                }
            }
            reset = false;
        }

        setIdTiroAux();
    }


//    public void atirandoC(Objeto3d alvo, float velocidade) {
//        if (!reset) {
//            for (int i = 0; i < getTiroNave().size(); i++) {
//                if (this.atirando) {
//                    setAtirar(i, true);
//                }
//
//                if (atirar.get(i) == false) {
//                    getTiroNave().get(i).setPosition(new Vetor3(getPosition().x, getPosition().getY(), getPosition().z));
//                    float distanciaZFI = (alvo.getPosition().z - getTiroNave().get(i).getPosition().z) / velocidade;
//                    float distanciaXFI = alvo.getPosition().x - getTiroNave().get(i).getPosition().x;
//                    getTiroNave().get(i).setVelocidadeHorizontal(distanciaXFI / distanciaZFI);
//                    if (alvo.getPosition().x >= getTiroNave().get(i).getPosition().x) {
//                        getTiroNave().get(i).setPositionXCorrent(1);
//                    } else {
//                        getTiroNave().get(i).setPositionXCorrent(0);
//                    }
//                    pegarGrauDeLocalizacao pg = new pegarGrauDeLocalizacao();
//                    getTiroNave().get(i).setGiroPosition(new Vetor3(0f, (float) pg.grauDeGiro(getPosition(), alvo.getPosition()), 0f));
//
//
//                } else {
//
//                    setTiroTime(i, tiroTime[i] + 1);
//                    getTiroNave().get(i).getPosition().setZ(getTiroNave().get(i).getPosition().z + velocidade);
//                    if (getTiroNave().get(i).getVelocidadeHorizontal() < velocidade) {
//                        getTiroNave().get(i).getPosition().setX(getTiroNave().get(i).getPosition().x + getTiroNave().get(i).getVelocidadeHorizontal());
//                    }
//                    if (tiroTime[i] >= 700) {
//                        setAtirar(i, false);
//                        setTiroTime(i, 0);
//                    }
//
//
//                }
//
//                if (getTiroNave().get(i).getPosition().z < -68f ||getTiroNave().get(i).getPosition().z > -58f || getTiroNave().get(i).getPosition().x > 2f || getTiroNave().get(i).getPosition().x < -2f) {
//                    getTiroNave().get(i).setDisparando(false);
//                }
//            }
//
//        } else {
//            for (int i = 0; i < getTiroNave().size(); i++) {
//                getTiroNave().get(i).getPosition().setZ(getTiroNave().get(i).getPosition().z + 1000f);
//                getTiroNave().get(i).setGiroPosition(new Vetor3(0f, 0f, 0f));
//                if (getTiroNave().get(i).getPosition().z < -68f ||getTiroNave().get(i).getPosition().z > -58f || getTiroNave().get(i).getPosition().x > 2f || getTiroNave().get(i).getPosition().x < -2f) {
//                    getTiroNave().get(i).setDisparando(false);
//                }
//            }
//            reset = false;
//        }
//
//    }
//
//
//


    public void atirandoUm(Vetor3 alvo,Vetor3 origem, float velocidade) {

                     setPosition(new Vetor3(getPosition().x, getPosition().getY(), getPosition().z));
                    float distanciaZFI = (alvo.getPosition().z - getPosition().z) / velocidade;
                    float distanciaXFI = alvo.getPosition().x - getPosition().x;
                     setVelocidadeHorizontal(distanciaXFI / distanciaZFI);
                    if (alvo.getPosition().x >= getPosition().x) {
                         setPositionXCorrent(1);
                    } else {
                         setPositionXCorrent(0);
                    }
                    pegarGrauDeLocalizacao pg = new pegarGrauDeLocalizacao();
                    setGiroPosition(new Vetor3(0f, (float) pg.grauDeGiro(getPosition(), alvo.getPosition()), 0f));


                    getPosition().setZ(getPosition().z + velocidade);
                    if (getVelocidadeHorizontal() < velocidade) {
                        getPosition().setX(getPosition().x + getVelocidadeHorizontal());
                    }


                if (getPosition().z >= alvo.z ||getPosition().z < -70f)  {

                setPosition(origem);
                setGiroPosition(new Vetor3(0f, 0f, 0f));


        }

    }



//    public void atirandoUmGiro(Vetor3 alvo,Vetor3 origem, float velocidade, boolean retorno) {
//
//        setPosition(new Vetor3(getPosition().x, getPosition().getY(), getPosition().z));
//        float distanciaZFI = (alvo.getPosition().z - getPosition().z) / velocidade;
//        float distanciaXFI = alvo.getPosition().x - getPosition().x;
//        setVelocidadeHorizontal(distanciaXFI / distanciaZFI);
//        if (alvo.getPosition().x >= getPosition().x) {
//            setPositionXCorrent(1);
//        } else {
//            setPositionXCorrent(0);
//        }
//        pegarGrauDeLocalizacao pg = new pegarGrauDeLocalizacao();
//        setGiroPosition(new Vetor3(0f, (float) pg.grauDeGiro(getPosition(), alvo.getPosition()), 0f));
//
//
//        getPosition().setZ(getPosition().z + velocidade);
//        if (getVelocidadeHorizontal() < velocidade) {
//            getPosition().setX(getPosition().x + getVelocidadeHorizontal());
//        }
//
//
//        if (getPosition().z >= alvo.z ||getPosition().z < -70f || retorno)  {
//
//            setPosition(origem);
//            setGiroPosition(new Vetor3(0f, 0f, 0f));
//           this.retorno=true;
//
//        }
//
//    }


    public void setPosition(Vetor3 position) {
        this.position = position;
    }

    public float getGiro() {
        return this.giro;
    }

    public void setGiro(float giro) {
        this.giro = giro;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTransparente(boolean transparente) {
        this.Transparente = transparente;
    }

    public void setMudarTamanho(boolean mudarTamanho) {
        this.mudarTamanho = mudarTamanho;
    }

    public void setVertices() {

        //ARMAZENA vertices EM BUFFER
        ByteBuffer vbb;
        verticeBuffer = new FloatBuffer[quadrosDeanimacao];
        float[] vertic = new float[leitorDeObj.vertecisA.length / quadrosDeanimacao];

        vertic = leitorDeObj.emQuadrarVertecisA(leitorDeObj.vertecisA.length / quadrosDeanimacao * 0);

        for (int i = 0; i < verticeBuffer.length; i++) {

            vbb = ByteBuffer.allocateDirect(vertic.length * 4);
            vbb.order(ByteOrder.nativeOrder());
            verticeBuffer[i] = vbb.asFloatBuffer();
            verticeBuffer[i].put(leitorDeObj.emQuadrarVertecisA(leitorDeObj.vertecisA.length / quadrosDeanimacao * i));

            verticeBuffer[i].position(0);
        }

    }

    public void setVerticesNormais() {

        ByteBuffer vbb;
        NormaisBuffer = new FloatBuffer[quadrosDeanimacao];
        float[] vertic = new float[leitorDeObj.vertecisANormais.length / quadrosDeanimacao];

        vertic = leitorDeObj.emQuadrarvertecisANormais(leitorDeObj.vertecisANormais.length / quadrosDeanimacao * 0);

        for (int i = 0; i < NormaisBuffer.length; i++) {

            vbb = ByteBuffer.allocateDirect(vertic.length * 4);
            vbb.order(ByteOrder.nativeOrder());
            NormaisBuffer[i] = vbb.asFloatBuffer();
            NormaisBuffer[i].put(leitorDeObj.emQuadrarvertecisANormais(leitorDeObj.vertecisANormais.length / quadrosDeanimacao * i));

            NormaisBuffer[i].position(0);
        }
    }

    public void setIndicesDeVertices(short[] indicesDeVertices) {
        this.indicesDeVertices = indicesDeVertices;

        //ARMAZENA indicesDeVertices EM BUFFER
        ByteBuffer ibb = ByteBuffer.allocateDirect(indicesDeVertices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indiceBuffer = ibb.asShortBuffer();
        indiceBuffer.put(indicesDeVertices);
        indiceBuffer.position(0);


    }

    public void setIndicesNormais(short[] indicesNormais) {
        this.indicesNormais = indicesNormais;


        //ARMAZENA indicesNormais EM BUFFER
        ByteBuffer ibbN = ByteBuffer.allocateDirect(indicesNormais.length * 2);
        ibbN.order(ByteOrder.nativeOrder());
        indiceNormaisBuffer = ibbN.asShortBuffer();
        indiceNormaisBuffer.put(indicesNormais);
        indiceNormaisBuffer.position(0);

    }

    public void setTexture(float[] texture) {
        this.texture = texture;


        //ARMAZENA texture EM BUFFER
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect( texture.length * 4 );
        byteBuffer.order( ByteOrder.nativeOrder() );
        texturaBuffer = byteBuffer.asFloatBuffer();
        texturaBuffer.put( texture );
        texturaBuffer.position( 0 );

    }


	/*CONSTRUTOR DO OBJETO CONTENDO COMO PARAMETROS
                   (asset="local do arquivo.obj",obj="nome do arquivo.obj",textura="imagem de pintura do objeto"
                    */

//    public Objeto3d() {
//
//    }
//
//    public void descarregar() {
//
//
//        verticeBuffer = null;
//        NormaisBuffer = null;
//        indiceBuffer = null;
//        indiceNormaisBuffer = null;
//
//
//    }

    public Objeto3d( Context context, AssetManager asset, String obj, int textura, Vetor3 tamanho, String nomeRef) throws IOException {
        leitorDeObj = new LeitorDeObj(asset, obj);
        this.m_UseMipmapping=true;
        //this.normalMap=res;
        quadrosDeanimacao = leitorDeObj.quadrosDeanimacao;
        this.nomeRef = nomeRef;
      this.textura = textura;
        this.tamanho = tamanho;
        this.context=context;
        m_ColorData = makeFloatBuffer(new float[]{0,0,0,0});
      //  this.m_BumpmapID = createTexture(context, this.normalMap, 1);
      // this.m_MainTexture = createTexture( context, this.textura,0);
        if (leitorDeObj.texturaT != null) {
            qtdtextura=leitorDeObj.getTesturas();
            setTexture(leitorDeObj.texturaT);//PEGA O MAPA DA TEXTURA DO arquivo.obj
        }
        setVertices();//PEGA OS VERTICES DO arquivo.obj
        if (leitorDeObj.vertecisNormais != null) {
            setVerticesNormais();//PEGA AS NORMALVERTICES DO arquivo.obj
        }
        setIndicesDeVertices(indicesDeVertices = leitorDeObj.indexesA);//PEGA OS INDICES DOS  VERTICES DO arquivo.obj
        setIndicesNormais(indicesNormais = leitorDeObj.indexesAN);//PEGA OS INDICES DOS  NORMALVERTICES DO arquivo.obj
        nome = leitorDeObj.getNome();
        leitorDeObj = null;
    }



    public Objeto3d( Context context, AssetManager asset, String obj, Bitmap texturaBitimap, Vetor3 tamanho, String nomeRef) throws IOException {
        leitorDeObj = new LeitorDeObj(asset, obj);
        this.m_UseMipmapping=true;
        //this.normalMap=res;
        quadrosDeanimacao = leitorDeObj.quadrosDeanimacao;
        this.nomeRef = nomeRef;
        this.texturaBitimap = texturaBitimap;
        this.tamanho = tamanho;
        this.context=context;
        m_ColorData = makeFloatBuffer(new float[]{0,0,0,0});
       //  createTexture(context, this.normalMap, 1);
         createTexture2(texturaBitimap,0);
        if (leitorDeObj.texturaT != null) {
            setTexture(leitorDeObj.texturaT);//PEGA O MAPA DA TEXTURA DO arquivo.obj
        }
        setVertices();//PEGA OS VERTICES DO arquivo.obj
        if (leitorDeObj.vertecisNormais != null) {
            setVerticesNormais();//PEGA AS NORMALVERTICES DO arquivo.obj
        }
        setIndicesDeVertices(indicesDeVertices = leitorDeObj.indexesA);//PEGA OS INDICES DOS  VERTICES DO arquivo.obj
        setIndicesNormais(indicesNormais = leitorDeObj.indexesAN);//PEGA OS INDICES DOS  NORMALVERTICES DO arquivo.obj
        nome = leitorDeObj.getNome();
        leitorDeObj = null;
    }

    protected static FloatBuffer makeFloatBuffer(float[] arr)
    {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }
    float[] matrix;
    boolean inicio = true;
    GL11 gl2;

//    public void giroTotal(GL11 gl, float valor, int x, int y, int z) {
//        gl.glPushMatrix();
//
//        gl.glRotatef(valor, x, 0, 0);
//        gl.glRotatef(valor, 0, y, 0);
//        gl.glRotatef(valor, 0, 0, z);
//
//        gl.glPopMatrix();
//
//    }

    public void draw(GL11 gl) {
        try{
            gl2 = gl;
            gl.glPushMatrix();
            if (quadrosDeanimacao > 1) {
                if (inicio) {
                    if (cont == quadrosDeanimacao - 1) {
                        inicio = false;
                    } else {
                        cont++;
                    }
                } else {

                    if (cont == 0) {
                        inicio = true;
                    } else {
                        cont--;
                    }

                }
            }
            if (mudarTamanho) {
                gl.glScalef(tamanho.getX(), tamanho.getY(), tamanho.getZ());
            }
            if (impacto && refletir) {
                if (!isFenix()) {

                    float[] ambientLight = {1.0f, 1.0f, 1.0f, 1.0f};//cor amarela do ambiente
                    float posicaoLuz[] = {-1f, 0, -65f, 1f};
                    gl.glLightModelfv(GL11.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);
                    gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_AMBIENT, ambientLight, 0);
                    gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, posicaoLuz, 0);
                    refletirTime++;
                    if (refletirTime == 20) {
                        impacto = false;
                        refletirTime = 0;
                    }
                } else {


                    float posicaoLuz[] = {getPosition().x, getPosition().y, getPosition().z - 0.1f, 1};
                    float[] ambientLight = {1.0f, 0.2f, 0.0f, 1.0f};//cor amarela do ambiente
                    gl.glLightModelfv(GL11.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);
                    gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_AMBIENT, ambientLight, 0);
                    gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, posicaoLuz, 0);

                    refletirTime++;
                    if (refletirTime == 20) {
                        impacto = false;
                        refletirTime = 0;
                    }

                }


            } else {

                if (getNomeRef().equals("btEspecial") && timeBtEspecial>0) {

                    float[] ambientLight = {1.0f, 0.0f, 0.0f, 1.0f};//cor amarela do ambiente
                    float posicaoLuz[] = {-1f, 0, -65f, 1f};
                    gl.glLightModelfv(GL11.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);
                    gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_AMBIENT, ambientLight, 0);
                    gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, posicaoLuz, 0);
                    refletirTime++;
                    if (refletirTime == 20) {
                        impacto = false;
                        refletirTime = 0;
                    }
                } else {
                    String reff = getNomeRef().equals("") ? getOrigem() : getNomeRef();
                    switch (reff) {
                        case "inimigosX":
                        case "inimigosA":
                        case "inimigosB":
                        case "inimigosC":
                        case "inimigosC2":
                        case "inimigosE":
                        case "inimigosE2":
                        case "Fenix1":
                        case "boss":
                            float[] ambientLight = new float[]{cores.x, cores.y, cores.z, 1.0f};//cor amarela do ambiente
                            float[] posicaoLuz = new float[]{-1f, -80, -65f,1};
                            gl.glLightModelfv(GL11.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);
                            gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_AMBIENT, ambientLight, 0);
                            gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, posicaoLuz, 0);
                        break;





                        default:
                            ambientLight = new float[]{1.0f, 1.0f, 1.0f, 1.0f};//cor amarela do ambiente
                            posicaoLuz = new float[]{-1f, 0, -30f, 1f};
                            gl.glLightModelfv(GL11.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);
                            gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_AMBIENT, ambientLight, 0);
                            gl.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, posicaoLuz, 0);
                            break;

                    }


                }




            }
            gl.glTranslatef(getPosition().getX(), getPosition().getY(), getPosition().getZ());
            gl.glRotatef(getGiroPosition().y, 0, 1, 0);
            gl.glRotatef(getGiroPosition().x, 1, 0, 0);
            gl.glRotatef(getGiroPosition().z, 0, 0, 1);
            if (Transparente) {
                gl.glEnable(GL11.GL_ALPHA_TEST);// abilita camada alpha
                gl.glEnable(GL11.GL_BLEND);// abilita mistura de cores
                gl.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);//
            }
            if (verticeBuffer[cont] != null) {
                gl.glFrontFace(gl.GL_CW);
                gl.glVertexPointer(3, GL11.GL_FLOAT, 0, verticeBuffer[cont]);
            }
            if (texturaBuffer != null) {

                gl.glEnable(GL10.GL_TEXTURE_2D);
                gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
                gl.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                gl.glEnableClientState(GL11.GL_NORMAL_ARRAY);
                gl.glNormalPointer(GL11.GL_FLOAT, 0, NormaisBuffer[cont]);
                gl.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texturaBuffer);
                // Draw the vertices as triangle strip
                gl.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 0, m_ColorData);
                float lightAngle = 0f;
                float x, y, z;
                x = (float) Math.sin(lightAngle * (3.14159 / 180.0f)); //2
                y = 0.0f;
                z = (float) Math.cos(lightAngle * (3.14159 / 180.0f));

                // Half shifting to have a value between 0.0f and 1.0f.
                x = x * 0.5f + 0.5f; //3
                y = y * 0.5f + 0.5f;
                z = z * 0.5f + 0.5f;

                gl.glColor4f(x, y, z, 1.0f); //4
                gl.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_REPLACE);
                gl.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texturaBuffer);
                gl.glEnable(GL11.GL_TEXTURE_2D);
//
                if (textures[0] == 0) {
                    loadGLTexture();
                }
                gl.glClientActiveTexture(GL11.GL_TEXTURE1);
                gl.glActiveTexture(GL11.GL_TEXTURE1);
                gl.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                gl.glBindTexture(GL11.GL_TEXTURE_2D, textures[1]);

                gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_COMBINE);//6
                gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL11.GL_COMBINE_RGB, GL11.GL_DOT3_RGB); //7
                gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL11.GL_SRC0_RGB, GL11.GL_TEXTURE); //8
                gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL11.GL_SRC1_RGB, GL11.GL_PREVIOUS);

                gl.glClientActiveTexture(GL11.GL_TEXTURE0);
                gl.glActiveTexture(GL11.GL_TEXTURE0);
                gl.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                gl.glBindTexture(GL11.GL_TEXTURE_2D, textures[0]);


                gl.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
                gl.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texturaBuffer);
                gl.glEnable(GL11.GL_TEXTURE_2D);
                gl.glNormalPointer(gl.GL_FLOAT, 0, NormaisBuffer[cont]);
                gl.glDrawElements(GL11.GL_TRIANGLES, indicesNormais.length, GL11.GL_UNSIGNED_SHORT, indiceNormaisBuffer);
                gl.glDrawElements(GL11.GL_TRIANGLES, indicesDeVertices.length, GL11.GL_UNSIGNED_SHORT, indiceBuffer);

            }
            gl.glPopMatrix();
        }catch (Exception e){

            Log.e("GL",e.getMessage());
        }

    }

    public int createTexture( Context contextRegf, int resource,int i)
    {

        Bitmap image = BitmapFactory.decodeResource(contextRegf.getResources(), resource); // 1

        GLES30.glGenTextures( 1, textures,0 );
        GLES30.glBindTexture( GLES30.GL_TEXTURE_2D, textures[i] );
        GLUtils.texImage2D(GL11 .GL_TEXTURE_2D, i, image, 0); // 4
        GLES30.glTexParameterf(GL11 .GL_TEXTURE_2D, GL11 .GL_TEXTURE_MIN_FILTER,GL11 .GL_LINEAR); // 5a
        GLES30.glTexParameterf(GL11 .GL_TEXTURE_2D, GL11 .GL_TEXTURE_MAG_FILTER,GL11 .GL_LINEAR); // 5b
     //   image.recycle(); //6
        return resource;

    }
    public void   createTexture2( Bitmap image,int i)
    {
        GLES30.glGenTextures( 1, textures,0 );
        GLES30.glBindTexture( GLES30.GL_TEXTURE_2D, textures[i] );
        GLUtils.texImage2D(GL11 .GL_TEXTURE_2D, i, image, 0); // 4
        GLES30.glTexParameterf(GL11 .GL_TEXTURE_2D, GL11 .GL_TEXTURE_MIN_FILTER,GL11 .GL_LINEAR); // 5a
        GLES30.glTexParameterf(GL11 .GL_TEXTURE_2D, GL11 .GL_TEXTURE_MAG_FILTER,GL11 .GL_LINEAR); // 5b
       // image.recycle(); //6

    }

    public void LoadTexture(Bitmap image)
    {

        GLES30.glGenTextures( 1, textures,0 );
        GLES30.glBindTexture( GLES30.GL_TEXTURE_2D, textures[0] );
        GLUtils.texImage2D(GL11 .GL_TEXTURE_2D, 0, image, 0); // 4
        GLES30.glTexParameterf(GL11 .GL_TEXTURE_2D, GL11 .GL_TEXTURE_MIN_FILTER,GL11 .GL_LINEAR); // 5a
        GLES30.glTexParameterf(GL11 .GL_TEXTURE_2D, GL11 .GL_TEXTURE_MAG_FILTER,GL11 .GL_LINEAR); // 5b


//        if (texturaBuffer  != null) {
//            textures[0] = 0;
//            texturaBuffer .clear();
//            texturaBuffer .put( texture );
//            texturaBuffer .position( 0 );
//            GLES20.glGenTextures( 1, textures, 0 );
//            GLES20.glBindTexture( GLES20.GL_TEXTURE_2D, textures[0] );
//            GLES20.glTexParameterf( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR );
//            GLES20.glTexParameterf( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR );
//            GLUtils.texImage2D( GLES20.GL_TEXTURE_2D, 0, image, 0 );
//
//
//        }

    }

    public void loadGLTexture() {

        if (texturaBuffer  != null) {
            Bitmap image = BitmapFactory.decodeResource(context.getResources(), textura); // 1
            textures[0] = 0;
            texturaBuffer .clear();
            texturaBuffer .put( texture );
            texturaBuffer .position( 0 );
            GLES20.glGenTextures( 1, textures, 0 );
            GLES20.glBindTexture( GLES20.GL_TEXTURE_2D, textures[0] );
            GLES20.glTexParameterf( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR );
            GLES20.glTexParameterf( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR );
            GLUtils.texImage2D( GLES20.GL_TEXTURE_2D, 0, image, 0 );


        }
    }
//    public void loadGLTextureB() {
//        if (texturaBuffer != null) {
//            textures[0] = 0;
//            texturaBuffer.clear();
//            texturaBuffer.put(texture);
//            texturaBuffer.position(0);
//            // loading texture
//            //bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.casco);
//
//            // generate one texture pointer
//            GLES20.glGenTextures(1, textures, 0);
//            // ...and bind it to our array
//
//            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0]);
//            // Create Nearest Filtered Texture
//
//            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
//            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
//
//            // Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
//            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, texturaBitimap, 0);
//            //Different possible texture parameters, e.positionY. GL10.GL_CLAMP_TO_EDGE
//            // gl2.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
//
//            // Use Android GLUtils to specify a two-dimensional texture image from our bitmap
//
//
//        }
//    }
//
//    public double grauDeGiro(Objeto3d alvo) {
//        float distanciaX=alvo.getPosition().x-getPosition().x;
//        float distanciaZ=alvo.getPosition().z-getPosition().z;
//        float hipotenuza = (float)Math.sqrt (Math.pow(distanciaX,2)+Math.pow(distanciaZ,2));
//        float seno=distanciaX/hipotenuza;
//        double angulo =(seno*180)/ Math.PI;
//        return angulo;
//    }


//    public void  limpar(){
//        verticeBuffer = null;
//        NormaisBuffer = null;
//        texturaBuffer = null;
//    }
}
