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
import static java.awt.image.AffineTransformOp.TYPE_BILINEAR;


public class Gui extends JPanel implements ActionListener{
	private Map<String, Map<Direction, Image>> images = new HashMap<String, Map<Direction, Image>>();
    public final int fps = 8;
    Timer timer = new Timer(1000/fps, this);
    private Map<Direction, Image> createMap(Direction[] directions, Image[] images) {
    	 Map<Direction, Image> map = new HashMap<Direction, Image>();
    	 for (int i = 0; i < directions.length; i++)
    		 map.put(directions[i], images[i]);
    	 return map;
    }


    private void initListOfImages() throws IOException {
   	    BufferedImage imgHead = ImageIO.read(new File("images/SnakeHead.png"));
   	    Map<Direction, Image> mapSnakeHead = new HashMap<Direction, Image>();
        mapSnakeHead.put(Direction.Down, rotateImage(imgHead, 90));
        mapSnakeHead.put(Direction.Left, rotateImage(imgHead, 180));
        mapSnakeHead.put(Direction.Up, rotateImage(imgHead, 270));
        mapSnakeHead.put(Direction.Right, rotateImage(imgHead, 0));
        this.images.put("SnakeHead", mapSnakeHead);
        
   	    BufferedImage imgTail = ImageIO.read(new File("images/SnakeTail.png"));
   	    Map<Direction, Image> mapSnakeTail = new HashMap<Direction, Image>();
   	    mapSnakeTail.put(Direction.Up, rotateImage(imgTail, 90));
   	    mapSnakeTail.put(Direction.Right, rotateImage(imgTail, 180));
        mapSnakeTail.put(Direction.Down, rotateImage(imgTail, 270));
        mapSnakeTail.put(Direction.Left, rotateImage(imgTail, 0));
        this.images.put("SnakeTail", mapSnakeTail);
        
   	    BufferedImage imgTwist = ImageIO.read(new File("images/SnakeTwist.png"));
   	    Map<Direction, Image> mapSnakeTwist = new HashMap<Direction, Image>();
   	    mapSnakeTwist.put(Direction.Down, rotateImage(imgTwist, 90));
   	    mapSnakeTwist.put(Direction.Up, rotateImage(imgTwist, 180));
   	    mapSnakeTwist.put(Direction.Left, rotateImage(imgTwist, 270));
   		mapSnakeTwist.put(Direction.Right, rotateImage(imgTwist, 0));
        this.images.put("SnakeTwist", mapSnakeTwist);
        
   	    BufferedImage imgPart = ImageIO.read(new File("images/SnakePart.png"));
   	    Map<Direction, Image> mapSnakePart = new HashMap<Direction, Image>();
   	    mapSnakePart.put(Direction.Up, rotateImage(imgPart, 90));
   	    mapSnakePart.put(Direction.Right, rotateImage(imgPart, 0));
        this.images.put("PieceOfSnake", mapSnakePart);
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
	    		if (pieceOfSnake.nextPiece.direction == Direction.Down && pieceOfSnake.direction == Direction.Right)
	    			return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Right);
	    		if (pieceOfSnake.nextPiece.direction == Direction.Left && pieceOfSnake.direction == Direction.Up)
	    			return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Right);
	    		if (pieceOfSnake.nextPiece.direction == Direction.Down && pieceOfSnake.direction == Direction.Left)
	    			return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Left);
	    		if (pieceOfSnake.nextPiece.direction == Direction.Right && pieceOfSnake.direction == Direction.Up)
	    			return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Left);
	    		if (pieceOfSnake.nextPiece.direction == Direction.Up && pieceOfSnake.direction == Direction.Right)
	    			return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Down);
	    		if (pieceOfSnake.nextPiece.direction == Direction.Left && pieceOfSnake.direction == Direction.Down)
	    			return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Down);
	    		if (pieceOfSnake.nextPiece.direction == Direction.Right && pieceOfSnake.direction == Direction.Down)
	    			return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Up);
	    		if (pieceOfSnake.nextPiece.direction == Direction.Up && pieceOfSnake.direction == Direction.Left)
	    			return this.images.get(objectOnField.nameOfTheObject()).get(Direction.Up);
	    		return this.images.get(objectOnField.nameOfTheObject()).get(pieceOfSnake.direction);
	    	} if (objectOnField.nameOfTheObject() == "PieceOfSnake") {
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
        this.game.snake.toInteractWithObject(field);
        if (!this.game.isEndGame()) {
        	this.field.appleGenerator();
        } else {
        	this.game.startNewGame();
        	this.field = this.game.field;
        }
        repaint();
    }
}
