package dechet; 

import java.util.ArrayList;

public class TriDechet{
	
	private ArrayList<Dechet> listePapier;
	private ArrayList<Dechet> listeVerre;
	private ArrayList<Dechet> listeAutre;
	private ArrayList<Dechet> listeDechet;
	
	
	public TriDechet(ArrayList<Dechet> listeDechet) {
		this.listeDechet=listeDechet;
		listePapier= new ArrayList<Dechet>();
		listeVerre= new ArrayList<Dechet>();
		listeAutre= new ArrayList<Dechet>();
	}
	
	public void tri() {
		for(Dechet d : listeDechet) {
			if(d.getMateriau().compareTo("Papier")==0) listePapier.add(d);
			if(d.getMateriau().compareTo("Verre")==0) listeVerre.add(d);
			if(d.getMateriau().compareTo("Autre")==0) listeAutre.add(d);
			//pas d'autre cas possible puisque c'est issu de l'affichage mvc
		}
	}
	
	public ArrayList<Dechet> getListePapier(){
		return listePapier;
	}
	
	public ArrayList<Dechet> getListeVerre(){
		return listeVerre;
	}
	
	public ArrayList<Dechet> getListeAutre(){
		return listeAutre;
	}
}	
