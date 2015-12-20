package ClientSide;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import Protocol.StreamClient;
import Protocol.StreamServer;

public class Client extends UnicastRemoteObject implements StreamClient {
		
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private StreamServer channel;
	private Registry registry;
	private ClientApp application;
	private boolean ready;
	private File history;
	private LinkedList<StreamClient> onlineClients;
	
	
	public Client(String host,String name, ClientApp app) throws RemoteException{
		super();
		application = app;	
		userName = name;
		try {
			registry = LocateRegistry.getRegistry(host);
			channel = (StreamServer) registry.lookup("ServerStream");

			channel.AddClient((StreamClient) this);
		    
			ready = true;
		
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ready = false;
		}
	}
	

	// GETTERS ===============================
	public String getUserName() throws RemoteException {
		return userName;
	}

	public void setUserName(String name) {
		userName = name;
	}

	public StreamServer getChannel() {
		return channel;
	}

	public Registry getRegistry() {
		return registry;
	}

	public boolean isReady() {
		
		return ready;
	}

	public ClientApp getApplication() {
		return application;
	}


	@Override
	public void Callback(String message) throws RemoteException {
		
		application.getChatPanel().getDisplayArea().display(message);
		
	}
	public void Logout() throws RemoteException
	{
		channel.LogOutClient(this);
	}
	public void History(File f) throws RemoteException
	{
		history=f;
	}
	public File getHistory()
	{
		return history;
	}
	public void setOnlineClients(LinkedList<StreamClient> client)
	{
		onlineClients=client;
	}
	public void DisplayClientsOnline()
	{
		String username="";
		application.getChatPanel().getOptionBox().getDisplayArea().CleanDisplay();;
		for(int i=0;i<onlineClients.size();i++)
		{
			try {
				username=onlineClients.get(i).getUserName();
				application.getChatPanel().getOptionBox().getDisplayArea().display(username);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public LinkedList<StreamClient> getOnlineClients()
	{
		return onlineClients;
	}


	@Override
	public void RefreshListOnlineClients() throws RemoteException {
		// TODO Auto-generated method stub
		DisplayClientsOnline();
	}
	
     
}
