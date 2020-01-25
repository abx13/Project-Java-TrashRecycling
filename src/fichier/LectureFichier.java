package fichier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dechet.Dechet;
import dechet.TriDechet;
import poubelle.Poubelle;
import poubelle.PoubelleAutre;
import poubelle.PoubellePapier;
import poubelle.PoubelleVerre;
import usine.Usine;
import vehicule.Camion;
import vehicule.Camionette;
import vehicule.Moteur;
import vehicule.Roue;
import vehicule.VehiculeException;



public class LectureFichier {
	private static int cpt=0;
	private File file;
	private ArrayList<Poubelle> listePoubelleVerre;
	private ArrayList<Poubelle> listePoubellePapier;
	private ArrayList<Poubelle> listePoubelleAutre;
	private ArrayList<Camion> listeCamion;
	private ArrayList<Camionette> listeCamionette;
	private ArrayList<Dechet> listeDechet;
	private Usine usine;
	
	
	public LectureFichier(File file) {
		
		this.file = file;
		listePoubelleVerre = new ArrayList<Poubelle>();
		listePoubellePapier = new ArrayList<Poubelle>();
		listePoubelleAutre = new ArrayList<Poubelle>();
		listeDechet= new ArrayList<Dechet>();
		listeCamion= new ArrayList<Camion>();
		listeCamionette= new ArrayList<Camionette>();
		
		cpt=0;
	}
	
	public void lire() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		int nbPoubelle=0, 
				nbPoubellePapier=0, 
				nbPoubelleVerre=0, 
				nbPoubelleAutre=0, 
				taillePoubelle=0,
				nbCamions=0, 
				tailleCamion=0,
				nbCamionettes=0, 
				tailleCamionette=0;
		while ((line=reader.readLine()) != null) {
			cpt++;
			
			//nbPoubelle
			if (cpt==1) {
				nbPoubelle = Integer.parseInt(line);
				nbPoubellePapier = nbPoubelle/3;
				nbPoubelleVerre = nbPoubelle/3;
				if (nbPoubelle%3==0) {
					nbPoubelleAutre = nbPoubelle/3;
				}else {
					nbPoubelleAutre = nbPoubelle%3;
				}
				
			}else {
				//taille poubelle
				if (cpt==2) {
					taillePoubelle = Integer.parseInt(line);
				} else {
					//nb camions
					if (cpt==3) {
						nbCamions = Integer.parseInt(line);
					} else {
						//taille camion
						if (cpt==4) {
							tailleCamion = Integer.parseInt(line);
						} else {
							//nb camionettes
							if (cpt==5) {
								nbCamionettes = Integer.parseInt(line);
							} else {
								//taille camionettes
								if (cpt==6) {
									tailleCamionette = Integer.parseInt(line);
								}else {
									//creation dechet
									listeDechet.add(new Dechet(line));
								}
							}
						}
					}
				}
			}
		}
			
		//creation et instantiation des differentes classes
		
		//creation Poubelle
		for (int i=0; i<nbPoubellePapier; i++) {
			listePoubellePapier.add(new PoubellePapier(taillePoubelle));
		}
		for (int i=0; i<nbPoubelleVerre; i++) {
			listePoubelleVerre.add(new PoubelleVerre(taillePoubelle));
		}
		for (int i=0; i<nbPoubelleAutre; i++) {
			listePoubelleAutre.add(new PoubelleAutre(taillePoubelle));
		}
		
		//creation camions
		for (int i=0; i<nbCamions; i++) {
			listeCamion.add(new Camion(tailleCamion));
		}
		
		//creation camionettes
		for (int i=0; i<nbCamionettes; i++) {
			listeCamionette.add(new Camionette(tailleCamionette));
		}
		usine=new Usine();
			
			
		
