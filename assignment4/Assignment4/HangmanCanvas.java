
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {

		removeAll();
		lastIncorrectLetterX = 15;
		drawScaffold();
		i = 8;

	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */

	public void displayWord(String word) {
		counter++;

		if (counter == 1) {
			GLabel hint = new GLabel(word);
			hint.setColor(Color.GREEN);
			hint.setFont("-30");
			add(hint, CORRECT_WORDS_X_OFFSET, getHeight() - CORRECT_WORDS_Y_OFFSET);
			previous = hint;

		} else {
			remove(previous);
			GLabel hint = new GLabel(word);
			hint.setColor(Color.GREEN);
			hint.setFont("-30");
			add(hint, CORRECT_WORDS_X_OFFSET, getHeight() - CORRECT_WORDS_Y_OFFSET);
			previous = hint;

		}
	}

	private int counter = 0;
	private GLabel previous;

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */
	public void noteIncorrectGuess(char letter) {
		i--;

		drawBodyParts();
		drawIncorrectWords(letter);

	}

	private void drawIncorrectWords(char letter) {

		/*
		 * this method adds incorrect words on canvas if received char = '1' it means
		 * that, user entered usedWord again so we don't need to print it on canvas
		 * again
		 */
		if (letter != '1') {
			GLabel label = new GLabel("" + letter);
			label.setColor(Color.RED);
			add(label, lastIncorrectLetterX, getHeight() - INCORRECT_WORDS_Y_OFFSET);
			lastIncorrectLetterX += label.getAscent() + OFFSET_BETWEEN_WORDS;
		}
	}

	private void drawBodyParts() {
		if (i == 7) {
			drawHead();
		}

		if (i == 6) {
			drawBody();
		}

		if (i == 5) {
			drawLeftHand();
		}

		if (i == 4) {
			drawRightHand();
		}

		if (i == 3) {
			drawLeftLeg();
		}

		if (i == 2) {
			drawRightLeg();
		}

		if (i == 1) {
			drawLeftFoot();
		}

		if (i == 0) {
			drawRightFoot();
		}
	}

	/* Methods below draw body parts */
	private void drawRightFoot() {

		double startY = Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;

		GLine line1 = new GLine(getWidth() / 2 + HIP_WIDTH, startY, getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH, startY);
		line1.setColor(Color.RED);
		add(line1);
	}

	private void drawLeftFoot() {

		double startY = Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;

		GLine line1 = new GLine(getWidth() / 2 - HIP_WIDTH, startY, getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH, startY);
		line1.setColor(Color.RED);
		add(line1);
	}

	private void drawRightLeg() {

		double startY = Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;

		GLine line1 = new GLine(getWidth() / 2, startY, getWidth() / 2 + HIP_WIDTH, startY);
		line1.setColor(Color.RED);
		add(line1);

		GLine line2 = new GLine(getWidth() / 2 + HIP_WIDTH, startY, getWidth() / 2 + HIP_WIDTH, startY + LEG_LENGTH);
		line2.setColor(Color.RED);
		add(line2);
	}

	private void drawLeftLeg() {

		double startY = Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;

		GLine line1 = new GLine(getWidth() / 2, startY, getWidth() / 2 - HIP_WIDTH, startY);
		line1.setColor(Color.RED);
		add(line1);

		GLine line2 = new GLine(getWidth() / 2 - HIP_WIDTH, startY, getWidth() / 2 - HIP_WIDTH, startY + LEG_LENGTH);
		line2.setColor(Color.RED);
		add(line2);

	}

	private void drawRightHand() {
		double startY = Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;

		GLine line1 = new GLine(getWidth() / 2, startY, getWidth() / 2 + UPPER_ARM_LENGTH, startY);
		line1.setColor(Color.RED);
		add(line1);

		GLine line2 = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH, startY, getWidth() / 2 + UPPER_ARM_LENGTH,
				startY + LOWER_ARM_LENGTH);
		line2.setColor(Color.RED);
		add(line2);

	}

	private void drawLeftHand() {
		double startY = Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;

		GLine line1 = new GLine(getWidth() / 2, startY, getWidth() / 2 - UPPER_ARM_LENGTH, startY);
		line1.setColor(Color.RED);
		add(line1);

		GLine line2 = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH, startY, getWidth() / 2 - UPPER_ARM_LENGTH,
				startY + LOWER_ARM_LENGTH);
		line2.setColor(Color.RED);
		add(line2);

	}

	private void drawBody() {
		int startY = Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS;
		GLine body = new GLine(getWidth() / 2, startY, getWidth() / 2, startY + BODY_LENGTH);
		body.setColor(Color.RED);
		add(body);
	}

	private void drawHead() {
		GOval head = new GOval(2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		head.setColor(Color.RED);
		add(head, getWidth() / 2 - HEAD_RADIUS, Y_OFFSET + ROPE_LENGTH);
	}

	/* draws Scaffold */
	private void drawScaffold() {

		GLine line1 = new GLine(getWidth() / 2 - BEAM_LENGTH, Y_OFFSET, getWidth() / 2 - BEAM_LENGTH,
				Y_OFFSET + SCAFFOLD_HEIGHT);
		line1.setColor(Color.RED);
		add(line1);

		GLine line2 = new GLine(getWidth() / 2 - BEAM_LENGTH, Y_OFFSET, getWidth() / 2, Y_OFFSET);
		line2.setColor(Color.RED);
		add(line2);

		GLine line3 = new GLine(getWidth() / 2, Y_OFFSET, getWidth() / 2, Y_OFFSET + ROPE_LENGTH);
		line3.setColor(Color.RED);
		add(line3);
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	/* I counts guessesLeft */
	private int i = 8;

	private static final int Y_OFFSET = 20;
	private static final int INCORRECT_WORDS_Y_OFFSET = 5;
	private static final int CORRECT_WORDS_X_OFFSET = 15;
	private static final int CORRECT_WORDS_Y_OFFSET = 30;
	private static final int OFFSET_BETWEEN_WORDS = 5;
	private int lastIncorrectLetterX = 15;

}
