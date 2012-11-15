import java.util.HashMap;
import java.util.Stack;
//import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe gère tous les aspects du joueur : son nom, la position actuelle, les objets qu'il porte, etc...
 * 
 * @author Thibault & Ngocky
 * @version 1.0
 */
public class Player
{
   private String aNom;   
   private String aGender;
   private Room aCurrentRoom;
   private String aDescriptionPlayer;
   private ArrayList<Item> listeItem;
   private GameEngine engine;
   private UserInterface gui;
   private Stack<Room> salles;
   
   public Player(final String pNom, final String pGender)
   {
	   aNom = pNom;
       aGender = pGender;
       
       listeItem = new ArrayList<Item>();
       
       if(aGender == "f")
       {
       aDescriptionPlayer = "Tu es une guerrière redoutable. Derrière tes airs de petite fille de campagne, tu as l'intélligence et la rapidité que les adversaires qui te sous-estiment vont vite regretter.";
       }
       if(aGender == "m")
       {
       aDescriptionPlayer = "Tu es un guerrier qui est malgré ton apparence peu viril, dispose une force exceptionnelle et la capacité de t'adapter à la nature que tes adversaires devront avoir peur de toi.";
       }
       
       salles = new Stack<Room>();  /*créer un Stack vide pour la méthode back, cette création est mise ici car si on la crée dans la méthode, lorsque 
       l'on exécute la commande back dès le début du jeu, ça génère des exceptions*/
   }
   
      
   public void setGameEngine(final GameEngine gameEngine)
   {
	   engine = gameEngine;
   }
   
   public void setUserInterface(final UserInterface userinterface)
   {
	   gui = userinterface;
   }
   
   public void setCurrentRoom(final Room pRoom)
   {
	   aCurrentRoom = pRoom;
	   salles.push(aCurrentRoom);
	   
   }
   
   public Room getRoom()
   {
	   return aCurrentRoom;
   }
     
//      
//    public void setName(final String pNom)
//    {
//        aNom = pNom;
//    }
//    
//    public void setGender(final String pGender)
//    {
//        aGender = pGender;
//    }
   
   public String getDescriptionPlayer()
   {
       return aDescriptionPlayer;
   }
   
   public String getLongDescriptionPlayer()
   {
       String description = "Tu t'appelles " + aNom + "." + "\n" + aDescriptionPlayer + "\n";
       
       return description;
   }
   
   /**Ajouter un objet dans l'inventaire du joueur. L'objet pris est défini par le 2ème mot de la commande
    * Par exemple: take arc
    * 
    * @param command
    */
   public void take(Command command)
   {
	   if(command.hasSecondWord())
	   {
		   String mot = command.getSecondWord();
		   HashMap<String, Item> hashmap = aCurrentRoom.getHahsMap();
		   Item item = hashmap.get(mot);
		   
		   listeItem.add(item);		   
	   }
   }
   
   /**Retirer un objet de l'inventaire du joueur. L'objet retiré est défini par le 2ème mot de la commande
    * Par exemple: drop arc
    * 
    * @param command
    */
   public void drop(Command command)
   {
	   if(command.hasSecondWord())
	   {
		   String mot = command.getSecondWord();
		   HashMap<String, Item> hashmap = aCurrentRoom.getHahsMap();
		   Item item = hashmap.get(mot);
		   
		   listeItem.remove(item);		   
	   }
   }
   
   
   public String getInventaire()
   {
	   String inventaire = "Dans ton inventaire: " + "\n";
   
	   if(!listeItem.isEmpty())
	   {
		   for(Item items : listeItem)
		   {
			   inventaire += items.getDescriptionItem() + "\n";
		   }
	   }
	   else
		   inventaire = "Il n'y a rien dans ton inventaire.";
		   
	   return inventaire;
   }
   
   
   
   /** 
    * Procédure pour passer d'une salle à une autre. Si il n'y a pas de sortie, entré un nouvelle direction. 
    * Sinon affiché un message d'erreur.
    */
   public void goRoom(Command command) 
   {
       if(!command.hasSecondWord()) 
       {
           gui.println("Aller ou exactement, soit un peu plus précis!");
       }
       
       //Stocker la salle actuelle dans le stack (pour la méthode back)
       salles.push(aCurrentRoom);
       
       String direction = command.getSecondWord();

       Room nextRoom = aCurrentRoom.getExit(direction);

       if (nextRoom == null) 
       {
           gui.println("Tu arrives au bord de l'île, tu vas tomber dans l'eau si tu continues!");
       }
       else 
       {         
           aCurrentRoom = nextRoom;
//           player.setCurrentRoom(nextRoom);
           engine.printLocationInfo();
           
           if(aCurrentRoom.getImageName() != null)
           {
               gui.showImage(aCurrentRoom.getImageName());
           }
       }
   }
   
   /**Renvoi ,pour le moment, un message au joueur l'indiquant qu'il a déjà mangé.
    * 
    */
   public void eat()
   {
       gui.println("Tu as déjà mangé, tu n'as plus faim");
   }
   
   /**Implémenter la commande Back pour retourner dans la salle précédente.
    * On stocke les salles visitée précédemment dans une Stack, à chaque fois on veut revenir sur notre pas, on utilise la commande "pop" qui prendre
    * la denière valeur de la Stack (donc la dernière salle visitée) et qui retire cette salle de la Stack.
    * Quand la Stack est vide, on est revenu au début du jeu.
    */
   public void back(Command command)
   {
       if(command.hasSecondWord())
       {
           gui.println("Tu ne peux pas revenir ou tu veux, c'est pas la fête ici!");
       }
       else
       {
           if(salles.empty() == true)
               gui.println("Tu es revenu au début du jeu!");
           else
           {
           aCurrentRoom = salles.pop();
           engine.printLocationInfo();
           
           if(aCurrentRoom.getImageName() != null)
               gui.showImage(aCurrentRoom.getImageName());
               
           }
       }         
           
   }
   
   /**Redonner les informations de la salle et les sorties disponibles
    */
   public void look()
   {
       gui.println(aCurrentRoom.getLongDescription());
   }
   
}
