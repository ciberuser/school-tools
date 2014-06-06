package infobeadCollection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class GroupSongSelection extends InfoBead{

	@Override
	public void handleData(Triplet data)
	{
		int ageAvg=0;
		
		List<UserPersonalData> groupOfUsers =(List<UserPersonalData>)data.getInfoItem().getInfoValue();
		System.out.println("new group is selected music for playing...");
		HashMap<String, Integer> electedType = new HashMap<String,Integer>();
		int maxType =0;
		String selectedType="";
		for(UserPersonalData user:groupOfUsers )
		{
			ageAvg+=user.getAge();
			if (!electedType.containsKey(user.getMusicType()))
			{
				electedType.put(user.getMusicType(), 1);
			}
			else
			{
				int elected = electedType.get(user.getMusicType());
				electedType.put(user.getMusicType(), elected++);
			}
		}
		ageAvg /= groupOfUsers.size();
		MusicSelector ms = new MusicSelector();
		for(Map.Entry<String, Integer> vote: electedType.entrySet())
		{
			if (maxType<vote.getValue()) {
				maxType = vote.getValue();
				selectedType = vote.getKey();
			}
		}
		String selectedSong= ms.ChooseSong(ageAvg, selectedType);
		if (selectedSong.equals(""))
		{
			System.out.println("no song have been selected...");
		}
		else
		System.out.println("group have selected the song  " +selectedSong);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
