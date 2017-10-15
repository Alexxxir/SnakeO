import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {

	public static void main(String[] args) {
        Field field = new Field(1000, 5);
        field.SurroundedByWall();
        field.AddRandomWall();
        field.AddApple();
        for (int i = 0; i < field.getLengthX(); i++)
        {
        	for (int j = 0; j < field.getLengthY(); j++)
        	{
        		if (field.GetObjectOnField(new Coordinate(i, j)) instanceof Wall)
        			System.out.print('#');
        		if (field.GetObjectOnField(new Coordinate(i, j)) instanceof EmptySpace)
        			System.out.print(' ');
        		if (field.GetObjectOnField(new Coordinate(i, j)) instanceof Apple)
        			System.out.print('0');
        	}
        	System.out.println();
        }
    }
	
}