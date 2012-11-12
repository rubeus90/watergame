import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

/**
 * Class Room - une salle ou un terrain dans le jeux d'aventure.
 *
 * Cette classe fait parti du jeux " Water Games" 
 * "Water Games" est un jeux très simple à prendre en main, c'est un jeux textuel.  
 *
 * Une salle représente un endroit dans le jeux.
 * Chaque salle est connecté à une autre grâce à des sorties.  Les sorties sont libellé par les directions suivantes: nord, sud, est, ouest, monter. 
 * Pour chaque direction saisie, le jeux vous envoi vers la salle voisine ou vous renvoi null si aucune salle voisine existe dans la direction voulu.
 * 
 */
public class Room 
{
    public String description;
    //     public Room northExit;
    //     public Room southExit;
    //     public Room eastExit;
    //     public Room westExit;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    
    //     private HashMap<String, Room> room;
    private String imageName;
    

    /**
     * Constructeur qui permet d'initialiser la description d'une salle. "
     * Exemple d'utilisation : 
     * "Une forêt" , "une belle cuisine"
     * @param  description La description de la salle.
     */
    public Room(String description, String image) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
        imageName = image;
    }

    //     /**HashMap pour contenir les rooms, chaque room est lié à son nom
    //      * 
    //      */
    //     public void setRoom(String nameRoom, Room currentRoom)
    //     {
    //         room.put(nameRoom, currentRoom);
    //     }

        
    /**
    * Défini les sorties possibles de la salle courante. 
    * Soit la direction mène à une salle, soit mène nulle part ( = NULL ).
    * @param direction Une direction, par exemple north,east ...
    * @param voisin Salle voisine ??
    */
    public void setExit(String direction, Room voisin) 
    {
    exits.put(direction, voisin);
    }

    /**
    * Défini les sorties possibles de la salle courante. 
    * Soit la direction mène à une salle, soit mène nulle part ( = NULL ).
    * @param direction Une direction, par exemple north,east ...
    */
    public Room getExit(String direction)
    {
    return exits.get(direction);
    }

    /**
    * Fonction qui permet de retourné la description de la salle
    * @return la description de la salle
    */
    public String getDescription()
    {
    return description;
    }

    /*Exo 7.9 : keySet() : returns a Set view of all the keys contained in this map (a reecire en francais dans le rapport)
    *
    * Methode getExitString() : dafuq I don't know what I'm coding.... A l'aide Thibault! Je te laisse cette explication
    *
    */
    /**
    * Fonction qui permet d'énumerer les sorties possibles. 
    * @return Les sorties disponibles
    */
    public String getExitString()
    {
    String exitString = "Les autres endroits : ";

    Set<String> keys = exits.keySet();
    for(String exit : keys)
    exitString += " " + "-"+ exit +"-";
    return exitString;
    }

    /**Retourner une description plus detaillée de la salle
    * @return Description detaillee de la salle
    */
    public String getLongDescription()
    {
    return "Tu es " + description + ".\n" + getExitString() + "\n" + getItemString() + "\n";
    }

    public String getImageName()
    {
    return imageName;
    }

    
    /**Ajouter un objet dans un HashMap
    */
    public void addItem(String nomItem, Item item)
    {
        items.put(nomItem, item);   
    }

    
    /**Retourner le nom, la description et le poids de tous les objets dans la salle
     * 
     * @return Nom, description et poids des objets
     */
    public String getItemString()
    {
        Set<String> cles = items.keySet();
             
        String itemString = "Regarde s'il y a des objets ici: ";
        
        for(String nom : cles)
        {
            Item valeurs = items.get(nom);
            
            if(valeurs == null)
                itemString += "Il n'y a pas d'objet ici";
            else
                itemString += "\n" + valeurs.getDescriptionItem() + " qui pèse " + valeurs.getWeightString() + " kg";
        }
                 
            return itemString;
        
    }
   
    
    
    
}
