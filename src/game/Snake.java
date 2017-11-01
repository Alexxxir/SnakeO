package game;
import java.util.Stack;

import objects.EmptySpace;
import objects.ImpenetrableObject;
import objects.PieceOfSnake;

public class Snake {
    private PieceOfSnake tail;
    private PieceOfSnake head;
    private Direction direction;
    private int size;
    private Stack<PieceOfSnake> lastPart;

    public Coordinate getTailCoordinate() {
        return tail.coordinate;
    }

    public Coordinate getHeadCoordinate() {
        return head.coordinate;
    }

    public int getLastMovesSize() {
        return this.lastPart.size();
    }

    public Snake(Coordinate coordinate, Field field) {
        this.direction = Direction.Right;
        PieceOfSnake newPiece = new PieceOfSnake(coordinate, this.direction, null);
        field.addObjectOnField(newPiece);
        this.tail = newPiece;
        this.head = newPiece;
        this.size = 1;
        this.lastPart = new Stack<>();
        this.pushFront(field);
    }

    public int size() {
        return this.size;
    }

    public void pushFront(Field field) {
        PieceOfSnake newPiece = new PieceOfSnake(
                this.head.coordinate.getNextCoordinate(this.direction),
                this.direction, this.head);
        field.addObjectOnField(newPiece);
        this.head = newPiece;
        this.size += 1;
    }

    public void pushBack(Field field) {
        if (!this.lastPart.isEmpty()) {
            if (field.getObjectOnField(this.lastPart.peek().coordinate) instanceof EmptySpace) {
                field.addObjectOnField(this.lastPart.peek());
                this.tail.lastPiece = this.lastPart.peek();
                this.tail = this.lastPart.pop();
                this.size += 1;
            }
        }
    }

    public boolean isPossibleToMove(Field field) {
        for (Direction direction : Direction.values()) {
            if (!(field.getObjectOnField(this.head.coordinate.getNextCoordinate(direction)) instanceof ImpenetrableObject))
                return true;
        }
        return false;
    }

    public void popBack(Field field) {
        if (this.size < 4) return;
        this.lastPart.add(this.tail);
        this.tail.nextPiece.lastPiece = null;
        field.deleteObjectOnField(this.tail);
        this.tail = this.tail.nextPiece;
        this.size -= 1;
    }

    public void popFront(Field field) {
        if (this.size < 4) return;
        this.head.lastPiece.nextPiece = null;
        field.deleteObjectOnField(this.head);
        this.head = this.head.lastPiece;
        this.size -= 1;
    }

    public void toInteractWithObject(Field field) {
        field.getObjectOnField(
                this.head.coordinate.getNextCoordinate(
                        this.direction)).toInteractWithSnake(
                this, field);
    }

    public void setDirection(Direction direction) {
        if (this.direction.ordinal() + direction.ordinal() != 5)
            this.direction = direction;
    }

}
