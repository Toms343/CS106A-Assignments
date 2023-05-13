import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the requested
	 * file does not exist or if an error occurs as the file is being read.
	 */

	// I save all information in this map bellow, after reading file
	// Key is name, value is line
	Map<String, NameSurferEntry> dataBase = new HashMap<String, NameSurferEntry>();

	public NameSurferDataBase(String filename) {

		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));

			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				
				// when program reads one line from .txt file, i create new NamesSUrferEntry 
				// after it i get name from line and add it on HashMap
				NameSurferEntry entry = new NameSurferEntry(line);
				String key = entry.getName();

				dataBase.put(key, entry);
			}

			rd.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If the
	 * name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		
		// firstly i remove "spaces" from word, next
		// i lowerCase word next I enlarge the first letter of the entered name
		// with this code line bellow to avoid some bugs
		StringTokenizer tk = new StringTokenizer(name);
		name = tk.nextToken();
		name = name.toLowerCase();
		name = name.substring(0,1).toUpperCase() + name.substring(1, name.length());
		
		// If dataBase contains entered name, I return NameSurferEntry object from HashMap
		// If not i return null
		if (dataBase.containsKey(name)) {
			return dataBase.get(name);
		} else {
			return null;
		}
	}
}
