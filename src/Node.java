
public class Node <V>{
    private V value;
    private Node<V> next;
    private Node<V> prev;
    private int index;
    
    public Node(V value, int index){
        this.value = value;
        this.next = null;
        this.prev = null;
        this.index = index;
    }
    
    public void setNext(Node<V> next){
        this.next = next;
    }
    
    public Node<V> getNext(){
        return this.next;
    }
    
    public void setPrev(Node<V> prev){
        this.prev = prev;
    }
    
    public Node<V> getPrev(){
        return this.prev;
    }
    
    public V getvalue(){
        return this.value;
    }
    
    public void setValue(V value){
        this.value = value;
    }
    
    public int getIndex(){
        return this.index;
    }
}
