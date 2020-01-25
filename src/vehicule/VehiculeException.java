package vehicule;

public class VehiculeException extends Exception{

	private static final long serialVersionUID = 1L;
	public VehiculeException() {}
	
	public String getMessage() {
		return "Problème : Moteur cassé!\n";
	}
	


}
