package lucene.extra;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.LetterTokenizer;

public class MetaphoneReplacementAnalyzer extends Analyzer{

	@Override
	protected TokenStreamComponents createComponents(String fileName, Reader reder) {
		LetterTokenizer source = new LetterTokenizer(reder);
		MetaphoneReplacementFilter filter = new MetaphoneReplacementFilter(source);
		return new TokenStreamComponents(source, filter);
	}

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */