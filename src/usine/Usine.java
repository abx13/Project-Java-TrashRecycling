package usine;

import java.util.ArrayList;

import poubelle.Poubelle;
import poubelle.PoubelleAutre;

public class Usine{
	private ArrayList<Poubelle> poubellesATraiter= new ArrayList<Poubelle>();
	private static int nbDechetRecycle=0;
	private static int nbDechetBrule=0;
	
	public Usine(){
	}
	
	public void traiter(){
		for(Poubelle p : poubellesATraiter){
			if(p!=null) {
				for(int i=0; i<p.getTaillePb(); i++){
					if(p.getDechets()[i]!=null) {
						if(p instanceof PoubelleAutre) this.bruler();
						else this.recycler();	 
					}
					
					
				}
			}
		}
	}
		
		public void recycler(){
			nbDechetRecycle++;
			//gagner de l'argent ?
		}
		
		public void bruler(){
			nbDechetBrule++;
			//perdre de l'argent ?
		}
		
		public static int getNbDechetRecycle(){return nbDechetRecycle;}
		
		public static int getNbDechetBrule(){return nbDechetBrule;}
		
		public int nbDechetTotal() {
			return nbDechetRecycle+nbDechetBrule;
		}
	
		public void setPoubellesATraiter(Poubelle p){
			poubellesATraiter.add(p);
		}
}