package com.marcneveling.main;

import java.util.LinkedList;
import java.util.List;

public class GeneratorFactory {
	private KeyStrokesGenerator ksGenerator;
	private CopyToClipBoardGenerator ctcGenerator;
	private List<Generator> generators;
	
	
	public GeneratorFactory(MathModel math, PageModel page) {
		generators = new LinkedList<>();
		ksGenerator = new KeyStrokesGenerator(math, page);
		ctcGenerator = new CopyToClipBoardGenerator(math, page);
		
		generators.add(ksGenerator);
		generators.add(ctcGenerator);
	}
	
	public Generator getKeyStrokesGenerator(){
		return ksGenerator;
	}
	
	public List<Generator> getAllGenerators(){
		return generators;
	}
}
