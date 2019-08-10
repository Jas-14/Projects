package geneticPgrm.evolution;

import virtual.Warrior;

import java.util.List;

/**
 * Interface Evolution, qui indique comment nos warriors doivent
 * évoluer
 */
public interface Evolution {

    /**
     * Méthode mutWarriors, qui permet d'avoir une chance de
     * faire muter tout les warriors de la population
     * @param population La population sélectionnée
     * @param bound Un paramètre supplémentaire si il y a besoin
     * de générer une ligne aléatoire.
     */
    void mutWarriors(List<Warrior> population,int bound);
}
