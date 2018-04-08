/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.util.ArrayList;
import javax.swing.event.EventListenerList;

/**
 *
 * @author zakariae
 */
public class Model {

    //listener
    private final EventListenerList listeners = new EventListenerList();
    private ArrayList<Segment> listOfSegments = new ArrayList<>();
   // private ArrayList<Segment> listOfSortedSegments = new ArrayList<>();

    private final FileParser fileToParser = new FileParser(this);
    private BspTree bspTree;

    /**
     *
     */
    public Model() {

    }

    /*
     add a new listener to the list of listener
     a view as an example
     */

    /**
     *
     * @param listener
     */

    public void addListener(ModelListener listener) {
        listeners.add(ModelListener.class, listener);
    }

    /*
     remove a listener
     */

    /**
     *
     * @param l
     */

    public void removeListener(ModelListener l) {
        listeners.remove(ModelListener.class, l);
    }

    /**
     *
     * @return
     */
    public ArrayList<Segment> getListOfSegments() {
        return this.listOfSegments;
    }

    /*
     create a list of segments
     from a txt file
     */

    /**
     *
     * @param file
     * @return
     */

    public ArrayList<Segment> parseFile(File file) {
        this.listOfSegments = fileToParser.parse(file);
        return listOfSegments;

    }

    /**
     *
     * @param file
     * @param heur
     */
    public void updateTheListOfSegments(File file, Heuristique heur) {
        System.out.println("updateTheListOfSegments Method");

        this.listOfSegments.clear();
        try {
            this.listOfSegments = parseFile(file);
        } catch (Exception ex) {
            System.out.println("we coudn't parse the file");
        }
        Node root = new Node();
        this.bspTree = new BspTree(root, heur);
        this.bspTree.buildBinarySpacePartitionTree(root, new ArrayList<>(listOfSegments));
        ModelListener[] listenerList = (ModelListener[]) listeners.getListeners(ModelListener.class);
        for (ModelListener listener : listenerList) {
            listener.updateListOfSegments(listOfSegments);
        }
    }

    /**
     *
     * @param viewPoint
     * @return
     */
    public ArrayList<Segment> getListOfSortedSegments(Point viewPoint) {
        bspTree.clearTheListOfSortedSegments();
        System.out.println("the root is " + this.bspTree.getRoot().getListOfSegments().get(0).getColor());
        bspTree.pantherAlgo(viewPoint, this.bspTree.getRoot());
        return bspTree.getListOfSortedSegments();

    }

}
