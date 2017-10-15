public class PieceOfSnake extends ImpenetrableObject {
    public PieceOfSnake lastPiece;
    public PieceOfSnake nextPiece;
    final Direction direction;
    
    PieceOfSnake(Coordinate coordinate, Field field,
                 Direction diection, PieceOfSnake lastPiece) {
        super(coordinate, field);
        this.direction = diection;
        this.lastPiece = lastPiece;
        this.nextPiece = null;
        if (lastPiece != null)
            lastPiece.nextPiece = this;
    }


}
