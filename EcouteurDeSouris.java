import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurDeSouris extends MouseAdapter {
    private PanneauJeu panneauJeu;

    public EcouteurDeSouris(PanneauJeu panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panneauJeu.getLogiqueJeu().clic(e.getY() / 30, e.getX() / 30);
        panneauJeu.repaint();
    }
}
