import javax.swing.JApplet;

public class Game extends JApplet
{
	private static GameEngine engine;
	private static UserInterface gui;
	/**
     * Create the game and initialise its internal map.
     */
public static void main(String[] args)
{
	engine = new GameEngine();
	gui = new UserInterface(engine);
	engine.setGUI(gui);
}
}
