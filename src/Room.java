import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
//import java.util.Iterator;

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
//    private HashMap<String, Item> items;
    private ArrayList<Potion> potion;
    
    //     private HashMap<String, Room> room;
    private String imageName;        
    private ItemListe items;

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
//        items = new HashMap<String, Item>();
        imageName = image;
        items = new ItemListe();
        potion = new ArrayList<Potion>();
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

    /*Exo 7.9 : keySet() : retourner dans un Set les clés de la HashMap des sorties
    *
    * Methode getExitString() : On crée un Set des clés de la HashMap des sorties. Pour chaque clé, on ajoute à la variable 
    * locale exitString (un String) le nom de la sortie, et on retourne la valeur de exitString
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
        items.putItem(nomItem, item);   
    }
    
    /**Retirer un objet du HashMap
     */
    public void deleteItem(String nomItem)
    {
        items.removeItem(nomItem);
    }
    
    public ItemListe getItemListe()
    {
        return items;
    }
       
    
    /**Retourner le nom, la description et le poids de tous les objets dans la salle
     * 
     * @return Nom, description et poids des objets
     */
    public String getItemString()
    {
        Set<String> cles = items.getKeys();
             
        String itemString = "Regarde s'il y a des objets ici: ";
        
        for(String nom : cles)
        {
            Item valeurs = items.getValue(nom);
            
            if(valeurs == null)
                itemString += "Il n'y a pas d'objet ici";
            else
                itemString += "\n" + valeurs.getDescriptionItem() + " qui pèse " + valeurs.toString() + " kg";
        }
            
        
        /**********Juste pour tester l'ArrayList des potions**********/
        for(int i=0; i<potion.size();i++)
        {
        	itemString += "\n" + potion.get(i).getNomPotion();        			
        }
        /****************************************************************/
        
        
            return itemString;
        
    }
   
    public void addPotion(final Potion pPotion)
    {
    	potion.add(pPotion);
    }
    
    public void removePotion(final Potion pPotion)
    {
    	potion.remove(pPotion);
    }
    
    public ArrayList<Potion> getArrayList()
    {
    	return potion;
    }
    
    /**Cette méthode est pour vérifier si une potion appartient à l'ArrayList des potions. Manques de meilleure idée, la méthode ici 
     * fait simplement une boucle de comparaison entre le nom de potion passé en paramètre et tous les noms de potion dans l'ArrayList.
     * 
     * C'est une méthode a priori moche donc à refaire si on a assez de temps.
     * 
     * @param nomPotion
     * @return
     */
    public boolean containPotion(String nomPotion)
    {
       	boolean contain = false;
    
    	for(int i=0; i<potion.size();i++)
    	{
    		if(potion.get(i).getNomPotion()==nomPotion)
    			contain = true;
    	}
    	
    	return contain;
    }
    
    /**Méthode aussi moche que la précédente pour retourner l'index de la potion voulue
     * 
     * @param nomPotion
     * @return
     */
    public int indexPotion(String nomPotion)
    {
    	int k = 0;
    	for(int i=0; i<potion.size();i++)
    	{
    		if(potion.get(i).getNomPotion()==nomPotion)
    			k = i;
    	}
    	
       	return k;
    }
}
