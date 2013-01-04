import javax.swing.JApplet;
import javax.swing.JOptionPane;

import pgk_Game.GameEngine;
import pgk_Game.UserInterface;
import pkg_Characters.Player;

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
	public static void main(String[] args) 
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
}
