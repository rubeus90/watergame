/**
 * Cette classe fait parti du jeux " Water Games" "Water Games" est un jeux très
 * simple à prendre en main, c'est un jeux textuel.
 * 
 * This class holds information about a command that was issued by the user. A
 * command currently consists of two strings: a command word and a second word
 * (for example, if the command was "take map", then the two strings obviously
 * are "take" and "map").
 * 
 * Le The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 * 
 * Si la commande a seulement une mot, alors le second mot est null.
 * 
 */

public class Command 
{
	private CommandWord commandWord;
	private String secondWord;

	/**
	 * Instance les commandes. First and second word must be supplied, but
	 * either one (or both) can be null.
	 * 
	 * @param firstWord
	 *            Le premier mot de la commande. Null si la commande n'est pas
	 *            reconnu.
	 * @param secondWord
	 *            Second mot de la commande.
	 */

	public Command(CommandWord commandWord, String secondWord) 
	{
		this.commandWord = commandWord;
		this.secondWord = secondWord;
	}

	/**
	 * Retourne le premier mot de la commande. Si la commande n'a pas été
	 * reconnu, le résultat sera NULL.
	 * 
	 * @return Le premier mot de la commande.
	 */
	public CommandWord getCommandWord() 
	{		
		return commandWord;
	}

	public String getStringCommandWord() 
	{
		return commandWord.toString();
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
	 * Fonction boolean qui retourne vrai si la commande n'a pas été reconnu.
	 * 
	 * @return vrai si la commande n'a pas été reconnu.
	 */
	public boolean isUnknown() 
	{
		return (commandWord == CommandWord.UNKNOWN);
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

	@Override
	public String toString() 
	{
		return "" + commandWord + " " + secondWord;
	}
}
