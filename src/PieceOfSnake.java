public class PieceOfSnake extends ImpenetrableObject {
	
	@Override
	public String toString() {
		if (this.lastPiece == null)
			return "SnakeTail";
		if (this.nextPiece == null)
			return "SnakeHead";
		if (this.lastPiece.direction != this.direction)
			return "SnakeTwist";
		return "PieceOfSnake";
	}
	
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
