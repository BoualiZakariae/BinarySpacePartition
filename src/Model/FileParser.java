/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * classe permettant de lire un fichier et créer une liste de segments
 */
public class FileParser {

    private final ArrayList<Segment> listOfSegments;/*la liste des segmens qui va être retournée*/
    private final Model model;
    /*un boolean pour indiquer si on est obligé de dimnuer les coordonnées,dans mon implementation je divise les cooedoonnées sur deux*/
    private boolean devideTheCoordinate;

    /**
     * Constructeur de la classe
     *
     * @param mod
     */
    public FileParser(Model mod) {
        this.model = mod;
        listOfSegments = new ArrayList<>();
    }

    /**
     * Constructeur de la classe
     */
    public FileParser() {
        this.listOfSegments = new ArrayList<>();
        this.model = null;
    }

    /**
     * la methode pour lire le fichier de la scène et construire une liste de
     * segments
     *
     * @param file le fichier
     * @return la liste des segments de la scène
     */
    public ArrayList<Segment> parse(File file) {
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
            String theFirstString = null;
            double MaxX = 0;
            double MaxY = 0;
            theFirstString = stringTokenizer.nextElement().toString();
            MaxX = Double.parseDouble(stringTokenizer.nextElement().toString());
            MaxY = Double.parseDouble(stringTokenizer.nextElement().toString());
            //redimensionnement des segments
            if (MaxX > 700 || MaxY > 400) {
                this.devideTheCoordinate = true;
            }
            Double x1, y1, x2, y2;
            String colorStr;
            Colors color;
            double tx1, ty1, tx2, ty2;
            Segment segment;
            Point p1;
            Point p2;
            while ((line = br.readLine()) != null) {
                stringTokenizer = new StringTokenizer(line, " ");
                while (stringTokenizer.hasMoreElements()) {
                    x1 = Double.parseDouble(stringTokenizer.nextElement().toString());
                    y1 = Double.parseDouble(stringTokenizer.nextElement().toString());
                    x2 = Double.parseDouble(stringTokenizer.nextElement().toString());
                    y2 = Double.parseDouble(stringTokenizer.nextElement().toString());
                    colorStr = stringTokenizer.nextElement().toString();
                    color = getColor(colorStr);
                    //
                    if (devideTheCoordinate == true) {
                        tx1 = translateXToSwingCoordinate(x1 / 2);
                        ty1 = translateYToSwingCoordinate(y1 / 2);

                        tx2 = translateXToSwingCoordinate(x2 / 2);
                        ty2 = translateYToSwingCoordinate(y2 / 2);

                    } else {
                        tx1 = translateXToSwingCoordinate(x1);
                        ty1 = translateYToSwingCoordinate(y1);

                        tx2 = translateXToSwingCoordinate(x2);
                        ty2 = translateYToSwingCoordinate(y2);
                    }
                    p1 = new Point(tx1, ty1);
                    p2 = new Point(tx2, ty2);
                    //pas de segments lorsqu' on a un le même point 
                    if (p1.equals(p2)) {
                        break;
                    }
                    segment = new Segment(p1, p2, color);
                    listOfSegments.add(segment);
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

            } catch (IOException ex) {
            }

        }

        return listOfSegments;
    }

    /**
     * grâce â cette methode on peut deplacer le segment au centre de la fenêtre
     *
     * @param x
     * @return
     */
    public double translateXToSwingCoordinate(double x) {
        return x + 700;//ok

    }

    //deplacement du segment au centre de la fenetre
    /**
     *
     * @param y
     * @return
     */
    public double translateYToSwingCoordinate(double y) {
        return ((y * -1)) + 400;

    }

    private Colors getColor(String toString) {
        Colors color = null;
        switch (toString) {
            case "Bleu":
                color = Colors.Bleu;
                break;
            case "Rouge":
                color = Colors.Rouge;
                break;
            case "Orange":
                color = Colors.Orange;
                break;
            case "Jaune":
                color = Colors.Jaune;
                break;
            case "Noir":
                color = Colors.Noir;
                break;
            case "Violet":
                color = Colors.Violet;
                break;
            case "Marron":
                color = Colors.Marron;
                break;
            case "Vert":
                color = Colors.Vert;
                break;
            case "Gris":
                color = Colors.Gris;
                break;
            case "Rose":
                color = Colors.Rose;
                break;

        }
        return color;
    }

}
