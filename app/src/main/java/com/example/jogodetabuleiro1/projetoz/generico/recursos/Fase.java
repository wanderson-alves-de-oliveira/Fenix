package com.example.jogodetabuleiro1.projetoz.generico.recursos;

import java.util.ArrayList;

public class Fase {

    public Fase(){}
    public  ArrayList<Cronograma> gerarFase(int fase) {
        Cronograma c;
        ArrayList<Cronograma>   cronograma = new ArrayList<>();
        switch (fase) {
            case 1:

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(200);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(500);
                c.setTimeOUT(1400);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(800);
                c.setTimeOUT(500);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);




                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1300);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1500);
                c.setTimeOUT(500);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1700);
                c.setTimeOUT(1400);
                c.setTimeMode(700);
                c.setModo(1);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1900);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(2);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(600);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(2200);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2300);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(2400);
                c.setTimeOUT(1400);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2500);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(2500);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(3000);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;


            case 2:

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(300);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(4);
                c.setPerpetuo(false);
                c.setTimeIN(600);
                c.setTimeOUT(600);
                c.setTimeMode(800);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1500);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1600);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(1800);
                c.setTimeOUT(1900);
                c.setTimeMode(700);
                c.setModo(1);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(1200);
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

                break;
            case 3:


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(400);
                c.setTimeMode(1000);
                c.setTimeOUT(400);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(25000);
                c.setTimeMode(100);
                c.setModo(0);
                c.setBoss(true);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;

            case 4:

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(200);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(500);
                c.setTimeOUT(1400);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(800);
                c.setTimeOUT(500);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);




                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1300);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1500);
                c.setTimeOUT(500);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1700);
                c.setTimeOUT(1400);
                c.setTimeMode(700);
                c.setModo(1);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1900);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(2);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(600);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(2200);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2300);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(2400);
                c.setTimeOUT(1400);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2500);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(2500);
                c.setTimeOUT(200);
                c.setTimeMode(1000);
                c.setModo(2);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(3000);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;


            case 5:

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(300);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(4);
                c.setPerpetuo(false);
                c.setTimeIN(600);
                c.setTimeOUT(600);
                c.setTimeMode(800);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1500);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1600);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(1800);
                c.setTimeOUT(1900);
                c.setTimeMode(700);
                c.setModo(1);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(1200);
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

                break;
            case 6:


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(400);
                c.setTimeMode(1000);
                c.setTimeOUT(400);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(25000);
                c.setTimeMode(100);
                c.setModo(0);
                c.setBoss(true);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;


            case 7:

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(300);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(4);
                c.setPerpetuo(false);
                c.setTimeIN(600);
                c.setTimeOUT(600);
                c.setTimeMode(800);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1500);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1600);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(1800);
                c.setTimeOUT(1900);
                c.setTimeMode(700);
                c.setModo(1);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(1200);
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

                break;
            case 8:
                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(300);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(4);
                c.setPerpetuo(false);
                c.setTimeIN(600);
                c.setTimeOUT(600);
                c.setTimeMode(800);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1500);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1600);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(1800);
                c.setTimeOUT(1900);
                c.setTimeMode(700);
                c.setModo(1);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(1200);
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
                break;

            case 9:


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(400);
                c.setTimeMode(1000);
                c.setTimeOUT(400);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(25000);
                c.setTimeMode(100);
                c.setModo(0);
                c.setBoss(true);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;

            case 10:

                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(300);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(4);
                c.setPerpetuo(false);
                c.setTimeIN(600);
                c.setTimeOUT(600);
                c.setTimeMode(800);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1500);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1600);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(1800);
                c.setTimeOUT(1900);
                c.setTimeMode(700);
                c.setModo(1);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(1200);
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
                break;
            case 11:
                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(100);
                c.setTimeMode(1000);
                c.setTimeOUT(100);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(300);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(4);
                c.setPerpetuo(false);
                c.setTimeIN(600);
                c.setTimeOUT(600);
                c.setTimeMode(800);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(5);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(1500);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(4);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1600);
                c.setTimeOUT(1200);
                c.setTimeMode(700);
                c.setModo(0);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(3);
                c.setPerpetuo(false);
                c.setTimeIN(1800);
                c.setTimeOUT(1900);
                c.setTimeMode(700);
                c.setModo(1);
                cronograma.add(c);



                c = new Cronograma();
                c.setId(2);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(300);
                c.setTimeMode(1000);
                c.setModo(3);
                cronograma.add(c);

                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(2000);
                c.setTimeOUT(1200);
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
                break;

            case 12:


                c = new Cronograma();
                c.setId(1);
                c.setPerpetuo(false);
                c.setTimeIN(400);
                c.setTimeMode(1000);
                c.setTimeOUT(400);
                c.setModo(0);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(0);
                c.setPerpetuo(false);
                c.setTimeIN(1200);
                c.setTimeOUT(25000);
                c.setTimeMode(100);
                c.setModo(0);
                c.setBoss(true);
                cronograma.add(c);


                c = new Cronograma();
                c.setId(-1);
                c.setPerpetuo(false);
                c.setTimeIN(1400);
                c.setTimeOUT(Integer.MAX_VALUE);
                c.setTimeMode(1000);
                c.setFim(true);
                cronograma.add(c);

                break;
        }
        /////////////////////////////////////////////////////////////////////////////////
return cronograma;
    }
}
