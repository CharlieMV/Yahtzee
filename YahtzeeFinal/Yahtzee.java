/**
 *	A classic game of Yahtzee on Java. The rules are shown when the
 * 	program starts. This program supports 2 players only.
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
		/* Roll for who goes first */
		game.rollForFirst(players, dg);
		game.printWholeCard(players);
		/* Run game */
		for (int round = 1; round <= 13; round++) {
			System.out.println("Round " + round + " of 13 rounds. \n");
			game.playRound(players, dg, game);
		}
		/* After 13 rounds, sum up the scores for the users */
		System.out.println(players[0].getName() + ", you scored " +
				players[0].getScoreCard().sumScore() + " points. \n");
		System.out.println(players[1].getName() + ", you scored " +
				players[1].getScoreCard().sumScore() + " points. \n");
	}
	
	/**
	 * Print the header to the game, including the rules.
	 */
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
		for (int i = 0; i < 2; i++) {
			/* Initiate YahtzeePlayer object in each spot of array */
			players[i] = new YahtzeePlayer();
			/* Initate scorecard of each YahtzeePlayer */
			players[i].getScoreCard().initiateScore();
			/* Get name */
			Scanner reader = new Scanner(System.in);
			System.out.print("Player " + (i + 1) + 
									", please enter your name - > ");
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
	public void rollForFirst (YahtzeePlayer[] players, DiceGroup dg) {
		/* Keep track of whether there is a tie, if there is a tie, the
		 * dice keep getting rolled until there isn't a tie */
		boolean tie = true;
		while (tie) {
			/* Keep track of each player's dice sum */
			int[] sum = new int[2];
			/* Roll once for each player*/
			for (int i = 0; i < 2; i++) {
				/* Print line and create scanner*/
				Scanner input = new Scanner(System.in);
				System.out.print("Let's see who will go first. " + 
							players[i].getName() + 
							", please hit enter to roll the dice -> ");
				/* Scan input, but ignore input*/
				String filler = input.nextLine();
				/* Roll the dice */
				dg.rollDice();
				dg.printDice();
				/* Change sum*/
				sum[i] = dg.getTotal();
			}
			if (sum[1] == sum[0]) {
				/* Say that there is a tie */
				System.out.println("Whoops, we have a tie (both rolled " +
				sum[0] + " ). Looks like we'll have to try that again . . .");
			}
			else if (sum[1] > sum[0]) {
				/* Print who got what score and who won*/
				System.out.print(players[0].getName() + ", you rolled a "
											+ "sum of " + sum[0] + ", ");
				System.out.println(players[1].getName() + ", you rolled a "
											+ "sum of " + sum[1] + ".");
				System.out.println(players[1].getName() + ", since your " +
									"sum was higher, you'll roll first.");
				/* Switch order so 1 is now 0, meaning they go first*/
				YahtzeePlayer temp = players[0];
				players[0] = players[1];
				players[1] = temp;
				/* Not a tie */
				tie = false;
			}
			else if (sum[0] > sum[1]) {
				/* Print who got what score and who won*/
				System.out.print(players[0].getName() + ", you rolled a "
											+ "sum of " + sum[0] + ", ");
				System.out.println(players[1].getName() + ", you rolled a "
											+ "sum of " + sum[1] + ".");
				System.out.println(players[0].getName() + ", since your " +
									" sum was higher, you'll roll first.");
				/* Not a tie */
				tie = false;
			}
		}
	}
	
	/**
	 * Print the whole scorecard for game
	 * 
	 * @param players
	 */
	public void printWholeCard(YahtzeePlayer players[]) {
		/* Print Card */
		players[0].getScoreCard().printCardHeader();
		players[0].getScoreCard().printPlayerScore(players[0]);
		players[1].getScoreCard().printPlayerScore(players[1]);
		players[0].getScoreCard().printCardFooter();
		System.out.println("\n");
	}
	
	/**
	 * Play out a round where each player takes a turn. This method shoud
	 * be run 13 times: one fore each scoring category.
	 * 
	 * @param players	YahtzeePlayer objects
	 * @param dg		DiceGroup object
	 * @param games		Yahtzee game obj to decompose and call other methods
	 */
	public void playRound(YahtzeePlayer players[], DiceGroup dg, Yahtzee game) {
		/* Run once for each player */
		for (int i = 0; i < 2; i++) {
			game.playerRollDice(players, dg, i);
			game.printWholeCard(players);
			game.playerScoreDie(players, dg, i);
			game.printWholeCard(players);
		}
	}
	
	/**
	 * Have the player roll up to 3 times, and get rawRawHolds and end rolls
	 */
	public void playerRollDice(YahtzeePlayer players[], DiceGroup dg, int playerTurn) {
		/* Roll the dice */
		System.out.print(players[playerTurn].getName() + ", it's your "
				+ "turn to play. Please hit enter to roll the dice -> ");
		/* Scanner */
		Scanner input = new Scanner(System.in);
		String filler = input.nextLine();
		/* Roll the dice then hold selection until turn is ended, or
		 * the die are rolled 3 times */
		boolean isRollingEnded = false;
		int dieRollCount = 0;
		/* Roll die the first time */
		dg.rollDice();
		dg.printDice();
		while (!isRollingEnded) {
			/* Increase dieRollCount */
			dieRollCount++;
			/* Get rawHold */
			System.out.print("Which di(c)e would you like to " +
				" keep? Enter the values you'd like to 'hold' " +
				"without spaces. For examples, if you'd like to " +
				"'hold' die 1, 2, and 5, enter 125 (enter -1 if " +
				"you'd like to end the turn) -> ");
			boolean isValidHold = false;
			String rawHold = "";
			int holdValue = 0;
			while (!isValidHold) {
				try {
					rawHold = input.nextLine();
					if (rawHold.length() != 0) {
						holdValue = Integer.parseInt(rawHold);
						/* Reading rawHold values */
						if (holdValue == - 1) {
							isValidHold = isRollingEnded = true;
						/* Check that rawHold length is greater than 0 and 
						 * less than 5. Also check that values are between
						 * 1 and 5 inclusive*/
						} else if (rawHold.length() < 5 && rawHold.length() 
																	> 0){
							/* Flip flop the temp */
							boolean isValidTemp = true;
							for (int j = 0; j < rawHold.length(); j++) {
								/* Chekc if each char is valid*/
								int digitAtJ = (int)rawHold.charAt(j) - 48;
								if (digitAtJ <= 0 || digitAtJ >= 6) {
									isValidTemp = false;
								}
							}
							/* Hold is valid if the temp is valid */
							if (isValidTemp) isValidHold = true;
							/* Tell user that the rawHold is invalid */
							else System.out.print("Invalid hold, please " +
													"try again - > ");
						}
						/* Tell user that the rawHold is invalid */
						else System.out.print("Invalid hold, please " +
													"try again - > ");
					/* If rawHold is blank, the hold is valid */
					} else if (rawHold.length() == 0) isValidHold = true;
				}
				catch (NumberFormatException e) {
					System.out.println("Please enter an integer");
				}
			}
			/* Dont roll a fourth time */
			if (dieRollCount == 2)
				isRollingEnded = true;
			/* Empty rawhold will roll all die */ 
			if (rawHold.length() == 0) {
				dg.rollDice();
				dg.printDice();
			/* Roll the die without rawHold */
			} else if (holdValue != -1) {
				dg.rollDice(rawHold);
				dg.printDice();
			} 
		}
	}

	/**
	 * Score 1 round of dice rolling
	 * 
	 * @param players[]	YahtzeePlayer object array
	 * @param dg		diceGroup object
	 */
	
	public void playerScoreDie(YahtzeePlayer players[], DiceGroup dg, int playerTurn) {
		/* Keep prompting player for scoring category until a valid one
		 * is selected. It is valid if it is between 1 and 13 inclusive,
		 * and if the category has not been selected before */
		boolean isValidCategory = false;
		while (!isValidCategory) {
			try {
				/* Get input */
				Scanner input = new Scanner(System.in);
				System.out.print(players[playerTurn].getName() + ", now you need " +
					"to make a choice. Pick a valid integer from the " +
					"list above -> ");
				/* Make sure the category is an int between 1 and 13 inclusive */
				int category = input.nextInt();
				/* If the category is taken, the if will be false, and 
				 * changeScore does nothing. If the category is open,
				 * changeScore increases the score and outputs true, 
				 * validating the if statement and ending the loop */
				if (category >= 1 && category <= 13 && 
				players[playerTurn].getScoreCard().changeScore(category, dg)) {
					isValidCategory = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer.");
			}
		}
	}
}
