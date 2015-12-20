package ServerSide;

import java.awt.Color;

import javax.swing.JFrame;

public class ServerApp extends JFrame  {

	private static final long serialVersionUID = 1L;

	private ServerPanel serverPanel;
	
	public ServerApp(){

		serverPanel = new ServerPanel();
		setContentPane(serverPanel);
		
		setTitle("ChatRoom -Server-");
		setSize(500,800);
		getContentPane().setBackground(new Color(50,50,50));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public ServerPanel getServerPanel() {
		return serverPanel;
	}
}
