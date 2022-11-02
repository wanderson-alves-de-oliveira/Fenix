package com.example.jogodetabuleiro1.projetoz.generico.recursos;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LeitorDeObj {


    public float[] vertecisA;
    public int quadrosDeanimacao ;
    public float[] vertecisANormais;
    public float[] texturaT;
    public short[] indexesA;
    public short[] indexesAN;
    private String nome;

    int linhaR =0;
    int  indR=0;
    public  ArrayList<Short> indexesz ;
    public  ArrayList<Short> indexeszN;
    public  ArrayList<Short> indexeszx ;
    public  ArrayList<Short> indexeszNx;


    public ArrayList<Vetor3> vertecisB;
    public ArrayList<Float> vertecis;
    public ArrayList<Float> vertecisN;
    public ArrayList<Vetor3> vertecisNormais;
    public ArrayList<Short> indexesB;
    public ArrayList<Short> indexesBN;
    public ArrayList<Vetor2> textura;
    public ArrayList<Short> texturaI;
    public ArrayList<Float> texturaz;
    public int[] texturaP;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuadrosDeanimacao() {
        return this.quadrosDeanimacao;
    }

    public void setQuadrosDeanimacao(int quadrosDeanimacao) {
        this.quadrosDeanimacao = quadrosDeanimacao;
    }

    public LeitorDeObj(AssetManager asset, String obj) throws IOException {

        indexesz = new ArrayList<>();
        indexeszN = new ArrayList<>();
        indexeszx = new ArrayList<>();
        indexeszNx = new ArrayList<>();
        vertecisB = new ArrayList<Vetor3>();
        vertecis = new ArrayList<Float>();
        vertecisN = new ArrayList<Float>();
        vertecisNormais = new ArrayList<Vetor3>();
        indexesB = new ArrayList<Short>();
        indexesBN = new ArrayList<Short>();
        textura = new ArrayList<Vetor2>();
        texturaI = new ArrayList<Short>();
        texturaz = new ArrayList<Float>();


        InputStream file = asset.open( obj );

        BufferedReader read = new BufferedReader( new InputStreamReader( file ) );

        String currentLine;


        while ((currentLine = read.readLine()) != null) {
            linhaR++;
            if (currentLine.startsWith( "mtllib " )) {
                nome = currentLine.split( " " )[1] ;

            }
            else if (currentLine.startsWith( "frames: " )) {

                quadrosDeanimacao = Integer.valueOf( currentLine.split( " " )[1] );

            } else if (currentLine.startsWith( "v " )) {

                vertecisB.add( new Vetor3( Float.valueOf( currentLine.split( " " )[1] ), Float.valueOf( currentLine.split( " " )[2] ),
                        Float.valueOf( currentLine.split( " " )[3] ) ) );

            } else if (currentLine.startsWith( "vt " )) {
//
                textura.add( new Vetor2( Float.valueOf( currentLine.split( " " )[1] ), 1 - Float.valueOf( currentLine.split( " " )[2] ) ) );

            } else if (currentLine.startsWith( "vn " )) {
//
                vertecisNormais.add( new Vetor3( Float.valueOf( currentLine.split( " " )[1] ), Float.valueOf( currentLine.split( " " )[2] ),
                        Float.valueOf( currentLine.split( " " )[3] ) ) );
            } else if (currentLine.startsWith( "f " )) {
                for (int j = 1; j < currentLine.split( " " ).length; j++) {


                    indexesz.add( (short) (Short.parseShort( currentLine.split( " " )[j].split( "/" )[0] ) - 1) );

                    texturaI.add( (short) (Short.parseShort( currentLine.split( " " )[j].split( "/" )[1] ) - 1) );
                    if (currentLine.split( " " )[j].split( "/" ).length == 3) {
                        indexeszN.add( (short) (Short.parseShort( currentLine.split( " " )[j].split( "/" )[2] ) - 1) );
                    }


                }
                if(currentLine.split( " " ).length==4){
                    for (int j = 0; j < currentLine.split( " " ).length-1; j++) {

                        indexesB.add( (short) (indR+j) );

                        indexesBN.add( (short) (indR + j) );
                    }
                    indR+=currentLine.split( " " ).length-1;

                }
                else  if(currentLine.split( " " ).length==5){


                    indexesB.add( (short) indR );
                    indexesB.add( (short) (indR + 1) );
                    indexesB.add( (short) (indR + 2) );
                    indexesB.add( (short) (indR + 2) );
                    indexesB.add( (short) indR );
                    indexesB.add( (short) (indR + 3) );

                    indexesBN.add( (short) indR );
                    indexesBN.add( (short) (indR + 1) );
                    indexesBN.add( (short) (indR + 2) );
                    indexesBN.add( (short) (indR + 2) );
                    indexesBN.add( (short) indR );
                    indexesBN.add( (short) (indR + 3) );
                    indR+=currentLine.split( " " ).length-1;

                }

            }

        }


        for (int j = 0; j < quadrosDeanimacao; j++) {


            for (int i = 0; i < indexesz.size(); i++) {

                vertecis.add( vertecisB.get( indexesz.get( i ) + j * vertecisB.size() / quadrosDeanimacao ).x );
                vertecis.add( vertecisB.get( indexesz.get( i ) + j * vertecisB.size() / quadrosDeanimacao ).y );
                vertecis.add( vertecisB.get( indexesz.get( i ) + j * vertecisB.size() / quadrosDeanimacao ).z );


            }


        }



        indexesA = new short[indexesB.size()];
        for (int i = 0; i < indexesB.size(); i++) {

            indexesA[i] = indexesB.get( i );

        }

        vertecisA = new float[vertecis.size()];
        texturaP = new int[texturaI.size()];

        float first = 0;
        for (int i = 0; i < vertecis.size(); i++) {

            vertecisA[i] = vertecis.get( i );

        }


        if (vertecisNormais.size() > 0) {
            for (int i = 0; i < indexeszN.size(); i++) {

                vertecisN.add( vertecisNormais.get( indexeszN.get( i ) ).x );
                vertecisN.add( vertecisNormais.get( indexeszN.get( i ) ).y );
                vertecisN.add( vertecisNormais.get( indexeszN.get( i ) ).z );

            }

            indexesAN = new short[indexesBN.size()];
            for (int i = 0; i < indexesBN.size(); i++) {

                indexesAN[i] = indexesBN.get( i );

            }


            vertecisANormais = new float[vertecisN.size()];
            for (int i = 0; i < vertecisN.size(); i++) {

                vertecisANormais[i] = vertecisN.get( i );

            }


        }

        if(textura.size()>0) {

            for (int i = 0; i < texturaI.size(); i++) {

                texturaz.add( textura.get( texturaI.get( i ) ).x );
                texturaz.add( textura.get( texturaI.get( i ) ).y );


            }
            texturaT = new float[texturaz.size()];
            for (int i = 0; i < texturaz.size(); i++) {
                texturaT[i] = texturaz.get( i );


            }
        }
    }

    public float[] emQuadrarVertecisA(int inicio){
        float[] vertecisANovo = new float[vertecisA.length/ quadrosDeanimacao];
        int pos = 0 ;
        for(int i =inicio; i<inicio+vertecisANovo.length; i++) {

            vertecisANovo[pos]= vertecisA[i];
            pos++;
        }
        return vertecisANovo;

    }
    public float[] emQuadrarvertecisANormais(int inicio){
        float[] vertecisANormaisNovo = new float[vertecisANormais.length/ quadrosDeanimacao];
        int pos = 0 ;
        for(int i =inicio; i<inicio+vertecisANormaisNovo.length; i++) {

            vertecisANormaisNovo[pos]= vertecisANormais[i];
            pos++;
        }
        return vertecisANormaisNovo;

    }


    public List<PositionVetor>    emQuadrarVertecisA(float[] mod,int inicio){
        List<PositionVetor>  posmod = new ArrayList<>(  );
        int pos = 0 ;
        for(int i =inicio; i<inicio+mod.length; i++) {
            if(mod[pos]!= vertecisA[i]){
                PositionVetor pv = new PositionVetor( pos, vertecisA[i] );
                posmod.add( pv );
            }
            pos++;
        }
        return posmod;

    }
}