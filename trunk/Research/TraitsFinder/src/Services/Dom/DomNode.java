package Services.Dom;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import Core.BaseCFinder;

public class DomNode extends BaseCFinder
{
	
	public  DomNode()
	{
		m_node = null;
	}
	
	public DomNode(Node node)
	{
		m_node = node;
		m_factory = XPathFactory.newInstance();
		m_xpath = m_factory.newXPath();
	}
	
	
	public Node GetNode(String path)
	{
		try 
		{
			return (Node) m_xpath.evaluate(path, m_node,XPathConstants.NODE);
		} catch (XPathExpressionException e) 
		{
			PrintError(e);
			return null;
		}
	}
	
	public String GetValue()
	{
		if (m_node ==null) return "";
		else
		{
			try
			{
				return 	m_node.getNodeValue();
			}
			catch (Exception e)
			{
				WriteLineToLog("error while try to get node value :  " + e.getMessage());
				return "";

			}
		}
	}
	
	public String GetAttribute(String attribute)
	{
		if (m_node ==null) return "";
		else
		{
			try
			{
				return m_node.getAttributes().getNamedItem(attribute).getNodeValue();	
			}
			catch (Exception e)
			{
				WriteLineToLog("error while try to get node " +attribute +" attribute  :  " + e.getMessage());
				return "";
			}
			
			
		}
	}
	
	public static String GetValue(Node node) throws Exception
	{
		if (node == null) return "";
		else
		{
			return 	node.getNodeValue();
		}
	}
	
	public static String GetAttribute(Node node ,String attribute) throws Exception
	{
		
		if (node ==null) return "";
		else
		{
			return node.getAttributes().getNamedItem(attribute).getNodeValue();	
		}
	}
	
	public static Node GetNode(String path,Node node) throws XPathExpressionException
	{
		return (Node) m_xpath.evaluate(path, node,XPathConstants.NODE);
	}
	
	public static Node GetNode(String path,Document doc) throws XPathExpressionException
	{
		return (Node) m_xpath.evaluate(path, doc,XPathConstants.NODE);
	}
	 
	
	private void PrintError(Exception e)
	{
		WriteLineToLog("Error: " +e.getMessage());
		WriteLineToLog(e.getStackTrace().toString());
		
	}
	
	private XPathFactory m_factory;
	private Node m_node;
	private static XPath m_xpath;

}