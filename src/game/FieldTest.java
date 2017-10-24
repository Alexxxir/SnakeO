package game;

import static org.junit.Assert.*;

import org.junit.Test;

import objects.EmptySpace;
import objects.PieceOfSnake;
import objects.Wall;

public class FieldTest {

	@Test
	public void testS() {
		Field field = new Field(new Coordinate(2,2));
		field.surroundedByWall();
		for (int i = 0; i < field.getLengthX(); i++)
		{
			for (int j = 0; j < field.getLengthY(); j++)
				assertTrue(field.getObjectOnField(new Coordinate(i,j)) instanceof Wall);
		}
	}
	
	@Test
	public void testCountEmptySpace() {
		Field field = new Field(new Coordinate(2,2));
		assertTrue(field.countEmptySpace() == 4);
	}
	
	@Test
	public void testEnvirions() {
		Field field = new Field(new Coordinate(5, 5));
		field.surroundedByWall();
		assertTrue(field.isEmptyEnvirons(new Coordinate(2, 2)));
	}
	
	@Test
	public void testRandomCoordinateWithEmptySpace() {
		Field field = new Field(new Coordinate(3,3));
		assertTrue(field.getObjectOnField(field.getRandomCoordinateWithEmptySpace()) instanceof EmptySpace);
	}
	
	@Test
	public void testAddRandomWall() {
		Field field = new Field(new Coordinate(5,5));
		field.surroundedByWall();
		field.addRandomWall();
		assertTrue(field.getObjectOnField(new Coordinate(2, 2)) instanceof Wall);
	}
	
	@Test
	public void testAddObjectOnField() {
		Field field = new Field(new Coordinate(3,3));
		Wall wall = new Wall(new Coordinate(1,1));
		field.addObjectOnField(wall);
		assertTrue(field.getObjectOnField(new Coordinate(1, 1)) instanceof Wall);
	}
	
	@Test
	public void testDeleteObjectOnField() {
		Field field = new Field(new Coordinate(3,3));
		Wall wall = new Wall(new Coordinate(1,1));
		field.addObjectOnField(wall);
		field.deleteObjectOnField(wall);
		assertTrue(field.getObjectOnField(new Coordinate(1, 1)) instanceof EmptySpace);
	}
	
	@Test
	public void testAddSnake() {
		Field field = new Field(new Coordinate(5,5));
		field.addSnake();
		assertTrue(field.getObjectOnField(new Coordinate(1, 1)) instanceof PieceOfSnake);
	}
	
}
