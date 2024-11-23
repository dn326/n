package csc212;

import java.util.Scanner;

public class menu {
    public static void main(String[] args) {
        Driver d = new Driver();
        d.LoadFiles("src/files/stop.txt","src/files/dataset.csv");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--------- MENU ---------");
            System.out.println("1. Retrieve a term");
            System.out.println("2. Boolean retrieval");
            System.out.println("3. Ranked retrieval");
            System.out.println("4. Number of unique words");
            System.out.println("5. Indexed document(print all documents with the number of words (tokens) in them)");
            System.out.println("6. Indexed token(print all the words (tokens) with the number of documents they appear in)");
            System.out.println("7. Inverted index with list of lists");
            System.out.println("8. Inverted index with BST");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.next();
                continue;
            }
            
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Retrieve a term:");
                    System.out.println("    a. Using index with lists");
                    System.out.println("    b. Using inverted index with lists");
                    System.out.println("    c. Using inverted index with BST");
                    System.out.print("Enter your choice: ");
                    char retrieveChoice = scanner.next().charAt(0);
                    
                    System.out.print("Enter word to retrieve:");
                    String word = scanner.next();
                    word = word.toLowerCase().replaceAll(" ", "");
                    
                    switch (retrieveChoice) {
                        case 'a':
                            LinkedList<Integer> a = d.index.getDoc(word);
                            System.out.print("word:"+ word + "[");
                            a.display();
                                    
                            System.out.println("]");
                            break;
                            
                        case 'b':
                            boolean found = d.wordsList.invertedSearch(word);
                            if(found)
                                d.wordsList.wordsList.retrieve().display();
                            break;
                            
                        case 'c':
                            found = d.wordsListBST.searchWord(word);
                            if(found)
                                d.wordsList.wordsList.retrieve().display();
                            break;
                            
                        default:
                            System.out.println("Invalid choice!");
                    }
                    break;
                    
                case 2:
                    System.out.println("Boolean retrieval:");
                    System.out.println("    a. Using AND");
                    System.out.println("    b. Using OR");
                    System.out.print("Enter your choice: ");
                    char queryChoice = scanner.next().charAt(0);
                    
                    System.out.print("Enter query:");
                    String Q = scanner.next();
                    Q = Q.toLowerCase().replaceAll(" ", "");
 
                    switch(queryChoice) {
                        case 'a':
                            Query q = new Query(d.wordsList);
                            LinkedList<Integer> docsID = Query.MixedQuery(Q); 
                            d.displayDocs(docsID);
                            break;
                            
                        case 'b':
                            q = new Query(d.wordsList);
                            docsID = Query.MixedQuery(Q); 
                            d.displayDocs(docsID);
                            break;
                            
                        default:
                            System.out.println("Invalid choice!");
                    }
                    break;

                case 3:
                    scanner.nextLine();
                    System.out.println("Ranked retrieval:");
                    System.out.print("Enter query to rank:");
                    String qr = scanner.nextLine();
                    qr = qr.toLowerCase();
                    Ranking r = new Ranking(qr, d.index,d.wordsListBST);
                    r.insertDocSorted();
                    r.display();                  
                    break;
                    
                case 4:
                    System.out.println("Unique words:");
                    System.out.println(d.wordsList.wordsList.n);
                    break;
                    
                case 5:
                    System.out.println("Indexed document:");
                    if (d.index.docsList.empty()) 
                        System.out.println("No documents in the list");
                    else {
                        d.index.docsList.findFirst();
                        while (!d.index.docsList.last()) { 
                            Document doc = d.index.docsList.retrieve();
                            System.out.println("Document ID: " + doc.id + ", Tokens: " + doc.words.n); 
                            d.index.docsList.findNext();
                        }

                        Document lastDoc = d.index.docsList.retrieve();
                        System.out.println("Document ID: " + lastDoc.id + ", Tokens: " + lastDoc.words.n);
                    }
                    break;

                case 6:
                    System.out.println("Indexed token:");
                    for (int i = 0; i < d.unique.size(); i++) {
                        String token = d.unique.retrieve(i); 
                        d.tokenDocCount(token); 
                    }
                    break;
                    
                case 7:
                    System.out.println("Inverted index with list of lists:");
                    d.wordsList.display();
                    break;
                    
                case 8:
                    System.out.println("Inverted index with BST:");
                    d.wordsListBST.display();
                    break;
                    
                case 9:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            scanner.nextLine();
        
        } while (choice != 9);

        scanner.close();
    }
}
