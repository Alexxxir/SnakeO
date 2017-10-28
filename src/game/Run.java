package game;
import javax.swing.*;

import graphics.Gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.util.*;

public class Run{
    private static void createControls(JFrame frame, Gui gui){
        Map<Integer, Direction> directions = new HashMap<Integer, Direction>();
        directions.put(65, Direction.Left);
        directions.put(87, Direction.Up);
        directions.put(83, Direction.Down);
        directions.put(68, Direction.Right);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (directions.containsKey(e.getKeyCode())){
                    gui.game.snake.setDirection(directions.get(e.getKeyCode()));
                }
            }
        });
    }

    public static void main(String args[]) throws IOException{
        JFrame frame = new JFrame("Snake");
        frame.setSize(720, 720);
        Gui gui = new Gui();
        frame.add(gui);
        createControls(frame, gui);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
