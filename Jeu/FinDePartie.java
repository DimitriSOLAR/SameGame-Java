import javax.swing.*;
import java.awt.event.*;

public class FinDePartie {
    public static void afficher(int score, JFrame fenetreActuelle) {
        int choix = JOptionPane.showOptionDialog(
            fenetreActuelle,
            "Partie terminée !\nScore final : " + score + "\nQue voulez-vous faire ?",
            "Fin de partie",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new Object[] {"Rejouer", "Quitter"},
            "Rejouer"
        );

        if (choix == JOptionPane.YES_OPTION) {
            fenetreActuelle.dispose();
        } else {
            System.exit(0);
        }
    }
}
