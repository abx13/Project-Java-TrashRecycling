package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;





public class IHMSwing implements ActionListener {
	//gere ce que font les boutons 
	private Modele modele;
	private Vue vue;
	
	public IHMSwing(Modele modele, Vue vue) {
		this.modele= modele;
		this.vue= vue;
	}
	
	public void actionPerformed(ActionEvent e) {
		//zones texte
		if (e.getActionCommand().contentEquals("Lancer")) {
			try {
				if (Integer.parseInt(vue.getZonesTxt()[0].getText())<3) {
					vue.erreur("Le nombre de poubelles doit être supérieur ou égal à 3");
				}else {
					modele.setNbPoubelles(Integer.parseInt(vue.getZonesTxt()[0].getText()));
				}
				modele.setTaillePoubelle(Integer.parseInt(vue.getZonesTxt()[1].getText()));
				modele.setNbCamions(Integer.parseInt(vue.getZonesTxt()[2].getText()));
				modele.setTailleCamion(Integer.parseInt(vue.getZonesTxt()[3].getText()));
				modele.setNbCamionettes(Integer.parseInt(vue.getZonesTxt()[4].getText()));
				modele.setTailleCamionette(Integer.parseInt(vue.getZonesTxt()[5].getText()));
			}catch (java.lang.NumberFormatException e1) {
				vue.erreur("Vous n'avez pas correctement rempli les zones texte");
			}
			
		}else {
			//boutons dechets
			String s=((JButton)e.getSource()).getText();
				modele.addDechet(s);
		}
		/*
		if (e.getSource()==vue.getZonesTxt()[0]) {
			modele.setNbPoubelles(Integer.parseInt(vue.getZonesTxt()[0].getText()));
		}
		if (e.getSource()==vue.getZonesTxt()[1]) {
			modele.setTaillePoubelle(Integer.parseInt(vue.getZonesTxt()[1].getText()));
		}
		if (e.getSource()==vue.getZonesTxt()[2]) {
			modele.setNbCamions(Integer.parseInt(vue.getZonesTxt()[2].getText()));
		}
		if (e.getSource()==vue.getZonesTxt()[3]) {
			modele.setTailleCamion(Integer.parseInt(vue.getZonesTxt()[3].getText()));
		}
		if (e.getSource()==vue.getZonesTxt()[4]) {
			modele.setNbCamionettes(Integer.parseInt(vue.getZonesTxt()[4].getText()));
		}
		if (e.getSource()==vue.getZonesTxt()[5]) {
			modele.setTailleCamionette(Integer.parseInt(vue.getZonesTxt()[5].getText()));
		}
		else {
			String s=((JButton)e.getSource()).getText();
			if (s.compareTo("Lancer")!=0) {
				modele.addDechet(s);
			}
		}*/
		
		
		//si tout a ete rempli, lancer l'écriture de fichier
		if (e.getActionCommand().contentEquals("Lancer")) {
			if (modele.toutRempli()==true) {
				try {
					modele.lancer();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				//lancer msg erreur
				vue.erreur("Vous devez tout remplir avant de cliquer sur Lancer");
			}
		}
	}

}