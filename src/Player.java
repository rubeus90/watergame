import java.util.List;
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
   private Room aCurrentRoom;
   private String aGender;
   private String aDescriptionPlayer;
   private ArrayList<Item> listeItem;
   
   public Player(final String pNom, final Room pCurrentRoom,final String pGender)
   {
       aNom = pNom;
       aCurrentRoom = pCurrentRoom;
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
       String description = "Tu t'appelles " + aNom + "." + "\n" + aDescriptionPlayer + "\n" + "Dans ton inventaire: " + "\n";
       
       for(Item items : listeItem)
       {
            description += items.getDescriptionItem() + "\n";
       }
       return description;
   }
   
//   public void take(String nomItem)
//   {
//       HashMap<String, Item> HashMapItem = getHashMap();
//       listeItem.add(HashMapItem.get(nomItem));
//   }
//   
//   public void drop(Item item)
//   {
//       HashMap HashMapItem = getHashMap();
//       listeItem.remove(HashMapItem.get(nomItem));
//   }
}
