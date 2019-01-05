#include<stdio.h>
#include<string.h>
#include<stdlib.h>

struct contact{
	char *nom;
	char *prenom;
	char *tel;
	};
	
int lecture_contacts(char* nom_fichier,struct contact tabc[1000]){
  	FILE* fichier;
  	char *champ, *l, ligne[100];
  	int i=0,a=0;
  	fichier=fopen(nom_fichier,"r");
  	while( fgets(ligne, sizeof ligne, fichier)!=NULL){
  		i=i+1;
  		l=strdup(ligne);
  		l[strlen(l)-1]=0;
  		while((champ=strsep(&l,":"))!=NULL){
  				a=a+1;
  				if(a==1){
  					tabc[i].nom=malloc(strlen(champ)*sizeof(char));
  					strcpy(tabc[i].nom,champ);
  				}
  				else if(a==2){
  					tabc[i].prenom=malloc(strlen(champ)*sizeof(char));
  					strcpy(tabc[i].prenom,champ);}
  				else{
  					tabc[i].tel=malloc(strlen(champ)*sizeof(char));
  					strcpy(tabc[i].tel,champ);}
  		}
  		a=0;	
  		}
  	return i;	
	}
	
void affiche_contact(struct contact c){
	printf("%s, %s, %s\n",c.nom, c.prenom, c.tel);}
	
void affiche_liste_contacts(struct contact l_c[1000], int n){
	int i;
	for(i=0;i<=n;i++){
		affiche_contact(l_c[i]);} 
	}

void saisie_nouveau_contact(struct contact l_c[1000],int k){
	char nom[100];
	char prenom[100];
	char tel[100];
	printf("Saisir nom,prénom et n°de téléphone:");
	scanf("%s",&nom); 
	scanf("%s",&prenom); 
	scanf("%s",&tel);
	strcpy(l_c[k].nom,nom);
	strcpy(l_c[k].prenom,prenom);
	strcpy(l_c[k].tel,tel);
	 
}

void ajout_contact_fichier(char* nom_fichier,struct contact c){
	FILE* fichier;
	char nom[100],prenom[100],tel[100];
	fichier=fopen(nom_fichier,"aw");
	fseek(fichier,0,SEEK_END);
	strcpy(nom,c.nom);
	strcpy(prenom,c.prenom);
	strcpy(tel,c.tel);
	fprintf(fichier,"\n%s:%s:%s",nom, prenom, tel);
	fclose(fichier);
	
}

int main(){
	struct contact tabc[1000];
	int n;
	printf("%d",lecture_contacts("contacts.txt",tabc));
	/*affiche_liste_contacts(tabc,n);
	saisie_nouveau_contact(tabc,n+1);
	n++;
	affiche_liste_contacts(tabc,n);
	ajout_contact_fichier("contacts.txt",tabc[n]);*/
}