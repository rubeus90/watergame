package pkg_Room;
import java.util.ArrayList;

/**
 * Cette classe gere la TransporterRoom, une salle qui, hormis mode teste, teleporte le joueur dans une salle aleatoire
 * sur la carte
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class TransporterRoom extends Room
{
	private RoomRandomizer random;
	private boolean testMode;
	private Room testModeRoom;
	
	/**
	 * Constructeur qui construit le TransporterRoom, qui herite da la classe Room, et qui est definie
	 * en plus par une ArrayList des salles du jeu
	 * 
	 * @param description
	 * 			La description de la salle
	 * @param image
	 * 			L'image de la salle
	 * @param nom
	 * 			Le nom de la salle
	 * @param rooms
	 * 			L'ArrayList des salles du jeu
	 */
	public TransporterRoom(final String description, final String image, final String nom, final ArrayList<Room> rooms)
	{
		super(description, image, nom);
		random = new RoomRandomizer(rooms);
		testMode = false;
	}
	
	/**
	 * Redefinir la methode getExit() de la classe Room. 
	 * 
	 * Ici, la methode permet en mode teste (avec la commande "aleas + nom de la salle) de retourner toujours la salle 
	 * correspondant au nom demande. Hors mode teste, elle retourne une salle aleatoire parmis les salles du jeu
	 */
	@Override
	public Room getExit(String direction)
	{
		if(testMode == false)
		{
			return random.getRandomRoom();
		}
		else 
			return testModeRoom;
		
	}
	
	/**
	 * Imposer le mode teste
	 * 
	 * @param mode
	 * 			True si on veut activer le mode teste, false si on veut le desactiver
	 */
	public void setTestMode(boolean mode)
	{
		testMode = mode;
	}
	
	/**
	 * Imposer la salle qui sera retournee dans le mode teste
	 * 
	 * @param room
	 * 			La salle qui est retournee dans le mode teste
	 */
	public void setTestModeRoom(Room room)
	{
		testModeRoom = room;
	}
}
