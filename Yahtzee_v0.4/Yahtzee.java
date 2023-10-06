/**
 *	A classic game of Yahtzee on Java. The rules are shown when the
 * 	program starts.
 *
 *	@author	Charles Chang
 *	@since	October 2, 2023
 */

import java.util.*;

public class Yahtzee {
	public static void main (String[] args) {
		/* Initialize objects or object arrays */
		Yahtzee game = new Yahtzee();
		DiceGroup dg = new DiceGroup();
		YahtzeePlayer[] players = new YahtzeePlayer[2];
		/* Print Header */
		game.printHeader();
		/* Get Player Names */
		game.setPlayerNames(players);
		// Test
		group.rollDice();
		group.printDice();
		players[0].getScoreCard().changeScore(6, group);
	}
	
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
	
	/**
	 * Asks for player name for each player, and initializes each of their
	 * YahtzeePlayer and ScoreCard objects
	 * 
	 * @param players	the YahtzeePlayer object array
	 */
	public void setPlayerNames(YahtzeePlayer[] players) {
		for (int i = 0; i < 2; i++) {\
			/* Initiate YahtzeePlayer object in each spot of array */
			players[i] = new YahtzeePlayer();
			/* Initate scorecard of each YahtzeePlayer */
			players[i].getScoreCard().initiateScore();
			/* Get name */
			Scanner reader = new Scanner(System.in);
			System.out.print("Player " + (i + 1) + ", please enter your name - > ");
			String name = reader.nextLine();
			/* Set name + extra line */
			players[i].setName(name);
			System.out.println();
		}
	}
	
	/**
	 * Roll to decide first player
	 * 
	 * @param players	the YahtzeePlayer object array
	 * @param dg		the DiceGroup to roll
	 */
	public void rollForFirst (YahtzeePlayer[] players, DiceGroup[] dg) {
		/* Keep track of whether there is a tie, if there is a tie, the
		 * dice keep getting rolled until there isn't a tie */
		boolean tie = true;
		while (tie) {
			
			System.out.print("Let's see who will go first. " + players[0]
							+ ", please hit enter to roll the dice : "
		}
	}
}
