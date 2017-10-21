package objects;

import game.Coordinate;
import game.Field;
import game.Snake;

public class Wall extends ImpenetrableObject{
    public Wall(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
        super.toInteractWithSnake(snake, field);
    }

    @Override
    public String nameOfTheObject() {
        return "Wall";
    }
}
