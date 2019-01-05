#Problème d'importation liée à un problème d'encodage, réglé par ces lignes
#!/usr/bin/env python
#-*- coding: utf-8 -*-

#Fichier définissant la classe histoire

from pickle import * #Module permettant la sauvegarde

class Histoire:
    """Classe d'objet contenant la totalite d'une histoire:
    un attribut pour les paragraphes
    un attribut pour les liens
    un attribut pour les choix
    """
    def __init__(self):
        self.paragraphes=[]
        self.liens=[]
        self.choix=[]
        
    #Méthode d'ajout des différents objets
    def ajout_p(self,p):
        #Normalement ce test n'a pas à être faux, mais on ne sait jamais
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
        #La méthode with permet d'ouvrir un fichier et d'exécuter des actions dans le fichier. Si jamais une erreur interrompt le
        #programme, le fichier sera quand même fermé
        with open(nom,"wb") as fichier: 
            pick=Pickler(fichier) #Le module pickle a une classe d'objet nommé Pickler, qui est associé à la variable contenant le fichier
            pick.dump(self) #La méthode dump de cette objet permet d'enregistrer n'importe quelle information dans un fichier, ici
                            #on enregistre l'objet histoire directement
        
    def charger(self,nom):
        """Permet de charger un objet de type histoire contenu dans un fichier. Attention, l'objet doit être VIDE
    sinon l'objet garde ses anciennes caractéristiques. Mettre le résultat de la fonction dans une var, self ne marche pas
    Exemple: Si h doit recevoir le fichier chargé écrire:
    h=Histoire()
    h=h.charger()"""
        try:
            with open(nom,"rb") as fichier:
                unpick=Unpickler(fichier) #Le module pickle contient également une classe d'objet nommé Unpickler, qui est associé
                                          #à la variable contenant le fichier. Il permet, cette fois ci, de récupérer chaque objet
                histoire=unpick.load()    #enregistré dans un fichier. Ici, notre objet histoire
            return histoire
        except:
            pass
            
    def verif(self):
        """Vérif de la validité de chacun des éléments. Non complet, évalue juste si les attributs sont non vides (pas d'info si
        toujours d'actualité)"""
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

    #Fonction de mise à jour des différents attributs de l'objet, qui sont tous des listes
    def del_l(self,lien):
        """Permet de supprimer l'objet lien de la liste correspondante de l'objet"""
        self.liens.remove(lien)

    def del_p(self, paragraphe):
        """Permet de supprimer l'objet paragraphe de la liste correspondante de l'objet"""
        self.paragraphes.remove(paragraphe)
   
    def del_c(self,choix):
        """Permet de supprimer l'objet choix de la liste correspondante de l'objet"""
        self.choix.remove(choix)
