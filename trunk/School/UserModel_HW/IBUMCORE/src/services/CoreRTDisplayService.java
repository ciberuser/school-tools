package services;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import genericInfoBead.Triplet;


/**
	 * A  service is an external application. 
	 * It is accessed from a service port. In this case "RTDisplayPort"
	 * View service provides a view of an info-bead triplet
	 * It has The following main parts:
	 * 		1. Initiating the service
	 * 		2. Building the display window
	 * 		3. Getting the data to be viewed by using observer mode
	 * 		4. Deciding on one of three actions:
	 * 			a. Continuous update of the most recent data by using the "Run" button
	 * 			b. Presenting the last data when the "Pause" button was selected
	 * 			c. Explaining the last data when the "Explain" button was selected
	 * 
	 * @author Eyal Dim
	 * @version 1.0
	 */

public class CoreRTDisplayService extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	////////////  Display labels ////////////////
	JLabel lblTimeNow = new JLabel("");  // Current time when information was received for display
	JLabel lblTime = new JLabel("Data not avilable"); // Information time
	JLabel lblValue = new JLabel("Data not avialable"); // Information value
	JLabel lblTimeNow_1 = new JLabel("Current Time:"); // Header
	JLabel lblTime_1 = new JLabel("Data Time:"); // Header
	JLabel lblValue_1 = new JLabel("Type: "); // Header for the value
		
	
	/**
	 * PlayOn returns true if in run mode,
	 * otherwise returns false (for pause or explain mode)
	 */
	private class PlayOn{ 
		
		private Boolean playOn;
		
		protected Boolean getPlayOn() {
			return playOn;
		}
		protected void setPlayOn(Boolean state) {
			this.playOn = state;
		}
	}
	
	final PlayOn playStatus = new PlayOn();
	
	/**
	 * ExpalainOn returns true if in explain mode,
	 * otherwise returns false (for pause or run mode)
	 */
	private class ExplainOn{ 
		
		private Boolean explainOn;
		
		protected Boolean getExplainOn() {
			return explainOn;
		}
		protected void setExplainOn(Boolean state) {
			this.explainOn = state;
		}
	}
	
	final ExplainOn explainStatus = new ExplainOn();
	
	Triplet lastTriplet = new Triplet("empty");

	
	/**
	 * Keeps current time 
	 */
	private class TimeNowString{
		
	    private String timeNow;

		public String getTimeNow() {
			return timeNow;
		}

		public void setTimeNow(){
			Calendar dateTime = Calendar.getInstance();
		    String strDate = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", dateTime.getTime());
			this.timeNow = strDate;
		} 
	    	
	}
	
	final TimeNowString timeNowStr = new TimeNowString();
	

	
	/**
	 * Constructor:
	 * Create the frame that presents the current time, 
	 * 		the information time and the value.
	 * Reacts to three buttons: run, pause, explain
	 * 		Run, when selected, presents recent information each time it is received
	 * 		Pause pauses on the last information displayed when selected
	 * 		Explain present the explanation of how the value was generated when selected
	 */
