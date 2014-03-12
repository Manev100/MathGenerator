package com.marcneveling.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Generator {
	
	private MathModel math;
	private PageModel page;
	private Random rand;
	
	public Generator(MathModel math, PageModel page) {
		this.math = math;
		this.page = page;
		this.rand = new Random();
	}
	
	public void generateIt(MathRobot robot){
		for (int i = 0; i < page.getLines(); i++) {
			for (int j = 0; j < page.getColumns(); j++) {
				
				robot.typeMathProblem(generateRandomMathProblem());
				if(j != page.getColumns()-1){
					robot.tab(page.getTabs());
				}else{
					robot.enter();
				}
			}
		}
	}
	
	
	
	private MathProblem generateRandomMathProblem() {
		List<Integer> constants = new LinkedList<Integer>();
		List<Operations> operations = new LinkedList<Operations>();
		boolean problemFound = false;
		MathProblem problem = null;
		while(!problemFound){
			for (int i = 0; i < math.getNumberOfConstants(); i++) {
				constants.add(randomInt(math.getMinValue(), math.getMaxValue()));
			}	
			for (int i = 0; i < math.getNumberOfConstants()-1; i++) {
				operations.add(randomOperation(math.getOperations()));
			}
			problem = new MathProblem(constants, operations);
			
			if(problem.getResult() >= math.getMinResult() && problem.getResult() <= math.getMaxResult()){
				problemFound = true;
			}else{
				constants.clear();
				operations.clear();
			}
			// maybe implement counter so method interrupts when no suitable math problem can be found
		}
		return problem;
	}
	
	private int randomInt(int min, int max) {
	    return (rand.nextInt((max - min) + 1) + min);
	}
	private Operations randomOperation(List<Operations> list){
		return list.get(randomInt(0, list.size()-1));
	}
	
	public MathModel getMathModel() {
		return math;
	}
	public void setMathModel(MathModel math) {
		this.math = math;
	}
	public PageModel getPageModel() {
		return page;
	}
	public void setPageModel(PageModel page) {
		this.page = page;
	}
	
}
