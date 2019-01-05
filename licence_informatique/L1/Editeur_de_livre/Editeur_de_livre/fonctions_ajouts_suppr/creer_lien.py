#Fichier pour créer un lien entre deux paragraphes, avec comme intermédiaire
#un choix

from tkinter import*
from tkinter.messagebox import*
from .objets import*
from .objets.c_histoire import*
from .objets.paragraphe_tkinter import*
from .objets.choix_tkinter import*
from .objets.c_lien import *
from .generator import *
from .fonctions_annexes import *

def dessiner_lien(appli,link):
    """Va dessiner le lien dans le canvas"""
    #On dessine simplement le trait à partir des coordonnées fournies dans l'attribut position de lien
    appli.canvas.create_line(link.position[0],link.position[1],link.position[2]-20,link.position[3]-15,arrow="last")

    
def Creation_lien(appli,p_I,p_O,choix,hist):
    """Va mettre à jour les attributs qui ont besoin d'être mis à jour"""
    link=generator_l(hist) #On crée le lien ici grâce au générateur dans le fichier generator
    link.link_para(p_I,p_O,choix) #On complète ses attributs grâce à une méthode d'objet de type lien
    link.set_coords(p_I,p_O) #On lui donne les coordonnées pour le trait qu'il va représenter sur le graphe
    #print(link)
    #print(link.para_O)

    #On mets à jour les attributs des autres objets reliés au lien
    
    p_I.link_O(link) #Méthode de paragraphe
    p_O.link_I(link) #idem
    choix.link_lien(link)#Méthode de choix
    
    
    dessiner_lien(appli,link)

def End_choix_l(event,appli,p_I,choix,liste_p_O,hist,cl):
    """Récupérer le p_O afin de lancer des fonctions de création de l'objet"""
    
    cl.unbind("<Return>") #On unbind la touche entrée pour la dernière fois
    
    line=liste_p_O.curselection() #On récupère le p_O comme précedemment 
    item=liste_p_O.get(line)
    
    p_O=get_p(item,hist)
    
    cl.destroy() #Fermeture de la fenêtre afin de commencer à traiter les objets
    
    Creation_lien(appli,p_I,p_O,choix,hist)
    


def Choix_p_O(event,appli,hist,p_I,liste_C,cl):
    """Dernière partie de demande lien, pour demander le p_O"""
    
    cl.unbind("<Return>") #On unbind la touche entrée
    
    line=liste_C.curselection() #On recommence les manips comme précedemment pour obtenir le choix
    item=liste_C.get(line)
    
    choix=get_c(item,p_I) #Fonction de fonctions_annexes
    
    Label(cl,text="Vers quel paragraphe faire le lien?").grid(row=13,column=0)
    
    liste_p_O=Listbox(cl) #Listbox des paragraphes à choisir
    liste_p_O.grid(row=15,column=0)
    
    remplir_listbox_p("Début",liste_p_O,hist,p_I) #Fonction de fonctions_annexes
    
    Label(cl,text="Appuyez sur entrée dès que le paragraphe est sélectionné")

    #Bind de la touche entrée pour pouvoir récupérer tout les objets
    cl.bind("<Return>",lambda e: End_choix_l(e,appli,p_I,choix,liste_p_O,hist,cl))
    

def Choix_c(event,appli,hist,p_I,liste_p_I,cl):
    """Suite de l'édition de lien. Permet de choisir un choix parmi ceux du p_I"""
    
    cl.unbind("<Return>") #Obligatoire, sinon l'événement va buguer car tout est bind avec la touche Entrée
    
    #On récupère la ligne sélectionné par le curseur au moment où la touche Entrée a été pressé
    line=liste_p_I.curselection() 
    item=liste_p_I.get(line) #Grâce au numéro de ligne, on peut récupérer l'objet associé dans la liste
    
    p_I=get_p(item,hist) #Fonction de fonctions_annexes

    if len(p_I.choix)==0: #Si il n'y a pas de choix dans le paragraphe, il est impossible de continuer
        showerror("Erreur","Il n'y a pas de choix dans votre paragraphe")
        cl.destroy()
        
    else:
        Label(cl,text="Sur quel choix doit commencer ce lien?").grid(row=8,column=0)
    
        liste_C=Listbox(cl) #Listbox des choix 
        liste_C.grid(row=10,column=0)
    
        remplir_listbox_c(p_I,liste_C,"lien") #Fonction de fonctions_annexes
    
        Label(cl,text="Appuyez sur entrée dès que le choix est sélectionné").grid(row=11,column=0)

        #Bind de la touche entrée pour la suite de la demande, pour le p_O
        cl.bind("<Return>",lambda e: Choix_p_O(e,appli,hist,p_I,liste_C,cl))
    


def demander_lien_principal(appli,hist,p_I):
    """Partie principale qui ouvrira la fenêtre d'édition de lien.
    Permet de choisir le p_I, initialisé à vide """
    cl=Tk()
    cl.title("Créer un lien")
    
    Label(cl,text="Sélectionner le premier paragraphe").grid(row=0,column=0)
    
    liste_p_I=Listbox(cl) #Listbox pour les p à choisir comme p_I
    liste_p_I.grid(row=2,column=0)
    
    remplir_listbox_p("Fin",liste_p_I,hist,p_I) #Fonction de fonctions_annexes
    
    Label(cl,text="Appuyez sur entrée dès que le p est sélectionné").grid(row=3,column=0)
    #Event qui va servir à poursuivre la demande avec le choix, nécessairement dans le p_I
    cl.bind("<Return>",lambda e: Choix_c(e,appli,hist,p_I,liste_p_I,cl)) 
    cl.mainloop()

def create_lien(appli,hist):
    """Fonction lancée lors de l'appel de la commande
    Les attributs à remplir dans l'ordre sont p_I,choix -> p_O"""
    p_I="" #Nécessaire d'initialiser, pour la fonction remplir_listbox_p
    
    demander_lien_principal(appli,hist,p_I)

    

if __name__=="__main__":
    root=Tk()
    appli=Appli(root)
    hist=c_histoire.Histoire()
    p1=Paragraphe()
    p2=Paragraphe()
    p2.statut="fin"
    ch=Choix()
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

    create_lien(appli,hist)
    root.mainloop()


    
