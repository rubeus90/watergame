package pkg_Items;
/**
 * Cette classe gère la/les potions du jeu. Pour l'instant il y a une seule
 * potion disponible.
 * 
 * @author rubeus
 * 
 */
public class Potion {
	private String nomPotion;
//	private int forcePotion;

	public Potion(final String pNomPotion) {
		nomPotion = pNomPotion;
	}

//	public Potion(final String pNomPotion, final int pForcePotion) {
//		nomPotion = pNomPotion;
//		forcePotion = pForcePotion;
//	}

	public String getNomPotion() {
		return nomPotion;
	}

//	public int getForcePotion() {
//		return forcePotion;
//	}
}
