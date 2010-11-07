package Classes.RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Interfaces.IGeneralRMI;


public class CGemeralClientRMI 
{

	public CGemeralClientRMI()
	{
		
	}
	
	public static void Run(String stringId,String host) {

		try {
		    Registry registry = LocateRegistry.getRegistry(host);
		    IGeneralRMI stub = (IGeneralRMI) registry.lookup(stringId);
		    String response = stub.testCon();
		    System.out.println("response: " + response);
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
	    }

}
