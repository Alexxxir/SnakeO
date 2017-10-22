package objects.disposableObjects;

import game.Coordinate;
import game.Field;
import game.Snake;

public class RottenApple extends DisposableObject {

    public RottenApple(Coordinate coordinate) {
        super(coordinate);

    }

    @Override
    public void EffectOfObject(Snake snake, Field field) {
        snake.popBack(field);
        snake.popBack(field);
        snake.popBack(field);
    }

    @Override
    public int getChanceOfOccurrence() {
        return 2;
    }

    @Override
    public String nameOfTheObject() {
        return "RottenApple";
    }
}
