
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {

	//GRect parameters
	private static final int WIDTH = 160;
	private static final int HEIGHT = 50;
	//Offset between rectangles
	private static final int OFFSET = 40;

	public void run() {

		//start Y coordinate for hierarchy
		//2*HEIGHT-OFFSET - hierarchy height
		int positionY = (getHeight() - 2 * HEIGHT - OFFSET) / 2;

		drawRects(positionY);
		drawLines(positionY);
		drawText(positionY);

	}

	private void drawRects(double a) {

		//top box
		GRect top = new GRect(WIDTH, HEIGHT);
		add(top, (getWidth() - WIDTH) / 2, a);

		
		for (int i = 0; i < 3; i++) {

			GRect down = new GRect(WIDTH, HEIGHT);
			//draws 3 box 
			double posX = (getWidth() - WIDTH) / 2 + (i - 1) * (OFFSET + WIDTH);
			add(down, posX, a + OFFSET + HEIGHT);

		}
	}

	private void drawLines(double a) {

		//lines start coordinates
		double startX = getWidth() / 2;
		double startY = a + HEIGHT;
		double endY = a + OFFSET + HEIGHT;

		for (int i = 0; i < 3; i++) {
			GLine lines = new GLine(startX, startY, getWidth() / 2 + (i - 1) * (WIDTH + OFFSET), endY);
			add(lines);
		}
	}

	private void drawText(double m) {

		//creating "Program"
		GLabel program = new GLabel("Program");
		double a = program.getWidth();
		double b = program.getAscent();
		add(program, (getWidth() - a) / 2, m + (HEIGHT + b) / 2);

		//creating "ConsoleProgram"
		GLabel console = new GLabel("ConsoleProgram");
		double c = console.getWidth();
		double d = console.getAscent();
		add(console, (getWidth() - c) / 2, m + OFFSET + HEIGHT * 3 / 2 + d / 2);

		//creating "GraphicsProgram"
		GLabel graphics = new GLabel("GraphicsProgram");
		double e = graphics.getWidth();
		double f = graphics.getAscent();
		add(graphics, (getWidth() - e) / 2 - OFFSET - WIDTH, m + OFFSET + HEIGHT * 3 / 2 + f / 2);

		//creating "DialogProgram"
		GLabel dialog = new GLabel("DialogProgram");
		double g = dialog.getWidth();
		double h = dialog.getAscent();
		add(dialog, (getWidth() - g) / 2 + OFFSET + WIDTH, m + OFFSET + HEIGHT * 3 / 2 + h / 2);

	}

}
