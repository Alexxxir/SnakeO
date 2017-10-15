import java.util.Random;

public class Field {
	private ObjectOnField[][] field;
	Field(int weidht, int height){
	    this.field = new ObjectOnField[weidht][height];
        for (int x = 0; x < this.getLengthX(); x++)
            for (int y = 0; y < this.getLengthY(); y++)
                this.field[x][y] = new EmptySpace(new Coordinate(x, y), this);
	}
		
	public int getLengthX()
	{
		return this.field.length;
	}
	
	public int getLengthY()
	{
		return this.field[0].length;
	}
    
    public void SurroundedByWall()
    {
    	for (int i = 0; i < this.getLengthX(); i++)
    		for (int j = 0; j < this.getLengthX(); j++)
    		{
    			if (i==0 || j == 0 || i == this.getLengthX() - 1 || j == this.getLengthY())
    			{
    				Wall wall = new Wall(new Coordinate(i, j), this);
    				AddObjectOnField(wall);
    			}
    		}
    }

	public ObjectOnField GetObjectOnField(Coordinate coordinate)
    {
        return field[coordinate.x][coordinate.y];
    }

    public boolean CheckEndGame()
    {
        for (int i = 0; i < this.getLengthX(); i++)
            for (int j = 0; j < this.getLengthY(); j++)
                if (field[i][j] instanceof EmptySpace)
                    return false;
        return true;
    }
    
    public boolean CheckEnvirons(Coordinate coordinate)
    {
    	for (int x = coordinate.x - 1; x < coordinate.x + 2; x++)
    		for (int y = coordinate.y - 1; y <  coordinate.y + 2; y++)
    		{
    			if (!(GetObjectOnField(new Coordinate(x, y)) instanceof EmptySpace))
					return false;
    		}
    	return true;
    }
    
    public void AddRandomWall(int count)
    {
    	while (count > 0)
    	{
        	if (CheckEndGame())
        		return;
        	boolean flag = false;
        	for (int i = 1; i < getLengthX() - 1; i++)
        		for (int j = 1; j < getLengthY() - 1; j++)
        		{
        			if (CheckEnvirons(new Coordinate(i, j)))
        				flag = true;
        		}
        	if (!flag)
        		return;
	        Random random = new Random();
	        int randomX;
	        int randomY;
	        do
	        {
	            randomX = random.nextInt(this.getLengthX() - 1);
	            randomY = random.nextInt(this.getLengthY() - 1);
	        }
	        while (!(this.field[randomX][randomY] instanceof EmptySpace));
	        if (CheckEnvirons(new Coordinate(randomX, randomY)))
	        {
	        	Wall wall = new Wall(new Coordinate(randomX, randomY), this);
				AddObjectOnField(wall);
				count--;
	        }
    	}   
    }

    public void AddApple()
    {
        Random random = new Random();
        int randomX;
        int randomY;
        do
        {
            randomX = random.nextInt(this.getLengthX() - 1);
            randomY = random.nextInt(this.getLengthY() - 1);
        }
        while (!(this.field[randomX][randomY] instanceof EmptySpace));
        new Apple(new Coordinate(randomX, randomY), this);
    }

    public void AddObjectOnField(ObjectOnField objectOnField) {
        field[objectOnField.coordinate.x][objectOnField.coordinate.y] = objectOnField;
    }

    public void DeleteObjectOnField(Coordinate coordinate) {
        field[coordinate.x][coordinate.y] = new EmptySpace(coordinate, this);
    }
}
