import javax.swing.*;
import java.awt.*;

public class PanneauJeu extends JPanel {
    private LogiqueJeu logiqueJeu;
    private EcouteurDeSouris ecouteurDeSouris;
    private JLabel scoreLabel;

    public PanneauJeu(LogiqueJeu logiqueJeu) {
        this.logiqueJeu = logiqueJeu;
        this.ecouteurDeSouris = new EcouteurDeSouris(this);
        this.addMouseListener(ecouteurDeSouris);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Bloc[][] plateau = logiqueJeu.getPlateau();
        int lignes = plateau.length;
        int colonnes = plateau[0].length;

        int largeurBloc = Math.min(getWidth() / colonnes, getHeight() / lignes);
        int hauteurBloc = largeurBloc;

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (plateau[i][j].getCouleur() != -1) {
                    g.setColor(obtenirCouleur(plateau[i][j].getCouleur()));
                    g.fillRect(j * largeurBloc, i * hauteurBloc, largeurBloc, hauteurBloc);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * largeurBloc, i * hauteurBloc, largeurBloc, hauteurBloc);
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
}
