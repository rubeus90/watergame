import java.util.HashMap;
import java.util.Stack;
//import java.util.List;
//import java.util.ArrayList;
import java.util.Set;

/**
 * Cette classe gère tous les aspects du joueur : son nom, la position actuelle,
 * les objets qu'il porte, etc...
 * 
 * @author Thibault & Ngocky
 * @version 1.0
 */
public class Player {
	private String aNom;
	private String aGender;
	private Room aCurrentRoom;
	private String aDescriptionPlayer;
	// private HashMap<String, Item> listeItem;
	private GameEngine engine;
	private UserInterface gui;
	private Stack<Room> salles;
	private ItemListe items;
	private int maxPoids;
	private int sante;

	public Player(final String pNom, final String pGender) {
		aNom = pNom;
		aGender = pGender;
		sante = 80;
		maxPoids = 100;

		// listeItem = new HashMap<String, Item>();

		if (aGender == "f") {
			aDescriptionPlayer = "Tu es une guerrière redoutable. Derrière tes airs de petite fille de campagne, tu as l'intélligence et la rapidité que les adversaires qui te sous-estiment vont vite regretter.";
		}
		if (aGender == "m") {
			aDescriptionPlayer = "Tu es un guerrier qui est malgré ton apparence peu viril, dispose une force exceptionnelle et la capacité de t'adapter à la nature que tes adversaires devront avoir peur de toi.";
		}

		salles = new Stack<Room>(); /*
									 * créer un Stack vide pour la méthode back,
									 * cette création est mise ici car si on la
									 * crée dans la méthode, lorsque l'on
									 * exécute la commande back dès le début du
									 * jeu, ça génère des exceptions
									 */

		items = new ItemListe();
	}

	public void setGameEngine(final GameEngine gameEngine) {
		engine = gameEngine;
	}

	public void setUserInterface(final UserInterface userinterface) {
		gui = userinterface;
	}

	public void setCurrentRoom(final Room pRoom) {
		aCurrentRoom = pRoom;
		salles.push(aCurrentRoom);

	}

	public Room getRoom() {
		return aCurrentRoom;
	}

	//
	// public void setName(final String pNom)
	// {
	// aNom = pNom;
	// }
	//
	// public void setGender(final String pGender)
	// {
	// aGender = pGender;
	// }

	public String getDescriptionPlayer() {
		return aDescriptionPlayer;
	}

	public String getLongDescriptionPlayer() {
		String description = "Tu t'appelles " + aNom + "." + "\n"
				+ aDescriptionPlayer + "\n" + "Santé : " + sante;

		return description;
	}

	/**
	 * Ajouter un objet dans l'inventaire du joueur. L'objet pris est défini par
	 * le 2ème mot de la commande Par exemple: take arc
	 * 
	 * @param command
	 */
	public void take(Command command) {
		String mot = command.getSecondWord();
		Item item = aCurrentRoom.getItemListe().getValue(mot);
		int poidsFuture = this.getPoidsInventaire() + item.getWeightItem();

		if (poidsFuture > maxPoids)
			gui.println("Ton sac est déjà trop lourd, tu ne peux pas prendre plus d'objet. Jette un autre objet sinon!");
		else {
			if (!aCurrentRoom.getItemListe().containsKey(mot))
				gui.println("Mais il n'y a pas de " + mot + " ici");
			else {
				// Item item = aCurrentRoom.getItemListe().getValue(mot);
				items.putItem(mot, item);
				aCurrentRoom.deleteItem(mot);
				gui.println("Tu as pris un(e) " + mot);
			}
		}

	}

	/**
	 * Retirer un objet de l'inventaire du joueur. L'objet retiré est défini par
	 * le 2ème mot de la commande Par exemple: drop arc
	 * 
	 * @param command
	 */
	public void drop(Command command) {
		String mot = command.getSecondWord();

		if (!items.containsKey(mot))
			gui.println("Tu n'as pas de " + mot);
		else {
			Item item = items.getValue(mot);
			items.removeItem(mot);
			aCurrentRoom.addItem(mot, item);
		}
	}

