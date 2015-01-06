package lucene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.xml.CorePlusExtensionsParser;
import org.apache.lucene.queryparser.xml.ParserException;
import org.apache.lucene.queryparser.xml.QueryTemplateManager;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.xml.sax.SAXException;

public class FormBasedXmlQueryDemo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private IndexSearcher searcher;
	
	private CorePlusExtensionsParser xmlParser;
	
	 private Analyzer analyzer = new StandardAnalyzer();
		
	 private QueryTemplateManager queryTemplateManager;
	/**
	 * Constructor of the object.
	 */
	public FormBasedXmlQueryDemo() {
		super();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Properties completedFormFields = new Properties();
		Enumeration<String> pNames = request.getParameterNames();
		while (pNames.hasMoreElements()) {
			String propName = (String) pNames.nextElement();
			String value = request.getParameter(propName);
			if ( (value != null) && (value.trim().length() > 0)) {
				completedFormFields.setProperty(propName, value);
			}
		}
		try {
			org.w3c.dom.Document xmlQuery = this.queryTemplateManager.getQueryAsDOM(completedFormFields);
			
			Query query = this.xmlParser.getQuery(xmlQuery.getDocumentElement());
			
			TopDocs topDocs = this.searcher.search(query, 10);
			
			if(topDocs != null) {
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;
				Document[] results = new Document[scoreDocs.length];
				for (int i = 0; i < results.length; i++) {
					results[i] = this.searcher.doc(scoreDocs[i].doc);
					request.setAttribute("results", results);
				}
			}
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (SAXException | ParserConfigurationException
				| TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			openExampleIndex();
			String xslFile = config.getInitParameter("xslFile");
			String defaultStandardQueryParserField = config.getInitParameter("defaultStandardQueryParserField");
			this.queryTemplateManager = new QueryTemplateManager(getServletContext().getResourceAsStream("/WEB-INF/"+xslFile));
			this.xmlParser = new CorePlusExtensionsParser(defaultStandardQueryParserField, this.analyzer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void openExampleIndex() throws 	IOException {
		RAMDirectory rd = new RAMDirectory();
		IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LATEST, this.analyzer);
		IndexWriter writer = new IndexWriter(rd, iwConfig);
		InputStream dataIn = getServletContext().getResourceAsStream("/WEB-INF/data.tsv");
		BufferedReader br = new BufferedReader(new InputStreamReader(dataIn));
		String line = br.readLine();
		FieldType textNoNorms = new FieldType(TextField.TYPE_STORED);
		textNoNorms.setOmitNorms(true);
		while(line != null) {
			 line = line.trim();
			 if (line.length() > 0) {
				 StringTokenizer st = new StringTokenizer(line, "\t");
				 Document doc = new Document();
				 doc.add(new Field("location", st.nextToken(), textNoNorms));
				 doc.add(new Field("salary", st.nextToken(), textNoNorms));
				 doc.add(new Field("type", st.nextToken(), textNoNorms));
				 doc.add(new Field("description", st.nextToken(), textNoNorms));
				 writer.addDocument(doc);
			 }
			 line = br.readLine();
		}
		writer.close();
		IndexReader reader = DirectoryReader.open(rd);
		this.searcher = new IndexSearcher(reader);
	}
}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */