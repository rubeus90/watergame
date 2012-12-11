import javax.swing.JApplet;

public class Game extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7803629994015778818L;
	private static GameEngine engine;
	private static UserInterface gui;
	private static Player player;

	/**
	 * Create the game and initialise its internal map.
	 */
	public static void main(String[] args) {
		player = new Player("Julie", "f");
		engine = new GameEngine(player);
		gui = new UserInterface(engine);
		player.setGameEngine(engine);
		player.setUserInterface(gui);
		engine.setGUI(gui);
	}
}
