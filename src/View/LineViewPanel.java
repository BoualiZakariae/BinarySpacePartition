/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Segment;
import Model.Direction;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

/**
 *
 * @author zakariae
 */
public class LineViewPanel extends JPanel {

    private final BspWindow bspWindow;

    /**
     *
     * @param bspWindow
     */
    public LineViewPanel(BspWindow bspWindow) {
        this.bspWindow = bspWindow;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {

        super.paintComponent(grphcs);
        Graphics2D g = (Graphics2D) grphcs;
        System.out.println("from LineViewPanel");
        Line2D l2;
         if (bspWindow.getListOfSegments() != null) {
            if (bspWindow.getBspPanel().getListOfSegmentToBeDrawn().size() > 0) {
                for (Segment segment : bspWindow.getBspPanel().getListOfSegmentToBeDrawn()) {
                    g.setColor(segment.getColor().getAwtColor());
                    if (bspWindow.getBspPanel().getDirection().equals(Direction.Top) || bspWindow.getBspPanel().getDirection().equals(Direction.Down) )
                        l2 = new Line2D.Double(segment.getP2().getX(), 100,
                            segment.getP1().getX(), 100);
                    else l2 = new Line2D.Double(segment.getP1().getY(), 100,
                            segment.getP2().getY(), 100);
                    g.draw(l2);
                    

                }
            }
        }

       }

}
