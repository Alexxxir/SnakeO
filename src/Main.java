import javax.swing.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class Main{
    public static final int cellSize = 10;
    
    static private Map<Integer, Direction> controls = new HashMap();
    public Gui newGui;
    
    static private void createControls(){
    	
        Map<Integer, Direction> map1 = new HashMap();
        map1.put(65, Direction.Left);
        map1.put(87, Direction.Up);
        map1.put(83, Direction.Down);
        map1.put(68, Direction.Right);
        controls = map1;
    };
    
    public static void main(String args[]) throws IOException{
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        Gui newGui = new Gui(frame);
        frame.add(newGui);
        createControls();
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            	if (controls.containsKey(e.getKeyCode())){
            		newGui.snake.setDirection(controls.get(e.getKeyCode()));
            	}
            }
        });
        
    }
}
