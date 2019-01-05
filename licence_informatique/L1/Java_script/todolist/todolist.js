"use strict";

/*La possibilité de drag and drop a été implanté, grâce à un tutorial de w3c
Malheureusement des bugs sont survenus, notamment car deux éléments li 
pouvaient fusionner. Les éléments li doivent peut être hériter de ol au 
niveau de l'attribut ondragover, ce qui permet aux li de recevoir eux
aussi des items.
J'ai donc mis en commentaire tout ce qui concernait 
le drag et drop*/

function Ajout_Item(event){
	
	/*Fonction pour ajouter un item à la liste, activé par le bouton
	submit*/
	
	event.preventDefault()
	
	let liste=document.querySelector("ol"); //On récupère la liste
	
	/*On récupère l'item en deux fois: d'abord on récupère le formulaire
	puis sa valeur*/
	
	let item=document.querySelector("form input"); 
	item=item.value;
	
	//On crée notre li, avec son textnode ayant pour valeur item
	let li=document.createElement("li");
	let texte=document.createTextNode(item+"   ");
	
	/*Grâce à trois fonctions différentes, qui pourraient être
	implantées en une seule, on crée les trois "boutons"
	supprimer,monter et descendre*/
	
	let suppr=bouton_Suppr();
	let monter=bouton_Monter();
	let descendre=bouton_Descendre();
	
	//Attributs spécifique au drag and drop
	
	/*li.setAttribute("draggable","true");
	li.setAttribute("ondragstart","drag(event)");
	let index=liste.children.length.toString();
	li.setAttribute("id",index);*/
	
	//On ajoute au li tout les élèments que l'on a créé
	li.appendChild(texte);
	li.appendChild(suppr);
	li.appendChild(monter);
	li.appendChild(descendre);
	
	//On ajoute le li à la liste
	liste.appendChild(li);
	
	
	//Fonction de mise à jour, voir ligne 231
	Maj(liste);
	

	}
	

function bouton_Suppr(){
	/*Fonction qui renvoie un élèment de type a qui fait office
	de bouton de suppression*/
	
	//On crée notre élèment a et son noeud texte
	let bouton=document.createElement("a");
	let texte=document.createTextNode("supprimer  ");
	
	//On set l'attribut href pour qu'il ait l'apparence d'un lien
	bouton.setAttribute("href","");
	
	//On ajoute le texte à l'élément
	bouton.appendChild(texte);
	
	//Enfin, on lui ajoute un eventlistener avant de le renvoyer
	bouton.addEventListener("click",Supprimer);
	
	return bouton;

}

function Supprimer(event) {
	
	/*Fonction s'activant si on clique sur un lien nommé
	supprimer. Permet de supprimer le li de la liste*/
	
	event.preventDefault();
	
	/*Pour récupérer le li, on va prendre le currentTarget,
	qui est le lien "supprimer" et on va chercher son parent,
	c'est à dire le li à supprimer*/
	
	let li=event.currentTarget.parentNode;
	
	//On récupère la liste
	let liste=document.querySelector("ol");
	
	//On enlève le li de la liste
	liste.removeChild(li);
	
	//Fonction de mise à jour, voir ligne 231
	Maj(liste);

}


function bouton_Monter(){
	
	/*Fonction qui renvoie un élèment de type a qui fait office
	de bouton pour monter un item de la liste*/
	
	
	//On crée notre élèment et son noeud texte
	let bouton=document.createElement("a");
	let texte=document.createTextNode("monter  ");
	
	//On set son attribut href pour qu'il ait l'apparence d'un lien
	bouton.setAttribute("href","");
	
	//On ajoute le noeud texte
	bouton.appendChild(texte);
	
	//On ajoute à l'élèment un eventlistener
	bouton.addEventListener("click",Monter);
	
	/*On ajoute une classe au bouton, la classe up qui servira pour
	la fonction de mise à jour*/
	
	bouton.classList.add("up");
	
	return bouton;	
	
}
	
function Monter(event){
	
	/*Fonction s'activant si on clique sur un lien nommé "monter"
	Va permettre de monter l'item dans la liste*/
	
	
	event.preventDefault();
	
	/*On récupère le li en récupèrant le parent du currentTarget
	qui est le lien "monter"*/
	let li=event.currentTarget.parentNode;
	
	//On récupère la liste
	let liste=document.querySelector("ol");
		
	/*On insère le li avant l'élément li.previousSibling.
	li.previousSibling est l'élément précédent li. Si il existe,
	c'est un autre élément li. Sinon, li.previousSibling vaut null,
	et l'élément ne bouge pas. 
	De toute façon, pour le premier élément de la liste, il n'y a
	pas de lien "monter"*/
	liste.insertBefore(li,li.previousSibling);

	/*Fonction de mise à jour, voir ligne 231*/
	Maj(liste);
		
}
	
