
/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerBoardKarel extends SuperKarel {

	/*
	 * how algorithm works - karel fills first line like chessboard while front i clear (1-0-1-0); 
	 * after karel fills he returns back and checks if beeper is in the first box;
	 * 1(if box is clear)-karel goes up and starts filling from first box;
	 * 2(if box is not clear)-karel goes up,next moves with one block and fills line again;
	 * but  when karel goes up and wall appears,he turns north and fills the line;
	 */
	public void run() {
		fillOneLine();
		moveBack();
		while (leftIsClear()) {
			checkBeeperMoveUpAndFill();
			moveBack();

		}
	}
	
	//When karel finishes the work he looks in the direction in which he has starts
	private void fillOneLine() {
		putBeeper();
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}
	
	//When karel finishes the work he looks in the direction in which he has starts
	private void moveBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnAround();
	}

	private void checkBeeperMoveUpAndFill() {
		// Situation 1 
		if (noBeepersPresent()) {
			moveUp();
			fillOneLine();
			//Situation 2 
		} else {
			moveUp();
			if (frontIsClear()) {
				move();
				fillOneLine();
				//Situation 3 (1xN World)
			} else {
				moveUp();
				fillOneVerticalLine();
			}
		}
	}

	private void moveUp() {
		if (leftIsClear()) {
			turnLeft();
			move();
			turnRight();
		}
	}

	private void fillOneVerticalLine() {
		turnLeft();
		fillOneLine();

	}
}
