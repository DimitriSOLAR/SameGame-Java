import java.util.*;
import javax.swing.*;

public class LogiqueJeu {
    private Bloc[][] plateau;
    private int lignes;
    private int colonnes;
    private int score;
    private PanneauJeu panneauJeu;
    private boolean surveillerFinPartie = false;

    public LogiqueJeu(int lignes, int colonnes, Bloc[][] plateauInitial, PanneauJeu panneauJeu) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.panneauJeu = panneauJeu;
        this.plateau = new Bloc[lignes][colonnes];

        if (plateauInitial != null) {
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < colonnes; j++) {
                    char c = Character.toUpperCase(plateauInitial[i][j].getLettre());
                    int couleur = switch (c) {
                        case 'R' -> 0;
                        case 'V' -> 1;
                        case 'B' -> 2;
                        default -> -1;
                    };
                    this.plateau[i][j] = new Bloc(couleur);
                }
            }
        } else {
            Random rand = new Random();
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < colonnes; j++) {
                    plateau[i][j] = new Bloc(rand.nextInt(3));
                }
            }
        }
    }

    public void setPanneauJeu(PanneauJeu panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    public Bloc[][] getPlateau() {
        return plateau;
    }

    public int getScore() {
        return score;
    }

    public void clic(int ligne, int colonne) {
        List<Bloc> groupe = trouverGroupe(ligne, colonne);
        if (groupe.size() >= 2) {
            for (Bloc bloc : groupe) {
                plateau[obtenirLigne(bloc)][obtenirColonne(bloc)] = null;
            }
            score += (groupe.size() - 2) * (groupe.size() - 2);
            compacter();
            if (panneauJeu != null) panneauJeu.updateScore();

            if (surveillerFinPartie && !resteGroupes()) {
                finDePartie();
            }
        }
    }

    private void compacter() {
        for (int j = 0; j < colonnes; j++) {
            int vide = lignes - 1;
            for (int i = lignes - 1; i >= 0; i--) {
                if (plateau[i][j] != null) {
                    plateau[vide][j] = plateau[i][j];
                    if (vide != i) plateau[i][j] = null;
                    vide--;
                }
            }
        }

        int destination = 0;
        for (int source = 0; source < colonnes; source++) {
            boolean colonneVide = true;
            for (int i = 0; i < lignes; i++) {
                if (plateau[i][source] != null) {
                    colonneVide = false;
                    break;
                }
            }
            if (!colonneVide) {
                if (destination != source) {
                    for (int i = 0; i < lignes; i++) {
                        plateau[i][destination] = plateau[i][source];
                        plateau[i][source] = null;
                    }
                }
                destination++;
            }
        }
    }

    public List<Bloc> trouverGroupe(int ligne, int colonne) {
        List<Bloc> groupe = new ArrayList<>();
        boolean[][] visite = new boolean[lignes][colonnes];
        if (plateau[ligne][colonne] == null) return groupe;

        int couleur = plateau[ligne][colonne].getCouleur();
        Queue<int[]> file = new LinkedList<>();
        file.add(new int[]{ligne, colonne});
        visite[ligne][colonne] = true;

        while (!file.isEmpty()) {
            int[] courant = file.poll();
            int i = courant[0];
            int j = courant[1];
            groupe.add(plateau[i][j]);

            int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] d : directions) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < lignes && nj >= 0 && nj < colonnes && !visite[ni][nj]
                        && plateau[ni][nj] != null && plateau[ni][nj].getCouleur() == couleur) {
                    file.add(new int[]{ni, nj});
                    visite[ni][nj] = true;
                }
            }
        }
        return groupe;
    }

    public int obtenirLigne(Bloc bloc) {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (plateau[i][j] == bloc) return i;
            }
        }
        return -1;
    }

    public int obtenirColonne(Bloc bloc) {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (plateau[i][j] == bloc) return j;
            }
        }
        return -1;
    }

    public List<Bloc> trouverGroupePourSurbrillance(int ligne, int colonne) {
        List<Bloc> groupe = trouverGroupe(ligne, colonne);
        return groupe.size() >= 2 ? groupe : null;
    }

    private boolean resteGroupes() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                List<Bloc> groupe = trouverGroupe(i, j);
                if (groupe.size() >= 2) return true;
            }
        }
        return false;
    }

    private void finDePartie() {
        int reponse = JOptionPane.showOptionDialog(null,
                "Partie terminée ! Score final : " + score + "\nVoulez-vous rejouer ?",
                "Fin de partie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Rejouer", "Quitter"},
                "Rejouer");

        if (reponse == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
                SameGame.main(new String[]{});
            });
        } else {
            System.exit(0);
        }
    }

    public void setSurveillerFinPartie(boolean surveiller) {
        this.surveillerFinPartie = surveiller;
    }
}