/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryspacepartition;

import Model.Consol;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * Classe pour lancer le programme console permettant d'afficher les
 * statistiques des hes differentes heuristiques
 *
 * @author zakariae
 */
public class TestHeurStat {

    private static JFileChooser fc;

    /**
     * au lancement du programme , l'utilisateur est invité à choisir un fichier
     * afin d'appliquer les differentes heuistiques
     *
     * @param args
     */
    public static void main(String[] args) {
        fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println("the path " + file.getPath());
            Consol consol = new Consol();
            consol.run(file);

        }
    }

}
