package infobeadCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class groupWaiterSelection extends InfoBead {


	
	
	
	@Override
	public void handleData(Triplet data) 
	{
		Map<String,Integer> allecationW = new HashMap<String,Integer>(); 
		List<UserPersonalData> groupOfUsers =(List<UserPersonalData>)data.getInfoItem().getInfoValue();
		System.out.println("new group is selected Wailter for service...");
		int maxType =0;
		String selectedType="";
		for(UserPersonalData user:groupOfUsers )
		{
			String lan = user.GetLanguage(); 
			if(!allecationW.containsKey(lan)) 
			{
				allecationW.put(lan, 1);
			}
			else
			{
				int num = allecationW.get(lan);
				allecationW.put(lan, num++);
			}
								
		}
		
		for(Map.Entry<String, Integer> lan: allecationW.entrySet())
		{
			if (maxType<lan.getValue()) {
				maxType = lan.getValue();
				selectedType = lan.getKey();
			}
		}
		System.out.println("new group speek "+selectedType);
		String selected = m_staSelector.GetWaiterKnowLang(selectedType);
		
		if(selected==m_staSelector.NOT_FOUND)
		{
			System.out.println("no waiter found that know "+selectedType +" will call wailter that know english" );
		}
		else
		{
			System.out.println(selected+" know "+selectedType+" so he will serve the group");
		}
				
	}
	@Override
	public void initialize()
	{
		// TODO Auto-generated method stub
		m_staSelector = new StaffSelector();
	}
	private StaffSelector m_staSelector;

}
