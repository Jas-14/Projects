#Fichier pour supprimer les liens. Rappel, un lien est conçu de manière suivante:
#p_I vers p_O

from tkinter import *
from tkinter.messagebox import*

#Même commentaire que dans suppr_choix

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
    objet= sys.modules[__package__ + '.objets']
    
from .objets.c_histoire import*
from .objets.paragraphe_tkinter import*
from .objets.choix_tkinter import*
from .objets.c_lien import *
from .fonctions_annexes import *

def del_lien_canvas(lien,canvas):
    """Va permettre la suppression du trait dans le canvas"""
    #On ajoute un tag au segment afin qu'il soit identifiable. addtag_closest attribue un tag, représenté
    #par une chaîne de caractère à l'élément présent aux coordonnées fournies en argument. Si il n'y a
    #pas d'élément aux coordonnées données, la fonction cherche aux alentours, dans un ratio défini par
    #défaut. 
    canvas.addtag_closest("lien",lien.position[0],lien.position[1]-50)
    
    canvas.delete("lien") #On peut donc supprimer l'élément repéré par le tag
    
    showinfo("Confirmation","Lien supprimé avec succès") #Fenêtre de confirmation
    

def del_attribut_l(p,c,root,hist,canvas,direct,para):
    """On va supprimer le lien de tout les attributs qui le référence. Cette fonction est particulière de
    part son argument direct. C'est un booléen permettant de savoir si la fonction est apppelée de manière
    directe ou indirecte par l'utilisateur
    La manière directe, c'est quand l'utilisateur demande la suppression d'un lien particulier.
    La manière indirecte, c'est quand l'utilisateur demande, par exemple, la suppression d'un choix et
    que ce choix est lié à un lien. L'utilisateur va supprimer ce lien indirectement
    Para permet de savoir si le paragraphe passé en argument est le p_i ou le p_o du lien"""
    
    
    if direct: #Si la fonction est exécutée de manière directe, on récupère le choix
        root.unbind("<Return>")
        line=c.curselection()
        item=c.get(line)

        c=get_c(item,p) #Fonction de fonctions_annexes
        
    if c.link=="": #Il faut savoir si le choix est lié à un lien, sinon la fonctionnalité est inutile
        #L'erreur ne se produit qu'en cas de fonction exécutée directement, mais je préfère faire un
        #test
        if direct: 
            showerror("Erreur","Votre choix n'est lié à rien du tout!")
            root.destroy()
    else:
        #Récupérons le lien associé au choix
        
        lien=c.link

        #Commencons le traitement des objets

        hist.del_l(lien) #Méthode d'objet histoire
        if para=="p_I":
            p.del_link_O(lien) #Méthode d'objet paragraphe

            p_O=lien.para_O #Le p_O se retrouve grâce à l'attribut du lien

            p_O.del_link_I(lien) #Méthode d'objet paragraphe

        else:
            p.del_link_I(lien)
            p_I=lien.para_I
            p_I.del_link_O(lien)

        c.del_link() #On maj l'attribut lien de choix
        
        root.destroy()

        del_lien_canvas(lien,canvas) #Dernier point: suppression du canvas

def del_attribut_l_e(e,para,c,root,hist,canvas):
    """Fonction event servant de transition à une fonction "classique". Très important pour l'utiliser
    dans suppr_choix"""
    del_attribut_l(para,c,root,hist,canvas,1,"p_I")


def get_choice(e,p,root,hist,appli):
    """Le lien est lié à un choix, il faut donc le sélectionner"""
    
    root.unbind("<Return>") #On unbind l'event car on n'en aura plus besoin

    line=p.curselection() #On récupère la ligne sélectionnée
    item=p.get(line) #On récupère l'item

    para=get_p(item,hist) #Fonction de fonctions_annexes

    if len(para.choix)==0: #Si il n'y a pas de choix, il n'y a pas de lien à supprimer
       showerror("Erreur","Pas de choix dans votre paragraphe!")
       root.destroy()
    else:
        Label(root,text="Votre lien est lié à un choix particulier. Veuillez choisir le choix auquel il est lié").grid(row=6,column=0)

        c=Listbox(root) #Listbox des choix du paragraphe
        c.grid(row=7,column=0)
        remplir_listbox_c(para,c,"") #Fonction contenue dans fonctions_annexes
        
        Label(root,text="Appuyez sur entrée lorsque vous avez choisi le choix").grid(row=8,column=0)

        #On bind la touche entrée avec une nouvelle fonction, on passe le canvas en argument dès à présent
        root.bind("<Return>",lambda e: del_attribut_l_e(e,para,c,root,hist,appli.canvas))
        


def del_link(hist,appli):
    """"Fonction qui est appelée lors de la commande Choix du p dans lequel
    supprimer le lien"""
    p_I="" #On initialise p_I pour éviter un problème dans la fonction remplir_listbox
    root=Tk()
    root.title("Supprimer un lien")
    Label(root,text="Veuillez indiquer dans quel paragraphe il faut supprimer le lien").grid(row=0,column=0)

    p=Listbox(root) #Listbox des paragraphes
    p.grid(row=2,column=0)
    remplir_listbox_p("",p,hist,p_I) #Fonction de fonctions_annexes
    Label(root,text="Appuyez sur entrée dès que vous avez fait votre choix").grid(row=4,column=0)

    #On bind la touche entrée à une fonction permettant de choisir le choix associé au lien
    root.bind("<Return>", lambda e: get_choice(e,p,root,hist,appli))
    
    root.mainloop()





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
        
    del_link(hist)
