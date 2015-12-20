package ClientSide;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Graphics.Bouton;
import Graphics.DisplayArea;
import Graphics.OptionBox;
import ServerSide.Server;

public class ChatPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private ClientApp application;
	
	private OptionBox optionBox;
	private JPanel chat;
	private DisplayArea displayArea;
	private JTextField writtingArea;
	private Bouton sendButton;
	private FileReader fr;
	
	public ChatPanel(ClientApp app) {
		
		application = app;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		// Option Box -------------------------------------------
		optionBox = new OptionBox();
		c.gridx = 0;
		c.weightx = 0;
		c.weighty =  1;
		c.fill = GridBagConstraints.BOTH;
		add(optionBox, c);
		
		// Chat --------------------------------------------------
		chat = new JPanel();
		chat.setBackground(new Color(40,40,40));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		chat.setBorder((BorderFactory.createCompoundBorder(border, 
	            BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		c.gridx = 1;
		c.weightx = 0.8;
		add(chat, c);
		
		GridBagConstraints g = new GridBagConstraints();
		chat.setLayout(new GridBagLayout());
		
		// Display area -----------------------------------------
		displayArea = new DisplayArea();
		g.gridy = 0;
		g.gridx = 0;
		g.gridwidth = 2;
		g.weightx = 1;
		g.weighty = 0.995;
		g.fill = GridBagConstraints.BOTH; 
		chat.add(displayArea,g);

		// Writting Area ---------------------------------------------
		writtingArea = new JTextField();
		writtingArea.setBackground(new Color(5,5,5));
		writtingArea.setForeground(new Color(255,255,255));
		border = BorderFactory.createLineBorder(new Color(80,80,80));
		writtingArea.setBorder((BorderFactory.createCompoundBorder(border, 
	            BorderFactory.createEmptyBorder(5, 5, 5, 5))));
		g.gridy = 1;
		g.gridx = 0;
		g.gridwidth = 1;
		g.weightx = 0.9;
		g.weighty = 0.005; 
		chat.add(writtingArea,g);
		
		// Bouton send ---------------------------------------------------
		sendButton = new Bouton("Envoyer");
		g.gridy = 1;
		g.gridx = 1;
		g.weightx = 0.1;
		g.weighty = 0.005; 
		chat.add(sendButton,g);
		
		// Listenners
		GestionBouton gest_b = new GestionBouton();
		optionBox.getDecoButton().addActionListener(gest_b);
		sendButton.addActionListener(gest_b);
	}
	
	private class GestionBouton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == optionBox.getDecoButton()){	
				
				try{
				application.getUser().Logout();
				}
				catch (RemoteException e2)
				{
					System.err.println("Failed to logout");
				}
				application.setContentPane(application.getMenuPanel());
				application.validate();
				application.getMenuPanel().setFocusable(true);
				application.getMenuPanel().requestFocus();
			}
			else if (e.getSource() == sendButton){
				try {
					
					String message = "<" + application.getUser().getUserName() + ">" + writtingArea.getText();
					application.getUser().getChannel().MessageDistribution(message);
					writtingArea.setText("");
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
					displayArea.display("Message error : not transmitted");
				};
			}
			
		}
	}

	

	public OptionBox getOptionBox() {
		return optionBox;
	}

	public JPanel getChat() {
		return chat;
	}

	public DisplayArea getDisplayArea() {
		return displayArea;
	}

	public JTextField getWrittingArea() {
		return writtingArea;
	}

	public Bouton getSendButton() {
		return sendButton;
	}
	

}
