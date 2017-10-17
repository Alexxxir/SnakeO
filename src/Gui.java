import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
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
import java.util.Random;

import static java.awt.image.AffineTransformOp.TYPE_BILINEAR;


public class Gui extends JPanel implements ActionListener{
    private Map<String, Map<Direction, Image>> images = new HashMap();

    public final int fps = 8;
    public int counter = fps*1;
    public Snake snake;
    
    Timer mainTimer = new Timer(1000/fps, this);
    
    private Map<Direction, Image> createMap(Direction[] directions, Image[] images) {
    	 Map<Direction, Image> map = new HashMap();
    	 for (int i = 0; i < directions.length; i++)
    		 map.put(directions[i], images[i]);
    	 return map;
    }


    private void initListOfImages() throws IOException {
    	this.images.put("EmptySpace", createMap(new Direction[] {Direction.None}, new Image[] {new ImageIcon("images/grass.jpg").getImage()}));
    	this.images.put("Apple", createMap(new Direction[] {Direction.None}, new Image[] {new ImageIcon("images/red_apple.png").getImage()}));
    	this.images.put("Wall", createMap(new Direction[] {Direction.None}, new Image[] {new ImageIcon("images/wall.jpg").getImage()}));
    	
   	    BufferedImage imgHead = ImageIO.read(new File("images/snake_head.png"));
   	    Map<Direction, Image> mapSnakeHead = new HashMap();
        mapSnakeHead.put(Direction.Down, rotateImage(imgHead, 90));
        mapSnakeHead.put(Direction.Left, rotateImage(imgHead, 180));
        mapSnakeHead.put(Direction.Up, rotateImage(imgHead, 270));
        mapSnakeHead.put(Direction.Right, rotateImage(imgHead, 0));
        this.images.put("SnakeHead", mapSnakeHead);
   	    BufferedImage imgTail = ImageIO.read(new File("images/snake_tail.png"));
   	    Map<Direction, Image> mapSnakeTail = new HashMap();
   	    mapSnakeTail.put(Direction.Up, rotateImage(imgTail, 90));
   	    mapSnakeTail.put(Direction.Right, rotateImage(imgTail, 180));
        mapSnakeTail.put(Direction.Down, rotateImage(imgTail, 270));
        mapSnakeTail.put(Direction.Left, rotateImage(imgTail, 0));
        this.images.put("SnakeTail", mapSnakeTail);
   	    BufferedImage imgTwist = ImageIO.read(new File("images/snake_r.png"));
   	    Map<Direction, Image> mapSnakeTwist = new HashMap();
   	    mapSnakeTwist.put(Direction.Down, rotateImage(imgTwist, 90));
   	    mapSnakeTwist.put(Direction.Up, rotateImage(imgTwist, 180));
   	    mapSnakeTwist.put(Direction.Left, rotateImage(imgTwist, 270));
   		mapSnakeTwist.put(Direction.Right, rotateImage(imgTwist, 0));
        this.images.put("SnakeTwist", mapSnakeTwist);
   	    BufferedImage imgPart = ImageIO.read(new File("images/snake_part.png"));
   	    Map<Direction, Image> mapSnakePart = new HashMap();
   	    mapSnakePart.put(Direction.Up, rotateImage(imgPart, 90));
   	    mapSnakePart.put(Direction.Right, rotateImage(imgPart, 0));
        this.images.put("PieceOfSnake", mapSnakePart);
    }
    
