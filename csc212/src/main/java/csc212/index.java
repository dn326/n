package csc212;

public class index {
    
    LinkedList<Document> docsList;

    public index(){
    docsList = new LinkedList<Document>();
    }

    public void addDoc(Document d){
        docsList.insert(d);
    }

    public Document getDoc(int id){
        if(docsList.empty()){
            System.out.println("no documents exist");
            return null;
        }

        docsList.findFirst();
        while(!docsList.last()){
            if(docsList.retrieve().id==id)
                return docsList.retrieve();
            docsList.findNext();
        }

        if(docsList.retrieve().id==id)
            return docsList.retrieve();
        
        return null;   
    }
    
    public LinkedList<Integer> getDoc(String word) {
    
        LinkedList<Integer> docID = new LinkedList<Integer> ();
        if(docsList.empty()) {
            System.out.println("No docs");
            return null;
        }
        
        docsList.findNext();
        while(!docsList.last()) {
            if(docsList.retrieve().words.exist(word.toLowerCase()))
                docID.insert(docsList.retrieve().id);
            
            docsList.findNext();
        }
        
        if(docsList.retrieve().words.exist(word.toLowerCase()))
                docID.insert(docsList.retrieve().id);
        
        return docID;
    }
            

    public void display(){
        if(docsList==null || docsList.empty()){
            System.out.println("No docs");
            return;
        }

        docsList.findFirst();
        while(!docsList.last()){
            Document doc=docsList.retrieve();
            System.out.println("\n--------------------------------------");
            System.out.println("ID"+doc.id);
            doc.words.display();

            docsList.findNext();
        }

        Document doc=docsList.retrieve();
        System.out.println("\n--------------------------------------");
        System.out.print("ID"+doc.id);
        doc.words.display();
    }
}