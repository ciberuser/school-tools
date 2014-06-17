package infobeadCollection;

import infobeadCollection.UserPersonalData.EmusicType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class groupWaiterRequest extends InfoBead{

	@Override
	public void handleData(Triplet data)
	{
		List<UserPersonalData> groupOfUsers =(List<UserPersonalData>)data.getInfoItem().getInfoValue();
		String[] groupNames = m_staffSelctor.GetAllWaiters();
		Map<String,Integer> reqestMap = new HashMap<String,Integer>();
		int maxPick =0;
		for(int i =0 ; i< groupOfUsers.size(); ++i)
		{
			int pick = new Random().nextInt(groupNames.length);
			if (!reqestMap.containsKey(groupNames[pick]))
			{
				reqestMap.put(groupNames[pick], 1);
			}
			Integer last = reqestMap.get(groupNames[pick]);
			last++;
			reqestMap.put(groupNames[pick], last);
		  //  m_music= EmusicType.values()[pick];
		}
		String watierName = "";
		for(Map.Entry<String, Integer> watier: reqestMap.entrySet())
		{
			if (maxPick < watier.getValue()) {
				maxPick = watier.getValue();
				 watierName= watier.getKey();
			}
		}
		System.out.println("the group selected "+watierName + " for thier personal waiter");
		
		
	}

	@Override
	public void initialize() {
		m_staffSelctor = new StaffSelector();
		
	}

	StaffSelector m_staffSelctor;
}
