package pkg_Dialogue;

import pkg_Game.GameEngine;

/**
 * Cette classe gère l'aspect général des dialogues du jeu. 
 * Chaque dialogue est défini par le bot qui parle et la salle ou se passe la conversation
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public abstract class Dialogue 
{
	private int etape;
	
	/**
	 * Constructeur qui creer un dialogue et initialiser les etapes du dialogue
	 */
	public Dialogue()
	{
		etape = 1;
	}
	
	/**
	 * Une methode abstraite qui permet d'implementer les dialogues
	 * 
	 * @param engine
	 */
	public abstract void afficheDialogue(GameEngine engine)
	;
	
	/**
	 * Methode qui permet de passer d'une etape du dialogue a celle suivante
	 */
	public void suivant()
	{
		etape++;
	}
	
	/**
	 * Retourner l'etape actuelle du dialogue
	 * 
	 * @return l'etape actuelle du dialogue
	 */
	public int getEtape()
	{
		return etape;
	}
	
	/**
	 * Imposer l'etape du dialogue
	 * 
	 * @param pEtape
	 */
	public void setEtape(int pEtape)
	{
		etape = pEtape;
	}
}
