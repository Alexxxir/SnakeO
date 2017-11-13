package graphics;

import static java.awt.image.AffineTransformOp.TYPE_BILINEAR;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import game.Direction;
import objects.ObjectOnField;

abstract public class Drower {
	public abstract Image getImage(ObjectOnField objectOnField, int counter);
	
//	public abstract String getObjectType() {}
	
    protected BufferedImage rotateImage(BufferedImage img, int angle) {
        int x = img.getWidth(null) / 2;
        int y = img.getHeight(null) / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), x, y);
        AffineTransformOp op = new AffineTransformOp(tx, TYPE_BILINEAR);
        return op.filter(img, null);
    }
    
    public abstract void initAnimationImages();

    protected void initAnimation( Map<String, String[]> animationImages, Map<String, Map<Direction, Image>> images)
	{
    	initAnimationImages();

    	for (String objectsNames: animationImages.keySet()) {
			for (String objectsAnimation: animationImages.get(objectsNames)) {
	            BufferedImage image;
				try {
                    image = ImageIO.read(
					        new File(String.format("images/%s.png", objectsAnimation)));
					Map<Direction, Image> map = new HashMap<>();
		            map.put(Direction.Down, rotateImage(image, 90));
		            map.put(Direction.Left, rotateImage(image, 180));
		            map.put(Direction.Up, rotateImage(image, 270));
		            map.put(Direction.Right, rotateImage(image, 0));
		            images.put(objectsAnimation, map);
				} catch (IOException e) {
					System.out.println(objectsAnimation + " не найден");
					System.exit(1);
				}
	        }
		}
	}
    

}
