package Graphics;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DisplayArea extends JScrollPane {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea display;

	public DisplayArea() {
		
		display = new JTextArea();
		display.setWrapStyleWord(true); 
		display.setBackground(new Color(15,15,15));
		display.setBorder((BorderFactory.createCompoundBorder(null, 
	            BorderFactory.createEmptyBorder(0, 10, 10, 10))));
		display.setEditable(false);
		display.setForeground(new Color(250,250,250));
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		setViewportView(display);
		setBackground(new Color(50,50,50));
		setOpaque(false);
		
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titleborder = new TitledBorder(border, " Chat Log ",TitledBorder.LEFT,TitledBorder.TOP,
				getFont(),new Color(100,210,240));
		setBorder(titleborder);
		
	}
	
	public void display(String message) {
		
		String text = display.getText() + "\n";
		text = text + message;
		display.setText(text);
		
	}
	public void CleanDisplay(){
		display.setText("");
	}

	public JTextArea getDisplay() {
		return display;
	}
	
}
