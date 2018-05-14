public class LinkedList <V>{
    private Node<V> head, tail;
    private int size;
    
    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void addFirst(V value){
        Node<V> tmp = new Node<V>(value, 0);
        tmp.setNext(this.head);
        this.head = tmp;
        if(tmp.getNext() == null){
            this.tail = tmp;
        }
        this.size++;
    }
    
    public void addLast(V value){
        Node<V> tmp = new Node<V>(value, 0);
        if(this.tail == null){
                this.head = tmp;
        }else{
            this.tail.setNext(tmp);
        }
        this.tail = tmp;
        this.size++;
    }
    
    public void addAt(V value, int index){
        if(index < 0){
            throw new IndexOutOfBoundsException();
        }
        else if(index == 0){
            this.addFirst(value);
        }else{
            Node<V> tmp = new Node<V>(value, 0);
            Node<V> current = this.head;
            for(int i = 0; i < index-1; i++){
                if(current == null){
                    throw new IndexOutOfBoundsException();
                }
                current = current.getNext();
            }
            if(current.getNext() == null){
                this.tail = tmp;
                
            }
                
                tmp.setNext(current.getNext());
                current.setNext(tmp);
                this.size++;
            
        }
    }
    
    public void remove(int index){
        if(index < 0){
            throw new IndexOutOfBoundsException();
        }else if(index == 0){
            this.size--;
            this.head = this.head.getNext();
        }else{
            Node<V> remove = this.getNode(index);
            Node<V> current = this.head;
            while(current.getNext() != remove){
                current = current.getNext();
            }
            current.setNext(remove.getNext());
            if(remove == this.tail){
                this.tail = current;
            }
            this.size--;
        }
    }
    
    public V get(int index){
        if(index < 0){
            throw new IndexOutOfBoundsException();
        }else{
            Node<V> current = this.head;
            for(int i = 0; i < index; i++){
                if(current == null || current.getNext() == null){
                    throw new IndexOutOfBoundsException();
                }
                current = current.getNext();
            }
            return current.getvalue();
        }
    }
    
    public Node<V> getNode(int index){
        if(index < 0){
            throw new IndexOutOfBoundsException();
        }else{
            Node<V> current = this.head;
            for(int i = 0; i < index; i++){
                if(current == null || current.getNext() == null){
                    throw new IndexOutOfBoundsException();
                }
                current = current.getNext();
            }
            return current;
        }
    }
    
    public String toString(){
        String res = "";
        
        Node<V> current = this.head;
        while(current != null){
            res += current.getvalue() + ", ";
            current = current.getNext();
        }
        return res;
    }
    
    public int getSize(){
        return this.size;
    }

}
