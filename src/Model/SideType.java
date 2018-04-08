/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * l'Enumeration SideType définit les cotés possible que peut prendre un segment
 * , un point par rapport à une droite
 *
 * @author zakariae
 */
public enum SideType {

    /**
     *
     */
    Back,
    /**
     *
     */
    Front,
    /**
     *
     */
    Collinear,
    /**
     *
     */
    OnLineSegment,
    /**
     *
     */
    Intersect;

}
