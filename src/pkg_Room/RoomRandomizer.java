package pkg_Room;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe permet de retourner une salle aleatoirement parmis une ArrayList des salles du jeu
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class RoomRandomizer 
{
	private ArrayList<Room> rooms;
	
	/**
	 * Constructeur qui cree un RoomRandomizer qui prendre l'ArrayList des salles comme parametre
	 * 
	 * @param pRooms
	 * 			L'ArrayListe des salles
	 */
	public RoomRandomizer(final ArrayList<Room> pRooms)
	{
		rooms = pRooms;
	}
	
	/**
	 * Retourner une salle aleatoire parmis les salles du jeu
	 * @return
	 */
	public Room getRandomRoom()
	{
		Random room = new Random();
		return(rooms.get(room.nextInt(rooms.size()-1)));
	}

}
