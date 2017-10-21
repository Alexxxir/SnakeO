public class Apple extends DisposableObject {
    Apple(Coordinate coordinate) {
        super(coordinate);
    }
    
	@Override
	public void toInteractWithSnake(Snake snake, Field field) {
		super.toInteractWithSnake(snake, field);
	}

	@Override
	public void EffectOfObject(Snake snake, Field field) {
		snake.pushBack(field);	
	}

	@Override
	public int getChanceOfOccurrence() {
		return 2;
	}
}
