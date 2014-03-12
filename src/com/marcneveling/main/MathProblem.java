package com.marcneveling.main;

import java.util.Iterator;
import java.util.List;

public class MathProblem {
	
	private List<Integer> constants;
	private List<Operations> operations;
	private int result;
	
	public MathProblem(List<Integer> constants, List<Operations> operations) {
		if(constants.size() == operations.size() + 1){
			this.constants = constants;
			this.operations = operations;
			this.result = solve();
		}else{
			throw new IllegalArgumentException("Number of constants must be equal to the number of operations plus 1: 1 + 2 - 3 -> 3 constants 2 operations ");
		}
	}
	
	private int solve() {
		int result = 0;
		// compute * and /
		for (int i = 0; i < operations.size(); i++) {
			Operations op = operations.get(i);
			if(op.isPrioritized()){
				// merge this part-problem and store result
				constants.set(i, op.result(constants.get(i), constants.get(i+1)));
				//remove the part-problem bc it has been computed
				constants.remove(i+1);
				operations.remove(i);
				//prevent skipping the next operation because the current one got removed
				i--;
			}
		}
		
		// compute + and -
		result = constants.get(0);
		for (int i = 0; i < operations.size(); i++) {
			Operations op = operations.get(i);
			result = op.result(result, constants.get(i+1)); 
		}
		return result;
	}

	public Iterator<Integer> getConstantsIterator(){
		return constants.iterator();
	}
	
	public Iterator<Operations> getOperationsIterator(){
		return operations.iterator();
	}

	public int getResult() {
		return result;
	}
}
