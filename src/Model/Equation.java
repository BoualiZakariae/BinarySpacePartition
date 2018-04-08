/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * La classe Equation represente l'equation d'une droite definit par deux points
 *
 * @author zakariae
 */
public class Equation {

    //les coefficients de l'equation
    private double a;
    private double b;
    private double c;

    //les deux points de la droite
    private Point p1;
    private Point p2;

    /**
     * constructeur de la classe
     */
    public Equation() {

    }

    /**
     * Constructeur de la classe
     *
     * @param a le coefficient a
     * @param b le coefficient b
     * @param c le coefficient c
     * @throws Exception
     */
    public Equation(double a, double b, double c) throws Exception {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    //
    //
    //we now that X=-b and Y=a

    /**
     * calculer les differents coefficients de l'equation
     *
     * @param p1 un point de la droite
     * @param p2 deuxième point de la droite
     */
    public Equation(Point p1, Point p2) {
        /*                                                    --->  
            X and Y sont les coordonnées du vecteur directeur p1p2
         */

        double X = p2.getX() - p1.getX();
        double Y = p2.getY() - p1.getY();
        this.a = Y;
        this.b = -X;
        c = (-a * p1.getX()) - (b * p1.getY());
    }

    /**
     *
     * @return le coefficient a
     */
    public double getA() {
        return a;
    }

    /**
     *
     * @return le coefficient b
     */
    public double getB() {
        return b;
    }

    /**
     *
     * @return le coefficient c
     */
    public double getC() {
        return c;
    }

    /**
     *
     * @param a
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     *
     * @param b
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     *
     * @param c
     */
    public void setC(double c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return a + "x" + " + " + b + " + " + "y" + " + " + c;
    }

    @Override
    public boolean equals(Object obj) {
        double epsilon = 0.000001;
        Equation theOtherEquation = (Equation) obj;
        if (theOtherEquation == null) {
            return false;
        }
        double quotienA = theOtherEquation.getA() / this.a;
        double quotienB = theOtherEquation.getB() / this.b;
        double quotienC = theOtherEquation.getC() / this.c;

        System.out.println(quotienA + " " + quotienB + " " + quotienC);
        if (Math.abs(quotienA - quotienB) >= epsilon) {
            return false;
        }
        return true;
    }

    /**
     * evaluer un point par rapport à une droite representé par cette equation
     *
     * @param point
     * @return
     */
    public double evalute(Point point) {
        return (this.a * point.getX()) + (this.b * point.getY()) + this.c;
    }

}
