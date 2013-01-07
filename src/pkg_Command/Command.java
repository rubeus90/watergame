package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gere les commandes du jeu rentrees par le joueur. C'est une classe abstraite qui permet d'implementer
 * les classes de differentes commandes qui l'heritent.
 * 
 * Une commande est compose de deux mots : un mot qui definit la commande et le deuxieme qui precise un parametre de la commande.
 * 
 * Dans cette classe, on ne s'interesse qu'au deuxieme mot de la commande.
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
	 * Instancie la commande. Par defaut, la commande n'a pas de deuxieme mot.
	 */
	public Command()
	{
		secondWord = null;
	}

	/**
	 * Retourne le deuxieme mot de la commande. Si la commande n'a pas ete
	 * reconnu, le resultat sera NULL.
	 * 
	 * @return Le deuxième mot de la commande.
	 */
	public String getSecondWord() 
	{
		return secondWord;
	}

	/**
	 * Fonction boolean qui retourne vrai si la commande possede un second mot.
	 * 
	 * @return vrai si la commande possede un second mot
	 */
	public boolean hasSecondWord() 
	{
		return (secondWord != null);
	}

	/**
	 * Imposer le second mot de la commande
	 * 
	 * @param secondWord
	 * 			Le deuxieme mot de la commande
	 */
	public void setSecondWord(String secondWord)
    {
        this.secondWord = secondWord;
    }
	
	/**
	 * Methode abstraite qui sera implementee dans les sous classes, qui permet d'executer les commandes
	 * 
	 * @param player
	 * 			Le joueur du jeu
	 */
	public abstract void execute(Player player);
}
