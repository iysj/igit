package lucene.test;

import junit.framework.TestCase;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class NearRealTimeTest  extends TestCase{
	
	public void testNearRealTime() throws Exception {
		RAMDirectory dir = new RAMDirectory();
		IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(Version.LATEST, new StandardAnalyzer()));
		for (int i = 0; i < 10; i++) {
			Document doc = new Document();
			doc.add(new StringField("id", ""+i, Field.Store.YES));
			doc.add(new TextField("text", "aaa bb", Field.Store.YES));
			writer.addDocument(doc);
		}
		DirectoryReader reader = DirectoryReader.open(writer, true);
		IndexSearcher searcher = new IndexSearcher(reader);
		
		TermQuery query = new TermQuery(new Term("text", "aaa"));
		TopDocs docs = searcher.search(query, 1);
		for (ScoreDoc scoreDoc : docs.scoreDocs) {
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println(doc.get("id")+"--------------"+doc.get("text"));
			System.out.println(docs.totalHits);
		}
		writer.deleteDocuments(new Term("id", "7"));
		
		Document document = new Document();
		document.add(new StringField("id", "11", Field.Store.YES));
		document.add(new TextField("text", "bbb", Field.Store.YES));
		writer.addDocument(document);
		DirectoryReader newReader = DirectoryReader.openIfChanged(reader);
		searcher = new IndexSearcher(newReader);
		TopDocs topDocs = searcher.search(query, 10);
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println(doc.get("id")+"--------------"+doc.get("text"));
			System.out.println(topDocs.totalHits);
		}
		query = new TermQuery(new Term("text", "bbb"));
		TopDocs hits = searcher.search(query, 1);
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println(doc.get("id")+"--------------"+doc.get("text"));
			System.out.println(hits.totalHits);
		};
		
		newReader.close();
		writer.close();
	}

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */