from fonctions_ajouts_suppr.objets.c_histoire import*
from tkinter import*
from fonctions_ajouts_suppr import*
import random

ListeCouleur=["dodger blue","khaki","dark orchid","SlateBlue1","RoyalBlue1","SeaGreen2","goldenrod1","salmon1"] #Liste de couple d ecouleur afin d'agrémenter les fenêtres de lecture
ListeCouleur1=[]

def Parse_contenu(contenu):
    """Va insérer des \n là dans le contenu"""
    i=0 #Var qui va compter les caractères
    n=0 #Var qui va servir d'indice
    contenu=contenu.split(" ")
    while i<len(contenu): #Contenu est ici une liste, donc on boucle sur la longueur de la liste composée de mots
        somme=0 #Var pour compter les caractères
        n=i #On mets l'indice n à la valeur i, pour reprendre au mot suivant
        while somme<30: #On veut insérer un \n au bout d'une trentaine de caractères environ.
                        #Il est possible que l'insertion soit inégale, car on ne veut pas couper des mots, donc
                        #la somme pourra être supérieure à 30.
            try:
                somme=somme+len(contenu[n]) #On fait la somme des caractères de chaque mot et on incrémente n
                n+=1
            except: #Si n s'est trop incrémenté, on est "out of range" dans la liste. Dans ce cas, le traitement
                    #est terminé. On transforme contenu en string et on le retourne
                contenu=" ".join(contenu)
                return contenu

        contenu.insert(n,"\n") #Lorsque la somme est supérieure à 30, on insert un \n à l'indice n.
        i=n #La valeur de i doit être à la valeur de n, pour continuer le while.
        
    contenu=" ".join(contenu)   
    return contenu
        
            

def Affiche_histoire(paragraphe, lecture):
    master=Tk()
    master.resizable(width=False, height=False)
    p=PanedWindow(master, orient=HORIZONTAL, bg="grey")
    p.pack(side=TOP, expand=Y, fill=BOTH, padx=2, pady=2)
    master.geometry("800x594")
    master.title(paragraphe.titre)

    if len(ListeCouleur1)!=7 :  #On va séléctionner un couple de couleur aléatoire dans la liste pour l'appliquer au paragraphe en cours puis l'ajouter dans une nouvelle liste avant de supprimer ce couple de la liste actuelle.
        nbCouleur=len(ListeCouleur)
        Couleur=random.randint(1, nbCouleur)
        Couleur=Couleur-1
        CouleurBouton = ListeCouleur[Couleur]
        ListeCouleur1.append(ListeCouleur[Couleur])
        del ListeCouleur[Couleur]
    elif len(ListeCouleur)!=7 : #Une fois la première liste vide, on répète l'opération avec la seconde en échangeant le rôle des deux listes.
        nbCouleur=len(ListeCouleur1)
        Couleur=random.randint(1, nbCouleur)
        Couleur=Couleur-1
        CouleurBouton = ListeCouleur1[Couleur]
        ListeCouleur.append(ListeCouleur1[Couleur])
        del ListeCouleur1[Couleur]
        
    bg=PhotoImage(file="Mode_Lecture/livre.gif")
    Canv=Canvas(p,width=400,height=594)
    Canv.create_image(200,297,image=bg)
    p.add(Canv,minsize=400)
    fb=PanedWindow(p, orient=VERTICAL, bg="grey")
    p.add(fb, minsize=400)
    contenu=Parse_contenu(paragraphe.contenu)
    Canv.create_text(210,300,text=contenu,font="TeXGyreChorus 16")
    if (paragraphe.statut=="Fin"): #On vérifie le statut du pargraphe et si c'est un paragraphe de fin, on vérifie son booléen fin_g
        if (paragraphe.fin_g):
            fb.add(Button(fb, text='Génial !', bg=CouleurBouton, activebackground='chartreuse3', font="TeXGyreChorus 16", command=master.destroy))
        else:
            fb.add(Button(fb, text='Dommage', bg=CouleurBouton, activebackground='firebrick1', font="TeXGyreChorus 16", command=master.destroy))
    else:    
        global choice
        choice=StringVar()
        mini=594//len(paragraphe.choix)
        for i in paragraphe.choix:
            contenu=Parse_contenu(i.contenu)
            fb.add(Radiobutton(fb, text=contenu,variable=choice, value=i.nom,font="TeXGyreChorus 15", command=lambda:Selection(master, lecture, p, paragraphe), indicatoron=0, bg=CouleurBouton),minsize=mini) #i.contenu et i.nom vont dans l'objet choix pour charger les contenus
    master.mainloop()
    
def Selection(master, lecture, p, paragraphe):
    select=choice.get() #On récupère le nom du choix sélectionnné
    for k in paragraphe.choix: #Parcours la liste des choix dans c_histoire afin d'en ressortir le lien lié au choix sélectionné
        if (k.nom==select):
            lien=k.link
    paragraphe=lien.para_O
    lecture.ajout_p(paragraphe)
    lecture.ajout_l(lien)
    master.destroy()
    Affiche_histoire(paragraphe, lecture)


def p_debut(hist,lecture):
    P_debut=[]
    for j in hist.paragraphes: #Créer une liste avec le ou les paragraphes de début qui ont été créés
        if (j.statut=="Début"):
            P_debut.append(j)
    nbP_debut=len(P_debut)
    debut=random.randint(1, nbP_debut)
    debut=debut-1
    paragraphe=P_debut[debut] #Séléctionne aléatoirement un paragraphe dans la liste
    lecture.ajout_p(paragraphe)
    Affiche_histoire(paragraphe, lecture)


