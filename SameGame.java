import javax.swing.*;
import java.awt.*;

public class SameGame {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fenetre = new JFrame("SameGame");
            int lignes = 10;
            int colonnes = 15;
            LogiqueJeu logiqueJeu = new LogiqueJeu(lignes, colonnes, null);
            PanneauJeu panneauJeu = new PanneauJeu(logiqueJeu);
            logiqueJeu.setPanneauJeu(panneauJeu);
            JLabel labelScore = new JLabel("Score : " + logiqueJeu.getScore());
            panneauJeu.setScoreLabel(labelScore); 
            JPanel panneauScore = new JPanel();
            panneauScore.add(labelScore);
            fenetre.setLayout(new BorderLayout());
            fenetre.add(panneauScore, BorderLayout.SOUTH);
            fenetre.add(panneauJeu, BorderLayout.CENTER);
            fenetre.setSize(colonnes * 30, lignes * 30 + 50);
            fenetre.setVisible(true);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