function bouton_Descendre(){
	
	/*Fonction qui renvoie un élèment de type a qui fait office de
	bouton pour descendre un item de la liste*/
	
	//On crée notre élèment et son noeud texte
	let bouton=document.createElement("a");
	let texte=document.createTextNode("descendre  ");
	
	//On set son attribut href pour qu'il ait l'apparence d'un lien
	bouton.setAttribute("href","");
	
	//On rajoute le noeud texte
	bouton.appendChild(texte);
	
	//On ajoute un eventlistener
	bouton.addEventListener("click",Descendre);
	
	/*On ajoute une classe au bouton, la classe down qui servira pour
	la fonction de mise à jour*/
	
	bouton.classList.add("down");
	
	return bouton;	
	
}
	
	
function Descendre(event){
	
	/*Fonction s'activant si on clique sur un lien nommé "descendre"
	Va permettre de descendre l'item dans la liste*/
	
	
	event.preventDefault();
	/*On récupère le li en récupèrant le parent du currentTarget, qui
	est le lien descendre*/
	
	let li=event.currentTarget.parentNode;
	
	//On récupère la liste
	let liste=document.querySelector("ol");
	
	//On récupère l'élément juste en dessous du li grâce à li.nextSibling
	//li.nextSibling vaut null si le li est le dernier de la liste
	let li_after=li.nextSibling;
	
	/*Test désormais inutile car le bouton descendre n'existe pas pour un li
	qui est le dernier de la liste*/
	if(li_after!=null){
		
		/*On insère le li avant l'élément qui précède son précédent.
		 
		Schéma:
		
		1:li
		2:li_after
		->Insertion
		3:li_after.nextSibling
		*/	
			
		liste.insertBefore(li,li_after.nextSibling);
		
	}
	
	/*Fonction de mise à jour, voir ligne 231*/
		
	Maj(liste);
		
}
function Maj(liste){
	/*Fonction qui va mettre la liste à jour au moindre changement:
	création, montée, descente ou suppression d'un item*/
	
	/*Nous allons tout d'abord agir sur le premier et le dernier de la liste.
	Le premier ne peut pas monter et le dernier ne peut pas descendre, il 
	faut supprimer les liens relatifs à ces commandes*/
	
	//On récupère le premier et le dernier li de la liste, afin de les identifier
	let premier=liste.childNodes[0];
	let dernier=liste.childNodes[liste.childNodes.length-1];
	
	//On boucle sur les fils de liste, c'est à dire tout les li
	for (let i=0;i<liste.childNodes.length;i++) {
		
		/*Pour chaque li, on récupère ses fils élèments, c'est à dire tout les 
		liens présents*/
		let liens=liste.childNodes[i].children;
		
		/*A partir de cette ligne, les choses auraient pû être mieux gérées, 
		nottamment en réduisant le nombre de boucles for*/
		
		//Si on tombe sur le premier li
		if (liste.childNodes[i]==premier) {
			
			//On parcourt les fils élèments du li
			for(let p=0;p<liens.length;p++){ 
				
				/*Si jamais il y a un lien ayant comme classe "up", c'est
				un lien "monter", on doit l'enlever*/
				if(liens[p].classList.contains("up")){
					
					premier.removeChild(liens[p]);					
				}
			}
		}
		//Si on tombe sur le dernier li
		else if(liste.childNodes[i]==dernier){
			
			//On parcourt les fils élèments du li
			for(let p=0;p<liens.length;p++){
			
				/*Si jamais il y a un lien ayant comme class "down", c'est
				un lien "descendre", on doit l'enlever*/
				
				if(liens[p].classList.contains("down")){
				
					dernier.removeChild(liens[p]);				
				
				}				
								
			}				
		}
		/*Si le li n'est ni dernier, ni premier, on doit s'assurer qu'il à
		tout ses liens, en particulier "monter" et "descendre"*/
		
		else{
		
		/*Deux variables booléennes qui vont évaluer pour chaque li si un
		lien avec la class "up" ou "down" est présent*/
		
		let contient_u=0; 
		let contient_d=0;
		
		//On parcourt les liens du li
		for(let p=0;p<liens.length;p++){
			/*Si il y a un lien ayant comme classe "up", on incrémente la
			variable contient_u. Même fonctionnement avec "down" pour 
			contient_d*/
			
			if(liens[p].classList.contains("up")){
			
				contient_u++;
				
			}
			if(liens[p].classList.contains("down")){
			
				contient_d++;		
			
			}	
		
		}
		/*On évalue ensuite le contenu des variables booléennes.
		!(contient_u) est vrai si contient_u est faux, c'est à dire si
		le li ne possède pas de lien "monter". De même pour contient_d
		et le lien "descendre"*/
		
		if(!(contient_u)){
		/*Si il n'y a pas de lien "monter", on le crée à nouveau et on
		l'ajouter aux fils du li. De même pour le lien "descendre"*/
		
			let monter=bouton_Monter();
			
			liste.childNodes[i].appendChild(monter);			
						
		}
	  if(!(contient_d)){
	  
	  		let descendre=bouton_Descendre();
	  		
	  		liste.childNodes[i].appendChild(descendre);
	  
		}
						
					
		}			
								
	}
				
}

/*Fonctions utilisées pour le drag and drop, grâce au tutoriel 
de w3cschool*/

/*function drag(event){

	event.dataTransfer.setData("text",event.target.id);

}

function allowDrop(event){
		
	event.preventDefault();
	
}

function drop(event){

	event.preventDefault();
	let data=event.dataTransfer.getData("text");
	event.target.appendChild(document.getElementById(data));	
	
}*/
