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
public class ItemListe {

	private HashMap<String, Item> items;

	public ItemListe() {
		items = new HashMap<String, Item>();
	}

	public HashMap<String, Item> getHashMap() {
		return items;
	}

	public void putItem(String nomItem, Item item) {
		items.put(nomItem, item);
	}

	public void removeItem(String nomItem) {
		items.remove(nomItem);
	}

	public Set<String> getKeys() {
		return items.keySet();
	}

	public Item getValue(String key) {
		return items.get(key);
	}

	public boolean containsKey(String mot) {
		return items.containsKey(mot);
	}
}
