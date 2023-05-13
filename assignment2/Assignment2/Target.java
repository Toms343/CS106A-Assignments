
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	// coefficient cm to pixels
	private static final double COEFFICIENT = 72 / 2.54;
	// first oval radius in cm
	private static final double FIRST_OVAL_RADIUS = 2.54;
	// offset between ovals (R-r)
	private static final double OFFSET_BETWEEN_OVALS = 0.89;
	// number of ovals
	private static final int NUMBER_OF_OVALS = 3;

	public void run() {

		drawCircles();

	}

	//this code only works if offset between all circles is same
	private void drawCircles() {

		for (int i = 0; i < NUMBER_OF_OVALS; i++) {

			double diameter = COEFFICIENT * (FIRST_OVAL_RADIUS - OFFSET_BETWEEN_OVALS * i) * 2;

			double midX = (getWidth() - diameter) / 2;
			double midY = (getHeight() - diameter) / 2;

			GOval target = new GOval(diameter, diameter);
			target.setFilled(true);
			if (i % 2 == 0) {
 				target.setColor(Color.RED);
			} else {
				target.setColor(Color.WHITE);
			}

			add(target, midX, midY);
		}
	}
}
