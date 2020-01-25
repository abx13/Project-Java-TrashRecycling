package swing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fichier.LectureFichier;



public class Modele {
	private int nbPoubelles;
	private int taillePoubelle;
	private int nbCamions;
	private int tailleCamion;
	private int nbCamionettes;
	private int tailleCamionette;
	private ArrayList<String> dechets;
	private boolean[] rempli = new boolean[7];
	private File file;
	private LectureFichier fichierLire;
	private String string;
	private Vue vue;
	
	public Modele(Vue vue) {
		this.vue=vue;
		dechets = new ArrayList<String>();
		for(int i=0; i<rempli.length; i++) {
			rempli[i]=false;
		}
	}
	
	public void addDechet(String s) {
		dechets.add(s);
		rempli[0]= true;
	}
	public void setNbPoubelles(int nb) {
		nbPoubelles= nb;
		rempli[1]= true;
	}
	
	public void setTaillePoubelle(int taille) {
		taillePoubelle=taille;
		rempli[2]= true;
	}
	public void setNbCamions(int nb) {
		nbCamions= nb;
		rempli[3]= true;
	}
	public void setTailleCamion(int taille) {
		tailleCamion=taille;
		rempli[4]= true;
	}
	public void setNbCamionettes(int nb) {
		nbCamionettes= nb;
		rempli[5]= true;
	}
	public void setTailleCamionette(int taille) {
		tailleCamionette=taille;
		rempli[6]= true;
	}
	
	public boolean toutRempli() {
		for(int i=0; i<rempli.length; i++) {
			if (rempli[i]==false) {
				return false;
			}
		}
		return true;
	}
	public void lancer() throws IOException {
		file = new File("Fichier_Sauvegarde.txt");
		System.out.println(file.getAbsolutePath());
		PrintWriter sortie = new PrintWriter(file);
		try {
			sortie.println(nbPoubelles);
			sortie.println(taillePoubelle);
			sortie.println(nbCamions);
			sortie.println(tailleCamion);
			sortie.println(nbCamionettes);
			sortie.println(tailleCamionette);
			for(int i=0; i<dechets.size(); i++) {
				sortie.println(dechets.get(i));
			}
		}finally {
			System.out.println("Fichier créé");
			sortie.close();
		}
		
		fichierLire = new LectureFichier(file);
		fichierLire.lire();
		this.string=fichierLire.executer();
		vue.resultats(string);
	}
	
	public File getFile() {
		return file;
	}
	
	public LectureFichier getLectureFichier() {
		return fichierLire;
	}
	
	public String getString() {
		return string;
	}
	
}
