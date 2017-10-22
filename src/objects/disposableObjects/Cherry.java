package objects.disposableObjects;

import game.Coordinate;
import game.Field;
import game.Snake;

public class Cherry extends DisposableObject {
    public Cherry(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
        super.toInteractWithSnake(snake, field);
    }

    @Override
    public void EffectOfObject(Snake snake, Field field) {
        snake.pushBack(field);
        snake.pushBack(field);
    }

    @Override
    public int getChanceOfOccurrence() {
        return 3;
    }

    @Override
    public String nameOfTheObject() {
        return "Cherry";
    }
}
