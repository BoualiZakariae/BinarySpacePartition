/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Classe Segment défini un segment par deux points, couleur et une equation
 *
 * @author zakariae
 */
public class Segment {

    private Point p1, p2;
    private Colors color;
    private Equation equation;

    /**
     * constructeur de la classe
     */
    public Segment() {
    }

    /**
     * constructeur de la classe
     *
     * @param p1 le premier point
     * @param p2 le deuxième point
     */
    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.equation = new Equation(p1, p2);

    }

    /**
     *
     * @param p1
     * @param p2
     * @param color
     */
    public Segment(Point p1, Point p2, Colors color) {
        this(p1, p2);
        this.color = color;
    }

    /**
     *
     * @return
     */
    public Point getP1() {
        return p1;
    }

    /**
     *
     * @return
     */
    public Point getP2() {
        return p2;
    }

    /**
     *
     * @param p1
     */
    public void setP1(Point p1) {
        this.p1 = p1;
    }

    /**
     *
     * @param p2
     */
    public void setP2(Point p2) {
        this.p2 = p2;
    }

    /**
     *
     * @return l'equation de la droite du segment
     */
    public Equation getEquation() {
        return equation;
    }

    /**
     *
     * @param equation
     */
    public void setEquation(Equation equation) {
        this.equation = equation;
    }

    @Override
    public boolean equals(Object obj) {
        Segment theOtherSegment = (Segment) obj;
        return (this.p1.equals(theOtherSegment.p1) && this.p2.equals(theOtherSegment.p2))
                || (this.p1.equals(theOtherSegment.p2) && this.p2.equals(theOtherSegment.p1));
    }

    /**
     * evaluer un point par rapport à la droite contanant le segment courant
     *
     * @param point le point à evaluer
     * @return le coté du point par rapport au segment
     */
    public SideType evalute(Point point) {
        double x = equation.evalute(point);
        double epsilon = 0.0000001;
        if ((x < epsilon && x >= 0) || (x > -epsilon && x <= 0)) {
            return SideType.OnLineSegment;
        } else if (x > 0) {
            return SideType.Front;
        } else /*(x < 0)*/ {
            return SideType.Back;
        }

    }

    /**
     * évaluer un segment s par rapport à la droite contanant le segment courant
     *
     * @param s le segment à evaluer
     * @return le coté du segment
     */
    public SideType evalute(Segment s) {
        SideType sidePoint1 = this.evalute(s.getP1());
        SideType sidePoint2 = this.evalute(s.getP2());

        if ((sidePoint1 == SideType.Back && sidePoint2 == SideType.Front) || (sidePoint2 == SideType.Back && sidePoint1 == SideType.Front)) {
            return SideType.Intersect;
        }
        if (sidePoint1 == SideType.Back && sidePoint2 == SideType.Back) {
            return SideType.Back;
        }
        if (sidePoint1 == SideType.Front && sidePoint2 == SideType.Front) {
            return SideType.Front;
        }
        if (sidePoint1 == SideType.OnLineSegment && sidePoint2 == SideType.OnLineSegment) {
            return SideType.Collinear;
        }

        if (sidePoint1 == SideType.OnLineSegment && sidePoint2 != SideType.OnLineSegment) {
            return sidePoint2;
        }
        /**
         * if (sidePoint2==SideType.OnLineSegment &&
         * sidePoint1!=SideType.OnLineSegment)*
         */
        return sidePoint1;

    }

    /**
     * diviser un segment @segmentToSpilt en deux segment , @theFrontSegment et
     *
     * @theBackSegment
     *
     * @param segmentToSpilt le segment à diviser
     * @param theFrontSegment le nouveau segment qui se trouve devant le segment
     * courant
     * @param theBackSegmentle nouveau segment qui se trouve derrière le segment
     * courant
     */
    public void spilt(Segment segmentToSpilt, Segment theFrontSegment, Segment theBackSegment) {
        Point intersectionPoint = getIntersectionPoint(segmentToSpilt);
        if (this.evalute(segmentToSpilt.getP1()) == SideType.Back) {
            theBackSegment.setP1(segmentToSpilt.getP1());
            theBackSegment.setP2(intersectionPoint);
            theFrontSegment.setP1(intersectionPoint);
            theFrontSegment.setP2(segmentToSpilt.getP2());

        } else {
            theBackSegment.setP1(segmentToSpilt.getP2());
            theBackSegment.setP2(intersectionPoint);
            theFrontSegment.setP1(intersectionPoint);
            theFrontSegment.setP2(segmentToSpilt.getP1());
        }
        theBackSegment.setColor(segmentToSpilt.getColor());
        theFrontSegment.setColor(segmentToSpilt.getColor());
        theBackSegment.setEquation(segmentToSpilt.getEquation());
        theFrontSegment.setEquation(segmentToSpilt.getEquation());
    }

    @Override
    public String toString() {
        return color.getColorName() + " segment with endpoints " + p1.toString() + " and " + p2.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    // we are sure that the two segment intersect in only one point
    //a1x+b1y+c1 this
    //a2x+b2y+c2 the other segment
    //  y = (c1a2 - c2a1) / (a1b2 - a2b1)
    //   x = -c1 / a1 - b1y / a1
    /**
     * calculer le point d'inserctions de de ladroite contenant le segment
     * courant avec le segment @segment2
     *
     * @param segment2 un segment
     * @return le point d'intersection
     */
    public Point getIntersectionPoint(Segment segment2) {

        double x = 0;
        double y = 0;
        /**
         * dans le cas ou la droit et le segment sont perpondicuare*
         */
        if (this.getEquation().getA() == 0 && segment2.getEquation().getB() == 0) {
            return new Point(((segment2.getEquation().getC() * -1) / segment2.getEquation().getA()), ((this.getEquation().getC() * -1) / this.getEquation().getB()));
        } else if (segment2.getEquation().getA() == 0 && this.getEquation().getB() == 0) {
            return new Point(((this.getEquation().getC() * -1) / this.getEquation().getA()), ((segment2.getEquation().getC() * -1) / segment2.getEquation().getB()));
        }

        y = ((this.getEquation().getC() * segment2.getEquation().getA())
                - (segment2.getEquation().getC() * this.getEquation().getA()))
                / ((this.getEquation().getA() * segment2.getEquation().getB())
                - (segment2.getEquation().getA() * this.getEquation().getB()));
        if (this.getEquation().getA() != 0) {
            x = (-this.getEquation().getC() / this.getEquation().getA())
                    - ((this.getEquation().getB() * y) / this.getEquation().getA());
        } else {
            x = (-segment2.getEquation().getC() / segment2.getEquation().getA())
                    - ((segment2.getEquation().getB() * y) / segment2.getEquation().getA());
        }
        return new Point(x, y);
    }

    /**
     *
     * @return la couleur du segment
     */
    public Colors getColor() {
        return this.color;
    }

    private void setColor(Colors color) {
        this.color = color;
    }

}
