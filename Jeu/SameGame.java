import javax.swing.*;
import java.awt.*;

public class SameGame {

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

            JPanel panneauScore = new JPanel();
            panneauScore.add(labelScore);

            JFrame fenetre = new JFrame("SameGame");
            fenetre.setLayout(new java.awt.BorderLayout());
            fenetre.add(panneauScore, java.awt.BorderLayout.SOUTH);
            fenetre.add(panneauJeu, java.awt.BorderLayout.CENTER);
            fenetre.setSize(colonnes * 30, lignes * 30 + 50);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setVisible(true);
        });
    }
}
