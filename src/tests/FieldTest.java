package tests;
import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {

	@Test
	public void testAddRandomWall() {
		Field field = new Field(5, 5);
		field.surroundedByWall();
		field.addRandomWall();
		assertTrue(field.getObjectOnField(new Coordinate(2, 2)) instanceof Wall);
		assertTrue(field.checkEnvirons(new Coordinate(2, 2)));
	}
	
	@Test
	public void testCheckEmptySpase() {
		Field field = new Field(5, 5);
		assertFalse(field.checkEmptySpace());
		for (int i = 0; i < field.getLengthX(); i++)
			for (int j = 0; j < field.getLengthY(); j++)
				new Wall(new Coordinate(i, j), field);
		assertTrue(field.checkEmptySpace());
	}
	
	@Test
	public void testGetRandomCoordinateWithEmptySpace() {
		Field field = new Field(5, 5);
		new Wall(new Coordinate(1, 1), field);
		for (int i = 0; i < 1000; i++)
			assertNotEquals(field.getRandomCoordinateWithEmptySpace(), new Coordinate(1, 1));
		field.deleteObjectOnField(new Coordinate(1, 1));
		assertTrue(field.getObjectOnField(new Coordinate(1, 1)) instanceof EmptySpace);
	}
}
