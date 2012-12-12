import java.util.ArrayList;
import java.util.Random;


public class RoomRandomizer 
{
	private ArrayList<Room> rooms;
	
	public RoomRandomizer(final ArrayList<Room> pRooms)
	{
		rooms = pRooms;
	}
	
	public Room getRandomRoom()
	{
		Random room = new Random();
		return(rooms.get(room.nextInt(rooms.size()-1)));
	}

}
