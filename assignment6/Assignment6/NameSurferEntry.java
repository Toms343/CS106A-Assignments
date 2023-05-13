
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers giving
	 * the rank of that name for each decade.
	 */

	private String name;
	private int[] stats;

	// This method bellow splits name and ranks from entered line
	public NameSurferEntry(String line) {
		
		stats = new int[NDECADES];
		StringTokenizer tok = new StringTokenizer(line);
		name = tok.nextToken();

		int counter = 0;

		while (tok.hasMoreTokens()) {
			stats[counter] = Integer.parseInt(tok.nextToken());
			counter++;

		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	
	public String getName() {

		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The decade
	 * value is an integer indicating how many decades have passed since the first
	 * year in the database, which is given by the constant START_DECADE. If a name
	 * does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {

		int index = (decade - START_DECADE) / DIFF_BETWEEN_DECADES;

		return stats[index];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a NameSurferEntry.
	 */
	public String toString() {
		
		String result = "";

		if (name != null) {
			result += name + " [";
			for (int i = 0; i < NDECADES; i++) {
				result += stats[i];
				if(i != NDECADES - 1) {
					result += " ";
				}
			}
			result += "]";
		}
		return result;
	}
}
