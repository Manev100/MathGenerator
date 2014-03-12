package com.marcneveling.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.marcneveling.main.Generator;
import com.marcneveling.main.MathModel;
import com.marcneveling.main.MathRobot;
import com.marcneveling.main.PageModel;

public class MainFrame extends JFrame {

	private JButton generateButton;
	private CountDownPane glasspane; 	
	private Generator generator;
	
	public MainFrame() {
		super("Math Generator");
		generator = new Generator(new MathModel(), new PageModel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 250);
		setLocationRelativeTo(null);
		setResizable(false);
		setGlassPane(glasspane = new CountDownPane());
		init();
		
		
		setVisible(true);
		
	}

	private void init() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		generateButton = new JButton(new GenerateAction());
		generateButton.setText("Go!");
		generateButton.setFocusable(false);
		
		mainPanel.add(new SetupPanel(generator.getMathModel(), generator.getPageModel()),BorderLayout.CENTER);
		mainPanel.add(generateButton, BorderLayout.SOUTH);
		
		setContentPane(mainPanel);
	}
	
	public void showSetupView() {
		getGlassPane().setVisible(false);
	}
	
	public void showCountDown() {
		
		getGlassPane().setVisible(true);
		startDownloadThread();
	}
	
	private void startDownloadThread(){
		Thread countdown = new Thread(new Runnable(){

			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					glasspane.countDown();
				}
				glasspane.setVisible(false);
				glasspane.reset();
				generator.generateIt(new MathRobot());
				
			}
		});	
		countdown.start();
	}
	
	private class GenerateAction extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			TransitionManager.showCountDown();
		}
	}
}
