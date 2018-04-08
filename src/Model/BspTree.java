/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zakariae
 *
 * La Classe BspTree represente la reference au premier noeud de l'arbre BSP
 *
 */
public class BspTree {

    private Node rootOfTeBSP;
    private Heuristique heuristiqueType;
    private ArrayList<Segment> listOfSortedSegments = new ArrayList<>();

    /**
     * Constructeur de la classe
     */
    public BspTree() {
    }

    /**
     * Constructeur de la classe
     *
     * @param root la racine de l'arbre BSP
     * @param heu le type d'heuristique pour créer la BSP
     */
    public BspTree(Node root, Heuristique heu) {
        this.heuristiqueType = heu;
        this.rootOfTeBSP = root;
    }

    /**
     * calculer la hauteur de L'arbre BSP
     *
     * @return cette hauteur
     */
    public int getHeight() {
        return 1 + this.rootOfTeBSP.getHeight();
    }

    /**
     * la methode récursif principale pour créer L'arbre BSP
     *
     * @param root un noeud
     * @param listOfSegments la liste des segments
     */
    public void buildBinarySpacePartitionTree(Node root, ArrayList<Segment> listOfSegments) {

        ArrayList<Segment> frontSegments = new ArrayList<>();
        ArrayList<Segment> backSegments = new ArrayList<>();
        Segment segment;
        int indice;/*indique l'indice du segment dans la liste*/
        switch (heuristiqueType) {
            case random:/*la construction aléatoire*/
                segment = getAndRemoveARandomSegment(listOfSegments);
                break;
            case thefirstsegemetofthelist:/*la construction on choisisant le premier segment de la liste*/
                segment = (Segment) listOfSegments.get(0);
                listOfSegments.remove(0);
                break;
            case myHeuristique:
                indice = Utilities.getTheIndiceFrom10Percent(listOfSegments);//on cherche le meilleur segment depuis 10% de la liste 
                segment = (Segment) listOfSegments.get(indice);
                listOfSegments.remove(indice);
                break;
            default:/*la construction en utilisant l'heuristique H2*/
                indice = Utilities.getTheIndiceOfTheBestSegment(listOfSegments);
                segment = (Segment) listOfSegments.get(indice);
                listOfSegments.remove(indice);
                break;
        }
        root.addNewSegmentToTheList(segment);/*on ajoute le segment choisie à la liste des segments dans ce nouveau noeud */
        Segment frontSegment, backSegement;
        //partitionnement
        for (Segment s : listOfSegments) {
            frontSegment = new Segment();
            backSegement = new Segment();
            switch (segment.evalute(s)) {
                case Collinear:
                    root.addNewSegmentToTheList(s);/*l'ajout à la liste des segments de ce noeud*/
                    break;
                case Front:
                    frontSegments.add(s);/*l'ajout à la liste de devant*/
                    break;
                case Back:
                    backSegments.add(s);/*l'ajout à la liste de derriére*/
                    break;
                default:/*le cas où il y'a une intersection*/
                    segment.spilt(s, frontSegment, backSegement);
                    frontSegments.add(frontSegment);
                    backSegments.add(backSegement);
                    break;
            }

        }
        if (!frontSegments.isEmpty()) {
            root.setFront(new Node());
            buildBinarySpacePartitionTree(root.getFront(), frontSegments);
        }

        if (!backSegments.isEmpty()) {
            root.setBack(new Node());
            buildBinarySpacePartitionTree(root.getBack(), backSegments);
        }

    }

    /**
     * retourner un segment aléatoire depuis une liste , puis le supprimer de
     * cette liste de segments
     *
     * @param listOfSegments la liste de segments
     */
    private Segment getAndRemoveARandomSegment(ArrayList listOfSegments) {
        int random = new Random().nextInt(listOfSegments.size());
        Segment RandomSegment = (Segment) listOfSegments.get(random);
        listOfSegments.remove(random);
        return RandomSegment;
    }

    /**
     *
     * @return la racine de l'arbre BSP
     */
    public Node getRoot() {
        return this.rootOfTeBSP;
    }

    /**
     * exécuter l'algorithme du peintre depuis un point de vue et inséerer les
     * segments parcouru par ordre dans une liste qui contiendra les segments
     * triées par rapport au point de vue
     *
     * @param viewpoint le point de vue
     * @param root un noeud de l'arbre BSP
     */
    public void pantherAlgo(Point viewpoint, Node root) {

        if (root == null) {
            return;
        }
        SideType sType = root.getListOfSegments().get(0).evalute(viewpoint);/*on evalue le point de vue par rapport au segment */
        if (sType != null) {
            switch (sType) {
                case Front:
                    pantherAlgo(viewpoint, root.getBack());
                    for (int i = 0; i < root.getListOfSegments().size(); i++) {
                        listOfSortedSegments.add(root.getListOfSegments().get(i));
                    }
                    pantherAlgo(viewpoint, root.getFront());
                    break;
                case Back:
                    pantherAlgo(viewpoint, root.getFront());
                    for (int i = 0; i < root.getListOfSegments().size(); i++) {
                        listOfSortedSegments.add(root.getListOfSegments().get(i));
                    }
                    pantherAlgo(viewpoint, root.getBack());
                    break;
                default:
                    /*le point de vue est collinéaire avec le segment*/
                    pantherAlgo(viewpoint, root.getFront());
                    pantherAlgo(viewpoint, root.getBack());
                    break;
            }
        }

    }

    /**
     *
     * @return la liste des segments auparavant triées
     */
    public ArrayList<Segment> getListOfSortedSegments() {
        return listOfSortedSegments;
    }

    /**
     * vider la liste des segments
     */
    public void clearTheListOfSortedSegments() {
        this.listOfSortedSegments.clear();
    }

    /**
     *
     * @return la taille de L'arbre BSP
     */
    public int getSize() {
        return rootOfTeBSP.getSize();
    }

}
