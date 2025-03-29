import javax.swing.*;

public class SameGame 
{
   	public static void main(String[] args) 
	{
        	JFrame fenetre = new JFrame("SameGame");
        	LogiqueJeu logiqueJeu = new LogiqueJeu(10, 15);
        	PanneauJeu panneauJeu = new PanneauJeu(logiqueJeu);
        	fenetre.add(panneauJeu);
        	fenetre.setSize(15 * 30, 10 * 30 + 50); 
        	fenetre.setVisible(true);
        	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
}
