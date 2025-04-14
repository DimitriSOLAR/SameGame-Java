import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;

public class SurbrillanceSouris extends MouseMotionAdapter {
    private PanneauJeu panneauJeu;
    private Point dernierPointSouris = new Point(-1, -1);

    public SurbrillanceSouris(PanneauJeu panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (panneauJeu == null || panneauJeu.getLogiqueJeu() == null) {
            return;
        }
        Bloc[][] plateau = panneauJeu.getLogiqueJeu().getPlateau();
        if (plateau != null && plateau.length > 0 && plateau[0].length > 0) {
            int lignes = plateau.length;
            int colonnes = plateau[0].length;
            int largeurBloc = Math.min(panneauJeu.getWidth() / colonnes, panneauJeu.getHeight() / lignes);
            int hauteurBloc = largeurBloc;

            int colonneSouris = e.getX() / largeurBloc;
            int ligneSouris = e.getY() / hauteurBloc;
            Point pointSourisActuel = new Point(colonneSouris, ligneSouris);

            if (ligneSouris >= 0 && ligneSouris < lignes && colonneSouris >= 0 && colonneSouris < colonnes) {
                if (!pointSourisActuel.equals(dernierPointSouris)) {
                    dernierPointSouris = pointSourisActuel;
                    panneauJeu.sourisLigne = ligneSouris;
                    panneauJeu.sourisColonne = colonneSouris;
                    if (plateau[panneauJeu.sourisLigne][panneauJeu.sourisColonne] != null && plateau[panneauJeu.sourisLigne][panneauJeu.sourisColonne].getCouleur() != -1) {
                        panneauJeu.groupeSurbrillance = panneauJeu.getLogiqueJeu().trouverGroupePourSurbrillance(panneauJeu.sourisLigne, panneauJeu.sourisColonne);
                    } else {
                        panneauJeu.groupeSurbrillance = null;
                    }
                    panneauJeu.repaint();
                }
            } else {
                if (panneauJeu.sourisLigne != -1 || panneauJeu.sourisColonne != -1 || panneauJeu.groupeSurbrillance != null) {
                    panneauJeu.sourisLigne = -1;
                    panneauJeu.sourisColonne = -1;
                    panneauJeu.groupeSurbrillance = null;
                    panneauJeu.repaint();
                }
            }
        } else {
            panneauJeu.sourisLigne = -1;
            panneauJeu.sourisColonne = -1;
            panneauJeu.groupeSurbrillance = null;
        }
    }
}