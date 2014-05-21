package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;
import javax.swing.JOptionPane;

public class userSelectionToBuy extends InfoBead   {



	@Override
	public void handleData(Triplet data) 
	{
		System.out.println("got somthing... "+data.getId());
		 
		 userPreferences p = (userPreferences)data.getInfoItem().getInfoValue(); 
		 
		JOptionPane.showMessageDialog(null,p.IWant2Buy(),"but following items",JOptionPane.YES_NO_OPTION);
		 
		
		
	}

	@Override
	public void initialize() {
		
		
		
	}
	
	

}
