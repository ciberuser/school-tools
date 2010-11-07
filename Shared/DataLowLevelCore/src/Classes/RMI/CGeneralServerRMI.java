package Classes.RMI;

import java.rmi.RemoteException;
import Interfaces.IGeneralRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.server.*; 
import java.rmi.registry.*;




public class CGeneralServerRMI extends UnicastRemoteObject implements IGeneralRMI {

	
	public CGeneralServerRMI(int port) throws RemoteException {
		super(port);
	}
	
	public CGeneralServerRMI() throws RemoteException {
		super();
	}
	
	 
	@Override
	public String testCon() throws RemoteException
	{
		return "remote test Pass!!!";
	}

	 public static void main(String args[]) {
		 
		 try
		 {
			 CGeneralServerRMI srvObj = new CGeneralServerRMI();
			 IGeneralRMI GeneralRMI  = null;
			 try 
			 {
				GeneralRMI = (IGeneralRMI)UnicastRemoteObject.exportObject(srvObj,0);
			 }
			 catch(RemoteException ex)
			 {
				 if (!ex.toString().contains("object already exported"))
				 {
					 System.err.println("Server exception: " + ex.toString());
					   ex.printStackTrace();
				 }
			 }
			 Registry registry = LocateRegistry.getRegistry();
			 if (GeneralRMI != null)
			 registry.bind("//myLocal/TestRMI",GeneralRMI);
			 System.err.println("Server ready");
		 }
		 catch (Exception e) {
			    System.err.println("Server exception: " + e.toString());
			    e.printStackTrace();
		 }

		 
		 
	 }
}
