final public class Coordinate {
    final public int x;
    final public int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coordinate(Coordinate coordinate) {
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public Coordinate getNeighborCoordinate(Direction direction) {
        if (direction == Direction.UpA)
            return new Coordinate(this.x, this.y + 1);
        else if (direction == Direction.DownA)
            return new Coordinate(this.x, this.y - 1);
        else if (direction == Direction.LeftA)
            return new Coordinate(this.x - 1, this.y);
        else if (direction == Direction.RightA)
            return new Coordinate(this.x + 1, this.y);
        else
            return this;
    }

}
