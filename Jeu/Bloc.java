public class Bloc {
	
    private int couleur; // 0 = Rouge, 1 = Vert, 2 = Bleu
    private char lettre;

    public Bloc(int couleur) {
        this.couleur = couleur;
        this.lettre = couleur == 0 ? 'R' : couleur == 1 ? 'V' : 'B';
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public char getLettre() {
        return lettre;
    }

    public void setLettre(char lettre) {
        this.lettre = lettre;
    }
}
