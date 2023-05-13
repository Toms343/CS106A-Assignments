
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	private static int DELAY = 10;

	private GRect paddle;
	private GOval ball;
	private int counter = NBRICKS_PER_ROW * NBRICK_ROWS;
	private double vy;
	private double vx;

	// RandomGenerator to get random ball speed
	private RandomGenerator rgen = RandomGenerator.getInstance();

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {

		addStartText();
		addMouseListeners();
		initializeGame();
		startGame();

	}

	private void addStartText() {
		// Creates start Text;
		GLabel click = new GLabel("Click to start game");
		double y = click.getAscent();
		double x = click.getWidth();
		add(click, (getWidth() - x) / 2, (getHeight() - y) / 2);
		waitForClick();
		remove(click);

	}

	private void addBricks() {
		// Draw bricks: "i" defines rows and "j" adds bricks;
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {

				double sizeOfOneRow = NBRICKS_PER_ROW * BRICK_WIDTH + BRICK_SEP * (NBRICKS_PER_ROW - 1);
				double firstX = ((getWidth() - sizeOfOneRow) / 2);
				double corX = firstX + (BRICK_SEP + BRICK_WIDTH) * j;
				double corY = BRICK_Y_OFFSET + (BRICK_SEP + BRICK_HEIGHT) * i;

				GRect blocks = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				blocks.setFilled(true);

				if (i < 2) {
					blocks.setColor(Color.RED); // 1-2 Line
				}
				if (i == 2 || i == 3) {
					blocks.setColor(Color.ORANGE); // 3-4 Line
				}
				if (i == 4 || i == 5) {
					blocks.setColor(Color.YELLOW); // 5-6 Line
				}
				if (i == 6 || i == 7) {
					blocks.setColor(Color.GREEN); // 7-8 Line
				}
				if (i == 8 || i == 9) {
					blocks.setColor(Color.CYAN); // 9-10 Line
				}
				add(blocks, corX, corY);

			}

		}

	}

	private void addPaddle() {
		// Draw paddle
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle, (getWidth() - PADDLE_WIDTH) / 2, getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
	}

	private void addBall() {
		// This code adds ball on canvas;

		ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball, getWidth() / 2 - BALL_RADIUS,getHeight() / 2 - BALL_RADIUS);

	}

	private void initializeGame() {
		// Initialization phase , creating bricks and paddle;
		addBricks();
		addPaddle();

	}

	public void mouseMoved(MouseEvent e) {

		// MouseEvent for paddle movement;
		// mouse tracks midpoint of paddle only if the paddle fits in the canvas;

		double x = e.getX() - PADDLE_WIDTH / 2;
		double y = getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET;

		if (e.getX() >= PADDLE_WIDTH / 2 && e.getX() <= getWidth() - PADDLE_WIDTH / 2) {
			paddle.setLocation(x, y);
		}
	}

	private void moveBall() {
		// This code controls ball movement;
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		while (true) {

			ball.move(vx, vy);
			pause(DELAY);

			// If ball hits top side of canvas it changes speed ( vy = -vy);
			if (ball.getY() < 0) {
				vy = -vy;
			}
			// If ball hits down side of canvas, while(true) loop will breaks;
			if (ball.getY() > getHeight() - BALL_RADIUS * 2) {
				remove(ball);
				break;
			}
			// If ball hits left or right side of canvas it changes speed (vx =-vx);
			if (ball.getX() > getWidth() - BALL_RADIUS * 2 || ball.getX() < 0) {
				vx = -vx;
			}
			GObject collider = getCollidingObject();
			/*
			 * This part of code checks for collision; if ball hits paddle from top, ball
			 * changes speed (vy = - vy); if ball hits paddle from left or right ball will
			 * go down; if ball hits bricks from right or left , it changes speed (vx = -vx)
			 * if ball hits bricks from top or down, it changes speed (vy = -vy);
			 */

			if (collider == paddle) {
				if (ball.getY() + 2 * BALL_RADIUS <= paddle.getY() + vy) {
					vy = -vy;
				}
			} else if (collider != null) {
				if (sideHitt(collider)) {
					vx = -vx;
					ball.move(2 * vx, 0);
				} else {
					vy = -vy;
				}
				counter--;
				remove(collider);
			}
			if (counter == 0) {
				break;
			}

		}
	}

	// i check if ball hits object from sides with this code
	// ეს ნაწილი წესიერად არ მუშაობს ........
	private boolean sideHitt(GObject collider) {

		double x = ball.getX();
		double y = ball.getY();
		double d = BALL_RADIUS * 2;

		if (y < collider.getY() + collider.getHeight() && y + d > collider.getY() ) {
			if (getElementAt(x, y) == collider && x + vx > collider.getX() + BRICK_WIDTH) {
				return true;
			} else if (getElementAt(x + d, y) == collider && x + d - vx < collider.getX()) {
				return true;
			} else if (getElementAt(x + d, y + d) == collider && x + d - vx < collider.getX()) {
				return true;
			} else if (getElementAt(x, y + d) == collider && x + vx > collider.getX() +  BRICK_WIDTH) {
				return true;
			}
		}
		return false;
	}

	private void startGame() {

		// every time moveBall() loop breaks one is added to i
		for (int i = 0; i < NTURNS; i++) {
			addBall();
			moveBall();

			if (counter == 0) {
				removeAll();
				GLabel win = new GLabel("You win");
				double x = win.getWidth();
				double y = win.getAscent();
				add(win, (getWidth() - x) / 2, (getHeight() - y) / 2);
				break;
			}
		}

		if (counter > 0) {
			removeAll();
			GLabel lose = new GLabel("You lose");
			double x = lose.getWidth();
			double y = lose.getAscent();
			add(lose, (getWidth() - x) / 2, (getHeight() - y) / 2);
		}

	}

	// if ball hits object code returns it, so we can remove returned object ( if it
	// is not paddle);
	private GObject getCollidingObject() {

		double x = ball.getX();
		double y = ball.getY();
		double d = 2 * BALL_RADIUS;

		if (getElementAt(x, y) != null) {
			return getElementAt(x, y);
		} else if (getElementAt(x + d, y) != null) {
			return getElementAt(x + d, y);
		} else if (getElementAt(x + d, y + d) != null) {
			return getElementAt(x + d, y + d);
		} else if (getElementAt(x, y + d) != null) {
			return getElementAt(x, y + d);
		} else
			return null;
	}

}