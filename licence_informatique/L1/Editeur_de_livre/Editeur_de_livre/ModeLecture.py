#Fichier maître pour lancer le Mode lecture

from Mode_Lecture import *
from Mode_Lecture.c_lecteur import *
from Mode_Lecture.fin import *
from Mode_Lecture.debut import*
from Mode_Lecture.Lecture import*
from Accessoires.charger import *
from fonctions_ajouts_suppr.objets.c_histoire import*

def ModeLecture(): 
    if lecture.hist.paragraphes==[]:
        return
    else:
        p_debut(lecture.hist,lecture)
        normal=fin(lecture)
        if normal:
            root=Tk()
            again=askyesno("Encore une fois!","Voulez vous recommencer le jeu?")
            root.destroy()
            if again:
                lecture.reset()
                return ModeLecture()

hist=Histoire() #On initialise un objet histoire pour récupérer le livre
lecture=Lecteur(hist) #On initialise un objet Lecteur pour commencer le jeu
debut_jeu(lecture) #On lance la fonction de début de jeu

if lecture.commencer: #Si le joueur veut commencer, alors on continue le déroulement du programme
    Ask_name(lecture) #On fait charger le fichier histoire
    ModeLecture() #Lancement du mode_lecture

