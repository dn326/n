package csc212;

public class Word {
    
    int docCount = 0;
    String text;
    LinkedList<Integer> docsIDList;
    
    public Word(String w)
    {
        text=w;
        docsIDList=new LinkedList<Integer>();
    }
    
    public void addID(int id){
        if(!searchID(id)) 
            docsIDList.insert(id);
    }
    
    public boolean searchID(Integer id){
        if(docsIDList.empty())
            return false;
        
        docsIDList.findFirst();
        while(!docsIDList.last()){
            if(docsIDList.retrieve().equals(id))
                return true;
            docsIDList.findNext();
        }
        
        if(docsIDList.retrieve().equals(id))
            return true;
        
        return false; 
    }
    
    public void incrementDocCount() {
        docCount++;
    }
    
    public LinkedList<Integer> getDocsIDList() {
        return docsIDList;
    }
    
    public void display(){  
         System.out.println("----------------------------------");
         System.out.print("word:" + text);
         System.out.print("[ ");
         docsIDList.display();
         System.out.println("]");
}
    
}