package com.wao.myx.projetoz.generico.recursos;

import com.wao.myx.projetoz.generico.recursos.Objeto3d;

public class GirarOBJ {
    public GirarOBJ() {
    }

    public void girarOBJ(Objeto3d obj, int giro, char dir, float velocidadeG) {
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
}
