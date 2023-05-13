
/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		
		topMiddle();
		goDownAndPutBeeper();
	}
	
	// With that function Karel goes to SOUTH side of GUI and puts beeper
	private void goDownAndPutBeeper() {
		
		turnRight();
		
		while(frontIsClear()) move();
		
		putBeeper();
		turnLeft();
	}
	
	// With this function Karel goes from left bottom corner to top middle block
	private void topMiddle() {
		
		// Because world is always square i have simple algorithm, go forward with one block
		// go up with two block, simple Mathematics :)
		while(true) {
			
			if(leftIsBlocked()) break;
			
			safeMove();
			doubleMoveUp();
		}
	}
	
	// With that function Karel goes up with 2 square
	private void doubleMoveUp() {
		
		turnLeft();
		
		safeMove();
		safeMove();
		
		turnRight();
	}
	
	// Function for safe move
	private void safeMove() {
	
		if(frontIsClear()) move();
	}
}

