package lucene.test;

import junit.framework.TestCase;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class PhraseQueryTest extends TestCase{

	private Directory dir;
	private IndexSearcher searcher;
	
	protected void setUp() throws Exception {
		dir = new RAMDirectory();
		IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(Version.LATEST, new WhitespaceAnalyzer()));
		Document doc = new Document();
		doc.add(new TextField("field", "the quick brown fox jumped over the lazy dog", Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
		searcher = new IndexSearcher(DirectoryReader.open(dir));
	}
	
	protected void tearDown() throws Exception {
		dir.close();
	}
	
	private boolean matched(String[] phrase, int slop) throws Exception {
		PhraseQuery query = new PhraseQuery();
		query.setSlop(slop);
		for (String str : phrase) {
			query.add(new Term("field", str));
		}
		TopDocs docs = searcher.search(query, 10);
		return docs.totalHits >0;
	}
	
	public void testSlopComparison() throws Exception {
		setUp();
		String[] phrase = new String[] {"quick","fox"};
		boolean b = matched(phrase, 3);
		System.out.println(b);
		tearDown();
	}
}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */