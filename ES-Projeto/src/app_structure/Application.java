package app_structure;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.SwingUtilities;

import gui.GUI;

public class Application {

	private GUI gui;
	private FileReader fr;

	public Application(FileReader fr) {
		this.gui = new GUI(this);
		this.fr = fr;
	} 

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
