/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author zakariae
 *
 * Utilities est une classe utilitaire contenant des methodes statiques utiles
 * pour les heuristique
 */
public class Utilities {

    /**
     * cette methode est applelé lorsque l'heurisque H2 estutilisé pour la
     * construction de L'arbre BSP,elle permet de reourner l'indice du segment
     * qui minimise fd ,dans le cas où on a plusieur tel segments on appel une
     * autre methode pour choisir celui qui maxiise g, l'indice represente
     * l'indice du segment dans la liste des segments
     *
     * @param listOfSegments la liste des segments
     * @return le segment selon l'heuristique H2
     */
    public static int getTheIndiceOfTheBestSegment(ArrayList<Segment> listOfSegments) {
        int theIndiceOfTheBestSegment = 0;
        int theMinNumOfIntersection = 9999999;
        int currentNumOfIntersectrion = 0;
        /*on définit une liste d'indice qui contiendra les indices des segments  qui vont maximiser fd**/
        ArrayList<Integer> listOfIndicesOfBestSegments = new ArrayList<>();
        for (int i = 0; i < listOfSegments.size(); i++) {
            for (int j = 0; j < listOfSegments.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (listOfSegments.get(i).evalute(listOfSegments.get(j)).equals(SideType.Intersect)) {
                    currentNumOfIntersectrion++;
                }
            }
            if (currentNumOfIntersectrion < theMinNumOfIntersection) {
                theMinNumOfIntersection = currentNumOfIntersectrion;
                theIndiceOfTheBestSegment = i;
                listOfIndicesOfBestSegments.clear();
                listOfIndicesOfBestSegments.add(i);
            } else if (currentNumOfIntersectrion == theMinNumOfIntersection) {
                listOfIndicesOfBestSegments.add(i);
            }
            currentNumOfIntersectrion = 0;
        }
        if (listOfIndicesOfBestSegments.size() == 1) {/*dans le cas où on a un seul bon segment*/
            return theIndiceOfTheBestSegment;
        } else {/*dans le cas où on a un plusieurs segments*/
            return GMaximisation(listOfSegments, listOfIndicesOfBestSegments);
        }
    }

    /**
     * methode appartenant à l'heuristique H2 pemettant de choisir un segment
     * parmi une liste de segments on choisissant celui qui maximise g
     *
     * @param listOfSegments
     * @param listOfIndicesOfBestSegments
     * @return
     */
    public static int GMaximisation(ArrayList<Segment> listOfSegments, ArrayList<Integer> listOfIndicesOfBestSegments) {
        int theIndiceOfTheBestSegment = listOfIndicesOfBestSegments.get(0);
        int theMaxNumOfIntersection = -9999999;
        int currentNumOfIntersection = 0;
        for (int i : listOfIndicesOfBestSegments) {
            for (int j = 0; j < listOfSegments.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (listOfSegments.get(j).evalute(listOfSegments.get(i)).equals(SideType.Intersect)) {
                    currentNumOfIntersection++;
                }
            }
            if (currentNumOfIntersection > theMaxNumOfIntersection) {
                theMaxNumOfIntersection = currentNumOfIntersection;
                theIndiceOfTheBestSegment = i;
            }
            currentNumOfIntersection = 0;
        }
        return theIndiceOfTheBestSegment;
    }

    static int getTheIndiceFrom10Percent(ArrayList<Segment> listOfSegments) {

        return 0;
    }

}
