/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankkajahti;

import static ankkajahti.Ikkuna.hitboxKoko;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author jphanski
 */
public class DrawableAnkka implements DrawableObject {
    private double x;
    private double y;
    private double leveys;
    private double korkeus;
    
    public DrawableAnkka(Ankka ankka) {
        
    }
    
    @Override
    public double getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean testCollision(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawObject(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillRoundRect((int) x, (int) y, (int) leveys, (int) korkeus, 6, 6);
    }
    
}
