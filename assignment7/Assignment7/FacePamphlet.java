
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.StringTokenizer;

import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the interactors in the
	 * application, and taking care of any other initialization that needs to be
	 * performed.
	 */
	public void init() {

		graphic = new FacePamphletCanvas();
		add(graphic);

		data = new FacePamphletDatabase();

		JLabel name = new JLabel("Name: ");
		add(name, NORTH);

		profileNameField = new JTextField(TEXT_FIELD_SIZE);
		add(profileNameField, NORTH);

		add = new JButton("Add");
		add(add, NORTH);

		delete = new JButton("Delete");
		add(delete, NORTH);

		lookup = new JButton("Lookup");
		add(lookup, NORTH);

		statusField = new JTextField(TEXT_FIELD_SIZE);
		add(statusField, WEST);
		statusField.addActionListener(this);

		changeStatus = new JButton("Change Status");
		add(changeStatus, WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		pictureField = new JTextField(TEXT_FIELD_SIZE);
		add(pictureField, WEST);
		pictureField.addActionListener(this);

		changePicture = new JButton("Change Picture");
		add(changePicture, WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		friendField = new JTextField(TEXT_FIELD_SIZE);
		add(friendField, WEST);
		friendField.addActionListener(this);

		addFriend = new JButton("Add Friend");
		add(addFriend, WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		addActionListeners();

	}

	private JTextField profileNameField;
	private JButton add;
	private JButton delete;
	private JButton lookup;

	private JTextField statusField;
	private JTextField pictureField;
	private JTextField friendField;
	private JButton changeStatus;
	private JButton changePicture;
	private JButton addFriend;

	private FacePamphletCanvas graphic;
	private FacePamphletDatabase data;
	private FacePamphletProfile currentProfile;

	private String message = "";

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		checkWestButtons(source);

		if (!profileNameField.getText().equals("")) {

			checkNorthButtons(source);
		}

		// I update display every time buttons are clicked or iterators are used
		graphic.displayProfile(currentProfile);
		graphic.showMessage(message);

	}
	
	// This method modifies string, removes spaces, enlarges first letter of every word
	private String modifieString(String name) {
		
		name = name.toLowerCase();
		
		String result ="";
		StringTokenizer tk = new StringTokenizer(name);
		
		while(tk.hasMoreTokens()) {
			String nextWord = tk.nextToken();
			result += nextWord.substring(0,1).toUpperCase() + nextWord.substring(1);
			if(tk.hasMoreTokens()) {
				result += " ";
			}
		}
		
		
		return result;
	}

	private void checkWestButtons(Object source) {

		// If profile is not chosen i just change "message" and return;
		if (currentProfile == null) {
			message = "Choose profile and try again";
			return;
		}

		if (!statusField.getText().equals("") && (source == statusField || source == changeStatus)) {

			changeStatus(); // Method changes profile status
			statusField.setText("");

		} else if (!pictureField.getText().equals("") && (source == pictureField || source == changePicture)) {

			changeProfilePicture(); // Method changes profile picture
			pictureField.setText("");

		} else if (!friendField.getText().equals("") && (source == friendField || source == addFriend)) {

			addFriend(); // Method add friends
			friendField.setText("");
		}
		
	}

	private void addFriend() {

		String friend = friendField.getText();
		friend = modifieString(friend); // Modifies friend name

		// If friend profile does not exist, I change "message" and return;
		if (!data.containsProfile(friend)) {
			message = "Can't find profile with name " + friend;
			return;
		}
		// If entered name equals current profiles name, I change message and return
		// If not I add entered name in current profiles friends list
		// But if profile already have entered user in friends, i just change message;
		if (!friend.equals(currentProfile.getName())) {

			if (currentProfile.addFriend(friend)) {
				FacePamphletProfile friendProfile = data.getProfile(friend);
				friendProfile.addFriend(currentProfile.getName());
				message = "Friend added";

			} else
				message = "You already have this person in friendlist";
			return;

		} else
			message = "You can't add yourself in friends list";
	}

	private void changeProfilePicture() {

		// In this code bellow i try to read picture
		GImage image = null;

		try {

			image = new GImage(pictureField.getText());

		} catch (ErrorException ex) {

			message = "Can't find picture"; // If program can't find picture, i just change message and return;
			return;
		}

		if (image != null) {

			currentProfile.setImage(image);
			message = "Profile Picture Updated";
		}
	}

	private void changeStatus() {

		// If "Entered Status" equals current status i just change "message"
		// If not i update status;
		if (!currentProfile.getStatus().equals(statusField.getText())) {

			currentProfile.setStatus(statusField.getText());
			message = "Status updated to " + currentProfile.getStatus();
		} else {
			message = "You can't set same status";
		}

	}

	private void checkNorthButtons(Object source) {

		String profileName = profileNameField.getText();
		profileName = modifieString(profileName); // Modifies profile name

		if (source == add) {

			addProfile(profileName); // This method adds profile
		} else if (source == delete) {

			deleteProfile(profileName); // this method deletes profile
		} else if (source == lookup) {

			lookupProfile(profileName); // this method look ups profile
		}
		
		profileNameField.setText(profileName);
	}

	private void lookupProfile(String profileName) {

		// If data contains userName  I change currentProfile And message
		if (data.containsProfile(profileName)) {
			currentProfile = data.getProfile(profileName);
			message = "Displaying " + profileName;

		} else {
			message = "Profile with name " + profileName + " does not exist";
			currentProfile = null;
		}
	}

	private void deleteProfile(String profileName) {

		// If data contains profile I delete it
		if (data.containsProfile(profileName)) {
			data.deleteProfile(profileName);
			message = "Profile of " + profileName + " deleted";
			currentProfile = null;

		} else {
			message = "A Profile with name " + profileName + " does not exist";

		}

	}

	private void addProfile(String profileName) {

		// If data contains profile I change currentProfile, else i create new one
		if (data.containsProfile(profileName)) {
			
			currentProfile = data.getProfile(profileName);
			message = " Welcome back "+ profileName;
			return;
		}
		
		currentProfile = new FacePamphletProfile(profileName);
		message = "New profile created, Wellcome " + profileName;
		data.addProfile(currentProfile);

	}
}
