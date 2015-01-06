package lucene.test;

import lucene.extra.MetaphoneReplacementAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class MetaphoneTest{
	
	@Test
	public void testKoolKat() throws Exception {
		RAMDirectory directory = new RAMDirectory();
		Analyzer analyzer = new MetaphoneReplacementAnalyzer();
		IndexWriter writer = new IndexWriter(directory, new IndexWriterConfig(Version.LATEST, analyzer));
		Document doc = new Document();
		doc.add(new TextField("contents", "cool cat", Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
		
		IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory));
		Query query = new QueryParser("contents", analyzer).parse("kool kat");
		TopDocs topDocs = searcher.search(query, 1);
		int docId = topDocs.scoreDocs[0].doc;
		Document document = searcher.doc(docId);
		System.out.println(document.get("contents"));
	}
}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */