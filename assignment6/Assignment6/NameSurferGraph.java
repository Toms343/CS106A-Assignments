
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);

	}

	// I draw "Starting Graph" with this method below
	private void createGrid() {

		drawVerticalLines();
		drawHorizontalLines();
		addDecades();
	}

	// i use this code bellow to add decades on canvas
	private void addDecades() {

		int startDec = START_DECADE;
		int startX = DECADES_MARGIN_SIZE;
		int diff = getWidth() / NDECADES;

		for (int i = 0; i < NDECADES; i++) {
			GLabel decade = new GLabel(Integer.toString(startDec));
			add(decade, startX, getHeight() - DECADES_MARGIN_SIZE);
			startX += diff;
			startDec += DIFF_BETWEEN_DECADES;

		}

	}
	
	private static final int DECADES_MARGIN_SIZE = 2;

	// This method draws 2 horizontal line
	private void drawHorizontalLines() {

		GLine startY = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		GLine endY = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);

		add(startY);
		add(endY);
	}

	// this method draws vertical lines
	private void drawVerticalLines() {

		int diff = getWidth() / NDECADES;
		int startX = 0;

		for (int i = 0; i < NDECADES; i++) {

			GLine line = new GLine(startX, 0, startX, getHeight());
			add(line);
			startX += diff;
		}
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */

	public void clear() {
		data.clear();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		// If entered NameSurferEntry object is already stored in data, program does nothing
		// else it will adds "entry" in arrayList
		if (!data.contains(entry)) {
			data.add(entry);
		}
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		createGrid();
		addGraphs();
	}

	// I save entered NameSurferEntry's down bellow
	private ArrayList<NameSurferEntry> data = new ArrayList<NameSurferEntry>();

	private void addGraphs() {

		// some variables bellow
		double coefficient = (getHeight() - 2 * GRAPH_MARGIN_SIZE) / (double) MAX_RANK;
		double diff = getWidth() / NDECADES;
		int colorCounter = 0;

		// with this for loop program can "Read" every NameSurferEntry Object and draw
		// relevant graph
		for (NameSurferEntry entry : data) {

			double x = 0;
			int decade = START_DECADE;

			for (int i = 0; i < NDECADES; i++) {

				double y = entry.getRank(decade) * coefficient + GRAPH_MARGIN_SIZE;

				drawText(x, y, entry.getRank(decade), entry.getName(), getColor(colorCounter));
				drawLine(x, y, entry.getRank(decade), getColor(colorCounter));
				x += diff;
				decade += DIFFERENCE_BETWEEN_DECADES;
			}
			colorCounter += 1;
			// If i not use codeLine bellow, next name graph will start from last name
			// Graphs end point
			line = null;

		}
	}

	private static final int DIFFERENCE_BETWEEN_DECADES = 10;
	private GLine line;

	// this adds "Ranks Line" on canvas
	private void drawLine(double x, double y, int rank, Color color) {
		if (rank == 0) {
			y = getHeight() - GRAPH_MARGIN_SIZE;
		}
		if (line == null) {
			line = new GLine(x, y, x, y);
		} else {
			line.setEndPoint(x, y);
			line = new GLine(x, y, x, y);
		}

		line.setColor(color);
		add(line);
	}

	// This method returns Red, Black or Blue colors
	private Color getColor(int colorCounter) {

		if (colorCounter % 3 == 1) {
			return Color.RED;
		}
		if (colorCounter % 3 == 2) {
			return Color.BLUE;
		}
		return Color.BLACK;
	}

	// this method adds Rank with name on canvas
	private void drawText(double x, double y, int rank, String name, Color color) {

		if (rank == 0) {
			name += " *";
			y = getHeight() - GRAPH_MARGIN_SIZE;
		} else {
			name += " " + rank;
		}

		GLabel text = new GLabel(name);
		add(text, x + text.getHeight() / 4, y - text.getHeight() / 4);
		text.setColor(color);
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
