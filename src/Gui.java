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
    private Map<String, Map<Direction, Image>> images = new HashMap();

    public final int fps = 7;
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
    	this.images.put("PieceOfSnake", createMap(new Direction[] {Direction.None}, new Image[] {new ImageIcon("images/snake_part.jpg").getImage()}));
    	this.images.put("SnakeHead", createMap(new Direction[] {Direction.None}, new Image[] {new ImageIcon("images/snake_part.jpg").getImage()}));
    	this.images.put("SnakeTail", createMap(new Direction[] {Direction.None}, new Image[] {new ImageIcon("images/snake_part.jpg").getImage()}));
    	this.images.put("SnakeTwist", createMap(new Direction[] {Direction.None}, new Image[] {new ImageIcon("images/snake_part.jpg").getImage()}));
       /* String fileName;
        ObjectOnField fieldObject = this.field.getObjectOnField(coordinate);
        if (fieldObject instanceof Apple)
            fileName = "images/red_apple.png";
        else if (fieldObject instanceof Wall)
            fileName = "images/wall.jpg";
        else if (fieldObject instanceof PieceOfSnake) {
        	fileName = "images/snake_part.jpg";
        }
        else
        	fileName = "images/grass.jpg";
        BufferedImage img = ImageIO.read(new File(fileName));
        rotateImage(img, 90);*/
    }
    
    private Image getImage(ObjectOnField objectOnField) {
		return this.images.get(objectOnField.toString()).get(Direction.None);
    }
    
    public Gui(JFrame frame) throws IOException {
        this.frame = frame;
        this.field = new Field(20, 20);
        this.field.surroundedByWall();
        this.field.addRandomWall();
        this.snake = this.field.addSnake();
        for (int i = 0; i < 50; i++)
        	this.field.addApple();
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
        repaint();
    }
}
