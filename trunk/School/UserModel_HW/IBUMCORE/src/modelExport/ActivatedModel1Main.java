package modelExport;

import java.awt.EventQueue;


public class ActivatedModel1Main {
	
	/**
	 * Launch the model.
	 */
	public static void main(String[] args) {
					
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					ActivatedModel1 am = new ActivatedModel1();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
