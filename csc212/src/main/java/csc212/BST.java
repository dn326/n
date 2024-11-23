package csc212;

class BSTNode<T>{
    public String key;
    public T data;
    public BSTNode<T> left, right;
            
    public BSTNode(String key,T data){
        this.key=key;
        this.data=data;
        left=right=null;
    }
}

public class BST<T>{
    private BSTNode<T> root,current;
    
    public BST(){
        current=root=null;
    }
    
    public boolean empty(){
        return root==null;
    }
    
    public boolean full(){
        return false;
    }
    
    public T retrieve(){
        return current.data;
    }
    
    public boolean findKey(String k){
        BSTNode<T> p = root;

        while (p != null) {
            current = p;

            if (k.compareToIgnoreCase(p.key) == 0) 
                return true;
            else if (k.compareToIgnoreCase(p.key) < 0) 
                p = p.left;
            else
                p = p.right;
        }

        return false;
    }
    
    public boolean insert(String k, T val) {
        if (root == null) {
            current = root = new BSTNode<T>(k, val);
            return true;
        }

        BSTNode<T> p = current;
        if (findKey(k)) {
            current = p;
            Word exist = (Word) p.data; 

            LinkedList<Integer> docsID = ((Word) val).getDocsIDList();

            docsID.iterateList();
            return false;
        }

        BSTNode<T> q = new BSTNode<T>(k, val);
        if (k.compareToIgnoreCase(current.key) < 0)
            current.left = q;
        else
            current.right = q;

        current = q;
        return true;
        }

    
    public void inOrder(){
        if (root==null)
            System.out.println("empty tree");
        else
            inOrder(root);
    }   
    
    
    public void inOrder(BSTNode p){
        if(p==null)
            return;
        
        inOrder(p.left);
        ((Word)p.data).display();
        inOrder(p.right);
    }

    
     public void preOrder(){
        if (root==null)
            System.out.println("empty tree");
        else
            preOrder(root);
        
    }
     
    public void preOrder(BSTNode p){
        if(p==null)
            return;
        System.out.println(p.data);
        preOrder(p.left);
        preOrder(p.right);
    }
}