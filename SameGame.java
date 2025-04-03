import javax.swing.*;
import java.awt.*;

public class SameGame {
        

    public static void main(String[] args) {
        JFrame fenetre = new JFrame("SameGame");
        LogiqueJeu logiqueJeu = new LogiqueJeu(10, 15);
        PanneauJeu panneauJeu = new PanneauJeu(logiqueJeu);

        JPanel panneauScore = new JPanel();
        JLabel labelScore = new JLabel("Score : " + logiqueJeu.getScore()); //j'arrive pas à faire changer le score
        panneauScore.add(labelScore);

        fenetre.setLayout(new BorderLayout());
        fenetre.add(panneauScore, BorderLayout.SOUTH); 
        fenetre.add(panneauJeu, BorderLayout.CENTER);  


        fenetre.setSize(15 * 30, 10 * 30 + 50); 
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
