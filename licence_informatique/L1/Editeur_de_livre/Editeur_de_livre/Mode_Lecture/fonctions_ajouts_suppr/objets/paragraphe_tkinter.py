#!/usr/bin/env python
#-*- coding: utf-8 -*-


from tkinter import *
from .init_grille import*
from .c_histoire import*
from ..suppr_choix import *



grille=créer_grille()
dico=créer_dico(grille)

class Paragraphe:
    "Classe de création de paragraphe:"
    "-statuts : type de paragraphe --> début, milieu, fin"
    "-text : contenu du paragraphe"
    "-choix : liste qui contient les choix possibles pour les paragraphes de début et milieu"
    "-lien_I : liste qui contient les liens entrant dans le paragraphe"
    "-lien_O : liste qui contient les liens sortant du paragraphe"
    "-titre : nom du paragraphe"
    "fin_g: Vaut true si c'est une bonne fin, false si c'est une mauvaise fin, rien sinon"
    "Non implanté"
    "position: position du paragraphe dans le canvas"
    
    def __init__(self):      
        self.statut=""
        self.fin_g=""
        self.contenu=""
        self.choix=[]
        self.lien_I=[] 
        self.lien_O =[]
        self.titre=""
        self.position=""


    def EcrirePara(self, c, hist):
        pot=Tk()
        pot.title('Création de paragraphe')
        
        Label(pot, text='Veuillez entrer un titre : ').pack() #Permet d'entrer le titre du paragraphe
        titre=StringVar()
        title=Entry(pot, textvariable=titre, width=50)
        title.pack()
        
        Label(pot, text='Veuillez entrer un texte : ').pack() #Permet d'entrer un texte pour le paragraphe
        content=Text(pot, height= 10, width=50)
        content.pack()
        
        Label(pot, text='Veuillez sélectionner le statut du paragraphe : ').pack() #Permet d'obtenir le statut du paragraphe
        statut=Listbox(pot, selectmode=SINGLE) #SINGLE empêche la sélection de plusieurs statut
        statut.insert(0, 'Début')
        statut.insert(1, 'Milieu')
        statut.insert(2, 'Fin Gagnante')
        statut.insert(3, 'Fin Perdante')
        statut.select_set(1) #Si rien n'est choisi, le paragraphe sera un paragraphe de milieu forcément
        statut.pack()
            
        Button(pot, text='Annuler', command=pot.destroy).pack(side=LEFT)
        Button(pot, text='Valider', command=lambda:self.CreerPara(pot, title, content, statut, c, hist)).pack(side=RIGHT)
        pot.mainloop()

    def CreerPara(self, pot, title, content, statut, c, hist):
        self.titre=title.get()
        self.contenu=content.get(1.0, 'end')
        ligne=statut.curselection()
        if (ligne==()): #Si le statut est déselectionné dans la Listbox, le statut sera automatiquement Milieu
            self.statut='Milieu'
        else:
            self.statut=statut.get(ligne)

        self.coords_pour_p(dico)

        if (self.position == -1):
            error=Tk()
            error.title('Erreur')
            Label(error, text='Vous ne pouvez pas créer plus de paragraphe du type sélectionné').pack()
            del (self)
            Button(error, text='OK', command=error.destroy).pack()
        else :
            hist.ajout_p(self)
            if (self.statut=="Fin Gagnante"):
                self.fin_g=True
                self.statut='Fin'
                b = Button(c, text=self.titre, bg='green', command=lambda:self.Affiche(b, hist,c))
            elif(self.statut=="Fin Perdante"):
                self.fin_g=False
                self.statut='Fin'
                b = Button(c, text=self.titre, bg='red', command=lambda:self.Affiche(b, hist,c))
            else:
                b = Button(c, text=self.titre, command=lambda:self.Affiche(b, hist,c))
            b_w=c.create_window(self.position[0], self.position[1], window=b)
        pot.destroy()

    def Affiche(self, b, hist,canvas):
        paragraphe=Tk()
        paragraphe.title(self.titre)
        texte=Text(paragraphe, height= 10, width=50)
        texte.pack()
        texte.insert(END, self.contenu)
        
        Button(paragraphe, text='Sauvegarder', command=lambda:self.Sauvegarder(texte, paragraphe)).pack(side=LEFT) #Sauvegarde les modifictaion de texte dans le Entry
        Button(paragraphe, text='Modifier titre', command=lambda:self.ModifierTitre(paragraphe, b)).pack(side=LEFT)
        Button(paragraphe, text='Afficher Choix', command=self.AfficherChoix).pack(side=LEFT)
        Button(paragraphe, text='Fermer', command=paragraphe.destroy).pack(side=LEFT)
        Button(paragraphe, text='Supprimer', command=lambda:self.Supprimer(b, paragraphe, hist,canvas)).pack(side=LEFT) #Dans cette fonction, il faudra utiliser les coordonnées du bouton b afin de le supprimer ainsi que son contenu
        paragraphe.mainloop()

    def Sauvegarder(self, texte, paragraphe):
        self.contenu=texte.get(1.0, 'end')
        Label(paragraphe, text='Sauvegarde réussie', fg='green').pack(side=BOTTOM)

    def ModifierTitre(self, paragraphe, b):
        nouveautitre=Tk()
        Label(nouveautitre, text='Veuillez entrer un nouveau titre : ').pack()
        titre=StringVar(nouveautitre, value=self.titre)
        title=Entry(nouveautitre, text=titre, width=50)
        title.pack()
        
        Button(nouveautitre, text='Annuler', command=nouveautitre.destroy).pack(side=LEFT)
        Button(nouveautitre, text='Valider', command=lambda:self.ValiderTitre(nouveautitre, title,b )).pack(side=RIGHT)
        paragraphe.destroy()
        nouveautitre.mainloop()

    def ValiderTitre(self, nouveautitre, title, b):
        self.titre=title.get()
        b['text']=self.titre #Change le textvariable du bouton par le nouveau titre
        nouveautitre.destroy()
        self.Affiche(b)

    def AfficherChoix(self):
        choice=Tk()
        choice.title('Choix')
        for i in self.choix:
            Label(choice, text='Nom : ').pack()
            Label(choice, text=i.nom).pack()
            Label(choice, text='Contenu : ').pack()
            Label(choice, text=i.contenu).pack()
        Button(choice, text='Fermer', command=choice.destroy).pack()

    def Supprimer(self, b, paragraphe, hist,canvas):
        self.suppression_coords(dico)
        hist.del_p(self)
        for choix in self.choix:
            del_attribut_c(self,choix,b,hist,canvas,0)
            
        del (self)
        b.destroy()
        paragraphe.destroy()

    def __del__(self):
        return

    def coords_pour_p(self,dico):
        """Permet de trouver des coords disponibles et logiques pour un p"""
        for coords,presence in dico.items():
            #Si c'est un paragraphe de début, on veut placer uniquement sur la première ligne,
            #donc y=20
            if self.statut=="Début" and coords[1]==20 and presence==0:
                self.position=coords 
                dico[coords]=1
                return
            #On place les paragraphes de milieu entre la deuxième et la septième ligne
            elif self.statut=="Milieu" and 120<=coords[1]<=620 and presence==0: 
                self.position=coords
                dico[coords]=1
                return
            #On laisse les deux dernières lignes pour les paragraphes de fin
            elif self.statut=="Fin Gagnante" and 720<=coords[1]<=820 and presence==0:
                self.position=coords
                dico[coords]=1
                return
            elif self.statut=="Fin Perdante" and 720<=coords[1]<=820 and presence==0:
                self.position=coords
                dico[coords]=1
                return
            else:
                #Code d'erreur, si il n'y a plus de place dans le canvas
                self.position=-1

    def suppression_coords(self,dico):
        """Si un paragraphe est supprimé, les coordonnées doivent redevenir libres"""
        for coords, presence in dico.items(): #On cherche les coordonnées dans le dico
            if self.position==coords:
                dico[coords]=0  #On maj le dictionnaire et l'attribut de paragraphe
                self.position=""

    #Méthodes de traitement de l'objet, pour ajouter des liens, des choix et les supprimer
    #Rappel pour les liens, du point de vue des paragraphes:
    #           lien
    #P_I(choix) ---> P_O
    #   |            |
    #   |            |    
    #   v            v   
    #Le lien est   Le lien est
    #dans l_O      dans l_I
    
    def link_I(self,lien_I):
        """Permet de lier un lien entrant à un p"""
        self.lien_I.append(lien_I)

    def link_O(self,lien_O):
        """Permet de lier un lien sortant à un p. Deux méthodes différentes pour ne
        pas confondre"""
        self.lien_O.append(lien_O)

    def ajout_c(self,choix):
       """Ajoute un choix au p"""
       self.choix.append(choix)
       
    def del_link_I(self,lien_I):
        """Maj de l'attribut lien_I"""
        self.lien_I.remove(lien_I)

    def del_link_O(self,lien_O):
        """Maj de l'attribut lien_O"""
        self.lien_O.remove(lien_O)
        
    def del_c(self,choix):
        """Maj de l'attribut choix"""
        self.choix.remove(choix)

def debut(c):
    para=Paragraphe()
    para.EcrirePara(c)

if __name__=='__main__':
    top=Tk()
    c=Canvas(top,width=1200,height=900, bg="white")
    Button(top,text="Créer",command=lambda:debut(c)).pack(side=BOTTOM)
    c.pack()
    top.mainloop()

    





