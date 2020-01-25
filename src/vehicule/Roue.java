package vehicule;

public class Roue {

	public int taille;
	public int niveauUsure;
	public static final int NIVEAU_USURE=20;
	private static int prixReparationsRoues=0;
	
	public Roue(int taille) {
		this.taille=taille;
		this.niveauUsure=NIVEAU_USURE; 
		// TODO Auto-generated constructor stub
	}
	
	public void user() {
		this.niveauUsure--; 
	}
	
	public boolean estTropUsee() {
		if(niveauUsure==0) {
			return true;
		}
		return false;
	}
	
	public void remplacerRoue() {
		this.niveauUsure=NIVEAU_USURE;
		prixReparationsRoues+=100;
	}
	

	public Roue cloneRoue() {
		return new Roue(this.taille);
	}
	
	public static int getPrixReparationsRoues(){return prixReparationsRoues;}
}