	public String getInventaire() {
		String inventaire = "Dans ton inventaire: " + "\n";

		if (!items.getHashMap().isEmpty()) {
			Set<String> keys = items.getKeys();
			for (String nom : keys) {
				Item item = items.getValue(nom);
				inventaire += item.getDescriptionItem() + " qui pèse "
						+ item.getWeightItem() + "\n";
			}
		} else
			inventaire = "Il n'y a rien dans ton inventaire.";

		return inventaire;
	}

	public int getPoidsInventaire() {
		Set<String> keys = items.getKeys();
		int poids = 0;
		for (String nom : keys) {
			Item item = items.getValue(nom);
			poids += item.getWeightItem();
		}
		return poids;
	}

	// public void printInventaire()
	// {
	// gui.println(getInventaire() + "\n" + "Le poids total de ton inventaire: "
	// + getPoidsInventaire());
	// }

	/**
	 * Procédure pour passer d'une salle à une autre. Si il n'y a pas de sortie,
	 * entré un nouvelle direction. Sinon affiché un message d'erreur.
	 */
	public void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			gui.println("Aller ou exactement, soit un peu plus précis!");
		}

		// Stocker la salle actuelle dans le stack (pour la méthode back)
		salles.push(aCurrentRoom);

		String direction = command.getSecondWord();

		Room nextRoom = aCurrentRoom.getExit(direction);

		if (nextRoom == null) {
			gui.println("Tu ne peux pas aller par là, soit il n'y a rien ici, soit le chemin que tu viens de prendre est sens unique");
		} else {
			aCurrentRoom = nextRoom;
			// player.setCurrentRoom(nextRoom);
			engine.printLocationInfo();

			if (aCurrentRoom.getImageName() != null) {
				gui.showImage(aCurrentRoom.getImageName());
			}
		}
	}

	// /**Renvoi ,pour le moment, un message au joueur l'indiquant qu'il a déjà
	// mangé.
	// *
	// */
	// public void eat()
	// {
	// gui.println("Tu as déjà mangé, tu n'as plus faim");
	// }

	/**
	 * Implémenter la commande Back pour retourner dans la salle précédente. On
	 * stocke les salles visitée précédemment dans une Stack, à chaque fois on
	 * veut revenir sur notre pas, on utilise la commande "pop" qui prendre la
	 * denière valeur de la Stack (donc la dernière salle visitée) et qui retire
	 * cette salle de la Stack. Quand la Stack est vide, on est revenu au début
	 * du jeu.
	 */
	public void back(Command command) {
		if (command.hasSecondWord()) {
			gui.println("Tu ne peux pas revenir ou tu veux, c'est pas la fête ici!");
		} else {
			if (salles.empty() == true)
				gui.println("Tu es revenu au début du jeu!");
			else {
				aCurrentRoom = salles.pop();
				engine.printLocationInfo();

				if (aCurrentRoom.getImageName() != null)
					gui.showImage(aCurrentRoom.getImageName());

			}
		}

	}

	/**
	 * Redonner les informations de la salle et les sorties disponibles
	 */
	public void look() {
		gui.println(aCurrentRoom.getLongDescription());
	}

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

	public int getSante() {
		return sante;
	}

	public void augmenteSante(final int nbr) {
		sante += nbr;
	}

	public void diminueSante(final int nbr) {
		if (sante > 0) {
			sante -= nbr;
		}
	}

	public void boire(Command command) {
		String mot = command.getSecondWord();

		if (mot.equals("potion")) {
			if (aCurrentRoom.containPotion("Potion")) {
				augmenteSante(10);
				int index = aCurrentRoom.indexPotion("Potion");
				aCurrentRoom.getArrayList().remove(index);
			} else {
				gui.println("Il n'y a pas (ou plus) de cette potion dans cette salle");
			}

		}

		else if (mot.equals("soin")) {
			if (aCurrentRoom.containPotion("Soin")) {
				augmenteSante(10);
				int index = aCurrentRoom.indexPotion("Soin");
				aCurrentRoom.getArrayList().remove(index);
			} else {
				gui.println("Il n'y a pas (ou plus) de ce soin dans cette salle");
			}
		}

		else
			gui.println("Il n'y a pas cette potion!");
	}

}
