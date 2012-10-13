package Elements;

import Elements.IElement;
import Services.Log.ELogLevel;

public class SubjectElement extends  EnumElement implements IElement
{
	
	public SubjectElement(String subjectName)
	{
		super(subjectName);
		//WriteLineToLog("new subject element created subject name= "+subjectName );
		AddProperty(EProperty.name.toString(), subjectName);
	}
	
	@Override
	public Object GetProperty(String ProperyDef) {
		
		if ( EProperty.valueOf(ProperyDef)==null)
		{
			WriteLineToLog("Error no "+ProperyDef+ "is exist ",ELogLevel.WARNING);
			return null;
		}
		return super.GetProperty(ProperyDef);
	}

	@Override
	public void Save() 
	{
		if ( m_serializer == null)
		{
			WriteLineToLog("no serializer for saving process ", ELogLevel.ERROR);	
			return;
		}
		m_serializer.Save();
		
		
	}
	
}
