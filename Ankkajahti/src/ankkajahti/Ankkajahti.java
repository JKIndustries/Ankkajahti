/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankkajahti;

import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author jphanski
 */
public class Ankkajahti {

    public static int ankkaKentta = 2;
    public static int ticks = 60;
    public static double gravity = -0.005;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ankka ankka;
        long now = System.currentTimeMillis();
        LinkedList<Ankka> ankat = new LinkedList<>();
        Random r = new Random();
        boolean[][] ankkaTaulu;

        JFrame f = new JFrame("Ankkajahti 0.01");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ikkuna peliIkkuna = new Ikkuna(ankat);
        f.add(peliIkkuna);
        f.pack();
        f.setVisible(true);
        long delay;
        long fpsCounter = System.currentTimeMillis();
        while (true) {
            //1000ms delay happens here            
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

            //Pelitilanteen päivitys
            for (int i = ankat.size() - 1; i >= 0; i--) {
                Ankka a = ankat.get(i);
                a.update();
                if (a.isRemoveable()) {
                    ankat.remove(a);
                }
            }
            
            
            //Pelitilanteen piirtäminen
            
            /*
            ankkaTaulu = new boolean[ankkaKentta][ankkaKentta];
            for (Ankka a : ankat) {
                ankkaTaulu[Math.round((float) a.getX())][Math.round((float) a.getY())] = true;
            }
            for (int j = ankkaKentta - 1; j >= 0; j--) {
                for (int i = 0; i < ankkaKentta; i++) {
                    if (ankkaTaulu[i][j]) {
                        System.out.print(" Quack ");
                    } else {
                        System.out.print("   .   ");
                    }
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("Kentällä " + ankat.size() + " ankkaa.");
            */
            if (System.currentTimeMillis() > fpsCounter + 1000) {
                System.out.println("FPS: " + peliIkkuna.fps);
                peliIkkuna.fps = 0;
                fpsCounter += 1000;
            }
            if (r.nextDouble() < 1.0 - 1.0 / ticks) {
                continue;
            }
            ankka = new Ankka();
            
            ankat.add(ankka);

        }
    }

}
