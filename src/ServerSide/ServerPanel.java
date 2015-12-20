package ServerSide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Graphics.Bouton;
import Graphics.DisplayArea;

public class ServerPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private DisplayArea displayArea;
	private DisplayArea ListCo;
	private JPanel ZoneButton;
	private Bouton decoButton;

	public ServerPanel(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		displayArea = new DisplayArea();
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 0.75;
		c.weighty = 0.75;
		c.fill = GridBagConstraints.BOTH; 
		add(displayArea,c);
		
		ListCo = new DisplayArea();
		ListCo.setBackground(new Color(30,30,30));
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titleborder = new TitledBorder(border, " List Connected Client ",TitledBorder.CENTER,TitledBorder.TOP,
				getFont(),new Color(50,210,150));
		ListCo.setBorder(titleborder);
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 0.25;
		c.weighty = 0.25;
		add(ListCo,c);
		
		ZoneButton = new JPanel();
		ZoneButton.setBackground(new Color(40,40,40));
		ZoneButton.setPreferredSize(new Dimension(600,100));
		c.gridy = 2;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		add(ZoneButton,c);
		
		decoButton = new Bouton("Stop");
		decoButton.setPreferredSize(new Dimension(120,30));
		ZoneButton.add(decoButton);
		
		GestionBouton gest_b = new GestionBouton();
		decoButton.addActionListener(gest_b);
	}
	
	private class GestionBouton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == decoButton){
				Server.getHistory().delete();
				System.err.println("Server Closed");
				System.exit(0);
			}
		}
	}

	public DisplayArea getDisplayArea() {
		return displayArea;
	}
	public DisplayArea getListCoArea(){
		return ListCo;
	}

	public JPanel getZoneButton() {
		return ZoneButton;
	}

	public Bouton getDecoButton() {
		return decoButton;
	}
}
