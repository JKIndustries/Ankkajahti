/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankkajahti;

import java.util.Random;

/**
 *
 * @author jphanski
 */
class Ankka {

    private double x;
    private double y;
    private double speed;
    private double direction;
    private static Random r = new Random();
    public int destruction = 8;
    
    
    public Ankka() {
        setX(r.nextDouble());
        setY(0.70);
        
        setDirection(r.nextDouble() * Math.PI / 8 + (14 - 5 * getX()) * Math.PI / 8);
        //setDirection(Math.PI / 4);
        setSpeed(r.nextDouble() * 0.15 + 0.6);
        
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void update() {
        double deltaX;
        double deltaY;
        deltaX = Math.cos(getDirection()) * speed / Ankkajahti.ticks;
        deltaY = Math.sin(getDirection()) * speed / Ankkajahti.ticks - Ankkajahti.gravity / Ankkajahti.ticks;
        setSpeed(Ankkajahti.ticks * Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
        setDirection(Math.atan2(deltaY, deltaX));

        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }

    /**
     * Tarkistaa onko tämä ankka poistettavissa. Syynä poistoon on ankan
     * poistuminen pelialueelta.
     *
     * @return
     */
    boolean isRemoveable() {
        int x = Math.round((float) Math.round(getX()));
        int y = Math.round((float) Math.round(getY()));
        if (x >= Ankkajahti.ankkaKentta || x < 0 || y < 0 || y >= Ankkajahti.ankkaKentta) {
            return true;
        }
        return false;
    }
}
