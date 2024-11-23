package csc212;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

    public class Driver{
                
        int token = 0;
        int tokenDocsNum = 0;
        LinkedList<String> stopWords;
        static index index;
        InvertedIndex wordsList;
        InvertedIndexBST wordsListBST;
        LinkedList<String> unique = new LinkedList<> ();
        
        
    public Driver(){
        stopWords=new LinkedList<>();
        index =new index();
        wordsList=new InvertedIndex(); 
        wordsListBST=new InvertedIndexBST();
    }
        
    public void LoadStopWords(String fileName)
    {
       try{
           File f = new File(fileName);
           if (!f.exists() || !f.isFile()) {
                System.out.println("File not found: " + fileName);
                return;
            }
           Scanner s = new Scanner(f);
           
           while(s.hasNextLine()) {
               String line= s.nextLine();
               stopWords.insert(line);
           }
       }
       catch(IOException e) {
           e.printStackTrace();
       }
    }
    
    public void LoadDocs(String fileName){
        String line=null;
        try{
            File f = new File(fileName);
            if (!f.exists() || !f.isFile()) {
                System.out.println("File not found: " + fileName);
                return;
            }
            Scanner s = new Scanner(f);
            s.nextLine();
            
            while(s.hasNextLine()) {
     
                line= s.nextLine();
                if (line.trim().length() == 0) {
                    System.out.println("Empty line");
                    break;
                }
                
                String x=line.substring(0,line.indexOf(','));
                int id=Integer.parseInt(x);
                String content =line.substring(line.indexOf(',')+1).trim();
                LinkedList<String> wordsInDoc = wordsLinkedList_index_invertedIndex(content,id);
                index.addDoc(new Document(id,wordsInDoc,content)); 
          }
        }
        catch(Exception e){
              System.out.println("end of file");
        }
    }
    
    public void LoadFiles(String stopFile,String docFile){
        LoadStopWords(stopFile);
        LoadDocs(docFile);
    }
    
    public LinkedList<String> wordsLinkedList_index_invertedIndex(String content, int id){
       LinkedList<String> wordsInDoc = new LinkedList<String>();
       index_invertedIndex(content,wordsInDoc,id);
       return wordsInDoc;
    }
    
    public void index_invertedIndex(String content,LinkedList<String> wordsInDoc,int id) {
        content = content.replaceAll("\'"," ");
        content = content.replaceAll("-"," ");
        content=content.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
        String[] tokens=content.split(" ");
        token += tokens.length;
        
        LinkedList<String> tokensInDoc = new LinkedList<>();
                
        for(String i: tokens){
            if(!unique.exist(i))
                unique.insert(i);
            
            if (!tokensInDoc.exist(i)) {
                tokensInDoc.insert(i);
                wordsInDoc.insert(i); 
                wordsList.insert(i, id);
                wordsListBST.addWord(i, id);
                    
                Word w = wordsList.wordsList.retrieve();
                w.incrementDocCount();
            }
        }
    }
    
    public void tokenDocCount(String w) {
    
        Word word = wordsList.retrieve(w);
        if (word != null) {
            int docCount = word.docCount;
            System.out.println( w + ": " + docCount + " document(s).");
        }
    }
        
    public boolean exists(String word){
        if(stopWords==null||stopWords.empty())
            return false;
        
        stopWords.findFirst();
        while(!stopWords.last()) {
            if(stopWords.retrieve().equals(word))
                return true;
            
        stopWords.findNext();  
        }
        
        if(stopWords.retrieve().equals(word))
            return true;
        
        return false;   
    }
    
    public void displayDocs(LinkedList<Integer>IDs){
        
        if(IDs.empty()){
            System.out.println("no documents exist");
            return;
        }
        
        IDs.findFirst();
        while(!IDs.last()){
            Document d=index.getDoc(IDs.retrieve());
            if(d!=null)
                System.out.println("Document "+d.id+": "+d.content);
                 
            IDs.findNext();    
        }  
        
        Document d=index.getDoc(IDs.retrieve());
        if(d!=null)
            System.out.println("Document "+d.id+": "+d.content);
                
        System.out.println("");
    }

    public void displaystopWords(){
        stopWords.display();
    }
    
}