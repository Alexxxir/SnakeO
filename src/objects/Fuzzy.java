package objects;

import game.Coordinate;
import game.Field;
import game.Snake;

public class Fuzzy extends DisposableObject {

	public Fuzzy(Coordinate coordinate) {
		super(coordinate);
		
	}

	@Override
	public void EffectOfObject(Snake snake, Field field) {
		snake.popBack(field);
		snake.popBack(field);
		snake.popBack(field);
	}

	@Override
	public int getChanceOfOccurrence() {
		return 1;
	}

	@Override
	public String nameOfTheObject() {
		return "Fuzzy";
	}
}
