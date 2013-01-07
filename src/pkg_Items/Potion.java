package pkg_Items;
/**
 * Cette classe gère les potions du jeu.
 * 
 * Les potions du jeu se composent de potion et de soin
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 * 
 */
public class Potion 
{
	private String nomPotion;

	/**
	 * Constructeur qui construit une potion du jeu (qui peut etre une potion ou un soin). La potion est 
	 * definie par son nom (Potion et Soin)
	 * 
	 * @param pNomPotion
	 * 			Nom de la potion
	 */
	public Potion(final String pNomPotion) 
	{
		nomPotion = pNomPotion;
	}

	/**
	 * Retourner le nom de la potion
	 * 
	 * @return le nom de la potion
	 */
	public String getNomPotion() 
	{
		return nomPotion;
	}
}
