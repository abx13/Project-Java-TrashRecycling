package vehicule;

import poubelle.Poubelle;
import usine.Usine;

public interface Vehicule {
	
	public void videPoubelle(Poubelle p);
	public void allerPoubelle();
	public void allerUsine(Usine usine) throws VehiculeException;
	public boolean estPlein();
	public Poubelle[] getPoubelles();

}