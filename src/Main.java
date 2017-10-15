import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {

	public static void main(String[] args) {
        Field field = new Field(10, 10);
        field.surroundedByWall();
        field.addRandomWall();
        field.addApple();
        field.addSnake();
        for (int i = 0; i < field.getLengthX(); i++)
        {
        	for (int j = 0; j < field.getLengthY(); j++)
        	{
        		if (field.getObjectOnField(new Coordinate(i, j)) instanceof Wall)
        			System.out.print('#');
        		if (field.getObjectOnField(new Coordinate(i, j)) instanceof EmptySpace)
        			System.out.print(' ');
        		if (field.getObjectOnField(new Coordinate(i, j)) instanceof Apple)
        			System.out.print('0');
        		if (field.getObjectOnField(new Coordinate(i, j)) instanceof PieceOfSnake)
        			System.out.print('=');
        	}
        	System.out.println();
        }
    }
	
}