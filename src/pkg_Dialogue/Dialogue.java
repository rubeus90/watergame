package pkg_Dialogue;


import pgk_Game.GameEngine;
import pkg_Characters.Bots;
import pkg_Room.Room;

/**Cette classe gère l'aspect général des dialogues du jeu. Chaque dialogue est défini par le bot qui parle et la salle ou se passe
 * la conversation
 * 
 * @author rubeus
 *
 */
public abstract class Dialogue 
{
	public Dialogue()
	{}
	
	public abstract void afficheDialogue(GameEngine engine)
	;
}
