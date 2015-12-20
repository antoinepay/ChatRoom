package Protocol;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface StreamClient extends Remote {

	public void Callback(String message) throws RemoteException;
	public String getUserName() throws RemoteException;	
	public void History(File f) throws RemoteException;
	public void setOnlineClients(LinkedList<StreamClient> client) throws RemoteException;
	public void RefreshListOnlineClients() throws RemoteException;
}
