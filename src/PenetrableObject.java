public abstract class PenetrableObject extends ObjectOnField
{
	PenetrableObject(Coordinate coordinate) {
		super(coordinate);
	}
	
	@Override
	public void toInteractWithSnake(Snake snake, Field field) {
		snake.pushFront(field);
		snake.popBack(field);
	}
}
