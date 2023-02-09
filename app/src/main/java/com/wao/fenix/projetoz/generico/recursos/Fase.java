package com.wao.fenix.projetoz.generico.recursos;

import java.util.ArrayList;
import java.util.Random;

public class Fase {

    public Fase() {
    }

    public ArrayList<Cronograma> gerarFase(int fase) {
        Cronograma c;
        ArrayList<Cronograma> cronograma = new ArrayList<>();

        ArrayList<Integer> pos = new ArrayList<>();
        pos.add(0);
        pos.add(1);
        pos.add(2);
        pos.add(3);
        pos.add(4);
        pos.add(5);
int dificult=1;
        for (int i = 0; i < dificult; i++) {

            int in = pos.get(new Random().nextInt(pos.size()));
            int mod = 0;
            c = new Cronograma();
            c.setId(in);
            c.setPerpetuo(false);
            c.setTimeIN(50 + (700 * i));
            c.setTimeOUT(50 + (700 * i));
            c.setTimeMode(700);

            switch (in) {
                case 0:
                case 1:
                    mod = 0;
                    break;
                case 2:
                case 5:
                    mod = new Random().nextInt(5);
                    break;
                case 3:
                    mod = new Random().nextInt(3);
                    break;


            }
            c.setModo(mod);
            if (in != 4) {
                cronograma.add(c);
            }

            pos.remove(pos.indexOf(in));
        }

        pos = new ArrayList<>();
        pos.add(0);
        pos.add(1);
        pos.add(2);
        pos.add(3);
        pos.add(4);
        pos.add(5);

        for (int i = 0; i < dificult; i++) {

            int in = pos.get(new Random().nextInt(pos.size()));
            int mod = 0;
            c = new Cronograma();
            c.setId(in);
            c.setPerpetuo(false);
            c.setTimeIN(50 + (750 * i));
            c.setTimeOUT(50 + (750 * i));
            c.setTimeMode(700);

            switch (in) {
                case 0:
                case 1:
                    mod = 0;
                    break;
                case 2:
                case 5:
                    mod = new Random().nextInt(5);
                    break;
                case 3:
                    mod = new Random().nextInt(3);
                    break;


            }
            c.setModo(mod);
            if (in != 4) {

                for (Cronograma cc: cronograma) {
                    if(cc.getId()==c.getId() && cc.getTimeIN()+700>=c.getTimeIN()){
                        c.setTimeIN(cc.getTimeIN()+701);
                        c.setTimeOUT(cc.getTimeOUT()+751);
                        break;
                    }
                }
                cronograma.add(c);
            }

            pos.remove(pos.indexOf(in));
        }



        if(fase==1){
            c = new Cronograma();
            c.setId(100);
            c.setPerpetuo(false);
            c.setTimeIN(2800);
            c.setTimeOUT(25000);
            c.setTimeMode(100);
            c.setModo(0);
            c.setBoss(true);
            cronograma.add(c);
        }



        c = new Cronograma();
        c.setId(-1);
        c.setPerpetuo(false);
        c.setTimeIN(3000);
        c.setTimeOUT(Integer.MAX_VALUE);
        c.setTimeMode(1000);
        c.setFim(true);
        cronograma.add(c);

        switch (20) {
            case 1:

                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeOUT(100);
                c.setTimeMode(700);
                c.setModo(2);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(800);
                c.setTimeOUT(800);
                c.setTimeMode(700);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(1600);
                c.setTimeOUT(1600);
                c.setTimeMode(700);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(2400);
                c.setTimeOUT(2400);
                c.setTimeMode(700);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(1800);
                c.setTimeOUT(1900);
                c.setTimeMode(700);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(4000);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);


//                c = new Cronograma();
//                c.setId(2);
//                c.setPerpetuo(false);
//                c.setTimeIN(100);
//                c.setTimeMode(1000);
//                c.setTimeOUT(100);
//                c.setModo(0);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(5);
//                c.setPerpetuo(false);
//                c.setTimeIN(200);
//                c.setTimeOUT(200);
//                c.setTimeMode(1000);
//                c.setModo(2);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(1);
//                c.setPerpetuo(false);
//                c.setTimeIN(500);
//                c.setTimeOUT(1400);
//                c.setTimeMode(700);
//                c.setModo(0);
//                cronograma.add(c);
//
//                c = new Cronograma();
//                c.setId(0);
//                c.setPerpetuo(false);
//                c.setTimeIN(800);
//                c.setTimeOUT(500);
//                c.setTimeMode(700);
//                c.setModo(0);
//                cronograma.add(c);
//
//
//
//
//                c = new Cronograma();
//                c.setId(2);
//                c.setPerpetuo(false);
//                c.setTimeIN(1200);
//                c.setTimeMode(1000);
//                c.setTimeOUT(100);
//                c.setModo(4);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(5);
//                c.setPerpetuo(false);
//                c.setTimeIN(1300);
//                c.setTimeOUT(200);
//                c.setTimeMode(1000);
//                c.setModo(3);
//                cronograma.add(c);
//
//
//
//                c = new Cronograma();
//                c.setId(0);
//                c.setPerpetuo(false);
//                c.setTimeIN(1500);
//                c.setTimeOUT(500);
//                c.setTimeMode(700);
//                c.setModo(0);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(1);
//                c.setPerpetuo(false);
//                c.setTimeIN(1700);
//                c.setTimeOUT(1400);
//                c.setTimeMode(700);
//                c.setModo(0);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(2);
//                c.setPerpetuo(false);
//                c.setTimeIN(1900);
//                c.setTimeMode(1000);
//                c.setTimeOUT(100);
//                c.setModo(2);
//                cronograma.add(c);
//
//                c = new Cronograma();
//                c.setId(0);
//                c.setPerpetuo(false);
//                c.setTimeIN(2000);
//                c.setTimeOUT(600);
//                c.setTimeMode(700);
//                c.setModo(0);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(5);
//                c.setPerpetuo(false);
//                c.setTimeIN(2200);
//                c.setTimeOUT(200);
//                c.setTimeMode(1000);
//                c.setModo(4);
//                cronograma.add(c);
//
//
//
//                c = new Cronograma();
//                c.setId(2);
//                c.setPerpetuo(false);
//                c.setTimeIN(2300);
//                c.setTimeOUT(200);
//                c.setTimeMode(1000);
//                c.setModo(3);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(1);
//                c.setPerpetuo(false);
//                c.setTimeIN(2600);
//                c.setTimeOUT(2600);
//                c.setTimeMode(700);
//                c.setModo(0);
//                cronograma.add(c);
//
//                c = new Cronograma();
//                c.setId(2);
//                c.setPerpetuo(false);
//                c.setTimeIN(2700);
//                c.setTimeMode(1000);
//                c.setTimeOUT(100);
//                c.setModo(3);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(5);
//                c.setPerpetuo(false);
//                c.setTimeIN(2700);
//                c.setTimeOUT(200);
//                c.setTimeMode(1000);
//                c.setModo(2);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(3);
//                c.setPerpetuo(false);
//                c.setTimeIN(3200);
//                c.setTimeOUT(200);
//                c.setTimeMode(1000);
//                c.setModo(1);
//                cronograma.add(c);
//
//
//                c = new Cronograma();
//                c.setId(2);
//                c.setPerpetuo(false);
//                c.setTimeIN(3500);
//                c.setTimeOUT(200);
//                c.setTimeMode(1000);
//                c.setModo(4);
//                cronograma.add(c);
//
//
//
//                c = new Cronograma();
//                c.setId(-1);
//                c.setPerpetuo(false);
//                c.setTimeIN(4000);
//                c.setTimeOUT(Integer.MAX_VALUE);
//                c.setTimeMode(1000);
//                c.setFim(true);
//                cronograma.add(c);

                c = new Cronograma();
                c.setId(100);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(25000);
                c.setTimeMode(100);
                c.setModo(0);
                c.setBoss(true);
                cronograma.add(c);



        }
        /////////////////////////////////////////////////////////////////////////////////
        return cronograma;
    }
}
