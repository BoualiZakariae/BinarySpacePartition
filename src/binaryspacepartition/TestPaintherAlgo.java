/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryspacepartition;

import Controler.Controler;
import Model.Model;

/**
 * Classe pour lancer l'application permettant d'afficher les scènes,choisir le
 * point de vue et appliquer l'algorithme du peintre.
 *
 * @author zakariae
 */
public class TestPaintherAlgo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model mod = new Model();
        Controler controleur = new Controler(mod);
        controleur.aff();
    }

}
