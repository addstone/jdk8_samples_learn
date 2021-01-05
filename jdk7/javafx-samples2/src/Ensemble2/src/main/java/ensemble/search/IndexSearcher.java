package ensemble.search;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.SearchGroup;
import org.apache.lucene.search.grouping.SecondPassGroupingCollector;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.util.Version;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Description:  Class for searching the index
 * Author: LuDaShi
 * Date: 2021-01-05-15:51
 * UpdateDate: 2021-01-05-15:51
 * FileName: IndexSearcher
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class IndexSearcher {

    private final static List<SearchGroup> searchGroups = new ArrayList<SearchGroup>();

    static {
        for (DocumentType dt : DocumentType.values()) {
            SearchGroup searchGroup = new SearchGroup();
            searchGroup.groupValue = dt.toString();
            searchGroup.sortValues = new Comparable[]{5f};
            searchGroups.add(searchGroup);
        }
    }

    private org.apache.lucene.search.IndexSearcher searcher;
    private final Analyzer analyzer;
    private final MultiFieldQueryParser parser;

    public IndexSearcher() {
        try {
            searcher = new org.apache.lucene.search.IndexSearcher(new ClasspathDirectory());
        } catch (IOException e) {
            e.printStackTrace();
        }
        analyzer = new StandardAnalyzer(Version.LUCENE_31);
        parser = new MultiFieldQueryParser(Version.LUCENE_31, new String[]{"name", "description"}, analyzer);
    }

    public Map<DocumentType, List<SearchResult>> search(String searchString) throws ParseException {
        Map<DocumentType, List<SearchResult>> resultMap = new TreeMap<DocumentType, List<SearchResult>>();
        try {
            Query query = parser.parse(searchString);
            final SecondPassGroupingCollector collector = new SecondPassGroupingCollector("documentType", searchGroups,
                Sort.RELEVANCE, null, 5, true, false, true);
            searcher.search(query, collector);
            final TopGroups groups = collector.getTopGroups(0);
            for (GroupDocs groupDocs : groups.groups) {
                DocumentType docType = DocumentType.valueOf(groupDocs.groupValue);
                List<SearchResult> results = new ArrayList<SearchResult>();
                for (ScoreDoc scoreDoc : groupDocs.scoreDocs) {
                    Document doc = searcher.doc(scoreDoc.doc);
                    SearchResult result = new SearchResult(
                        docType,
                        doc.get("name"),
                        doc.get("url"),
                        doc.get("className"),
                        doc.get("package"),
                        doc.get("ensemblePath"),
                        doc.get("shortDescription")
                    );
                    results.add(result);
                }
                resultMap.put(docType, results);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * Simple command line test application
     */
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        IndexSearcher indexSearcher = new IndexSearcher();
        while (true) {
            System.out.println("Enter query: ");
            String line = in.readLine();
            if (line == null || line.length() == -1) {
                break;
            }
            line = line.trim();
            if (line.length() == 0) {
                break;
            }
            Map<DocumentType, List<SearchResult>> results = indexSearcher.search(line);
            for (Map.Entry<DocumentType, List<SearchResult>> entry : results.entrySet()) {
                System.out.println("--------- " + entry.getKey() + " [" + entry.getValue().size() + "] --------------------------------");
                for (SearchResult result : entry.getValue()) {
                    System.out.println(result.toString());
                }
            }
        }
    }
}
