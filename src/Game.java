import javax.swing.JApplet;
import javax.swing.JOptionPane;

import pkg_Characters.Player;
import pkg_Game.GameEngine;
import pkg_Game.UserInterface;

public class Game extends JApplet 
{
	/**
	 * 
	 */
	private static GameEngine engine;
	private static UserInterface gui;
	private static Player player;

	/**
	 * Create the game and initialise its internal map.
	 */	
	public Game()
	{
		JOptionPane menu = new JOptionPane();
	    String nom = menu.showInputDialog(null, "Entre ton nom:", "Nom du personnage", JOptionPane.QUESTION_MESSAGE);		
		String[] gender = {"masculin", "féminin", "indéterminé"};
		String sexe = (String)menu.showInputDialog(null, "Choisis ton sexe:", "Le sexe du personnage", JOptionPane.QUESTION_MESSAGE,
	      null,
	      gender,
	      gender[1]);	
		
		player = new Player(nom, sexe, 80);
		engine = new GameEngine(player);
		gui = new UserInterface(engine);
		player.setGameEngine(engine);
		player.setUserInterface(gui);
		engine.setGUI(gui);
	}
	
	public static void main(String[] args) 
	{
		new Game();
	}
	
	@Override 
	public void init()
    {
        super.init();
    }
}
