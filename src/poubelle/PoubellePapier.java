package poubelle;

public class PoubellePapier extends Poubelle {
	private String color;

	public PoubellePapier(int taille) {
		super(taille);
		color="blue";
	}
	public PoubellePapier (Poubelle p) {
		super(p);
		color="blue";
	}
	
	public String toString(){
		return "La poubelle de papiers de couleur"+color+" est pleine :"+this.estPleine();
	}
	public String getColor() {
		return color;
	}
	public Poubelle clone() {
		Poubelle p= new PoubellePapier(super.taille) ;
		p.dechets=this.getDechets();
		return p;
	}

}