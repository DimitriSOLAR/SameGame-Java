import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogiqueJeu 
{
	private Bloc[][] plateau;
   	private int lignes;
    	private int colonnes;
    	private int score;
   	public LogiqueJeu(int lignes, int colonnes) 
	{
        	this.lignes = lignes;
        	this.colonnes = colonnes;
        	this.plateau = new Bloc[lignes][colonnes];
        	this.score = 0;
        	initialiserPlateau();
   	}
    	private void initialiserPlateau() 
	{
        	Random random = new Random();
        	for (int i = 0; i < lignes; i++) 
		{
            		for (int j = 0; j < colonnes; j++) 
			{
                		plateau[i][j] = new Bloc(random.nextInt(3)); 
            		}
        	}
    	}
    	public void clic(int ligne, int colonne) 
	{
        	List<Bloc> groupe = trouverGroupe(ligne, colonne);
        	if (groupe.size() > 1) 
		{
            		supprimerGroupe(groupe);
            		effondrerPlateau();
            		int n = groupe.size();
            		score += 1 ; // A modifier pour que ca en rapport avec le tableau des scores donné dans la SAE
       		}
    	}
	 private List<Bloc> trouverGroupe(int ligne, int colonne) 
	 {
       		List<Bloc> groupe = new ArrayList<>();
        	List<Bloc> aVerifier = new ArrayList<>();
        	Bloc debut = plateau[ligne][colonne];
        	aVerifier.add(debut);
        	while (!aVerifier.isEmpty()) 
		{
            		Bloc actuel = aVerifier.remove(0);
            		if (!actuel.estMarque()) 
			{
                		groupe.add(actuel);
               		 	actuel.marquer();
                		int l = obtenirLigne(actuel);
                		int c = obtenirColonne(actuel);
                		ajouterVoisins(l, c, actuel.getCouleur(), aVerifier);
            		}
        	}
        	for (Bloc bloc : groupe) 
		{
            		bloc.deMarquer();
        	}
        	return groupe;
    	}
   	private void ajouterVoisins(int ligne, int colonne, int couleur, List<Bloc> aVerifier) 
	{
    	    	if (ligne > 0 && plateau[ligne - 1][colonne].getCouleur() == couleur) 
		{
            		aVerifier.add(plateau[ligne - 1][colonne]);
       		}
        	if (ligne < lignes - 1 && plateau[ligne + 1][colonne].getCouleur() == couleur) 
		{
            		aVerifier.add(plateau[ligne + 1][colonne]);
        	}
        	if (colonne > 0 && plateau[ligne][colonne - 1].getCouleur() == couleur) 
		{
            		aVerifier.add(plateau[ligne][colonne - 1]);
       		}
        	if (colonne < colonnes - 1 && plateau[ligne][colonne + 1].getCouleur() == couleur) 
		{
            		aVerifier.add(plateau[ligne][colonne + 1]);
        	}
   	}
    	private void supprimerGroupe(List<Bloc> groupe) 
	{
        	for (Bloc bloc : groupe) 
		{
            		bloc.setCouleur(-1); 
        	}
    	}
   	private void effondrerPlateau() 
	{
   		for (int j = 0; j < colonnes; j++) 
	     	{
           		int indexEcriture = lignes - 1;
            		for (int i = lignes - 1; i >= 0; i--) 
			{
                		if (plateau[i][j].getCouleur() != -1) 
				{
                    			plateau[indexEcriture][j] = plateau[i][j];
                    			indexEcriture--;
                		}
            		}
            		for (int i = indexEcriture; i >= 0; i--) 
			{
               	 		plateau[i][j] = new Bloc(-1); 
            		}
       		}
        	int colonneEcriture = 0;
       		for (int j = 0; j < colonnes; j++)
	       	{
            		if (plateau[lignes - 1][j].getCouleur() != -1) 
			{
                		for (int i = 0; i < lignes; i++) 
				{
                    			plateau[i][colonneEcriture] = plateau[i][j];
               		 	}
                		colonneEcriture++;
            		}
        	}
        	for (int j = colonneEcriture; j < colonnes; j++) 
		{
            		for (int i = 0; i < lignes; i++) 
			{
                		plateau[i][j] = new Bloc(-1); 
            		}
        	}
    	}
    	public Bloc[][] getPlateau() 
	{
        	return plateau;
    	}
    	public int getScore() 
	{
       		return score;
    	}
    	private int obtenirLigne(Bloc bloc) 
	{
        	for (int i = 0; i < lignes; i++) 
		{
            		for (int j = 0; j < colonnes; j++) 
			{
                		if (plateau[i][j] == bloc) 
				{
                    			return i;
                		}
            		}
        	}
        	return -1;
    	}
    	private int obtenirColonne(Bloc bloc) 
	{
        	for (int i = 0; i < lignes; i++) 
		{
            		for (int j = 0; j < colonnes; j++) 
			{
                		if (plateau[i][j] == bloc) 
				{
                    			return j;
                		}
           		}
       		}
        	return -1;
    	}
}
