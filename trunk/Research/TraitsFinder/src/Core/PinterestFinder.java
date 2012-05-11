package Core;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Services.Logger;

import Interfaces.IElement;
import Interfaces.IFinder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PinterestFinder implements IFinder {

	//final static String USER_ADDRESS_Path = 
	final static String PINTEREST_FINDER = "PinterstFinder";
	String m_FilePath;
	private Document doc;
	
	@Override
	public IElement Find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IElement Find(String item) {
		m_FilePath = item;
		File fXmlFile = new File(item);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			NodeList nodes = doc.getChildNodes();
			
		} 
		catch (Exception e)
		{
			Logger.GetLogger().WriteLine(PINTEREST_FINDER, e.toString());
			Logger.GetLogger().Write(PINTEREST_FINDER, e.toString());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean SaveItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CreateResultsPool(String Path) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
