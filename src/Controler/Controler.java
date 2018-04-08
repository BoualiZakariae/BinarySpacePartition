/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Heuristique;
import Model.Model;
import Model.Point;
import Model.Segment;
import View.View;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author zakariae
 *
 * la Classe Controler permet de lier la vue au model ainsi elle permet de
 * passer des messages au model
 */
public class Controler {

    private View vue;
    private Model model;

    /**
     * le constructeur de la classe Controler on enregistre le modele comme
     * ecouteur des evenements de la vue
     *
     * @param modele le model qui va ecouter les evenements parvenant declenché
     * ar la vue
     */
    public Controler(Model modele) {
        this.model = modele;
        vue = new View(this);
        addListenersToModel();
    }

    /**
     * la vue s'enregistre comme ecouteur du modele
     */
    private void addListenersToModel() {
        model.addListener(vue);
    }

    /**
     * demander l'affichage de la vue
     */
    public void aff() {

        System.out.println("hello from the view");
        vue.aff();
    }

    /**
     *
     * @return la listes des segments du fichier
     */
    public ArrayList<Segment> getListOfSegments() {
        return model.getListOfSegments();
    }

    /**
     * demander au model de créer une liste de segments depuis un fihier texte
     * puis utiliser cette liste pour créer l'arbre BSP
     *
     * @param file le fichier contenant les segments
     * @param heur le type d'heuristique
     */
    public void parseFile(File file, Heuristique heur) {
        model.updateTheListOfSegments(file, heur);
    }

    /**
     *
     * @return le modele de l'application
     */
    public Model getModel() {
        return model;
    }

    /**
     *
     * @param viewPoint le point de vue de la scène
     * @return la liste des segments triées du plus loin au plus prés par
     * rapport à un point de vue de la scène
     */
    public ArrayList<Segment> getListOfSortedSegments(Point viewPoint) {
        return model.getListOfSortedSegments(viewPoint);
    }

}