    private Image getImage(ObjectOnField objectOnField) {
    	if (objectOnField.toString() == "SnakeHead") {
    		PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
    		return this.images.get(objectOnField.toString()).get(pieceOfSnake.direction);
    	} if (objectOnField.toString() == "SnakeTail") {
    		PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
    		return this.images.get(objectOnField.toString()).get(pieceOfSnake.nextPiece.direction);
    	} if (objectOnField.toString() == "SnakeTwist") {
    		PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
    		if (pieceOfSnake.nextPiece.direction == Direction.Down && pieceOfSnake.direction == Direction.Right)
    			return this.images.get(objectOnField.toString()).get(Direction.Right);
    		if (pieceOfSnake.nextPiece.direction == Direction.Left && pieceOfSnake.direction == Direction.Up)
    			return this.images.get(objectOnField.toString()).get(Direction.Right);
    		if (pieceOfSnake.nextPiece.direction == Direction.Down && pieceOfSnake.direction == Direction.Left)
    			return this.images.get(objectOnField.toString()).get(Direction.Left);
    		if (pieceOfSnake.nextPiece.direction == Direction.Right && pieceOfSnake.direction == Direction.Up)
    			return this.images.get(objectOnField.toString()).get(Direction.Left);
    		if (pieceOfSnake.nextPiece.direction == Direction.Up && pieceOfSnake.direction == Direction.Right)
    			return this.images.get(objectOnField.toString()).get(Direction.Down);
    		if (pieceOfSnake.nextPiece.direction == Direction.Left && pieceOfSnake.direction == Direction.Down)
    			return this.images.get(objectOnField.toString()).get(Direction.Down);
    		if (pieceOfSnake.nextPiece.direction == Direction.Right && pieceOfSnake.direction == Direction.Down)
    			return this.images.get(objectOnField.toString()).get(Direction.Up);
    		if (pieceOfSnake.nextPiece.direction == Direction.Up && pieceOfSnake.direction == Direction.Left)
    			return this.images.get(objectOnField.toString()).get(Direction.Up);
    		return this.images.get(objectOnField.toString()).get(pieceOfSnake.direction);
    	} if (objectOnField.toString() == "PieceOfSnake") {
    		PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
    		if (pieceOfSnake.direction == Direction.Down || pieceOfSnake.direction == Direction.Up) {
    			return this.images.get(objectOnField.toString()).get(Direction.Up);
    		} else {
    			return this.images.get(objectOnField.toString()).get(Direction.Right);
    		}
    	}
    	
		return this.images.get(objectOnField.toString()).get(Direction.None);
    }
    
    public Gui(JFrame frame) throws IOException {
        this.frame = frame;
        this.field = new Field(20, 20);
        this.field.surroundedByWall();
        this.field.addRandomWall();
        this.snake = this.field.addSnake();

        this.initListOfImages();
        mainTimer.start();

    }

    private JFrame frame;

    	
    private Field field;
    private int cellWidth() {return frame.getWidth() / (this.field.getLengthY());}
    private int cellHeight() {return frame.getHeight() / (this.field.getLengthX());}

    private BufferedImage rotateImage(BufferedImage img, int angle){
        int x = img.getWidth(null) / 2;
        int y = img.getHeight(null) / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), x, y);
        AffineTransformOp op = new AffineTransformOp(tx, TYPE_BILINEAR);
        return op.filter(img, null);
    }
    
    public void paint(Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
        for (int x = 0; x < this.field.getLengthX(); x++){
            for (int y = 0; y < this.field.getLengthY(); y++){
                (g2d).drawImage(new ImageIcon("images/grass.jpg").getImage(), y * cellHeight(), x * cellWidth(), cellWidth(), cellHeight(), null);
            }
        }
        for (int x = 0; x < this.field.getLengthY(); x++) {
		    for (int y = 0; y < this.field.getLengthX(); y++) {
		        Image img = getImage(this.field.getObjectOnField(new Coordinate(x, y)));
		        g2d.drawImage(img,
		        		y * cellHeight(),
		        		x * cellWidth(),
		                cellWidth(), cellHeight(),
		                null);
		    }
		}
}


	@Override
    public void actionPerformed(ActionEvent e) {
        this.snake.move(field);
        if (!this.snake.checkEndGame(this.field)) {
        	this.field.appleGenerator();
        } else {
        	for (int i = 0; i < this.field.getLengthX(); i++)
        		for(int j = 0; j < this.field.getLengthY(); j++)
        			new EmptySpace(new Coordinate(i, j), field);
        	this.field.surroundedByWall();
        	this.field.addRandomWall();
        	this.snake = this.field.addSnake();
        }
        repaint();
    }
}
