package com.marcneveling.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.marcneveling.operation.*;

public class KeyStrokesGenerator implements Generator{
	
	private MathModel math;
	private PageModel page;
	
	
	public KeyStrokesGenerator(MathModel math, PageModel page) {
		this.math = math;
		this.page = page;
	}
	
	public void generateIt(MathRobot robot){
		MathProblemFactory factory = new MathProblemFactory();
		List<MathProblem> problems = factory.generateRandomMathProblems(page.getNumberOfProblems(), math, page.getNumberOfProblems());
		Iterator<MathProblem> problemsIt = problems.iterator();
		for (int i = 0; i < page.getLines(); i++) {
			for (int j = 0; j < page.getColumns(); j++) {
				if(problemsIt.hasNext()){
					robot.typeMathProblem(problemsIt.next());
					if(j != page.getColumns()-1){
						robot.tab(page.getTabs());
					}else{
						robot.enter();
					}
				}
			}
		}
	}
	
	public void generateIt(){
		generateIt(new MathRobot());
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
	
	public String toString(){
		return "Key Strokes Generator";
	}
}	