#Fichier pour enclencher le mode lecture (mini menu de démarrage)

from tkinter import*
from .c_lecteur import*

def Charger(lecture,root):
    """Lorsque l'on appuie sur le bouton charger, la fenêtre se ferme, mais on mets à jour la variable commencer de l'objet lecture"""
    lecture.commencer=True
    root.destroy()


def debut_jeu(lecture):
    """Fonction qui enclenche le début du jeu"""
    root=Tk() #On ouvre une fenêtre
    root.geometry("550x100")
    root.title("Mode Lecture du Livre dont vous êtes le héros")

    #Pour avoir une couleur de fond uniforme, on va tout mettre dans
    #des frames afin de set leur background

    #Frame principale, qui englobe les autres
    Frame_root=Frame(root,bg="DarkGoldenrod2")
    Frame_root.pack(fill=BOTH,expand=1)

    #Frame pour le texte
    general_Frame=Frame(Frame_root,padx=5,bg="DarkGoldenrod2")
    general_Frame.pack(fill=BOTH,expand=1)
    general_Frame.rowconfigure(0, weight=1)
    general_Frame.columnconfigure(0, weight=1) 
    Label(general_Frame,bg="DarkGoldenrod2",text="Bienvenue dans le Mode Lecture du Livre dont vous êtes le héros!\nEntrez dans une aventure formidable écrite par vous même ou par quelqu'un!").grid(row=0,column=2)
    Label(general_Frame,bg="DarkGoldenrod2",text="Pour commencer l'aventure, appuyez sur le bouton Charger, afin de charger le livre").grid(row=2,column=2)

    #Frame des boutons
    Bouton_f=Frame(Frame_root,bg="DarkGoldenrod2")
    Bouton_f.pack(fill=X,expand=1,padx=150)

    #Le bouton qui permet de charger l'histoire, donc de commencer le jeu
    charger=Button(Bouton_f,bg="dark goldenrod",text="Charger",relief="raised",borderwidth=5,activebackground="green2",width=10,command=lambda: Charger(lecture,root))
    charger.pack(side=LEFT)
    quitter=Button(Bouton_f,bg="dark goldenrod",text="Quitter",relief="raised",borderwidth=5,activebackground="red3",width=10,command=root.destroy)
    quitter.pack(side=LEFT)
    root.mainloop()
