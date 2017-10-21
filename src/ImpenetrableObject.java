public abstract class ImpenetrableObject extends ObjectOnField {
	ImpenetrableObject(Coordinate coordinate) {
		super(coordinate);
	}
	
	@Override
	public void toInteractWithSnake(Snake snake, Field field) {;}
}
