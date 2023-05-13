
/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * database.
	 */
	public FacePamphletDatabase() {

		dataBase = new HashMap<>();

	}

	Map<String, FacePamphletProfile> dataBase;

	/**
	 * This method adds the given profile to the database. If the name associated
	 * with the profile is the same as an existing name in the database, the
	 * existing profile is replaced by the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {

		String profileName = profile.getName();
		dataBase.put(profileName, profile);

	}

	/**
	 * This method returns the profile associated with the given name in the
	 * database. If there is no profile in the database with the given name, the
	 * method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {

		if (dataBase.containsKey(name)) {

			return dataBase.get(name);

		}
		return null;
	}

	/**
	 * This method removes the profile associated with the given name from the
	 * database. It also updates the list of friends of all other profiles in the
	 * database to make sure that this name is removed from the list of friends of
	 * any other profile.
	 * 
	 * If there is no profile in the database with the given name, then the database
	 * is unchanged after calling this method.
	 */
	public void deleteProfile(String name) {

		if (dataBase.containsKey(name)) {
			dataBase.remove(name);

			// This code removes deleted user from everyones friend list
			for (String profileName : dataBase.keySet()) {

				FacePamphletProfile profile = dataBase.get(profileName);

				if (profile.getFriends() != null) {

					while (profile.getFriends().hasNext()) {
						if (profile.removeFriend(name)) {
							break;
						}
					}
				}

			}
		}
	}

	/**
	 * This method returns true if there is a profile in the database that has the
	 * given name. It returns false otherwise.
	 */
	public boolean containsProfile(String name) {

		if (dataBase.containsKey(name))
			return true;

		return false;
	}

}
