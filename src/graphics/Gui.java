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
    private Map<String, Map<Direction, Image>> images = new HashMap<String, Map<Direction, Image>>();
    private MapOfDirections mapOfDirections;
    private final int fps = 8;
    private Timer timer = new Timer(1000/fps, this);


    private void initListOfImages() throws IOException {
        for (String typeOfPiece: PieceOfSnake.typesOfPieces) {
            BufferedImage imgHead = ImageIO.read(
                    new File(String.format("images/%s.png", typeOfPiece)));
            Map<Direction, Image> map = new HashMap<Direction, Image>();
            map.put(Direction.Down, rotateImage(imgHead, 90));
            map.put(Direction.Left, rotateImage(imgHead, 180));
            map.put(Direction.Up, rotateImage(imgHead, 270));
            map.put(Direction.Right, rotateImage(imgHead, 0));
            this.images.put(typeOfPiece, map);
        }
    }

    private void initDirectionsOfSnake() {
        this.mapOfDirections = new MapOfDirections();
        this.mapOfDirections.addAllDirections(Direction.Down, Direction.Right, Direction.Right);
        this.mapOfDirections.addAllDirections(Direction.Down, Direction.Left, Direction.Up);
        this.mapOfDirections.addAllDirections(Direction.Up, Direction.Right, Direction.Down);
        this.mapOfDirections.addAllDirections(Direction.Right, Direction.Down, Direction.Left);
    }

    private Image getImage(ObjectOnField objectOnField) {
        if (!(objectOnField instanceof PieceOfSnake)) {
            Image image = new ImageIcon(String.format("images/%s.png", objectOnField.nameOfTheObject())) .getImage();
            if (image.getHeight(null) != -1) {
                return image;
            } else {
                return new ImageIcon(String.format("images/Undefined.png", objectOnField.nameOfTheObject())) .getImage();
            }
        } else {
            if (objectOnField.nameOfTheObject() == "SnakeHead") {
                PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
                return this.images.get(objectOnField.nameOfTheObject()).get(pieceOfSnake.direction);
            } if (objectOnField.nameOfTheObject() == "SnakeTail") {
                PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
                return this.images.get(objectOnField.nameOfTheObject()).get(pieceOfSnake.nextPiece.direction);
            } if (objectOnField.nameOfTheObject() == "SnakeTwist") {
                PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
                this.initDirectionsOfSnake();
                return this.images.get(objectOnField.nameOfTheObject()).get(
                        this.mapOfDirections.get(pieceOfSnake.nextPiece.direction, pieceOfSnake.direction)
                );
            }
            if (objectOnField.nameOfTheObject() == "SnakePart") {
                PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
                if (pieceOfSnake.direction == Direction.Down || pieceOfSnake.direction == Direction.Up) {
                    return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Up);
                } else {
                    return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Right);
                }
            }
            return this.images.get(objectOnField.nameOfTheObject()).get(Direction.None);
        }
    }

    public Gui(JFrame frame) throws IOException {
        this.frame = frame;
        this.game = new Game();
        this.game.startNewGame();
        this.field = this.game.field;
        this.initListOfImages();
        timer.start();

    }
    private JFrame frame;
    public Game game;
    public Field field;
    private int cellWidth() {
        return frame.getHeight() / (this.field.getLengthX() + 1);
    }
    private int cellHeight() {
        return frame.getWidth() / (this.field.getLengthY() + 1);
    }

    private BufferedImage rotateImage(BufferedImage img, int angle){
        int x = img.getWidth(null) / 2;
        int y = img.getHeight(null) / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), x, y);
        AffineTransformOp op = new AffineTransformOp(tx, TYPE_BILINEAR);
        return op.filter(img, null);
    }

    public void paint(Graphics g) {
        for (int x = 0; x < this.field.getLengthX(); x++){
            for (int y = 0; y < this.field.getLengthY(); y++){
                ((Graphics2D)g).drawImage(new ImageIcon("images/EmptySpace.png").getImage(),
                        y * cellHeight(),
                        x * cellWidth(),
                        cellHeight(),
                        cellWidth(),
                        null);
            }
        }
        for (int x = 0; x < this.field.getLengthX(); x++) {
            for (int y = 0; y < this.field.getLengthY(); y++) {
                Image img = null;
                img = getImage(this.field.getObjectOnField(new Coordinate(x, y)));
                ((Graphics2D)g).drawImage(img,
                        y * cellHeight(),
                        x * cellWidth(),
                        cellHeight(),
                        cellWidth(),
                        null);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.game.snake.toInteractWithObject(this.field);
        if (!this.game.isEndGame()) {
            this.field.appleGenerator();
        } else {
            this.game.startNewGame();
            this.field = this.game.field;
        }
        repaint();
    }
}
