import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point; // Import nécessaire pour la classe Point

public class LogiqueJeu {
    private Bloc[][] plateau;
    private int lignes;
    private int colonnes;
    private int score;
    private PanneauJeu panneauJeu;

    public LogiqueJeu(int lignes, int colonnes, PanneauJeu panneauJeu) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.plateau = new Bloc[lignes][colonnes];
        this.score = 0;
        this.panneauJeu = panneauJeu;
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        Random random = new Random();
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                plateau[i][j] = new Bloc(random.nextInt(3));
            }
        }
    }

    public void clic(int ligne, int colonne) {
        if (estValide(ligne, colonne) && plateau[ligne][colonne].getCouleur() != -1) {
            List<Bloc> groupe = trouverGroupe(ligne, colonne);

            if (groupe.size() > 1) {
                supprimerGroupe(groupe);
                effondrerPlateau();
                int n = groupe.size();
                score += Math.max(0, (n - 2) * (n - 2));
                if (panneauJeu != null) {
                    panneauJeu.updateScore();
                    panneauJeu.repaintPanneau();
                }
            }
        }
    }

    // Nouvelle méthode pour trouver le groupe pour la surbrillance
    public List<Bloc> trouverGroupePourSurbrillance(int ligne, int colonne) {
        if (estValide(ligne, colonne) && plateau[ligne][colonne].getCouleur() != -1) {
            return trouverGroupeInterne(ligne, colonne, false); // Ne pas marquer les blocs
        }
        return null;
    }

    private List<Bloc> trouverGroupe(int ligne, int colonne) {
        return trouverGroupeInterne(ligne, colonne, true); // Marquer les blocs lors de la recherche pour la suppression
    }

    private List<Bloc> trouverGroupeInterne(int ligne, int colonne, boolean marquer) {
        List<Bloc> groupe = new ArrayList<>();
        List<Point> aVerifier = new ArrayList<>();
        aVerifier.add(new Point(colonne, ligne)); // Stocker les coordonnées pour éviter de manipuler directement les Blocs

        boolean[][] dejaVerifie = new boolean[lignes][colonnes];
        int couleurCible = plateau[ligne][colonne].getCouleur();

        while (!aVerifier.isEmpty()) {
            Point pointActuel = aVerifier.remove(0);
            int c = pointActuel.x;
            int l = pointActuel.y;

            if (estValide(l, c) && plateau[l][c].getCouleur() == couleurCible && !dejaVerifie[l][c]) {
                Bloc actuel = plateau[l][c];
                groupe.add(actuel);
                dejaVerifie[l][c] = true; // Marquer comme vérifié

                // Ajouter les voisins à vérifier
                ajouterVoisinSiValideEtCouleurPourGroupe(l - 1, c, couleurCible, aVerifier, dejaVerifie);
                ajouterVoisinSiValideEtCouleurPourGroupe(l + 1, c, couleurCible, aVerifier, dejaVerifie);
                ajouterVoisinSiValideEtCouleurPourGroupe(l, c - 1, couleurCible, aVerifier, dejaVerifie);
                ajouterVoisinSiValideEtCouleurPourGroupe(l, c + 1, couleurCible, aVerifier, dejaVerifie);
            }
        }
        return groupe;
    }

    private void ajouterVoisinSiValideEtCouleurPourGroupe(int ligne, int colonne, int couleurCible, List<Point> aVerifier, boolean[][] dejaVerifie) {
        if (estValide(ligne, colonne) && plateau[ligne][colonne].getCouleur() == couleurCible && !dejaVerifie[ligne][colonne]) {
            aVerifier.add(new Point(colonne, ligne));
        }
    }

    private boolean estValide(int ligne, int colonne) {
        return ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes && plateau[ligne][colonne] != null;
    }

    private void supprimerGroupe(List<Bloc> groupe) {
        for (Bloc bloc : groupe) {
            bloc.setCouleur(-1);
        }
    }

    private void effondrerPlateau() {
        for (int j = 0; j < colonnes; j++) {
            int indexEcriture = lignes - 1;
            for (int i = lignes - 1; i >= 0; i--) {
                if (plateau[i][j] != null && plateau[i][j].getCouleur() != -1) {
                    plateau[indexEcriture][j] = plateau[i][j];
                    indexEcriture--;
                }
            }
            for (int i = indexEcriture; i >= 0; i--) {
                plateau[i][j] = new Bloc(-1);
            }
        }

        int colonneEcriture = 0;
        for (int j = 0; j < colonnes; j++) {
            boolean colonneNonVide = false;
            for (int i = 0; i < lignes; i++) {
                if (plateau[i][j] != null && plateau[i][j].getCouleur() != -1) {
                    colonneNonVide = true;
                    break;
                }
            }
            if (colonneNonVide) {
                for (int i = 0; i < lignes; i++) {
                    plateau[i][colonneEcriture] = plateau[i][j];
                }
                colonneEcriture++;
            }
        }
        for (int j = colonneEcriture; j < colonnes; j++) {
            for (int i = 0; i < lignes; i++) {
                plateau[i][j] = new Bloc(-1);
            }
        }
    }

    public Bloc[][] getPlateau() {
        return plateau;
    }

    public int getScore() {
        return score;
    }

    public int obtenirLigne(Bloc bloc) {
        if (bloc == null) return -1;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (plateau[i][j] == bloc) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int obtenirColonne(Bloc bloc) {
        if (bloc == null) return -1;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (plateau[i][j] == bloc) {
                    return j;
                }
            }
        }
        return -1;
    }

    public void setPanneauJeu(PanneauJeu panneauJeu) {
        this.panneauJeu = panneauJeu;
    }
}