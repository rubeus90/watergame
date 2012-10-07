import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;


/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    public String description;
//     public Room northExit;
//     public Room southExit;
//     public Room eastExit;
//     public Room westExit;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction, Room voisin) 
    {
        exits.put(direction, voisin);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * @return la description de la salle
     */
    public String getDescription()
    {
        return description;
    }

    
    /*Exo 7.9 : keySet() : returns a Set view of all the keys contained in this map (a reecire en francais dans le rapport)
     *
     * Methode getExitString() : dafuq I don't know what I'm coding.... A l'aide Thibault! Je te laisse cette explication
     *
     */
    /**@return Les sorties disponibles**/
    public String getExitString()
    {
        String exitString = "Les autres endroits : ";
        
        Set<String> keys = exits.keySet();
        for(String exit : keys)
            exitString += " " + exit;
        return exitString;
    }
    
}
