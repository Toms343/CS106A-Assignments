
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {

	ArrayList<String> st = new ArrayList<String>();

	public int getWordCount() {
		return st.size();
	}

	public String getWord(int index) {

		return st.get(index);
	}

	public void getWords() {

		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));

			while (true) {
				String word = rd.readLine();
				if (word == null)
					break;
				st.add(word);

			}
			rd.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
