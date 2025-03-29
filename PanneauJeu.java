import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanneauJeu extends JPanel 
{
    	private LogiqueJeu logiqueJeu;
   	public PanneauJeu(LogiqueJeu logiqueJeu) 
	{
        	this.logiqueJeu = logiqueJeu;
        	addMouseListener(new MouseAdapter() 
		{
            		@Override
           		public void mouseClicked(MouseEvent e) 
			{
            			logiqueJeu.clic(e.getY() / 30, e.getX() / 30);
                		repaint();
            		}
        	});
    	}
    	@Override
       	protected void paintComponent(Graphics g) 	
	{
        	super.paintComponent(g);
        	Bloc[][] plateau = logiqueJeu.getPlateau();
        	int lignes = plateau.length;
        	int colonnes = plateau[0].length;
        	for (int i = 0; i < lignes; i++) 
		{
            		for (int j = 0; j < colonnes; j++) 
			{
                		if (plateau[i][j].getCouleur() != -1) 
				{
                    			g.setColor(obtenirCouleur(plateau[i][j].getCouleur()));
                    			g.fillRect(j * 30, i * 30, 30, 30);
                    			g.setColor(Color.BLACK);
                    			g.drawRect(j * 30, i * 30, 30, 30);
                		}
            		}
        	}
        	g.drawString("Score: " + logiqueJeu.getScore(), 10, 20);
    	}
	private Color obtenirCouleur(int couleur) 
	{
        	switch (couleur) 
		{
            		case 0:
                		return Color.RED;
            		case 1:
               			return Color.GREEN;
            		case 2:
                		return Color.BLUE;
            		default:
                		return null;
        	}
    	}
}
