/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * -Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 * Neither the name of Oracle nor the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 */
package ServerSide;

import java.rmi.registry.Registry;
import java.io.File;
import java.io.FileWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import Protocol.StreamClient;
import Protocol.StreamServer;

public class Server {

	private static Registry registry;
	private static ServerApp serverApp;
	private static LinkedList<StreamClient> ListClient = new LinkedList<StreamClient>();
	private static File history;
	private static FileWriter fw;
	
	
	public Server() {
		
    	serverApp = new ServerApp();
        try {
            LocateRegistry.createRegistry(1099);
            Channel channel = new Channel();
            StreamServer stream = (StreamServer) UnicastRemoteObject.exportObject(channel, 0);
            // Bind the remote object's stub in the registry
            registry = LocateRegistry.getRegistry();
            registry.bind("ServerStream", stream);
            history = new File("History.txt");
            fw = new FileWriter(history);
            System.err.println("Server ready");
			
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}

	public static void main(String args[]) {
		
		@SuppressWarnings("unused")
		Server server = new Server();
    }
    
	// M�thode de marche du serveur
    public void run()
    {
    	
    }
    
    
	public static File getHistory() {
		return history;
	}

	public static ServerApp getServerApp() {
		return serverApp;
	}

	public static LinkedList<StreamClient> getListClient() {
		return ListClient;
	}

	
}
