/*<
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.EventListener;

/**
 * les ecouteurs du modele seront notifiés de la mise à jour de la liste des
 * segments via cette interface
 *
 * @author zakariae
 */
public interface ModelListener extends EventListener {

    /**
     *
     * @param theNewListOfSegments
     */
    public void updateListOfSegments(ArrayList<Segment> theNewListOfSegments);

}
