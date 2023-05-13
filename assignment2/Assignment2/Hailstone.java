
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {

		// "K" counts number of process;
		int k = 0;

		int a = readInt("Enter the number: ");

		while (true) {
			if (a == 1) {
				break;
			}

			k++;

			/*
			 * if number is even, code makes it half if number is odd, code multiplies it by
			 * 3 and adds 1
			 */
			if (a % 2 == 0) {

				println(a + " is even, so i make it half: " + (a / 2));
				a = a / 2;

			} else {
				println(a + " is odd, so i make 3n + 1: " + (3 * a + 1));
				a = 3 * a + 1;

			}

		}
		println(" ");
		println("The process took " + k + " to reach 1");
	}
}
