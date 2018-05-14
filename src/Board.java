import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Board extends JPanel implements Runnable{
    
    private int width, height, rows, cols, pixelSize;
    private Snake snake;
    private SparceMatrix<Boolean> paintBoard;
    private Integer[] apple;
    private Random ran;
    private boolean changeDirection;
    private int score;
    private int velocity;
    private GameScreen parent;
    
    public Board(int width, int height, int velocity, GameScreen parent){
        super();
        this.parent = parent;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.pixelSize = 20;        // Size of each pixel
        this.rows = height/this.pixelSize;
        this.cols = width/this.pixelSize;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.changeDirection = true;
        this.ran = new Random();
        
        this.paintBoard = new SparceMatrix<Boolean>(this.rows, this.cols);
        this.snake = new Snake(this.pixelSize, new Integer[]{this.rows/2, this.cols/2},new Integer[]{this.rows/2, this.cols/2-1} ,new Integer[]{this.rows/2, this.cols/2-2},this.rows, this.cols);
        
        // Add head to the board
        this.paintBoard.add(this.snake.getHead()[0], this.snake.getHead()[1], true);
        // Add body and tail to board
        this.paintBoard.add(this.rows/2, this.cols/2-1, true);
        this.paintBoard.add(this.snake.getTail()[0], this.snake.getTail()[1], true);
        
        // Add apple to the board
        this.apple = new Integer[2];
        this.locateApple();
        this.paintBoard.add(this.apple[0], this.apple[1], true);
        
        Thread hilo = new Thread(this);
        hilo.start();
        
        // Key Strokes
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "rightReleased");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "leftReleased");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "upReleased");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "downReleased");
        
        am.put("rightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Board.this.changeDirection){
                    Board.this.snake.changeDirection(Snake.RIGHT);
                    Board.this.changeDirection = false;
                }
            }
        });
        am.put("leftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Board.this.changeDirection){
                    Board.this.snake.changeDirection(Snake.LEFT);
                    Board.this.changeDirection = false;
                }
            }
        });
        am.put("upReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Board.this.changeDirection){
                    Board.this.snake.changeDirection(Snake.UP);
                    Board.this.changeDirection = false;
                }
            }
        });
        am.put("downReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Board.this.changeDirection){
                    Board.this.snake.changeDirection(Snake.DOWN);
                    Board.this.changeDirection = false;
                }
            }
        });
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Paint background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.width, this.height);
        LinkedList<Integer[]> coors = this.paintBoard.getOccupiedCoordinates();
        g.setColor(Color.white);
        g.drawString("Score: " + this.score, this.rows/2*this.pixelSize, 30);
        for(int i = 0; i < coors.getSize(); i++){
            if(this.apple != null && coors.get(i)[0] == this.apple[0] && coors.get(i)[1] == this.apple[1]){
                g.setColor(Color.decode("#ba2110"));
            }else{
                g.setColor(Color.decode("#4eb712"));
            }
            g.fillRect(coors.get(i)[1]*this.snake.getSide(), coors.get(i)[0]*this.snake.getSide(), this.snake.getSide(), this.snake.getSide());
            g.setColor(Color.BLACK);
            g.drawRect(coors.get(i)[1]*this.snake.getSide(), coors.get(i)[0]*this.snake.getSide(), this.snake.getSide(), this.snake.getSide());
        }
    }

    public void run() {
        boolean appleCollision = false;
        while(true){
            try {
                this.score = this.paintBoard.getSize()-1;
                this.snake.move();
                if(this.paintBoard.get(this.snake.getHead()[0], this.snake.getHead()[1]) == null){        // Check the new Position of the head
                    // If there is no collision
                    this.paintBoard.add(this.snake.getHead()[0], this.snake.getHead()[1], true);    // Only paint the head of the snake
                    this.paintBoard.remove(this.snake.getTail()[0], this.snake.getTail()[1]); // Remove the previous tail position
                    this.snake.moveTail();  // Move the tail
                }else{
                    // There is a collision
                    // Check if the head touched the apple
                    if(this.snake.getHead()[0] == this.apple[0] && this.snake.getHead()[1] == this.apple[1]){
                        if(this.paintBoard.getSize() == this.rows*this.cols){
                            // Se lleno la matriz
                            this.apple = null;
                            this.score = this.paintBoard.getSize();
                            this.repaint();
                            break;
                        }else{
                            this.locateApple();
                            this.paintBoard.add(this.apple[0],this.apple[1], true);
                        }
                    }else{
                        // Game over
                        break;
                    }
                }
                this.repaint();
                Thread.sleep(this.velocity);
                this.changeDirection = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.parent.reset();
    }

    public void locateApple(){
        
        Integer applex = this.ran.nextInt(this.cols);
        Integer appley = this.ran.nextInt(this.rows);
        
        if(this.paintBoard.get(appley, applex) != null){
            // Si esta ocupado
            this.locateApple();
        }else{
            this.apple[0] = appley;
            this.apple[1] = applex;
            
        }
    }

}
