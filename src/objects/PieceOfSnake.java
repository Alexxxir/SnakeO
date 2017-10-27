package objects;

import game.Coordinate;
import game.Direction;
import game.Field;
import game.Snake;

public class PieceOfSnake extends ImpenetrableObject {

    public PieceOfSnake lastPiece;
    public PieceOfSnake nextPiece;
    public final Direction direction;

    public PieceOfSnake(Coordinate coordinate,
                        Direction diection, PieceOfSnake lastPiece) {
        super(coordinate);
        this.direction = diection;
        this.lastPiece = lastPiece;
        this.nextPiece = null;
        if (lastPiece != null)
            lastPiece.nextPiece = this;
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
        super.toInteractWithSnake(snake, field);
    }

    @Override
    public String nameOfTheObject() {
        if (this.lastPiece == null)
            return "SnakeTail";
        if (this.nextPiece == null)
            return "SnakeHead";
        if (this.nextPiece.direction != this.direction)
            return "SnakeTwist";
        return "SnakePart";
    }
}
