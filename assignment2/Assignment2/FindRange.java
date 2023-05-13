
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	private static final int SENTINEL = 0;

	// returns max number;
	private int max(int a, int b) {

		if (a > b) {
			return a;
		}
		return b;

	}

	// returns min number
	private int min(int a, int b) {
		if (a < b) {
			return a;

		}
		return b;
	}

	public void run() {

		int max = 0;
		int min = 0;
		int counter = 0;

		/*
		 * if user enters SENTINEL first, counter will be 0; 
		 * else counter will increase by 1 every time user enters number;
		 */

		while (true) {

			/*
			 * 1. counter = 0, code writes "No numbers" and ends working; 
			 * 2. counter = 1,code writes first number in "max" and "min" boxes; 
			 * 2.1 after max=min="First entered number",
			 * code compares every new number to max and min
			 * (and saves minimal and maximal numbers)
			 * 
			 * when user enters SENTINEL "while loop" breaks
			 * and code writes MAXIMAL and MINIMAL numbers;
			 * 
			 */

			int num = readInt("Enter Number: ");
			if (num == SENTINEL) {
				if (counter == 0) {
					println("No numbers");
				}
				break;
			}

			counter += 1;

			if (counter == 1) {
				max = num;
				min = num;
			}
			max = max(max, num);
			min = min(min, num);

		}
		// if counter != 0, code writes minimal and maximal numbers;
		if (counter != 0) {
			println("");
			println("There is " + counter + " number.");
			println("Minimum is: " + min);
			println("Maximum is: " + max);

		}

	}

}
