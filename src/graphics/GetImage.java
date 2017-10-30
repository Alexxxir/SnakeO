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
import game.MapOfDirections;
import objects.ObjectOnField;
import objects.PieceOfSnake;

class GetImage {

	GetImage() {
        this.initAnimation();
        this.initDirectionsOfSnake();
    }

	private Map<String, String[]> animationImages = new HashMap<String, String[]>();

	private Map<String, Map<Direction, Image>> images = new HashMap<String, Map<Direction, Image>>();
    private MapOfDirections mapOfDirections = new MapOfDirections();

    private Direction chooseSnakePieceTurn(PieceOfSnake pieceOfSnake) {
        if (pieceOfSnake.nameOfTheObject().equals("SnakeTail"))
            return pieceOfSnake.nextPiece.direction;
        if (pieceOfSnake.nameOfTheObject().equals("SnakeTwist"))
            return this.mapOfDirections.get(pieceOfSnake.nextPiece.direction, pieceOfSnake.direction);
        return pieceOfSnake.direction;
    }
    
    Image getImage(ObjectOnField objectOnField, int counter) {
    	Direction direction = Direction.Right;
    	if (objectOnField instanceof PieceOfSnake) {
    		PieceOfSnake pieceOfSnake = (PieceOfSnake)objectOnField;
    		direction = this.chooseSnakePieceTurn(pieceOfSnake);
    	}
		String obj = objectOnField.nameOfTheObject();
		return images.get(animationNameOfTheObject(obj, counter)).get(direction);
    }
    
    private BufferedImage rotateImage(BufferedImage img, int angle) {
        int x = img.getWidth(null) / 2;
        int y = img.getHeight(null) / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), x, y);
        AffineTransformOp op = new AffineTransformOp(tx, TYPE_BILINEAR);
        return op.filter(img, null);
    }

    private void initDirectionsOfSnake() {
        this.mapOfDirections.addDoubleDirections(Direction.Down, Direction.Right, Direction.Right);
        this.mapOfDirections.addDoubleDirections(Direction.Down, Direction.Left, Direction.Up);
        this.mapOfDirections.addDoubleDirections(Direction.Up, Direction.Right, Direction.Down);
        this.mapOfDirections.addDoubleDirections(Direction.Right, Direction.Down, Direction.Left);
    }


	private String animationNameOfTheObject(String objectName, int counter) {
    	if (animationImages.containsKey(objectName))
    		return animationImages.get(objectName)[counter % animationImages.get(objectName).length];
    	return "Undefined0";
    }

    private void initAnimation()
	{
		animationImages.put("SnakeTail", new String[] {"SnakeTail0", "SnakeTail1"});
		animationImages.put("SnakeHead", new String[] {"SnakeHead0", "SnakeHead1"});
		animationImages.put("SnakeTwist", new String[] {"SnakeTwist0"});
		animationImages.put("SnakePart", new String[] {"SnakePart0"});
		//animationImages.put("Wall", new String[] {"Wall0", "Wall1", "Wall2", "Wall3", "Wall4", "Wall5", "Wall6", "Wall7", "Wall8", "Wall9", "Wall10"});
        animationImages.put("Wall", new String[] {"Wall0"});
		animationImages.put("RottenApple", new String[] {"RottenApple0"});
		animationImages.put("EmptySpace", new String[] {"EmptySpace0"});
		animationImages.put("Clock", new String[] {"Clock0"});
		animationImages.put("Cherry", new String[] {"Cherry0"});
		animationImages.put("Apple", new String[] {"Apple0"});
        animationImages.put("Undefined", new String[] {"Undefined0"});
		
		for (String ObjectsNames: animationImages.keySet()) {
			for (String ObjectsAnimation: animationImages.get(ObjectsNames)) {
	            BufferedImage image;
				try {
                    image = ImageIO.read(
					        new File(String.format("images/%s.png", ObjectsAnimation)));
					Map<Direction, Image> map = new HashMap<Direction, Image>();
		            map.put(Direction.Down, rotateImage(image, 90));
		            map.put(Direction.Left, rotateImage(image, 180));
		            map.put(Direction.Up, rotateImage(image, 270));
		            map.put(Direction.Right, rotateImage(image, 0));
		            this.images.put(ObjectsAnimation, map);
				} catch (IOException e) {
					System.out.println(e);
				}
	        }
		}
	}

}

