public class Game
{
	private static UserInterface gui;
	private static GameEngine engine;


    /**
     * Create the game and initialise its internal map.
     */
//    public Game() 
//    {
//		engine = new GameEngine();
//		gui = new UserInterface(engine);
//		engine.setGUI(gui);
//    }


public static void main(String[] args)
{
	engine = new GameEngine();
	gui = new UserInterface(engine);
	engine.setGUI(gui);
}
}
