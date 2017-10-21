
public class Fuzzy extends DisposableObject {

	Fuzzy(Coordinate coordinate) {
		super(coordinate);
		
	}

	@Override
	public void EffectOfObject(Snake snake, Field field) {
		for (int i = 0; i < 100; i++)
			snake.popBack(field);
	}

	@Override
	public int getChanceOfOccurrence() {
		return 1;
	}

}
