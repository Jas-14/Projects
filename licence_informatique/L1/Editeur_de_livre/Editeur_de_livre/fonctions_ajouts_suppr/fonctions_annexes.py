#Fichier contenant toutes les fonctions utiles
#à presque tout les fichiers de modifications du graphe

def remplir_listbox_p(no_statut,listebox,hist,p_I):
    """Permet de remplir une listbox avec des paragraphes, selon des options
    bien précises:
    -l'argument "no_statut" représente le statut à éliminer: si un paragraphe
    possède ce statut, il ne sera pas dans la listbox.
    -l'argument p_I permet d'exclure un paragraphe, dans le cas où l'on ne
    peut pas sélectionner deux fois le même paragraphe. Il ne sera pas inclu
    dans la listbox"""
    
    i=0
    for p in hist.paragraphes:
    #On parcourt le rangement des paragraphes de l'objet histoire pour avoir
    #tout les paragraphes
        if p.statut!=no_statut and p!=p_I: #Le test en fonction des arguments
            i+=1
            listebox.insert(i,p.titre)

def remplir_listbox_c(p,listebox,mot_cle):
    """Fonction permettant de remplir une listbox avec les choix d'un paragraphe
    selon une option:
    -si le mot_cle vaut "lien", on doit exclure tout les choix qui sont déjà
    liés à un lien"""
    
    i=0
    if mot_cle=="lien": #On fait le test du mot clé
        #Malheureusement je n'ai pas trouvé le moyen de faire cette fonction
        #en une seule boucle, sinon soit le test n'avait aucun effet (le choix
        #sera ajouté dans tout les cas) ou alors le choix n'était pas encore
        #référencé au moment du test
        
        for choix in p.choix:
            #Ici on fait le test du link (si le choix est lié ou non)
            if choix.link=="":  
                i+=1
                listebox.insert(i,choix.nom)
    else: #Sinon on se contente de parcourir la liste dans faire de sélection
        for choix in p.choix:
            i+=1
            listebox.insert(i,choix.nom)
          
def get_p(titre,hist):
    """Trouver un paragraphe avec son titre"""
    #On cherche le paragraphe dans l'objet de type histoire et on le renvoie
    for p in hist.paragraphes: 
        if p.titre==titre:
            return p

def get_c(nom,para):
    """Trouver le choix avec son nom. Même principe que pour les
    paragraphes"""
    for c in para.choix:
        if c.nom==nom:
            return c