public CoreRTDisplayService(){

		playStatus.setPlayOn(true);  //Default 
		explainStatus.setExplainOn(false);  //Default 
	
			//////////////// Set window /////////////////////
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 300);
			getContentPane().setLayout(null);
			setTitle("Real Time Display Service");
				
			///////////////  Set Buttons  /////////////////////
			JButton playButton = new JButton("Run");
			playButton.setBounds(60, 180, 145, 35);
			getContentPane().add(playButton);
			playButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					playStatus.setPlayOn(true);
					explainStatus.setExplainOn(false);
				}
			});
				
			JButton pauseButton = new JButton("Pause");
			
			pauseButton.setBounds(235, 180, 145, 35);
			getContentPane().add(pauseButton);
			pauseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					playStatus.setPlayOn(false);
					explainStatus.setExplainOn(false);
				}
			});
				
			JButton explainButton = new JButton("Explain");
			explainButton.setBounds(410, 180, 145, 35);
			getContentPane().add(explainButton);
			explainButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					explainStatus.setExplainOn(true);
						
				}
			});
			
			//////////////  Set initial info to be displayed  /////////////////
			timeNowStr.setTimeNow();
			lblTimeNow.setText("Initialized at: " + timeNowStr.getTimeNow());
			lblTimeNow.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTimeNow.setBounds(400, 12, 350, 37);
			getContentPane().add(lblTimeNow);
	
			lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTime.setBounds(400, 60, 350, 37);
			getContentPane().add(lblTime);
			
			lblValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblValue.setBounds(400, 108, 350, 37);
			getContentPane().add(lblValue);
			
		
			lblTimeNow_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTimeNow_1.setBounds(47, 12, 200, 37);
			getContentPane().add(lblTimeNow_1);
			
			lblTime_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTime_1.setBounds(47, 60, 200, 37);
			getContentPane().add(lblTime_1);
			
			lblValue_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblValue_1.setBounds(47, 108, 300, 37);
			getContentPane().add(lblValue_1);
			
			Thread periodicUpdateThread = new Thread(this,"periodicUpdate");
			periodicUpdateThread.start();				
			

	}		

	
	/**
	 * Ongoing update from info-beads
	 * @param typeS The type of the value presented string
	 * @param valueAndUnitsS  The value presented and its units 
	 * @param timeS The value recording time string
	 * @param explainS The explanation string 
	 * @throws InterruptedException 
	 */
		public void displayS(String typeS, String valueAndUnitsS, String timeS) throws InterruptedException {

			timeNowStr.setTimeNow();
			lblTimeNow.setText("Initialized at: " + timeNowStr.getTimeNow());
			getContentPane().remove(lblTimeNow);
			getContentPane().remove(lblValue_1);
			lblTimeNow.setText(timeNowStr.getTimeNow());
			lblValue_1.setText("Type: " + typeS );
			getContentPane().add(lblTimeNow);
			getContentPane().add(lblValue_1);
			getContentPane().repaint();
	
			/////////// Information update in run state /////////
			if (playStatus.getPlayOn()) {
	
				getContentPane().remove(lblTimeNow);
				getContentPane().remove(lblTime);
				getContentPane().remove(lblValue);
				lblTimeNow.setText(timeNowStr.getTimeNow());
				lblTime.setText(timeS);
				lblValue.setText(valueAndUnitsS);
				lblValue_1.setText("Type: " + typeS );
				getContentPane().add(lblTimeNow);
				getContentPane().add(lblTime);
				getContentPane().add(lblValue);
				getContentPane().repaint();
					
			}
			
	}
	
	
		
	public void displayExplanation(String explainS){
		if (explainStatus.getExplainOn()){
		    explainStatus.setExplainOn(false);
			// create a JTextArea
		    JTextArea textArea = new JTextArea(10, 60);
		    textArea.setText(explainS);
		    textArea.setEditable(false);
	        // wrap a scroll pane around it
	        JScrollPane scrollPane = new JScrollPane(textArea);
	       // display them in a message dialog
		    JOptionPane.showMessageDialog(getContentPane(), scrollPane);
		}
	}
	
	
	/**
	 * Present Triplet data received from an info-bead in a window
	 * @param t the Triplet
	 */
	public void displayTriplet(Triplet t) {
		
		lastTriplet = t;
		String valueS = "No Data";
		String typeS = "Not Available";
		String unitS = "";
		String explainS = "No Data \n";
		String timeS = "Not Available";
		if (t.getInfoItem() != null){
			typeS = t.getInfoItem().getInfoType();
			if (t.getInfoItem().getInfoValue() != null)
			{
				
				valueS = t.getInfoItem().getInfoValue().toString();
			}
			unitS = t.getInfoItem().getInfoUnits();
			explainS = t.getInfoItem().getExplainInfo();
		    timeS = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL", t.getInfoItem().getInferenceTime());
		}
		
		final String typeS1 = typeS;
		final String valueS1 = valueS;
		final String unitS1 = unitS;
		final String timeS1 = timeS;
		final String explainS1 = explainS;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					displayS(typeS1, valueS1+" , "+unitS1, timeS1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		if (explainStatus.getExplainOn()){
			displayExplanation(explainS1);
		};
				
		}




	@Override
	public void run() {
 		while (true){
			try {
				Thread.sleep(997);
				displayTriplet(lastTriplet);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
	}
}
 
