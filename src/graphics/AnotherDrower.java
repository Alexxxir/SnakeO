package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import game.Direction;
import objects.ObjectOnField;

public class AnotherDrower extends Drower {
	private Map<String, String[]> animationImages = new HashMap<>();
	private Map<String, Map<Direction, Image>> images = new HashMap<>();
	
	AnotherDrower() {
		this.initAnimation(animationImages, images);
	}
	
	public void initAnimationImages() {
        animationImages.put("Wall", new String[] {"Wall0"});
        animationImages.put("RottenApple", new String[] {"RottenApple0"});
		animationImages.put("EmptySpace", new String[] {"EmptySpace0"});
		animationImages.put("Clock", new String[] {"Clock0"});
		animationImages.put("Cherry", new String[] {"Cherry0"});
		animationImages.put("Apple", new String[] {"Apple0"});
        animationImages.put("Undefined", new String[] {"Undefined0"});
	}


	
	public Image getImage(ObjectOnField objectOnField, int counter) {
		return images.get(animationNameOfTheObject(objectOnField.nameOfTheObject(), counter)).get(Direction.Right);
	}
	
	private String animationNameOfTheObject(String objectName, int counter) {
    	if (animationImages.containsKey(objectName))
    		return animationImages.get(objectName)[counter % animationImages.get(objectName).length];
    	return "Undefined0";
    }
	


}
