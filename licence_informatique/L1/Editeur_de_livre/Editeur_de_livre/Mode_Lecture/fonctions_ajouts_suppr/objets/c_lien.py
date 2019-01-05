#Problème d'importation lié à l'encodage, réglé avec ces lignes
#!/usr/bin/env python
#-*- coding: utf-8 -*-

#Fichier définissant la classe lien
class Lien:
    """Objet de type lien
    para_I: Paragraphe dont est issue le lien
    para_O: Paragraphe vers lequel pointe le lien
    position: La position du lien sur le canvas
    Un lien se construit de la manière suivante:
               lien
    P_I(choix) ---> P_O
   """

    def __init__(self):
        self.para_I=""
        self.para_O=""
        self.link=""
        self.position=""

    def link_para(self,para_I,para_O,choice):
        """Complète les attributs du lien lié aux paragraphes"""
        if choice in para_I.choix: #Test sûrement inutile
            self.para_I=para_I
            self.para_O=para_O
            self.link=choice

    def verif(self):
        """Vérification de la validité des liens, si complet ou non
        (pas d'info si d'actualité ou non)"""
        if self.para_I!="" and self.para_O!="" and self.link!="":   #Pour l'instant, les seules conditions sont que
                                                                    #les trois éléments ne soient pas vides, mais il faudra préciser par la suite 
            return True
        else:
            return False
    
    def set_coords(self,p_I,p_O):
        """Permet de set les coords du lien avec les coordonnées des paragraphes"""
        self.position=[p_I.position[0],p_I.position[1],p_O.position[0],p_O.position[1]]
