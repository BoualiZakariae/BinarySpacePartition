/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.geom.Point2D;

/**
 * La classe represente un point dans un plan
 *
 * @author zakariae
 */
public class Point extends Point2D {

    //les deux cordonnées
    private double x, y;

    /**
     *constructeur de la classe
     */
    public Point() {
    }

    /**
     *constructeur de la classe
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x :" + this.x + " and y : " + this.y; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        Point theOther = (Point) obj;
        return this.x == theOther.getX()
                && this.y == theOther.getY();
    }

    @Override
    public void setLocation(double x, double y) {

    }

}
