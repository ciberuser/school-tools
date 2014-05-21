package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;
import javax.swing.JOptionPane;

public class userSelectionToBuy extends InfoBead implements Runnable  {



	@Override
	public void handleData(Triplet data) {
		
		 
		 userPreferences p = (userPreferences)data.getInfoItem().getInfoValue(); 
		 
		JOptionPane.showMessageDialog(null,p.IWant2Buy(),"but following items",JOptionPane.YES_NO_OPTION);
		 
		
		
	}

	@Override
	public void initialize() {
		
		Thread buy = new Thread(this, "");
		buy.start();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
