package editor;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JOptionPane;

import editor.gui.Editor;

public class EditorMain {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		String sloc =  EditorMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String SUrl =EditorMain.class.getProtectionDomain().getCodeSource().getLocation().toString();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				Editor.getInstance().setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		JOptionPane.showMessageDialog(Editor.getInstance(),
				"Welcome to the Info-bead User Model Editor.\n\n" +
				"To start please use the File menu to generate a new model\n or to open an exiting user model or group model.");

	}
	
}
