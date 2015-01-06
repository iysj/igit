package lucene.extra;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import junit.framework.Assert;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class AnalyzerUtils {
	
	public static class MyToken {
		private String termText; //项
		private int positionIncrement; //位置增量
		private String type; //类型
		private int startOffset; //开始偏移位
		private int endOffset; //结束偏移位
		
		private int xx;
		
		public MyToken(String termText, int positionIncrement, String type,
				int startOffset, int endOffset) {
			super();
			this.termText = termText;
			this.positionIncrement = positionIncrement;
			this.type = type;
			this.startOffset = startOffset;
			this.endOffset = endOffset;
		}
		public String getTermText() {
			return termText;
		}
		public void setTermText(String termText) {
			this.termText = termText;
		}
		public int getPositionIncrement() {
			return positionIncrement;
		}
		public void setPositionIncrement(int positionIncrement) {
			this.positionIncrement = positionIncrement;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getStartOffset() {
			return startOffset;
		}
		public void setStartOffset(int startOffset) {
			this.startOffset = startOffset;
		}
		public int getEndOffset() {
			return endOffset;
		}
		public void setEndOffset(int endOffset) {
			this.endOffset = endOffset;
		}
		
	}
	
	public static MyToken[] tokensFromAnalysis(Analyzer analyzer, String text) throws IOException {
		TokenStream stream = analyzer.tokenStream("content", new StringReader(text));
		CharTermAttribute charTermAttribute = stream.addAttribute(CharTermAttribute.class);
		PositionIncrementAttribute positionIncrementAttribute = stream.addAttribute(PositionIncrementAttribute.class);
		TypeAttribute typeAttribute = stream.addAttribute(TypeAttribute.class);
		OffsetAttribute offsetAttribute = stream.addAttribute(OffsetAttribute.class);
		ArrayList<MyToken> tokenList = new ArrayList<>();
		stream.reset();
		while (stream.incrementToken()) {
			tokenList.add(new MyToken(charTermAttribute.toString(), 
					positionIncrementAttribute.getPositionIncrement(), typeAttribute.type(), offsetAttribute.startOffset(), offsetAttribute.endOffset()));
		}
		stream.end();
		stream.close();
		return (MyToken[]) tokenList.toArray(new MyToken[0]);
	}
	
	public static void displayTokens(Analyzer analyzer,String text) throws IOException {
		MyToken[] tokens = tokensFromAnalysis(analyzer, text);
		for (MyToken myToken : tokens) {
			System.out.println("["+myToken.getTermText()+"] ");
		}
	}
	
	public static void displayTokensWithPositions(Analyzer analyzer, String text) throws IOException {
		MyToken[] tokens = tokensFromAnalysis(analyzer, text);
		int position = 0;
		for (MyToken myToken : tokens) {
			int increment = myToken.getPositionIncrement();
			if(increment > 0) {
				position += increment;
				System.out.println("");
				System.out.println(position+": ");
			}
			System.out.println("["+myToken.getTermText()+"]");
		}
		System.out.println();
	}
	
	public static void displayTokensWithFullDetails(Analyzer analyzer, String text) throws IOException {
		MyToken[] tokens = tokensFromAnalysis(analyzer, text);
		int position = 0;
		for (MyToken token : tokens) {
			int increment = token.getPositionIncrement();
			if(increment > 0) {
				position += increment;
				System.out.println();
				System.out.print(position+": ");
			}
			System.out.print("[" + token.getTermText() + ":" +
					token.getStartOffset() + "->" + 
					token.getEndOffset() + ":" +
					token.getType()+"]");
		}
		System.out.println();
	}
	
	public static void assertTokenEqual(MyToken[] tokens, String[] strings) {
		Assert.assertEquals(tokens.length, strings.length);
		for (int i = 0; i < tokens.length; i++) {
			 Assert.assertEquals("index " + i, strings[i], tokens[i].getTermText());
		}
	}
	
}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */