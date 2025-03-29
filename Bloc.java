public class Bloc
{
	private int couleur;
   	private boolean marque;
   	public Bloc(int couleur) 
	{
        	this.couleur = couleur;
        	this.marque = false;
   	}
    	public int getCouleur() 
	{
        	return couleur;
    	}
    	public void marquer() 
	{
        	this.marque = true;
    	}

   	public boolean estMarque() 
	{
        	return marque;
    	}
    	public void deMarquer() 
	{
        	this.marque = false;
   	}
    	public void setCouleur(int couleur) 
	{
        	this.couleur = couleur;
    	}
}
