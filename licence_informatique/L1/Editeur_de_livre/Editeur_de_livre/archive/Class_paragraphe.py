statut=["debut","fin","millieu"] #Var globale des statuts 

class Paragraphe:
    "Classe de création de paragraphe:"
        "-statuts : type de paragraphe --> début, milieu, fin"
        "-text : contenu du paragraphe"
        "-choix : liste qui contient les choix possibles pour les paragraphes de début et milieu"
        "-lien_I : liste qui contient les liens entrant dans le paragraphe"
        "-lien_O : liste qui contient les liens sortant du paragraphe"
        "-titre : nom du paragraphe"
        "fin_g: Vaut true si c'est une bonne fin, false si c'est une mauvaise fin, rien sinon"
        "position: position du paragraphe dans la fenêtre"
    
    def __init__(self):      
        self.statut=""
        self.fin_g=""
        self.text=""
        self.choix=[]
        self.lien_I=[] 
        self.lien_O=[]
        self.titre=""
        self.position=[0,0]

    def EcrirePara(self):
        if (self.text != ""):   #Condition non nécessaire vu qu'il y a une méthode édition. Je dirai même, méthode pas nécessaire
            print("Erreur : Il y a déjà un paragraphe de tapé ici.")
        else:
            print("Écrire un paragraphe :")
            self.text=input()

    def Statut(self):
        if (self.statut != ""):
            print("Erreur : Le statut est déjà défini.")
            return
        while(True):    #Pour une boucle infinie, même si c'est pas top
            print("Quel est le statut du paragraphe ? Début Fin Milieu")
            p=input()
            if (p=="Début" or p=="début"):
                self.statut="Début"
                return
            elif (p=="Fin" or p=="fin"):
                self.statut="Fin"
                return
            elif (p=="Milieu" or p=="milieu"):
                self.statut="Milieu"
                return
            else:
                print("La saisie n'est pas valide")
            print("Souhaitez-vous réessayer ? y/n =")
            a=input()

    def Titre(self):
        if (self.titre != ""):
            print("Un titre est déjà saisie pour votre paragraphe")
        else:
            print("Saisir un titre :")
            self.titre=input()

    def Edition(self):
        while(True):        #idem
            print("Que souhaitez-vous éditer ? Paragraphe Titre Statut")
            a=input()
            if (a.lowercase()=="paragraphe"): #Je crois que c'est la fonction, à vérif
                print("Votre paragraphe actuel est : ", self.text)
                self.text=input() #Va remplacer le paragraphe actuel
                return
            elif (a.lowercase()=="titre"):
                print("Votre titre actuel est : ", self.titre)
                self.titre=input() #Va remplacer le titre actuel
                return
            elif (a.lowercase()=="statut"):
                print("Le statut actuel est : ", self.statut)
                a=input()       #Pour éviter de mettre n'importe quoi dans le statut
                if a is not in statut:
                    print("Error, not a statut")
                else:
                    self.statut=a
                return
            else:
                print("La saisie n'est pas valide")
            print("Souhaitez-vous réessayer ? y/n =")
            b=input()

    def Supprimer(self):
        print("Voulez-vous vraiment supprimmer ? Oui Non")
        rep=input()
        if(rep.lowercase()=="oui"):
            self.statut=""
            self.fin_g=""
            self.text=""
            self.choix=[]
            self.lien_I=[]
            self.lien_O=[]
            self.position=[0,0]
            self.titre="" #La fonction va remettre à zéro tout les paramètres
        else:
            return

    def Ajout_choix(self):
        if (self.statut=="fin"):
            print("Vous ne spouvez pas ajouter de choix à un paragraphe de fin")
        else:
            choice=Choix() #penser à modifier le code
            choice.creer_choix() #fonction à créer sur le fichier choice
        
    def verif(self):
        """Vérif des paragraphes en fonction de leur statut"""
        if (self.statut=="debut"):
            if self.text=="" or self.choix==[] or self.lien_I!=[] or self.liens_O==[]: 
                return False
        else if (self.statut=="fin"):
            if self.text=="" or self.choix==[] or self.lien_I==[] or self.liens_O!=[]:
                return False
        else:
            if self.text=="" or self.choix==[] or self.lien_I==[] or self.liens_O==[]:
                return False
        return True

    def position(self):
        """Donnera la position en fonction de son statut. A travailler"""
            
