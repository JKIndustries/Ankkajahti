/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankkajahti;

/**
 *
 * @author jphanski
 */
public class Ankkajahti {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ankka ankka = new Ankka();
        long now = System.currentTimeMillis();

        while (true) {
            //1000ms delay happens here            
            try {
                Thread.sleep(System.currentTimeMillis() + 1000 - now);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            now += 1000;
            
            //Pelitilanteen päivitys
            ankka.update();
            
            //Pelitilanteen piirtäminen
            System.out.println("Ankka");
        }
    }

}
