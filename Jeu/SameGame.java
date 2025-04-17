import javax.swing.*;
import java.awt.*;

/**
 * Cette classe contient le point d'entrée du programme, le jeu `SameGame`.
 * Elle initialise l'interface graphique et commence le jeu.
 * Le jeu permet à l'utilisateur de jouer à une version interactive de SameGame.
 */
public class SameGame {

    /**
     * Méthode principale qui démarre le jeu.
     * @param args Arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int lignes = 10;
            int colonnes = 15;

            Bloc[][] plateauInitial = ChoixModeJeu.obtenirPlateauDepuisChoix(lignes, colonnes);
            LogiqueJeu logiqueJeu = new LogiqueJeu(lignes, colonnes, plateauInitial, null);
            PanneauJeu panneauJeu = new PanneauJeu(logiqueJeu);
            logiqueJeu.setPanneauJeu(panneauJeu);
            logiqueJeu.setSurveillerFinPartie(true);

            JLabel labelScore = new JLabel("Score : " + logiqueJeu.getScore());
            panneauJeu.setScoreLabel(labelScore);
            JFrame frame = new JFrame("SameGame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(panneauJeu, BorderLayout.CENTER);
            frame.add(labelScore, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
