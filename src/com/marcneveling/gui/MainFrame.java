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

import com.jgoodies.binding.PresentationModel;
import com.marcneveling.main.KeyStrokesGenerator;
import com.marcneveling.main.MathModel;
import com.marcneveling.main.MathRobot;
import com.marcneveling.main.PageModel;

public class MainFrame extends JFrame {

	private JButton generateButton;
	private CountDownPane glasspane; 	
	private MathModel math;
	private PageModel page;
	private GuiController controller;

	
	public MainFrame(MathModel math, PageModel page, GuiController controller) {
		super("Math Generator");
		this.math = math;
		this.page = page;
		this.controller = controller;
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
		
		mainPanel.add(new SetupPanel(math, page, controller),BorderLayout.CENTER);
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
				controller.startGenerating();
				
			}
		});	
		countdown.start();
	}
	
	private class GenerateAction extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(math.toString());
			TransitionManager.showCountDown();
		}
	}
}
