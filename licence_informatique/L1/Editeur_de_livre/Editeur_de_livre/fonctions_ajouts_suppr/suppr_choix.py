#Fichier pour supprimer les choix

from tkinter import*
from tkinter.messagebox import*

#On aura besoin de suppr_lien pour des raisons évidentes: si on supprime un
#choix, le lien associé doit être supprimé
from .suppr_lien import*

#Petit code d'importation tiré d'internet afin d'éviter les problèmes
#d'importation récursive. Le fichier paragraphe_tkinter.py a besoin de
#suppr_choix.py et inversement. Pour éviter un problème de réimportation, on
#utilise un bloc try. Si .objets ne peut pas être importé, cela veut dire qu'il
#est déjà dans les modules importés par python, donc dans sys.modules.
#sys.modules est un dictionnaire construit de la manière suivante:
#-la clé est le nom du module, une chaîne de caractère
#-la valeur est le fichier en lui même, qui est un objet
#Dans le cas où le module objet est déjà importé, il est dans ce dictionnaire.
#Donc il suffit de dire que le nom du module est égal à la valeur de
#sys.modules[__package__+".nommodule"]
#__package__ est une variable créé par python lors de l'importation, et doit
#contenir le nom du module ou package, si il a été importé. Ici,
# __package__ est le nom du dossier fonctions_ajoutsuppr. Il faut donc ajouter
#le second package objets pour former la clé du dictionnaire qui permettra de
#retrouver le module. Ainsi, le reste du code peut continuer à s'exécuter sans
#problème


try:
    from .objets import*
except:
    import sys
    objets= sys.modules[__package__ +'.objets']
    
from .objets.c_histoire import*
from .objets.paragraphe_tkinter import*
from .objets.choix_tkinter import*
from .objets.c_lien import *
from .fonctions_annexes import *

def del_attribut_c(para,c,root,hist,canvas,direct):
    """Nous allons travailler sur l'objet choix sélectionné. Cette fonction est particulière à cause de l'argument
    direct. C'est un booléen servant à évaluer si la fonction est appelée de manière directe ou indirecte.
    La manière directe, c'est lorsque l'utilisateur sélectionne la commande "Supprimer un choix". Il supprime un choix
    de façon directe.
    La manière indirecte, c'est lorsque l'utilisateur supprime un paragraphe. Les choix contenus dans ce paragraphes
    sont également supprimés par l'utilisateur, mais de manière indirecte."""
    
    if direct: #Si la fonction se lance de manière directe, il faut récupérer l'objet de la listbox
        line=c.curselection()
        item=c.get(line)

        c=get_c(item,para)

    #Commencons le traitement des objets

    if c.link!="": #Si il y a un lien qui est relié au choix, il faut le supprimer également, de manière indirecte
        del_attribut_l(para,c,root,hist,canvas,0,"p_I")
    elif direct:  #Si la fonction est lancée de manière directe, on ferme la fenêtre à ce moment là
        root.destroy()
        
    hist.del_c(c) #Méthode d'objet histoire

    para.del_c(c) #Méthode d'objet paragraphe

    showinfo("Confirmation","Suppression du choix effectuée") #Petite fenêtre de confirmation visuelle 

def del_attribut_c_e(e,para,c,root,hist,appli):
    """Passage de la fonction event à une fonction classique. Très important pour garantir la modularité entre
    fichier, notamment dans le cas où un paragraphe doit supprimer ses choix"""
    del_attribut_c(para,c,root,hist,appli.canvas,1) #On passe le canvas directement, pas la peine de garder l'appli



def get_Choice(e,p,root,hist,appli):
    """Choix du choix à supprimer. C'est une recopie de get_choice de suppr_lien, mais pour éviter toute confusion
    et avoir de l'organisation, je la recopie dans ce fichier."""
    
    root.unbind("<Return>") #On unbind l'event

    line=p.curselection() #On récupère la ligne sélectionnée
    item=p.get(line) #On récupère l'item

    para=get_p(item,hist)

    if len(para.choix)==0: #Si jamais le paragraphe n'a pas de choix, il n'y a rien à supprimer!
        showerror("Erreur","Pas de choix dans votre paragraphe!")
        root.destroy()
    else:
        Label(root,text="Sélectionnez le choix à supprimer").grid(row=6,column=0)
        
        c=Listbox(root) #Listbox contenant les choix
        c.grid(row=7,column=0)
        
        remplir_listbox_c(para,c,"") #Fonction de fonctions_annexes

        Label(root,text="Appuyez sur entrée lorsque vous avez choisi le choix").grid(row=8,column=0)

        #On bind la touche entrée à une nouvelle fonction
        root.bind("<Return>",lambda e:del_attribut_c_e(e,para,c,root,hist,appli))



def del_choice(hist,appli):
    """Fonction lancée lors de la commande dans le menu.
    Choix du paragraphe dans lequel supprimer le choix"""
    root=Tk()
    p_I="" #On initialise p_I pour éviter un problème dans la fonction remplir_listbox_p
    root.title("Supprimer un choix")
    
    Label(root,text="Veuillez indiquer dans quel paragraphe il faut supprimer le choix").grid(row=0,column=0)
    
    p=Listbox(root) #Listbox contenant les paragraphes
    p.grid(row=2,column=0)
    
    remplir_listbox_p("",p,hist,p_I) #Fonction de fonctions_annexes
    
    Label(root,text="Appuyez sur entrée dès que vous avez fait votre choix")
    #On bind la touche entrée avec une fonction qui permettra de continuer le traitement
    root.bind("<Return>", lambda e:get_Choice(e,p,root,hist,appli))
    





if __name__=="__main__":
    hist=Histoire()
    p1=Paragraphe()
    p2=Paragraphe()
    
    p2.statut="Fin"
    
    ch=Choix()
    ch2=Choix()
    ch.nom="test"
    ch2.nom="Je n'ai pas de lien"
    hist.ajout_c(ch)
    hist.ajout_c(ch2)
    
    p1.statut="Début"
    p1.choix.append(ch)
    p1.choix.append(ch2)
    ch.paragraphe=p1
    hist.ajout_p(p1)
    hist.ajout_p(p2)

    p1.position=[50,50]
    p2.position=[150,150]


    p1.titre="je suis p1"
    p2.titre="je suis p2"

    link=Lien()
    link.name="Lien"
    hist.ajout_l(link)

    p1.lien_O.append(link)
    p2.lien_I.append(link)

    ch.link=link

    link.para_I=p2
    link.para_O=p1
    link.link=ch
    link.position=[50,50,150,150]
