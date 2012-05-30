package Core;

import Interfaces.EUserDataType;
import Interfaces.ISavedUserData;
import Services.AbstractFactory;

public class UserDataFactory extends AbstractFactory
{
	ISavedUserData GetInerface(EUserDataType type)
	{
		switch(type)
		{
			case eUserDataDictonary :
				return new UserDataDictionary();
		}
		return null;
	}
	
}
