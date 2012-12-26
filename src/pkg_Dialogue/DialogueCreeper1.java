package pkg_Dialogue;


import pgk_Game.GameEngine;
import pkg_Characters.Bots;
import pkg_Room.Room;

public class DialogueCreeper1 extends Dialogue
{
	public DialogueCreeper1(final Bots creeper, final Room foret)
	{
		super(creeper, foret);
	}
	
	public void afficheDialogue(GameEngine engine)
	{
		engine.getGUI().println("Hey hey, toi ! Oui toi là !  Je suis bloqué dans un trou juste là. Vient m'aider !" + "\n");
		
		if(this.help(true))
			engine.getGUI().println("Quoi tu n'as pas de corde ? Tu comptais m'aider comment ? Tu es élastigirl peut-être ? Je plaisante, t'inquiètes pas. Je pense que tu pourra trouver une corde en haut du PIC. Si tu m'aides à sortir de là, je te donnerai quelques conseils pour survivre sur cette île" + "\n");
		else
		{
//			engine.getGUI().println("Tchiiiiiiiiiiii...... BOOOOOOOM!!!!" + "Creeper a explosé dans la face, tu es mort!!!! Faillait accepter d'aider Creeper, espèce de méchant sans coeur!!!!" + "\n");
			engine.getGUI().createGameOver("creeper");
		}
	}
	
	public boolean help(final boolean choix)
	{
		return choix;			
	}
}
