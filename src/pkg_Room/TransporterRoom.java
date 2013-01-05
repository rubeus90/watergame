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
	private boolean testMode;
	private Room testModeRoom;
	
	public TransporterRoom(final String description, final String image, final String nom, final ArrayList<Room> rooms)
	{
		super(description, image, nom);
		random = new RoomRandomizer(rooms);
	}
	
	@Override
	public Room getExit(String direction)
	{
		if(testMode = false)
		{
			return random.getRandomRoom();
		}
		else
		{
			return testModeRoom;
		}
	}
	
	public void setTestMode(boolean mode)
	{
		testMode = mode;
	}
	
	public void setTestModeRoom(Room room)
	{
		testModeRoom = room;
	}
}
