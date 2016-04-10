/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankkajahti;

import java.awt.Graphics2D;

/**
 *
 * @author jphanski
 */
public interface DrawableObject extends Comparable {
    public static final int ANKKA = 1;
    public double getX();
    public double getY();
    public boolean testCollision(double x, double y);
    public void drawObject(Graphics2D g2d, int screenWidth, int screenHeight);
    public int getType();
}
