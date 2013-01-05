package pkg_Dialogue;


import pkg_Characters.Bots;
import pkg_Game.GameEngine;
import pkg_Items.Item;
import pkg_Room.Room;

public class DialogueCreeper2 extends Dialogue
{
	public DialogueCreeper2()
	{
		super();
	}
	
	public void afficheDialogue(GameEngine engine)
	{
		if(engine.gameResetted())
		{
			super.setEtape(1);
			engine.setResetGame();
		}
		
		engine.getGUI().showDialogue(1);
				
		if(engine.getPlayer().getItemListe().containsKey("papier"))
		{
			if(super.getEtape() == 10)
			{
				super.setEtape(1);
			}
			
			switch(super.getEtape())
			{		
				case 1:
				{
					engine.getGUI().println("Creeper : Alors tu revoilà! Je peux voir la feuille dans ta main?");
					super.suivant();
					break;
				}
				case 2:
				{
					engine.getGUI().println("Creeper : RedStone? Cet inscrit me fait penser à un mythe de cette île. Les histoires disent qu'il" +
							"existe une salle secrète sur l'île, ou on peut trouver une hallebarde tellement puissante que l'on peut" +
							"vraincre n'importe quel ennemi!");
					super.suivant();
					break;
				}
				case 3:
				{
					engine.getGUI().println("Creeper : Mais pour trouver cette salle secrète, il faut que l'on aille jusqu'au temple, " +
							"c'est là-bas que l'on trouvera le chemin");
					super.suivant();
					break;
				}
				case 4:
				{
					engine.getGUI().println("Creeper : On ne peut pas y aller ensemble, c'est bien trop dangeureux. On se retrouve là-bas, " +
							"fais bien attention à toi!");
					super.suivant();
					break;
				}
				default: 
				{
					engine.getPlayer().getRoom().removeBot("Creeper");
					engine.getArrayListRoom().get(1).addBot("Creeper", new Bots("Creeper", null, 80, false));
					engine.getGUI().closeDialogue();				
				}
			}
		}
		else
		{
			if(engine.getArrayListRoom().get(6).containBot("Enderman"))
			{
				switch(super.getEtape())
				{
					case 1:
					{
						engine.getGUI().println("Creeper : Tiens tu revoilà! Tu n'as pas croisé d'autres ennemis sur le chemin j'espère?");
						super.suivant();
						break;
					}
					case 2:
					{
						engine.getGUI().println("Creeper : Quoi? Tu as croisé Enderman mais tu l'as pas tué? C'est un ennemi puissante," +
								"c'est trop dangereux de le laisser en vie!");
						super.suivant();
						break;
					}
					case 3:
					{
						engine.getGUI().println("Creeper : De plus, l'épée que je t'ai donné est suffisamment puissante pour l'achever! Il faut" +
								"que tu y retourne impérativement pour finir la vie d'Enderman. Je t'attends ici!");
						super.suivant();
						break;
					}
					case 0:
					{
						engine.getGUI().println("Creeper : Tu attends quoi encore? Vas-y battre Enderman!");
						super.setEtape(4);
						break;
					}
					default: 
					{
						engine.getGUI().closeDialogue();		
						super.setEtape(0);
					}
				}
			}
			else
			{
				if(super.getEtape() == 0)
				{
					super.setEtape(1);
				}
				switch(super.getEtape())
				{
					case 1:
					{
						engine.getGUI().println("Creeper : Tiens tu revoilà! ");
						super.suivant();
						break;
					}
					case 2:
					{
						engine.getGUI().println("Creeper : Je vois que tu as réussi à tuer Enderman! C'est très bien!");
						super.suivant();
						break;
					}
					case 3:
					{
						engine.getGUI().println("Creeper : Mais j'ai entendu que Enderman a sur lui un papier qui contient une information" +
								"très importante, il doit être encore à l'endroit que tu as tué Enderman," +
								"vas le chercher, ça peut nous aider!");
						super.suivant();
						break;
					}
					case 10:
					{
						engine.getGUI().println("Creeper : Tu attends quoi encore? Vas chercher le papier!");
						super.setEtape(4);
						break;
					}
					default: 
					{
						engine.getGUI().closeDialogue();		
						super.setEtape(10);
					}
				}
			}
		}
	}
	
}
