package com.marcneveling.main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Iterator;
import java.util.List;

public class CopyToClipBoardGenerator implements Generator{
	private MathModel math;
	private PageModel page;
	
	public CopyToClipBoardGenerator(MathModel math, PageModel page) {
		this.math = math;
		this.page = page;
	}

	@Override
	public void generateIt() {
		MathProblemFactory factory = new MathProblemFactory();
		List<MathProblem> problems = factory.generateRandomMathProblems(page.getNumberOfProblems(), math, page.getNumberOfProblems());
		Iterator<MathProblem> problemsIt = problems.iterator();
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < page.getLines(); i++) {
			for (int j = 0; j < page.getColumns(); j++) {
				if(problemsIt.hasNext()){
					builder.append(problemsIt.next().toStringHideResult());
					if(j != page.getColumns()-1){
						for (int j2 = 0; j2 < page.getTabs(); j2++) {
							builder.append("\t");
						}
					}else{
						builder.append("\n");
					}
				}
			}
		}
		copyToClipboard(builder.toString());
	}
	
	
	private void copyToClipboard(String text){
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}
	
	@Override
	public MathModel getMathModel() {
		return math;
	}

	@Override
	public void setMathModel(MathModel math) {
		this.math = math;
		
	}

	@Override
	public PageModel getPageModel() {
		return page;
	}

	@Override
	public void setPageModel(PageModel page) {
		this.page = page;
		
	}
	
	public String toString(){
		return "Copy to Clipboard Generator";
	}

}

