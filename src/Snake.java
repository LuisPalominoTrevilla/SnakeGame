
public class Snake {
    
    private int side;   // Size of each pixel
    private int boardRows, boardCols;
    static final int LEFT = -1;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int DOWN = -2;
    
    private int direction;
    private Integer[] head;   // Head coordinates
    private Integer[] tail;   // Tal coordinates
    private QueueArray<Integer[]> previousPositions;
    
    public Snake(int side, Integer[] startPos,Integer[] midPos ,Integer[] endPos, int rows, int cols){
        this.side = side;
        this.direction = Snake.RIGHT;
        this.previousPositions = new QueueArray<Integer[]>();
        this.head = new Integer[2];
        this.tail = new Integer[2];
        this.head[0] = startPos[0];
        this.head[1] = startPos[1];
        
        this.previousPositions.enQueue(new Integer[]{midPos[0], midPos[1]});
        this.previousPositions.enQueue(new Integer[]{startPos[0], startPos[1]});
        this.tail[0] = endPos[0];
        this.tail[1] = endPos[1];
        this.boardCols = cols;
        this.boardRows = rows;
    }
    
    public Integer[] getHead(){
        return this.head;
    }
    
    public Integer[] getTail(){
        return this.tail;
    }
    
    public int getSide(){
        return this.side;
    }
    
    public int getDirection(){
        return this.direction;
    }
    
    public void move(){
        int headX = this.head[1];
        int headY = this.head[0];
        
        switch(this.direction){
        case Snake.DOWN:
            headY++;
            break;
        case Snake.UP:
            headY--;
            break;
        case Snake.RIGHT:
            headX++;
            break;
        case Snake.LEFT:
            headX--;
            break;
        }
        if(headX < 0){
            this.head[1] = this.boardCols-1;
            this.head[0] = headY%this.boardRows;
        }else if(headY < 0){
            this.head[0] = this.boardRows-1;
            this.head[1] = headX%this.boardCols;
        }else{
            this.head[1] = headX%this.boardCols;
            this.head[0] = headY%this.boardRows;
        }
        this.previousPositions.enQueue(new Integer[]{this.head[0], this.head[1]});
        
    }
    
    public void moveTail(){
        this.tail = this.previousPositions.deQueue();
    }
    
    public void changeDirection(int newDirection){
        switch(newDirection){
        case Snake.DOWN:
            if(this.direction != Snake.UP){
                this.direction = newDirection;
            }
            
            break;
        case Snake.UP:
            if(this.direction != Snake.DOWN){
                this.direction = newDirection;
            }
            
            break;
        case Snake.RIGHT:
            if(this.direction != Snake.LEFT){
                this.direction = newDirection;
            }
            
            break;
        case Snake.LEFT:
            if(this.direction != Snake.RIGHT){
                this.direction = newDirection;
            }
            
            break;
        }
    }
}
