package ServerSide;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import Protocol.StreamClient;
import Protocol.StreamServer;

public class Channel implements StreamServer {
	private FileWriter fw;
	public Channel() {
		
	}
	
	@Override
	public void MessageDistribution(String message) throws RemoteException {
		Server.getServerApp().getServerPanel().getDisplayArea().display(message);
		for(int i = 0 ; i < Server.getListClient().size(); i++ ) {
			Server.getListClient().get(i).Callback(message); 
		}
		try{
			fw = new FileWriter(Server.getHistory(),true);
			fw.write(message + "\r\n");
			fw.close();
		}
		catch(IOException e)
		{
			System.err.println("Unable to write message in history");
		}
	}

	

	@Override
	public void AddClient(StreamClient client) throws RemoteException {
		try {	
			Server.getListClient().add(client);
			System.err.println("Client added");
			String username ="<"+ Server.getListClient().getLast().getUserName() + ">";
			Server.getServerApp().getServerPanel().getDisplayArea().display(username + " connected");
			Server.getServerApp().getServerPanel().getListCoArea().display(username);
			Server.getListClient().getLast().History(Server.getHistory());
			for(int i=0;i<Server.getListClient().size();i++){
				Server.getListClient().get(i).setOnlineClients(Server.getListClient());
				Server.getListClient().get(i).RefreshListOnlineClients();
			}
		}
		catch (Exception e) {
			System.err.println("Client add Error");
		}
		
	}
	
	public void LogOutClient(StreamClient client) throws RemoteException{
		try {
			int i = Server.getListClient().indexOf(client);
			if(i!=-1)
			{
				String userName="<" + Server.getListClient().get(i).getUserName() + ">";
				Server.getListClient().remove(i);
				Server.getServerApp().getServerPanel().getDisplayArea().display(userName + " disconnected");
				Server.getServerApp().getServerPanel().getListCoArea().CleanDisplay();
				for(int j=0;j<Server.getListClient().size();j++){
					userName="<" + Server.getListClient().get(j).getUserName()+">";
					Server.getServerApp().getServerPanel().getListCoArea().display(userName);
				}
			}
			
		}
		catch (Exception e){
			System.err.println("Failed to logout");
		}
	}

	@Override
	public void welcome() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
