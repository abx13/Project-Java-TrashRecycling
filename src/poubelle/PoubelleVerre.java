package poubelle;

public class PoubelleVerre extends Poubelle {
	private String color;

	public PoubelleVerre(int taille) {
		super(taille);
		color="green";
	}
	
	public PoubelleVerre (Poubelle p) {
		super(p);
		color="vert";
	}
	
	public String toString(){
		return "La poubelle de verre de couleur"+color+ "est pleine :"+this.estPleine();
	}
	
	public String getColor() {
		return color;
	}
	public Poubelle clone() {
		Poubelle p= new PoubelleVerre(super.taille) ;
		p.dechets=this.getDechets();
		return p;
	}

}