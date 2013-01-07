package pkg_Items;

/**
 * Cette classe crée un téléporteur qui herite de la classe Item.
 * 
 * Le téléporteur est caractérisé par son état d'activation. Quand le joueur récupère 
 * le téléporteur, cet objet est désactivé. Il doit le charger en récupérant un objet dans une autre salle. A chaque 
 * utilisation, il est déchargé.
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class Pierre extends Item
{
	private boolean activated;
	
	/**
	 * Constructeur qui construit la pierre magique 
	 */
	public Pierre()
	{
		super("La pierre EnderPearl", 30);
		activated = false;
	}
	
	/**
	 * Retourner si oui ou non la pierre est chargee
	 * 
	 * @return true si la pierre est chargee, false sinon
	 */
	public boolean getValueActivation()
	{
		return activated;
	}
	
	/**
	 * Imposer l'etat d'activation de la pierre magique
	 * 
	 * @param etat
	 * 			Etat d'activation de la pierre (elle est chargee ou non)
	 */
	public void setActivation(boolean etat)
	{
		activated = etat;
	}

}
