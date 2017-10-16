import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {
	@Test
	public void TestIsInFrontWall() {
		Field field = new Field(100, 1);
		Snake snake = new Snake(new Coordinate(1, 0), field);
		new Wall(new Coordinate(2, 0), field);
		assertTrue(snake.isInFrontWall(field));

	}

	@Test
	public void TestMoveWithoutEating() {
		Field field = new Field(100, 1);
		Snake snake = new Snake(new Coordinate(1, 0), field);
		for (int i = 1; i < field.getLengthX() - 1; i++) {
			snake.move(field);
			assertEquals(snake.size(), 2);
		}

		for (int i = 0; i < field.getLengthX() - 2; i++) {
			assertTrue(field.getObjectOnField(new Coordinate(i, 0)) instanceof EmptySpace);
		}
	}

	@Test
	public void TestMoveWithEating() {
		Field field = new Field(100, 1);
		Coordinate startCoordinate = new Coordinate(2, 0);
		Snake snake = new Snake(new Coordinate(1, 0), field);
		for (int i = 1; i < field.getLengthX() - 1; i++) {
			new Apple(startCoordinate, field);
			snake.move(field);
			assertEquals(snake.size(), i + 2);
			startCoordinate = startCoordinate.getNeighborCoordinate(Direction.Down);
		}

		for (int i = 0; i < field.getLengthX() - 1; i++) {
			assertTrue(field.getObjectOnField(new Coordinate(i, 0)) instanceof PieceOfSnake);
		}
	}

}
