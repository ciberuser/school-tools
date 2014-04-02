package modelExport;

import java.awt.EventQueue;
import models.ActivatedModel;


public class ActivatedModelMain {
	
	/**
	 * Launch the model.
	 */
	public static void main(String[] args) {
					
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					ActivatedModel am = new ActivatedModel();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
