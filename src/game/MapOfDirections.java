package game;

import java.util.*;

public class MapOfDirections {
    private HashMap<Direction, ArrayList<Direction>[]> map;

    public MapOfDirections() {
        this.map = new HashMap<Direction, ArrayList<Direction>[]>();
        for (Direction direction: Direction.values()) {
            this.map.put(
                    direction, new ArrayList[] {
                            new ArrayList(),new ArrayList()});
        }
    }
    public void add(Direction keyFirst,
               Direction keySecond,
               Direction value) {
        this.map.get(value)[0].add(keyFirst);
        this.map.get(value)[1].add(keySecond);
    }

    public void addDoubleDirections(Direction keyFirst,
                                 Direction keySecond,
                                 Direction value) {
        this.add(keyFirst, keySecond, value);
        ArrayList<Direction> anotherDirections = new ArrayList<Direction>();
        for (Direction direction: Direction.values()) {
            if (direction != Direction.None && direction != keyFirst &&
                    direction != keySecond) {
                anotherDirections.add(direction);
            }
        }
        if (keyFirst.ordinal() + anotherDirections.get(0).ordinal() != 5) {
            this.add(anotherDirections.get(0), anotherDirections.get(1), value);
        } else {
            this.add(anotherDirections.get(1), anotherDirections.get(0), value);
        }

    }

    public Direction get(Direction keyFirst, Direction keySecond) {
        for (Direction direction: Direction.values()) {
            for (int i = 0; i < this.map.get(direction)[0].size(); i++) {
                if (this.map.get(direction)[0].get(i) == keyFirst &&
                        this.map.get(direction)[1].get(i) == keySecond) {
                    return direction;
                }
            }
        }
        return Direction.None;
    }
}
