/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;

/**
 *
 * @author zakariae
 *
 * La classe Colors contenant les couleurs utilisées dans les scènes
 */
public enum Colors {

    /**
     *
     */
    Bleu(Color.BLUE, "Bleu"),
    /**
     *
     */
    Rouge(Color.RED, "Rouge"),
    /**
     *
     */
    Orange(Color.ORANGE, "Orange"),
    /**
     *
     */
    Jaune(Color.YELLOW, "Jaune"),
    /**
     *
     */
    Noir(Color.BLACK, "Noir"),
    /**
     *
     */
    Violet(Color.MAGENTA, "Violet"),
    /**
     *
     */
    Marron(new Color(128, 0, 0), "Marron"),
    /**
     *
     */
    Vert(Color.GREEN, "Vert"),
    /**
     *
     */
    Gris(Color.GRAY, "Gris"),
    /**
     *
     */
    Rose(Color.PINK, "Rose");

    private final Color awtColor;
    private final String colorName;

    private Colors(Color awtColor, String name) {
        this.awtColor = awtColor;
        this.colorName = name;
    }

    /**
     *
     * @return
     */
    public Color getAwtColor() {
        return awtColor;
    }

    /**
     *
     * @return
     */
    public String getColorName() {
        return colorName;
    }
}
