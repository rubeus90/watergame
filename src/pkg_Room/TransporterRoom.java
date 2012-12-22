package pkg_Room;
import java.util.ArrayList;

/**Cette classe gere la transporter room
 * 
 * @author rubeus
 *
 */
public class TransporterRoom extends Room

{
	private RoomRandomizer random;
	
	public TransporterRoom(final String description, final String image, final String nom, final ArrayList<Room> rooms)
	{
		super(description, image, nom);
		random = new RoomRandomizer(rooms);
	}
	
	@Override
	public Room getExit(String direction)
	{
		return random.getRandomRoom();
	}
}
