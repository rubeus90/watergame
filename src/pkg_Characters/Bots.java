package pkg_Characters;

/**Cette classe gère les personnages non joueurs.
 * 
 * @author rubeus
 *
 */
public class Bots extends Player
{
	private boolean attaquable;
	
	
	public Bots(final String pNom, final String pGender, final int pSante, final boolean attaquable)
	{
		super(pNom, pGender, pSante);
		this.attaquable = attaquable; 
	}
	
	public boolean attaquable()
	{
		return attaquable;
	}
	
	
}
