package csc212;

public class Query {

    static InvertedIndex inverted;
    
    public Query(InvertedIndex inverted) {
        this.inverted = inverted;
    }

    public static LinkedList<Integer> MixedQuery(String Q) {
        LinkedList<Integer> A = new LinkedList<Integer> ();
        LinkedList<Integer> B = new LinkedList<Integer> ();
        
        if(Q.length() == 0)
            return A;
        
        String OR[] = Q.split("OR");
        A = AND(OR[0]);
        for(int i=1 ; i < OR.length ; i++) {
            B = AND(OR[i]);
            A = OR(A,B);
        }
        
        return A;
    }
    
    public static LinkedList<Integer> AND(String Q) {
        LinkedList<Integer> A = new LinkedList<Integer> ();
        LinkedList<Integer> B = new LinkedList<Integer> ();
        String words[] = Q.split("AND");
        if(words.length == 0) 
            return A;
        
        boolean found = inverted.invertedSearch(words[0].trim().toLowerCase());
        if(found)
            A = inverted.wordsList.retrieve().docsIDList;
        
        for(int i = 1 ; i < words.length ; i++) {
            found = inverted.invertedSearch(words[i].trim().toLowerCase());
            if(found)
                B = inverted.wordsList.retrieve().docsIDList;
            
            A = AND(A,B);
        } 
        
        return A;
    }
    
    public static LinkedList<Integer> AND(LinkedList<Integer> A , LinkedList<Integer> B) {
        LinkedList<Integer> result = new LinkedList<Integer> ();
        if(A.empty() || B.empty())
            return result;
          
        A.findFirst();
        while(true) {
            boolean found = exist(result,A.retrieve());
            if(!found) {
                B.findFirst();
               
                while(true) {
                    if(B.retrieve().equals(A.retrieve())) {
                        result.insert(A.retrieve());
                        break;
                    }
                    
                    if(!B.last())
                        B.findNext();
                    else
                        break;
                }
            }
            
            if(!A.last())
                A.findNext();
            else
                break;
        }
        
        return result;
    }
    
    public static LinkedList<Integer> OR(String Q) {
        LinkedList<Integer> A = new LinkedList<Integer> ();
        LinkedList<Integer> B = new LinkedList<Integer> ();
        String words[] = Q.split("OR");
        if(words.length == 0) 
            return A;
        
        boolean found = inverted.invertedSearch(words[0].trim().toLowerCase());
        if(found)
            A = inverted.wordsList.retrieve().docsIDList;
        
        for(int i = 1 ; i < words.length ; i++) {
            found = inverted.invertedSearch(words[i].trim().toLowerCase());
            if(found)
                B = inverted.wordsList.retrieve().docsIDList;
            
            A = OR(A,B);
        } 
        
        return A;
    }
   
    public static LinkedList<Integer> OR(LinkedList<Integer> A , LinkedList<Integer> B) {
        LinkedList<Integer> result = new LinkedList<Integer> ();
        if(A.empty() && B.empty())
            return result;
          
        A.findFirst();
        while(A.empty()) {
            boolean found = exist(result,A.retrieve());
            if(!found) 
                result.insert(A.retrieve());
               
            if(!A.last())
                A.findNext();
            else
                break;  
        }
            
        B.findFirst();
        while(!B.empty()) {
            boolean found = exist(result,B.retrieve());
            if(!found)
                result.insert(B.retrieve());
            
            if(!B.last())
                B.last();
            else
                break;
        }

        return result;
    } 
    
    public static boolean exist(LinkedList<Integer> result , Integer id) {
        if(result.empty())
            return false;
        result.findFirst();
        while(!result.last()) {
            if(result.retrieve().equals(id))
                return true;
        
            result.findNext();
        }
        
        if(result.retrieve().equals(id))
            return true;
        
        return false;
    }
}