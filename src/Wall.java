public class Wall extends ImpenetrableObject{
	Wall(Coordinate coordinate) {
		super(coordinate);
	}
	
	@Override
    public void toInteractWithSnake(Snake snake, Field field) {
		super.toInteractWithSnake(snake, field);
	}
}
