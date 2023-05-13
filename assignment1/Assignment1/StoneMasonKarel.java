
/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while (frontIsClear()) {
			fillVerticalLine(); // Fills Vertical Line With Beeper (Only 1 Beeper - Each Block)
			moveBack(); // Goes From Top Of Pillars To Start
			moveToNextPillar(); // Goes To Next Pillar

		}
		fillVerticalLine(); // fills last pillar
	}

	//Karel starts working facing east
	//When karel finishes job,he is facing north
	private void fillVerticalLine() {
		turnLeft();
		if (noBeepersPresent()) {
			putBeeper();
		}
		while (frontIsClear()) {
			move();
			if (noBeepersPresent()) {
				putBeeper();
			}
		}
	}

	//When karel finishes job,
	private void moveBack() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}

	//Moves with only 4 block
	private void moveToNextPillar() {
		move();
		move();
		move();
		move();
	}
}
