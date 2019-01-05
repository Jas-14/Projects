#!/usr/bin/env python
#-*- coding: utf-8 -*-


from pickle import *

class Histoire:
    """Classe d'objet contenant la totalite d'une histoire:
    les paragraphes
    les liens
    les choix
    """
    def __init__(self):
        self.paragraphes=[]
        self.liens=[]
        self.choix=[]
    def ajout_p(self,p):                    #Fonctions d'ajout des différents objets
        if p not in self.paragraphes:
            self.paragraphes.append(p)
    def ajout_l(self,l):
        if l not in self.liens:
            self.liens.append(l)
    def ajout_c(self,c):
        if c not in self.choix:
            self.choix.append(c)

    def sauvegarde(self,nom):
        """Permet de sauvegarder l'objet dans un fichier"""
        with open(nom,"wb") as fichier:     #Méthode permettant de fermer le fichier automatiquement. Pour le reste, voir module Pickle
            pick=Pickler(fichier)
            pick.dump(self)
        
    def charger(self,nom):
        """Permet de charger un objet de type histoire contenu dans un fichier. Attention, l'objet doit être VIDE
    sinon l'objet garde ses anciennes caractéristiques. Mettre le résultat de la fonction dans une var, self ne marche pas
    Exemple: Si h doit recevoir le fichier chargé écrire:
    h=Histoire()
    h=h.charger()"""
        with open(nom,"rb") as fichier:
            unpick=Unpickler(fichier)
            self=unpick.load()
        return self
            
    def verif(self):
        """Vérif de la validité de chacun des éléments. Non complet, évalue juste si les attributs sont non vides"""
        for p in self.paragraphes:
            if p.verif() !=True:            #Méthode à créer
                return "Error"
        for l in self.liens:
            if l.verif() !=True:
                return "Error"
        for c in self.choix:
            if c.verif() !=True:            #Méthode à créer 
                return "Error"
        #A l'avenir, il faudra créer des fonctions pour chaque erreur, afin d'indiquer à l'utilisateur l'erreur qu'il a commise

    def del_l(self,lien):
        """Permet de supprimer l'objet lien de la liste correspondante de c_histoire"""
        self.liens.remove(lien)

    def del_p(self, paragraphe):
        """Permet de supprimer l'objet paragraphe de la liste correspondante de c_histoire"""
        self.paragraphes.remove(paragraphe)
   
    def del_c(self,choix):
        """Permet de supprimer l'objet choix de la liste correspondante de c_histoire"""
        self.choix.remove(choix)
