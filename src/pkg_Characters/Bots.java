package pkg_Characters;

/**Cette classe gère les personnages non joueurs.
 * 
 * @author rubeus
 *
 */
public class Bots extends Player
{
	private int force;
	private boolean isBoss;
	
	
	public Bots(final String pNom, final String pGender, final int pForce, final boolean pIsBoss)
	{
		super(pNom, pGender);
		this.force = pForce;
		this.isBoss = pIsBoss; 
	}
	
	public int getForce()
	{
		return force;
	}
	
	public boolean getIsBoss()
	{
		return isBoss;
	}
	
	
}
