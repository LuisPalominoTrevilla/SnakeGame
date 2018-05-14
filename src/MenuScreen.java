import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuScreen extends JFrame{
    
    private GameScreen game;
    private String color1, color2, color3;
    private JPanel newPanel;
    
    public MenuScreen(){
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        color1 = "#000000";
        color2 = "#000000";
        color3 = "#000000";
        
        newPanel = new JPanel(){
            
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 1200, 500);
                g.setColor(Color.GREEN);
                g.setFont(new Font("normal", Font.BOLD, 80));
                g.drawString("SNAKE", 450, 100);
                
                // Dificultades
                g.setColor(Color.decode(color1));
                g.fillRect(0, 200, 400, 300);
                g.setColor(Color.GREEN);
                g.setFont(new Font("normal", Font.BOLD, 40));
                g.drawString("Facil", 150, 400);
                
                g.setColor(Color.decode(color2));
                g.fillRect(400, 200, 400, 300);
                g.setColor(Color.GREEN);
                g.setFont(new Font("normal", Font.BOLD, 40));
                g.drawString("Medio", 550, 400);
                
                g.setColor(Color.decode(color3));
                g.fillRect(800, 200, 400, 300);
                g.setColor(Color.GREEN);
                g.setFont(new Font("normal", Font.BOLD, 40));
                g.drawString("Dificil", 950, 400);
            }
        };
        
        newPanel.setPreferredSize(new Dimension(1200, 500));
        newPanel.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
                
            }
            
            public void mouseEntered(MouseEvent e) {
                
            }
            
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                if(y > 200){
                    if(x <= 400){
                        new GameScreen(200, MenuScreen.this);
                        MenuScreen.this.setVisible(false);
                    }else if(x > 400 && x <= 800){
                        new GameScreen(100, MenuScreen.this);
                        MenuScreen.this.setVisible(false);
                    }else{
                        new GameScreen(50, MenuScreen.this);
                        MenuScreen.this.setVisible(false);
                    }
                }
            }
        });
        
        this.newPanel.addMouseMotionListener(new MouseMotionListener() {
            
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                if(y > 200){
                    if(x <= 400){
                        MenuScreen.this.color1 = "#345e26";
                        MenuScreen.this.color2 = "#000000";
                        MenuScreen.this.color3 = "#000000";
                        MenuScreen.this.newPanel.repaint();
                    }else if(x > 400 && x <= 800){
                        MenuScreen.this.color2 = "#345e26";
                        MenuScreen.this.color3 = "#000000";
                        MenuScreen.this.color1 = "#000000";
                        MenuScreen.this.newPanel.repaint();
                    }else{
                        MenuScreen.this.color3 = "#345e26";
                        MenuScreen.this.color2 = "#000000";
                        MenuScreen.this.color1 = "#000000";
                        MenuScreen.this.newPanel.repaint();
                    }
                }else{
                    MenuScreen.this.color3 = "#000000";
                    MenuScreen.this.color2 = "#000000";
                    MenuScreen.this.color1 = "#000000";
                    MenuScreen.this.newPanel.repaint();
                }
                
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        this.add(newPanel);
        this.pack();
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MenuScreen();
    }

}
