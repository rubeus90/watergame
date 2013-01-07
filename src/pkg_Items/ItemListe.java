package pkg_Items;
import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe sert à gérer les listes d'objets pour éviter la duplication des
 * listes d'objets (les HahsMap) dans Player et dans Room
 * 
 * @author rubeus
 * 
 */
public class ItemListe 
{
	private HashMap<String, Item> items;

	/**
	 * Constructeur qui cree une ItemListe et instancier le HashMap des objets
	 */
	public ItemListe() 
	{
		items = new HashMap<String, Item>();
	}

	/**
	 * Retourner le HashMap des objets
	 * 
	 * @return le HashMap des objets
	 */
	public HashMap<String, Item> getHashMap() 
	{
		return items;
	}

	/**
	 * Ajouter un objet dans le HashMap
	 * 
	 * @param nomItem
	 * @param item
	 */
	public void putItem(String nomItem, Item item) 
	{
		items.put(nomItem, item);
	}

	/**
	 * Supprimer un objet du HashMap
	 * 
	 * @param nomItem
	 */
	public void removeItem(String nomItem) 
	{
		items.remove(nomItem);
	}

	/**
	 * Retourner un ensemble des cles du HashMap, qui correspond aux noms des objets dans le HashMap
	 * 
	 * @return ensemble de noms des objets
	 */
	public Set<String> getKeys() 
	{
		return items.keySet();
	}

	/**
	 * Retourner 
	 * @param key
	 * @return
	 */
	public Item getValue(String key) 
	{
		return items.get(key);
	}

	public boolean containsKey(String mot) 
	{
		return items.containsKey(mot);
	}
}
