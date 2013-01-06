package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gère les commandes du jeu rentrées par le joueur. C'est une classe abstraite qui permet d'implémenter
 * les classes de différentes commandes qui l'héritent.
 * 
 * Une commande est composé de deux mots : un mot qui définit la commande et le deuxième qui précise un paramètre de la commande.
 * 
 * Dans cette classe, on ne s'intéresse qu'au deuxième mot de la commande.
 * Si la commande a seulement une mot, alors le second mot est null.
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 * 
 */

public abstract class Command 
{
	private String secondWord;

	/**
	 * Instancie la commande. Par défaut, la commande n'a pas de deuxième mot.
	 */
	public Command()
	{
		secondWord = null;
	}

	/**
	 * Retourne le deuxième mot de la commande. Si la commande n'a pas été
	 * reconnu, le résultat sera NULL.
	 * 
	 * @return Le deuxième mot de la commande.
	 */
	public String getSecondWord() 
	{
		return secondWord;
	}

	/**
	 * Fonction boolean qui retourne vrai si la commande possède un second mot.
	 * 
	 * @return Vrai si la commande possède un second mot
	 */
	public boolean hasSecondWord() 
	{
		return (secondWord != null);
	}

	/**
	 * Imposer le second mot de la commande
	 * 
	 * @param secondWord
	 */
	public void setSecondWord(String secondWord)
    {
        this.secondWord = secondWord;
    }
	
	/**
	 * Méthode abstraite qui sera implémentée dans les sous classes, qui permet d'exécuter les commandes
	 * 
	 * @param player
	 */
	public abstract void execute(Player player);
}
