/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.util.ArrayList;

/**
 * la Classe Consol permettants d'afficher les statistiques des différentes
 * heuristiques
 *
 * @author zakariae
 */
public class Consol {

    private FileParser fileToParser = new FileParser();
    private BspTree bspTree;
    private ArrayList<Segment> listOfSegments = new ArrayList<>();

    /**
     * le constructeur de la classe
     */
    public Consol() {
    }

    /**
     * créer des segments depuis un fichier texte
     *
     * @param file le fichier contenant les coordonnées des segments
     */
    public void parseFile(File file) {
        this.listOfSegments = fileToParser.parse(file);
    }

    /**
     * construction aléatoire de L'arbre BSP affichage de la heuteur , taille ,
     * le temps requis pour la contruire ainsi le temps requis pour exécuter
     * l'algorithme du peintre
     */
    private void runTheRandomHeur() {
        Node root = new Node();
        this.bspTree = new BspTree(root, Heuristique.random);
        long startTime = System.currentTimeMillis();
        this.bspTree.buildBinarySpacePartitionTree(root, new ArrayList<>(listOfSegments));
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        startTime = System.currentTimeMillis();
        this.bspTree.pantherAlgo(new Point(500, 500), root);
        endTime = System.currentTimeMillis();
        long painteralgoDuration = (endTime - startTime);
        System.out.println("the height is " + bspTree.getHeight());
        System.out.println("the size of the tree is " + bspTree.getSize());
        System.out.println("the time elapsed to build the BSP is :" + duration + " ms");
        System.out.println("the painter execution time  :" + painteralgoDuration + " ms");

    }

    /**
     * construction de L'arbre BSP en utilisant l'heuristique H2 affichage de la
     * heuteur , taille ,le temps requis pour la contruire ainsi le temps requis
     * pour exécuter l'algorithme du peintre
     */
    private void runTheH2Heur() {
        //    System.out.println("the time before :" + new Date().toString());
        //   System.out.println("the listOfSegmentsSize is " + listOfSegments.size());
        Node root = new Node();
        this.bspTree = new BspTree(root, Heuristique.H2);
        long startTime = System.currentTimeMillis();
        this.bspTree.buildBinarySpacePartitionTree(root, new ArrayList<>(listOfSegments));
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        startTime = System.currentTimeMillis();
        this.bspTree.pantherAlgo(new Point(500, 500), root);
        endTime = System.currentTimeMillis();
        long painteralgoDuration = (endTime - startTime);
        System.out.println("the height is " + bspTree.getHeight());
        System.out.println("the size of the tree is " + bspTree.getSize());
        System.out.println("the time elapsed to build the BSP is :" + duration + " ms");
        System.out.println("the painter execution time  :" + painteralgoDuration + " ms");

    }

    /**
     * construction de L'arbre BSP en utilisant la 3ème heuristique affichage de
     * la heuteur , taille ,le temps requis pour la contruire ainsi le temps
     * requis pour exécuter l'algorithme du peintre
     */
    private void runTheFirstSegmentHeur() {
        Node root = new Node();
        this.bspTree = new BspTree(root, Heuristique.thefirstsegemetofthelist);
        long startTime = System.currentTimeMillis();
        this.bspTree.buildBinarySpacePartitionTree(root, new ArrayList<>(listOfSegments));
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        startTime = System.currentTimeMillis();
        this.bspTree.pantherAlgo(new Point(500, 500), root);
        endTime = System.currentTimeMillis();
        long painteralgoDuration = (endTime - startTime);
        System.out.println("the height is "
                + bspTree.getHeight());
        System.out.println("the size of the tree is " + bspTree.getSize());
        System.out.println("the time elapsed to build the BSP is :" + duration + " ms");
        System.out.println("the painter execution time  :" + painteralgoDuration + " ms");

    }

    /**
     * exécuter les differntes heuristiques et afficher les statistiques pour
     * chaque heuristique
     *
     * @param file
     */
    public void run(File file) {
        parseFile(file);
        System.out.println("le nombre de segments est " + listOfSegments.size());
        System.out.println(" \n\n1-Random heuristique          ");
        runTheRandomHeur();
        System.out.println("\n\n 3-TheFirstSegmentOfTheListHeur        ");
        runTheFirstSegmentHeur();
        System.out.println("\n\n 2-H2      ");
        runTheH2Heur();
    }

    /**
     * tester toutes les scènes
     *
     * @param args
     */
    public static void main(String args[]) {
        System.out.println("Teser tout les fichiers");
        String f = "/home/zakariae/Desktop/Scenes/";
        String[] filePAthArray = {"ellipses/ellipsesSmall.txt", "ellipses/ellipsesMedium.txt", "ellipses/ellipsesLarge.txt", "rectangles/rectanglesSmall.txt", "rectangles/rectanglesMedium.txt", "rectangles/rectanglesLarge.txt",
            "random/randomSmall.txt", "random/randomMedium.txt", "random/randomLarge.txt"
        };
        //, "random/randomLarge.txt"
        String[] HugefilePAthArray = {"rectangles/rectanglesSmall.txt", "rectangles/rectanglesHuge.txt", "random/randomHuge.txt"};

        File file;
        for (String p : filePAthArray) {
            System.out.println(f + p);
            file = new File(f + p);
            Consol c = new Consol();
            c.run(file);
        }

    }
}
