/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 * Classe Node represente un noeud dans l'arbre BSP
 *
 * @author zakariae
 */
public class Node {

    /*les deux noeuds fils*/
    private Node front;
    private Node back;
    /*la liste des segments de ce noeud , conteanat aussi segment proincipal qui a divisé la reigion*/
    private ArrayList<Segment> listOfSegments = new ArrayList<>();

    /**
     * constructeur de la classe
     */
    public Node() {
    }

    /**
     *
     * @param front
     * @param back
     */
    public Node(Node front, Node back) {
        this.front = front;
        this.back = back;
    }

    /**
     *
     * @return
     */
    public Node getBack() {
        return back;
    }

    /**
     *
     * @return
     */
    public ArrayList<Segment> getListOfSegments() {
        return listOfSegments;
    }

    /**
     *
     * @return
     */
    public Node getFront() {
        return front;
    }

    /**
     *
     * @param back
     */
    public void setBack(Node back) {
        this.back = back;
    }

    /**
     *
     * @param front
     */
    public void setFront(Node front) {
        this.front = front;
    }

    /**
     *
     * @param listOfSegments
     */
    public void setListOfSegments(ArrayList<Segment> listOfSegments) {
        this.listOfSegments = listOfSegments;
    }

    /**
     * ajouter un nouveau segment à la liste des segmets colinéaire
     *
     * @param s un segment colinéaire
     */
    public void addNewSegmentToTheList(Segment s) {
        this.listOfSegments.add(s);
    }

    @Override
    public String toString() {
        return "the node with segment " + listOfSegments.get(0) + " : " + listOfSegments.get(0).getEquation().toString();
    }

    /**
     * retourner la hauteur de ce noeud
     *
     * @return
     */
    public int getHeight() {
        if (front == null && back == null) {
            return 0;
        } else if (front != null && back != null) {
            return 1 + Math.max(front.getHeight(), back.getHeight());
        } else if (front == null) {
            return 1 + back.getHeight();
        } else // (back==null)
        {
            return 1 + front.getHeight();
        }
    }

    /**
     *
     * @return la taille du sous arbre dont la racine est ce noeud
     */
    public int getSize() {
        if (front == null && back == null) {
            return this.getListOfSegments().size();
        } else if (front != null && back != null) {
            return this.getListOfSegments().size() + front.getSize() + back.getSize();
        } else if (front == null) {
            return this.getListOfSegments().size() + back.getSize();
        } else // (back==null)
        {
            return this.getListOfSegments().size() + front.getSize();
        }
    }

}
