package graphics;


import java.awt.Image;
import objects.ObjectOnField;

public class ImageGetter {

	private SnakePieceDrower snakePieceDrawer;

	private AnotherDrower anotherDrawer;
	private Drower[] drawers;

	public ImageGetter() {
		this.snakePieceDrawer = new SnakePieceDrower();
		this.anotherDrawer = new AnotherDrower();
    }

    
    Image getImage(ObjectOnField objectOnField, int counter) {
    	if (objectOnField.nameOfTheObject().equals("PieceOfSnake")) {
    		return this.snakePieceDrawer.getImage(objectOnField, counter);}
    	else
    		return this.anotherDrawer.getImage(objectOnField, counter);
    }
   


}
/*
public class ImageGetter2 {

	private Drower[] drawers;

	public ImageGetter(Drower[] drawers) {
    }

    
    Image getImage(ObjectOnField objectOnField, int counter) {
    	if (objectOnField.nameOfTheObject().equals("PieceOfSnake")) {
    		return this.snakePieceDrawer.getImage(objectOnField, counter);}
    	else
    		return this.anotherDrawer.getImage(objectOnField, counter);
    }
   


}
*/