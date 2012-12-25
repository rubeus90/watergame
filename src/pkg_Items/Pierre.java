package pkg_Items;

/**
 * Cette classe crée un téléporteur. Le téléporteur est caractérisé par son état d'activation. Quand le joueur récupère 
 * le téléporteur, cet objet est désactivé. Il doit le charger en récupérant un objet dans une autre salle. A chaque 
 * utilisation, il est déchargé.
 * 
 * @author Ngoc & Thibault
 *
 */
public class Pierre extends Item
{
	private boolean activated;
	
	public Pierre()
	{
		super("La pierre EnderPearl", 0);
		activated = false;
	}
	
//	public void chargeBeamer()
//	{
//		activated = true;
//	}
//	
//	public void useBeamer()
//	{
//		activated = false;
//	}
	
	public boolean getValueActivation()
	{
		return activated;
	}
	
	public void setActivation(boolean etat)
	{
		activated = etat;
	}

}
