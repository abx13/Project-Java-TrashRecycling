package vehicule;

public class Moteur {

	private boolean etat;
	private String type;
	private static int prixReparationsMoteur=0;
	
	public Moteur(String type) {
		etat=true;
		this.type=type;
	}
	
	public Moteur(Moteur m) {
		this.etat=m.etat;
		this.type=m.type;
	}
	
	public void casser() {
		this.etat=false;
	}
	
	public boolean estCasse() {
		if (this.etat==false) return true;
		return false;
	}
	
	public void reparer() {
		this.etat=true;
		prixReparationsMoteur+=500;
	}
	public static int getPrixReparationsMoteur(){return prixReparationsMoteur;}
}