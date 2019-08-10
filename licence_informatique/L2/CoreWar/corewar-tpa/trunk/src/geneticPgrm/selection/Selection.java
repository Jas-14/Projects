package geneticPgrm.selection;

import virtual.Warrior;

import java.util.List;

/**
 * Interface Selection, qui indique comment nos Warriors sont
 * sélectionnés
 */
public interface Selection {

    /**
     * Méthode doSelection, qui effectue la sélection au
     * sein de l'algorithme génétique
     * @param population La population de départ
     * @return La nouvelle population sélectionnée
     */
    List<Warrior> doSelection(List<Warrior> population);

    /**
     * Méthode getBest, qui permet d'obtenir le Warrior le
     * plus performant d'une population
     * @param population La population de départ
     * @return Le Warrior le plus performant
     */
    Warrior getBest(List<Warrior> population);
}
