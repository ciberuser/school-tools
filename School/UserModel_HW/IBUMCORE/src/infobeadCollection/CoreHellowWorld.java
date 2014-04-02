	package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;
import genericInfoBead.InfoBead;


/**
 * Example of how a sensor info-bead sends data to a display port connected to it
 * @author Eyal
 * @vesion  1.0;
 *
 */
public class CoreHellowWorld extends InfoBead implements Runnable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void initialize() {
		Thread helloThread = new Thread(this,"HelloWorld");
		helloThread.start();
				
	}

	public void handleData(Triplet tripletTest) {
		
	// no need for a sensor	
	
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Triplet tripletTest = new Triplet("Hello World Info-bead");
		for (int n=1;  n < 15; n++){
			int randomSleep = 500 + (int)(Math.random() * ((2000 - 500) + 1));
			System.out.println("<HelloWorld> value is : " + n + " Sleep: " + randomSleep + "\n");
			Time t = new Time(System.currentTimeMillis());
			InfoItem data = new InfoItem();
			data.setInferenceTime(t);
			data.setExplainInfo("Main >> Testing Display Services");
			data.setInfoType("test");
			data.setInfoUnits("testUnits");
			data.setInfoValue(n);
			tripletTest.setTime(t);
			tripletTest.setInfoItem(data);
			pushData(tripletTest);

			try {
				Thread.sleep(randomSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
