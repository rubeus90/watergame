package pkg_Room;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

import pkg_Characters.Bots;
import pkg_Items.Beamer;
import pkg_Items.Pierre;
import pkg_Items.Item;
import pkg_Items.ItemListe;
import pkg_Items.Potion;

//import java.util.Iterator;

/**
 * Class Room - une salle ou un terrain dans le jeux d'aventure.
 * 
 * Cette classe fait parti du jeux " Water Games" "Water Games" est un jeux très
 * simple à prendre en main, c'est un jeux textuel.
 * 
 * Une salle représente un endroit dans le jeux. Chaque salle est connecté à une
 * autre grâce à des sorties. Les sorties sont libellé par les directions
 * suivantes: nord, sud, est, ouest, monter. Pour chaque direction saisie, le
 * jeux vous envoi vers la salle voisine ou vous renvoi null si aucune salle
 * voisine existe dans la direction voulu.
 * 
 */
public class Room 
{
	public String description;
	private HashMap<String, Room> exits;
	private ArrayList<Potion> potion;
	private String imageName;
	private ItemListe items;
	private String nomRoom;
	
	private HashMap<String, Bots> bots;

	/**
	 * Constructeur qui permet d'initialiser la description, l'image et le nom d'une salle.
	 * 
	 * @param description
	 *            La description de la salle.
	 * @param image
	 * 			L'image d'une salle
	 * @param pNomRoom
	 * 			Le nom de la salle
	 */
	public Room(String description, String image, String pNomRoom) 
	{
		this.description = description;
		exits = new HashMap<String, Room>();
		imageName = image;
		items = new ItemListe();
		potion = new ArrayList<Potion>();
		nomRoom = pNomRoom;
		
		bots = new HashMap<String, Bots>(); //un HashMap pour contenir les personnages non joueurs qui est dans la salle
	}

	/**
	 * Ajouter un personnage non joueur dans la salle (donc dans le HashMap)
	 * 
	 * @param nomBot
	 * 		Nom du personnage non joueur
	 * @param bot
	 * 		Le personnage non joueur
	 */
	public void addBot(String nomBot, Bots bot)
	{
		bots.put(nomBot, bot);
	}
	
	/**
	 * Supprimer un personnage non joueur de la salle (donc du HashMap)
	 * 
	 * @param nomBot
	 * 			Nom du personnage non joueur
	 */
	public void removeBot(String nomBot)
	{
		bots.remove(nomBot);
	}

	/**
	 * Ajouter la pierre magique dans la salle (donc dans le HashMap du ItemListe)
	 * 
	 * @param pierre
	 * 			La pierre magique EnderPearl
	 */
	public void addItem(Pierre pierre)
	{
		items.putItem("EnderPearl", pierre);
	}

	/**
	 * Ajouter un objet dans la salle (donc dans le HashMap du ItemListe)
	 */
	public void addItem(String nomItem, Item item) {
		items.putItem(nomItem, item);
	}
	
	/**
	 * Ajouter un Beamer dans la salle (donc dans le HashMap du ItemListe)
	 * 
	 * @param beamer
	 * 			Le Beamer
	 */
	public void addItem(Beamer beamer)
	{
		items.putItem("beamer", beamer);
	}

	/**
	 * Ajouter un potion dans la salle
	 * 
	 * @param pPotion
	 * 			La potion (potion ou soin)
	 */
	public void addPotion(final Potion pPotion) 
	{
		potion.add(pPotion);
	}

	/**
	 * Cette méthode est pour vérifier si une potion appartient à l'ArrayList
	 * des potions. 
	 * On compare le nom de potion passé en paramètre et tous les noms de potion dans l'ArrayList. 
	 * Si on trouve le meme nom, la potion est donc presente dans l'ArrayList
	 * 
	 * @param nomPotion
	 * 			Nom de la potion
	 * @return true si la potion est presente, false sinon
	 */
	public boolean containPotion(String nomPotion) 
	{
		boolean contain = false;

		for (int i = 0; i < potion.size(); i++) 
		{
			if (potion.get(i).getNomPotion() == nomPotion)
				contain = true;
		}

		return contain;
	}

	/**
	 * Retirer un objet de la salle (donc du HashMap du ItemListe)
	 */
	public void deleteItem(String nomItem) 
	{
		items.removeItem(nomItem);
	}
	
	/**
	 * Retourner l'ArrayList des potions de la salle
	 * 
	 * @return l'ArrayList des potions de la salle
	 */
	public ArrayList<Potion> getArrayList() 
	{
		return potion;
	}

	/**
	 * Fonction qui permet de retourné la description de la salle
	 * 
	 * @return la description de la salle
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * Définir les sorties possibles de la salle courante. Soit la direction mène
	 * à une salle, soit mène nulle part ( = NULL ).
	 * 
	 * @param direction
	 *            Une direction, par exemple nord, sud ...
	 */
	public Room getExit(String direction) 
	{
		return exits.get(direction);
	}

