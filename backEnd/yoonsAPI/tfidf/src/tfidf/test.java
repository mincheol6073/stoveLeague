//package tfidf;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class test {
//	
//	
// 
//
//    public double idf(List<List<String>> docs, String term) {
//        double n = 0;
//        for (List<String> doc : docs) {
//            for (String word : doc) {
//                if (term.equalsIgnoreCase(word)) {
//                    n++;
//                    break;
//                }
//            }
//        }
//        return Math.log(docs.size() / n);
//    }
//
//    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
//        return tf(doc, term) * idf(docs, term);
//
//    }
//
//    public static void main(String[] args) {
//
//        List<String> doc1 = Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum");
//        List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
//        List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
//        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);
//
//        test calculator = new test();
//        for(int i = 0; i<doc1.size(); i++) {
//        	double tfidf = calculator.tfIdf(doc1, documents, doc1.get(i));
//        	System.out.println("TF-IDF("+doc1.get(i)+") = " + tfidf);
//        }
//        
//        
//        
//
//    }
//
//
//}
