package csc212;

class DocRanking { 

    int id;
    int rank;
    
    public DocRanking(int id, int rank) {
        this.id = id;
        this.rank = rank;
    }
    
    public void display() {
        System.out.println("ID: "+id+"         "+"Score: "+rank);
    }

}

public class Ranking { 

    static String query;
    static index doc;
    static InvertedIndexBST wordsListBST;
    static LinkedList<Integer> qDocs;
    static LinkedList<DocRanking> rankedDocs;
    
    public Ranking(String query, index doc, InvertedIndexBST wordsListBST) {
        this.query = query;
        this.doc = doc;
        this.wordsListBST = wordsListBST;
        qDocs = new LinkedList<Integer> ();
        rankedDocs = new LinkedList<DocRanking> ();
    }
    
    public static Document getDoc(int id) {
        return doc.getDoc(id);
    }
    
    public static int frequency(String word, Document doc) {
        int f = 0;
        LinkedList<String> words = doc.words;
        if(words.empty())
            return 0;
        
        words.findFirst();
        
        while(!words.last()) {
            if(words.retrieve().equalsIgnoreCase(word))
                f++;
            
            words.findNext();
        }
        
        if(words.retrieve().equalsIgnoreCase(word))
            f++;
        return f;
    }
    
    public static int getRank(Document doc, String q) {
        if(q.length() == 0)
            return 0;
        
        int f = 0;
        String words[] = q.split(" ");
        for(int i = 0 ; i < words.length ; i++) 
            f += frequency(words[i].toLowerCase(), doc);
        
        return f;
    }
        
    public static void rankedQuery(String q) {
        LinkedList<Integer> docsID = new LinkedList<Integer> ();
        if(q.length() == 0)
            return;
        
        String words[] = q.split(" ");
        boolean found = false;
        for(int i = 0 ; i < words.length ; i++) {
            found = wordsListBST.searchWord(words[i].toLowerCase());
            if(found)
                docsID = ((Word)wordsListBST.wordsListBST.retrieve()).docsIDList;
            sortedList(docsID);
        }
    }
    
    public static void sortedList(LinkedList<Integer> docsID) {
        if(docsID.empty()) 
            return;
        
        docsID.findFirst();
        while(!docsID.empty()) {
            boolean found = exist(qDocs,docsID.retrieve());
            if(!found) 
                insertIDSorted(docsID.retrieve());
            
            if(!docsID.last())
                docsID.findNext();
            else
                break;
        }
    }
    
    public static void insertIDSorted(Integer id) {
        if(qDocs.empty()) {
            qDocs.insert(id);
            return;
        }
        
        qDocs.findFirst();
        while(!qDocs.last()) {
            if(id < qDocs.retrieve()) {
                Integer updated = qDocs.retrieve();
                qDocs.update(id);
                qDocs.insert(updated);
                return;
            }
            else 
                qDocs.findNext();
        }
        
        if(id < qDocs.retrieve()) {
            Integer updated = qDocs.retrieve();
            qDocs.update(id);
            qDocs.insert(updated);
            return;
        }
        else 
            qDocs.insert(id);     
    }
    
    public static void insertDocSorted() {
        rankedQuery(query);
        if(qDocs.empty()) {
            System.out.println("No query");
            return;
        }
        
        qDocs.findFirst();
        while(!qDocs.last()) {
            Document doc = getDoc(qDocs.retrieve());
            int rank = getRank(doc,query);
            insertRankedDoc(new DocRanking(qDocs.retrieve(),rank));
            qDocs.findNext();
        }
        
        Document doc = getDoc(qDocs.retrieve());
        int rank = getRank(doc,query);
        insertRankedDoc(new DocRanking(qDocs.retrieve(),rank));
    }
    
    public static void insertRankedDoc(DocRanking rd) {
        if(rankedDocs.empty()) {
            rankedDocs.insert(rd);
            return;
        }
        
        rankedDocs.findFirst();
        while(!rankedDocs.last()) {
            if(rd.rank > rankedDocs.retrieve().rank) {
                DocRanking updated = rankedDocs.retrieve();
                rankedDocs.update(rd);
                rankedDocs.insert(updated);
                return;
            }
            else 
                rankedDocs.findNext();
        }
        
        if(rd.rank > rankedDocs.retrieve().rank) {
            DocRanking updated = rankedDocs.retrieve();
            rankedDocs.update(rd);
            rankedDocs.insert(updated);
            return;
        }
        else 
            rankedDocs.insert(rd); 
    }
    
    public static boolean exist(LinkedList<Integer> docsID, Integer id) {
        if(docsID.empty())
            return false;
        
        docsID.findFirst();
        while(!docsID.last()) {
            if(docsID.retrieve().equals(id))
                return true;
            
            docsID.findNext();
        }
        
        if(docsID.retrieve().equals(id))
            return true; 
        
        return false;
    }
    
     public static void display() {
        if(rankedDocs.empty()) {
            System.out.println("No documents");
            return;
        }
        
        rankedDocs.findFirst();
        while(!rankedDocs.last()) {
            rankedDocs.retrieve().display();
            rankedDocs.findNext();
        }
        
        rankedDocs.retrieve().display();
    }
            
        
}