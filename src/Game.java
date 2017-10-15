public class Game {
	
	Game(){
		Field field = new Field(5, 5);
		Snake snake = new Snake(new Coordinate(0, 0), field);
	}
}
