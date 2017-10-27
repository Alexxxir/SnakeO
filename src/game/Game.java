package game;
import java.util.Random;

public class Game {

    public Field field;
    public Snake snake;

    private Coordinate getRandomFieldSize() {
        Random random = new Random();
        int randx = 10 + random.nextInt(20);
        int randy = 10 + random.nextInt(20);
        return new Coordinate(Math.max(randx, randy),
                Math.min(randx, randy));
    }

    public boolean isEndGame() {
        return !this.snake.isPossibleToMove(this.field);
    }

    public void startNewGame(){
        this.field = new Field(getRandomFieldSize());
        this.field.toPlaceTheWalls();
        this.snake = this.field.addSnake();
    }


}
