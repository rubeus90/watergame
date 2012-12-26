package pkg_Dialogue;


import pgk_Game.GameEngine;

/**Cette classe gère l'aspect général des dialogues du jeu. Chaque dialogue est défini par le bot qui parle et la salle ou se passe
 * la conversation
 * 
 * @author rubeus
 *
 */
public abstract class Dialogue 
{
	private int etape;
	
	public Dialogue()
	{
		etape = 1;
	}
	
	public abstract void afficheDialogue(GameEngine engine)
	;
	
	public void suivant()
	{
		etape++;
	}
	
	public int getEtape()
	{
		return etape;
	}
	
	public void setEtape(int pEtape)
	{
		etape = pEtape;
	}
}
