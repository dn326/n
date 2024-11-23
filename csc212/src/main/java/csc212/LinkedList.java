package csc212;

class Node<T>{
    
    public T data;
    public Node<T> next;
    
    public Node(T val){
        data = val;
        next = null;
    }
}

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;
    int n = 0;
    
    public LinkedList(){
        head = current = null;
    }

    public boolean empty(){
        return head == null;
    }

    public boolean last(){
        return current.next == null;
    }

    public boolean full(){
        return false;
    }

    public void findFirst(){
        current = head;
    }

    public void findNext(){
        current = current.next;
    }

    public T retrieve (){
        return current.data;
    }
    
    public String retrieve(int index) {
        if (index < 0 || index >= size()) 
        throw new IndexOutOfBoundsException("Index out of bounds");

        Node<T> current = head;
        int counter = 0;

        while (counter < index) {
            current = current.next;
            counter++;
        }

        return (String)current.data;  
    }
    
    public void iterateList() {
        Node<T> p = head;
        while (p != null) {
            Integer docID = (Integer) p.data; 
            System.out.println(docID); 
            p = p.next;
        }
    }

    public void update(T val){
        current.data = val;
    }

    public void insert(T val){
        n++;
        Node<T> tmp;
        if(empty()){
            current = head = new Node<T>(val);
        }
        else{
            tmp = current.next;
            current.next = new Node<T>(val);
            current = current.next;
            current.next = tmp;
        }
    }

    public int size(){
        int n = 0;
        Node<T> p = head;
        while(p != null) {
            n++;
            p = p.next;
        }
        return n;
    }

    public boolean exist(T e) {
        Node<T> p = head;
        while(p != null) {
            if(p.data.equals(e))
                return true;
            p = p.next;
        }
        
        return false;
    }
    
    public void display(){
        if(head == null) {
            System.out.print("Empty list");
            return;
        }
        Node<T> p = head;
        while(p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
    }

}