package geneticPgrm.fighting;

import main.CoreWarGame;
import virtual.Warrior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe Tournament, qui effectue un tournoi de Warriors
 */
public class Tournament implements Fighting {

    @Override
    public void fight(List<Warrior> warriors){

        //Listes des participants
        List<Warrior> contestants=new ArrayList<>(warriors);

        while (contestants.size()!=1){

            //Participants à l'étape courante
            List<Warrior> currents=new ArrayList<>(contestants);

            //Les gagnants de l'étape courante
            List<Warrior> winners=new ArrayList<>();



            while (!(currents.isEmpty())) {

                Warrior w1 = this.chooseRandomRival(currents);
                currents.remove(w1);

                Warrior w2;

                /*On cherche à retirer deux participants. Si ce
                n'est pas possible, alors on considère le premier
                warrior tiré comme gagnant
                 */
                if (currents.isEmpty()) {

                    winners.add(w1);

                } else {
                    w2 = this.chooseRandomRival(currents);
                    currents.remove(w2);

                    w1.increaseMatches();
                    w2.increaseMatches();

                    CoreWarGame game = new CoreWarGame(w1, w2);

                    //Combat
                    game.gameLoop();

                    //Récupération du gagnant
                    Warrior winner = game.winnner(false);

                    //Traitement en fonction du gagnant
                    if (winner.equals(w1)) {

                        w1.winMatch();
                        w2.looseMatch();

                    } else {

                        w2.winMatch();
                        w1.looseMatch();
                    }

                    //Le gagnant est ajouté à la liste des gagnants
                    winners.add(winner);

                }
            }

            //Les nouveaux participants sont les gagnants de l'étape
            contestants=winners;



        }

    }

    /**
     * Méthode chooseRandomRival, qui permet de choisir un
     * Warrior parmi les participants
     * @param contestants Les participants
     * @return Le Warrior choisi
     */
    private Warrior chooseRandomRival(List<Warrior> contestants){

        Random generator=new Random();

        return contestants.get(generator.nextInt(contestants.size()));


    }

}
