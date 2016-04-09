/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankkajahti;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author jphanski
 */
public class Ankkajahti {
    public static int ankkaKentta = 10;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ankka ankka;
        long now = System.currentTimeMillis();
        LinkedList<Ankka> ankat = new LinkedList<>();
        Random r = new Random();
        boolean[][] ankkaTaulu;

        while (true) {
            //1000ms delay happens here            
            now += 500;
            try {
                Thread.sleep(now - System.currentTimeMillis());
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
            ankkaTaulu = new boolean[ankkaKentta][ankkaKentta];
            for (Ankka a : ankat) {
                ankkaTaulu[Math.round((float) a.getX())][Math.round((float) a.getY())] = true;
            }
            for (int j = ankkaKentta - 1; j >= 0; j--) {
                for (int i = 0; i < ankkaKentta; i++) {
                    if (ankkaTaulu[i][j]) {
                        System.out.print(" Quack ");
                    } else {
                        System.out.print("  ..  ");
                    }
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("Kentällä " + ankat.size() + " ankkaa.");
            ankka = new Ankka();
            ankka.setDirection(r.nextDouble() * Math.PI * 2);
            ankka.setSpeed(r.nextDouble());
            ankka.setX(5);
            ankka.setY(5);
            
            ankat.add(ankka);
            
        }
    }

}
