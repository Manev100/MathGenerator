package com.marcneveling.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.marcneveling.operation.Operation;

public class MathProblemFactory {
	private Random rand;
	
	public MathProblemFactory() {
		rand = new Random();
	}

	public List<MathProblem> generateRandomMathProblems(int numberOfProblems, MathModel math, int count) {
		List<MathProblem> problems = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			problems.add(generateRandomMathProblem(math));
		}
		return problems;
	}
	
	private MathProblem generateRandomMathProblem(MathModel math) {
		List<Integer> constants = new LinkedList<Integer>();
		List<Operation> operations = new LinkedList<Operation>();
		boolean problemFound = false;
		MathProblem problem = null;
		while(!problemFound){
			for (int i = 0; i < math.getNumberOfConstants(); i++) {
				constants.add(randomInt(math.getMinValue(), math.getMaxValue()));
			}	
			for (int i = 0; i < math.getNumberOfConstants()-1; i++) {
				operations.add(randomOperation(math.getOperations()));
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
}
		
