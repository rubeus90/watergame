package pkg_Characters;


import java.util.ArrayList;
import java.util.Stack;
import java.util.Set;

import pkg_Game.GameEngine;
import pkg_Game.UserInterface;
import pkg_Items.Item;
import pkg_Items.ItemListe;
import pkg_Room.Room;


/**
 * Cette classe gère tous les aspects du joueur : son nom, la position actuelle,
 * les objets qu'il porte, etc...
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 */
public class Player {
	private String aNom;
	private String aGender;
	private Room aCurrentRoom;
	private String aDescriptionPlayer;
	private GameEngine engine;
	private UserInterface gui;
	private Stack<Room> salles;
	private ItemListe items;
	private int maxPoids;
	private int sante;
	private ArrayList<Room> beamerRoom;

	/**
	 * Constructeur de la classe. Un joueur est donc défini par son nom, son sexe et sa santé
	 * 
	 * @param pNom
	 * @param pGender
	 * @param pSante
	 */
	public Player(final String pNom, final String pGender, int pSante) 
	{
		aNom = pNom;
		aGender = pGender;
		sante = pSante;
		maxPoids = 100;

		if (aGender == "féminin") 
		{
			aDescriptionPlayer = "Tu es une guerrière redoutable. Derrière tes airs de petite fille de campagne, tu as l'intélligence et la rapidité que les adversaires qui te sous-estiment vont vite regretter.";
		}
		else if (aGender == "masculin") 
		{
			aDescriptionPlayer = "Tu es un guerrier qui est malgré ton apparence peu viril, dispose une force exceptionnelle et la capacité de t'adapter à la nature que tes adversaires devront avoir peur de toi.";
		}
		else
		{
			aDescriptionPlayer = "Tu es quelqu'un de bizarre qui ne veut pas choisir de sexe. Les développeurs de ce jeu ne jugent personne, mais ça fait bizarre quand même...";
		}

		salles = new Stack<Room>(); /*
									 * créer un Stack vide pour la méthode back,
									 * cette création est mise ici car si on la
									 * crée dans la méthode, lorsque l'on
									 * exécute la commande back dès le début du
									 * jeu, ça génère des exceptions
									 */

		items = new ItemListe();
		beamerRoom = new ArrayList<Room>();
	}

	/**
	 * Permettre d'augmenter la santé du joueur
	 * 
	 * @param nbr
	 */
	public void augmenteSante(final int nbr) {
		sante += nbr;
	}
	
	/**
	 * Pemettre de diminuer la santé du joueur
	 * 
	 * @param nbr
	 */
	public void diminueSante(final int nbr) {
		if (sante > 0) {
			sante -= nbr;
		}
	}

	/**
	 * Retourner la description du joueur
	 * 
	 * @return la description du joueur
	 */
	public String getDescriptionPlayer() {
		return aDescriptionPlayer;
	}

	/**
	 * Retourner le GameEngine du jeu
	 * 
	 * @return le GameEgine du jeu
	 */
	public GameEngine getGameEngine()
	{
		return engine;
	}
	
	/**
	 * Retourner l'interface du jeu
	 * 
	 * @return l'interface du jeu
	 */
	public UserInterface getGUI()
	{
		return gui;
	}

	/**
	 * Retourner un String qui décrit l'inventaire du joueur
	 * 
	 * @return un String qui décrit l'inventaire du joueur
	 */
	public String getInventaire() 
	{
		String inventaire = "Dans ton inventaire: " + "\n";

		if (!items.getHashMap().isEmpty()) 
		{
			Set<String> keys = items.getKeys();
			for (String nom : keys) 
			{
					Item item = items.getValue(nom);
					inventaire += item.getDescriptionItem() + " qui pèse " + item.getWeightItem() + " kg" + "\n";
			}
			
		} 
		else
			inventaire = "Il n'y a rien dans ton inventaire.";

		return inventaire;
	}

