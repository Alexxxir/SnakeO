package game;
final public class Coordinate {
    final public int x;
    final public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coordinate(Coordinate coordinate) {
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public Coordinate getNextCoordinate(Direction direction) {
        if (direction == Direction.Right)
            return new Coordinate(this.x, this.y + 1);
        else if (direction == Direction.Left)
            return new Coordinate(this.x, this.y - 1);
        else if (direction == Direction.Up)
            return new Coordinate(this.x - 1, this.y);
        else if (direction == Direction.Down)
            return new Coordinate(this.x + 1, this.y);
        else
            return this;
    }

    public Coordinate getLastCoordinate(Direction direction) {
        if (direction == Direction.Left)
            return new Coordinate(this.x, this.y + 1);
        else if (direction == Direction.Right)
            return new Coordinate(this.x, this.y - 1);
        else if (direction == Direction.Down)
            return new Coordinate(this.x - 1, this.y);
        else if (direction == Direction.Up)
            return new Coordinate(this.x + 1, this.y);
        else
            return this;
    }

}
