import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChoixModeJeu {

    public static Bloc[][] obtenirPlateauDepuisChoix(int lignes, int colonnes) {
        int choix = JOptionPane.showOptionDialog(null,
                "Choisissez le mode de jeu",
                "Mode de Jeu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Aléatoire", "Charger un fichier"},
                "Aléatoire");

        if (choix == 1) {
            return chargerPlateauDepuisFichier(lignes, colonnes);
        } else {
            return genererPlateauAleatoire(lignes, colonnes);
        }
    }

    private static Bloc[][] chargerPlateauDepuisFichier(int lignes, int colonnes) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                List<String> lignesFichier = new ArrayList<>();
                String ligne;
                while ((ligne = br.readLine()) != null) {
                    lignesFichier.add(ligne);
                }

                if (lignesFichier.size() != lignes || lignesFichier.get(0).length() != colonnes) {
                    JOptionPane.showMessageDialog(null, "Le fichier ne correspond pas à la taille du plateau.");
                    return genererPlateauAleatoire(lignes, colonnes); // Retourner un plateau aléatoire en cas d'erreur
                }

                Bloc[][] plateau = new Bloc[lignes][colonnes];
                for (int i = 0; i < lignes; i++) {
                    for (int j = 0; j < colonnes; j++) {
                        char couleurChar = lignesFichier.get(i).charAt(j);
                        int couleur = (couleurChar == 'R') ? 0 : (couleurChar == 'V') ? 1 : 2;
                        plateau[i][j] = new Bloc(couleur);
                    }
                }
                return plateau;

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier.");
            }
        }
        return genererPlateauAleatoire(lignes, colonnes); // Retourner un plateau aléatoire si aucun fichier n'est sélectionné
    }

    private static Bloc[][] genererPlateauAleatoire(int lignes, int colonnes) {
        Bloc[][] plateau = new Bloc[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                plateau[i][j] = new Bloc((int) (Math.random() * 3)); // Génère un nombre aléatoire entre 0 et 2
            }
        }
        return plateau;
    }
}
