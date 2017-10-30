package graphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import game.*;
import objects.ObjectOnField;
import objects.PieceOfSnake;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static java.awt.image.AffineTransformOp.TYPE_BILINEAR;


public class Gui extends JPanel implements ActionListener{

    private final int fps = 8;
    private Timer timer = new Timer(1000/fps, this);
	private GetImage paintImage;

    public Gui() throws IOException {
    	this.paintImage = new GetImage();
        this.game = new Game();
        this.game.startNewGame();
        this.field = this.game.field;
        paintImage.initAnimation();
        paintImage.initDirectionsOfSnake();
        timer.start();

    }
    public Game game;
    public Field field;
    private int cellHeight() {
        return getHeight() / (this.field.getLengthY());
    }
    private int cellWidth() {
        return getWidth() / (this.field.getLengthX());
    }

    public void paint(Graphics g) {
        for (int x = 0; x < this.field.getLengthX(); x++){
            for (int y = 0; y < this.field.getLengthY(); y++){
                ((Graphics2D)g).drawImage(new ImageIcon("images/EmptySpace0.png").getImage(),
                        x * cellWidth(),
                        y * cellHeight(),
                        cellWidth(),
                        cellHeight(),
                        null);
            }
        }
        for (int x = 0; x < this.field.getLengthX(); x++) {
            for (int y = 0; y < this.field.getLengthY(); y++) {
                Image img = null;
                img = paintImage.getImage(this.field.getObjectOnField(new Coordinate(x, y)), counter);
                ((Graphics2D)g).drawImage(img,
                        x * cellWidth(),
                        y * cellHeight(),
                        cellWidth(),
                        cellHeight(),
                        null);
            }
        }
    }

    private int counter = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        this.game.snake.toInteractWithObject(this.field);
        if (!this.game.isEndGame()) {
        	counter++;
            this.field.objectGenerator();
        } else {
            this.game.startNewGame();
            this.field = this.game.field;
        }
        repaint();
    }
}
