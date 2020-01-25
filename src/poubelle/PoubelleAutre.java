package poubelle;

public class PoubelleAutre extends Poubelle {
	private String color;

	public PoubelleAutre(int taille) {
		super(taille);
		color="brown";
	}
	
	public PoubelleAutre (Poubelle p) {
		super(p);
		color="brown";
	}
	
	public String toString(){
		return "La poubelle de couleur"+color+" est pleine :"+this.estPleine();
	}

	
	public String getColor() {
		return color;
	}
	
	public Poubelle clone() {
		Poubelle p= new PoubelleAutre(super.taille) ;
		p.dechets=this.getDechets();
		return p;
	}

}