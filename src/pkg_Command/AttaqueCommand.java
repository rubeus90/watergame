package pkg_Command;

import pkg_Characters.Bots;
import pkg_Characters.Player;
import pkg_Items.Item;
import pkg_Room.Room;

/**
 * Cette classe s'occupe de la commande "attaque" du jeu
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class AttaqueCommand extends Command
{
	/**
	 * Constructeur qui permet de creer une commande Attaque
	 */
	public AttaqueCommand()
	{}
		
	/**
	 * Quand on tape la commande attaque, la sante du joueur et la sante de l'ennemi diminue en fonction de l'arme que le 
	 * joueur possede.
	 * Dans le cas que l'ennemi soit Creeper, qui doit etre un allié, le joueur perd tout de suite s'il l'attaque.
	 * Quand tous les ennemis sont tues, on affiche la fenetre de victoire du jeu.
	 */
	public void execute(Player player)
	{
		Bots bot = player.getRoom().getBot();
		int degat = 0;
		int perte = 0;
		
		
		if(player.getItemListe().getHashMap().isEmpty())
		{
			player.getGUI().println("Tu n'as pas d'arme, tu ne peux pas attaquer");
		}
		else
		{
			//Selon l'arme que le joueur porte, le dégât de la commande Attaque sera différente			
			if(player.getItemListe().containsKey("epee") && !player.getItemListe().containsKey("hallebarde"))
			{
				degat = 100;
				perte = 15;
			}
			else if(player.getItemListe().containsKey("hallebarde"))
			{
				degat = 100;
				perte = 7;
			}
			else
			{
				degat = 100;
				perte = 25;
			}
		
			if(bot != null)
			{
				if(bot.attaquable()) //c'est-à-dire si le bot n'est pas Creeper (qui s'explose quand on l'attaque)
				{
					//Tant que la santé du bot n'est pas nul, on peut l'attaquer. 
					if(bot.getSante() - degat >= 0) //le "- degat " est là pour éviter de retrouver le bot avec une santé négative
					{
						bot.diminueSante(degat);
						player.diminueSante(perte);
						player.getGUI().resetTextPanel();
						player.getGUI().println("La santé de " + bot.getNom() + ": " + bot.getSante());

						//Prevenir le joueur s'il attaque un bot trop puissant sans avoir l'arme requise
						if((!player.getItemListe().containsKey("epee") && !player.getItemListe().containsKey("hallebarde")) || 
								(!player.getItemListe().containsKey("hallebarde") && bot.getNom() == "Blaze"))
						{
							player.getGUI().println("\n" + "\n" + "Méfis-toi! Peut-être cet ennemis est trop fort pour toi. Il te faut d'autres armes plus puissantes. Si tu te sens pas capable de le vaincre mainteant, enfuis-toi avant qu'il ne te tue!!!!");
						}
					}
					else
					{
						if(bot.getNom() == "Enderman")
						{
							player.getRoom().removeBot(bot.getNom()); //supprimer ce bot mort de la salle
							player.getRoom().setImage("images/vallee.png");
							player.getGUI().showImage(player.getRoom().getImageName());
							player.getRoom().getItemListe().putItem("papier", new Item("Un papier avec inscrit 'RedStone' ", 0));
							player.getGUI().resetTextPanel();
							player.getGUI().println("Tu as vaincu Enderman" + "\n" + "D'ailleurs il a fait tombé un papier, ça peut être intéressant de le prendre, on ne sait jamais");
						}
						
						else if(bot.getNom() == "Blaze")
						{
							player.getRoom().removeBot(bot.getNom()); //supprimer ce bot mort de la salle		
							player.getRoom().setImage("images/plage.png");
							player.getGUI().showImage(player.getRoom().getImageName());
							player.getGUI().resetTextPanel();
							player.getGUI().println("Tu as vaincu Blaze");						
						}
						
						//S'il n'y pas plus de bot dans toutes les salles, alors le joueur a gagné, on affiche la fenêtre Victory
						int hasBot = 0;
						for(int i=0; i < player.getGameEngine().getArrayListRoom().size() ; i++)
						{
							Room room = player.getGameEngine().getArrayListRoom().get(i);
							if(room.getBot() != null)
								hasBot++;									
						}								
						if(hasBot == 0)
							player.getGUI().createWinGame();
					}
				}				
				else
				{
					player.getGUI().createGameOver("creeper"); //si le bot est Creeper, alors le joueur perd
				}
			}		
			else
			{
				player.getGUI().println("Il n'y a pas d'ennemis pour attaquer ici"); 
			}
		
		
		}
		
	}
}
