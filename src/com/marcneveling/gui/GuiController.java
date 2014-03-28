package com.marcneveling.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;

import com.marcneveling.main.Generator;
import com.marcneveling.main.GeneratorFactory;
import com.marcneveling.main.KeyStrokesGenerator;
import com.marcneveling.main.MathModel;
import com.marcneveling.main.PageModel;
import com.marcneveling.operation.Operation;
import com.marcneveling.operation.Plus;

public class GuiController {
	private MainFrame mainFrame;
	private MathModel math;
	private PageModel page;
	private GeneratorFactory generatorFac;
	private Generator generator;
	
	public GuiController(MathModel math, PageModel page) {
		this.math = math;
		this.page = page;
		
		generatorFac = new GeneratorFactory(math, page);
		
		this.generator = generatorFac.getKeyStrokesGenerator();
		this.mainFrame = TransitionManager.buildMainFrame(math, page, this);
	}
	
	public List<Generator> getGenerators(){
		return generatorFac.getAllGenerators();
	}
	
	public void startGenerating(){
		generator.generateIt();
	}
	
	private void addOperation(Operation op){
		math.addOperation(op);
	}
	
	private boolean removeOperation(Operation op){
		return math.removeOperation(op);
	}
	
	
	public ColorChangeAction getColorChangeAction(Operation op) {
		return new ColorChangeAction(op);
	}
	
	private class ColorChangeAction extends AbstractAction{
		private Operation op;
		public ColorChangeAction(Operation op){
			super("" + op.getSign());
			this.op = op;
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JToggleButton source = (JToggleButton)e.getSource();
			if(source.isSelected()){
				source.setBackground(Color.GREEN);
				addOperation(op);
			}else{
				if(removeOperation(op)){
					source.setBackground(Color.RED);
				}else{
					source.setSelected(false);
				}
			}
		}
	}
	
	public ChangeGeneratorAction getChangeGeneratorAction(){
		return new ChangeGeneratorAction();
	}
	
	private class ChangeGeneratorAction extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<Generator> source = (JComboBox<Generator>)e.getSource();
			generator = (Generator) source.getSelectedItem();
		}
		
	}

}

