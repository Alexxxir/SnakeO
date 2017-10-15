import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {
	@Test
	public void TestIsInFrontWall() {
		Field field = new Field(100, 1);
		Snake snake = new Snake(new Coordinate(0, 0), field);
		new Wall(new Coordinate(1, 0), field);
		assertTrue(snake.IsInFrontWall(field));

	}

	@Test
	public void TestMoveWithoutEating() {
		Field field = new Field(100, 1);
		Snake snake = new Snake(new Coordinate(0, 0), field);
		for (int i = 1; i < field.getLengthX() - 1; i++) {
			snake.Move(field);
			assertEquals(snake.size(), 1);
		}

		for (int i = 0; i < field.getLengthX() - 2; i++) {
			assertTrue(field.GetObjectOnField(new Coordinate(i, 0)) instanceof EmptySpace);
		}
	}

	@Test
	public void TestMoveWithEating() {
		Field field = new Field(100, 1);
		Coordinate startCoordinate = new Coordinate(1, 0);
		Snake snake = new Snake(new Coordinate(0, 0), field);
		for (int i = 1; i < field.getLengthX() - 1; i++) {
			new Apple(startCoordinate, field);
			snake.Move(field);
			assertEquals(snake.size(), i + 1);
			startCoordinate = startCoordinate.GetNeighborCoordinate(Direction.Right);
		}

		for (int i = 0; i < field.getLengthX() - 1; i++) {
			assertTrue(field.GetObjectOnField(new Coordinate(i, 0)) instanceof PieceOfSnake);
		}
	}

}
