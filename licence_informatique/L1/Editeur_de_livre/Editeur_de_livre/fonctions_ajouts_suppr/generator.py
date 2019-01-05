#Fichier permettant de générer les objets et de les ajouter directement à
#l'objet de type histoire associé

from .objets import *
from .objets.paragraphe_tkinter import*


def generator_c(hist):
    """Generator des choix"""
    #On crée le choix, on l'ajoute à l'objet de type histoire et on le retourne
    choix=choix_tkinter.Choix() 
    hist.ajout_c(choix) #Méthode d'objet de type histoire
    return choix

def generator_l(hist):
    """Generator des liens"""
    #On crée le lien, on l'ajoute à l'objet de type histoire et on le retourne
    lien=c_lien.Lien()
    hist.ajout_l(lien) #Méthode d'objet de type histoire
    return lien

def generator_p(hist):
    paragraphe=paragraphe_tkinter.Paragraphe()
    return paragraphe
