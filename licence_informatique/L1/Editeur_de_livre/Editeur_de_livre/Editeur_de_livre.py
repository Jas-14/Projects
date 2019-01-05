#Fichier maitre pour générer le logiciel

from tkinter import*
import Accessoires.c_fenetre
from fonctions_ajouts_suppr import *
from fonctions_ajouts_suppr.objets import *



def Logiciel():
    """Fonction pour raccourcir le programme principal le plus possible.
    Peut également servir à ouvrir une autre instance de l'éditeur de livre"""
    hist=c_histoire.Histoire()
    root=Tk()
    appli=Accessoires.c_fenetre.Appli(root,hist)


Logiciel()
