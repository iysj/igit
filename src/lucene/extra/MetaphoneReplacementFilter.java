package lucene.extra;

import java.io.IOException;

import org.apache.commons.codec.language.Metaphone;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class MetaphoneReplacementFilter extends TokenFilter{
	
	private static final String METAPHOE = "metaphone";
	
	private Metaphone metaphoner=new Metaphone(); //近义词查询算法
	private CharTermAttribute charTermAttribute;
	private TypeAttribute typeAttribute;

	protected MetaphoneReplacementFilter(TokenStream input) {
		super(input);
		charTermAttribute = addAttribute(CharTermAttribute.class);
		typeAttribute = addAttribute(TypeAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		if(!input.incrementToken()) {
			return false;
		} else {
			String encoded;
			encoded = metaphoner.encode(charTermAttribute.toString());
			charTermAttribute.setEmpty();
			charTermAttribute.append(encoded);
			typeAttribute.setType(METAPHOE);
			return true;
		}
	}

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */