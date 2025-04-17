import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;

/**
 * Cette classe gère la surbrillance des blocs du jeu lorsque la souris est déplacée.
 * Elle détecte la position de la souris et met en surbrillance les blocs sous le curseur.
 */
public class SurbrillanceSouris extends MouseMotionAdapter {

    /** Le panneau de jeu sur lequel la surbrillance est appliquée. */
    private PanneauJeu panneauJeu;

    /** Le dernier point où la souris a été positionnée, utilisé pour éviter des redessins inutiles. */
    private Point dernierPointSouris = new Point(-1, -1);

    /**
     * Constructeur de la classe SurbrillanceSouris.
     * @param panneauJeu Le panneau de jeu sur lequel la surbrillance est appliquée.
     */
    public SurbrillanceSouris(PanneauJeu panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    /**
     * Méthode appelée lorsqu'un mouvement de la souris est détecté.
     * Cette méthode met à jour la surbrillance des blocs en fonction de la position de la souris.
     * @param e L'événement généré par le mouvement de la souris.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // Vérifier si le panneau de jeu et la logique du jeu existent
        if (panneauJeu == null || panneauJeu.getLogiqueJeu() == null) {
            return;
        }

        Bloc[][] plateau = panneauJeu.getLogiqueJeu().getPlateau();

        // Vérifier si le plateau est valide
        if (plateau != null && plateau.length > 0 && plateau[0].length > 0) {
            int lignes = plateau.length;
            int colonnes = plateau[0].length;

            // Calculer la taille des blocs
            int largeurBloc = Math.min(panneauJeu.getWidth() / colonnes, panneauJeu.getHeight() / lignes);
            int hauteurBloc = largeurBloc;

            // Calculer la position de la souris en termes de lignes et colonnes
            int colonneSouris = e.getX() / largeurBloc;
            int ligneSouris = e.getY() / hauteurBloc;
            Point pointSourisActuel = new Point(colonneSouris, ligneSouris);

            // Vérifier si la souris est sur un bloc valide
            if (ligneSouris >= 0 && ligneSouris < lignes && colonneSouris >= 0 && colonneSouris < colonnes) {
                // Si la souris a bougé depuis la dernière position, mettre à jour la surbrillance
                if (!pointSourisActuel.equals(dernierPointSouris)) {
                    dernierPointSouris = pointSourisActuel;
                    panneauJeu.sourisLigne = ligneSouris;
                    panneauJeu.sourisColonne = colonneSouris;

                    // Vérifier si le bloc sous la souris est valide et mettre à jour la surbrillance
                    if (plateau[panneauJeu.sourisLigne][panneauJeu.sourisColonne] != null &&
                        plateau[panneauJeu.sourisLigne][panneauJeu.sourisColonne].getCouleur() != -1) {
                        panneauJeu.groupeSurbrillance = panneauJeu.getLogiqueJeu().trouverGroupePourSurbrillance(panneauJeu.sourisLigne, panneauJeu.sourisColonne);
                    } else {
                        panneauJeu.groupeSurbrillance = null;
                    }
                    panneauJeu.repaint();
                }
            } else {
                // Si la souris est hors du plateau, réinitialiser la surbrillance
                if (panneauJeu.sourisLigne != -1 || panneauJeu.sourisColonne != -1 || panneauJeu.groupeSurbrillance != null) {
                    panneauJeu.sourisLigne = -1;
                    panneauJeu.sourisColonne = -1;
                    panneauJeu.groupeSurbrillance = null;
                    panneauJeu.repaint();
                }
            }
        } else {
            // Si le plateau est invalide, réinitialiser la surbrillance
            panneauJeu.sourisLigne = -1;
            panneauJeu.sourisColonne = -1;
            panneauJeu.groupeSurbrillance = null;
        }
    }
}
