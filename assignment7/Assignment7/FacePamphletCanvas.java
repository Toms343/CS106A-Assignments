/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		
		if(message != null) {
			remove(message);
		}
		
		message = new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		add(message, (getWidth() - message.getWidth())/2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		
	}
	
	private GLabel message;
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		
		removeAll();
		
		if(profile != null) {
		addProfileName(profile.getName());
		addImage(profile.getImage());
		addStatus(profile.getStatus(), profile.getName());
		addFriendList(profile.getFriends());
		}
		
	}
	
	// This Method adds String("Friends") on display and adds friend list if profile contains it
	private void addFriendList(Iterator<String> profileFriends) {
		
		GLabel friends = new GLabel("Friends");
		friends.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friends, getWidth()/2, IMAGE_MARGIN + TOP_MARGIN);
		
		if(profileFriends != null) {
			
			double xCoordinant = IMAGE_MARGIN + TOP_MARGIN + DIFF_BETWEEN_FRIEND_NAMES;
			
			while(profileFriends.hasNext()) {
				
				GLabel friendName = new GLabel (profileFriends.next());
				friendName.setFont(PROFILE_FRIEND_FONT);
				add(friendName, getWidth()/2, xCoordinant);
				xCoordinant += DIFF_BETWEEN_FRIEND_NAMES;
			}
		}
	}
	
	// This method adds Profile Status on display
	private void addStatus(String status, String profileName) {
		
		GLabel profileStatus;
		
		if(status.equals("")) {
			profileStatus = new GLabel("No current status");
		}else {
			profileStatus = new GLabel(profileName + " is " + status);
		}
		profileStatus.setFont(PROFILE_STATUS_FONT);
		add(profileStatus, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + STATUS_MARGIN + IMAGE_HEIGHT);
		
	}
	
	// This method adds Profile Picture on display, if picture equals "null" Program just draws "Box"
	private void addImage(GImage profilePicture) {
		
		if(profilePicture != null) {
			profilePicture.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(profilePicture, LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN);
		}else {
			GRect rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(rect, LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN);
			
			GLabel text = new GLabel("No Image");
			text.setFont(PROFILE_IMAGE_FONT);
			add(text, LEFT_MARGIN + (IMAGE_WIDTH - text.getWidth())/2, IMAGE_MARGIN + TOP_MARGIN + (IMAGE_HEIGHT + text.getHeight())/2 );
			
		}
	}
	
	// This method adds Profile Name on display
	private void addProfileName(String profileName) {
		
		GLabel name = new GLabel(profileName);
		name.setColor(Color.BLUE);
		name.setFont(PROFILE_NAME_FONT);
		add(name, LEFT_MARGIN, TOP_MARGIN);
				
	}

	
}
