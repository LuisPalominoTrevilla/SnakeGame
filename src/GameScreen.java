import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameScreen extends JFrame{
    
    private Board bp;
    private MenuScreen parent;
    
    public GameScreen(int vel, MenuScreen parent){
        super();
        this.parent = parent;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.bp = new Board(1000, 1000, vel, this);
        this.add(bp);
        this.pack();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public void reset(){
        this.parent.setVisible(true);
        this.dispose();
    }

}
