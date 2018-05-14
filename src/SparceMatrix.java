
public class SparceMatrix<V>{
    
    private SmartNode<Node<V>> rowStart;
    private int rows, cols;
    private int size;
    
    public SparceMatrix(int rows, int cols){
        this.rowStart = null;
        this.rows = rows;
        this.cols = cols;
        this.size = 0;
    }
    
    public void add(int row, int col, V value){
        
        if(row < 0 || col < 0 || row >= this.rows || col >= this.cols){
            
            throw new IndexOutOfBoundsException();
        }
        if(this.rowStart == null){
            this.size++;
            Node<V> tmp = new Node<V>(value, col);
            this.rowStart = new SmartNode<Node<V>>(tmp, row);
        }else{
            SmartNode<Node<V>> currentRow = this.rowStart;
            while(currentRow != null){
                if(currentRow.getIndex() == row){
                    // Insertar en algun lugar de esta fila
                    Node<V> currentCol = currentRow.getvalue();
                    while(currentCol != null){
                        if(currentCol.getIndex() == col){
                            currentCol.setValue(value);
                            break;
                        }if(currentCol.getIndex() > col){
                            // Si es menor
                            Node<V> newCol = new Node<V>(value, col);
                            this.size++;
                            if(currentCol.getPrev() == null){
                                newCol.setNext(currentCol);
                                currentCol.setPrev(newCol);
                                currentRow.setValue(newCol);
                            }else{
                                currentCol.getPrev().setNext(newCol);
                                newCol.setNext(currentCol);
                                newCol.setPrev(currentCol.getPrev());
                                currentCol.setPrev(newCol);
                            }
                            break;
                        }else{
                            // Si es mayor
                            if(currentCol.getNext() != null){
                                currentCol = currentCol.getNext();
                                
                            }else{
                                // Si no hay siguiente columna
                                this.size++;
                                Node<V> newCol = new Node<V>(value, col);
                                currentCol.setNext(newCol);
                                newCol.setPrev(currentCol);
                                break;
                            }
                        }
                    }
                    return;
                }else if(currentRow.getIndex() > row){
                    Node<V> tmp = new Node<V>(value, col);
                    SmartNode<Node<V>> newRow = new SmartNode<Node<V>>(tmp, row);
                    this.size++;
                    if(currentRow.getPrev() == null){       // Si currentRow es el primer nodo
                        this.rowStart = newRow;
                        newRow.setNext(currentRow);
                        currentRow.setPrev(newRow);
                    }else{
                        currentRow.getPrev().setNext(newRow);
                        newRow.setNext(currentRow);
                        newRow.setPrev(currentRow.getPrev());
                        currentRow.setPrev(newRow);
                    }
                    return;
                }else{
                    // Es mayor, por lo que si currentRow es el ultimo nodo se debe aniadir despues
                    if(currentRow.getNext() != null){
                        currentRow = currentRow.getNext();
                    }else{
                        // CurrentRow era el ultimo nodo y no tenia el row
                        this.size++;
                        Node<V> tmp = new Node<V>(value, col);
                        SmartNode<Node<V>> newRow = new SmartNode<Node<V>>(tmp, row);
                        currentRow.setNext(newRow);
                        newRow.setPrev(currentRow);
                        return;
                    }
                }
            }
        }
    }
    
    public V get(int row, int col){
        if(row < 0 || col < 0 || row >= this.rows || col >= this.cols){
            throw new IndexOutOfBoundsException();
        }
        
        SmartNode<Node<V>> current = this.rowStart;
        while(current != null && current.getIndex() <= row){
            if(current.getIndex() == row){
                Node<V> tmp = current.getvalue();
                while(tmp != null){
                    if(tmp.getIndex() == col){
                        return tmp.getvalue();
                    }else if(tmp.getIndex() > col){
                        break;
                    }else{
                        tmp = tmp.getNext();
                    }
                }
                break;
            }else{
                current = current.getNext();
            }
        }
        return null;
    }
    
    public void remove(int row, int col){
        if(row < 0 || col < 0 || row >= this.rows || col >= this.cols){
            throw new IndexOutOfBoundsException();
        }
        SmartNode<Node<V>> currentRow = this.rowStart;
        while(currentRow != null){
            if(currentRow.getIndex() == row){
                Node<V> currentCol = currentRow.getvalue();
                // Recorremos la fila
                while(currentCol != null){
                    if(currentCol.getIndex() == col){
                        //  Encontramos el nodo
                        this.size--;
                        if(currentCol.getPrev() == null && currentCol.getNext() == null){   // Si es la unica columna
                            if(currentRow.getPrev() == null){           // Si es la primera fila
                                if(currentRow.getNext() == null){       // Si es el ultimo elemento
                                    this.rowStart = null;
                                }else{
                                    this.rowStart = currentRow.getNext();
                                    this.rowStart.setPrev(null);
                                }
                                currentRow = null;
                            }else if(currentRow.getNext() == null){     // Si es la ultima fila
                                currentRow.getPrev().setNext(null);
                                currentRow = null;
                            }else{  // Si esta en medio
                                currentRow.getPrev().setNext(currentRow.getNext());
                                currentRow.getNext().setPrev(currentRow.getPrev());
                                currentRow = null;
                            }
                        }else if(currentCol.getPrev() == null){     // Es la primera columna
                            currentRow.setValue(currentCol.getNext());
                            currentCol.getNext().setPrev(null);
                            currentCol = null;
                        }else if(currentCol.getNext() == null){     // Es la ultima columna
                            currentCol.getPrev().setNext(null);
                            currentCol = null;
                        }else{
                            currentCol.getPrev().setNext(currentCol.getNext());
                            currentCol.getNext().setPrev(currentCol.getPrev());
                        }
                        break;
                    }else if(currentCol.getIndex() > col){
                        break;
                    }else{
                        currentCol = currentCol.getNext();
                    }
                }
                return;
            }else if(currentRow.getIndex() > row){
                return;
            }else{
                currentRow = currentRow.getNext();
            }
        }
    }
    
    public LinkedList<Integer[]> getOccupiedCoordinates(){
        LinkedList<Integer[]> occupied = new LinkedList<Integer[]>();
        SmartNode<Node<V>> currentRow = this.rowStart;
        while(currentRow != null){
            Node<V> val = currentRow.getvalue();
            int row =  currentRow.getIndex();
            while(val != null){
                int col = val.getIndex();
                occupied.addLast(new Integer[]{row, col});
                val = val.getNext();
            }
            currentRow = currentRow.getNext();
        }
        return occupied;
    }
    
    public String toString(){
        String res = "";
        SmartNode<Node<V>> currentRow = this.rowStart;
        while(currentRow != null){
            Node<V> val = currentRow.getvalue();
            res+= "Row: " + currentRow.getIndex() +" \n";
            while(val != null){
                res += "Column: " + val.getIndex() + " Value: " + val.getvalue()+"\n";
                val = val.getNext();
            }
            currentRow = currentRow.getNext();
        }
        return res;
    }
    
    public int getSize(){
        return this.size;
    }
    
    private class SmartNode<V>{
        private V value;
        private int index;
        private SmartNode<V> next;
        private SmartNode<V> prev;
        
        public SmartNode(V value, int index){
            this.value = value;
            this.index = index;
            this.next = null;
            this.prev = null;
        }
        
        public void setNext(SmartNode<V> next){
            this.next = next;
        }
        
        public SmartNode<V> getNext(){
            return this.next;
        }
        
        public void setPrev(SmartNode<V> prev){
            this.prev = prev;
        }
        
        public SmartNode<V> getPrev(){
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
    
}
