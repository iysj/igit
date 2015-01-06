package lucene.test;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import junit.framework.TestCase;

public class FuzzyQueryTest extends TestCase{
	
	private Directory dir;
	
	private void indexSingleFieldDocs(TextField[] fields) throws Exception {
	    dir = new RAMDirectory();
		IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(Version.LATEST, new WhitespaceAnalyzer()));
		for (Field field : fields) {
			Document doc = new Document();
			doc.add(field);
			writer.addDocument(doc);
		}
		writer.close();
	}
	
	public void testFuzzy() throws Exception {
		indexSingleFieldDocs(new TextField[] {new TextField("content", "fuzzy", Field.Store.YES),new TextField("content", "wuzzy", Field.Store.YES)});
		IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(dir));
//		FuzzyQuery query = new FuzzyQuery(new Term("content", "wuzz"));
		QueryParser parser = new QueryParser("content", new WhitespaceAnalyzer());
		Query parse = parser.parse("wuzz~");
		TopDocs docs = searcher.search(parse, 10);
		for (ScoreDoc scoreDoc : docs.scoreDocs) {
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println(doc.get("content"));
		}
		
		
	}
}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */