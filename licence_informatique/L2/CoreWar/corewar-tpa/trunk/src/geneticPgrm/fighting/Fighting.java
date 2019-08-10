package geneticPgrm.fighting;

import virtual.Warrior;

import java.util.List;

/**
 * Interface Fighting, qui définit comment les combats doivent
 * se dérouler entre warriors
 */
public interface Fighting {

    /**
     * Méthode fight, qui exécute les combats
     * @param population Les warriors participants aux combats
     */
    void fight(List<Warrior> population);


}
