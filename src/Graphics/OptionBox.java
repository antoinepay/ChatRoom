package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class OptionBox extends JPanel {

	private static final long serialVersionUID = 1L;

	private DisplayArea otherClient;
	private Bouton decoButton;
	
	public OptionBox(){
		
		setBackground(new Color(20,20,20));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		otherClient = new DisplayArea();
		otherClient.setBackground(new Color(20,20,20));
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titleborder = new TitledBorder(border, " Online ",TitledBorder.CENTER,TitledBorder.TOP,
				getFont(),new Color(51,204,0));
		otherClient.setBorder(titleborder);
		c.gridy = 0;
		c.weightx = 1;
		c.weighty =  0.95;
		c.fill = GridBagConstraints.BOTH;

		add(otherClient,c);
		
		decoButton = new Bouton("Déconnexion");
		decoButton.setPreferredSize(new Dimension(120,30));
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		add(decoButton,c);
	}

	// GETTERS =====================
	public Bouton getDecoButton() {
		return decoButton;
	}
	public DisplayArea getDisplayArea()
	{
		return otherClient;
	}
	
	
}
