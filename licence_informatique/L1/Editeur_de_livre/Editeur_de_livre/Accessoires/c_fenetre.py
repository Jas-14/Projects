#!/usr/bin/env python
#-*- coding: utf-8 -*-

#Classe pour générer la fenêtre principale et ses différents composants

from tkinter import *
from fonctions_ajouts_suppr import *
from Accessoires.sauvegarde import*
from Accessoires.verification import*

class Appli():
    """Classe maître de la fenêtre qui se crée dès le lancement du logiciel"""
    def __init__(self,master,hist):
        self.hist=hist #On garde la mention de l'objet histoire
        self.master=master #Ainsi que le master
        
        self.master.resizable(width=False,height=False)
        self.master.title("Editeur de livre")
        
        self.canvas=Canv(master) #On crée et place le canvas et le menu, qui
        self.menubar=Menub(master,self,hist) #sont deux classes distinctes
        self.canvas.pack()


class Canv(Canvas):
    """Classe du canvas, initialisée à partir de la classe canvas de tkinter"""
    def __init__(self,master):
        Canvas.__init__(self,master,width=1200,height=900,bg="white")
        
        
class Menub(Menu):
    """Classe du menu, où sont précisées toutes les commandes en méthode de
    cette classe"""
    def __init__(self,master,appli,hist):
        Menu.__init__(self,master)
        #On initialise le menu comme le menu de tkinter

        #On garde les références au master, objet histoire et l'appli
        self.master=master 
        self.hist=hist
        self.appli=appli

        #Première barre de menu, pour tout ce qui est en rapport avec l'objet histoire

        #On créé la première barre de menu, comme un objet de type menu de Tkinter
        menu1=Menu(self,tearoff=0)

        #On lui ajoute les différentes commandes
        
        menu1.add_command(label="Sauvegarder histoire",command=self.save)
        #Commande pour sauvegarder le fichier
        menu1.add_command(label="Vérification", command=self.verif)
        #Commande pour vérifier que le graphe est complet
        menu1.add_separator()
        menu1.add_command(label="Quitter",command=master.destroy)
        #Quitter le logiciel

        #On ajoute le sous menu à la menubar en tant que menu cascade
        self.add_cascade(label="Fichier",menu=menu1)

        #Deuxième barre de menu, pour tout ce qui est création
        
        menu2=Menu(self,tearoff=0)
        menu2.add_command(label="Créer un paragraphe", command=self.creer_paragraphe)
        #Commande pour créer un paragraphe
        menu2.add_command(label="Créer un choix",command=self.creer_choix)
        #Commande pour créer un choix
        menu2.add_command(label="Editer des liens",command=self.creer_lien)
        #Commande pour créer un lien
        
        
        self.add_cascade(label="Création",menu=menu2)

        #Troisième barre de menu, pour tou tce qui est suppression
        #La suppression des paragraphes est disponible en cliquant dessus

        menu3=Menu(self,tearoff=0)
        menu3.add_command(label="Supprimer lien",command=self.suppr_lien)
        #Commande pour supprimer un lien
        menu3.add_command(label="Supprimer choix",command=self.suppr_choix)
        #Commande pour supprimer un choix
        
        self.add_cascade(label="Suppression",menu=menu3)

        #Quatrième barre de menu, plutôt de l'aide. Pas eu le temps de le développer
        
        menu4=Menu(self,tearoff=0)
        menu4.add_command(label="A propos")
        menu4.add_command(label="Manuel")
        self.add_cascade(label="Aide",menu=menu4)

        master.config(menu=self) #Placement du menu dans la fenêtre

    def creer_lien(self):
        """Commande qui va lancer la fonction pour créer un lien, voir fichier correspondant"""
        creer_lien.create_lien(self.appli,self.hist)
        
    def creer_choix(self):
        """Commande qui va lancer la fonction pour créer un choix, voir fichier correspondant"""
        creer_choix.create_choix(self.hist)

    def creer_paragraphe(self):
        paragraphe=generator.generator_p(self.hist)
        paragraphe.EcrirePara(self.appli.canvas, self.hist)
        
    def suppr_lien(self):
        """Commande qui va lancer la fonction pour supprimer un lien, voir fichier correspondant"""
        suppr_lien.del_link(self.hist,self.appli)

    def suppr_choix(self):
        """Commande qui va lancer la fonction pour supprimer un choix, voir fichier correspondant"""
        suppr_choix.del_choice(self.hist,self.appli)

    def save(self):
        """Commande qui va lancer la fonction pour sauvegarder l'objet de type histoire, voir fichier correspondant"""
        Ask_save(self.hist)

    def verif(self):
        Verif_global(self.hist)
        
        
        
            
if __name__=="__main__":
    root=Tk()
    hist=c_histoire.Histoire()
    appli=Appli(root,hist)
    w=Button(root,text="fghf")
    appli.canvas.create_window(1000,100,window=w)

    p1=paragraphe_tkinter.Paragraphe()
    p2=paragraphe_tkinter.Paragraphe()
    p2.statut="fin"
    ch=choix_tkinter.Choix()
    ch.nom="test"
    hist.ajout_c(ch)
    p1.statut="debut"
    p1.choix.append(ch)
    hist.ajout_p(p1)
    hist.ajout_p(p2)

    p1.position=[50,50]
    p2.position=[150,150]

    p1.titre="je suis p1"
    p2.titre="je suis p2"

    link=c_lien.Lien()
    root.mainloop()






