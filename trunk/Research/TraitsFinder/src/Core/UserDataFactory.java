package Core;

import Core.Interfaces.EUserDataType;
import Core.Interfaces.ISavedUserData;
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
