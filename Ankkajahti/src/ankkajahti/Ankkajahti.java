/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankkajahti;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author jphanski
 */
public class Ankkajahti {

    public static int ankkaKentta = 1;
    public static int ticks = 60;
    public static double gravity = -0.007;
    public static LinkedList<Ankka> ankat;
    public static LinkedList<Ankka> tuhottavat;
    public static int ohiMenneet;
    public static int pisteet;
    public static String titleText;
    public static boolean infoTekstiMuuttunut = true;
    private static String infoText = "";
    private static double infoTextFade = 0.8;
    private static int ruleState;
    private static final int GAMERULES_PRE_GAME = 1;
    private static final int GAMERULES_GAME = 2;
    private static final int GAMERULES_POST_GAME = 3;
    private static int streakCounter = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ankka ankka;
        long now = System.currentTimeMillis();
        ankat = new LinkedList<>();
        tuhottavat = new LinkedList<>();
        Random r = new Random();
        double peliViive = 5;
        int countDown = 5;

        JFrame f = new JFrame("Ankkajahti 0.2");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ikkuna peliIkkuna = new Ikkuna();
        f.add(peliIkkuna);
        f.pack();
        f.setVisible(true);
        long delay;
        long fpsCounter = System.currentTimeMillis();
        pisteet = 0;
        ohiMenneet = 0;
        ruleState = GAMERULES_PRE_GAME;
        titleText = "GAME STARTING!";
        while (true) {

            //Waiting for ticks happens here            
            now += 1000 / ticks;
            delay = now - System.currentTimeMillis();
            if (delay < 1) {
                delay = 1;
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            //Infotekstin piirto ja poisto
            infoTextFade -= 1.0 / ticks;
            if (infoTextFade < 0) {
                infoText = "";
            }

            switch (ruleState) {
                

                case GAMERULES_GAME:
                    if (ohiMenneet > 10) {
                        ruleState = GAMERULES_PRE_GAME;
                        peliViive = 5;
                        countDown = 5;
                        titleText = "GAME OVER...";
                    }
                    //Ankkojen lisääminen
                    if (r.nextDouble() > 1.0 - 0.8 / ticks) {
                        ankka = new Ankka();
                        ankat.add(ankka);
                    }

                    //Pelitilanteen päivitys
                    for (Ankka a : tuhottavat) {
                        ankat.remove(a);
                    }
                    tuhottavat = new LinkedList<>();
                    for (int i = ankat.size() - 1; i >= 0; i--) {
                        Ankka a = ankat.get(i);
                        a.update();
                        if (a.isRemoveable()) {
                            ankat.remove(a);
                            ohiMenneet++;
                            streakCounter = 0;
                            infoTekstiMuuttunut = true;
                            infoText = "Oops!";
                            infoTextFade = 0.3;
                        }
                    }
                    PriorityQueue<DrawableObject> piirrettavat = new PriorityQueue<>();
                    for (int i = 0; i < ankat.size(); i++) {
                        piirrettavat.add(new DrawableAnkka(ankat.get(i)));
                    }
                    peliIkkuna.objektit = piirrettavat;
                    break;
                case GAMERULES_PRE_GAME:
                    peliViive -= 1.0 / ticks;
                    if (peliViive < 0) {
                        infoTextFade = 0.8;
                        infoTekstiMuuttunut = true;
                        infoText = "Peli alkaa!";
                        titleText = "STAGE 1";
                        pisteet = 0;
                        ohiMenneet = 0;
                        ankat = new LinkedList<>();
                        ruleState = GAMERULES_GAME;
                    } else if (peliViive < countDown) {
                        infoTextFade = 0.5;
                        infoTekstiMuuttunut = true;
                        infoText = countDown + "...";

                        countDown--;
                    }
                    break;
            }
        }
    }

    static void tuhoaAnkka(Ankka a) {
        tuhottavat.add(a);
        pisteet++;
        streakCounter++;
        if (streakCounter > 2) {
            infoTekstiMuuttunut = true;
            infoText = streakCounter + " streak!";
            infoTextFade = 0.8;
        }
    }

    static String getTitleText() {
        return titleText;
    }

    static String getInfoText() {
        return infoText;
    }

    static String getPisteText() {
        return "Pisteet: " + pisteet;
    }

    static String getOhiMenneetText() {
        return "Ohimenneitä: " + ohiMenneet;
    }

}
