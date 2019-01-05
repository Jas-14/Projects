class Lecteur:
    """Classe qui sert à enregistrer la lecture du livre.
    Elle représente la progression du joueur.
    Attributs:
    hist: pour sauvegarder l'objet histoire chargé
    p_lus: liste des paragraphes qui sont lus par le joueur
    liens_parcourus: liste des liens parcourus par le joueur
    commencer: booléen qui sert à savoir si le joueur commence ou non le jeu"""
    def __init__(self,hist):
        self.hist=hist
        self.p_lus=[]
        self.liens_parcourus=[]
        self.commencer=False

    def ajout_p(self,p):
        """Méthode pour ajouter un paragraphe lu à la liste"""
        self.p_lus.append(p)

    def ajout_l(self,lien):
        """Méthode pour ajouter un lien parcourus à la liste"""
        self.liens_parcourus.append(lien)
    def reset(self):
        """Reset tout les attributs"""
        self.p_lus=[]
        self.liens_parcourus=[]
