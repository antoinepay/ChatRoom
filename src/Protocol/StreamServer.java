package Protocol;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StreamServer extends Remote {
	
	public void MessageDistribution(String message) throws RemoteException;
	public void welcome() throws RemoteException;
	public void AddClient(StreamClient client) throws RemoteException;
	public void LogOutClient(StreamClient client) throws RemoteException;
	
}
