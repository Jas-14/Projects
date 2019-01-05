#Fichier contenant les fonctions nécessaires à la création d'un choix dans
#un paragraphe

from tkinter import*
from .objets import*
from .objets.c_histoire import*
from .objets.paragraphe_tkinter import*
from .objets.choix_tkinter import*
from .objets.c_lien import *
from .fonctions_annexes import *
from .generator import *


def obj_choix(c_c,nom,contenu,p,hist):
    """Création effective du choix"""
    
    name=nom.get() #On récupère le nom du choix et son contenu
    content=contenu.get('1.0','end')
    
    choix=generator_c(hist) #On crée l'objet choix. Voir generator
    
    choix.complete_attribut(name,content) #Méthode de l'objet choix, pour
    choix.link_p(p)                       #complèter ses attributs
    
    p.ajout_c(choix) #Méthode de l'objet paragraphe

    c_c.destroy()
    

def info_choix(e,c_c,liste_p,hist):
    """Fonction pour renseigner les attributs du choix"""
    #On crée quelques stringvar pour avoir des contenus par défaut
    nom_valdef=StringVar()
    contenu_valdef=StringVar()
    contenu_valdef.set("  Mettre le contenu du choix, le texte qui permettra de décrire le choix")
    
    line=liste_p.curselection() #On récupère la ligne sélectionnée dans la listbox
    item=liste_p.get(line) #Ce qui permet de récupérer l'item de la liste
    p=get_p(item,hist) #Grâce au titre du paragraphe on récupère l'objet associé
    
    c_c.unbind("<Return>") #On unbind la touche entrée
    
    Label(c_c,text="Veuillez renseigner les différents champs afin de créer votre choix").grid(row=7,column=0)

    #Création d'un label frame pour les renseignements du choix
    
    choix_f=LabelFrame(c_c,text="Renseignements du choix")
    choix_f.grid(row=8,column=0)
    
    Label(choix_f,text="Titre du choix").grid(row=0,column=0)
    nom=Entry(choix_f, textvariable=nom_valdef,width="60") #Capter le nom du choix
    nom.grid(row=1,column=0)

    Label(choix_f,text="Contenu").grid(row=4,column=0)
    Label(choix_f,textvariable=contenu_valdef).grid(row=5,column=0)
    contenu=Text(choix_f,width="50") #Capter le contenu du choix
    contenu.grid(row=6,column=0)
    
    #Bouton valider pour envoyer les informations
    Button(c_c,text="Valider",command=lambda:obj_choix(c_c,nom,contenu,p,hist)).grid(row=10,column=0)
    

def create_choix(hist):
    """Première fonction qui se lance, pour capter le paragraphe où va être
    créé le choix"""
    
    c_c=Tk()
    p_I="" #On initialise une variable à la string vide pour la fonction
           #remplir_listbox_p
    c_c.title("Créer un choix")
    Label(c_c,text="Dans quel paragraphe faut-il créer ce nouveau choix?").grid(row=0,column=0)
    liste_p=Listbox(c_c) #Listbox contenant les paragraphes
    
    liste_p.grid(row=1,column=0)
    remplir_listbox_p("Fin",liste_p,hist,p_I) #Fonction de fonctions_annexes
    Label(c_c,text="Appuyez sur entrée dès que le paragraphe est sélectionné").grid(row=3,column=0)
    #On bind l'événement "lancer la prochaine étape" avec la touche entrée
    #afin que la fenêtre se complète au fur et à mesure
    c_c.bind("<Return>",lambda e: info_choix(e,c_c,liste_p,hist))


if __name__=="__main__":
    
    hist=Histoire()
    
    p1=Paragraphe()
    p2=Paragraphe()

    p1.statut="debut"
    p2.statut="fin"

    p1.titre="Je suis p1"
    p2.titre="Je suis p2"

    hist.ajout_p(p1)
    hist.ajout_p(p2)

    create_choix(hist)





    
    
