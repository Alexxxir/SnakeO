package objects.disposableObjects;

import game.Coordinate;
import game.Field;
import game.Snake;
import objects.PenetrableObject;

import java.util.ArrayList;
import java.util.Random;

public abstract class DisposableObject extends PenetrableObject {
    public DisposableObject(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
        super.toInteractWithSnake(snake, field);
        EffectOfObject(snake, field);
    }

    public abstract void EffectOfObject(Snake snake, Field field);
    public abstract int getChanceOfOccurrence();

    public static void generateDisposableObject(Field field) {
        ArrayList<DisposableObject> disposableObjects = new ArrayList<>();

        disposableObjects.add(new Apple(field.getRandomCoordinateWithEmptySpace()));
        disposableObjects.add(new Cherry(field.getRandomCoordinateWithEmptySpace()));
        disposableObjects.add(new RottenApple(field.getRandomCoordinateWithEmptySpace()));

        if (field.countEmptySpace() < (field.getLengthX() * field.getLengthX() / 10)) {
            return;
        }
        int chance = 1;
        for (int i = 0; i < field.getLengthX(); i++) {
            for (int j = 0; j < field.getLengthY(); j++)
                if (field.getObjectOnField(new Coordinate(i, j)) instanceof DisposableObject) {
                    Random random = new Random();
                    chance = random.nextInt(100);
                    break;
                }
            if (chance != 1) {
                break;
            }
        }
        for (DisposableObject object: disposableObjects) {
            if (object.getChanceOfOccurrence() > chance) {
                field.addObjectOnField(object);
            }
        }
    }
}
