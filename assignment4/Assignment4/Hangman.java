
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {

	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanCanvas canvas;

	private static final int GUESSES = 8;
	private int guessesLeft;
	private int wordsLeft;

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run() {

		println("Welcome To Hangman!");

		int n = 1;
		while (n == 1) {
			guessesLeft = GUESSES;
			canvas.reset();
			playGame();

			n = askForMoreGames();
		}
		println("Thanks For playing my Hangman!");
	}

	// this method return number, if entered number = 1, user can play again;
	private int askForMoreGames() {
		println("");
		println("Do you want play again? enter 1 if you want");
		int n = readInt("Wanna play? -- ");
		return n;
	}

	private void playGame() {
		String playWord = generateWord();
		wordsLeft = playWord.length();
		String hint = generateHint(playWord);
		String wordStorage = "";

		while (true) {
			printGameStats(hint);
			String input = getUserGuess();
			hint = checkUserGuess(input, wordStorage, playWord, hint);
			wordStorage += input;

			if (guessesLeft == 0) {
				youLose(playWord);
				break;
			}
			if (wordsLeft == 0) {
				youWin(playWord);
				break;
			}

		}
	}

	// this method checks input and return changed hint
	private String checkUserGuess(String input, String wordStorage, String playWord, String hint) {

		/*
		 * if input is not used and play word does not contain it i reduce guesses left
		 * by one and use noteInccorect method to update graphics;
		 */
		if (!wordStorage.contains(input) && !playWord.contains(input)) {
			println("There are no " + input.charAt(0) + "'s in the word");
			guessesLeft--;
			canvas.noteIncorrectGuess(input.charAt(0));
		}
		/*
		 * if input is used and play word does not contain it i reduce guesses left by
		 * one and use noteInccorect method to update graphics, but i use '1' for method
		 * argument, in this way this method do not add incorrect word on canvas;
		 */
		else if (wordStorage.contains(input) && !playWord.contains(input)) {
			guessesLeft--;
			canvas.noteIncorrectGuess('1');
			println("You entered incorrect word again, you lost one life");
		}
		// if input is not used and play word contain it, i change hint;
		else if (playWord.contains(input) && !wordStorage.contains(input)) {
			println("That guess is correct");
			hint = changeHint(playWord, input, hint);
		} else {
			println("You entered correct word again. ");
		}

		return hint;
	}

	// this method prints that user won the game
	private void youWin(String playWord) {
		canvas.displayWord(playWord);
		println("You guessed the word: " + playWord);
		println("You Win.");
		println("");

	}

	// this method prints that user lost the game
	private void youLose(String playWord) {
		canvas.displayWord(playWord);
		println("You're compeletely hung.");
		println("The word was: " + playWord);
		println("You Lost.");
		println("");

	}

	// This method gets only one word from user
	private String getUserGuess() {
		String input = readLine("Your guess: ");
		input = input.toUpperCase();

		// i firstly check input length, in this way i avoid "Enter pressed" bug;
		while (input.length() != 1 || !(input.charAt(0) >= 'A' && input.charAt(0) <= 'Z')) {
			input = readLine("Enter only one word, not numbers or symbols: ");
			input = input.toUpperCase();
		}
		return input;
	}

	/*
	 * This method have 3 arguments, playWord, word entered by user and hint(-----);
	 * I check every word in playWord and if any equals to enteredWord, I will
	 * change hint and reduce wordsLeft by one. for example, 
	 * if 1st word equals to entered word, i will replace
	 * 1st symbol in hint with enteredWord
	 */
	private String changeHint(String playWord, String input, String hint) {
		for (int i = 0; i < playWord.length(); i++) {
			if (playWord.charAt(i) == input.charAt(0)) {
				wordsLeft--;
				hint = hint.substring(0, i) + input.charAt(0) + hint.substring(i + 1);
			}
		}
		return hint;
	}

	// I use this method to print game statistic
	private void printGameStats(String hint) {
		canvas.displayWord(hint);
		println("");
		println("The word now looks likes this: " + hint);
		println("You have " + guessesLeft + " guesses left.");
		println("");
	}

	// This method generates hint from playWord
	private String generateHint(String playWord) {
		String hint = "";
		for (int i = 0; i < playWord.length(); i++) {
			hint += "-";
		}
		return hint;
	}

	// This method generates random word from HangmanLexicon
	private String generateWord() {
		HangmanLexicon w = new HangmanLexicon();
		w.getWords();
		String playWord = w.getWord(rgen.nextInt(0, w.getWordCount()));
		return playWord;
	}

}