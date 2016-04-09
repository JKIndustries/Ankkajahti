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
class Ankka {
    private double x;
    private double y;
    private double speed;
    private double direction;
    
    public Ankka() {
        
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
        double newX;
        double newY;
        newX = getX() + Math.cos(getDirection()) * speed;
        newY = getY() + Math.sin(getDirection()) * speed;
        
        setX(newX);
        setY(newY);
    }

    /**
     * Tarkistaa onko tämä ankka poistettavissa. Syynä poistoon on ankan poistuminen pelialueelta.
     * 
     * @return 
     */
    boolean isRemoveable() {
        int x = Math.round((float) Math.round(getX()));
        int y = Math.round((float) Math.round(getY()));
        if (x >= 20 || x < 0 || y < 0 || y >= 20) {
            return true;
        }
        return false;
    }
}
