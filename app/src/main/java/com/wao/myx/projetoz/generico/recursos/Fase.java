package com.wao.myx.projetoz.generico.recursos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Fase {

    public Fase() {
    }

    public ArrayList<Cronograma> gerarFase(int fase) {
        Cronograma c;
        ArrayList<Cronograma> cronograma = new ArrayList<>();
        String[] fasex = String.valueOf(fase).split("");
        int indice = 1;
        int indiceLevel = 1;
        if (fasex.length > 1) {
            indice = Integer.parseInt(fasex[1]);
            indiceLevel = Integer.parseInt(fasex[0] + 1);
        } else {
            indice = Integer.parseInt(fasex[0]);
        }
        ArrayList<Integer> pos = new ArrayList<>();
        pos.add(0);
        pos.add(1);
        pos.add(2);
        pos.add(3);
        pos.add(4);
        pos.add(5);
        pos.add(6);
        pos.add(7);


        int dificult = indice < 7 ? indice : 6;
        int base = 2 + (indice + (indiceLevel) + indice) + 1;
        int nivel = 1;
        indice = 1;
        if (base > 150) base = 150;
        if ((fase + 1) % 10 == 0) {

            base = 2;//+ (fase * indiceLevel);
            nivel = 1;
        }


    //   base = 3;

        for (int ii = 0; ii < base; ii++) {

            //    for (int i = 0; i < dificult; i++) {
            if (pos.size() > 0) {
                int in = pos.get(new Random().nextInt(pos.size()));
                if (in < 0) {
                    in *= -1;
                }
                int mod = 0;
                c = new Cronograma();
                c.setId(in);
                c.setPerpetuo(false);
                c.setTimeIN((50 + (100 * ii)) * nivel);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(700);

                switch (in) {
                    case 0:
                    case 1:
                    case 6:
                        mod = 0;
                        break;
                    case 2:
                    case 5:
                        mod = new Random().nextInt(3) + 2;
                        break;
                    case 3:
                        mod = new Random().nextInt(3);
                        break;
                    case 7:
                        mod = 1;
                        break;

                }
                c.setModo(mod);
                if (in != 4) {
                    cronograma.add(c);
                }

                //   pos.remove(pos.indexOf(in));
            }

        }
        if ((fase + 1) % 10 == 0) {
            c = new Cronograma();
            c.setId(100);
            c.setPerpetuo(false);
            c.setTimeIN(Integer.MAX_VALUE);
            c.setTimeOUT(Integer.MAX_VALUE);
//                c.setTimeIN(50);
//                c.setTimeOUT(250);
            c.setTimeMode(100);
            c.setModo(0);
            c.setBoss(true);
            cronograma.add(c);
        }


        c = new Cronograma();
        c.setId(-1);
        c.setPerpetuo(false);
        c.setTimeIN(Integer.MAX_VALUE);
        c.setTimeOUT(Integer.MAX_VALUE);
        c.setTimeMode(1000);
        c.setFim(true);
        cronograma.add(c);

//        for (int i = 0; i < cronograma.size() - 1; i++) {
//
//            while (cronograma.get(i).getId() == cronograma.get(i + 1).getId() && cronograma.get(i).getTimeIN() + 1200 > cronograma.get(i + 1).getTimeIN()) {
//                cronograma.get(i + 1).setTimeIN(cronograma.get(i + 1).getTimeIN() + 1);
//            }
//
//        }

        ArrayList<Cronograma> a = new ArrayList<>();
        ArrayList<Cronograma> b = new ArrayList<>();

        for (int i = 0; i < cronograma.size() - 1; i++) {

            if (cronograma.get(i).getId() == 2) {
                a.add(cronograma.get(i));
            }

            if (cronograma.get(i).getId() == 5) {
                b.add(cronograma.get(i));
            }


        }

        for (int i = 0; i < a.size() - 1; i++) {

            while (a.get(i).getTimeIN() + 1200 > a.get(i + 1).getTimeIN()) {
                a.get(i + 1).setTimeIN(a.get(i + 1).getTimeIN() + 1);
            }

        }


        for (int i = 0; i < b.size() - 1; i++) {

            while (b.get(i).getTimeIN() + 1200 > b.get(i + 1).getTimeIN()) {
                b.get(i + 1).setTimeIN(b.get(i + 1).getTimeIN() + 1);
            }

        }


        for (int i = 0; i < cronograma.size() - 1; i++) {

            if (a.size() > 0) {
                if (cronograma.get(i).getId() == a.get(0).getId()) {
                    cronograma.set(i, a.get(0));
                    a.remove(a.get(0));
                }
            }

            if (b.size() > 0) {

                if (cronograma.get(i).getId() == b.get(0).getId()) {
                    cronograma.set(i, b.get(0));
                    b.remove(b.get(0));

                }
            }

        }
        c = new Cronograma();
        c.setId(1);
        c.setPerpetuo(false);
        c.setTimeIN(100);
        c.setTimeOUT(Integer.MAX_VALUE);
        c.setTimeMode(700);
        c.setFim(true);
        cronograma.add(c);

        Collections.sort(cronograma);

        /////////////////////////////////////////////////////////////////////////////////
        return cronograma;
    }


}
