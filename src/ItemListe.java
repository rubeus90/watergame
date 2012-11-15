import java.util.HashMap;


/**Cette classe sert à gérer les listes d'objets pour éviter la duplication des listes d'objets (les HahsMap) dans Player et dans Room
 * 
 * @author rubeus
 *
 */
public class ItemListe {

	private HashMap<String, Item> itemPlayer;
	private HashMap<String, Item> itemRoom;
	
	public ItemListe()
	{
		itemPlayer = new HashMap<String, Item>();
		itemRoom = new HashMap<String, Item>();
	}
	
	public HashMap<String, Item> getHashMapPlayer()
	{
		return itemPlayer;
	}
	
	public HashMap<String, Item> getHashMapRoom()
	{
		return itemRoom;
	}
	
	
}
