import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.util.*;

public class Main{
    static private Map<Integer, Direction> controls = new HashMap<Integer, Direction>();
    private static void createControls(){
    	
        Map<Integer, Direction> directions = new HashMap<Integer, Direction>();
        directions.put(65, Direction.Left);
        directions.put(87, Direction.Up);
        directions.put(83, Direction.Down);
        directions.put(68, Direction.Right);
        controls = directions;
    };
    
    public static void startProcessingControl(JFrame frame, Gui gui) {
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            	if (controls.containsKey(e.getKeyCode())){
            		gui.game.snake.setDirection(controls.get(e.getKeyCode()));
            	}
            }
        });
    }
    
    public static void main(String args[]) throws IOException{
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        Gui gui = new Gui(frame);
        frame.add(gui);
        createControls();
        startProcessingControl(frame, gui);
        frame.setVisible(true);
    }
}
