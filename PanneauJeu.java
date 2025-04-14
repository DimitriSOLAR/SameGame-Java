import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

public class PanneauJeu extends JPanel {
    private LogiqueJeu logiqueJeu;
    private EcouteurDeSouris ecouteurDeSouris;
    private JLabel scoreLabel;
    int sourisLigne = -1; // Rendre non-privé pour l'accès depuis SurbrillanceSouris
    int sourisColonne = -1; // Rendre non-privé pour l'accès depuis SurbrillanceSouris
    List<Bloc> groupeSurbrillance = null; // Rendre non-privé pour l'accès depuis SurbrillanceSouris
    private SurbrillanceSouris surbrillanceSouris;

    public PanneauJeu(LogiqueJeu logiqueJeu) {
        this.logiqueJeu = logiqueJeu;
        this.ecouteurDeSouris = new EcouteurDeSouris(this);
        this.addMouseListener(ecouteurDeSouris);

        surbrillanceSouris = new SurbrillanceSouris(this); // Passer une référence à PanneauJeu
        this.addMouseMotionListener(surbrillanceSouris);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Bloc[][] plateau = logiqueJeu.getPlateau();
        if (plateau == null || plateau.length == 0 || plateau[0].length == 0) {
            return;
        }
        int lignes = plateau.length;
        int colonnes = plateau[0].length;

        int largeurBloc = Math.min(getWidth() / colonnes, getHeight() / lignes);
        int hauteurBloc = largeurBloc;

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (plateau[i][j] != null && plateau[i][j].getCouleur() != -1) {
                    g.setColor(obtenirCouleur(plateau[i][j].getCouleur()));
                    g.fillRect(j * largeurBloc, i * hauteurBloc, largeurBloc, hauteurBloc);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * largeurBloc, i * hauteurBloc, largeurBloc, hauteurBloc);

                    // Dessiner la surbrillance du groupe
                    if (groupeSurbrillance != null) {
                        for (Bloc bloc : groupeSurbrillance) {
                            int ligneBloc = logiqueJeu.obtenirLigne(bloc);
                            int colonneBloc = logiqueJeu.obtenirColonne(bloc);
                            if (ligneBloc == i && colonneBloc == j) {
                                g.setColor(Color.YELLOW); // Couleur de la surbrillance
                                g.drawRect(j * largeurBloc + 2, i * hauteurBloc + 2, largeurBloc - 4, hauteurBloc - 4);
                            }
                        }
                    }
                }
            }
        }
    }

    private Color obtenirCouleur(int couleur) {
        switch (couleur) {
            case 0: return Color.RED;
            case 1: return Color.GREEN;
            case 2: return Color.BLUE;
            default: return null;
        }
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void updateScore() {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + logiqueJeu.getScore());
        }
    }

    public LogiqueJeu getLogiqueJeu() {
        return logiqueJeu;
    }

    public void repaintPanneau() {
        repaint();
    }
}