	/**
	 * Retourner l'ItemListe du joueur, qui comporte le HashMap des objets dans l'inventaire du joueur
	 * 
	 * @return l'ItemListe du joueur
	 */
	public ItemListe getItemListe()
	{
		return items;
	}

	/**
	 * Retourner une description complète du joueur
	 * 
	 * @return la description complète du joueur
	 */
	public String getLongDescriptionPlayer() 
	{
		String description = "Tu t'appelles " + aNom + "." + "\n" + aDescriptionPlayer + "\n";

		return description;
	}

	/**
	 * Retourner le poids maximal des objets que le joueur peut porter
	 * 
	 * @return le poids maximal de l'inventaire
	 */
	public int getMaxPoids()
	{
		return maxPoids;
	}

	/**
	 * Retourner le poids total de l'inventaire du joueur
	 * 
	 * @return le poids total de l'inventaire
	 */
	public int getPoidsInventaire() {
		Set<String> keys = items.getKeys();
		int poids = 0;
		for (String nom : keys) {
			Item item = items.getValue(nom);
			poids += item.getWeightItem();
		}
		return poids;
	}
	
	/**
	 * Retourner l'endroit actuel du joueur
	 * 
	 * @return la salle actuelle du joueur
	 */
	public Room getRoom() 
	{
		return aCurrentRoom;
	}

	/**
	 * Retourner la niveau de santé du joueur
	 * 
	 * @return la santé du joueur
	 */
	public int getSante() {
		return sante;
	}

	/**
	 * Retourner la pile de salle qui stocke les anciennes salles visitées par le joueur
	 * 	
	 * @return la pile des anciennes salles visitées
	 */
	public Stack<Room> getStackRoom()
	{
		return salles;
	}
	
	/**
	 * Imposer une salle comme la salle ou le joueur se trouve et ajouter cette salle dans la pile des salles visitées
	 * 
	 * @param pRoom
	 */
	public void setCurrentRoom(final Room pRoom) {
		aCurrentRoom = pRoom;
		salles.push(aCurrentRoom);

	}

	/**
	 * Imposer le GameEngine
	 * 
	 * @param gameEngine
	 */
	public void setGameEngine(final GameEngine gameEngine) {
		engine = gameEngine;
	}

	/**
	 * Imposer le poids maximal de l'inventaire du joueur en fonction de sa santé. Plus sa santé diminue, moins il peut porter
	 * les objets
	 */
	public void setMaxPoids() {
		if (sante >= 0 && sante < 20)
			maxPoids = 0;
		if (sante >= 20 && sante < 60)
			maxPoids = 70;
		if (sante >= 60 && sante < 80)
			maxPoids = 100;
		if (sante >= 80 && sante < 100)
			maxPoids = 140;
		if (sante >= 100)
			maxPoids = 160;
	}

	/**
	 * Imposer une salle comme la salle ou le joueur se trouve mais sans ajouter cette salle dans la pile des salles visitées
	 * 
	 * @param pRoom
	 */
	public void setRoom(Room pRoom)
	{
		aCurrentRoom = pRoom;
	}

	/**
	 * Imposer le niveau de santé du joueur
	 * 
	 * @param pSante
	 */
	public void setSante(int pSante)
	{
		sante = pSante;
	}
	
	/**
	 * Imposer l'UserInterface, l'interface graphique du jeu
	 * 
	 * @param userinterface
	 */
	public void setUserInterface(final UserInterface userinterface) 
	{
		gui = userinterface;
	}
	
	/**
	 * Retourner le beamerRoom, qui est une ArrayList qui a pour but de stocker la salle ou on charge le Beamer
	 * @return l'ArrayList qui stocke la salle ou le Beamer est chargé
	 */
	public ArrayList<Room> getBeamerRoom()
	{
		return beamerRoom;
	}
		
	/**
	 * Retourner le nom du joueur
	 * 
	 * @return le nom du joueur
	 */
	public String getNom()
	{
		return aNom;
	}
	
}
