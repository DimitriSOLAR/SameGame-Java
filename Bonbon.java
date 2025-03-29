import java.awt.Color;

public class Bonbon 
{
	private int type;
   	private Color couleur;
   	public Bonbon(int type, Color couleur) 
	{
        	this.type = type;
        	this.couleur = couleur;
    	}
    	public int getType() 
	{
        	return type;
    	}
   	public Color getCouleur() 
	{
        	return couleur;
   	}
}
