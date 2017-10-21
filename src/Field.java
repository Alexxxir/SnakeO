import java.util.Random;

public class Field {
	private ObjectOnField[][] field;
	Field(Coordinate fieldSize){
	    this.field = new ObjectOnField[fieldSize.y][fieldSize.x];
        for (int x = 0; x < this.getLengthX(); x++)
            for (int y = 0; y < this.getLengthY(); y++)
            	addObjectOnField(new EmptySpace(new Coordinate(x, y)));
	}
		
	public int getLengthX() {
		return this.field.length;
	}
	
	public int getLengthY() {
		return this.field[0].length;
	}
    
	private void surroundedByWall() {
    	for (int i = 0; i < this.getLengthX(); i++)
    		for (int j = 0; j < this.getLengthY(); j++)
    		{
    			if (i==0 || j == 0 || i == this.getLengthX() - 1 || j == this.getLengthY() - 1)
    			{
    				addObjectOnField(new Wall(new Coordinate(i, j)));
    			}
    		}
		
	}

	public ObjectOnField getObjectOnField(Coordinate coordinate) {
        return field[coordinate.x][coordinate.y];
    }

    public boolean checkEmptySpace() {
        for (int i = 0; i < this.getLengthX(); i++)
            for (int j = 0; j < this.getLengthY(); j++)
                if (field[i][j] instanceof EmptySpace)
                    return false;
        return true;
    }
    
    public boolean checkEnvirons(Coordinate coordinate) {
    	int count = 0;
    	for (int x = coordinate.x - 1; x < coordinate.x + 2; x++)
    		for (int y = coordinate.y - 1; y <  coordinate.y + 2; y++)
    		{
    			if (!(getObjectOnField(new Coordinate(x, y)) instanceof EmptySpace))
					count++;
    		}
    	return count <= 1;
    }
    
    public Coordinate getRandomCoordinateWithEmptySpace() {
    	Random random = new Random();
    	int randomX;
        int randomY;
        do
        {
            randomX = random.nextInt(this.getLengthX() - 1);
            randomY = random.nextInt(this.getLengthY() - 1);
        }
        while (!(this.field[randomX][randomY] instanceof EmptySpace));
        return new Coordinate(randomX, randomY);
    }
    
    private void addRandomWall() {
    	int count = this.getLengthX() * this.getLengthY() * 2;
    	while (count > 0)
    	{
        	if (this.checkEmptySpace())
        		return;
	        Coordinate emptyCoordinate = this.getRandomCoordinateWithEmptySpace();
	        if (checkEnvirons(emptyCoordinate))
	        {
				addObjectOnField(new Wall(emptyCoordinate));
	        }
			count--;
    	}   
    }
    
    public void toPlaceTheWalls() {
    	this.surroundedByWall();
    	this.addRandomWall();
    }
    
    public void appleGenerator() {
    	DisposableObject.generateDisposableObject(this);
    }

    public Snake addSnake() {
    	Coordinate emptyCoordinate = new Coordinate(2, 1);
    	return new Snake(emptyCoordinate, this);
    	
    }
    
    public void addObjectOnField(ObjectOnField objectOnField) {
        field[objectOnField.coordinate.x][objectOnField.coordinate.y] = objectOnField;
    }

    public void deleteObjectOnField(Coordinate coordinate) {
    	this.addObjectOnField(new EmptySpace(coordinate));
    }
}
