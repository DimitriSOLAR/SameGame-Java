import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurDeSouris extends MouseAdapter {
    private PanneauJeu panneauJeu;

    public EcouteurDeSouris(PanneauJeu panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int largeurBloc = panneauJeu.getWidth() / panneauJeu.getLogiqueJeu().getPlateau()[0].length;
        int hauteurBloc = panneauJeu.getHeight() / panneauJeu.getLogiqueJeu().getPlateau().length;

        int colonne = e.getX() / largeurBloc;
        int ligne = e.getY() / hauteurBloc;

        panneauJeu.getLogiqueJeu().clic(ligne, colonne);
        
        panneauJeu.repaint();
    }
}
