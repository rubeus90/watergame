/**Cette classe gère l'aspect général des dialogues du jeu
 * 
 * @author rubeus
 *
 */
public abstract class Dialogue 
{
	private Bots bot;
	private Room room;
	
	public Dialogue(final Bots bot, final Room room)
	{
		this.bot = bot;
		this.room = room;
	}
	
	public void afficheDialogue()
	{
	}
}
