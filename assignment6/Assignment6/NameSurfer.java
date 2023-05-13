
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */

	private static final int TEXT_WINDOW_SIZE = 25;
	NameSurferDataBase data;
	NameSurferGraph graphic;

	private JTextField textWind;
	private JButton graph;
	private JButton clear;

	public void init() {
		data = new NameSurferDataBase("names-data.txt");
		graphic = new NameSurferGraph();
		add(graphic);

		// I add buttons and textField bellow
		JLabel name = new JLabel("Name");
		add(name, SOUTH);

		textWind = new JTextField(TEXT_WINDOW_SIZE);
		add(textWind, SOUTH);
		textWind.addActionListener(this);

		graph = new JButton("Graph");
		add(graph, SOUTH);

		clear = new JButton("Clear");
		add(clear, SOUTH);

		addActionListeners();

	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so you
	 * will have to define a method to respond to button actions.
	 */

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		// If user click on clear button, i use clear() method from NameSurferGraph
		if (source == clear) {
			graphic.clear();
			graphic.update();

		}
		// If user presses on enter or "Graph" button and textWindon will be clear code
		// do nothing
		if (source == graph || source == textWind && !textWind.getText().equals("")) {
			// if player press on enter or click on "Graph" button i use code bellow to
			// update graph on canvas
			NameSurferEntry entry = data.findEntry(textWind.getText());
			// if name is not in dataBase i do nothing
			// else i update graph on canvas
			if (entry != null) {
				graphic.addEntry(entry);
				graphic.update();
			}
			textWind.setText("");
		}
	}
}
