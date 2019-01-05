#!/usr/bin/env python
#-*- coding: utf-8 -*-
from fonctions_ajouts_suppr import *
from tkinter.messagebox import*

def Verif_P(hist):
    cpt_g=0
    for i in hist.paragraphes: #parcours la liste de tout les paragraphes stockés dasn c_histoire.
        if (i.statut=="Début"):
            if i.titre=="" or i.contenu=="" or i.choix==[] or i.lien_I!=[] or i.lien_O==[]: #Vérifie si les paragraphes de début contiennent tous un titre, du texte, un ou plusieurs choix, aucun liens d'entrés et au moins un lien de sortie.
                return False
        elif (i.statut=="Fin"): #Vérifie si les paragraphes de fin contiennent bien un titre, du texte, un ou plusieurs liens d'entrés et aucun liens de sorti.
            if i.fin_g==True:
                cpt_g=cpt_g+1 #On incrémente une variable si on rencontre un paragraphe gagnant.
            if i.titre=="" or i.contenu=="" or i.lien_I==[] or i.lien_O!=[]:
                return False
        elif (i.statut=="Milieu"):
            if i.titre=="" or i.contenu=="" or i.choix==[] or i.lien_I==[] or i.lien_O==[]:
                return False
    if cpt_g==0: #Si aucun paragraphe gagant n'existe, retourne False
        return False
    return True

def Verif_L(hist): #parcours la liste de tout les liens dans c_histoire
    for j in hist.liens:
        if (j.para_I=="" and j.para_O=="" and j.link==""): #Vérifie si les liens font biens le pont entre deux paragraphes et qu'ils possèdent bien un nom
            return False
    return True

def Verif_C(hist): #parcours la liste de tout les choix dans c_histoire
    for k in hist.choix:
        if (k.contenu=="" and k.paragraphe=="" and k.link==""): #Vérifie si les choix possèdent bien un texte, sont liés à un paragraphe et à un lien
            return False
    return True

def Verif_global(hist):
    P=Verif_P(hist)
    L=Verif_L(hist)
    C=Verif_C(hist)
    if (P==False or L==False or C==False): #S'il y a le moindre problème, la vérification renvoie un message d'erreur
        showerror("Erreur","Il y a un problème dans votre histoire, allez vérifier")
        return 0
    else:
        showinfo("Validation","Votre histoire est complète")
        return 1
