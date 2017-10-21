package objects;

import game.Coordinate;
import game.Field;
import game.Snake;

public abstract class ObjectOnField {
    public Coordinate coordinate;

    public ObjectOnField(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    protected int getX() {
        return this.coordinate.x;
    }
    protected int getY() {
        return this.coordinate.y;
    }

    public abstract void toInteractWithSnake(Snake snake, Field field);

    public String nameOfTheObject() {
        return null;
    };
}
