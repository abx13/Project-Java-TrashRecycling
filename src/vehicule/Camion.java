package vehicule;

import poubelle.Poubelle;
import poubelle.PoubelleAutre;
import poubelle.PoubellePapier;
import poubelle.PoubelleVerre;
import usine.Usine;

public class Camion implements Vehicule{
	private Poubelle[] poubelles;
	private int cpt;
	private int taille;
	private Roue[] roues;
	private static final int NB_ROUES=4;
	private Moteur moteur;
	
	public Camion(int taille){
		this.taille=taille;
		this.poubelles= new Poubelle[taille];
		this.roues= new Roue[NB_ROUES];
		for(int i=0; i<NB_ROUES; i++){
			roues[i]=new Roue(120);
		}
		//decision aléatoire du type de moteur
		if(Math.random()<0.5) moteur = new Moteur("diesel");
		else moteur = new Moteur("essence");
		cpt=0;
	}

	public void allerPoubelle(){
		for(int i=0; i<roues.length; i++) {
			this.roues[i].user();
			if(this.roues[i].estTropUsee()) this.roues[i].remplacerRoue();
		}
	}


	public boolean estPlein(){
		if(cpt==taille) return true;
		return false;
	}

	public void videPoubelle (Poubelle p) {
		if(cpt<taille) {
			if(p.estPleine()) {
				if(p.getColor().compareTo("blue")==0) {
					 poubelles[cpt] = new PoubellePapier(p);
				}else if(p.getColor().compareTo("green")==0) {
					 poubelles[cpt] = new PoubelleVerre(p);
				}else poubelles[cpt] = new PoubelleAutre(p);
			}
			cpt++;
		}
		/*for (int i=0; i<p.getTaillePb(); i++){
			p.resetDechets();
		}*/
		
	}

	public void allerUsine (Usine usine) throws VehiculeException {
		//usure liée au déplacement
		for(int i=0; i<roues.length; i++) {
			this.roues[i].user();
			if(this.roues[i].estTropUsee()) this.roues[i].remplacerRoue();
		}
		if(Math.random()<0.2) this.moteur.casser();
		if(this.moteur.estCasse()==true) {
			
			this.moteur.reparer();
			throw new VehiculeException();
		}
		
		//déchargement des poubelles dans l'usine
		
		for(int i=0; i<taille; i++){
			if(this.poubelles[i]!=null) usine.setPoubellesATraiter(this.poubelles[i]);
		}
		
	}
	
	public Poubelle[] getPoubelles() {
		return poubelles;
	}
		
}