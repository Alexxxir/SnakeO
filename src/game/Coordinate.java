package game;
final public class Coordinate {
    final public int x;
    final public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public Coordinate getNextCoordinate(Direction direction) {
        if (direction == Direction.Right)
            return new Coordinate(this.x + 1, this.y);
        else if (direction == Direction.Left)
            return new Coordinate(this.x - 1, this.y);
        else if (direction == Direction.Up)
            return new Coordinate(this.x, this.y - 1);
        else if (direction == Direction.Down)
            return new Coordinate(this.x, this.y + 1);
        else
            return this;
    }

    public boolean equals(Coordinate coordinate) {
        return this.x == coordinate.x && this.y == coordinate.y;
    }
}
