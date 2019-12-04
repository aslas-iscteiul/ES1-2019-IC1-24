package app_structure;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.SwingUtilities;

import gui.GUI;

/**
 * 	@author anabela, catarina a.
 *	
 *	The Application class is used as the bridge between the technical and graphical parts of this project.
 * 	Application only requires a FileReader so it can reach it when the user either requires the visualization of the Excel file
 * 	or wants to apply a rule/tool.
 * 	This class is creates a new GUI (that receives an object of this class) and calls a method that starts the GUI.
 *
 */

public class Application {

	private GUI gui;
	private FileReader fr;

	public Application(FileReader fr) {
		this.gui = new GUI(this);
		this.fr = fr;
	} 

	/**
	 * Returns the FileReader that was created when the Application is initiated. 
	 * @return this application's FileReader. 
	 */
	
	public FileReader getFileReader() {
		return this.fr;
	}

	Application() throws MalformedURLException {
		gui.createAndShowGUI();
	}

	public static void main(String[] args) throws IOException {
		Application app = new Application(new FileReader());

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Application();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
