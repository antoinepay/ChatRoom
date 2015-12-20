package Graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Bouton extends JButton{
	
	private static final long serialVersionUID = 1L;

	private static GradientPaint  colorScheme_normal = new GradientPaint(0, 0, new Color(102,0,0), 0, 40, new Color(40,0,0));
	
	public Bouton(String title) {
		super(title);
		
		setForeground(Color.WHITE);
		setVerticalTextPosition(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER); 
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		setFocusPainted(false);
		setContentAreaFilled(false);
		
		GestionSouris gest = new GestionSouris();
		addMouseListener(gest);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(colorScheme_normal);
		g2.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g2);
	}
	
	private class GestionSouris implements MouseListener{
		public void mouseClicked(MouseEvent arg0) {	

		}
		public void mouseEntered(MouseEvent arg0) {	
			colorScheme_normal = new GradientPaint(0, 0, new Color(162,0,0), 0, 30, new Color(100,0,0));
		}
		public void mouseExited(MouseEvent arg0) {	
			colorScheme_normal = new GradientPaint(0, 0, new Color(122,0,0), 0, 30, new Color(60,0,0));
		}
		public void mousePressed(MouseEvent arg0) {	
			colorScheme_normal = new GradientPaint(0, 0, new Color(102,0,0), 0, 30, new Color(40,0,0));
		}
		public void mouseReleased(MouseEvent arg0) {		
		}
	}


	
}
