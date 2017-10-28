package game;
import java.util.Random;

import objects.disposableObjects.DisposableObject;
import objects.EmptySpace;
import objects.ObjectOnField;
import objects.Wall;

public class Field {
    private ObjectOnField[][] field;
    public Field(Coordinate fieldSize){
        this.field = new ObjectOnField[fieldSize.x][fieldSize.y];
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

    public void surroundByWall() {
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

    public int countEmptySpace() {
        int count = 0;
        for (int i = 0; i < this.getLengthX(); i++)
            for (int j = 0; j < this.getLengthY(); j++)
                if (this.getObjectOnField(new Coordinate(i, j)) instanceof EmptySpace)
                    count += 1;
        return count;
    }

    public boolean isEmptyEnvirons(Coordinate coordinate) {
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
        do {
            int countWall = 0;
            do {
                countWall = 0;
                randomX = random.nextInt(this.getLengthX() - 2) + 1;
                randomY = random.nextInt(this.getLengthY() - 2) + 1;
                for (Direction direction: Direction.values()) {
                    if (this.getObjectOnField(
                            new Coordinate(
                                    randomX, randomY).getNextCoordinate(direction)) instanceof Wall) {
                        countWall += 1;
                    }
                }
            }
            while (countWall >= 3);
        }
        while (!(this.getObjectOnField(new Coordinate(randomX, randomY)) instanceof EmptySpace));
        return new Coordinate(randomX, randomY);
    }

    public void addRandomWall() {
        int count = this.getLengthX() * this.getLengthY() * 2;
        while (count > 0)
        {
            if (this.countEmptySpace() == 0)
                return;
            Coordinate emptyCoordinate = this.getRandomCoordinateWithEmptySpace();
            if (isEmptyEnvirons(emptyCoordinate))
            {
                addObjectOnField(new Wall(emptyCoordinate));
            }
            count--;
        }
    }

    public void toPlaceTheWalls() {
        this.surroundByWall();
        this.addRandomWall();
    }

    public void objectGenerator() {
    	GeneratorOfDisposableObject.generateDisposableObject(this);
    }

    public Snake addSnake() {
        Coordinate emptyCoordinate = new Coordinate(1, 1);
        return new Snake(emptyCoordinate, this);
    }

    public void addObjectOnField(ObjectOnField objectOnField) {
        field[objectOnField.coordinate.x][objectOnField.coordinate.y] = objectOnField;
    }

    public void deleteObjectOnField(ObjectOnField objectOnField) {
        this.addObjectOnField(new EmptySpace(objectOnField.coordinate));
    }
}
