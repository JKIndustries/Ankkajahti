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
    private final Ankka ankka;
    private double koko = 0.08;
    
    public DrawableAnkka(Ankka ankka) {
        this.ankka = ankka;
    }
    
    @Override
    public double getX() {
        return ankka.getX();
    }

    @Override
    public double getY() {
        return ankka.getY();
    }

    @Override
    public boolean testCollision(double x, double y) {
        if (getX() > x || getY() > y || getX() + koko < x || getY() + koko < y) {
            return false;
        }
        return true;
    }

    @Override
    public void drawObject(Graphics2D g2d, int screenWidth, int screenHeight) {
        g2d.setColor(Color.YELLOW);
        g2d.fillRoundRect((int) (getX() * screenWidth), (int) (getY() * screenHeight), (int) (koko * screenWidth), (int) (koko * screenHeight), 6, 6);
    }

    @Override
    public int getType() {
        return DrawableObject.ANKKA;
    }
    public Ankka getAnkka() {
        return ankka;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
    
}
