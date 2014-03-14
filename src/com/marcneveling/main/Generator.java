package com.marcneveling.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.marcneveling.operation.*;

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
		List<MathProblem> problems = generateRandomMathProblems(page.getNumberOfProblems());
		Iterator<MathProblem> problemsIt = problems.iterator();
		for (int i = 0; i < page.getLines(); i++) {
			for (int j = 0; j < page.getColumns(); j++) {
				
				robot.typeMathProblem(problemsIt.next());
				if(j != page.getColumns()-1){
					robot.tab(page.getTabs());
				}else{
					robot.enter();
				}
			}
		}
	}
	
	
	private List<MathProblem> generateRandomMathProblems(int count){
		List<MathProblem> problems = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			problems.add(generateRandomMathProblem());
		}
		return problems;
	}
	
	
	private MathProblem generateRandomMathProblem() {
		List<Integer> constants = new LinkedList<Integer>();
		List<Operation> operations = new LinkedList<Operation>();
		boolean problemFound = false;
		MathProblem problem = null;
		while(!problemFound){
			for (int i = 0; i < math.getNumberOfConstants(); i++) {
				constants.add(randomInt(math.getMinValue(), math.getMaxValue()));
			}	
			for (int i = 0; i < math.getNumberOfConstants()-1; i++) {
				operations.add(randomOperation(Operation.ALL_OPERATIONS));
			}
			
			try{
				problem = new MathProblem(constants, operations);
				if(problem.getResult() >= math.getMinResult() && problem.getResult() <= math.getMaxResult()){
					problemFound = true;
				}else{
					constants.clear();
					operations.clear();
				}
			}catch(IllegalArgumentException e){ 
				// skip to next possible problem 
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
	private Operation randomOperation(List<Operation> list){
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