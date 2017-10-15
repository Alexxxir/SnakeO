public class Snake {
    private PieceOfSnake tail;
    private PieceOfSnake head;
    private Direction direction;
    private int size;

    Snake(Coordinate coordinate, Field field) {
        this.direction = Direction.Right;
        PieceOfSnake newPiece = new PieceOfSnake(coordinate, field, this.direction, null);
        this.tail = newPiece;
        this.head = newPiece;
        this.size = 1;
    }

    public int size() {
        return this.size;
    }

    private void Push(Field field) {
        PieceOfSnake newPiece = new PieceOfSnake(
                this.head.coordinate.GetNeighborCoordinate(this.direction),
                field, this.direction, this.head);
        this.head = newPiece;
        this.size += 1;
    }

    private void Pop(Field field) {
        this.tail.nextPiece.lastPiece = null;
        field.DeleteObjectOnField(this.tail.coordinate);
        this.tail = this.tail.nextPiece;
        this.size -= 1;
    }

    private ObjectOnField GetNeighborObject(Field field) {
        return field.GetObjectOnField(this.head.coordinate.GetNeighborCoordinate(this.direction));
    }

    public boolean IsInFrontWall(Field field) {
        return this.GetNeighborObject(field) instanceof ImpenetrableObject;
    }

    public void Move(Field field) {
    	boolean needToGrow = this.GetNeighborObject(field) instanceof Apple;
        this.Push(field);
        if (!(needToGrow)) {
            this.Pop(field);
        }
    }

}
