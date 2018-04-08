/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Direction;
import Model.Heuristique;
import Model.Point;
import Model.Segment;
import Model.SideType;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * la classe BspPanel represente un panneau pour pouvoir dessiner les segments
 * de la scène
 *
 * @author zakariae
 */
public class BspPanel extends JPanel {

    private final BspWindow bspWindow;

    private JButton viewButton = new JButton();
    private Point leftMouse = new Point(0, 0);
    private Point RightMouse = new Point(0, 0);

    private Segment rightLine;
    private Segment leftLine;
    private Segment topLine;
    private Segment downLine;

    //la direction par defaut
    public static Direction direction = Direction.Top;
    //l'heuristique par defaut
    private Heuristique heur = Heuristique.random;
    //la liste qui cotient que les segments qu'on peut projecter
    private ArrayList<Segment> listOfSegmentToBeDrawn = new ArrayList<>();

    private Polygon polygon = new Polygon();

    /**
     *
     * @param bspWindow
     */
    public BspPanel(final BspWindow bspWindow) {
        this.bspWindow = bspWindow;
        this.setVisible(true);
        lineInitialisation();
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (SwingUtilities.isLeftMouseButton(me)) {
                    leftMouse.setX(me.getX());
                    leftMouse.setY(me.getY());
                    viewButton.setBounds(me.getX(), me.getY(), 10, 10);
                    System.out.println("the mouse coordinate are :" + leftMouse.getX() + " " + leftMouse.getY());
                    repaint();

                } else {
                    refreshTheView();

                }

            }

        });
        //evenements liées au changement de direction
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        System.out.println("left");
                        BspPanel.direction = Direction.Left;
                        refreshTheView();
                        bspWindow.changeDirection(Direction.Left);
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("right");
                        BspPanel.direction = Direction.Right;
                        refreshTheView();
                        bspWindow.changeDirection(Direction.Right);
                        break;
                    case KeyEvent.VK_UP:
                        System.out.println("top");
                        BspPanel.direction = Direction.Top;
                        refreshTheView();
                        bspWindow.changeDirection(Direction.Top);
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("down");
                        BspPanel.direction = Direction.Down;
                        refreshTheView();
                        bspWindow.changeDirection(Direction.Down);
                        break;
                    default:
                        break;
                }
            }

        });
        this.add(viewButton);

    }

    /**
     *
     */
    public void resizeThePolygone() {
        polygon.reset();
        polygon.addPoint((int) leftMouse.getX(), (int) leftMouse.getY());
        switch (direction) {
            case Right:
                polygon.addPoint(1350, 0);
                polygon.addPoint(1350, 800);
                break;
            case Left:
                polygon.addPoint(0, 0);
                polygon.addPoint(0, 800);
                break;
            case Down:
                polygon.addPoint(0, 800);
                polygon.addPoint(1350, 800);
                break;
            case Top:
                System.out.println("hello");
                polygon.addPoint(0, 0);
                polygon.addPoint(1350, 0);
                break;
            default:
                break;
        }

    }

    /**
     *
     */
    public void refreshTheView() {
        listOfSegmentToBeDrawn.clear();//we clear the list
        RightMouse.setX(((leftMouse.getX())));
        RightMouse.setY(((leftMouse.getY())));
        resizeThePolygone();
        if (bspWindow.getListOfSegments() != null) {
            if (bspWindow.getListOfSegments().size() > 0) {
                ArrayList<Segment> l = bspWindow.getListOfSortedSegments(RightMouse);
                for (int i = 0; i < l.size(); i++) {
                    addLineTobedrawn(l.get(i));
                }
                bspWindow.getlineViewPanel().repaint();
            }
        }
        repaint();

    }

    /**
     * tester si le segment est contenu dans le polygone
     *
     * @param s le segment à tester
     * @return true si le segment est contenu dans le polygone,non dans le cas
     * contraire
     */
    public boolean isTheSegmentContainedInThePolygone(Segment s) {

        return (polygon.contains(s.getP1().getX(), s.getP1().getY())) && (polygon.contains(s.getP2().getX(), s.getP2().getY()));

    }

    @Override
    protected void paintComponent(Graphics grphcs) {

        super.paintComponent(grphcs);
        Graphics2D g = (Graphics2D) grphcs;
        System.out.println("from bsppanel");
        Line2D l2;
        for (Segment segment : bspWindow.getListOfSegments()) {
            g.setColor(segment.getColor().getAwtColor());
            l2 = new Line2D.Double(segment.getP1().getX(), segment.getP1().getY(),
                    segment.getP2().getX(), segment.getP2().getY());
            g.draw(l2);

        }
        g.drawPolygon(polygon);

    }

    /**
     *
     * @return
     */
    public Model.Point getViewPoint() {
        return this.RightMouse;
    }

    /**
     
     
     */
    private void lineInitialisation() {
        rightLine = new Segment(new Point(1350, 0), new Point(1350, 800));
        topLine = new Segment(new Point(0, 0), new Point(1350, 0));
        leftLine = new Segment(new Point(0, 800), new Point(0, 0));
        downLine = new Segment(new Point(1350, 800), new Point(0, 800));
    }

    private Segment getLeftAngleSegment() {
        switch (direction) {
            case Right:
                return new Segment(leftMouse, new Point(1350, 0));
            case Left:
                return new Segment(leftMouse, new Point(0, 800));
            case Down:
                return new Segment(leftMouse, new Point(1350, 800));
            //(direction.equals(Direction.Top))
            default:
                return new Segment(leftMouse, new Point(0, 0));
        }
    }

    private Segment getRightAngleSegment() {
        switch (direction) {
            case Right:
                return new Segment(leftMouse, new Point(1350, 800));
            case Left:
                return new Segment(leftMouse, new Point(0, 0));
            case Down:
                return new Segment(leftMouse, new Point(0, 800));
            //if (direction.equals(Direction.Top))
            default:
                return new Segment(leftMouse, new Point(1350, 0));
        }
    }

    private Segment getTheCuurrentLine() {
        switch (direction) {
            case Right:
                return rightLine;
            case Left:
                return leftLine;
            case Down:
                return downLine;
            //if (direction.equals(Direction.Top))
            default:
                return topLine;
        }

    }

    /**
     *
     * @param s
     */
    public void addLineTobedrawn(Segment s) {

        Segment s1 = new Segment(leftMouse, s.getP1());
        Segment s2 = new Segment(leftMouse, s.getP2());
        Segment leftAngleSegment = getLeftAngleSegment();
        Segment rightAngleSegment = getRightAngleSegment();

        Segment segmentToBeDrawn = null;
        if (polygon.contains(s.getP1()) && polygon.contains(s.getP2())) {
            //  System.out.println(s.getColor().toString() + " is contained in the polygone  :");
            Point p1 = s1.getIntersectionPoint(getTheCuurrentLine());//
            Point p2 = s2.getIntersectionPoint(getTheCuurrentLine());
            segmentToBeDrawn = new Segment(p1, p2, s.getColor());
        } else if (polygon.contains(s.getP1()) && !polygon.contains(s.getP2())) {
            // System.out.println(s.getColor().toString() + " one point is inside the polygone  :");
            s1 = new Segment(leftMouse, s.getP1());

            Point p1 = s1.getIntersectionPoint(getTheCuurrentLine());
            Point p2 = null;

            Point leftInterPoint = leftAngleSegment.getIntersectionPoint(s);
            Point secondInterPoint = rightAngleSegment.getIntersectionPoint(s);

            if (leftAngleSegment.evalute(s).equals(SideType.Intersect) && inLineSegment(leftAngleSegment, leftInterPoint) == true) {
                p2 = leftAngleSegment.getP2();
                segmentToBeDrawn = new Segment(p1, p2, s.getColor());

            } else if (rightAngleSegment.evalute(s).equals(SideType.Intersect) && inLineSegment(rightAngleSegment, secondInterPoint) == true) {
                p2 = rightAngleSegment.getP2();
                segmentToBeDrawn = new Segment(p1, p2, s.getColor());

            }

        } else if (!polygon.contains(s.getP1()) && polygon.contains(s.getP2())) {
            s1 = new Segment(leftMouse, s.getP2());
            Point p1 = s1.getIntersectionPoint(getTheCuurrentLine());
            Point p2 = null;
            Point leftInterPoint = leftAngleSegment.getIntersectionPoint(s);
            Point secondInterPoint = rightAngleSegment.getIntersectionPoint(s);
            if (leftAngleSegment.evalute(s).equals(SideType.Intersect) && inLineSegment(leftAngleSegment, leftInterPoint) == true) {
                p2 = leftAngleSegment.getP2();
                segmentToBeDrawn = new Segment(p1, p2, s.getColor());

            } else if (rightAngleSegment.evalute(s).equals(SideType.Intersect) && inLineSegment(rightAngleSegment, secondInterPoint) == true) {
                p2 = rightAngleSegment.getP2();
                segmentToBeDrawn = new Segment(p1, p2, s.getColor());

            }

        } else if (leftAngleSegment.evalute(s).equals(SideType.Intersect) && rightAngleSegment.evalute(s).equals(SideType.Intersect)) {
            Point leftInterPoint = leftAngleSegment.getIntersectionPoint(s);
            Point secondInterPoint = rightAngleSegment.getIntersectionPoint(s);
            if (inLineSegment(leftAngleSegment, leftInterPoint) == true && inLineSegment(rightAngleSegment, secondInterPoint) == true) {
                segmentToBeDrawn = new Segment(getTheCuurrentLine().getP1(), getTheCuurrentLine().getP2(), s.getColor());
            }

        }

        if (segmentToBeDrawn != null) {
            listOfSegmentToBeDrawn.add(segmentToBeDrawn);
        }

    }

    /**
     *
     * @return
     */
    public ArrayList<Segment> getListOfSegmentToBeDrawn() {
        return listOfSegmentToBeDrawn;
    }

    /**
     *
     * @param s
     * @param C
     * @return
     */
    public boolean inLineSegment(Segment s, Point C) {
        double epsilon = 0.0001;
        double d = Line2D.ptSegDist(s.getP1().getX(), s.getP1().getY(), s.getP2().getX(), s.getP2().getY(), C.getX(), C.getY());
        if (d <= epsilon) {
            return true;
        }
        return false;

    }

    /**
     *
     * @return
     */
    public Direction getDirection() {
        return direction;
    }

}
