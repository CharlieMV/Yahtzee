/**
 *	The scorecard is combined with the scoring itself, and displays players
 * 	going down, and displays score types going to the right. 
 *
 *	@author	Charles Chang
 *	@since	October 2, 2023
 */
public class YahtzeeScoreCard {
	
	private int[] scores = new int[13];
	
	/* Initiating array for score */
	public void initiateScore() {
		for (int i = 0; i < 13; i++) {
			scores[i] = 0;
		}
	}
	/**
	 *	Get a category score on the score card.
	 *	@param category		the category number (1 to 13)
	 *	@return				the score of that category
	 */
	public int getScore(int i) {
		return scores[i - 1];
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if (choice >= 1 & choice <= 5) numberScore(choice, dg);
		else if (choice == 7) threeOfAKind(dg);
		else if (choice == 8) fourOfAKind(dg);
		else if (choice == 9) fullHouse(dg);
		else if (choice == 10) smallStraight(dg);
		else if (choice == 11) largeStraight(dg);
		else if (choice == 12) chance(dg);
		else if (choice == 13) yahtzeeScore(dg);
		return true;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		for (int i = 0; i < 5; i++) {
			dg.getDie(i).getLastRollValue();
		}
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		int[] numbers = new int[6];
		for (int i = 0; i < numbers.length(); i++)
			numbers[i] = 0;
		for (int i = 0; i < 5; i++)
			numbers[dg.getDie(i).getLastRollValue()] ++;
		
	}
	
	public void fourOfAKind(DiceGroup dg) {}
	
	public void fullHouse(DiceGroup dg) {}
	
	public void smallStraight(DiceGroup dg) {}
	
	public void largeStraight(DiceGroup dg) {}
	
	public void chance(DiceGroup dg) {}
	
	public void yahtzeeScore(DiceGroup dg) {}

}
