package objects.disposableObjects;

import game.Coordinate;
import game.Field;
import game.Snake;
import objects.PieceOfSnake;

import java.util.Random;

public class Clock extends DisposableObject {
    public Clock(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
        super.toInteractWithSnake(snake, field);
    }

    @Override
    public void EffectOfObject(Snake snake, Field field) {
        Random random = new Random();
        int rnd = random.nextInt(snake.getLastMovesSize());
        for (int i = 0; i < rnd; i++) {
            Coordinate lastTail = snake.getTailCoordinate();
            PieceOfSnake pieceOfSnake = (PieceOfSnake)field.getObjectOnField(snake.getHeadCoordinate());
            snake.setDirection(pieceOfSnake.direction);
            snake.pushBack(field);
            if (lastTail == snake.getTailCoordinate())
                return;
            snake.popFront(field);
        }
        PieceOfSnake snakeHead = (PieceOfSnake)field.getObjectOnField((snake.getHeadCoordinate()));
    	snake.setDirection(snakeHead.direction);
    }

    @Override
    public int getChanceOfOccurrence() {
        return 2;
    }

    @Override
    public String nameOfTheObject() {
        return "Clock";
    }
}
