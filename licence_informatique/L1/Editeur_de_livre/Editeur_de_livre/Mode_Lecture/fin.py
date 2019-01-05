#Fichier qui contient tout les éléments nécessaires à la fin du jeu, en particulier
#le dessin du graphe

from tkinter import*
from tkinter.messagebox import *

def dessin_graphe(canvas,fenetre,lecture):
    """Fonction qui permet de redessiner le graphe"""
    
    for p in lecture.hist.paragraphes: #On boucle sur tout les paragraphes de l'histoire
        if p in lecture.p_lus: #Si il a été lu, il est dans la liste p_lus, il possède une couleur de fond particulière
                b=Button(fenetre,text=p.titre,bg="DarkGoldenrod2",activebackground="DarkGoldenrod2")
                if p.statut=="Fin" and p.fin_g:
                    #Si c'est une fin, alors on peut préciser grâce à sa couleur si c'est une bonne ou une mauvaise fin
                    #mais que pour la fin qui est lue!
                    #Le vert est la couleur de la fin gagnante, le rouge pour une fin perdante
                    b.config(activebackground="chartreuse3")
                elif p.statut=="Fin":
                    b.config(activebackground="firebrick1")
                
        else:
            #Sinon, on représente le paragraphe par un bouton simple
            b=Button(fenetre,text=p.titre)
        #On place le bouton sur le canvas   
        canvas.create_window(p.position[0],p.position[1],window=b)
        
    for lien in lecture.hist.liens: #On boucle sur les liens de l'histoire
        if lien in lecture.liens_parcourus:
            #Si il a été parcourus, il est dans l'attribut liens_parcourus, on le représente en vert
            canvas.create_line(lien.position[0],lien.position[1],lien.position[2]-20,lien.position[3]-15,arrow="last",fill="green")
        else:
            #Sinon, c'est une flèche noire
            canvas.create_line(lien.position[0],lien.position[1],lien.position[2]-20,lien.position[3]-15,arrow="last")
        
    return canvas


def fin(lecture):
    """Fonction qui se déclenche à la fin du mode lecture"""
    #On ouvre la fenêtre
    fin=False
    for p in lecture.p_lus:
        if p.statut=="Fin":
            fin=True
    if fin:
        root=Tk()
        root.title("Chemin parcouru")
        #Canvas où va se retrouver le nouveau graphe
        C=Canvas(root,bg="white",width=1200,height=900)
        #Le canvas est immédiatement remplacé par celui qui est dessiné avec la fonction dessin_graphe
        C=dessin_graphe(C,root,lecture)
        C.pack()
        i=len(lecture.liens_parcourus) #Variable qui compte le nombre d'étapes qu'à fait le lecteur pour arriver à la fin
        showinfo("Fin du jeu","Vous avez réalisé l'histoire en "+str(i)+" étapes!")
        root.mainloop()
        return 1
    else:
        return 0

