package objects;

import game.Coordinate;
import game.Direction;
import game.Field;
import game.Snake;

public abstract class ImpenetrableObject extends ObjectOnField {
    public ImpenetrableObject(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {;}
}
