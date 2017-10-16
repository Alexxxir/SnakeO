import java.util.Random;

public class Field {
	private ObjectOnField[][] field;
	public Snake snake;
	Field(int weidht, int height){
	    this.field = new ObjectOnField[height][weidht];
        for (int x = 0; x < this.getLengthX(); x++)
            for (int y = 0; y < this.getLengthY(); y++)
                this.field[x][y] = new EmptySpace(new Coordinate(x, y), this);
	}
		
	public int getLengthX() {
		return this.field.length;
	}
	
	public int getLengthY() {
		return this.field[0].length;
	}
    
	public void surroundedByWall() {
    	for (int i = 0; i < this.getLengthX(); i++)
    		for (int j = 0; j < this.getLengthY(); j++)
    		{
    			if (i==0 || j == 0 || i == this.getLengthX() - 1 || j == this.getLengthY() - 1)
    			{
    				Wall wall = new Wall(new Coordinate(i, j), this);
    				addObjectOnField(wall);
    			}
    		}
		
	}

	public ObjectOnField getObjectOnField(Coordinate coordinate) {
        return field[coordinate.x][coordinate.y];
    }

    public boolean checkEndGame() {
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
    
    private Coordinate getRandomCoordinateWithEmptySpace() {
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
    
    public void addRandomWall() {
    	int count = this.getLengthX() * this.getLengthY() * 2;
    	while (count > 0)
    	{
        	if (this.checkEndGame())
        		return;
	        Coordinate emptyCoordinate = this.getRandomCoordinateWithEmptySpace();
	        if (checkEnvirons(emptyCoordinate))
	        {
	        	Wall wall = new Wall(emptyCoordinate, this);
				addObjectOnField(wall);
	        }
			count--;
    	}   
    }

    public void addApple() {
    	if (this.checkEndGame())
    		return;
    	Coordinate emptyCoordinate = this.getRandomCoordinateWithEmptySpace();
        new Apple(emptyCoordinate, this);
    }

    public Snake addSnake() {
    	Coordinate emptyCoordinate;
    	do
    	{
    		emptyCoordinate = this.getRandomCoordinateWithEmptySpace();
    	}
    	while(!(this.getObjectOnField(emptyCoordinate.getNeighborCoordinate(Direction.Left)) instanceof EmptySpace));
    	emptyCoordinate = new Coordinate(2, 1);
    	return new Snake(emptyCoordinate, this);
    	
    }
    
    public void addObjectOnField(ObjectOnField objectOnField) {
        field[objectOnField.coordinate.x][objectOnField.coordinate.y] = objectOnField;
    }

    public void deleteObjectOnField(Coordinate coordinate) {
        field[coordinate.x][coordinate.y] = new EmptySpace(coordinate, this);
    }
}
