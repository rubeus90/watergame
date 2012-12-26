package pkg_Characters;

/**Cette classe gère les personnages non joueurs.
 * 
 * @author rubeus
 *
 */
public class Bots extends Player
{
	private boolean attaquable;
	
	
	public Bots(final String pNom, final String pGender, final boolean attaquable)
	{
		super(pNom, pGender);
		this.attaquable = attaquable; 
	}
	
	public boolean attaquable()
	{
		return attaquable;
	}
	
	
}
