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
 * Chaque salle est connecté à une autre grâce à des sorties.  Les sorties sont libellé par les directions suivantes north north, east, south, west. 
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

    /**
     * Constructeur qui permet d'initialiser la description d'une salle. "
     * Exemple d'utilisation : 
     * "Une forêt" , "une belle cuisine"
     * @param  description La description de la salle.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

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
            exitString += " " + exit;
        return exitString;
    }
    
    /**Retourner une description plus detaillee de la salle
     * @return Description detaillee de la salle
     */
    public String getLongDescription()
    {
    	return "Tu es " + description + ".\n" + getExitString();
    }
    
}
