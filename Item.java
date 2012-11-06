import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe gère les objets dans les salles
 * Chaque objet est défini par sa description et son poids
 */
public class Item
{
    private int aPoids;
    private String aDescription;
    
    
    public Item(String pDescription, int pPoids)
    {
        this.aDescription = pDescription;
        this.aPoids = pPoids;      
    }
    
    /**Retourner la description de l'objet
     * @return Description de l'objet
     */
    public String getDescriptionItem()
    {
        return(this.aDescription);
    }
    
    /**Retorner le poids de l'objet
     * @return Poids de l'objet
     */
    public int getWeightItem()
    {
        return(this.aPoids);
    }
    
    /**Redéfinir la méthode toString()
     */
    public String toString()
    {
        return("" + this.getWeightItem());
    }
    
    /**Retourner le poids de l'objet sous forme de String
     * @return Poids de l'objet sous forme String
     */
    public String getWeightString()
    {
        return(toString());
    }
}

