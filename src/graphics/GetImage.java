package graphics;

import static java.awt.image.AffineTransformOp.TYPE_BILINEAR;

import java.awt.Image;
import java.awt.List;
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
import objects.PieceOfSnake;

public class GetImage {

	public Map<String, String[]> animationImages = new HashMap<String, String[]>();
	
    public Map<String, Map<Direction, Image>> images = new HashMap<String, Map<Direction, Image>>();
    private MapOfDirections mapOfDirections = new MapOfDirections();
    
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
        this.mapOfDirections.addDoubleDirections(Direction.Down, Direction.Right, Direction.Right);
        this.mapOfDirections.addDoubleDirections(Direction.Down, Direction.Left, Direction.Up);
        this.mapOfDirections.addDoubleDirections(Direction.Up, Direction.Right, Direction.Down);
        this.mapOfDirections.addDoubleDirections(Direction.Right, Direction.Down, Direction.Left);
    }
    
   /* public String animationNameOfTheObjectSnake(PieceOfSnake snakePart, int counter) 
    {
	    if (snakePart.lastPiece == null) {
	    	if (counter % 2 == 0)
	    		return "SnakeTail0";
	    	return "SnakeTail1";
	    }
	    if (snakePart.nextPiece == null) {
	    	if (counter % 2 == 0)
	    		return "SnakeHead0";
	    	return "SnakeHead1";
	    }
	    if (snakePart.nextPiece.direction != snakePart.direction)
	        return "SnakeTwist0";
	    return "SnakePart0";
    } */
    
    public String animationNameOfTheObjectSnake(String snakePart, int counter) 
    {
    	snakePart = GetName(snakePart);   		
    	
	    if (snakePart == "SnakeTail") {
	    	if (counter % 2 == 0)
	    		return "SnakeTail0";
	    	return "SnakeTail1";
	    }
	    if (snakePart == "SnakeHead") {
	    	if (counter % 2 == 0)
	    		return "SnakeHead0";
	    	return "SnakeHead1";
	    }
	    if (snakePart == "SnakeTwist")
	        return "SnakeTwist0";
	    return "SnakePart0";
    }
    
    public String animationNameOfTheObjectOther(String obj, int counter) 
    {
    	obj = GetName(obj);
	    if (obj == "Wall") {
	    	if (counter % 2 == 0)
	    		return "Wall0";
	    	return "Wall0";
	    }
	    if (obj == "EmptySpace") {
	    	if (counter % 2 == 0)
	    		return "EmptySpace0";
	    	return "EmptySpace0";
	    }
	    if (obj == "Apple") {
	    	if (counter % 2 == 0)
	    		return "Apple0";
	    	return "Apple0";
	    }
	    if (obj == "Cherry") {
	    	if (counter % 2 == 0)
	    		return "Cherry0";
	    	return "Cherry0";
	    }
	    if (obj == "Clock") {
	    	if (counter % 2 == 0)
	    		return "Clock0";
	    	return "Clock0";
	    }
	    return "Undefind";
    }
    
    public String GetName(String word)
    {
    	int number = 0;
    	for (int i = 0; i < word.length(); i++)
    	{
    		try 
    		{
    	        number = Character.getNumericValue(word.charAt(i));
    	        return word.substring(0, i);
    	    } 
    		catch (NumberFormatException e) 
    		{
    	    }	    		
    	}
    	return "None";
    }  
	
	public void initAnimation()
	{
		animationImages.put("SnakeTail", new String[] {"SnakeTail0", "SnakeTail1"});
		animationImages.put("SnakeHead", new String[] {"SnakeHead0", "SnakeHead1"});
		animationImages.put("SnakeTwist", new String[] {"SnakeTwist0"});
		animationImages.put("SnakePart", new String[] {"SnakePart0"});
		animationImages.put("Wall", new String[] {"Wall0"});
		animationImages.put("EmptySpace", new String[] {"EmptySpace0"});
		animationImages.put("Clock", new String[] {"Clock0"});
		animationImages.put("Cherry", new String[] {"Cherry0"});
		animationImages.put("Apple", new String[] {"Apple0"});
		
		for (String str: animationImages.keySet()) 
		{
			
			for (int i = 0; i < animationImages.get(str).length; i++) 
			{
				System.out.println(animationImages.get(str)[i]);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
	}

}

