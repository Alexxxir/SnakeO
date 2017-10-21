public class Cherry extends DisposableObject {
	Cherry(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public void toInteractWithSnake(Snake snake, Field field) {
		super.toInteractWithSnake(snake, field);
	}

	@Override
	public void EffectOfObject(Snake snake, Field field) {
		for (int i = 0; i < 100; i++)
			snake.pushBack(field);	
	}

	@Override
	public int getChanceOfOccurrence() {
		return 2;
	}
}
