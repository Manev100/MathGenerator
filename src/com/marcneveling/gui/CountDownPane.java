package com.marcneveling.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class CountDownPane extends JComponent {
	private int countdown;
	private static int TEXT_SIZE = 200;
	
	public CountDownPane(){
		
		countdown = 3;
		
		//intercept mouse and key events, so that underlying UI is disabled
		addMouseListener(new MouseAdapter() { });
		addMouseMotionListener(new MouseMotionAdapter() { });
		addKeyListener(new KeyAdapter() { });
		// focus is on glasspane and not on underlying UI
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent evt) {
				requestFocusInWindow();
			}
		});
		// can't change focus by hiting tab
		setFocusTraversalKeysEnabled(false);
		
		setFont(new Font("Arial",Font.PLAIN, TEXT_SIZE));
	}
	@Override
	protected void paintComponent(Graphics g){
		// enables anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        // gets the current clipping area
        Rectangle area = g.getClipBounds();
        
        // sets a 65% translucent composite
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.65f);
        Composite composite = g2.getComposite();
        g2.setComposite(alpha);
        
        // fills the background
        g2.setColor(Color.RED);
        g2.fillRect(area.x, area.y, area.width, area.height);
        
		//center string
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D rect = fm.getStringBounds("" + countdown, g);
		

		int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
		int panelHeight= this.getBounds().height;
		int panelWidth = this.getBounds().width;
		
		int x = (panelWidth  - textWidth)  / 2;
		int y = (panelHeight - textHeight) / 2  + fm.getAscent();
		
		g2.setColor(Color.BLACK);
		g2.drawString("" + countdown, x, y);
		
		g2.setComposite(composite);
		
	}
	
	
	protected void countDown(){
		countdown--;
		repaint();
	}
	public void reset() {
		countdown = 3;
		
	}
}
