package com.wao.fenix.projetoz.generico;

import com.wao.fenix.projetoz.generico.recursos.Vetor3;

public class pegarGrauDeLocalizacao {

    public double grauDeGiro(Vetor3 obj, Vetor3 alvo) {
        float distanciaX=alvo.x-obj.x;
        float distanciaZ=alvo.z-obj.z;
        float hipotenuza = (float)Math.sqrt (Math.pow(distanciaX,2)+Math.pow(distanciaZ,2));
        float seno=distanciaX/hipotenuza;
        double angulo =(seno*180)/ Math.PI;
        return angulo;
    }
}
