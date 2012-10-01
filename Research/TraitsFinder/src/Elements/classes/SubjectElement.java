package Elements.classes;

import Services.Logger.ELogLevel;

public class SubjectElement extends  EnumElement
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
	
}
