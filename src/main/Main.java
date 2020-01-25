package main;

import javax.swing.JFrame;
import swing.IHMSwing;
import swing.Modele;
import swing.Vue;

public class Main {
//gere la coordination entre l'interaction de l'utilisateur et le programme
	public static void main(String[] args) {
		Vue vue = new Vue();
		Modele modele = new Modele(vue);
		
		IHMSwing ihm = new IHMSwing(modele, vue);
		for(int i=0; i<vue.getBoutons().length; i++) {
			vue.getBoutons()[i].addActionListener(ihm);
		}
		
		//interaction avec utilisateur
		vue.getFenetre().setVisible(true);
		vue.getFenetre().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}	