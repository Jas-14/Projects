class Choix:
    "Classe qui définit les choix possibles par paragraphe"
    "nom est le nom du choix"
    "contenu est le contenu du choix"
    "paragraphe est le paragraphe dans lequel est le choix"
    "link est le lien auquel il est lié"
    
    def __init__(self, nom, contenu,paragraphe):
        self.nom = nom
        self.contenu = contenu
        self.paragraphe=""
        self.link=""

    def supprimer_contenu(self):
        print("Voulez-vous vraiment supprimmer ? Oui Non")
        rep=input()
        if(rep.lowercase()==oui):
            self.contenu="" #va remplacer le contenu actuel par un contenu vide
        else:
            return

    def modifier_contenu(self):
        self.contenu=input("Ecrivez le contenu du choix")
    
    
    def link_choice(self,lien):
        """Permet de lier lien et choix"""
        if isinstance(lien,Lien):
            if self.link !="":
                a=input("Warning, not empty\nContinue? y/n:  ")
                if a=="n":
                   print("Stop")
                else:
                    self.link=lien
            else:
                self.link=lien
        else:
            print("Error, the object is not a link")

    def verif(self):
        """Vérification de la validité des choix"""
        if self.contenu!="" and self.paragraphe!="" and self.link!="":
            return True
        else:
            return False
    
