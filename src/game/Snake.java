package game;
import java.util.LinkedList;
import java.util.Stack;

import objects.EmptySpace;
import objects.ImpenetrableObject;
import objects.ObjectOnField;
import objects.PieceOfSnake;

public class Snake {
    public PieceOfSnake tail;
    public PieceOfSnake head;
    private Direction direction;
    private int size;
    private Stack<PieceOfSnake> lastPart;

    Snake(Coordinate coordinate, Field field) {
        this.direction = Direction.Right;
        PieceOfSnake newPiece = new PieceOfSnake(coordinate.getNextCoordinate(Direction.Up), this.direction, null);
        field.addObjectOnField(newPiece);
        this.tail = newPiece;
        this.head = newPiece;
        this.size = 1;
        this.lastPart = new Stack<PieceOfSnake>();
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
                return false;
        }
        return true;
    }

    public void popBack(Field field) {
        if (this.size < 4) return;
        this.lastPart.add(this.tail);
        this.tail.nextPiece.lastPiece = null;
        field.deleteObjectOnField(this.tail.coordinate);
        this.tail = this.tail.nextPiece;
        this.size -= 1;
    }

    private ObjectOnField getNeighborObject(Field field) {
        return field.getObjectOnField(this.head.coordinate.getNextCoordinate(this.direction));
    }

    public boolean isInFrontWall(Field field) {
        return this.getNeighborObject(field) instanceof ImpenetrableObject;
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
