package graphics;

import static java.awt.image.AffineTransformOp.TYPE_BILINEAR;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import game.Direction;
import game.MapOfDirections;
import objects.ObjectOnField;
import objects.PieceOfSnake;

public class GetImage {

	public Map<String, String[]> animationImages = new HashMap<String, String[]>();
	
    public Map<String, Map<Direction, Image>> images = new HashMap<String, Map<Direction, Image>>();
    private MapOfDirections mapOfDirections = new MapOfDirections();
    
    public Image getImage(ObjectOnField objectOnField, int counter) {
    	Direction direction = Direction.Right;
    	if (objectOnField instanceof PieceOfSnake) {
    		PieceOfSnake pieceOfSnake = (PieceOfSnake) objectOnField;
    		direction = pieceOfSnake.direction;
    	}
		String obj = objectOnField.nameOfTheObject();
		return images.get(animationNameOfTheObject(obj, counter)).get(direction);
    }
    
    private BufferedImage rotateImage(BufferedImage img, int angle)
    {
        int x = img.getWidth(null) / 2;
        int y = img.getHeight(null) / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), x, y);
        AffineTransformOp op = new AffineTransformOp(tx, TYPE_BILINEAR);
        return op.filter(img, null);
    }
    
    public void initDirectionsOfSnake() 
    {
        this.mapOfDirections.add(Direction.Down, Direction.Right, Direction.Right);
        this.mapOfDirections.add(Direction.Up, Direction.Left, Direction.Right);
        this.mapOfDirections.add(Direction.Down, Direction.Left, Direction.Up);
        this.mapOfDirections.add(Direction.Up, Direction.Right, Direction.Up);
        this.mapOfDirections.add(Direction.Up, Direction.Right, Direction.Down);
        this.mapOfDirections.add(Direction.Down, Direction.Left, Direction.Down);
        this.mapOfDirections.add(Direction.Right, Direction.Down, Direction.Left);
        this.mapOfDirections.add(Direction.Left, Direction.Up, Direction.Left);
    }
   
    
    public String animationNameOfTheObject(String objectName, int counter) 
    {
    	if (animationImages.containsKey(objectName))
    		return animationImages.get(objectName)[counter % animationImages.get(objectName).length];
    	return "Undefined0";
    }
	
	public void initAnimation()
	{
		animationImages.put("SnakeTail", new String[] {"SnakeTail0", "SnakeTail1"});
		animationImages.put("SnakeHead", new String[] {"SnakeHead0", "SnakeHead1"});
		animationImages.put("SnakeTwist", new String[] {"SnakeTwist0"});
		animationImages.put("SnakePart", new String[] {"SnakePart0"});
		animationImages.put("Wall", new String[] {"Wall0", "Wall1", "Wall2", "Wall3", "Wall4", "Wall5", "Wall6", "Wall7", "Wall8", "Wall9", "Wall10"});
		animationImages.put("RottenApple", new String[] {"RottenApple0"});
		animationImages.put("EmptySpace", new String[] {"EmptySpace0"});
		animationImages.put("Clock", new String[] {"Clock0"});
		animationImages.put("Cherry", new String[] {"Cherry0"});
		animationImages.put("Apple", new String[] {"Apple0"});
		
		for (String str: animationImages.keySet()) 
		{
			
			for (int i = 0; i < animationImages.get(str).length; i++) 
			{
	            BufferedImage imgHead;
				try {
					imgHead = ImageIO.read(
					        new File(String.format("images/%s.png", animationImages.get(str)[i])));
					Map<Direction, Image> map1 = new HashMap<Direction, Image>();
		            map1.put(Direction.Down, rotateImage(imgHead, 90));
		            map1.put(Direction.Left, rotateImage(imgHead, 180));
		            map1.put(Direction.Up, rotateImage(imgHead, 270));
		            map1.put(Direction.Right, rotateImage(imgHead, 0));
		            this.images.put(animationImages.get(str)[i], map1);
				} catch (IOException e) {
					System.out.println(e);
				}
	        }
		}
	}

}

