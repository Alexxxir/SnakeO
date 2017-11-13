package graphics;

import java.awt.Image;
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

public class SnakePieceDrower extends Drower {
	private Map<String, String[]> animationImages = new HashMap<>();
	private Map<String, Map<Direction, Image>> images = new HashMap<>();

	SnakePieceDrower() {
		this.initDirectionsOfSnake();
		this.initAnimation(animationImages, images);
	}

    private Direction chooseSnakePieceTurn(PieceOfSnake pieceOfSnake) {
        if (pieceOfSnake.lastPiece == null)
            return pieceOfSnake.nextPiece.direction;
        if (pieceOfSnake.nextPiece == null)
        	return pieceOfSnake.direction;
        if (pieceOfSnake.nextPiece.direction != pieceOfSnake.direction)
            return this.mapOfDirections.get(pieceOfSnake.nextPiece.direction, pieceOfSnake.direction);
        return pieceOfSnake.direction;
    }
	
    private MapOfDirections mapOfDirections = new MapOfDirections();
    
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
	
	public Image getImage(ObjectOnField objectOnField, int counter) {
		PieceOfSnake pieceOfSnake = (PieceOfSnake)objectOnField;
		String typePiecePart;
	    if (pieceOfSnake.lastPiece == null)
	    	typePiecePart = "SnakeTail";
	    else if (pieceOfSnake.nextPiece == null)
	    	typePiecePart = "SnakeHead";
	    else if (pieceOfSnake.nextPiece.direction != pieceOfSnake.direction)
	        typePiecePart = "SnakeTwist";
	    else 
	    	typePiecePart = "SnakePart";
	    return images.get(animationNameOfTheObject(typePiecePart, counter)).get(chooseSnakePieceTurn(pieceOfSnake));
	}
	
	public void initAnimationImages() {
		animationImages.put("SnakeTail", new String[] {"SnakeTail0", "SnakeTail1"});
		animationImages.put("SnakeHead", new String[] {"SnakeHead0", "SnakeHead1"});
		animationImages.put("SnakeTwist", new String[] {"SnakeTwist0"});
		animationImages.put("SnakePart", new String[] {"SnakePart0"});
	}
}
