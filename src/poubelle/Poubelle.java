package poubelle;

import dechet.Dechet;

public abstract class Poubelle {
			
	protected Dechet[] dechets;
	public int taille;
	public int cpt; 
		
	
	public Poubelle(int taille) {
		this.taille=taille;
		this.dechets = new Dechet[taille];
		cpt=0;
	}
	
	public Poubelle(Poubelle p) {
		this.taille=p.taille;
		this.dechets = p.dechets;
		this.cpt=p.cpt;
	}
	
	public void ajoutDechet(Dechet d) {
		if(!this.estPleine()) {
			dechets[cpt]=d;
			cpt++;
		}
		else System.out.println("poubelle pleine, ça déborde !!");
		
	}
	
	public boolean estPleine() {
		if (cpt==taille) return true;
		return false;
	}
	
	public abstract String toString();
	
	public Dechet[] getDechets() {
		return dechets; 
	}
	
	public int getTaillePb() {
		return taille;
	}

	public void resetDechets(){
		
		for(int i=0; i<taille; i++){
			this.dechets[i]=null;
		}
		
	}

	public abstract String getColor();
	
	public abstract Poubelle clone() ;
		
	
	
	

}
