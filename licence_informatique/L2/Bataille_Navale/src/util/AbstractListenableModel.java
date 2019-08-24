package util;

import java.util.ArrayList;
/**
 * classe qui gere les ecouteurs
 */
public abstract class AbstractListenableModel {
/**
 * une liste qui stocke les ecouteurs
 */
    private ArrayList<ModelListener> listeners;
/**
 * Constructeur
 */
    public AbstractListenableModel() {
        this.listeners = new ArrayList<>();
    }
/**
 * methode qui permet d'ajouter un ecouteur dans la liste et de faire une mise a jour
 * @param l écouteur à ajouter
 */
    public void addListener(ModelListener l) {
        listeners.add(l);
        l.modelUpdated(this);

    }
/**
 * methode qui permet de supprimer un ecouteur de la liste
 * @param l écouteur à supprimer
 */
    public void removeListener(ModelListener l) {
        listeners.remove(l);
    }
/**
 * methode qui met a jour la liste des ecouteurs lorsque le model change
 */
    protected void modelChange() {

        for (ModelListener l : listeners)
        {
            l.modelUpdated(this);
        }
    }
}