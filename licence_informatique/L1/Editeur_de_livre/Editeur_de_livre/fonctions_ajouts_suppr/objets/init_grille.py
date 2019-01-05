#Fichier pour les possibilité de coordonnées pour les paragraphes et
#gérer la présence/abscence de paragraphes à cet endroit


def créer_grille():
    """On génère la grille de coordonnées possibles,en représentant les
    coordonnées par des set"""
    grille=[]
    #On démarre avec la colonne, coords x, qui varie de 100 à 1200 en laissant des
    #espaces de 200
    for i in range(100,1200,200):
        #Puis les lignes, coords y, qui varie entre 20 et 900, en laissant des espaces
        #de 100
        for j in range(20,900,100):   
            grille.append((i,j))
    return grille

def créer_dico(grille):
    """A chaque set doit correspondre une entrée dans le dictionnaire, et sa valeur est
    1 (présence) ou 0 (absence). Par défaut :0"""
    dico={}
    for coords in grille:
        dico[coords]=0
    return dico
        

                
            



        

