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
			scores[i] = -1;
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
		/* Return false if there is anything other than a -1 for the score */
		if (scores[choice - 1] == -1) return false;
		/*	Run scoring method for choice*/
		if (choice >= 1 & choice <= 6) numberScore(choice, dg);
		else if (choice == 7) threeOfAKind(dg);
		else if (choice == 8) fourOfAKind(dg);
		else if (choice == 9) fullHouse(dg);
		else if (choice == 10) smallStraight(dg);
		else if (choice == 11) largeStraight(dg);
		else if (choice == 12) chance(dg);
		else if (choice == 13) yahtzeeScore(dg);
		/* Return the value */
		return true;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		/* Turn score from -1 to 0 */
		scores[choice - 1] = 0;
		/* Scan dices one by one */
		for (int i = 0; i < 5; i++) {
			/* If dice i is same as choice (1, 2, 3, 4, 5, 6) */
			if (dg.getDie(i).getLastRollValue() == choice)
				/* Add score for each matching dice*/
				scores[choice - 1] += choice;
		}
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		/* Turn score from -1 to 0 */
		scores[choice - 1] = 0;
		/* Array to keep track of how many of each number is rolled */
		int[] numberCount = new int[6];
		/* All values of array to 0 */
		for (int i = 0; i < 6; i++)
			numberCount[i] = 0;
		/* Increase count of each number rolled */
		for (int i = 0; i < 6; i++)
			numbers[dg.getDie(i + 1).getLastRollValue()] ++;
		/* Scan count array for any values >= 3 */
		for (int i = 0; i < 6; i++) {
			/* Add score if value found */
			if (numberCount[i] >= 3)
				scores[choice - 1] += 3 * i;
		}
	}
	
	public void fourOfAKind(DiceGroup dg) {
		/* Turn score from -1 to 0 */
		scores[choice - 1] = 0;
		/* Array to keep track of how many of each number is rolled */
		int[] numberCount = new int[6];
		/* All values of array to 0 */
		for (int i = 0; i < 6; i++)
			numberCount[i] = 0;
		/* Increase count of each number rolled */
		for (int i = 0; i < 6; i++)
			numbers[dg.getDie(i + 1).getLastRollValue()] ++;
		/* Scan count array for any values >= 4*/
		for (int i = 0; i < 6; i++) {
			/* Add score if value found */
			if (numberCount[i] >= 4)
				scores[choice - 1] += 4 * i;
		}
	}
	
	public void fullHouse(DiceGroup dg) {
		/* Turn score from -1 to 0 */
		scores[choice - 1] = 0;
		/* Array to keep track of how many of each number is rolled */
		int[] numberCount = new int[6];
		boolean hasPair = false;
		boolean hasSet = false;
		/* All values of array to 0 */
		for (int i = 0; i < 6; i++)
			numberCount[i] = 0;
		/* Increase count of each number rolled */
		for (int i = 0; i < 6; i++)
			numbers[dg.getDie(i + 1).getLastRollValue()] ++;
		/* Scan count array for any values == 2 and == 3*/
		for (int i = 0; i < 6; i++) {
			/* Scan for pair */
			if (numberCount[i] == 2)
				hasPair = true;
			/* Scan for set */
			if (numberCount[i] == 3)
				hasSet = true;
		}
		if (hasPair && hasSet) scores[choice - 1] += 25;
	}
	
	public void smallStraight(DiceGroup dg) {
		/* Turn score from -1 to 0 */
		scores[choice - 1] = 0;
		/* Array to keep track of how many of each number is rolled */
		int[] numberCount = new int[6];
		boolean isStraight = false;
		/* All values of array to 0 */
		for (int i = 0; i < 6; i++)
			numberCount[i] = 0;
		/* Increase count of each number rolled */
		for (int i = 0; i < 6; i++)
			numbers[dg.getDie(i + 1).getLastRollValue()] ++;
		/* Scan the score from 1-3 to check for 1-4, 2-5, and 3-6 
		 * small straights*/
		for (int i = 1; i <= 3; i++) {
			/* See if there are any zeroes in count for values of the straight */
			boolean hasZero = false;
			for (int j = 0; j < 4;  j++)
				if (numberCount[i + j] == 0) hasZero = true;
			/* There is a straight if there are no zeroes for the
			 * consecutice numbers */
			if (!hasZero) isStraight = true;
		}
		/* If there is a (small) straight, add 30 points */
		if (isStraight) scores[choice - 1] += 30;
	}
	
	public void largeStraight(DiceGroup dg) {
		/* Turn score from -1 to 0 */
		scores[choice - 1] = 0;
		/* Array to keep track of how many of each number is rolled */
		int[] numberCount = new int[6];
		boolean isStraight = false;
		/* All values of array to 0 */
		for (int i = 0; i < ; i++)
			numberCount[i] = 0;
		/* Increase count of each number rolled */
		for (int i = 0; i < 6; i++)
			numbers[dg.getDie(i + 1).getLastRollValue()] ++;
		/* Scan the score from 1-2 to check for 1-5, and 2-6 
		 * large straights*/
		for (int i = 1; i <= 2; i++) {
			/* See if there are any zeroes in count for values of the straight */
			boolean hasZero = false;
			for (int j = 0; j < 5;  j++)
				if (numberCount[i + j] == 0) hasZero = true;
			/* There is a straight if there are no zeroes for the
			 * consecutice numbers */
			if (!hasZero) isStraight = true;
		}
		/* If there is a (large) straight, add 40 points */
		if (isStraight) scores[choice - 1] += 40;
	}
	
	public void chance(DiceGroup dg) {
		/* Turn score from -1 to 0 */
		scores[choice - 1] = 0;
		
	}
	
	public void yahtzeeScore(DiceGroup dg) {}

}
