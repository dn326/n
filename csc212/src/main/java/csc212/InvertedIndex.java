package csc212;

public class InvertedIndex {
    
    LinkedList<Word> wordsList;
    
    public InvertedIndex(){
        wordsList = new LinkedList<Word>();
    }
       
    public boolean invertedSearch(String w) {
        if(wordsList==null||wordsList.empty())
            return false;
        
        wordsList.findFirst();
        
        while(!wordsList.last()){
            if(wordsList.retrieve().text.equals(w))
                return true;
            
            wordsList.findNext();
        }
        
        if(wordsList.retrieve().equals(w)) 
            return true;
          
        return false;
    }
    
    public void insert(String text, int id){
        
        if(!invertedSearch(text)){
            Word w =new Word(text);
            w.docsIDList.insert(id);
            wordsList.insert(w);  
        }
        else{
            Word exist = wordsList.retrieve();
            exist.docsIDList.insert(id);
        }  
    }
    
    public Word retrieve(String token) {
        wordsList.findFirst();
        while (!wordsList.last()) {
            Word current = wordsList.retrieve();
            if (current.text.equals(token)) 
                return current;
        
            wordsList.findNext();
        }

        Word last = wordsList.retrieve();
        if (last.text.equals(token)) 
            return last;

        return null;
    }    

    public void display(){
        if(wordsList==null || wordsList.empty()){
            System.out.println("No words");
            return;
        }
        
        wordsList.findFirst();
        while(!wordsList.last()){
            wordsList.retrieve().display();
            wordsList.findNext();
        }
        
        wordsList.retrieve().display();
    } 
}