import java.util.Random;

public class Game {
	
	public Field field;
	public Snake snake;
	
	Game(){
		Random random = new Random();
		int randx = 10 + random.nextInt(20);
		int randy = 10 + random.nextInt(20);
		this.field = new Field(Math.max(randx, randy), Math.min(randx, randy));
		this.field.surroundedByWall();
		this.field.addRandomWall();
		this.snake = this.field.addSnake();
	}
}
