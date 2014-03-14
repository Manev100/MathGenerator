package com.marcneveling.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.marcneveling.operation.Div;
import com.marcneveling.operation.Operation;

public class MathProblem {
	
	private List<Integer> constants;
	private List<Operation> operations;
	private int result;
	
	public MathProblem(List<Integer> constants, List<Operation> operations) throws IllegalArgumentException{
		if(operations.isEmpty() || constants.isEmpty()){
			throw new IllegalArgumentException("constants and operations cannot be empty!");
		}
		if(constants.size() == operations.size() + 1){
			this.constants = constants;
			this.operations = operations;
			try{
				this.result = solve();
			}catch(IllegalStateException e){
				throw new IllegalArgumentException("Problem includes a division by 0 and therefore cannot be solved!");
			}
		}else{
			throw new IllegalArgumentException("Number of constants must be equal to the number of operations plus 1: 1 + 2 - 3 -> 3 constants 2 operations ");
		}
	}
	
	private int solve() throws IllegalStateException{
		int result = 0;
		// clone constants and operations
		List<Integer> constantsCopy = new LinkedList<>(constants);
		List<Operation> operationsCopy = new LinkedList<>(operations);
		
		// compute * and /
		for (int i = 0; i < operationsCopy.size(); i++) {
			Operation op = operationsCopy.get(i);
			if(op.isPrioritized()){
				// check for division by 0
				if(op.equals(new Div()) && constantsCopy.get(i+1) == 0){
					throw new IllegalStateException("Cannot Solve because of division by 0!");
				}
				
				// merge this part-problem and store result
				constantsCopy.set(i, op.result(constantsCopy.get(i), constantsCopy.get(i+1)));
				//remove the part-problem bc it has been computed
				constantsCopy.remove(i+1);
				operationsCopy.remove(i);
				//prevent skipping the next operation because the current one got removed
				i--;
			}
		}
		
		// compute + and -
		result = constantsCopy.get(0);
		for (int i = 0; i < operationsCopy.size(); i++) {
			Operation op = operationsCopy.get(i);
			result = op.result(result, constantsCopy.get(i+1)); 
		}
		return result;
	}

	public Iterator<Integer> getConstantsIterator(){
		return constants.iterator();
	}
	
	public Iterator<Operation> getOperationsIterator(){
		return operations.iterator();
	}

	public int getResult() {
		return result;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		Iterator<Integer> constantsIt = getConstantsIterator();
		Iterator<Operation> operationsIt = getOperationsIterator();
		while(operationsIt.hasNext()){
			str.append(constantsIt.next());
			str.append(operationsIt.next().getSign());
		}
		str.append(constantsIt.next());
		str.append("=");
		str.append(result);
		return str.toString();
	}
}
		
