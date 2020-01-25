package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Vue {
//gere la creation de boutons et de zones de texte
	private JFrame fenetre;
	private JButton[] boutons;
	private JTextField[] zonesTxt;
	
	public Vue() {
		
		//creation fenetre
		fenetre = new JFrame();
		fenetre.setLayout(new GridLayout());
		fenetre.setTitle("Recyclage Poubelles");
		
		
		//creation panel boutons 
		JPanel panelGlob = new JPanel();
		panelGlob.setLayout(new BorderLayout());
		JPanel panelBout= new JPanel();
		fenetre.add(panelGlob);
		panelBout.setLayout(new FlowLayout());
		JLabel titreBout = new JLabel("Cliquez sur le type de dechet que vous voulez jeter à la poubelle, chaque clic est un déchet jeté");
		//creation boutons
		boutons= new JButton[4];
		boutons[0]= new JButton("Papier");
		boutons[1]= new JButton("Verre");
		boutons[2]= new JButton("Autre");
		boutons[3]= new JButton("Lancer");
		//insertion dans panel
		panelBout.add(boutons[0], FlowLayout.LEFT);
		panelBout.add(boutons[1], FlowLayout.CENTER);
		panelBout.add(boutons[2], FlowLayout.RIGHT);
		panelGlob.add(titreBout, BorderLayout.NORTH);
		panelGlob.add(panelBout, BorderLayout.CENTER);
		panelGlob.add(boutons[3], BorderLayout.SOUTH);
		
		
		//creation panel txt + creation frame[] + zones txt
		//creation d'un panel global
		JPanel txt = new JPanel();
		fenetre.add(txt);
		txt.setLayout(new GridLayout(6,1));
		//creation de frames pour chacune des zones txt
		JPanel[] panelTxt = new JPanel[6];
		for(int i=0; i<panelTxt.length; i++) {
			panelTxt[i]= new JPanel();
		}
		//insertion des zones txt dans les frames
		zonesTxt= new JTextField[6];
		JLabel[] labelTxt = new JLabel[zonesTxt.length];
		
		//titre-instruction pour les zones txt
		labelTxt[0]= new JLabel("Inserer le nombre de poubelles >=3");
		labelTxt[1]= new JLabel("Inserer le nombre de dechets max pour une poubelle");
		labelTxt[2]= new JLabel("Inserer le nombre de camions");
		labelTxt[3]= new JLabel("Inserer le nombre de poubelles max pour un camion");
		labelTxt[4]= new JLabel("Inserer le nombre de camionette");
		labelTxt[5]= new JLabel("Inserer le nombre de poubelles max pour une camionette");
		
		for(int i=0; i<zonesTxt.length; i++) {
			zonesTxt[i]= new JTextField();
			zonesTxt[i].setColumns(4);
		}
		
		
		//insertion
		for(int i=0; i<panelTxt.length; i++) {
			panelTxt[i].add(labelTxt[i]);
			panelTxt[i].add(zonesTxt[i]);
			
		}
		for(int i=0; i<panelTxt.length; i++) {
			txt.add(panelTxt[i]);
		}
		fenetre.pack();
		
		
	}
	
	public void erreur (String String) {
		JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, String, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	public void resultats (String s) {
		
		JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, s, "Résultats", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public JButton[] getBoutons() {
		return boutons;
	}
	
	public JFrame getFenetre() {
		return fenetre;
	}	
	
	public JTextField[] getZonesTxt(){
		return zonesTxt;
	}
	
}