		System.out.println("Fichier lu");
		reader.close();
		
	}
	
	public String executer() {
		TriDechet tri = new TriDechet(listeDechet);
		tri.tri();
		String s="Votre fichier de sauvegarde s'appelle : Fichier_Sauvegarde\n";
		ArrayList<Dechet> listeVerre= new ArrayList<Dechet>();
		ArrayList<Dechet> listePapier =new ArrayList<Dechet>();
		ArrayList<Dechet> listeAutre = new ArrayList<Dechet>();
		listeVerre= tri.getListeVerre();
		listePapier= tri.getListePapier();
		listeAutre= tri.getListeAutre();
		
		//ajout des déchets crées dans les poubelles adéquates + affichage nombre de poubelles pleines 
		
		int cptVerre=0;
		for (int i=0; i<listePoubelleVerre.size();i++) {
				
				while(listePoubelleVerre.get(i).estPleine()!=true && listeVerre.size()>0) {
						Dechet dv = listeVerre.get(0);
						listePoubelleVerre.get(i).ajoutDechet(dv);
						listeVerre.remove(0);
				}
				if (listePoubelleVerre.get(i).estPleine()==true)cptVerre++;
			
		}
		s+="Nombre de poubelles verre pleines : "+cptVerre+"\n";
		
		int cptPapier=0;
		for (int i=0; i<listePoubellePapier.size();i++) {
			while(listePoubellePapier.get(i).estPleine()!=true && listePapier.size()>0) {
				Dechet dv = listePapier.get(0);
				listePoubellePapier.get(i).ajoutDechet(dv);
				listePapier.remove(0);
			}	
			if (listePoubellePapier.get(i).estPleine()==true)cptPapier++;
		}
		s+="Nombre de poubelles papier pleines : "+cptPapier+"\n";
		
		int cptAutre=0;
		for (int i=0; i<listePoubelleAutre.size();i++) {
			while(listePoubelleAutre.get(i).estPleine()!=true && listeAutre.size()>0) {
				Dechet dv = listeAutre.get(0);
				listePoubelleAutre.get(i).ajoutDechet(dv);
				listeAutre.remove(0);
			}	
			if (listePoubelleAutre.get(i).estPleine()==true)cptAutre++;
		}
		s+="Nombre de poubelles autres pleines : "+cptAutre+"\n";
		
		//On vérifie que tous les déchets ont été mis à la poubelle.Sinon on affiche un message d'erreur
		boolean listeVide = true;
		if(listeVerre.size()!=0) {
			listeVide = false; 
			}
		
		if(listePapier.size()!=0) {
		 listeVide = false; 
			}
		
		if(listePoubelleAutre.size()!=0) {
			listeVide = false; 
		}
		
		
		if(listeVide==false) s+="Problème : il reste des déchets non traités car la poubelle n'était pas pleine! Rectifiez vos données: soit le nombre de déchets, soit la taille des poubelles."+"\n";
		
		
		//vidage des poubelles par les camions
		
		int cptCamion=0;
		//on parcourt la liste de camions dont on va remplir les poubelles
		for (int i=0; i<listeCamion.size();i++) {
			//on vide les poubelles verre pleines 
			while(listeCamion.get(i).estPlein()!=true && listePoubelleVerre.size()>0) {
					Poubelle pv = listePoubelleVerre.get(0);
					listeCamion.get(i).allerPoubelle();
					listeCamion.get(i).videPoubelle(pv);
					listePoubelleVerre.remove(pv);
			}
		
			//on vide les poubelles papier pleines
			while(listeCamion.get(i).estPlein()!=true && listePoubellePapier.size()>0) {
				Poubelle pp = listePoubellePapier.get(0);
				listeCamion.get(i).allerPoubelle();
				listeCamion.get(i).videPoubelle(pp);
				listePoubellePapier.remove(pp);
				//problème : si la poubelle n'est pas pleine il l'enlève : il faudrait un compteur des déchets non traités
			}	
			
			//on vide les poubelles autre pleines
			while(listeCamion.get(i).estPlein()!=true && listePoubelleAutre.size()>0) {
				Poubelle pa = listePoubelleAutre.get(0);
				listeCamion.get(i).allerPoubelle();
				listeCamion.get(i).videPoubelle(pa);
				listePoubelleAutre.remove(pa);
			}	
			if (listeCamion.get(i).estPlein()==true)cptCamion++;
		}
		
		s+="Nombre de Camion plein : "+cptCamion+"\n";
		
		//on vérifie que toutes les poubelles pleines dans les 3 listes de poubelles ont été vidées. Sinon, on refait la boucle avec la camionette
		
		boolean listePoubelleVide = true;
		if(listePoubelleVerre.size()!=0) {
			for(Poubelle pv:listePoubelleVerre) {
				if(pv.estPleine()==true) listePoubelleVide = false; 
			}
		}
		if(listePoubellePapier.size()!=0) {
			for(Poubelle pp:listePoubellePapier) {
				if(pp.estPleine()==true) listePoubelleVide = false; 
			}
		}
		if(listePoubelleAutre.size()!=0) {
			for(Poubelle pa:listePoubelleAutre) {
				if(pa.estPleine()==true) listePoubelleVide = false; 
			}
		}
		
		int cptCamionette=0;
		
		if(listePoubelleVide==false) {		//passage de la camionette
				
			//on parcourt la liste de camionettes dont on va remplir les poubelles
			for (int i=0; i<listeCamionette.size();i++) {
				//on vide les poubelles verre pleines 
				while(listeCamionette.get(i).estPlein()!=true && listePoubelleVerre.size()>0) {
					Poubelle pv = listePoubelleVerre.get(0);
					listeCamionette.get(i).allerPoubelle();
					listeCamionette.get(i).videPoubelle(pv);
					listePoubelleVerre.remove(pv);
				}	
			
				//on vide les poubelles papier pleines
				while(listeCamionette.get(i).estPlein()!=true && listePoubellePapier.size()>0) {
					Poubelle pp = listePoubellePapier.get(0);
					listeCamionette.get(i).allerPoubelle();
					listeCamionette.get(i).videPoubelle(pp);
					listePoubellePapier.remove(pp);
				}	
				
				//on vide les poubelles autre pleines
				while(listeCamionette.get(i).estPlein()!=true && listePoubelleAutre.size()>0) {
					Poubelle pa = listePoubelleAutre.get(0);
					listeCamionette.get(i).allerPoubelle();
					listeCamionette.get(i).videPoubelle(pa);
					listePoubelleAutre.remove(pa);
				}	
				if (listeCamionette.get(i).estPlein()==true)cptCamionette++;
			}
			
		}	
			s+="Nombre de Camionettes pleines : "+cptCamionette+"\n";
	
		//après le passage des camionettes on revérifie que toutes les poubelles ont été vidées. Sinon, message d'alerte
		boolean listePoubelleVide2 = true;
		if(listePoubelleVerre.size()!=0) {
			for(Poubelle pv:listePoubelleVerre) {
				if(pv.estPleine()==true) listePoubelleVide2 = false; 
			}
		}
		if(listePoubellePapier.size()!=0) {
			for(Poubelle pp:listePoubellePapier) {
				if(pp.estPleine()==true) listePoubelleVide2 = false; 
			}
		}
		if(listePoubelleAutre.size()!=0) {
			for(Poubelle pa:listePoubelleAutre) {
				if(pa.estPleine()==true) listePoubelleVide2 = false; 
			}
		}
		
		if(listePoubelleVide2==false) s+="Problème : il n'y pas assez de camions et de camionettes pour vider tous les déchets!!"+"\n";
		
		//aller à l'usine
		
		for(Camion camion: listeCamion) {
			if(camion!=null)
				try {
					camion.allerUsine(usine);
				} catch (VehiculeException e) {
					s+=e.getMessage();
				}
		}
		for(Camionette camionette: listeCamionette) {
			if(camionette!=null)
				try {
					camionette.allerUsine(usine);
				} catch (VehiculeException e) {
					s+=e.getMessage();
				}
		}
		
		//traiter les déchets
		usine.traiter();
	
		s+="Prix des réparations moteur : "+Moteur.getPrixReparationsMoteur()+" euros \n";
		s+="Prix des réparations roues : "+Roue.getPrixReparationsRoues()+" euros \n";

		s+= (Usine.getNbDechetRecycle()+ Usine.getNbDechetBrule())*100/Dechet.getCpt()+"% de déchets traités\n"+Usine.getNbDechetRecycle()+" déchets recyclés et "+Usine.getNbDechetBrule()+" déchets non recyclables brûlés";
		return s;
	}
	
	
	public ArrayList<Poubelle> getListePoubellePapier(){
		return listePoubellePapier;
	}
	
	public ArrayList<Poubelle> getListePoubelleVerre(){
		return listePoubelleVerre;
	}
	
	public ArrayList<Poubelle> getListePoubelleAutre(){
		return listePoubelleAutre;
	}
	
	public ArrayList<Camion> getListeCamion(){
		return listeCamion;
	}
	public ArrayList<Camionette> getListeCamionette(){
		return listeCamionette;
	}
	public ArrayList<Dechet> getListeDechet(){
		return listeDechet;
	}
	public Usine getUsine() {
		return usine;
	}
	
	
}