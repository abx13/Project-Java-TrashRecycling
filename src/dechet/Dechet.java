package dechet;

public class Dechet {
	private String materiau ;
	private static int cpt=0 ;
	
	
	public Dechet(String materiau) {
		this.materiau=materiau;
		cpt++;
	}
	
	public String getMateriau() {
		return materiau;
	}
	
	public static int getCpt() {
		return cpt;
	}
	
	

}