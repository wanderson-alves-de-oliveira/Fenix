package com.example.jogodetabuleiro1.projetoz.generico.recursos;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

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
public class Tut implements Serializable {


    private short[] indicesDeVertices;
    private short[] indicesNormais;

    private float texture[];

    private FloatBuffer[] verticeBuffer;
    private FloatBuffer[] NormaisBuffer;
    private ShortBuffer indiceBuffer;
    private ShortBuffer indiceNormaisBuffer;
    private int frames;
    private FloatBuffer texturaBuffer;

    public short[] getIndicesDeVertices() {
        return this.indicesDeVertices;
    }

    public void setIndicesDeVertices(short[] indicesDeVertices) {
        this.indicesDeVertices = indicesDeVertices;
    }

    public short[] getIndicesNormais() {
        return this.indicesNormais;
    }

    public void setIndicesNormais(short[] indicesNormais) {
        this.indicesNormais = indicesNormais;
    }

    public float[] getTexture() {
        return this.texture;
    }

    public void setTexture(float[] texture) {
        this.texture = texture;
    }

    public FloatBuffer[] getVerticeBuffer() {
        return this.verticeBuffer;
    }

    public void setVerticeBuffer(FloatBuffer[] verticeBuffer) {
        this.verticeBuffer = verticeBuffer;
    }

    public FloatBuffer[] getNormaisBuffer() {
        return this.NormaisBuffer;
    }

    public void setNormaisBuffer(FloatBuffer[] normaisBuffer) {
        this.NormaisBuffer = normaisBuffer;
    }

    public ShortBuffer getIndiceBuffer() {
        return this.indiceBuffer;
    }

    public void setIndiceBuffer(ShortBuffer indiceBuffer) {
        this.indiceBuffer = indiceBuffer;
    }

    public ShortBuffer getIndiceNormaisBuffer() {
        return this.indiceNormaisBuffer;
    }

    public void setIndiceNormaisBuffer(ShortBuffer indiceNormaisBuffer) {
        this.indiceNormaisBuffer = indiceNormaisBuffer;
    }

    public int getFrames() {
        return this.frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public FloatBuffer getTexturaBuffer() {
        return this.texturaBuffer;
    }

    public void setTexturaBuffer(FloatBuffer texturaBuffer) {
        this.texturaBuffer = texturaBuffer;
    }

//ARMAZENA vertices EM BUFFER



    public  Tut(int frame,int numV,int numVn ,int numIv,int numIvn,int numVt,AssetManager assets,String asset) throws IOException {

        frames = frame;
        ByteBuffer vbb;
        verticeBuffer = new FloatBuffer[frame];
        InputStream file = assets.open( asset);
        BufferedReader read = new BufferedReader( new InputStreamReader( file ) );
        String currentLine;

        float[] vertic = new float[numV];
        for (int i = 0; i < vertic.length; i++) {
            currentLine = read.readLine();
            if (currentLine.startsWith( "v " )) {
                vertic[i] = Float.valueOf( currentLine.split( " " )[1] );
            }
        }
        for (int i = 0; i < verticeBuffer.length; i++) {
            float[] verticTemp = new float[numV/frame];

            for (int ii = 0; ii < verticTemp.length; ii++) {
                verticTemp[ii] = vertic[ii + ((numV/frame) * i)];
            }
            vbb = ByteBuffer.allocateDirect((numV/frame) * 4 );
            vbb.order( ByteOrder.nativeOrder() );
            verticeBuffer[i] = vbb.asFloatBuffer();
            verticeBuffer[i].put( verticTemp );
            verticeBuffer[i].position( 0 );
        }


        NormaisBuffer = new FloatBuffer[frame];
        vertic = new float[numVn];
        for (int i = 0; i < vertic.length; i++) {
            currentLine = read.readLine();
            if (currentLine.startsWith( "vn " )) {
                vertic[i] = Float.valueOf( currentLine.split( " " )[1] );
            }
        }
        for (int i = 0; i < NormaisBuffer.length; i++) {
            float[] verticTemp = new float[numVn/frame];

            for (int ii = 0; ii < verticTemp.length; ii++) {
                verticTemp[ii] = vertic[ii + ((numVn/frame) * i)];
            }
            vbb = ByteBuffer.allocateDirect( (numVn/frame) * 4 );
            vbb.order( ByteOrder.nativeOrder() );
            NormaisBuffer[i] = vbb.asFloatBuffer();
            NormaisBuffer[i].put( verticTemp );
            NormaisBuffer[i].position( 0 );
        }


        this.indicesDeVertices = new short[numIv];

        for (int i = 0; i < this.indicesDeVertices.length; i++) {
            currentLine = read.readLine();
            if (currentLine.startsWith( "iv " )) {
                this.indicesDeVertices[i] = Short.valueOf( currentLine.split( " " )[1] );
            }
        }


        //ARMAZENA indicesDeVertices EM BUFFER
        ByteBuffer ibb = ByteBuffer.allocateDirect( indicesDeVertices.length * 2 );
        ibb.order( ByteOrder.nativeOrder() );
        indiceBuffer = ibb.asShortBuffer();
        indiceBuffer.put( indicesDeVertices );
        indiceBuffer.position( 0 );


        this.indicesNormais = new short[numIvn];

        for (int i = 0; i < this.indicesNormais.length; i++) {
            currentLine = read.readLine();
            if (currentLine.startsWith( "ivn " )) {
                this.indicesNormais[i] = Short.valueOf( currentLine.split( " " )[1] );
            }
        }


        //ARMAZENA indicesNormais EM BUFFER
        ByteBuffer ibbN = ByteBuffer.allocateDirect( indicesNormais.length * 2 );
        ibbN.order( ByteOrder.nativeOrder() );
        indiceNormaisBuffer = ibbN.asShortBuffer();
        indiceNormaisBuffer.put( indicesNormais );
        indiceNormaisBuffer.position( 0 );


        this.texture = new float[numVt];

        for (int i = 0; i < this.texture.length; i++) {
            currentLine = read.readLine();
            if (currentLine.startsWith( "vt " )) {
                this.texture[i] = Float.valueOf( currentLine.split( " " )[1] );
            }
        }

        //ARMAZENA texture EM BUFFER
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect( texture.length * 4 );
        byteBuffer.order( ByteOrder.nativeOrder() );
        texturaBuffer = byteBuffer.asFloatBuffer();
        texturaBuffer.put( texture );
        texturaBuffer.position( 0 );

    }

}
