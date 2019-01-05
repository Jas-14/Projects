#Problème d'importation lié à l'encodage, réglé avec ces lignes
#!/usr/bin/env python
#-*- coding: utf-8 -*-

class Choix:
    "Classe qui définit les choix possibles par paragraphe"
    "nom est le nom du choix"
    "contenu est le contenu du choix"
    "paragraphe est le paragraphe dans lequel est le choix"
    "link est le lien auquel il est lié"
    
    def __init__(self):
        self.nom = ""
        self.contenu = ""
        self.paragraphe=""
        self.link=""
        
    def complete_attribut(self,nom,contenu):
        """Permet de modifier les attributs contenu et nom"""
        self.nom=nom
        self.contenu=contenu
    def link_p(self,p):
        """Pour lier le choix à son paragraphe"""
        self.paragraphe=p
    def link_lien(self,lien):
         """Lier un choix à son lien"""
         self.link=lien

    def del_link(self):
        """Maj attribut lien"""
        self.link=""
