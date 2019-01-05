#Fichier permettant la fonction de sauvegarde

from fonctions_ajouts_suppr.objets.c_histoire import*
#On a uniquement besoin de c_histoire
from Accessoires.verification import*
from tkinter.messagebox import*
from tkinter import*

def Save_hist(hist,fichier,nom,root):
    """Permet la sauvegarde effective du fichier"""
    #On enregistre le nom du fichier dans la variable stringvar définie avant
    nom=fichier.get()
    root.destroy()
    if nom=="": #Si c'est la string vide, on donne un nom par défaut
        nom="new" 
    hist.sauvegarde(nom) #Méthode d'objet histoire
    
    showinfo("Sauvegarde","Sauvegarde effectuée avec succès!")

def Ask_save(hist):
    """Permet de demander la sauvegarde du fichier"""

    if(Verif_global(hist)):
        #Save est un objet de tkinter.messagebox, permettant de faire apparaître
        #une fenêtre pour recevoir la réponse oui ou non, qui corresponde à des
        #booléens
    
        save=askyesno("Sauvegarder","Voulez vous sauvegarder?")
    
        if not save: #Si l'utilisateur ne veut pas sauvegarder, on sort de la fonction
            return
        else: #Sinon, le booléen vaut 1, l'utilisateur veut sauvegarder
            nom=StringVar()
            #On ouvre une fenêtre afin de demander à l'utilisateur le nom qu'il
            #veut donner à son fichier
            root=Tk()
            root.title("Sauvegarder")
            Label(root,text="Donnez un nom et une extension à votre fichier. Attention, si le nom existe déjà, le fichier sera écrasé").grid(row=0,column=0)
            fichier=Entry(root,textvariable=nom) #Nom du fichier capté ici
            fichier.grid(row=2,column=0)
            #Bouton pour envoyer le nom du fichier
            Validate=Button(root,text="Valider",command=lambda:Save_hist(hist,fichier,nom,root))
            Validate.grid(row=4,column=0)
            root.mainloop()



if __name__=="__main__":
    hist=Histoire()
    Ask_save(hist)