	/**
	 * Fonction qui permet d'énumerer les sorties possibles.
	 * 
	 * @return Les sorties disponibles
	 */
	public String getExitString() 
	{
		String exitString = "Les autres endroits : ";

		Set<String> keys = exits.keySet();
		for (String exit : keys)
			exitString += " " + "-" + exit + "-";
		return exitString;
	}

	/**
	 * Retourner le nom de l'image de la salle
	 * 
	 * @return le nom de l'image de la salle
	 */
	public String getImageName() 
	{
		return imageName;
	}

	/**
	 * Retourner l'ItemListe (qui contient une HashMap des objets de la salle)
	 * 
	 * @return l'ItemListe
	 */
	public ItemListe getItemListe() 
	{
		return items;
	}

	/**
	 * Retourner le nom, la description et le poids de tous les objets et les potions dans la
	 * salle
	 * 
	 * @return Nom, description et poids des objets
	 */
	public String getItemString() 
	{
		String itemString = "Regarde s'il y a des objets ici: ";
		
		if (!items.getHashMap().isEmpty())
		{
			Set<String> cles = items.getKeys();
			for (String nom : cles) 
			{
				if(nom != "beamer")
				{
					Item valeurs = items.getValue(nom);
					itemString += "\n" + valeurs.getDescriptionItem() + " qui pèse " + valeurs.toString() + " kg";
				}
			}
		}
		else
		{
			itemString = "Il n'y a pas d'objet ici";
		}
		

	
		/********** L'ArrayList des potions **********/
		itemString += "\n";
		for (int i = 0; i < potion.size(); i++) {
			itemString += "\n" + "Un(e) " + potion.get(i).getNomPotion();
		}
		/****************************************************************/

		return itemString;

	}

	/**
	 * Retourner une description plus detaillée de la salle
	 * 
	 * @return Description detaillee de la salle
	 */
	public String getLongDescription() 
	{
		return "Tu es " + description + "\n" + getExitString() + "\n" + "\n"+ getItemString() + "\n" + "\n" + getBotString();
	}

	/**
	 * Retourner le nom de la salle
	 * 
	 * @return le nom de la salle
	 */
	public String getNomRoom()
	{
		return nomRoom;
	}

	/**
	 * Retourner l'indexe de la potion correspondante au nom passe en parametre dans l'ArrayListe des potions
	 * 
	 * @param nomPotion
	 * @return l'indexe de la potion
	 */
	public int indexPotion(String nomPotion) 
	{
		int k = 0;
		for (int i = 0; i < potion.size(); i++) 
		{
			if (potion.get(i).getNomPotion() == nomPotion)
				k = i;
		}

		return k;
	}

	/**
	 * Retourner le nom d'une potion (Potion ou Soin)
	 * 
	 * @param pPotion
	 * 			Nom de la potion
	 */
	public void removePotion(final Potion pPotion) 
	{
		potion.remove(pPotion);
	}
	
	/**
	 * Défini les sorties possibles de la salle courante. Soit la direction mène
	 * à une salle, soit mène nulle part ( = NULL ).
	 * 
	 * @param direction
	 *            Une direction, par exemple nord, sud ...
	 * @param voisin
	 *            Salle voisine 
	 */
	public void setExit(String direction, Room voisin) 
	{
		exits.put(direction, voisin);
	}
	
	/**
	 * Retourner un String qui enumere tous les personnages non joueurs qui sont presents dans la salle
	 * 
	 * @return un String qui affiche le nom de tous les ennemis dans la salle
	 */
	public String getBotString()
	{
		String nomBots = "";
		Set<String> keys = bots.keySet();
		for(String nom : keys)
			nomBots += nom + "  ";
		
		if(nomBots != "")
			return "!!!! Fais attention, il y a d'autre(s) ennemi(s) dans cet endroit: " + nomBots + "!!!!";
		else 
			return "Il n'y a pas d'ennemi ici!";
	}
	
	/**
	 * Retourner l'ennemi present dans la salle
	 * 
	 * @return l'ennemi dans la salle
	 */
	public Bots getBot()
	{
		Bots bot = null;
		Set<String> keys = bots.keySet();
		for(String nom : keys)
		{
			if(nom != null)
				bot = bots.get(nom);
			else
				bot = null;
		}
		return bot;
	}
	
	/**
	 * Imposer l'image de la salle
	 * @param image
	 */
	public void setImage(String image)
	{
		imageName = image;
	}
	
	/**
	 * Verifier si dans une salle, le bot correspondant au nom passe en parametre est bien present.
	 * Retourner vrai si oui, false sinon
	 * 
	 * @param nomBot
	 * 			Nom de l'ennemi
	 * @return true si le bot est present dans la salle, false sinon
	 */
	public boolean containBot(String nomBot)
	{
		boolean contain = false;
		
		if(bots.containsKey(nomBot))
			contain = true;
		
		return contain;
	}
}
