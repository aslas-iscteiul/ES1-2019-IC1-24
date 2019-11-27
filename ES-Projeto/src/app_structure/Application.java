package app_structure;

import gui.GUI;

public class Application {
 
	private GUI gui;
	private FileReader fr;

	public Application(GUI gui, FileReader fr) {
		this.gui = gui;
		this.fr=fr;
	}
	
}
