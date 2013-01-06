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
	private static final long serialVersionUID = 1L;
	/**
	 * Classe qui contient le main et qui génère tout le jeu
	 * 
	 * @author NGUYEN Hong Ngoc
	 * @author PATOIS Thibault
	 */
	private static GameEngine engine;
	private static UserInterface gui;
	private static Player player;

	/**
	 * Créer le jeu et initialiser les attributs
	 */
	public Game()
	{
		String nom = JOptionPane.showInputDialog(null, "Entre ton nom:", "Nom du personnage", JOptionPane.QUESTION_MESSAGE);		
		String[] gender = {"masculin", "féminin", "indéterminé"};
		String sexe = (String)JOptionPane.showInputDialog(null, "Choisis ton sexe:", "Le sexe du personnage", JOptionPane.QUESTION_MESSAGE,
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
	
	/**
	 * Méthode main du jeu
	 */
	public static void main(String[] args) 
	{
		new Game();
	}
	
	/**
	 * Permet d'initialiser l'applet de jeu
	 */
	@Override 
	public void init()
    {
        super.init();
    }
}
