package lucene.test;

import java.io.IOException;

import lucene.extra.AnalyzerUtils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class AnalyzerDemo {
	
	private static final String[] examples = {"The quick brown fox jumped over the lazy dog","XY&Z Coperation - xyz@example.com"};
	
	private static final Analyzer[] analyzers = {
		new WhitespaceAnalyzer(),
		new SimpleAnalyzer(),
		new StopAnalyzer(),
		new StandardAnalyzer()
	};
	
	public static void main(String[] args) throws IOException {
		String[] strs = examples;
		if(args.length > 0) {
			strs = args;
		}
		for (String string : strs) {
			analyze(string);
		}
	}
	
	private static void analyze(String text) throws IOException {
		System.out.println("Analyzing \""+text+"\"");
		for (Analyzer analyzer : analyzers) {
			String name = analyzer.getClass().getSimpleName();
			System.out.println(" "+name+" ");
			System.out.println(" ");
//			AnalyzerUtils.displayTokens(analyzer, text);
			AnalyzerUtils.displayTokensWithFullDetails(analyzer, text);
			System.out.println("\n");
		}
	}
	
	

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */