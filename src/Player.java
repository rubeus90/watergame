import java.util.HashMap;
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
   
   public Player(final String pNom, final String pGender)
   {
       aNom = pNom;
       aCurrentRoom = null;
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
   }
   
   public void setCurrentRoom(final Room pRoom)
   {
	   aCurrentRoom = pRoom;
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
}
