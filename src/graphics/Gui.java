package graphics;
import javax.swing.*;
import javax.swing.Timer;
import game.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui extends JPanel implements ActionListener{

    private final int fps = 8;
    private Timer timer = new Timer(1000/fps, this);
    private GetImage paintImage;
    public Game game;

    public Gui() {
        this.game = new Game();
        this.game.startNewGame();
        this.paintImage = new GetImage();
        this.timer.start();

    }
    private int cellHeight() {
        return getHeight() / (this.game.field.getLengthY());
    }
    private int cellWidth() {
        return getWidth() / (this.game.field.getLengthX());
    }

    public void paint(Graphics g) {
        for (int x = 0; x < this.game.field.getLengthX(); x++) {
            for (int y = 0; y < this.game.field.getLengthY(); y++) {
                g.drawImage(new ImageIcon("images/EmptySpace0.png").getImage(),
                        x * cellWidth(), y * cellHeight(), cellWidth(), cellHeight(), null);
                g.drawImage(this.paintImage.getImage(this.game.field.getObjectOnField(new Coordinate(x, y)), counter),
                        x * cellWidth(), y * cellHeight(), cellWidth(), cellHeight(), null);
            }
        }
    }

    private int counter = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        this.game.snake.toInteractWithObject(this.game.field);
        if (!this.game.isEndGame()) {
            this.counter++;
            this.game.field.objectGenerator();
        } else {
            this.game.startNewGame();
        }
        repaint();
    }
}
