package game;

import java.util.ArrayList;
import java.util.Random;

import objects.disposableObjects.Apple;
import objects.disposableObjects.Cherry;
import objects.disposableObjects.Clock;
import objects.disposableObjects.DisposableObject;
import objects.disposableObjects.RottenApple;

class GeneratorOfDisposableObject {
	static void generateDisposableObject(Field field) {
        ArrayList<DisposableObject> disposableObjects = new ArrayList<>();

        disposableObjects.add(new Apple(field.getRandomCoordinateWithEmptySpace()));
        disposableObjects.add(new Cherry(field.getRandomCoordinateWithEmptySpace()));
        disposableObjects.add(new RottenApple(field.getRandomCoordinateWithEmptySpace()));
        disposableObjects.add(new Clock(field.getRandomCoordinateWithEmptySpace()));

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
