package csc212;

public class InvertedIndexBST<T> {
    BST<Word> wordsListBST;
    
    public InvertedIndexBST(){
        wordsListBST = new BST<Word>();
    } 
    
    public void add(InvertedIndex inverted ){
        if(inverted.wordsList.empty())
            return;
        
        inverted.wordsList.findFirst();
        while(!inverted.wordsList.last()){
            wordsListBST.insert(inverted.wordsList.retrieve().text , inverted.wordsList.retrieve());
            inverted.wordsList.findNext();
        }
            wordsListBST.insert(inverted.wordsList.retrieve().text , inverted.wordsList.retrieve());
    }
    
    public void addWord(String text,int id){
        if(!searchWord(text)){
            Word w = new Word(text);
            w.docsIDList.insert(id);
            wordsListBST.insert(text, w);
        }else{
            Word exist = wordsListBST.retrieve();
            exist.addID(id);
        }
    }
    
    public boolean searchWord(String w){
        return wordsListBST.findKey(w);
    }
    
    public void display(){
        if(wordsListBST==null){
            System.out.println("null inverted index");
            return;
        }
        else if(wordsListBST.empty()){
            System.out.println("empty inverted index");
            return;
        }
        wordsListBST.inOrder();
        
    }
   
}