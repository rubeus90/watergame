package pkg_Characters;

/**Cette classe gere les personnages non joueurs du jeu.
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class Bots extends Player
{
	private boolean attaquable;	
	
	/**
	 * Constructeur du personnage non joueur. Un personnage non joueur est defini par son nom, (le sexe n'est pas utilise), sa sante et
	 * par le parametre qui defini si on peut l'attaquer ou pas 
	 * 
	 * @param pNom
	 * @param pGender
	 * @param pSante
	 * @param attaquable
	 */
	public Bots(final String pNom, final String pGender, final int pSante, final boolean attaquable)
	{
		super(pNom, pGender, pSante);
		this.attaquable = attaquable; 
	}
	
	/**
	 * Retourner la valeur du boolean qui definit si le bot est attaquable ou non
	 * 
	 * @return si le bot est attaquable ou non
	 */
	public boolean attaquable()
	{
		return attaquable;
	}
	
	
}
