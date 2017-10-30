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
    
 /*   private String[] typesOfPieces = {"SnakeTail",
									  "SnakeTail2",
							          "SnakeHead",
							          "SnakeHead2",
							          "SnakeTwist",
							          "SnakePart"};

    private void initListOfImages() throws IOException {
        for (String typeOfPiece: this.typesOfPieces) {
            BufferedImage imgHead = ImageIO.read(
                    new File(String.format("images/%s.png", typeOfPiece)));
            Map<Direction, Image> map = new HashMap<Direction, Image>();
            map.put(Direction.Down, rotateImage(imgHead, 90));
            map.put(Direction.Left, rotateImage(imgHead, 180));
            map.put(Direction.Up, rotateImage(imgHead, 270));
            map.put(Direction.Right, rotateImage(imgHead, 0));
            this.images.put(typeOfPiece, map);
        }
    } */

  /*  private void initDirectionsOfSnake() {
        this.mapOfDirections = new MapOfDirections();
        this.mapOfDirections.addDoubleDirections(Direction.Down, Direction.Right, Direction.Right);
        this.mapOfDirections.addDoubleDirections(Direction.Down, Direction.Left, Direction.Up);
        this.mapOfDirections.addDoubleDirections(Direction.Up, Direction.Right, Direction.Down);
        this.mapOfDirections.addDoubleDirections(Direction.Right, Direction.Down, Direction.Left);
    } */
    
 /*   public String animationNameOfTheObject(PieceOfSnake snakePart, int counter) {
        if (snakePart.lastPiece == null) {
        	if (counter % 2 == 0)
        		return "SnakeTail";
        	return "SnakeTail2";
        }
        if (snakePart.nextPiece == null) {
        	if (counter % 2 == 0)
        		return "SnakeHead";
        	return "SnakeHead2";
        }
        if (snakePart.nextPiece.direction != snakePart.direction)
            return "SnakeTwist";
        return "SnakePart";
    } */

    private Image getImage(ObjectOnField objectOnField) {
    	if (objectOnField instanceof PieceOfSnake) 
    	{
    		PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
    		return paintImage.images.get(paintImage.animationNameOfTheObjectSnake(objectOnField.nameOfTheObject(), counter)).get(pieceOfSnake.direction);
    	}
    	else
    	{
    		String obj = objectOnField.nameOfTheObject();
    		return paintImage.images.get(paintImage.animationNameOfTheObjectOther(obj, counter)).get(Direction.None);
    	}
    	
     /*   if (!(objectOnField instanceof PieceOfSnake)) {
            Image image = new ImageIcon(String.format("images/%s.png", objectOnField.nameOfTheObject())) .getImage();
            if (image.getHeight(null) != -1) {
                return image;
            } else {
                return new ImageIcon(String.format("images/Undefined.png", objectOnField.nameOfTheObject())) .getImage();
            }
        } else {
        	PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
            if (objectOnField.nameOfTheObject() == "SnakeHead")
                return this.images.get(animationNameOfTheObject(pieceOfSnake, counter)).get(pieceOfSnake.direction);
            if (objectOnField.nameOfTheObject() == "SnakeTail")
            	return this.images.get(animationNameOfTheObject(pieceOfSnake, counter)).get(pieceOfSnake.nextPiece.direction);
            if (objectOnField.nameOfTheObject() == "SnakeTwist") {                
                this.initDirectionsOfSnake();
                return this.images.get(animationNameOfTheObject(pieceOfSnake, counter)).get(
                        this.mapOfDirections.get(pieceOfSnake.nextPiece.direction, pieceOfSnake.direction)
                );
            }
            if (objectOnField.nameOfTheObject() == "SnakePart") 
                return this.images.get(animationNameOfTheObject(pieceOfSnake, counter)).get(pieceOfSnake.direction);                
            return this.images.get(animationNameOfTheObject(pieceOfSnake, counter)).get(Direction.None);
        } */
    }

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
                ((Graphics2D)g).drawImage(new ImageIcon("images/EmptySpace.png").getImage(),
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
                img = getImage(this.field.getObjectOnField(new Coordinate(x, y)));
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
