#Fichier pour charger une histoire

from fonctions_ajouts_suppr.objets.c_histoire import*
from tkinter import *
from tkinter.messagebox import*

def Charger_hist(root,nom,name_hist,lecteur):
    nom=name_hist.get()
    if nom=="":
        showerror("Erreur de fichier","Pas de nom")
        root.destroy()
    else:
        try:
            fichier=open(nom,"rb")
        except:
            showerror("Erreur de fichier","Fichier inexistant ou incompatible")
            root.destroy()
            return -1
            
        fichier.close()
        lecteur.hist=lecteur.hist.charger(nom)
        root.destroy()
            
        
    

def Ask_name(lecteur):
    root=Tk()
    root.title("Charger une histoire")
    Label(root,text="Entrez le nom de votre fichier histoire").grid(row=0,column=0)
    Label(root,text="Attention, veuillez à ce que le fichier soit dans le même dossier que le logiciel").grid(row=2,column=0)
    nom=StringVar()
    name_hist=Entry(root,textvariable=nom)
    name_hist.grid(row=4,column=0)
    Validate=Button(root,text="Valider",command=lambda:Charger_hist(root,nom,name_hist,lecteur))
    Validate.grid(row=6,column=0)
    root.mainloop()

if __name__=="__main__":
    hist=Histoire()
    Ask_name(hist)
    print(hist.paragraphes)
