
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
   
   public Player(final String pNom, final Room pCurrentRoom,final String pGender)
   {
       aNom = pNom;
       aCurrentRoom = pCurrentRoom;
       aGender = pGender;
       
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
       return("Tu t'appelles " + aNom + "." + "\n" + aDescriptionPlayer + "\n");
   }
   
   public void take(Item item)
   {
       
   }
}