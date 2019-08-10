package geneticPgrm.evolution;

import virtual.Warrior;

import java.util.List;

/**
 * Interface Crossing, qui indique comment les programmes doivent
 * se croiser.
 */
public interface Crossing {

    /**
     * Méthode cross pour croiser un ensemble de Warriors
     * @param warriorsToCross La liste des Warriors à croiser ensemble
     * @return Un nouveau Warrior issu des warriors sélectionnés
     */
    Warrior cross(List<Warrior> warriorsToCross);

    /**
     * Méthode crossAll, qui va croiser toute la population de
     * Warriors et ainsi créer plusieurs nouveaux warriors
     * @param population La population à croiser
     * @return La nouvelle population, avec les nouveaux Warriors
     */
    List<Warrior> crossAll(List<Warrior> population);
}
