package ClientSide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import Graphics.Bouton;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Bouton JButton1;
	private JTextField textArea;
	private ClientApp application;

	public MenuPanel(ClientApp app){
		
		application = app;
		
		setBackground(new Color(35,35,35));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		textArea = new JTextField();
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.insets = new Insets(10,0,0,0);
		textArea.setBackground(new Color(30,30,30));
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		c.anchor = GridBagConstraints.LAST_LINE_END;
		textArea.setPreferredSize(new Dimension(160,20));
		this.add(textArea, c);
		
		JButton1 = new Bouton("Connect");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		JButton1.setPreferredSize(new Dimension(160,25));	
		this.add(JButton1, c);
		
		GestionBouton gest_b = new GestionBouton();
		JButton1.addActionListener(gest_b);
		textArea.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
				// V�rifier que le pseudo est possible et connecter le cas �ch�ant
					if(textArea.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Veuillez sp�cifier votre Username");
					}
					else{
						try {
							application.setUser(new Client(application.getHost(),textArea.getText(),application));
							File f = application.getUser().getHistory();
							BufferedReader buf = new BufferedReader(new FileReader(f));
							String line;
							while((line=buf.readLine()) != null)
							{
								application.getChatPanel().getDisplayArea().display(line);
							}
							buf.close();
							application.getUser().DisplayClientsOnline();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "error client");
							e1.printStackTrace();
						}
						if(application.getUser().isReady()) {
							application.setContentPane(application.getChatPanel());
							application.validate();
							application.getChatPanel().setFocusable(true);
							application.getChatPanel().requestFocus();
						}
						else {
						
							JOptionPane.showMessageDialog(null, "Impossible de se connecter");
						}
					
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

	}

	public JButton getJButton1() {
		return JButton1;
	}

	public JTextField getTextArea() {
		return textArea;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		g2.setFont(getFont().deriveFont((float) 80.0));
		g2.setColor(new Color(30,30,30));
		g2.drawString("ChatRoom",50, 110);
	}
	
	// M�thode de test de nom sur le serveur
		
	private class GestionBouton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == JButton1){	
				
				// V�rifier que le pseudo est possible et connecter le cas �ch�ant
				if(textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez sp�cifier votre Username");
				}
				else{
					try {
						application.setUser(new Client(application.getHost(),textArea.getText(),application));
						File f = application.getUser().getHistory();
						BufferedReader buf = new BufferedReader(new FileReader(f));
						String line;
						while((line=buf.readLine()) != null)
						{
							application.getChatPanel().getDisplayArea().display(line);
						}
						buf.close();
						application.getUser().DisplayClientsOnline();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "error client");
						e1.printStackTrace();
					}
					if(application.getUser().isReady()) {
						application.setContentPane(application.getChatPanel());
						application.validate();
						application.getChatPanel().setFocusable(true);
						application.getChatPanel().requestFocus();
					}
					else {
					
						JOptionPane.showMessageDialog(null, "Impossible de se connecter");
					}
				
				}
			
			}
		}
	
	}

	
}