/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.Controler;
import Model.Heuristique;
import Model.ModelListener;
import Model.Point;
import Model.Segment;
import java.io.File;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author zakariae
 */
public class View implements ModelListener {

    private final Controler ctrl;
    private BspWindow bspWindow;

    /**
     *
     * @param ctrl
     */
    public View(Controler ctrl) {
        this.ctrl = ctrl;
    }

    /**
     *
     */
    public void aff()  {
        System.out.println("hello form " + this.toString());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                bspWindow = new BspWindow(View.this);
                bspWindow.setVisible(true);

            }
        });
    }

    /**
     *
     * @param file
     * @param heur
     */
    public void parseFile(File file,Heuristique heur) {
        ctrl.parseFile(file,heur);
    }

    /**
     *
     * @return
     */
    public Controler getCtrl() {
        return ctrl;
    }

    /**
     *
     * @param theNewListOfSegments
     */
    @Override
    public void updateListOfSegments(ArrayList<Segment> theNewListOfSegments) {
        bspWindow.repaint();
    }

    /**
     *
     * @return
     */
    public ArrayList<Segment> getListOfSegments() {
        return ctrl.getListOfSegments();
    }

    /**
     *
     * @param viewPoint
     * @return
     */
    public ArrayList<Segment> getListOfSortedSegments(Point viewPoint) {
        return ctrl.getListOfSortedSegments(viewPoint);
    }

}
